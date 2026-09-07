import { useCallback, useState } from 'react'
import { useAuth } from '../context/auth-context'
import { getProfile, createProfile, updateProfile, getProfileCompletion } from '../services/profile'
import type { ProfileData, ProfileCompletionData, CreateProfilePayload, ApiError } from '../types/api'

type ProfileState = 'idle' | 'loading' | 'error' | 'loaded'

export function useProfileApi() {
  const { user } = useAuth()
  const [profile, setProfile] = useState<ProfileData | null>(null)
  const [completion, setCompletion] = useState<ProfileCompletionData | null>(null)
  const [state, setState] = useState<ProfileState>('idle')
  const [error, setError] = useState<ApiError | null>(null)

  const userId = user?.id

  const fetchProfile = useCallback(async () => {
    if (!userId) return
    setState('loading')
    setError(null)
    try {
      const data = await getProfile(userId)
      setProfile(data)
      setState('loaded')
    } catch (err) {
      const apiError = err as ApiError
      if (apiError.kind === 'not-found') {
        setProfile(null)
        setState('loaded')
      } else {
        setError(apiError)
        setState('error')
      }
    }
  }, [userId])

  const fetchCompletion = useCallback(async () => {
    if (!userId) return
    try {
      const data = await getProfileCompletion(userId)
      setCompletion(data)
    } catch {
      // completion is non-critical
    }
  }, [userId])

  const loadProfile = useCallback(async () => {
    await fetchProfile()
    await fetchCompletion()
  }, [fetchProfile, fetchCompletion])

  const saveProfile = useCallback(
    async (payload: CreateProfilePayload, isUpdate: boolean) => {
      if (!userId) return
      setState('loading')
      setError(null)
      try {
        const data = isUpdate
          ? await updateProfile(userId, payload)
          : await createProfile(userId, payload)
        setProfile(data)
        setState('loaded')
        await fetchCompletion()
      } catch (err) {
        const apiError = err as ApiError
        setError(apiError)
        setState('error')
        throw apiError
      }
    },
    [userId, fetchCompletion],
  )

  return {
    profile,
    completion,
    state,
    error,
    loadProfile,
    saveProfile,
  }
}

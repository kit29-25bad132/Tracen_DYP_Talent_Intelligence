import { request } from './api-client'
import type { ProfileData, ProfileCompletionData, CreateProfilePayload } from '../types/api'

function profilePath(userId: number): string {
  return `/users/${userId}/profile`
}

export async function getProfile(userId: number): Promise<ProfileData> {
  const response = await request<ProfileData>(profilePath(userId))
  return response.data
}

export async function createProfile(
  userId: number,
  payload: CreateProfilePayload,
): Promise<ProfileData> {
  const response = await request<ProfileData>(profilePath(userId), {
    method: 'POST',
    body: JSON.stringify(payload),
  })
  return response.data
}

export async function updateProfile(
  userId: number,
  payload: CreateProfilePayload,
): Promise<ProfileData> {
  const response = await request<ProfileData>(profilePath(userId), {
    method: 'PUT',
    body: JSON.stringify(payload),
  })
  return response.data
}

export async function getProfileCompletion(userId: number): Promise<ProfileCompletionData> {
  const response = await request<ProfileCompletionData>(`${profilePath(userId)}/completion`)
  return response.data
}

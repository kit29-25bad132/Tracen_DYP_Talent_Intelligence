export type ApiErrorKind =
  | 'network'
  | 'unauthorized'
  | 'forbidden'
  | 'validation'
  | 'conflict'
  | 'not-found'
  | 'server-error'
  | 'unknown'

export interface ApiError {
  readonly kind: ApiErrorKind
  readonly status: number
  readonly message: string
  readonly fieldErrors?: Readonly<Record<string, string>>
}

export interface ApiResponse<T> {
  readonly data: T
}

export interface LoginCredentials {
  readonly email: string
  readonly password: string
}

export interface RegisterCredentials {
  readonly name: string
  readonly email: string
  readonly password: string
}

export interface UserRecord {
  readonly id: number
  readonly name: string
  readonly email: string
}

export interface LoginResult extends UserRecord {
  readonly token: string
}

export interface ApiConfig {
  readonly baseUrl: string
  readonly requestTimeoutMs: number
  readonly credentialsMode: 'include' | 'omit'
}

// ---- Profile ----

export interface ProfileData {
  readonly id: number
  readonly userId: number
  readonly education: string | null
  readonly degree: string | null
  readonly graduationYear: number | null
  readonly currentSkills: string | null
  readonly workExperience: string | null
  readonly preferredIndustries: string | null
  readonly dreamRoles: string | null
  readonly weeklyLearningHours: number | null
  readonly careerPriorities: string | null
  readonly preferredWorkLocation: string | null
}

export interface ProfileCompletionData {
  readonly userId: number
  readonly profileExists: boolean
  readonly completionPercentage: number
  readonly completed: boolean
}

export interface CreateProfilePayload {
  readonly education: string
  readonly degree: string
  readonly graduationYear: number
  readonly currentSkills: string
  readonly workExperience: string
  readonly preferredIndustries: string
  readonly dreamRoles: string
  readonly weeklyLearningHours: number
  readonly careerPriorities: string
  readonly preferredWorkLocation: string
}

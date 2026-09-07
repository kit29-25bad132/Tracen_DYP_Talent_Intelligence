import { useEffect, useState } from 'react'
import { useProfileApi } from '../hooks/use-profile-api'
import { useAuth } from '../context/auth-context'
import type { CreateProfilePayload, ApiError } from '../types/api'

const EMPTY_PAYLOAD: CreateProfilePayload = {
  education: '',
  degree: '',
  graduationYear: new Date().getFullYear(),
  currentSkills: '',
  workExperience: '',
  preferredIndustries: '',
  dreamRoles: '',
  weeklyLearningHours: 10,
  careerPriorities: '',
  preferredWorkLocation: '',
}

function ProfilePage() {
  const { user } = useAuth()
  const { profile, completion, state, error, loadProfile, saveProfile } = useProfileApi()
  const [form, setForm] = useState<CreateProfilePayload>(EMPTY_PAYLOAD)
  const [isEditing, setIsEditing] = useState(false)
  const [serverError, setServerError] = useState<string | null>(null)
  const [submitting, setSubmitting] = useState(false)
  const [successMessage, setSuccessMessage] = useState<string | null>(null)

  useEffect(() => {
    loadProfile()
  }, [loadProfile])

  useEffect(() => {
    if (profile) {
      setForm({
        education: profile.education ?? '',
        degree: profile.degree ?? '',
        graduationYear: profile.graduationYear ?? new Date().getFullYear(),
        currentSkills: profile.currentSkills ?? '',
        workExperience: profile.workExperience ?? '',
        preferredIndustries: profile.preferredIndustries ?? '',
        dreamRoles: profile.dreamRoles ?? '',
        weeklyLearningHours: profile.weeklyLearningHours ?? 10,
        careerPriorities: profile.careerPriorities ?? '',
        preferredWorkLocation: profile.preferredWorkLocation ?? '',
      })
    }
  }, [profile])

  const hasProfile = profile !== null

  const setField = <K extends keyof CreateProfilePayload>(
    key: K,
    value: CreateProfilePayload[K],
  ) => {
    setForm((prev) => ({ ...prev, [key]: value }))
  }

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    setSubmitting(true)
    setServerError(null)
    setSuccessMessage(null)
    try {
      await saveProfile(form, hasProfile)
      setIsEditing(false)
      setSuccessMessage(hasProfile ? 'Profile updated successfully.' : 'Profile created successfully.')
      setTimeout(() => setSuccessMessage(null), 5000)
    } catch (err) {
      const apiError = err as ApiError
      setServerError(apiError.message || 'Failed to save profile.')
    } finally {
      setSubmitting(false)
    }
  }

  if (state === 'loading' && !profile) {
    return (
      <div className="auth-card" style={{ textAlign: 'center', padding: '2rem' }}>
        <p>Loading profile…</p>
      </div>
    )
  }

  if (error && state === 'error' && !profile) {
    return (
      <div className="auth-card">
        <h2>Profile</h2>
        <span className="field-error" role="alert">{error.message}</span>
        <button type="button" className="submit-btn" style={{ marginTop: '1rem' }} onClick={() => loadProfile()}>
          Retry
        </button>
      </div>
    )
  }

  // Viewing profile (not editing)
  if (hasProfile && !isEditing) {
    return (
      <div style={{ width: '100%', maxWidth: '520px', margin: '0 auto' }}>
        <div className="auth-card">
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1rem' }}>
            <h2 style={{ margin: 0 }}>My Profile</h2>
            <button type="button" className="submit-btn" onClick={() => setIsEditing(true)}>
              Edit Profile
            </button>
          </div>

          {completion && (
            <div style={{ marginBottom: '1.25rem' }}>
              <CompletionBar percentage={completion.completionPercentage} />
              {completion.completed ? (
                <p style={{ fontSize: '0.85rem', color: 'var(--success)', marginTop: '0.25rem' }}>
                  Profile 100% complete ✓
                </p>
              ) : (
                <p style={{ fontSize: '0.85rem', color: 'var(--text)', marginTop: '0.25rem' }}>
                  {completion.completionPercentage}% complete — fill in all fields for a full profile.
                </p>
              )}
            </div>
          )}

          {successMessage && (
            <div className="global-message success" role="status" style={{ maxWidth: '100%' }}>
              <span className="global-message-text">{successMessage}</span>
            </div>
          )}

          <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '1rem' }}>
            <ProfileField label="Education" value={form.education} />
            <ProfileField label="Degree" value={form.degree} />
            <ProfileField label="Graduation Year" value={String(form.graduationYear)} />
            <ProfileField label="Weekly Learning Hours" value={String(form.weeklyLearningHours)} />
          </div>
          <div style={{ display: 'grid', gap: '1rem', marginTop: '1rem' }}>
            <ProfileField label="Current Skills" value={form.currentSkills} />
            <ProfileField label="Work Experience" value={form.workExperience} />
            <ProfileField label="Preferred Industries" value={form.preferredIndustries} />
            <ProfileField label="Dream Roles" value={form.dreamRoles} />
            <ProfileField label="Career Priorities" value={form.careerPriorities} />
            <ProfileField label="Preferred Work Location" value={form.preferredWorkLocation} />
          </div>
        </div>
      </div>
    )
  }

  // Creating or editing profile (form mode)
  return (
    <div style={{ width: '100%', maxWidth: '520px', margin: '0 auto' }}>
      <div className="auth-card">
        <h2 style={{ marginBottom: '0.25rem' }}>
          {hasProfile ? 'Edit Profile' : 'Create Your Profile'}
        </h2>
        <p style={{ color: 'var(--text)', fontSize: '0.9rem', marginBottom: '1rem' }}>
          {hasProfile
            ? 'Update your profile details to keep your talent intelligence up to date.'
            : 'Fill in your details so DYP can personalise your assessment and career plan.'}
        </p>

        {hasProfile && completion && (
          <div style={{ marginBottom: '1rem' }}>
            <CompletionBar percentage={completion.completionPercentage} />
          </div>
        )}

        {serverError && (
          <div className="global-message error" role="alert" style={{ maxWidth: '100%', marginBottom: '1rem' }}>
            <span className="global-message-text">{serverError}</span>
          </div>
        )}

        {successMessage && (
          <div className="global-message success" role="status" style={{ maxWidth: '100%', marginBottom: '1rem' }}>
            <span className="global-message-text">{successMessage}</span>
          </div>
        )}

        <form className="form-stack" onSubmit={handleSubmit} noValidate>
          <div className="field">
            <label htmlFor="edu">Education *</label>
            <input
              id="edu"
              data-testid="profile-education"
              value={form.education}
              onChange={(e) => setField('education', e.target.value)}
              placeholder="e.g. B.Tech"
              required
              disabled={submitting}
            />
          </div>

          <div className="field">
            <label htmlFor="deg">Degree *</label>
            <input
              id="deg"
              data-testid="profile-degree"
              value={form.degree}
              onChange={(e) => setField('degree', e.target.value)}
              placeholder="e.g. Computer Science"
              required
              disabled={submitting}
            />
          </div>

          <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '1rem' }}>
            <div className="field">
              <label htmlFor="grad-year">Graduation Year *</label>
              <input
                id="grad-year"
                data-testid="profile-graduation-year"
                type="number"
                min={2020}
                max={2040}
                value={form.graduationYear}
                onChange={(e) => setField('graduationYear', Number(e.target.value))}
                required
                disabled={submitting}
              />
            </div>
            <div className="field">
              <label htmlFor="learn-hrs">Weekly Learning Hours *</label>
              <input
                id="learn-hrs"
                data-testid="profile-weekly-learning-hours"
                type="number"
                min={1}
                max={80}
                value={form.weeklyLearningHours}
                onChange={(e) => setField('weeklyLearningHours', Number(e.target.value))}
                required
                disabled={submitting}
              />
            </div>
          </div>

          <div className="field">
            <label htmlFor="skills">Current Skills</label>
            <textarea
              id="skills"
              data-testid="profile-current-skills"
              value={form.currentSkills}
              onChange={(e) => setField('currentSkills', e.target.value)}
              placeholder="e.g. Java, Python, SQL, React"
              rows={2}
              disabled={submitting}
            />
          </div>

          <div className="field">
            <label htmlFor="work-exp">Work Experience</label>
            <textarea
              id="work-exp"
              data-testid="profile-work-experience"
              value={form.workExperience}
              onChange={(e) => setField('workExperience', e.target.value)}
              placeholder="e.g. Student projects, internships"
              rows={2}
              disabled={submitting}
            />
          </div>

          <div className="field">
            <label htmlFor="industries">Preferred Industries</label>
            <input
              id="industries"
              data-testid="profile-preferred-industries"
              value={form.preferredIndustries}
              onChange={(e) => setField('preferredIndustries', e.target.value)}
              placeholder="e.g. Software, AI, FinTech"
              disabled={submitting}
            />
          </div>

          <div className="field">
            <label htmlFor="dream-roles">Dream Roles</label>
            <input
              id="dream-roles"
              data-testid="profile-dream-roles"
              value={form.dreamRoles}
              onChange={(e) => setField('dreamRoles', e.target.value)}
              placeholder="e.g. Backend Engineer, AI Engineer"
              disabled={submitting}
            />
          </div>

          <div className="field">
            <label htmlFor="priorities">Career Priorities</label>
            <input
              id="priorities"
              data-testid="profile-career-priorities"
              value={form.careerPriorities}
              onChange={(e) => setField('careerPriorities', e.target.value)}
              placeholder="e.g. Learning, Salary, Growth"
              disabled={submitting}
            />
          </div>

          <div className="field">
            <label htmlFor="location">Preferred Work Location</label>
            <input
              id="location"
              data-testid="profile-preferred-work-location"
              value={form.preferredWorkLocation}
              onChange={(e) => setField('preferredWorkLocation', e.target.value)}
              placeholder="e.g. Remote, India, US"
              disabled={submitting}
            />
          </div>

          <div style={{ display: 'flex', gap: '0.75rem', marginTop: '0.5rem' }}>
            <button
              type="submit"
              data-testid="profile-submit"
              className="submit-btn"
              disabled={submitting || !form.education || !form.degree}
            >
              {submitting ? 'Saving…' : hasProfile ? 'Update Profile' : 'Create Profile'}
            </button>
            {hasProfile && (
              <button
                type="button"
                className="submit-btn"
                style={{ background: 'transparent', color: 'var(--text)', border: '1px solid var(--border)' }}
                onClick={() => {
                  setIsEditing(false)
                  setServerError(null)
                  setSuccessMessage(null)
                  // Reset form to current profile values
                  if (profile) {
                    setForm({
                      education: profile.education ?? '',
                      degree: profile.degree ?? '',
                      graduationYear: profile.graduationYear ?? new Date().getFullYear(),
                      currentSkills: profile.currentSkills ?? '',
                      workExperience: profile.workExperience ?? '',
                      preferredIndustries: profile.preferredIndustries ?? '',
                      dreamRoles: profile.dreamRoles ?? '',
                      weeklyLearningHours: profile.weeklyLearningHours ?? 10,
                      careerPriorities: profile.careerPriorities ?? '',
                      preferredWorkLocation: profile.preferredWorkLocation ?? '',
                    })
                  }
                }}
                disabled={submitting}
              >
                Cancel
              </button>
            )}
          </div>
        </form>
      </div>
    </div>
  )
}

// ---- Helper components ----

function ProfileField({ label, value }: { label: string; value: string }) {
  return (
    <div>
      <dt style={{ fontSize: '0.8rem', color: 'var(--text)', marginBottom: '0.15rem', fontWeight: 500 }}>{label}</dt>
      <dd style={{ margin: 0, fontSize: '0.95rem' }}>{value || <span style={{ color: 'var(--border)' }}>—</span>}</dd>
    </div>
  )
}

function CompletionBar({ percentage }: { percentage: number }) {
  return (
    <div style={{ width: '100%' }}>
      <div
        style={{
          width: '100%',
          height: '8px',
          background: 'var(--border)',
          borderRadius: '4px',
          overflow: 'hidden',
        }}
      >
        <div
          style={{
            width: `${percentage}%`,
            height: '100%',
            background: percentage === 100 ? 'var(--success)' : 'var(--accent)',
            borderRadius: '4px',
            transition: 'width 0.3s ease',
          }}
        />
      </div>
    </div>
  )
}

export default ProfilePage

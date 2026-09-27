export type AnnouncementCategory =
  | 'Exam'
  | 'Assignment'
  | 'Event'
  | 'Workshop'
  | 'Hackathon'
  | 'Internship'
  | 'Scholarship'
  | 'Club'
  | 'Placement'
  | 'Deadline'
  | 'Emergency'
  | 'General'

export type PriorityLevel = 'High' | 'Medium' | 'Low'

export interface TargetAudience {
  college?: string
  departments: string[]
  semesters: string[] // e.g. ['S1', 'S2']
  communities?: string[]
}

export interface VerifiedSource {
  id: string
  name: string
  role: string // e.g. "Department Head", "Placement Officer", "IEDC Lead"
  isVerified: boolean
  isOfficial: boolean
  avatarUrl?: string
}

export interface ActionItem {
  description: string
  dueDate?: string
  dueTime?: string
  location?: string
  link?: string
}

export interface Announcement {
  id: string
  title: string
  rawText: string
  structuredContent: {
    title: string
    category: AnnouncementCategory
    summary: string
    audienceSummary: string
    date?: string
    time?: string
    location?: string
    actionRequired?: string
    priority: PriorityLevel
  }
  audience: TargetAudience
  source: VerifiedSource
  category: AnnouncementCategory
  priority: PriorityLevel
  publishedAt: string
  isConflictDetected?: boolean
  conflictDetails?: {
    conflictField: string
    sourceA: { name: string; value: string }
    sourceB: { name: string; value: string }
    status: 'Verification required' | 'Resolved'
  }
  isRead?: boolean
  isBookmarked?: boolean
}

export type UserRole = 'student' | 'admin'

export interface StudentProfile {
  id: string
  name: string
  email: string
  college: string
  department: string // e.g. "Information Technology"
  semester: string // e.g. "S1"
  joinedCommunities: string[] // e.g. ["IEDC", "Coding Club", "IEEE"]
  avatarUrl?: string
}

export interface AdminProfile {
  id: string
  name: string
  email: string
  college: string
  department: string
  designation: string // e.g. "HOD - Computer Science", "Placement Coordinator"
  verifiedSourceId: string
  avatarUrl?: string
}

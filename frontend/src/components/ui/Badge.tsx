import React from 'react'
import { cn } from '../../lib/utils'
import type { PriorityLevel, AnnouncementCategory } from '../../types/announcement'
import { AlertTriangle, ShieldCheck } from 'lucide-react'

export interface BadgeProps extends React.HTMLAttributes<HTMLSpanElement> {
  variant?: 'default' | 'outline' | 'verified' | 'priority' | 'category'
  priority?: PriorityLevel
  category?: AnnouncementCategory
  isVerified?: boolean
}

export const Badge: React.FC<BadgeProps> = ({
  className,
  variant = 'default',
  priority,
  category,
  isVerified = false,
  children,
  ...props
}) => {
  if (variant === 'priority' && priority) {
    const priorityStyles: Record<PriorityLevel, string> = {
      High: 'bg-amber-50 text-amber-700 border-amber-200/80 font-semibold',
      Medium: 'bg-indigo-50 text-indigo-700 border-indigo-200/80 font-medium',
      Low: 'bg-slate-100 text-slate-600 border-slate-200/80 font-normal',
    }

    return (
      <span
        className={cn(
          'inline-flex items-center gap-1 px-2.5 py-0.5 rounded-full text-xs border',
          priorityStyles[priority],
          className
        )}
        {...props}
      >
        {priority === 'High' && <AlertTriangle className="w-3 h-3 text-amber-600" />}
        {priority} Priority
      </span>
    )
  }

  if (variant === 'verified' || isVerified) {
    return (
      <span
        className={cn(
          'inline-flex items-center gap-1 px-2.5 py-0.5 rounded-full text-xs font-medium bg-emerald-50 text-emerald-700 border border-emerald-200/80',
          className
        )}
        {...props}
      >
        <ShieldCheck className="w-3.5 h-3.5 text-emerald-600" />
        {children || 'Verified Source'}
      </span>
    )
  }

  if (variant === 'category' && category) {
    const categoryColors: Record<AnnouncementCategory, string> = {
      Exam: 'bg-rose-50 text-rose-700 border-rose-200/80',
      Assignment: 'bg-amber-50 text-amber-700 border-amber-200/80',
      Event: 'bg-indigo-50 text-indigo-700 border-indigo-200/80',
      Workshop: 'bg-purple-50 text-purple-700 border-purple-200/80',
      Hackathon: 'bg-blue-50 text-blue-700 border-blue-200/80',
      Internship: 'bg-teal-50 text-teal-700 border-teal-200/80',
      Scholarship: 'bg-emerald-50 text-emerald-700 border-emerald-200/80',
      Club: 'bg-violet-50 text-violet-700 border-violet-200/80',
      Placement: 'bg-cyan-50 text-cyan-700 border-cyan-200/80',
      Deadline: 'bg-orange-50 text-orange-700 border-orange-200/80',
      Emergency: 'bg-red-100 text-red-800 border-red-300 font-semibold animate-pulse',
      General: 'bg-slate-100 text-slate-700 border-slate-200/80',
    }

    return (
      <span
        className={cn(
          'inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium border',
          categoryColors[category] || 'bg-slate-100 text-slate-700 border-slate-200',
          className
        )}
        {...props}
      >
        {category}
      </span>
    )
  }

  return (
    <span
      className={cn(
        'inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-slate-100 text-slate-700 border border-slate-200/80',
        className
      )}
      {...props}
    >
      {children}
    </span>
  )
}

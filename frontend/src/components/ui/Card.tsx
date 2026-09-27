import React from 'react'
import { cn } from '../../lib/utils'

export interface CardProps extends React.HTMLAttributes<HTMLDivElement> {
  hoverable?: boolean
  variant?: 'default' | 'outlined' | 'glass' | 'flat'
}

export const Card = React.forwardRef<HTMLDivElement, CardProps>(
  ({ className, hoverable = false, variant = 'default', children, ...props }, ref) => {
    const variants = {
      default: 'bg-white border border-slate-200/80 shadow-2xs text-slate-900',
      outlined: 'bg-white border border-slate-200 text-slate-900',
      glass: 'bg-white/70 backdrop-blur-md border border-white/40 shadow-xs text-slate-900',
      flat: 'bg-slate-50/80 border border-slate-100 text-slate-900',
    }

    return (
      <div
        ref={ref}
        className={cn(
          'rounded-2xl p-5 relative overflow-hidden',
          variants[variant],
          hoverable && 'card-hover cursor-pointer',
          className
        )}
        {...props}
      >
        {children}
      </div>
    )
  }
)
Card.displayName = 'Card'

export const CardHeader = ({
  className,
  ...props
}: React.HTMLAttributes<HTMLDivElement>) => (
  <div className={cn('flex flex-col space-y-1.5 mb-3', className)} {...props} />
)

export const CardTitle = ({
  className,
  ...props
}: React.HTMLAttributes<HTMLHeadingElement>) => (
  <h3
    className={cn('text-lg font-semibold tracking-tight text-slate-900', className)}
    {...props}
  />
)

export const CardDescription = ({
  className,
  ...props
}: React.HTMLAttributes<HTMLParagraphElement>) => (
  <p className={cn('text-sm text-slate-500 font-normal leading-relaxed', className)} {...props} />
)

export const CardContent = ({
  className,
  ...props
}: React.HTMLAttributes<HTMLDivElement>) => (
  <div className={cn('pt-0', className)} {...props} />
)

export const CardFooter = ({
  className,
  ...props
}: React.HTMLAttributes<HTMLDivElement>) => (
  <div className={cn('flex items-center pt-4 border-t border-slate-100 mt-4', className)} {...props} />
)

import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import { Button } from '../ui/Button'
import { Container } from '../ui/Container'
import { Menu, X, ArrowRight, Layers } from 'lucide-react'

export const Navbar: React.FC = () => {
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false)

  const navLinks = [
    { label: 'How it Works', href: '#how-it-works' },
    { label: 'AI Engine', href: '#ai-engine' },
    { label: 'Student vs Admin', href: '#experience' },
    { label: 'Interactive Demo', href: '#demo' },
  ]

  return (
    <header className="sticky top-0 z-50 glass-nav transition-all duration-200">
      <Container size="lg">
        <div className="flex items-center justify-between h-16 sm:h-20">
          {/* Logo & Tagline */}
          <Link to="/" className="flex items-center gap-3 group">
            <div className="w-10 h-10 rounded-xl bg-slate-900 text-white flex items-center justify-center shadow-md group-hover:scale-105 transition-transform duration-200">
              <Layers className="w-5 h-5 text-indigo-400" />
            </div>
            <div className="flex flex-col">
              <div className="flex items-center gap-2">
                <span className="font-bold text-lg text-slate-900 tracking-tight">CAMPUSLY</span>
                <span className="hidden sm:inline-flex items-center px-2 py-0.5 rounded-full text-[10px] font-semibold bg-indigo-50 text-indigo-700 border border-indigo-200/80">
                  MVP v0.1
                </span>
              </div>
              <span className="text-[11px] text-slate-500 font-medium hidden md:inline-block">
                Don't scroll. Know what matters.
              </span>
            </div>
          </Link>

          {/* Desktop Navigation Links */}
          <nav className="hidden md:flex items-center gap-8">
            {navLinks.map((link) => (
              <a
                key={link.label}
                href={link.href}
                className="text-sm font-medium text-slate-600 hover:text-slate-900 transition-colors"
              >
                {link.label}
              </a>
            ))}
          </nav>

          {/* Desktop CTA / App Explorer Buttons */}
          <div className="hidden sm:flex items-center gap-3">
            <Button
              variant="outline"
              size="sm"
              onClick={() => {
                const demoEl = document.getElementById('demo')
                if (demoEl) {
                  demoEl.scrollIntoView({ behavior: 'smooth' })
                }
              }}
            >
              Live Demo
            </Button>
            <Button
              variant="accent"
              size="sm"
              rightIcon={<ArrowRight className="w-4 h-4" />}
              onClick={() => {
                const demoEl = document.getElementById('demo')
                if (demoEl) {
                  demoEl.scrollIntoView({ behavior: 'smooth' })
                }
              }}
            >
              Explore App
            </Button>
          </div>

          {/* Mobile Menu Button */}
          <div className="flex sm:hidden items-center">
            <button
              onClick={() => setIsMobileMenuOpen(!isMobileMenuOpen)}
              className="p-2 text-slate-600 hover:text-slate-900 focus:outline-none"
              aria-label="Toggle Navigation Menu"
            >
              {isMobileMenuOpen ? <X className="w-6 h-6" /> : <Menu className="w-6 h-6" />}
            </button>
          </div>
        </div>

        {/* Mobile Dropdown Menu */}
        {isMobileMenuOpen && (
          <div className="sm:hidden py-4 border-t border-slate-100 flex flex-col gap-4 animate-in slide-in-from-top-2 duration-200">
            <nav className="flex flex-col gap-3">
              {navLinks.map((link) => (
                <a
                  key={link.label}
                  href={link.href}
                  onClick={() => setIsMobileMenuOpen(false)}
                  className="text-sm font-medium text-slate-700 hover:text-indigo-600 py-1"
                >
                  {link.label}
                </a>
              ))}
            </nav>
            <div className="flex flex-col gap-2 pt-2 border-t border-slate-100">
              <Button
                variant="accent"
                size="md"
                className="w-full justify-center"
                rightIcon={<ArrowRight className="w-4 h-4" />}
                onClick={() => {
                  setIsMobileMenuOpen(false)
                  const demoEl = document.getElementById('demo')
                  if (demoEl) demoEl.scrollIntoView({ behavior: 'smooth' })
                }}
              >
                Explore Campusly App
              </Button>
            </div>
          </div>
        )}
      </Container>
    </header>
  )
}

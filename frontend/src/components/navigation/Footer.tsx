import React from 'react'
import { Container } from '../ui/Container'
import { Layers, ShieldCheck } from 'lucide-react'

export const Footer: React.FC = () => {
  return (
    <footer className="bg-slate-900 text-slate-400 py-12 md:py-16 border-t border-slate-800">
      <Container size="lg">
        <div className="grid grid-cols-1 md:grid-cols-4 gap-8 mb-12">
          {/* Brand Col */}
          <div className="md:col-span-2 space-y-4">
            <div className="flex items-center gap-3">
              <div className="w-9 h-9 rounded-xl bg-indigo-600 text-white flex items-center justify-center shadow-sm">
                <Layers className="w-5 h-5" />
              </div>
              <span className="font-bold text-xl text-white tracking-tight">CAMPUSLY</span>
            </div>
            <p className="text-sm text-slate-400 max-w-sm leading-relaxed">
              Intelligent information delivery layer connecting college & community administrators directly with students.{' '}
              <strong className="text-slate-200">Don't scroll. Know what matters.</strong>
            </p>
            <div className="flex items-center gap-2 text-xs text-slate-500 pt-2">
              <ShieldCheck className="w-4 h-4 text-emerald-400" />
              <span>Verified Source Architecture · AI Structuring Powered by Gemini</span>
            </div>
          </div>

          {/* Product Links */}
          <div>
            <h4 className="text-xs font-semibold uppercase tracking-wider text-slate-200 mb-4">
              Core Capabilities
            </h4>
            <ul className="space-y-2.5 text-sm">
              <li><a href="#ai-engine" className="hover:text-white transition-colors">AI Natural Language Structuring</a></li>
              <li><a href="#how-it-works" className="hover:text-white transition-colors">Personalized Student Feed</a></li>
              <li><a href="#demo" className="hover:text-white transition-colors">Conflict Detection Warning</a></li>
              <li><a href="#demo" className="hover:text-white transition-colors">One-Click Calendar Export</a></li>
              <li><a href="#how-it-works" className="hover:text-white transition-colors">"What You Missed" Catchup</a></li>
            </ul>
          </div>

          {/* Stack & Hackathon Info */}
          <div>
            <h4 className="text-xs font-semibold uppercase tracking-wider text-slate-200 mb-4">
              Hackathon Architecture
            </h4>
            <ul className="space-y-2 text-xs text-slate-400">
              <li className="flex items-center justify-between py-1 border-b border-slate-800/60">
                <span>Frontend</span>
                <span className="text-slate-200 font-mono">React 19 + Vite</span>
              </li>
              <li className="flex items-center justify-between py-1 border-b border-slate-800/60">
                <span>Styling</span>
                <span className="text-slate-200 font-mono">Tailwind CSS v4</span>
              </li>
              <li className="flex items-center justify-between py-1 border-b border-slate-800/60">
                <span>Backend API</span>
                <span className="text-slate-200 font-mono">Python FastAPI</span>
              </li>
              <li className="flex items-center justify-between py-1">
                <span>AI Engine</span>
                <span className="text-indigo-400 font-mono">Google Gemini 1.5</span>
              </li>
            </ul>
          </div>
        </div>

        {/* Bottom Bar */}
        <div className="pt-8 border-t border-slate-800 flex flex-col sm:flex-row items-center justify-between gap-4 text-xs text-slate-500">
          <p>© {new Date().getFullYear()} Campusly MVP. Designed & Built for Hackathon Excellence.</p>
          <div className="flex items-center gap-4">
            <span className="hover:text-slate-300">Privacy</span>
            <span>·</span>
            <span className="hover:text-slate-300">Terms</span>
            <span>·</span>
            <span className="hover:text-slate-300">Security & Trust</span>
          </div>
        </div>
      </Container>
    </footer>
  )
}

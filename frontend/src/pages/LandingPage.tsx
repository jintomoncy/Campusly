import React, { useState } from 'react'
import { Container } from '../components/ui/Container'
import { Card, CardHeader, CardTitle, CardContent } from '../components/ui/Card'
import { Button } from '../components/ui/Button'
import { Badge } from '../components/ui/Badge'
import { mockLandingAnnouncements } from '../data/mockLandingData'
import {
  Sparkles,
  ShieldCheck,
  Calendar,
  AlertTriangle,
  Clock,
  Filter,
  CheckCircle2,
  ArrowRight,
  Zap,
  MessageSquareOff,
  UserCheck,
  FileText,
  Cpu,
  ChevronRight,
  ExternalLink,
} from 'lucide-react'

export const LandingPage: React.FC = () => {
  // State for interactive Personalization toggle demo
  const [selectedStudentProfile, setSelectedStudentProfile] = useState<'S1_IT' | 'S3_ECE'>('S1_IT')

  // State for interactive AI Announcement Structurer demo
  const [rawInputText, setRawInputText] = useState(
    'All S1 IT students are informed that the Python lab examination will be conducted on Friday October 4 at 10 AM in Lab 2. Students should bring their completed records.'
  )
  const [isAiProcessing, setIsAiProcessing] = useState(false)
  const [structuredOutput, setStructuredOutput] = useState<{
    title: string
    category: 'Exam' | 'Workshop' | 'Assignment'
    audience: string
    date: string
    time: string
    location: string
    action: string
    priority: 'High' | 'Medium'
  } | null>({
    title: 'Python Lab Examination',
    category: 'Exam',
    audience: 'S1 IT',
    date: 'October 4, 2026',
    time: '10:00 AM',
    location: 'Lab 2 (IT Block)',
    action: 'Bring completed lab records & ID card',
    priority: 'High',
  })

  const handleSimulateAiStructure = () => {
    setIsAiProcessing(true)
    setTimeout(() => {
      setStructuredOutput({
        title: rawInputText.includes('Python') ? 'Python Lab Examination' : 'Extracted Announcement',
        category: rawInputText.toLowerCase().includes('exam') ? 'Exam' : 'Workshop',
        audience: rawInputText.includes('S1 IT') ? 'S1 IT' : 'All Students',
        date: rawInputText.includes('October 4') ? 'October 4, 2026' : 'Extracted Date',
        time: rawInputText.includes('10 AM') ? '10:00 AM' : 'Not specified',
        location: rawInputText.includes('Lab 2') ? 'Lab 2 (IT Block)' : 'Not specified',
        action: rawInputText.includes('records') ? 'Bring completed records' : 'Not specified',
        priority: 'High',
      })
      setIsAiProcessing(false)
    }, 600)
  }

  // Filtered announcements based on interactive personalization demo profile
  const personalizedAnnouncements = mockLandingAnnouncements.filter((ann) => {
    if (selectedStudentProfile === 'S1_IT') {
      return (
        ann.audience.departments.includes('Information Technology') ||
        ann.audience.departments.includes('All')
      )
    } else {
      return (
        ann.audience.departments.includes('Electronics & Communication') ||
        ann.audience.departments.includes('All') ||
        ann.audience.departments.includes('Computer Science')
      )
    }
  })

  return (
    <div className="relative overflow-hidden space-y-24 pb-20">
      {/* Background ambient glow */}
      <div className="hero-glow" />

      {/* ================================================================= */}
      {/* 1. HERO SECTION */}
      {/* ================================================================= */}
      <section className="pt-16 sm:pt-24 pb-12 relative z-10">
        <Container size="lg" className="text-center space-y-8">
          {/* Eyebrow Badge */}
          <div className="inline-flex items-center gap-2 px-3.5 py-1.5 rounded-full text-xs font-semibold bg-indigo-50/90 text-indigo-700 border border-indigo-200/80 shadow-2xs">
            <Sparkles className="w-3.5 h-3.5 text-indigo-600 animate-pulse" />
            <span>INTELLIGENT CAMPUS INFORMATION LAYER</span>
          </div>

          {/* Headline & Tagline */}
          <div className="max-w-4xl mx-auto space-y-4">
            <h1 className="text-4xl sm:text-6xl font-extrabold tracking-tight text-slate-900 leading-[1.1]">
              Don't scroll. <br className="hidden sm:inline" />
              <span className="gradient-text">Know what matters.</span>
            </h1>
            <p className="text-lg sm:text-xl text-slate-600 max-w-2xl mx-auto font-normal leading-relaxed">
              Campusly transforms scattered, noisy college announcements into clear, structured, and personalized actions for every student.
            </p>
          </div>

          {/* Hero CTAs */}
          <div className="flex flex-col sm:flex-row items-center justify-center gap-4 pt-2">
            <Button
              variant="accent"
              size="lg"
              className="w-full sm:w-auto shadow-md"
              rightIcon={<ArrowRight className="w-5 h-5" />}
              onClick={() => {
                const demoEl = document.getElementById('demo')
                if (demoEl) demoEl.scrollIntoView({ behavior: 'smooth' })
              }}
            >
              Explore Interactive Demo
            </Button>
            <Button
              variant="outline"
              size="lg"
              className="w-full sm:w-auto"
              onClick={() => {
                const howEl = document.getElementById('how-it-works')
                if (howEl) howEl.scrollIntoView({ behavior: 'smooth' })
              }}
            >
              How Campusly Works
            </Button>
          </div>

          {/* ================================================================= */}
          {/* HERO ARCHITECTURE FLOW DIAGRAM */}
          {/* ================================================================= */}
          <div id="how-it-works" className="pt-12 max-w-5xl mx-auto">
            <Card variant="glass" className="p-6 sm:p-8 border-slate-200/90 shadow-xl bg-white/90">
              <div className="text-xs font-bold uppercase tracking-wider text-slate-400 mb-6 text-center">
                The Campusly Information Delivery Pipeline
              </div>

              <div className="grid grid-cols-1 md:grid-cols-4 gap-4 items-center">
                {/* Step 1: Admin */}
                <div className="bg-slate-50 p-4 rounded-xl border border-slate-200/80 text-left space-y-2 relative">
                  <div className="w-8 h-8 rounded-lg bg-slate-900 text-white flex items-center justify-center text-xs font-bold">
                    1
                  </div>
                  <div className="text-sm font-semibold text-slate-900">College / Admin</div>
                  <div className="text-xs text-slate-500">Publishes raw announcement or notice</div>
                </div>

                {/* Arrow */}
                <div className="hidden md:flex justify-center text-indigo-500">
                  <ChevronRight className="w-6 h-6 animate-pulse" />
                </div>

                {/* Step 2: AI Intelligence */}
                <div className="bg-indigo-50/80 p-4 rounded-xl border border-indigo-200/80 text-left space-y-2 relative">
                  <div className="w-8 h-8 rounded-lg bg-indigo-600 text-white flex items-center justify-center text-xs font-bold">
                    2
                  </div>
                  <div className="text-sm font-semibold text-indigo-950 flex items-center gap-1.5">
                    Campusly AI Layer <Sparkles className="w-3.5 h-3.5 text-indigo-600" />
                  </div>
                  <div className="text-xs text-indigo-800">
                    Classifies, extracts dates, audience & action items
                  </div>
                </div>

                {/* Arrow */}
                <div className="hidden md:flex justify-center text-indigo-500">
                  <ChevronRight className="w-6 h-6 animate-pulse" />
                </div>

                {/* Step 3: Student */}
                <div className="bg-emerald-50/80 p-4 rounded-xl border border-emerald-200/80 text-left space-y-2">
                  <div className="w-8 h-8 rounded-lg bg-emerald-600 text-white flex items-center justify-center text-xs font-bold">
                    3
                  </div>
                  <div className="text-sm font-semibold text-emerald-950 flex items-center gap-1.5">
                    Action Card <CheckCircle2 className="w-3.5 h-3.5 text-emerald-600" />
                  </div>
                  <div className="text-xs text-emerald-800">
                    Personalized card + 1-click calendar export
                  </div>
                </div>
              </div>
            </Card>
          </div>
        </Container>
      </section>

      {/* ================================================================= */}
      {/* 2. THE PROBLEM VS SOLUTION SECTION */}
      {/* ================================================================= */}
      <section className="py-12 bg-slate-100/60 border-y border-slate-200/80">
        <Container size="lg" className="space-y-12">
          <div className="text-center max-w-2xl mx-auto space-y-3">
            <h2 className="text-2xl sm:text-3xl font-bold text-slate-900 tracking-tight">
              Solving College Information Overload
            </h2>
            <p className="text-slate-600 text-sm sm:text-base">
              Campusly is not another chat application or message board. It is an intelligent information filter.
            </p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
            {/* The Problem */}
            <Card className="border-rose-200/80 bg-rose-50/30">
              <CardHeader>
                <div className="flex items-center gap-2 text-rose-700 font-semibold text-sm">
                  <MessageSquareOff className="w-4 h-4" />
                  <span>THE OLD WAY (INFORMATION NOISE)</span>
                </div>
                <CardTitle className="text-xl text-rose-950">Scattered WhatsApp & Notice Chaos</CardTitle>
              </CardHeader>
              <CardContent className="space-y-3 text-sm text-slate-700">
                <div className="flex items-start gap-2.5 p-3 rounded-xl bg-white/80 border border-rose-100">
                  <span className="text-rose-500 font-bold">✕</span>
                  <span>Important exam date buried inside a 200-message class WhatsApp group.</span>
                </div>
                <div className="flex items-start gap-2.5 p-3 rounded-xl bg-white/80 border border-rose-100">
                  <span className="text-rose-500 font-bold">✕</span>
                  <span>Students receiving workshop notices meant for a completely different department.</span>
                </div>
                <div className="flex items-start gap-2.5 p-3 rounded-xl bg-white/80 border border-rose-100">
                  <span className="text-rose-500 font-bold">✕</span>
                  <span>Conflicting dates shared by unofficial sources with zero verification.</span>
                </div>
              </CardContent>
            </Card>

            {/* The Solution */}
            <Card className="border-indigo-200/80 bg-indigo-50/30">
              <CardHeader>
                <div className="flex items-center gap-2 text-indigo-700 font-semibold text-sm">
                  <Zap className="w-4 h-4" />
                  <span>THE CAMPUSLY WAY (STRUCTURED CLARITY)</span>
                </div>
                <CardTitle className="text-xl text-indigo-950">Intelligent Personalization & Action</CardTitle>
              </CardHeader>
              <CardContent className="space-y-3 text-sm text-slate-700">
                <div className="flex items-start gap-2.5 p-3 rounded-xl bg-white/80 border border-indigo-100">
                  <CheckCircle2 className="w-4 h-4 text-emerald-600 mt-0.5 shrink-0" />
                  <span>AI converts long text notices into clean cards with Date, Time, Venue & Action.</span>
                </div>
                <div className="flex items-start gap-2.5 p-3 rounded-xl bg-white/80 border border-indigo-100">
                  <CheckCircle2 className="w-4 h-4 text-emerald-600 mt-0.5 shrink-0" />
                  <span>Strict audience matching: Only see notices relevant to your department and semester.</span>
                </div>
                <div className="flex items-start gap-2.5 p-3 rounded-xl bg-white/80 border border-indigo-100">
                  <CheckCircle2 className="w-4 h-4 text-emerald-600 mt-0.5 shrink-0" />
                  <span>Verified Source Trust System + Automatic Conflict Detection flags discrepancies.</span>
                </div>
              </CardContent>
            </Card>
          </div>
        </Container>
      </section>

      {/* ================================================================= */}
      {/* 3. CORE INTELLIGENT FEATURES GRID */}
      {/* ================================================================= */}
      <section id="ai-engine" className="py-12">
        <Container size="lg" className="space-y-12">
          <div className="text-center max-w-2xl mx-auto space-y-3">
            <div className="inline-flex items-center gap-2 px-3 py-1 rounded-full text-xs font-medium bg-slate-100 text-slate-700">
              <Cpu className="w-3.5 h-3.5 text-indigo-600" />
              <span>CORE ARCHITECTURE</span>
            </div>
            <h2 className="text-3xl font-bold text-slate-900 tracking-tight">
              Engineered for Speed, Trust & Action
            </h2>
            <p className="text-slate-600 text-sm">
              Every feature in Campusly is designed to reduce cognitive load and prevent missed opportunities.
            </p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
            {/* Feature 1: AI Structuring */}
            <Card hoverable className="space-y-3">
              <div className="w-10 h-10 rounded-xl bg-indigo-50 text-indigo-600 flex items-center justify-center">
                <Sparkles className="w-5 h-5" />
              </div>
              <h3 className="font-semibold text-slate-900 text-base">AI Announcement Structuring</h3>
              <p className="text-xs text-slate-600 leading-relaxed">
                Transforms unstructured paragraph announcements into strict JSON fields for Title, Exam Date, Time, Venue, and Required Actions.
              </p>
            </Card>

            {/* Feature 2: Smart Personalization */}
            <Card hoverable className="space-y-3">
              <div className="w-10 h-10 rounded-xl bg-emerald-50 text-emerald-600 flex items-center justify-center">
                <Filter className="w-5 h-5" />
              </div>
              <h3 className="font-semibold text-slate-900 text-base">Targeted Audience Personalization</h3>
              <p className="text-xs text-slate-600 leading-relaxed">
                Matches notices strictly against student profiles (College, Department, Semester, Clubs). S1 IT never gets spammed with S3 ECE notices.
              </p>
            </Card>

            {/* Feature 3: Verified Trust System */}
            <Card hoverable className="space-y-3">
              <div className="w-10 h-10 rounded-xl bg-blue-50 text-blue-600 flex items-center justify-center">
                <ShieldCheck className="w-5 h-5" />
              </div>
              <h3 className="font-semibold text-slate-900 text-base">Verified Source Architecture</h3>
              <p className="text-xs text-slate-600 leading-relaxed">
                Every card clearly distinguishes official department/HOD releases from student club activities, establishing clear authority.
              </p>
            </Card>

            {/* Feature 4: What You Missed */}
            <Card hoverable className="space-y-3">
              <div className="w-10 h-10 rounded-xl bg-amber-50 text-amber-600 flex items-center justify-center">
                <Clock className="w-5 h-5" />
              </div>
              <h3 className="font-semibold text-slate-900 text-base">"What You Missed" Catch-up</h3>
              <p className="text-xs text-slate-600 leading-relaxed">
                Away for 4 hours? See a 3-bullet summary of critical updates without scrolling through hundreds of messages.
              </p>
            </Card>

            {/* Feature 5: Conflict Detection */}
            <Card hoverable className="space-y-3">
              <div className="w-10 h-10 rounded-xl bg-rose-50 text-rose-600 flex items-center justify-center">
                <AlertTriangle className="w-5 h-5" />
              </div>
              <h3 className="font-semibold text-slate-900 text-base">Conflict Warning System</h3>
              <p className="text-xs text-slate-600 leading-relaxed">
                Detects conflicting exam dates or venues between sources instead of hallucinating. Prompts verification required alert.
              </p>
            </Card>

            {/* Feature 6: Calendar & Reminders */}
            <Card hoverable className="space-y-3">
              <div className="w-10 h-10 rounded-xl bg-purple-50 text-purple-600 flex items-center justify-center">
                <Calendar className="w-5 h-5" />
              </div>
              <h3 className="font-semibold text-slate-900 text-base">1-Click Calendar Export</h3>
              <p className="text-xs text-slate-600 leading-relaxed">
                Converts extracted exam & workshop dates directly into downloadable `.ics` calendar events or Google Calendar reminders.
              </p>
            </Card>
          </div>
        </Container>
      </section>

      {/* ================================================================= */}
      {/* 4. INTERACTIVE DEMO PREVIEW SECTION */}
      {/* ================================================================= */}
      <section id="demo" className="py-12 bg-slate-900 text-white rounded-3xl mx-4 sm:mx-8 p-6 sm:p-12 shadow-2xl relative">
        <Container size="lg" className="space-y-10">
          <div className="text-center max-w-2xl mx-auto space-y-3">
            <div className="inline-flex items-center gap-2 px-3 py-1 rounded-full text-xs font-semibold bg-indigo-500/20 text-indigo-300 border border-indigo-500/30">
              <Zap className="w-3.5 h-3.5 text-indigo-400" />
              <span>LIVE INTERACTIVE SHOWCASE</span>
            </div>
            <h2 className="text-3xl font-bold tracking-tight text-white">
              Try Campusly Intelligence Live
            </h2>
            <p className="text-slate-400 text-xs sm:text-sm">
              Test how Campusly structures raw announcements for admins and personalizes cards for students.
            </p>
          </div>

          <div className="grid grid-cols-1 lg:grid-cols-2 gap-8 items-start">
            {/* DEMO 1: ADMIN AI ANNOUNCEMENT STRUCTURER */}
            <div className="bg-slate-800/90 rounded-2xl p-6 border border-slate-700 space-y-4">
              <div className="flex items-center justify-between border-b border-slate-700/80 pb-3">
                <div className="flex items-center gap-2">
                  <Sparkles className="w-4 h-4 text-indigo-400" />
                  <span className="text-sm font-semibold text-slate-200">Admin AI Announcement Structurer</span>
                </div>
                <span className="text-[11px] bg-indigo-950 text-indigo-300 px-2 py-0.5 rounded-full border border-indigo-800">
                  Natural Language ➔ JSON Schema
                </span>
              </div>

              <div className="space-y-2">
                <label className="text-xs text-slate-400 font-medium">Raw Unstructured Announcement Input:</label>
                <textarea
                  value={rawInputText}
                  onChange={(e) => setRawInputText(e.target.value)}
                  rows={3}
                  className="w-full bg-slate-900 border border-slate-700 rounded-xl p-3 text-xs text-slate-200 focus:outline-none focus:border-indigo-500"
                />
              </div>

              <Button
                variant="accent"
                size="sm"
                className="w-full"
                isLoading={isAiProcessing}
                leftIcon={<Sparkles className="w-4 h-4" />}
                onClick={handleSimulateAiStructure}
              >
                Structure with Campusly AI
              </Button>

              {/* Structured AI Result Card */}
              {structuredOutput && (
                <div className="bg-slate-900 rounded-xl p-4 border border-slate-700/80 space-y-3 animate-in fade-in duration-300">
                  <div className="flex items-center justify-between">
                    <Badge category={structuredOutput.category} variant="category" />
                    <Badge priority={structuredOutput.priority} variant="priority" />
                  </div>
                  <div>
                    <h4 className="font-semibold text-white text-sm">{structuredOutput.title}</h4>
                    <p className="text-xs text-slate-400">Target Audience: {structuredOutput.audience}</p>
                  </div>
                  <div className="grid grid-cols-2 gap-2 text-xs bg-slate-800/60 p-2.5 rounded-lg text-slate-300">
                    <div>📅 <span className="font-semibold text-white">{structuredOutput.date}</span></div>
                    <div>⏰ <span className="font-semibold text-white">{structuredOutput.time}</span></div>
                    <div>📍 <span className="font-semibold text-white">{structuredOutput.location}</span></div>
                    <div>⚡ <span className="font-semibold text-indigo-400">{structuredOutput.action}</span></div>
                  </div>
                </div>
              )}
            </div>

            {/* DEMO 2: STUDENT PERSONALIZED FEED PREVIEW */}
            <div className="bg-slate-800/90 rounded-2xl p-6 border border-slate-700 space-y-4">
              <div className="flex items-center justify-between border-b border-slate-700/80 pb-3">
                <div className="flex items-center gap-2">
                  <Filter className="w-4 h-4 text-emerald-400" />
                  <span className="text-sm font-semibold text-slate-200">Personalized Student Feed</span>
                </div>

                {/* Profile Switcher Buttons */}
                <div className="flex items-center gap-1 bg-slate-900 p-1 rounded-lg border border-slate-700">
                  <button
                    onClick={() => setSelectedStudentProfile('S1_IT')}
                    className={`px-2.5 py-1 text-xs rounded-md font-medium transition-colors ${
                      selectedStudentProfile === 'S1_IT'
                        ? 'bg-indigo-600 text-white'
                        : 'text-slate-400 hover:text-white'
                    }`}
                  >
                    S1 IT Student
                  </button>
                  <button
                    onClick={() => setSelectedStudentProfile('S3_ECE')}
                    className={`px-2.5 py-1 text-xs rounded-md font-medium transition-colors ${
                      selectedStudentProfile === 'S3_ECE'
                        ? 'bg-indigo-600 text-white'
                        : 'text-slate-400 hover:text-white'
                    }`}
                  >
                    S3 ECE Student
                  </button>
                </div>
              </div>

              <div className="text-xs text-slate-400 flex items-center justify-between">
                <span>Active Profile Feed: <strong className="text-slate-200">{selectedStudentProfile === 'S1_IT' ? 'Jinto (S1 IT)' : 'Ananya (S3 ECE)'}</strong></span>
                <span className="text-emerald-400 text-[11px]">Showing {personalizedAnnouncements.length} relevant notices</span>
              </div>

              {/* Announcement List */}
              <div className="space-y-3 max-h-[280px] overflow-y-auto pr-1">
                {personalizedAnnouncements.map((ann) => (
                  <div key={ann.id} className="bg-slate-900 p-3.5 rounded-xl border border-slate-700 space-y-2 text-xs">
                    <div className="flex items-center justify-between">
                      <div className="flex items-center gap-1.5">
                        <Badge category={ann.category} variant="category" />
                        <Badge isVerified={ann.source.isVerified} variant="verified" />
                      </div>
                      <Badge priority={ann.priority} variant="priority" />
                    </div>

                    <h5 className="font-semibold text-white text-sm">{ann.structuredContent.title}</h5>
                    <p className="text-slate-400 leading-relaxed text-xs">{ann.structuredContent.summary}</p>

                    {ann.isConflictDetected && (
                      <div className="p-2 rounded-lg bg-rose-950/60 border border-rose-800 text-rose-300 text-[11px] flex items-center gap-2">
                        <AlertTriangle className="w-4 h-4 text-rose-400 shrink-0" />
                        <span><strong>Conflict Detected:</strong> Source dates mismatch ({ann.conflictDetails?.sourceA.value} vs {ann.conflictDetails?.sourceB.value})</span>
                      </div>
                    )}

                    <div className="flex items-center justify-between pt-1 text-slate-400 border-t border-slate-800">
                      <span>📍 {ann.structuredContent.location} · 📅 {ann.structuredContent.date}</span>
                      <button className="text-indigo-400 hover:underline flex items-center gap-1">
                        Add to Calendar <ExternalLink className="w-3 h-3" />
                      </button>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          </div>
        </Container>
      </section>

      {/* ================================================================= */}
      {/* 5. STUDENT VS ADMIN EXPERIENCE COMPARISON */}
      {/* ================================================================= */}
      <section id="experience" className="py-12">
        <Container size="lg" className="space-y-12">
          <div className="text-center max-w-2xl mx-auto space-y-3">
            <h2 className="text-3xl font-bold text-slate-900 tracking-tight">
              Designed for Both Key User Roles
            </h2>
            <p className="text-slate-600 text-sm">
              Empowering administrators to publish effortlessly while giving students instant clarity.
            </p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
            {/* Student Experience */}
            <Card className="p-6 space-y-4 border-slate-200">
              <div className="flex items-center gap-3">
                <div className="w-10 h-10 rounded-xl bg-indigo-100 text-indigo-700 flex items-center justify-center font-bold">
                  <UserCheck className="w-5 h-5" />
                </div>
                <div>
                  <h3 className="text-lg font-bold text-slate-900">Student Experience</h3>
                  <p className="text-xs text-slate-500">Know what matters instantly</p>
                </div>
              </div>
              <ul className="space-y-2.5 text-sm text-slate-700">
                <li className="flex items-center gap-2"><CheckCircle2 className="w-4 h-4 text-emerald-600" /> Personalized announcement feed matching Dept & Semester</li>
                <li className="flex items-center gap-2"><CheckCircle2 className="w-4 h-4 text-emerald-600" /> High-priority exam & deadline indicators</li>
                <li className="flex items-center gap-2"><CheckCircle2 className="w-4 h-4 text-emerald-600" /> 1-Click Google & ICS Calendar event export</li>
                <li className="flex items-center gap-2"><CheckCircle2 className="w-4 h-4 text-emerald-600" /> "What You Missed" 4-hour quick summary</li>
              </ul>
            </Card>

            {/* Admin Experience */}
            <Card className="p-6 space-y-4 border-slate-200">
              <div className="flex items-center gap-3">
                <div className="w-10 h-10 rounded-xl bg-slate-900 text-white flex items-center justify-center font-bold">
                  <FileText className="w-5 h-5 text-indigo-400" />
                </div>
                <div>
                  <h3 className="text-lg font-bold text-slate-900">Administrator Portal</h3>
                  <p className="text-xs text-slate-500">Publish structured notices in seconds</p>
                </div>
              </div>
              <ul className="space-y-2.5 text-sm text-slate-700">
                <li className="flex items-center gap-2"><CheckCircle2 className="w-4 h-4 text-emerald-600" /> AI Natural Language Structuring for raw notices</li>
                <li className="flex items-center gap-2"><CheckCircle2 className="w-4 h-4 text-emerald-600" /> Precise target audience selection (Dept / Semester / Club)</li>
                <li className="flex items-center gap-2"><CheckCircle2 className="w-4 h-4 text-emerald-600" /> Verified source authority tag configuration</li>
                <li className="flex items-center gap-2"><CheckCircle2 className="w-4 h-4 text-emerald-600" /> Read statistics and engagement insights</li>
              </ul>
            </Card>
          </div>
        </Container>
      </section>

      {/* ================================================================= */}
      {/* 6. FINAL CTA BANNER */}
      {/* ================================================================= */}
      <section className="py-12">
        <Container size="lg">
          <div className="bg-slate-900 text-white rounded-3xl p-8 sm:p-12 text-center space-y-6 shadow-xl relative overflow-hidden">
            <div className="max-w-2xl mx-auto space-y-3 relative z-10">
              <h2 className="text-3xl sm:text-4xl font-extrabold tracking-tight">
                Ready to eliminate campus information overload?
              </h2>
              <p className="text-slate-400 text-sm sm:text-base">
                Join Campusly today and experience intelligent, clear, and actionable college communication.
              </p>
            </div>
            <div className="flex flex-col sm:flex-row items-center justify-center gap-4 relative z-10">
              <Button
                variant="accent"
                size="lg"
                rightIcon={<ArrowRight className="w-5 h-5" />}
                onClick={() => {
                  const demoEl = document.getElementById('demo')
                  if (demoEl) demoEl.scrollIntoView({ behavior: 'smooth' })
                }}
              >
                Launch Campusly Demo
              </Button>
            </div>
          </div>
        </Container>
      </section>
    </div>
  )
}

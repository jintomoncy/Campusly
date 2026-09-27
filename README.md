# CAMPUSLY

> *"Don't scroll. Know what matters."*

Campusly is an intelligent information layer connecting college and community administrators with students. It enables administrators to publish announcements and transforms them into clear, structured, and actionable cards for targeted student audiences.

---

## 🎯 Key Features & Vision

- 🏫 **Admin Announcement Publishing**: Simple, centralized publishing portal for campus announcements.
- 🤖 **AI-Powered Structuring**: Intelligent extraction of key details, dates, and action items.
- 🎯 **Targeted Audience Matching**: Delivers relevant information directly to intended student groups.
- 📅 **Automatic Event & Deadline Extraction**: Keeps students aware of upcoming schedules.
- 🔐 **Verified Information Source**: Eliminates clutter from scattered messaging channels.

---

## 🏗 Project Structure

```
campusly/
├── frontend/    # React + Vite + TypeScript + Tailwind CSS
├── backend/     # Python + FastAPI
├── README.md
└── .gitignore
```

---

## 🛠 Tech Stack

- **Frontend**: React, TypeScript, Vite, Tailwind CSS
- **Backend**: Python, FastAPI, Uvicorn

---

## 🚀 Getting Started

### Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

### Backend Setup

```bash
cd backend
python -m venv venv

# Windows (PowerShell):
.\venv\Scripts\Activate.ps1

# Linux / macOS:
# source venv/bin/activate

pip install -r requirements.txt
uvicorn main:app --reload
```

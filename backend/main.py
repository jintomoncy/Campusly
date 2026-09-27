from fastapi import FastAPI

app = FastAPI(
    title="Campusly API",
    description="Intelligent information layer connecting college admins with students.",
    version="0.1.0"
)

@app.get("/")
def read_root():
    return {"message": "Campusly API is running"}

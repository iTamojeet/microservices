from fastapi import FastAPI

app = FastAPI()

BOOKS = [
    {"title":"A","author":"B","category":"C"},
    {"title":"A","author":"B","category":"C"},
    {"title":"A","author":"B","category":"C"}
]

@app.get("/python")
def call():
    return "I am python backend"

@app.get("/books")
def callBooks():
    return BOOKS
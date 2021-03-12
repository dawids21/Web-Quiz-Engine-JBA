# Web Quiz Engine
This project was built during learning from JetBrains Academy platform. It is a REST API, that can be used to implement quiz system with an option of answering questions and creating them.

# Endpoints

## Quizzes

### GET
- `/quizzes?n` - get all quizzes on page "n"
- `/quizzes/{id}` - get quiz with given id
- `/quizzes/completed?n` - get nth page with completed quizzes

### POST
- `/quizzes` - add new Quiz
- `/quizzes/{id}/solve` - post an answer to a quiz

### DELETE
- `/quizzes/{id}` - delete given quiz

## Account
Authentication is implemented using HTTP Basic Auth.

### POST
- `/register` - create an account

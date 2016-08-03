package com.example.jamiekilburn.quizapp

class WrongNumberOfAddendsException: Exception() {
    override val message = "Uh oh!"
}

class DuplicateCorrectAnswers(question: String, answerChoices: MutableList<String>): Exception() {
    override val message = "Uh oh! It looks like there's more than one correct answer here." +
            "\n|       Question: $question" +
            "\n|       Answer Choices: $answerChoices"
}

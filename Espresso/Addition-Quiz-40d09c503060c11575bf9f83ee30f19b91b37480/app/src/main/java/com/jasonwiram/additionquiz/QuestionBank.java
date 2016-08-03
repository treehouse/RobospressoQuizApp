package com.jasonwiram.additionquiz;

import java.util.Random;

public class QuestionBank {

    public String mQuestion;
    public String mCorrectAnswer;
    public String mFirstIncorrectAnswer;
    public String mSecondIncorrectAnswer;

    public void setQuestionAndAnswers() {
        Random randomGenerator = new Random();
        int leftAdder = randomGenerator.nextInt(100);
        int rightAdder = randomGenerator.nextInt(100);
        int correctAnswer = leftAdder + rightAdder;
        mQuestion = "What is " + leftAdder + " + " + rightAdder + "?";
        mCorrectAnswer = String.valueOf(leftAdder + rightAdder);
        mFirstIncorrectAnswer = String.valueOf(correctAnswer + randomGenerator.nextInt(10));
        mSecondIncorrectAnswer = String.valueOf(correctAnswer - randomGenerator.nextInt(10));
    }
}

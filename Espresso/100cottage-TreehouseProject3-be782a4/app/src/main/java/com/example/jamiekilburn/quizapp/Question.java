package com.example.jamiekilburn.quizapp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by jamiekilburn on 16-07-16.
 */
public class Question {
    private String question;
    List<Integer>answers;
    int correct;
    public Question(){
        Random random = new Random();
        int a = random.nextInt(30)+1;
        int b = random.nextInt(30)+1;
        Integer[]choices = new Integer[3];
        choices[0] = a+b;
        choices[1] = a+b+random.nextInt(4)+1;
        int ch = a-b+random.nextInt(7)+1;
        choices[2] = (ch == a+b) || ch <= 0 ? Math.max(a+b +12, a+b+random.nextInt(16)) : ch;
         answers = Arrays.asList(choices);
        Collections.shuffle(answers);
        for(int i = 0; i < answers.size(); i++){
            if(answers.get(i).equals((a+b))){
                correct = i;
            }
        }
        question = (a) + " + " + (b);


    }

    public List<Integer >getAnswers() {
        return answers;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getCorrectAnswerIndex(){
        return correct;
    }







}

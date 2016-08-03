package com.jasonwiram.additionquiz;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class AdditionQuizActivity extends AppCompatActivity {
    private MediaPlayer wrongSound;
    private MediaPlayer correctSound;
    private QuestionBank mQuestionBank = new QuestionBank();
    private RadioGroup mRadioGroup;
    private RadioButton mAnswerButton1;
    private RadioButton mAnswerButton2;
    private RadioButton mAnswerButton3;
    private Button mSubmitButton;
    private TextView mQuestionTextView;
    private TextView mRight;
    private TextView mAttempted;
    private String mChoice = "default";
    private String mToast;
    private int numRight = 0;
    private int numAttempted = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_quiz);

        wrongSound = MediaPlayer.create(this, R.raw.wrong);
        correctSound = MediaPlayer.create(this, R.raw.correct);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mAnswerButton1 = (RadioButton) findViewById(R.id.button1);
        mAnswerButton2 = (RadioButton) findViewById(R.id.button2);
        mAnswerButton3 = (RadioButton) findViewById(R.id.button3);
        mSubmitButton = (Button) findViewById(R.id.submitButton);
        mQuestionTextView = (TextView) findViewById(R.id.QuestionTextView);
        mRight = (TextView) findViewById(R.id.rightTextView);
        mAttempted = (TextView) findViewById(R.id.attemptedTextView);

        nextQuestion();

        View.OnClickListener listener1 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mChoice = (String) mAnswerButton1.getText();
            }
        };

        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChoice = (String) mAnswerButton2.getText();
            }
        };

        View.OnClickListener listener3 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChoice = (String) mAnswerButton3.getText();
            }
        };

        mAnswerButton1.setOnClickListener(listener1);
        mAnswerButton2.setOnClickListener(listener2);
        mAnswerButton3.setOnClickListener(listener3);

        mSubmitButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!mChoice.equalsIgnoreCase("default")) {
                            if (mChoice.equalsIgnoreCase(mQuestionBank.mCorrectAnswer)) {
                                correctSound.start();
                                mToast = "Correct!";
                                numRight++;
                            } else {
                                wrongSound.start();
                                mToast = "Incorrect.";
                            }
                            numAttempted++;
                            Toast.makeText(AdditionQuizActivity.this, mToast, Toast.LENGTH_SHORT).show();
                            nextQuestion();
                            mRadioGroup.clearCheck();
                            mChoice = "default";
                        } else {
                            Toast.makeText(AdditionQuizActivity.this, "Choose an answer.", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void nextQuestion() {
        mRight.setText(String.valueOf(numRight));
        mAttempted.setText(String.valueOf(numAttempted));
        mQuestionBank.setQuestionAndAnswers();
        mQuestionTextView.setText(mQuestionBank.mQuestion);
        assignAnswerPosition();
    }

    public void assignAnswerPosition() {
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(3);
        if (randomNumber == 0) {
            mAnswerButton1.setText(mQuestionBank.mCorrectAnswer);
            mAnswerButton2.setText(mQuestionBank.mFirstIncorrectAnswer);
            mAnswerButton3.setText(mQuestionBank.mSecondIncorrectAnswer);
        } else if (randomNumber == 1) {
            mAnswerButton2.setText(mQuestionBank.mCorrectAnswer);
            mAnswerButton1.setText(mQuestionBank.mFirstIncorrectAnswer);
            mAnswerButton3.setText(mQuestionBank.mSecondIncorrectAnswer);
        } else if (randomNumber == 2) {
            mAnswerButton3.setText(mQuestionBank.mCorrectAnswer);
            mAnswerButton1.setText(mQuestionBank.mFirstIncorrectAnswer);
            mAnswerButton2.setText(mQuestionBank.mSecondIncorrectAnswer);
        }
    }
}


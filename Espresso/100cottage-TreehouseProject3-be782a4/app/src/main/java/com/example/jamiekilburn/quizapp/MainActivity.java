package com.example.jamiekilburn.quizapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements DialogInterface.OnClickListener {

Question question;
    Button button1,button2,button3;
    TextView questionText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.question1);
        button2 =  (Button) findViewById(R.id.question2);
        button3 = (Button) findViewById(R.id.question3);
        questionText = (TextView) findViewById(R.id.questionText);
        generateNewQuestion();





    }
    public void generateNewQuestion(){
        question = new Question();
        questionText.setText(question.getQuestion());
        button1.setText(Integer.toString(question.getAnswers().get(0)));
        button2.setText(Integer.toString(question.getAnswers().get(1)));
        button3.setText(Integer.toString(question.getAnswers().get(2)));

        button1.setBackgroundColor(Color.TRANSPARENT);
        button2.setBackgroundColor(Color.TRANSPARENT);
        button3.setBackgroundColor(Color.TRANSPARENT);




    }
    public void sendToast(boolean correct){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        if(correct) {


            Toast toast = Toast.makeText(context, "That's correct!", duration);
            toast.show();

        }else{
            Toast toast = Toast.makeText(context,"Sorry, that isn't the correct answer.", duration);
            toast.show();


        }

    }

    public void onQuestionOneClicked(View arg0){
        if(question.getCorrectAnswerIndex() == 0){
            sendToast(true);
            button1.setBackgroundColor(Color.GREEN);

        }else{
            sendToast(false);
            button1.setBackgroundColor(Color.RED);
        }


        generateNewQuestion();
    }
    public void onQuestionTwoClicked(View arg0){
        if(question.getCorrectAnswerIndex() == 1){

            sendToast(true);
        }else{
            sendToast(false);
        }

        generateNewQuestion();

    }
    public void onQuestionThreeClicked(View arg0){
        if(question.getCorrectAnswerIndex() == 2){
            sendToast(true);
        }else{
            sendToast(false);
        }
        generateNewQuestion();

    }



    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }


}

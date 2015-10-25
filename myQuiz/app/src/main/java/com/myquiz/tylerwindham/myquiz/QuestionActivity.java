package com.myquiz.tylerwindham.myquiz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class QuestionActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);


        TextView question = (TextView) findViewById(R.id.question);

        String q = "What is the capitol of Thailand?";
        List<String> answers = new ArrayList<>();
        answers.add("China");
        answers.add("Bangcock");
        answers.add("Texas");
        answers.add("Bombay");
        answers.add("Hamburg");
        final String answer = "B";
        final Question ques = new Question(q, answer, answers);

        String q2 = "What is the capitol of Austin?";
        List<String> answers2 = new ArrayList<>();
        answers.add("Austin");
        answers.add("Houston");
        answers.add("Dallas");
        answers.add("San Antonio");
        answers.add("Amarillo");

        final String answer2 = "A";

        final Question ques2 = new Question(q2, answer2, answers2);

        List<Question> questions = new ArrayList<Question>();
        questions.add(ques);
        questions.add(ques2);

        final Quiz quiz = new Quiz(questions);


            question.setText(quiz.questionList.get(quiz.current).question);


            final Button aButton = (Button) findViewById(R.id.aButton);
            final Button bButton = (Button) findViewById(R.id.bButton);
            final Button cButton = (Button) findViewById(R.id.cButton);
            final Button dButton = (Button) findViewById(R.id.dButton);
            final Button eButton = (Button) findViewById(R.id.eButton);


            aButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(aButton.getText().toString().equals(ques.answer)){
                        quiz.current++;
                        Toast toast = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG);
                        toast.show();
                    }else{
                        quiz.current++;
                        Toast toast = Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            });

            bButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(bButton.getText().toString().equals(ques.answer)){
                        quiz.current++;
                        Toast toast = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG);
                        toast.show();
                    }else{
                        quiz.current++;
                        Toast toast = Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            });

            cButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cButton.getText().toString().equals(ques.answer)){
                        quiz.current++;
                        Toast toast = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG);
                        toast.show();
                    }else{
                        quiz.current++;
                        Toast toast = Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            });

            dButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dButton.getText().toString().equals(ques.answer)){
                        quiz.current++;
                        Toast toast = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG);
                        toast.show();
                    }else{
                        quiz.current++;
                        Toast toast = Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            });

            eButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quiz.current++;
                    if(eButton.getText().toString().equals(ques.answer)){
                        Toast toast = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG);
                        toast.show();
                    }else{
                        quiz.current++;
                        Toast toast = Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            });













    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

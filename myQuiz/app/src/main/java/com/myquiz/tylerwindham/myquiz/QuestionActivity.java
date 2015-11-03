package com.myquiz.tylerwindham.myquiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends ActionBarActivity {

    public Quiz getQuizQs(){
        String q = "What is the capitol of Thailand?";
        List<String> answers = new ArrayList<>();
        answers.add("China");
        answers.add("Bangcock");
        answers.add("Texas");
        answers.add("Bombay");
        answers.add("Hamburg");
        final String answer = "B";
        final Question ques = new Question(q, answer, answers);

        String q2 = "What is the capitol of Texas?";
        List<String> answers2 = new ArrayList<>();
        answers2.add("Austin");
        answers2.add("Houston");
        answers2.add("Dallas");
        answers2.add("San Antonio");
        answers2.add("Amarillo");

        final String answer2 = "A";

        final Question ques2 = new Question(q2, answer2, answers2);

        List<Question> questions = new ArrayList<Question>();
        questions.add(ques);
        questions.add(ques2);

        final Quiz quizQuestions = new Quiz(questions);
        quizQuestions.quizName = "Quiz 1";
        return quizQuestions;
    }
    public int getNums(){
        return getQuizQs().size();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);

        final TextView question = (TextView) findViewById(R.id.question);
        final TextView score = (TextView) findViewById(R.id.score);
        String q = "What is the capitol of Thailand?";
        List<String> answers = new ArrayList<>();
        answers.add("China");
        answers.add("Bangcock");
        answers.add("Texas");
        answers.add("Bombay");
        answers.add("Hamburg");
        final String answer = "B";
        final Question ques = new Question(q, answer, answers);

        String q2 = "What is the capitol of Texas?";
        List<String> answers2 = new ArrayList<>();
        answers2.add("Austin");
        answers2.add("Houston");
        answers2.add("Dallas");
        answers2.add("San Antonio");
        answers2.add("Amarillo");

        final String answer2 = "A";

        final Question ques2 = new Question(q2, answer2, answers2);

        List<Question> questions = new ArrayList<Question>();
        questions.add(ques);
        questions.add(ques2);

        final Quiz quiz = new Quiz(questions);
        quiz.quizName = "Quiz 1";


            question.setText(quiz.questionList.get(quiz.current).question);
            score.setText("Score: " + quiz.score);


            final Button aButton = (Button) findViewById(R.id.aButton);
            final Button bButton = (Button) findViewById(R.id.bButton);
            final Button cButton = (Button) findViewById(R.id.cButton);
            final Button dButton = (Button) findViewById(R.id.dButton);
            final Button eButton = (Button) findViewById(R.id.eButton);


            aButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(aButton.getText().toString().equals(ques.answer)){
                        if(quiz.current+1 == quiz.questionList.size()){
                            //Reached last question, return the final score
                            quiz.current++;
                           quiz.correctCount++;
                            quiz.score = ((double)quiz.correctCount / quiz.questionList.size()) * 100 ;
                            Toast toast = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG);
                            toast.show();

                            try {
                                InternalStorage.writeObject(getApplicationContext(),"Quiz", quiz);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Intent intent = new Intent(v.getContext(), ScoreActivity.class);
                            intent.putExtra("quizObj", quiz);
                            intent.putExtra("score", quiz.score);
                            startActivityForResult(intent,0);
                        }else{
                            quiz.current++;
                            quiz.correctCount++;
                            quiz.score = ((double)quiz.correctCount / quiz.questionList.size()) * 100 ;
                            ques.answer = quiz.questionList.get(quiz.current).answer;
                            ques.question = quiz.questionList.get(quiz.current).question;
                            ques.answers = quiz.questionList.get(quiz.current).answers;
                            question.setText(quiz.questionList.get(quiz.current).question);
                            score.setText("Score: " + quiz.score);

                            Toast toast = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG);
                            toast.show();
                        }

                    }else{
                        if(quiz.current+1 == quiz.questionList.size()){
                            //Reached last question, return the final score
                            quiz.current++;
                            quiz.score = ((double)quiz.correctCount / quiz.questionList.size()) * 100 ;


                            Toast toast = Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG);
                            toast.show();

                            try {
                                InternalStorage.writeObject(getApplicationContext(),"Quiz", quiz);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Intent intent = new Intent(v.getContext(), ScoreActivity.class);
                            intent.putExtra("score", quiz.score);
                            intent.putExtra("quizObj", quiz);
                            startActivityForResult(intent,0);

                        }else{
                            quiz.current++;
                            quiz.score = ((double)quiz.correctCount / quiz.questionList.size()) * 100 ;
                            ques.answer = quiz.questionList.get(quiz.current).answer;
                            ques.question = quiz.questionList.get(quiz.current).question;
                            ques.answers = quiz.questionList.get(quiz.current).answers;
                            question.setText(quiz.questionList.get(quiz.current).question);
                            score.setText("Score: " + quiz.score);
                            Toast toast = Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG);
                            toast.show();
                        }

                    }
                }
            });

            bButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(bButton.getText().toString().equals(ques.answer)){
                        if(quiz.current+1 == quiz.questionList.size()){
                            //Reached last question, return the final score
                            quiz.current++;
                            quiz.correctCount++;
                            quiz.score = ((double)quiz.correctCount / quiz.questionList.size()) * 100 ;

                            Toast toast = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG);
                            toast.show();

                            try {
                                InternalStorage.writeObject(getApplicationContext(),"Quiz", quiz);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Intent intent = new Intent(v.getContext(), ScoreActivity.class);
                            intent.putExtra("score", quiz.score);
                            intent.putExtra("quizObj", quiz);
                            startActivityForResult(intent,0);
                        }else{
                            quiz.current++;
                            quiz.correctCount++;
                            quiz.score = ((double)quiz.correctCount / quiz.questionList.size()) * 100 ;
                            ques.answer = quiz.questionList.get(quiz.current).answer;
                            ques.question = quiz.questionList.get(quiz.current).question;
                            ques.answers = quiz.questionList.get(quiz.current).answers;
                            question.setText(quiz.questionList.get(quiz.current).question);
                            score.setText("Score: " + quiz.score);

                            Toast toast = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG);
                            toast.show();
                        }

                    }else{
                        if(quiz.current+1 == quiz.questionList.size()){
                            //Reached last question, return the final score
                            quiz.current++;

                            quiz.score = ((double)quiz.correctCount/ quiz.questionList.size()) * 100 ;
                            Toast toast = Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG);
                            toast.show();

                            try {
                                InternalStorage.writeObject(getApplicationContext(),"Quiz", quiz);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Intent intent = new Intent(v.getContext(), ScoreActivity.class);
                            intent.putExtra("score", quiz.score);
                            intent.putExtra("quizObj", quiz);
                            startActivityForResult(intent,0);

                        }else{
                            quiz.current++;
                            quiz.score = ((double)quiz.correctCount / quiz.questionList.size()) * 100 ;
                            ques.answer = quiz.questionList.get(quiz.current).answer;
                            ques.question = quiz.questionList.get(quiz.current).question;
                            ques.answers = quiz.questionList.get(quiz.current).answers;
                            question.setText(quiz.questionList.get(quiz.current).question);
                            score.setText("Score: " + quiz.score);
                            Toast toast = Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG);
                            toast.show();
                        }

                    }
                }
            });

            cButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cButton.getText().toString().equals(ques.answer)){
                        if(quiz.current+1 == quiz.questionList.size()){
                            //Reached last question, return the final score
                            quiz.current++;
                            quiz.correctCount++;
                            quiz.score = ((double)quiz.correctCount / quiz.questionList.size()) * 100 ;


                            Toast toast = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG);
                            toast.show();

                            try {
                                InternalStorage.writeObject(getApplicationContext(),"Quiz", quiz);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Intent intent = new Intent(v.getContext(), ScoreActivity.class);
                            intent.putExtra("score", quiz.score);
                            intent.putExtra("quizObj", quiz);
                            startActivityForResult(intent,0);
                        }else{
                            quiz.current++;
                            quiz.correctCount++;
                            quiz.score = ((double)quiz.correctCount / quiz.questionList.size()) * 100 ;
                            ques.answer = quiz.questionList.get(quiz.current).answer;
                            ques.question = quiz.questionList.get(quiz.current).question;
                            ques.answers = quiz.questionList.get(quiz.current).answers;
                            question.setText(quiz.questionList.get(quiz.current).question);
                            score.setText("Score: " + quiz.score);

                            Toast toast = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG);
                            toast.show();
                        }

                    }else{
                        if(quiz.current+1 == quiz.questionList.size()){
                            //Reached last question, return the final score
                            quiz.current++;

                            quiz.score = ((double)quiz.correctCount / quiz.questionList.size()) * 100 ;
                            Toast toast = Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG);
                            toast.show();

                            try {
                                InternalStorage.writeObject(getApplicationContext(),"Quiz", quiz);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            Intent intent = new Intent(v.getContext(), ScoreActivity.class);
                            intent.putExtra("score", quiz.score);
                            intent.putExtra("quizObj", quiz);
                            startActivityForResult(intent,0);

                        }else{
                            quiz.current++;
                            quiz.score = ((double)quiz.correctCount / quiz.questionList.size()) * 100 ;
                            ques.answer = quiz.questionList.get(quiz.current).answer;
                            ques.question = quiz.questionList.get(quiz.current).question;
                            ques.answers = quiz.questionList.get(quiz.current).answers;
                            question.setText(quiz.questionList.get(quiz.current).question);
                            score.setText("Score: " + quiz.score);
                            Toast toast = Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG);
                            toast.show();
                        }

                    }
                }
            });

            dButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dButton.getText().toString().equals(ques.answer)){
                        if(quiz.current+1 == quiz.questionList.size()){
                            //Reached last question, return the final score
                            quiz.current++;
                            quiz.correctCount++;
                            quiz.score = ((double)quiz.correctCount / quiz.questionList.size()) * 100 ;

                            Toast toast = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG);
                            toast.show();

                            try {
                                InternalStorage.writeObject(getApplicationContext(),"Quiz", quiz);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Intent intent = new Intent(v.getContext(), ScoreActivity.class);
                            intent.putExtra("score", quiz.score);
                            intent.putExtra("quizObj", quiz);
                            startActivityForResult(intent,0);
                        }else{
                            quiz.current++;
                            quiz.correctCount++;
                            quiz.score = ((double)quiz.correctCount / quiz.questionList.size()) * 100 ;
                            ques.answer = quiz.questionList.get(quiz.current).answer;
                            ques.question = quiz.questionList.get(quiz.current).question;
                            ques.answers = quiz.questionList.get(quiz.current).answers;
                            question.setText(quiz.questionList.get(quiz.current).question);
                            score.setText("Score: " + quiz.score);

                            Toast toast = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG);
                            toast.show();
                        }

                    }else{
                        if(quiz.current+1 == quiz.questionList.size()){
                            //Reached last question, return the final score
                            quiz.current++;

                            quiz.score = ((double)quiz.correctCount / quiz.questionList.size()) * 100 ;

                            Toast toast = Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG);
                            toast.show();

                            try {
                                InternalStorage.writeObject(getApplicationContext(),"Quiz", quiz);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Intent intent = new Intent(v.getContext(), ScoreActivity.class);
                            intent.putExtra("score", quiz.score);
                            intent.putExtra("quizObj", quiz);
                            startActivityForResult(intent,0);

                        }else{
                            quiz.current++;
                            quiz.score = ((double)quiz.correctCount / quiz.questionList.size()) * 100 ;
                            ques.answer = quiz.questionList.get(quiz.current).answer;
                            ques.question = quiz.questionList.get(quiz.current).question;
                            ques.answers = quiz.questionList.get(quiz.current).answers;
                            question.setText(quiz.questionList.get(quiz.current).question);
                            score.setText("Score: " + quiz.score);
                            Toast toast = Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG);
                            toast.show();
                        }

                    }
                }
            });


            eButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    eButton.setBackgroundColor(Color.GRAY);


                    if(eButton.getText().toString().equals(ques.answer)){
                        if(quiz.current+1 == quiz.questionList.size()){
                            //Reached last question, return the final score
                            quiz.current++;
                            quiz.correctCount++;
                            quiz.score = ((double)quiz.correctCount / quiz.questionList.size()) * 100 ;


                            Toast toast = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG);
                            toast.show();

                            try {
                                InternalStorage.writeObject(getApplicationContext(),"Quiz", quiz);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Intent intent = new Intent(v.getContext(), ScoreActivity.class);
                            intent.putExtra("score", quiz.score);
                            intent.putExtra("quizObj", quiz);
                            startActivityForResult(intent, 0);
                        }else{
                            quiz.current++;
                            quiz.correctCount++;
                            quiz.score = ((double)quiz.correctCount / quiz.questionList.size()) * 100 ;
                            ques.answer = quiz.questionList.get(quiz.current).answer;
                            ques.question = quiz.questionList.get(quiz.current).question;
                            ques.answers = quiz.questionList.get(quiz.current).answers;
                            question.setText(quiz.questionList.get(quiz.current).question);
                            score.setText("Score: " + quiz.score);

                            Toast toast = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG);
                            toast.show();
                        }

                    }else{
                        if(quiz.current+1 == quiz.questionList.size()){
                            //Reached last question, return the final score
                            quiz.current++;

                            quiz.score = ((double)quiz.correctCount / quiz.questionList.size()) * 100 ;

                            Toast toast = Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG);
                            toast.show();

                            try {
                                InternalStorage.writeObject(getApplicationContext(),"Quiz", quiz);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Intent intent = new Intent(v.getContext(), ScoreActivity.class);
                            intent.putExtra("score", quiz.score);
                            intent.putExtra("quizObj", quiz);
                            startActivityForResult(intent,0);

                        }else{
                            quiz.current++;
                            quiz.score = ((double)quiz.correctCount / quiz.questionList.size()) * 100 ;
                            ques.answer = quiz.questionList.get(quiz.current).answer;
                            ques.question = quiz.questionList.get(quiz.current).question;
                            ques.answers = quiz.questionList.get(quiz.current).answers;
                            question.setText(quiz.questionList.get(quiz.current).question);
                            score.setText("Score: " + quiz.score);
                            Toast toast = Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG);
                            toast.show();
                        }

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

    public void onBackPressed() {
        // your code.
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Leave Quiz")
                .setMessage("Are you sure you want to leave the quiz?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Stop the activity
                       QuestionActivity.this.finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
 }


package com.myquiz.tylerwindham.myquiz;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class GetQuizActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TAG", "IN ON CREATE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_quiz);

       final EditText urlField = (EditText) findViewById(R.id.quizURL);

        Button getQuizButton = (Button) findViewById(R.id.getQuizButton);



        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);



            getQuizButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        // Create a URL for the desired page
                        String urlString = urlField.getText().toString();
                        Log.d("URL", urlString);
                        URL url = new URL(urlString);

                        // Read all the text returned by the server
                        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                        String str = org.apache.commons.io.IOUtils.toString(in);
                        Log.d("FILE", str);

                        Parser parser = new Parser();
                        final Quiz quiz = parser.getQuiz(str);

                        Intent intent = new Intent(v.getContext(), QuestionActivity.class);
                        intent.putExtra("quiz", quiz);
//                   / finish();
                        startActivityForResult(intent, 0);

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }






                }
            });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_quiz, menu);
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

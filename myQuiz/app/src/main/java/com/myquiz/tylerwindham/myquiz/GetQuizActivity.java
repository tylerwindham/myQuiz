package com.myquiz.tylerwindham.myquiz;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class GetQuizActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TAG", "IN ON CREATE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_quiz);
        setTitle("Download Quiz");
        Spinner spinner = (Spinner) findViewById(R.id.quizURL);
        String[] spinnerArray = new String[2];
        final Map<String, String> quizMap = new HashMap<String,String>();
        quizMap.put("Quiz1","http://people.tamu.edu/~almeagher/app/test.txt");
        quizMap.put("Quiz2","http://students.cse.tamu.edu/iks5005/");
        spinnerArray[0] = "Quiz1";
        spinnerArray[1] = "Quiz2";
        final String[] qn = new String[1];
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, spinnerArray);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("item", (String) parent.getItemAtPosition(position));
                qn[0] = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //String quizName = spinner.getSelectedItem().toString();
        //String quizName = qn[0];
        //Log.d("QUIZNAME", quizName);
       //final String urlString = quizMap.get(quizName);



       //final EditText urlField = (EditText) findViewById(R.id.quizURL);


        Button getQuizButton = (Button) findViewById(R.id.getQuizButton);



        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);



            getQuizButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        String quizName = qn[0];
                        String urlString = quizMap.get(quizName);
                        // Create a URL for the desired page
                        //String urlString = urlField.getText().toString();
                       // Log.d("URL", urlString);
                        //URL url = new URL(urlString);
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

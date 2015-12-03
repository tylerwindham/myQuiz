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

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        final String[] quizMapString = new String[1];

        String quizMapURL = "http://students.cse.tamu.edu/iks5005/quizPortal.txt";
        URL url = null;
        try {
            url = new URL(quizMapURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Read all the text returned by the server
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str = org.apache.commons.io.IOUtils.toString(in);
            quizMapString[0] = str;

            List<String> postedQuizzes = new ArrayList<String>();

            InputStream in2 = IOUtils.toInputStream(quizMapString[0], "UTF-8");
            InputStreamReader streamReader = new InputStreamReader(in2);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                postedQuizzes.add(line);
                Log.i("buffer reader", line);
            }
            List<String[]> stringList = new ArrayList<String[]>();
            for(int i=0; i < postedQuizzes.size(); ++i){
                String s = postedQuizzes.get(i);
                String[] words = s.split("\\s+");
                for (int j = 0; j < words.length; j++) {
                    // You may want to check for a non-word character before blindly
                    // performing a replacement
                    // It may also be necessary to adjust the character class
                    words[j] = words[j].replaceAll("[^\\w]", "");
                }

                stringList.add(words);
            }


            Log.d("DEBUG", Integer.toString(stringList.size()));


        }catch (IOException e) {
            e.printStackTrace();
        }










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

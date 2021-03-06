package com.myquiz.tylerwindham.myquiz;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.os.StrictMode;

import android.os.AsyncTask;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import mehdi.sakout.fancybuttons.FancyButton;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;



public class HomeActivity extends ActionBarActivity {

    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //THE ATTEMPT THAT CALLS PARSE CLASS
        //Parser tester = new Parser();
        //HomeActivity.context = getApplicationContext();
        //tester.getFile(context);

        /*TRIED DOING IT STRAIGHT UP HERE INSTEAD
        AssetManager assetManager = getAssets();

        InputStream input;
        try {
            input = assetManager.open("test.txt");
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();
            String text = new String(buffer);


        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //Log.d("INSIDE HOME ACTIVITY", "BBBBBBBBBBB");
        setContentView(R.layout.activity_home);
        ActionBar bar = getActionBar();

       //bar.setBackgroundDrawable(new ColorDrawable((Color.parseColor("#009688"))));
        FancyButton takeQuiz = (FancyButton) findViewById(R.id.takeQuizButton);
        takeQuiz.setFocusBackgroundColor(Color.parseColor("#B6B6B6"));
        FancyButton pastQuiz = (FancyButton) findViewById(R.id.pastQuizzesButton);
        pastQuiz.setFocusBackgroundColor(Color.parseColor("#B6B6B6"));


        SharedPreferences settings = getSharedPreferences(Preferences.PREFS_NAME, 0); // 0 - for private mode
        SharedPreferences.Editor editor = settings.edit();

//Set "hasLoggedIn" to true
        editor.putBoolean("hasLoggedIn", true);

// Commit the edits!
        editor.commit();

        String username = getIntent().getStringExtra("username");
        Log.d("USERNAME", username);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        try {
            // Create a URL for the desired page
            URL url = new URL("http://students.cse.tamu.edu/iks5005/");

            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str = org.apache.commons.io.IOUtils.toString(in);
            Log.d("FILE", str);

            Parser parser = new Parser();
            final Quiz quiz = parser.getQuiz(str);


            takeQuiz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Intent intent = new Intent(v.getContext(), QuestionActivity.class);
                   // intent.putExtra("quiz", quiz);
                   // startActivityForResult(intent,0);

                    Intent intent = new Intent(v.getContext(), GetQuizActivity.class);
                    startActivityForResult(intent,0);


                }
            });

            pastQuiz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PastQuizzesActivity.class);
                    startActivityForResult(intent,0);

                }
            });;





        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }










    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

//    class task extends AsyncTask<String, String, Void> {
//        private ProgressDialog progDialog = new ProgressDialog(HomeActivity.this);
//        InputStream is = null;
//        String result = "";
//        protected void onPreExecute(){
//            progDialog.setMessage("Fetching data...");
//            progDialog.show();
//            progDialog.setOnCancelListener(new DialogInterface.OnCancelListener()) {
//                @Override
//                public void onCancel(DialogInterface) {
//                    task.this.cancel(true);
//                }
//            });
//        }
//    }

}

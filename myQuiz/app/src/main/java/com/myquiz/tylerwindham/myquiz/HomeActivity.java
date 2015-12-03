package com.myquiz.tylerwindham.myquiz;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.os.StrictMode;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import mehdi.sakout.fancybuttons.FancyButton;
//import com.google.api.services.drive.Drive;
//mport com.google.api.services.drive.model.File;



public class HomeActivity extends ActionBarActivity {

    private GoogleApiClient mGoogleApiClient;

    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        //String username = getIntent().getStringExtra("username");
        //Log.d("USERNAME", username);
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

        if (id == R.id.sign_out) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, 0);
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

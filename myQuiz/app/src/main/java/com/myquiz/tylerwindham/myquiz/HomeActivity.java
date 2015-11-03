package com.myquiz.tylerwindham.myquiz;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import mehdi.sakout.fancybuttons.FancyButton;

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
            setContentView(R.layout.activity_home);
        ActionBar bar = getActionBar();
       //bar.setBackgroundDrawable(new ColorDrawable((Color.parseColor("#009688"))));
        FancyButton takeQuiz = (FancyButton) findViewById(R.id.takeQuizButton);
        takeQuiz.setFocusBackgroundColor(Color.parseColor("#B6B6B6"));
        FancyButton pastQuiz = (FancyButton) findViewById(R.id.pastQuizzesButton);
        pastQuiz.setFocusBackgroundColor(Color.parseColor("#B6B6B6"));

        Parser parser = new Parser();
        parser.getFile(getApplicationContext());


        takeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), QuestionActivity.class);
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

}

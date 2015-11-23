package com.myquiz.tylerwindham.myquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import mehdi.sakout.fancybuttons.FancyButton;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText username = (EditText) findViewById(R.id.usernameField);
        final EditText password = (EditText) findViewById(R.id.passwordField);

        FancyButton loginButton = (FancyButton) findViewById(R.id.loginButton);
        loginButton.setFocusBackgroundColor(Color.parseColor("#B6B6B6"));

        SharedPreferences pref = this.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        getSharedPreferences("pref", Context.MODE_PRIVATE).edit().clear().commit();

        SharedPreferences settings = getSharedPreferences(Preferences.PREFS_NAME, 0);
        //Get "hasLoggedIn" value. If the value doesn't exist yet false is returned
        boolean hasLoggedIn = settings.getBoolean("hasLoggedIn", false);

        Log.d ("BOOLEAN", Boolean.toString(hasLoggedIn));


        /*
        if(hasLoggedIn)
        {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivityForResult(intent, 0);
            LoginActivity.this.finish();
            Log.d("TAG", "IN DISSS");
        }
        */

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.length() == 0 || password.length() == 0){
                    Toast toast = Toast.makeText(getApplicationContext(), "Must provide credentials", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                Intent intent = new Intent(v.getContext(), HomeActivity.class);
                intent.putExtra("username", username.getText().toString());
                startActivityForResult(intent,0);

            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

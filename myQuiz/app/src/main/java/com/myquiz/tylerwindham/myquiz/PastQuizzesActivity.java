package com.myquiz.tylerwindham.myquiz;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PastQuizzesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_quizzes);
        final ListView listView = (ListView) findViewById(R.id.quizzes);
        // registerForContextMenu(listView);

        setTitle("Past Quizzes");
        //  ActionBar bar = getActionBar();
        //bar.setDisplayHomeAsUpEnabled(true);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        try {
            final List<Quiz> quizzes = new ArrayList<Quiz>();
            //List<Quiz> cachedEntries = (List<Quiz>) InternalStorage.readObject(this, "Quiz");
            //for (Quiz quiz : cachedEntries) {
            //  quizzes.add(quiz);
            //}
            final List<Quiz> cachedEntry = (List<Quiz>) InternalStorage.readObject(this, "Quizzes");
            Log.d("TEMP", "TEMP");
            //cachedEntry.current = 0;
            //cachedEntry.correctCount = 0;
            //cachedEntry.score = 0;
            String [] values = new String[20];
            for(int i=0; i < cachedEntry.size(); ++i){
                values[i] = cachedEntry.get(i).quizName;
                cachedEntry.get(i).current = 0;
                cachedEntry.get(i).correctCount = 0;
                //cachedEntry.get(i).score = 0;

            }

            //String[] values = {cachedEntry.quizName};
            //Log.d("QUIZZZZZZZ!!@#E!@#", cachedEntry.quizName);
            //ArrayAdapter<String> codeLearnArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
            //listView.setAdapter(codeLearnArrayAdapter);

            List<Map<String, String>> data = new ArrayList<Map<String, String>>();


            for(int i=0; i < cachedEntry.size(); ++i){
                Map<String, String> datum = new HashMap<String, String>(2);


                datum.put("name", cachedEntry.get(i).quizName);
                datum.put("score", "Score: " + String.valueOf(cachedEntry.get(i).score));
                data.add(datum);
            }
            //datum.put("name", cachedEntry.quizName);
            //datum.put("score", "Score: " + String.valueOf(cachedEntry.score));
            //data.add(datum);

            final SimpleAdapter adapter = new SimpleAdapter(this, data,
                    android.R.layout.simple_list_item_2,
                    new String[] {"name", "score"},
                    new int[] {android.R.id.text1,
                            android.R.id.text2});


            listView.setAdapter(adapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                    Quiz q = cachedEntry.get(position);
                    q.score = 0;
                    intent.putExtra("quiz", q);
                    startActivityForResult(intent,0);
                }
            });



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_past_quizzes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_settings:
                return true;
        }



        return super.onOptionsItemSelected(item);
    }
/*
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        this.getMenuInflater().inflate(R.menu.menu_past_quizzes, menu);
    }
*/

}
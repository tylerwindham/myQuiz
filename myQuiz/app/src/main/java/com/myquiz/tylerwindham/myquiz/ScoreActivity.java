package com.myquiz.tylerwindham.myquiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import mehdi.sakout.fancybuttons.FancyButton;

public class ScoreActivity extends ActionBarActivity {
    QuestionActivity qa = new QuestionActivity();
    int index;
    public Quiz quiz(){
        Quiz quiz = qa.getQuizQs();
        return quiz;
    }

    public String correctAnswer(int index){
        String questionAnswer = quiz().getQuestion(index).getAnswer();
        return questionAnswer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        TextView numericScore = (TextView) findViewById(R.id.numericScore);

        double score = getIntent().getDoubleExtra("score", 0);
        double quizQIndex = getIntent().getDoubleExtra("index", 0);

        numericScore.setText(String.valueOf(score));

        TextView questionLabel = (TextView) findViewById(R.id.questionLabel);
        questionLabel.setText("Question " + index + 1);

        BarChart chart = (BarChart) findViewById(R.id.chart);
        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.setDescription("");
        chart.animateXY(2000, 2000);
        chart.setDoubleTapToZoomEnabled(false);
        chart.invalidate();

        chart.getAxisRight().setEnabled(false); // gets rid of right y label
        chart.getXAxis().setPosition(XAxisPosition.BOTTOM); // x label on bottom
        chart.getLegend().setEnabled(false); // gets rid of legend

        FancyButton nextButton = (FancyButton) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quiz().current+1 == quiz().questionList.size()){
                    //Reached last question, return the final score
                    quiz().current++;
                    quiz().correctCount++;

                    try {
                        InternalStorage.writeObject(getApplicationContext(),"Quiz", quiz());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(v.getContext(), HomeActivity.class);
                    intent.putExtra("score", quiz().score);
                    intent.putExtra("index", index++);
                    startActivityForResult(intent,0);
                }else{
                    quiz().current++;
                    quiz().correctCount++;
                    TextView questionLabel = (TextView) findViewById(R.id.questionLabel);
                    questionLabel.setText("Question " + quiz().current + 1);
                }

            }
        });
    }

    private void setCorrectColors(BarDataSet barDataSet1){
        int r = Color.rgb(255, 140, 157);
        int g = Color.rgb(192, 255, 140);
        switch(correctAnswer(index)){
            case "A":
                barDataSet1.setColors(new int[]{g, r, r, r, r});
                break;
            case "B":
                barDataSet1.setColors(new int[]{r, g, r, r, r});
                break;
            case "C":
                barDataSet1.setColors(new int[]{r, r, g, r, r});
                break;
            case "D":
                barDataSet1.setColors(new int[]{r, r, r, g, r});
                break;
            case "E":
                barDataSet1.setColors(new int[]{r, r, r, r, g});
                break;
            default:
                barDataSet1.setColors(ColorTemplate.VORDIPLOM_COLORS);
                break;
        }
    }

    private ArrayList<BarDataSet> getDataSet() {
        //pretend 100 students in class

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i < 5; i++){ // set class random data for now
            valueSet1.add(new BarEntry(rand.nextInt(50), i));
        }

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Question Statistics");
        setCorrectColors(barDataSet1); // set correct bar to green, rest bars red color

        ArrayList<BarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("A");
        xAxis.add("B");
        xAxis.add("C");
        xAxis.add("D");
        xAxis.add("E");

        for(int i = 0; i < xAxis.size(); i++){
            if(xAxis.get(i) == correctAnswer(0))
                xAxis.set(i, xAxis.get(i) +" (correct)");
        }

        return xAxis;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_score, menu);
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

    @Override
    public void onBackPressed() {
        // your code.
        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivityForResult(intent,0);
    }
}

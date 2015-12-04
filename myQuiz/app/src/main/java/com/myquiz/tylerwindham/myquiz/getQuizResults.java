package com.myquiz.tylerwindham.myquiz;

import android.content.Intent;
import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


/**
 * Created by ISAUERZOPF on 12/2/2015.
 */
public class getQuizResults {
    // get statistics from other users taking same quiz

    Vector<resultTable> results;

    getQuizResults(){
        results = new Vector<resultTable>();
    }

        // Create a URL for the desired page
        //String urlString = urlField.getText().toString();
        //Log.d("URL", urlString);
        //URL url = new URL(urlString);

    Vector<resultTable> getResults(String quizName){

        //Vector<String> quizList = new Vector<String>();
        //Vector<Question> questionList = new Vector<Question>();

        // put quiz result structure here

        Quiz quiz;
        String line;
        Vector<resultTable> totTable = new Vector<resultTable>();

        try {
            // specified portal where answers are written. Answers written by users's applications
            URL answerPortal = new URL("http://students.cse.tamu.edu/iks5005/testFile.txt");

            InputStreamReader streamReader = new InputStreamReader(answerPortal.openStream());
            BufferedReader bufferedReader = new BufferedReader(streamReader);


            while ((line = bufferedReader.readLine()) != null) {
                if (line != "" && line.charAt(0) == '*'){
                    String qName = line.substring(1);
                    Log.d("**** quizname", qName);
                    Log.d("**** actual ", quizName);
                    if(qName.equals(quizName)) {
                        Log.d("**** enter", "success");
                        String userName = "";
                        line = bufferedReader.readLine();
                        if (line.charAt(0) == 'U') {
                            userName = line.substring(1);
                            Log.d("**** username read", "success");
                            resultTable restable = new resultTable(qName, userName);
                            while ((line = bufferedReader.readLine()) != null && line.charAt(0) != '*') {
                                String questlab = line.substring(0, 2);
                                String userAns = line.substring(2, 3);
                                String actualAns = line.substring(4, 5);
                                Log.d("questlab", questlab);
                                Log.d("userans", userAns);
                                Log.d("actualans", actualAns);
                                restable.addQuest(questlab, userAns, actualAns);
                            }
                            Log.d("**** reading restable", "success");
                            totTable.add(restable);
                        }
                    }
                }

                //Log.i("buffer reader", line);
            }

            bufferedReader.close();




        } catch (IOException e) {
            e.printStackTrace();
            Log.d("FAIL", "FAILURE TO OPEN QUIZ RESULT FILE");
        }

        //results = totTable;
        return totTable;

    }

    Vector<Vector<Number>> getFinalResults(String quizName){

        //results.clear();

        Vector<resultTable> res = getResults(quizName);

        Vector<Vector<Number>> finRes = new Vector<Vector<Number>>();

        for(int i = 0; i < res.size(); i++){
            Vector<Number> qRes = getQuestionResults(quizName, i);
            finRes.add(qRes);
            qRes.clear();

        }

        return finRes;
    }

    Vector<Number> getQuestionResults(String quizName, int questionNumber){

        //results.clear();

        Vector<resultTable> res = getResults(quizName);

        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;

        if(res.size() == 0){
            Log.d("err", "error reading result");
        }

        for(int i = 0; i < res.size(); i++){
            String ans = res.elementAt(i).answerForQuestion(questionNumber);
            if(ans == "A"){
                a++;
            }
            else if(ans == "B"){
                b++;
            }
            else if(ans == "C"){
                c++;
            }
            else if(ans == "D"){
                d++;
            }
            else if(ans == "E"){
                e++;
            }
        }

        Vector<Number> questionResults = new Vector<Number>();
        questionResults.add(a);
        questionResults.add(b);
        questionResults.add(c);
        questionResults.add(d);
        questionResults.add(e);
        return questionResults;
    }


}

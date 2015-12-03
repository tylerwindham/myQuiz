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


        // Create a URL for the desired page
        //String urlString = urlField.getText().toString();
        //Log.d("URL", urlString);
        //URL url = new URL(urlString);

    void getResults(String quizName){

        //Vector<String> quizList = new Vector<String>();
        //Vector<Question> questionList = new Vector<Question>();

        // put quiz result structure here

        Quiz quiz;
        String line;
        Vector<resultTable> totTable = new Vector<resultTable>();

        try {
            //InputStream in = IOUtils.toInputStream(file, "UTF-8");
            InputStreamReader streamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(streamReader);


            while ((line = bufferedReader.readLine()) != null) {
                if (line.charAt(0) == '*'){
                    String qName = line.substring(1);
                    String userName = "";
                    line = bufferedReader.readLine();
                    if(line.charAt(0) == 'U') {
                        userName = line.substring(1);
                    }
                    resultTable restable = new resultTable(qName,userName);
                    while((line = bufferedReader.readLine()) != null && line.charAt(0) != '*') {
                        String questlab = line.substring(0,2);
                        String userAns = line.substring(2, 2);
                        String actualAns = line.substring(4, 4);
                        restable.addQuest(questlab, userAns, actualAns);
                    }
                    totTable.add(restable);
                }

                //Log.i("buffer reader", line);
            }

            bufferedReader.close();

            String question = new String();
            String answer = new String();
            List<String> answers = new ArrayList<String>();
            String quizName = "";

            for (int i = 0; i < quizList.size(); ++i) {
                char temp = quizList.elementAt(i).charAt(0);
                switch(temp) {
                    case '$':
                        question = quizList.elementAt(i).substring(1);
                        break;
                    case 'A':
                        answers.add(0, quizList.elementAt(i).substring(1));
                        break;
                    case 'B':
                        answers.add(1, quizList.elementAt(i).substring(1));
                        break;
                    case 'C':
                        answers.add(2, quizList.elementAt(i).substring(1));
                        break;
                    case 'D':
                        answers.add(3, quizList.elementAt(i).substring(1));
                        break;
                    case 'E':
                        answers.add(4, quizList.elementAt(i).substring(1));
                        break;
                    case '@':
                        answer = quizList.elementAt(i).substring(1);
                        Question nextQ = new Question(question, answer, answers);
                        questionList.add(nextQ);
                        answers.clear();
                        break;
                    case '*':
                        quizName = quizList.elementAt(i).substring(1);
                        break;
                    default:
                        break;
                }
            }
            Log.i("parse class", questionList.elementAt(0).question);
            Log.i("parse class", questionList.elementAt(1).question);
            Quiz quiz2 = new Quiz(questionList);
            quiz2.quizName = quizName;
            return quiz2;

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("FAIL", "FAILUREEEEEE");
        }
        return quiz;

    }
}

package com.myquiz.tylerwindham.myquiz;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.util.Log;

import org.apache.commons.io.IOUtils;

/**
 * Created by Snyperanihilatr on 11/2/2015.
 */
public class Parser extends Activity {

    Vector<String> quizList = new Vector<String>();
    Vector<Question> questionList = new Vector<Question>();

    Quiz quiz;

    public Quiz getQuiz(String file) {




        String line;

        try {
            InputStream in = IOUtils.toInputStream(file, "UTF-8");
            InputStreamReader streamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            while ((line = bufferedReader.readLine()) != null) {

                quizList.add(line);
                Log.i("buffer reader", line);
            }

            bufferedReader.close();

            String question = new String();
            String answer = new String();
            List<String> answers = new ArrayList<String>();

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
                    default:
                        break;
                }
            }
            Log.i("parse class", questionList.elementAt(0).question);
            Log.i("parse class", questionList.elementAt(1).question);
            Quiz quiz2 = new Quiz(questionList);

            return quiz2;

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("FAIL", "FAILUREEEEEE");
        }
        return quiz;
    }

}

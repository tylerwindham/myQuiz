package com.myquiz.tylerwindham.myquiz;

import android.util.Log;

import java.util.Vector;

/**
 * Created by ISAUERZOPF on 12/2/2015.
 */
public class resultTable {

    String quizName;
    String userId;
    double score;
    Vector<Vector< String >> table;

    resultTable(String qname, String uName){
        quizName = qname;
        userId = uName;
        table = new Vector<Vector<String>>();

    }

    void addQuest(String q, String userAnswer, String actualAnswer){
        Vector<String> a = new Vector<String>();
        if(q.length() == 0 || userAnswer.length() == 0 || actualAnswer.length() == 0){
            Log.d("ERROR", "addquest fields empty");
        }
        a.add(q);
        a.add(userAnswer);
        a.add(actualAnswer);
        table.add(a);
    }

    double grade(){

        int correctAnswers = 0;

        for(int i = 0; i < table.size(); i++ ){
            if(table.elementAt(i).elementAt(1) == table.elementAt(i).elementAt(2)){
                correctAnswers++;
            }
            else{
                // do nothing
            }
        }

        score = correctAnswers/(table.size());

        return score;

    }

    String answerForQuestion(int questNum){
        questNum--;
        if(table.size() < questNum){
            return "NA";
        }

        // return answer given by student
        return table.elementAt(questNum).elementAt(1);
    }


}

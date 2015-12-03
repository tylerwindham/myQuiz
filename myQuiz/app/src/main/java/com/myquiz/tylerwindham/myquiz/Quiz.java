package com.myquiz.tylerwindham.myquiz;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tyler on 10/25/2015.
 */
public class Quiz implements Serializable{

    List<Question> questionList;
    int current = 0;
    double score = 0;
    int correctCount = 0;
    String quizName;

    Quiz(List<Question> questions){
        this.questionList = questions;
    }

    int size(){
        return questionList.size();
    }

    Question getQuestion(int i){
        return questionList.get(i);
    }

    public String toString(){
        String a = "";
        for(int i = 0; i < questionList.size(); i++){
            a += questionList.get(i) + "\n";
        }
        return a;
    }

}
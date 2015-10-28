package com.myquiz.tylerwindham.myquiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler on 10/25/2015.
 */
public class Question implements Serializable {

    String question;
    String answer;
    List<String> answers;
    List<String> choices;

    Question(String q, String a, List<String>answ){
        this.question = q;
        this.answer = a;
        this.answers = answ;
        this.choices = new ArrayList<String>();
        this.choices.add("[A]");
        this.choices.add("[B]");
        this.choices.add("[C]");
        this.choices.add("[D]");
        this.choices.add("[E]");

        for(int i=0; i < answers.size(); ++i){
            this.question += "\n" + choices.get(i) + " " + answers.get(i);
        }

    }

}


package com.myquiz.tylerwindham.myquiz;

import java.util.List;

/**
 * Created by Tyler on 10/25/2015.
 */
public class Quiz {

    List<Question> questionList;
    int current = 0;

    Quiz(List<Question> questions){
        this.questionList = questions;
    }
}

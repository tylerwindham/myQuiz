package com.myquiz.tylerwindham.myquiz;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.Context;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import android.util.Log;

/**
 * Created by Snyperanihilatr on 11/2/2015.
 */
public class Parser extends Activity {

    Vector<String> quizList;
    public void getFile(Context context) {
        AssetManager assetManager = getAssets();
        InputStream inputFile;

        try {
            inputFile = assetManager.open("test.txt");
                if (inputFile != null) {
                    Log.i("parse class", "IT WORKED");
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void parseRead(Context context) {
        InputStream is;

        try {
            is = new BufferedInputStream(context.getResources().getAssets().open("test.txt"));
        } catch(IOException e) {
            e.printStackTrace();
        }



    }
}

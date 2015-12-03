package com.myquiz.tylerwindham.myquiz;

/**
 * Created by ISAUERZOPF on 11/23/2015.
 */
//ANDROID HTTP POST EXAMPLE

import android.app.Activity;

//package com.example.httprequestexample;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class HTTPPostActivity extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Log.d("here ", "here");

        //makePostRequest();

    }
    
    // for quiz app
    public void makeQuizPost(String quiz_name, String user_name, Vector<Vector<String>> user_q_answers) {
        // either individual question or all questions
        // test
        Log.d("here ", "and here ");
        HttpClient httpClient = new DefaultHttpClient();
        // replace with your url
        HttpPost httpPost = new HttpPost("http://students.cse.tamu.edu/iks5005/formsubmit.php");


        //Post Data
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);

        // create format for posting on page
        String qname = "*"; // + quizName
        String uname = "U"; // + userName
        Vector<String> questions = new Vector<String>();

        for(int i = 0; i < user_q_answers.size(); i++){
            questions.add("Q" + i + user_q_answers.elementAt(i).elementAt(0) + "?" + user_q_answers.elementAt(i).elementAt(1));
        }

        String output = qname + '\n' + uname + '\n';

        for(int i = 0; i < questions.size(); i++){
            output += questions.elementAt(i) + '\n';
        }

         

        nameValuePair.add(new BasicNameValuePair("datastring", output));
        //nameValuePair.add(new BasicNameValuePair("submitbox", " HERE THERE EV"));


        //Encoding POST data
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // log exception
            Log.d("cat.1", "cat.1");
            e.printStackTrace();
        }

        //making POST request.
        try {
            HttpResponse response = httpClient.execute(httpPost);
            // write response to log
            Log.d("Http Post Response:", response.toString());
        } catch (ClientProtocolException e) {
            // Log exception
            Log.d("cat.2", "cat.2 ");
            e.printStackTrace();
        } catch (IOException e) {
            // Log exception
            Log.d("cat.3 ", " cat.3 ");
            e.printStackTrace();
        }

    }
    
    // test function for standalone use
    /*public void makePostRequest() {

        Log.d("here ", "and here ");
        HttpClient httpClient = new DefaultHttpClient();
        // replace with your url
        HttpPost httpPost = new HttpPost("http://students.cse.tamu.edu/iks5005/formsubmit.php");


        //Post Data
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
        nameValuePair.add(new BasicNameValuePair("datastring", "\n----- \n %%user_3 \n q1:a \n q2:b \n q3:c \n -------\n"));
        //nameValuePair.add(new BasicNameValuePair("submitbox", " HERE THERE EV"));


        //Encoding POST data
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // log exception
            Log.d("cat.1", "cat.1");
            e.printStackTrace();
        }

        //making POST request.
        try {
            HttpResponse response = httpClient.execute(httpPost);
            // write response to log
            Log.d("Http Post Response:", response.toString());
        } catch (ClientProtocolException e) {
            // Log exception
            Log.d("cat.2", "cat.2 ");
            e.printStackTrace();
        } catch (IOException e) {
            // Log exception
            Log.d("cat.3 ", " cat.3 ");
            e.printStackTrace();
        }

    } */




    

}

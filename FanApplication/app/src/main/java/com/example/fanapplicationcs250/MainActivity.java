package com.example.fanapplicationcs250;

import com.example.fanapplicationcs250.R;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private Survey currentSurvey;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gson = new Gson();
        new getQuestionOfTheDayTask().execute();
    }

    //task to get question of the day
    private class getQuestionOfTheDayTask extends AsyncTask<String, Void, String> {
        private String uri;

        getQuestionOfTheDayTask() {
            uri="http://"+URIHandler.hostName+"/survey";
        }

        @Override
        protected String doInBackground(String... urls) {
            return URIHandler.doGet(uri,"0");
        }

        @Override
        protected void onPostExecute(String json) {
            loadSurvey(json);
        }
    }


    private void loadSurvey(String json) {
        currentSurvey = gson.fromJson(json,Survey[].class)[0];
        String[] answers = currentSurvey.getAnswers().split(",");
        int l = answers.length;
        TextView QuestionText = (TextView) this.findViewById(R.id.QuestionText);
        QuestionText.setText(currentSurvey.getQuestion());
        if (l > 1) {
            b1 = findViewById(R.id.button1);
            b1.setText(answers[0]);
            b2 = findViewById(R.id.button2);
            b2.setText(answers[1]);
            b3 = findViewById(R.id.button3);
            b3.setText(answers[2]);
            b4 = findViewById(R.id.button4);
            b4.setText(answers[3]);
        }

    }

    public void sendResponseButton1(View view) {
        Response response = new Response();
        response.setSurvey_id(currentSurvey.getSurveyId());
        response.setAnswer(b1.getText().toString());
        new PostResponseTask(response).execute();
    }

    public void sendResponseButton2(View view) {
        Response response = new Response();
        response.setSurvey_id(currentSurvey.getSurveyId());
        response.setAnswer(b2.getText().toString());
        new PostResponseTask(response).execute();
    }

    public void sendResponseButton3(View view) {
        Response response = new Response();
        response.setSurvey_id(currentSurvey.getSurveyId());
        response.setAnswer(b3.getText().toString());
        new PostResponseTask(response).execute();
    }

    public void sendResponseButton4(View view) {
        Response response = new Response();
        response.setSurvey_id(currentSurvey.getSurveyId());
        response.setAnswer(b4.getText().toString());
        new PostResponseTask(response).execute();
    }

    private class PostResponseTask extends AsyncTask<String, Void, String> {
        private String uri;
        private Response response;
        PostResponseTask(Response response) {
            uri="http://"+URIHandler.hostName+"/response";
            this.response = response;
        }

        @Override
        protected String doInBackground(String... urls) {
            return URIHandler.doPost(uri,gson.toJson(response));
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
            startActivity(intent);
        }
    }


}

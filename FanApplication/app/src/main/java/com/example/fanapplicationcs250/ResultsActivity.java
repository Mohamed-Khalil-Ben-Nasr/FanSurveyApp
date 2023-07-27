package com.example.fanapplicationcs250;

import com.example.fanapplicationcs250.R;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

public class ResultsActivity extends AppCompatActivity {
    private Vote[] votes;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        gson = new Gson();
        new getResultsOfTheDayTask().execute();
    }

    //task to get results of the day
    private class getResultsOfTheDayTask extends AsyncTask<String, Void, String> {
        private String uri;

        getResultsOfTheDayTask() {
            uri="http://"+URIHandler.hostName+"/votes";
        }

        @Override
        protected String doInBackground(String... urls) {
            return URIHandler.doGet(uri,"0");
        }

        @Override
        protected void onPostExecute(String json) {
            loadResults(json);
        }
    }

    private void loadResults(String json) {
        votes = gson.fromJson(json, Vote[].class);
        String[] votesStrs = new String[votes.length];
        for(int n = 0; n < votesStrs.length; n++) {
            Vote vote = votes[n];
            votesStrs[n] = vote.getAnswer() + ":" + vote.getNumberOfVotes();
        }

        ListView votesList = (ListView) findViewById(R.id.votes_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, votesStrs);
        votesList.setAdapter(adapter);
    }
}

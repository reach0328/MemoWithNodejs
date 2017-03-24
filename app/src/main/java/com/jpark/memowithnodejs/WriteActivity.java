package com.jpark.memowithnodejs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jpark.memowithnodejs.domain.QnA;

public class WriteActivity extends AppCompatActivity {

    EditText editTitle, editName,editContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editContent = (EditText) findViewById(R.id.edit_content);
        editName = (EditText) findViewById(R.id.edit_name);
        editTitle = (EditText) findViewById(R.id.edit_title);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            AsyncTask<String,Void,String> network = new AsyncTask<String,Void,String>() {
               @Override
                protected String doInBackground(String... params) {

                    QnA qna = new QnA();
                    qna.setTitle(params[0]);
                    qna.setName(params[1]);
                    qna.setContent(params[2]);

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(qna);
                    String result = Remote.postData(MainActivity.SITE_URL+"bbs",jsonString);

                    return result;
                }
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    Snackbar.make(view, s, Snackbar.LENGTH_LONG).show();
                }
            };
            network.execute(editTitle.getText().toString()
                    , editName.getText().toString()
                    , editContent.getText().toString());
        });
    }

}

package com.example.esra.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ListIterator;

public class ShowingClassroomsActivity extends AppCompatActivity {

    private ArrayList<String> finalclassrooms;
    ListView classlistview;
    private ArrayAdapter<String> adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_classrooms);
        classlistview = (ListView) findViewById(R.id.listView2);
        new GetClassrooms().execute();

    }

    private class GetClassrooms extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progressDialog = new ProgressDialog(ShowingClassroomsActivity.this);
        private JSONObject object;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Please Wait");
            progressDialog.setMessage("Getting Classrooms...");
            progressDialog.setIndeterminate(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            Bundle bunble = getIntent().getExtras();
            String url2 = bunble.getString("url");

            try {
                object = new JSONObject(Helpers.makeHTTPRequest(url2));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();

            try {
                JSONArray array = object.getJSONArray("floors");
                finalclassrooms = new ArrayList<>();

                ArrayList[][] classByFloor = new ArrayList[6][20];

                for (int i = 0; i < array.length(); i++) {
                    int j = 0;
                    JSONArray temp = array.getJSONObject(i).getJSONArray("classrooms");

                    while (true) {

                        classByFloor[i][j] = new ArrayList();
                        classByFloor[i][j].add(temp.getJSONObject(j).getString("code"));
                        if(i==0){
                        finalclassrooms.add(temp.getJSONObject(j).getString("code") + " -Ground Floor");
                        }
                        else {finalclassrooms.add(temp.getJSONObject(j).getString("code") + " -" + i + ". Floor");
                            }
                        j++;
                        if (temp.length() == j) break;

                    }
                }


                adapter = new ArrayAdapter<String>(ShowingClassroomsActivity.this,
                        android.R.layout.simple_list_item_1, finalclassrooms);

                classlistview.setAdapter(adapter);


            } catch (JSONException e)
            { e.printStackTrace(); }
        }
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
package com.example.esra.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.util.ListIterator;

public class ShowingClassroomsActivity extends AppCompatActivity {
    private ArrayList<Classrooms2> classrooms2= new ArrayList<>();
    private ArrayList<Object> ClassroomsCodes=new ArrayList<>();
    private ArrayList<String> orcun;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_classrooms);
        t1=(TextView) findViewById(R.id.textView);
        new GetDepartments().execute();

            }

    private class GetDepartments extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progressDialog = new ProgressDialog(ShowingClassroomsActivity.this);

        private JSONObject object;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Getting Departments...");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

        @Override
        protected Void doInBackground(Void... params) {
            Bundle bunble=getIntent().getExtras();
            String url2=bunble.getString("url");

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
                for (int i = 0; i < array.length(); i++) {
                    t1.setText(i+". floor");
                    JSONArray temp = array.getJSONObject(i).getJSONArray("classrooms");

                    for (int j = 0; j < temp.length(); j++) {
                        classrooms2.add(new Classrooms2(temp.getJSONObject(j).getString("code"), temp.getJSONObject(j).getInt("floor_id")));
                        ClassroomsCodes.add(classrooms2.get(j).getClasscode() + "   " + classrooms2.get(j).getFloorsid());

                    }
                }

                orcun = new ArrayList<>();
                for(int i =0;i<classrooms2.size();i++){
                    orcun.add(classrooms2.get(i).getClasscode()) ;
                    orcun.add(classrooms2.get(i).getFloorsid()+"");
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

}

    @Override
    public void onBackPressed() {
        finish();
    }
}
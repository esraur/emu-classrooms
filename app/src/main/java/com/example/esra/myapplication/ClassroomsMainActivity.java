package com.example.esra.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

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

public class ClassroomsMainActivity extends AppCompatActivity {
    ListView classroomslistview;
    private ArrayList<Classrooms> departments;
    private ArrayList<String> departmentCodes;
    private ArrayAdapter<String> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classrooms_main);
        classroomslistview = (ListView) findViewById(R.id.listView);
        departments = new ArrayList<>();
        departmentCodes = new ArrayList<>();

        new GetDepartments("http://178.62.227.214/departments.json", 0).execute();
        classroomslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //taking department's information from listview items according to the their id's
                switch (position) {
                    case 0:
                        new GetDepartments("http://178.62.227.214/departments/1.json", 1).execute();
                        break;
                    case 1:
                        new GetDepartments("http://178.62.227.214/departments/2.json", 1).execute();
                        break;
                    case 2:
                        new GetDepartments("http://178.62.227.214/departments/3.json", 1).execute();
                        break;
                    case 3:
                        new GetDepartments("http://178.62.227.214/departments/5.json", 1).execute();
                        break;
                    case 4:
                        new GetDepartments("http://178.62.227.214/departments/14.json", 1).execute();
                        break;
                    case 5:
                        new GetDepartments("http://178.62.227.214/departments/8.json", 1).execute();
                        break;
                    case 6:
                        new GetDepartments("http://178.62.227.214/departments/12.json", 1).execute();
                        break;
                    case 7:
                        new GetDepartments("http://178.62.227.214/departments/15.json", 1).execute();
                        break;
                    case 8:
                        new GetDepartments("http://178.62.227.214/departments/16.json", 1).execute();
                        break;
                    case 9:
                        new GetDepartments("http://178.62.227.214/departments/6.json", 1).execute();
                        break;
                    case 10:
                        new GetDepartments("http://178.62.227.214/departments/17.json", 1).execute();
                        break;
                    case 11:
                        new GetDepartments("http://178.62.227.214/departments/17.json", 1).execute();
                        break;
                    case 12:
                        new GetDepartments("http://178.62.227.214/departments/18.json", 1).execute();
                        break;
                    case 13:
                        new GetDepartments("http://178.62.227.214/departments/4.json", 1).execute();
                        break;
                    case 14:
                        new GetDepartments("http://178.62.227.214/departments/13.json", 1).execute();
                        break;
                    case 15:
                        new GetDepartments("http://178.62.227.214/departments/19.json", 1).execute();
                        break;
                    case 16:
                        new GetDepartments("http://178.62.227.214/departments/20.json", 1).execute();
                        break;
                    case 17:
                        new GetDepartments("http://178.62.227.214/departments/21.json", 1).execute();
                        break;
                    case 18:
                        new GetDepartments("http://178.62.227.214/departments/22.json", 1).execute();
                        break;
                    case 19:
                        new GetDepartments("http://178.62.227.214/departments/23.json", 1).execute();
                        break;
                    case 20:
                        new GetDepartments("http://178.62.227.214/departments/10.json", 1).execute();
                        break;
                    case 21:
                        new GetDepartments("http://178.62.227.214/departments/11.json", 1).execute();
                        break;

                }
            }
        });
    }

    private class GetDepartments extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progressDialog = new ProgressDialog(ClassroomsMainActivity.this);
        private String request;
        private int option;
        private JSONObject object;

        public GetDepartments(String request, int option) {
            this.request = request;
            this.option = option;
        }

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

            switch (option) {
                case 0:
                    try {
                        JSONArray array = new JSONArray(Helpers.makeHTTPRequest(request));
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject temp = array.getJSONObject(i);

                            departments.add(new Classrooms(temp.getString("code"), temp.getInt("id")));
                            departmentCodes.add(departments.get(i).getCode());
                        }
                        for (int i = 0; i < departments.size(); i++) {
                            Log.i("Departments : ", departments.get(i).toString());
                        }
                        return null;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                   try {

                    } catch ( Exception e) {

                    }
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            switch (option) {
                case 0:
                    adapter = new ArrayAdapter<>(ClassroomsMainActivity.this, android.R.layout.simple_list_item_1, departmentCodes);
                    classroomslistview.setAdapter(adapter);
                    break;
                case 1:
                    String url=request;
                    Intent intent=new Intent(ClassroomsMainActivity.this,ShowingClassroomsActivity.class);
                    intent.putExtra("url",url);
                    startActivity(intent);
            }


    }
    }
}

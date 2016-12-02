package com.example.shogun.znk.requests;

import android.os.AsyncTask;

import com.example.shogun.znk.models.Consultation;
import com.example.shogun.znk.models.Teacher;
import com.example.shogun.znk.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class GetTeachers extends AsyncTask<String, Void, String> {

    private static final String URL = com.example.shogun.znk.URL.getInstance().getURL();

    @Override
    protected String doInBackground(String[] params) {
        // do above Server call here
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url(URL + "/api/teachers")
                .addHeader("Authorization", "Bearer " + User.getInstance().getToken())
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "some message";
    }

    @Override
    protected void onPostExecute(String message) {
    }

    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        JSONArray teacherJSON = null;
        try {
            String result = execute().get();
            System.out.println("To jest result: " + result);
            teacherJSON = new JSONArray(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < teacherJSON.length(); i++) {

            try {
                JSONObject jsonObject = teacherJSON.getJSONObject(i);
                int teacherId = jsonObject.getInt("id");
                String login = jsonObject.getString("login");
                String firstName = jsonObject.getString("firstName");
                String lastName = jsonObject.getString("lastName");


                teachers.add(new Teacher(login, teacherId));
            } catch (JSONException e) {
                e.printStackTrace();

            }

        }return teachers;
    }}

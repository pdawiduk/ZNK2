package com.example.shogun.znk.requests;

import android.os.AsyncTask;

import com.example.shogun.znk.models.Consultation;
import com.example.shogun.znk.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Krystian on 2016-12-07.
 */

public class GetStudentSignedConsultations extends AsyncTask<String, Void, String> {

    private static final String URL = com.example.shogun.znk.URL.getInstance().getURL();
    private Request request;


    @Override
    protected String doInBackground(String[] params) {
        // do above Server call here
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();


        if (params.length == 1) {
            request = new Request.Builder()
                    .url(URL + "/api/consultations/student/" + params[0])
                    .addHeader("Authorization", "Bearer " + User.getInstance().getToken())
                    .build();
        } else if (params.length == 0) {
            request = new Request.Builder()
                    .url(URL + "/api/consultations")
                    .addHeader("Authorization", "Bearer " + User.getInstance().getToken())
                    .build();
        }
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

    public List<Consultation> getStudentSignedConsultations(long id) {
        List<Consultation> consultations = new ArrayList<>();
        JSONArray consultationJSON = null;
        try {
            String result = execute(String.valueOf(id)).get();
            System.out.println("To jest result: " + result);
            consultationJSON = new JSONArray(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat output = new SimpleDateFormat("HH:mm dd-MM-yyyy ");


        for (int i = 0; i < consultationJSON.length(); i++) {

            try {
                JSONObject jsonObject = consultationJSON.getJSONObject(i);
                int consultationId = jsonObject.getInt("id");
                String dateTime = jsonObject.getString("dateTime");
                Date d = sdf.parse(dateTime);
                String formattedTime = output.format(d);
                String teacherLogin = jsonObject.getString("teacherLogin");
                Boolean cancelled = jsonObject.getBoolean("cancelled");
                String address = jsonObject.getString("address");
                JSONArray jsonArray = jsonObject.getJSONArray("registeredStudents");
                List<String> registeredStudents = new ArrayList<>();
                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONObject student = jsonArray.getJSONObject(j);
                    registeredStudents.add(student.getString("login"));
                }

                consultations.add(new Consultation(consultationId, formattedTime, cancelled, registeredStudents,teacherLogin,address));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return consultations;
    }

    {
    }
}

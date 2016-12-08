package com.example.shogun.znk.requests;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.shogun.znk.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Krystian on 2016-11-14.
 */
public class PostConsultation extends AsyncTask<String, Void, String> {

    private static final String URL = com.example.shogun.znk.URL.getInstance().getURL();
    private Request request;

    @Override
    protected String doInBackground(String[] params) {
        // do above Server call here
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        String json = null;
        try {
            if(params.length==1){
                json = new JSONObject().put("id",params[0]).toString();
                RequestBody body = RequestBody.create(JSON, json);
                request = new Request.Builder()
                        .url(URL + "/api/consultations/" + params[0] + "/cancel")
                        .post(body)
                        .addHeader("Authorization", "Bearer " + User.getInstance().getToken())
                        .build();
            } else {
                json = new JSONObject().put("dateTime", params[0]).put("teacherId", User.getInstance().getId()).put("cancelled", params[1]).put("address",params[2]).toString();
                RequestBody body = RequestBody.create(JSON, json);
                request = new Request.Builder()
                        .url(URL + "/api/consultations")
                        .post(body)
                        .addHeader("Authorization", "Bearer " + User.getInstance().getToken())
                        .build();
            }
            System.out.println(json);
        } catch (JSONException e) {
            e.printStackTrace();
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

    public void addConsultation(String date, String address) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        final SimpleDateFormat output = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        try {
            Date d = output.parse(date);
            String dateToPut = sdf.format(d);
            System.out.println(execute(dateToPut+".000Z", String.valueOf(false),address).get());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void cancelConsultation(int id){
        try {
            System.out.println(execute(String.valueOf(id)).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}



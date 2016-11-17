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
public class PutConsultation extends AsyncTask<String, Void, String> {

    private static final String URL = "http://10.7.2.10:8080";
    private Request request;

    @Override
    protected String doInBackground(String[] params) {
        // do above Server call here
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        String json = null;
        try {
            json = new JSONObject().put("id",params[0]).put("cancelled",params[1]).put("dateTime",params[2]).toString();
            System.out.println(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(JSON, json);
            request = new Request.Builder()
                    .url(URL + "/api/consultations")
                    .put(body)
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

    public void editConsultation(int id, Boolean cancelled, String date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        final SimpleDateFormat output = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy ");
        try {
            Date d = output.parse(date);
            String dateToPut = sdf.format(d);
            System.out.println(execute(String.valueOf(id),String.valueOf(cancelled),dateToPut+".000Z").get());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}


package com.example.shogun.znk.requests;

import android.os.AsyncTask;

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

public class CancelConsultation extends AsyncTask<String, Void, String> {

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
                json = new JSONObject().put("id",params[0]).toString();
                RequestBody body = RequestBody.create(JSON, json);
                request = new Request.Builder()
                        .url(URL + "/api/consultations/" + params[0] + "/cancel")
                        .post(body)
                        .addHeader("Authorization", "Bearer " + User.getInstance().getToken())
                        .build();
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

    public void cancelConsultation(long id) {
        try {
            System.out.println(execute(String.valueOf(id)).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
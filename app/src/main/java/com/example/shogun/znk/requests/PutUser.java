package com.example.shogun.znk.requests;

import android.icu.lang.UScript;
import android.os.AsyncTask;

import com.example.shogun.znk.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Krystian on 2016-12-06.
 */

public class PutUser extends AsyncTask<String, Void, String> {

    private static final String URL = com.example.shogun.znk.URL.getInstance().getURL();
    private Request request;

    @Override
    protected String doInBackground(String[] params) {
        // do above Server call here
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        String json = null;
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(User.getInstance().getAuthorities().toArray());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            json = new JSONObject().put("id",User.getInstance().getId()).put("firstName",params[0]).put("lastName",params[1])
                    .put("email",User.getInstance().getEmail()).put("langKey","en").put("login",User.getInstance().getLogin())
                    .put("activated",User.getInstance().getActivated()).put("authorities", jsonArray).put("address",params[2]).toString();
            System.out.println(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(JSON, json);
        request = new Request.Builder()
                .url(URL + "/api/users")
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

    public void updateProfile(String firstName, String lastName, String address) {
        try {
            System.out.println(execute(firstName,lastName, address).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

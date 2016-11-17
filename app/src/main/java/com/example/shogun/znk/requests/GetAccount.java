package com.example.shogun.znk.requests;

import android.os.AsyncTask;

import com.example.shogun.znk.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Krystian on 2016-11-12.
 */
public class GetAccount extends AsyncTask<String, Void, String> {

    private static final String URL = "http://10.1.0.102:8080";

    @Override
    protected String doInBackground(String[] params) {
        // do above Server call here
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        String json = null;
        try {
            json = new JSONObject().put("id_token",params[0]).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(URL + "/api/account")
                .addHeader("Authorization","Bearer " + params[0])
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

    public User getUser(String token) {
        User user = null;
        try {
            String account = execute(String.valueOf(token)).get();
            JSONObject userJSON = new JSONObject(account);
            System.out.println("To jest account: " + account);

            user = User.getInstance();
            user.setLogin(userJSON.getString("login"));
            user.setFirstName(userJSON.getString("firstName"));
            user.setLastName(userJSON.getString("lastName"));
            user.setEmail(userJSON.getString("email"));
            user.setActivated(userJSON.getBoolean("activated"));
            user.setLangKey(userJSON.getString("langKey"));
            user.setToken(token);
            JSONArray jsonArray = userJSON.getJSONArray("authorities");
            Set<String> authoritySet = new HashSet<>();
            for (int i = 0; i < jsonArray.length();i++) {
                authoritySet.add((jsonArray.getString(i)));
            }
            user.setAuthorities(authoritySet);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }
}

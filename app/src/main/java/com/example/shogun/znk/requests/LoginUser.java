package com.example.shogun.znk.requests;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Krystian on 2016-11-12.
 */

public class LoginUser extends AsyncTask<String, Void, String> {

    private static final String URL = com.example.shogun.znk.URL.getInstance().getURL();
    private final Context context;

    public LoginUser(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String[] params) {
        // do above Server call here
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();


        String json = null;
        try {
            json = new JSONObject().put("username",params[0]).put("password",params[1]).put("rememberMe",params[2]).toString();
            System.out.println(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(URL + "/api/authenticate")
                .post(body)
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

    public String signUpUser(String... params){
        String tokenValue = null;
        String token;

        try {
            token = execute(params[0],params[1],String.valueOf(params[2])).get();
            JSONObject tokenJSON = new JSONObject(token);
            tokenValue = tokenJSON.getString("id_token");
        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(context,"Cannot log in", Toast.LENGTH_LONG).show();
        } catch (ExecutionException e) {
            Toast.makeText(context,"Cannot log in", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context,"Wrong login/password or account is not activated", Toast.LENGTH_LONG).show();
        }

        return tokenValue;
    }
}

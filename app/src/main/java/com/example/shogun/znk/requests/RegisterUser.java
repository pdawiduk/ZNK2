package com.example.shogun.znk.requests;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.shogun.znk.MainActivity;
import com.example.shogun.znk.fragments.LoginFragment;

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
 * Created by Krystian on 2016-11-14.
 */

public class RegisterUser extends AsyncTask<String, Void, String> {

    private static final String URL = com.example.shogun.znk.URL.getInstance().getURL();
    private final Context context;

    public RegisterUser(Context context) {
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
            json = new JSONObject().put("login",params[0]).put("password",params[1]).put("email",params[2]).put("langKey","en").toString();
            System.out.println(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(URL + "/api/register")
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

    public void registerUser(String... params) {
        String response = "";
        try {
            response = execute(params[0], params[1], params[2]).get();
            System.out.println(response);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
        } catch (ExecutionException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
        }

        if (response.isEmpty()) {
            MainActivity.switchContent(LoginFragment.newInstance());
            Toast.makeText(context, "Account created. Please log in", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Wronga data. Try again", Toast.LENGTH_LONG).show();
        }
    }
}
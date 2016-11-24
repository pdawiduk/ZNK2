package com.example.shogun.znk;

import android.app.Application;

import com.example.shogun.znk.models.User;

/**
 * Created by Krystian on 2016-11-18.
 */

public class URL  {
    private static URL mInstance = null;

    String URL ="https://zapisy-na-konsultacje.herokuapp.com";

    private URL() {
    }

    public static synchronized URL getInstance() {
        if (mInstance == null) {

            if (mInstance == null) {
                mInstance = new URL();
            }

        }
        return mInstance;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}

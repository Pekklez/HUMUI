package com.uaq.HUMUI.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.uaq.HUMUI.Activities.retos.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by adolfo on 10/13/16.
 */



public class SplashActivity extends AppCompatActivity {

    String video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new getVideo(this).execute();
    }
}

//------------------GET VIDEO
class getVideo extends AsyncTask<String, Void, JSONObject> {


    Context context;
    String video;

    public getVideo(Context context){
        this.context = context;
    }

    protected JSONObject doInBackground(String... urls) {

        String fullString = "";
        URL url_url = null;
        JSONObject jsonObject = null;
        JSONParser jsonParser;
        try {
            String url = "http://ingenieria.uaq.mx/humui/api/token/humui2016token/video/current";
            url_url = new URL(url);

            BufferedReader reader = new BufferedReader(new InputStreamReader(url_url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                fullString += line;

            }
            reader.close();

            jsonParser = new JSONParser();
            jsonObject = new JSONObject("{\"User\":["+fullString+"]}");

        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    protected void onPostExecute(JSONObject jsonObject) {

        // Getting JSON Array
        JSONArray User=null;

        try {
            User = jsonObject.getJSONArray("User");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < User.length(); i++) {
            JSONObject json = null;
            try {
                json = User.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {

                this.video = new String(json.getString("videoId"));

                String TAG3 = "VIDEO ID--------->";
                Log.v(TAG3,  this.video);

                Intent intent = new Intent(context, videoActivity.class);
                intent.putExtra("EXTRA_VIDEO", video);
                String TAG = "VIDEO--->";
                Log.v(TAG, "URL: " + video );
                context.startActivity(intent);
                ((Activity)context).finish();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
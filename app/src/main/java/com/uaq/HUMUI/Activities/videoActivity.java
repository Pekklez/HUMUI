package com.uaq.HUMUI.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
import com.uaq.HUMUI.Activities.retos.JSONParser;
import com.uaq.HUMUI.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class videoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;

    String video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(Config.YOUTUBE_API_KEY, this);

        new getVideo(video).execute();

        Button buttonGoMain = (Button)findViewById(R.id.buttonGoMain);
        buttonGoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMenuActivity = new Intent(videoActivity.this, MainActivity.class);
                startActivity(intentMenuActivity);
            }
        });

    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {

            player.cueVideo("-sLUS21h_TM");
        }
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
        }
    }

    protected Provider getYouTubePlayerProvider() {
        return youTubeView;
    }

}

//------------------GET MPADIS
class getVideo extends AsyncTask<String, Void, JSONObject> {


    String video;

    public getVideo(String video){
        this.video = video;
    }

    public String getUrlVideo(){
        return  this.video;
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



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
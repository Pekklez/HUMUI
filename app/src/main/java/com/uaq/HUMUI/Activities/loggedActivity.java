package com.uaq.HUMUI.Activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.uaq.HUMUI.Activities.retos.ActividadPrincipal;
import com.uaq.HUMUI.Activities.retos.JSONParser;
import com.uaq.HUMUI.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import static com.uaq.HUMUI.R.id.buttonUserName;
import static com.uaq.HUMUI.R.id.expediente;

public class loggedActivity extends AppCompatActivity {

    public Button buttonUserName = (Button)findViewById(com.uaq.HUMUI.R.id.buttonUserName);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.uaq.HUMUI.R.layout.activity_logged);

        //---GETMPADIS
        String idUser = getIntent().getExtras().getString("idUser");
        new getExpediente(idUser).execute();


        Intent intent = getIntent();
        final Bundle extras = intent.getExtras();
        ImageView imageViewFacebook_logged = (ImageView)findViewById(com.uaq.HUMUI.R.id.ImageViewFacebook_logged);
        if(extras != null){



            if(extras.containsKey("idTwitter") && extras.containsKey("nameTwitter")) {
                //imagen de usuario
                String nameTwitter = getIntent().getExtras().getString("nameTwitter");
                new getUserPicTw(imageViewFacebook_logged).execute("https://twitter.com/"+nameTwitter+"/profile_image?size=bigger");

                // Nombre de Usuario
                buttonUserName.setText("  @"+nameTwitter.toString()+"  ");
            }
            if(extras.containsKey("idFacebook") && extras.containsKey("nameFacebook")){
                //Imagen de usuario
                String idFacebook = getIntent().getExtras().getString("idFacebook");
                new getUserPicFB(imageViewFacebook_logged).execute("https://graph.facebook.com/"+idFacebook+"/picture?type=large");
                // Nombre de Usuario
                String nameFacebook = getIntent().getExtras().getString("nameFacebook");
                buttonUserName.setText("  "+nameFacebook.toString()+"  ");

            }

        }

        ImageView imageViewRetos = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageViewRetos);
        imageViewRetos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentMenuActivity = new Intent(loggedActivity.this, ActividadPrincipal.class);
                    intentMenuActivity.putExtra("idUser", getIntent().getExtras().getString("idUser"));
                startActivity(intentMenuActivity);
            }
        });
        ImageView imageViewMpadis = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageViewMPADIS);
        imageViewMpadis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMenuActivity = new Intent(loggedActivity.this, RecompensasActivity.class);
                intentMenuActivity.putExtra("idUser", getIntent().getExtras().getString("idUser"));
                startActivity(intentMenuActivity);
            }
        });

        /*ImageView imageViewLogros = (ImageView)findViewById(R.id.imageViewLogros);
        imageViewLogros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMenuActivity = new Intent(loggedActivity.this, LogrosActivity.class);
                startActivity(intentMenuActivity);
            }
        });*/
    }
}
class getUserPicFB extends AsyncTask<String, Void, Bitmap>{
    ImageView imageView;

    public getUserPicFB(ImageView imageView){
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {

        int width = result.getWidth();
        int height = result.getHeight();
        int newW = 200;
        int newH = 200;
        float scaleW = ((float)newW)/width;
        float scaleH = ((float)newH)/height;
        Matrix x = new Matrix();
        x.postScale(scaleW,scaleH);
        Bitmap resized = Bitmap.createBitmap(result,0,0,width,height,x,true);
        RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(Resources.getSystem() , resized);
        roundedDrawable.setCornerRadius(resized.getHeight());
        imageView.setImageDrawable(roundedDrawable);
    }



}
class getUserPicTw extends AsyncTask<String, Void, Bitmap>{
    ImageView imageView;

    public getUserPicTw(ImageView imageView){
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {

        int width = result.getWidth();
        int height = result.getHeight();
        int newW = 200;
        int newH = 200;
        float scaleW = ((float)newW)/width;
        float scaleH = ((float)newH)/height;
        Matrix x = new Matrix();
        x.postScale(scaleW,scaleH);
        Bitmap resized = Bitmap.createBitmap(result,0,0,width,height,x,true);

        RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(Resources.getSystem() , resized);
        roundedDrawable.setCornerRadius(resized.getHeight());
        imageView.setImageDrawable(roundedDrawable);
    }
}
//------------------GET MPADIS
class getExpediente extends AsyncTask<String, Void, JSONObject>{

    String IdUser;
    public getExpediente(String idUser){
        this.IdUser = idUser;
    }

    protected JSONObject doInBackground(String... urls) {

        String fullString = "";
        URL url_url = null;
        JSONObject jsonObject = null;
        JSONParser jsonParser;
        try {
            String url = "http://ingenieria.uaq.mx/humui/api/token/humui2016token/user/get/"+this.IdUser;
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
        String expediente="";
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

                expediente = new Integer(json.getInt("expediente")).toString();

                String TAG3 = "EXPEDIENTE--------->";
                Log.v(TAG3, expediente );

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
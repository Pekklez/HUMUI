package com.uaq.HUMUI.Activities.retos.RetosCumplidos;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;
import com.uaq.HUMUI.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

import io.fabric.sdk.android.Fabric;


public class DetailRetoInscritoActivity extends AppCompatActivity {


    private static final String EXTRA_NAME = "com.uaq.toolbarapp.name";
    private static final String EXTRA_DRAWABLE = "com.uaq.toolbarapp.drawable";
    private CallbackManager callbackManager;
    ShareDialog shareDialog;
    int REQUEST_CAMERA = 0;

    public String HASHTAG;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_inscrito);

        ImageButton imageShare = (ImageButton) findViewById(R.id.shareFB);

        shareDialog = new ShareDialog(this);  // intialize facebook shareDialog.

        imageShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


        setToolbar();// Añadir action bar
        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Context mContext = this;


        TwitterAuthConfig authConfig =  new TwitterAuthConfig("GGphQaBOU0qFliTaAmGjTMvGI", "QDX3fS71CzBVEtG2DpS2AraFuHPgOGZBJ6R3q9948LnGMGUib0");
        Fabric.with(getApplicationContext(), new TwitterCore(authConfig), new TweetComposer());

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        Intent i = getIntent();

        final String _IDUSER  = i.getExtras().getString("EXTRA__IDUSER");
        final String _ID  = i.getExtras().getString("EXTRA__ID");
        final String NAME  = i.getExtras().getString("EXTRA_NAME");
        HASHTAG  = "#" + i.getExtras().getString("EXTRA_HASHTAG");

        String CATEGORIA  = i.getExtras().getString("EXTRA_CATEGORIA");
        String DESCRIPCION  = i.getExtras().getString("EXTRA_DESCRIPCION");
        String HISTORIA  = i.getExtras().getString("EXTRA_HISTORIA");
        String CONTACTO  = i.getExtras().getString("EXTRA_CONTACTO");
        String VIGENCIA  = i.getExtras().getString("EXTRA_VIGENCIA");
        String LOGISTICA  = i.getExtras().getString("EXTRA_LOGISTICA");
        String NOTAS  = i.getExtras().getString("EXTRA_NOTAS");
        Integer MPDAIS = i.getExtras().getInt("EXTRA_MPADIS");
        String LINK  = i.getExtras().getString("EXTRA_LINK");
        Integer LIMITE = i.getExtras().getInt("EXTRA_LIMITE");



        getSupportActionBar().setTitle(NAME);


        TextView textView_categoria = (TextView) findViewById(R.id.text_Categoria);
        textView_categoria.setText(CATEGORIA);

        TextView textView_descripcion = (TextView) findViewById(R.id.text_Descripcion);
        textView_descripcion.setText(DESCRIPCION);

        TextView textView_historia = (TextView) findViewById(R.id.text_Historia);
        textView_historia.setText(HISTORIA);

        TextView textView_contacto = (TextView) findViewById(R.id.text_contacto);
        textView_contacto.setText(CONTACTO);

        TextView textView_vigencia = (TextView) findViewById(R.id.text_vigencia);
        textView_vigencia.setText(VIGENCIA);

        TextView textView_logistica = (TextView) findViewById(R.id.text_logistica);
        textView_logistica.setText(LOGISTICA);

        TextView textView_notas = (TextView) findViewById(R.id.text_notas);
        textView_notas.setText(NOTAS);

        TextView textView_MPDIS = (TextView) findViewById(R.id.text_mpadis);
        textView_MPDIS.setText(MPDAIS.toString());

        TextView textView_link = (TextView) findViewById(R.id.text_link);
        textView_link.setText(LINK);

        TextView textView_limite = (TextView) findViewById(R.id.text_limite);
        textView_limite.setText(LIMITE.toString());


        // Setear escucha al FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_close_white_24dp));
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new AlertDialog.Builder(DetailRetoInscritoActivity.this)
                                .setTitle("Salir del Reto")
                                .setMessage(NAME)
                                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        SalirUsertoReto sal = new SalirUsertoReto(_ID, _IDUSER);
                                        sal.execute();
                                        try {
                                            String result = sal.get();
                                            if(result.equals("OK")){
                                                new AlertDialog.Builder(DetailRetoInscritoActivity.this)
                                                        .setTitle("Reto "+NAME)
                                                        .setMessage("Saliste de el reto desaparecera de inscritos la proxima vez que inicies session")
                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int which) {
                                                            }
                                                        })
                                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                                        .show();
                                            }
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        } catch (ExecutionException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // do nothing
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();

                    }
                }
        );

        ImageButton btn_shareTw = (ImageButton)findViewById(R.id.shareTw);
        btn_shareTw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TweetComposer.Builder builder = new TweetComposer.Builder(mContext)
                        .text(getString(R.string.hashtag) + " " + HASHTAG);
                builder.show();
            }
        });


    }

    private void setToolbar() {
        // Añadir la Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void returnResult(){
        this.setResult(Activity.RESULT_OK);
    }

    // Initialize the facebook sdk and then callback manager will handle the login responses.

    protected void facebookSDKInitialize() {

        FacebookSdk.sdkInitialize(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();
    }

    // this method is for create a dialog box to choose options to select Image to share on facebook.
    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Cancel" };

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(DetailRetoInscritoActivity.this);
        builder.setTitle("Selecciona la foto!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Tomar Foto")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Cancelar")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == REQUEST_CAMERA)

                onCaptureImageResult(data);
        }

    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ShareDialog(HASHTAG, thumbnail);
    }

    public void ShareDialog(String hashtag, Bitmap imagePath){

        String TAG = "RETO HASHTAG";
        Log.v(TAG,hashtag );

        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(imagePath)
                .setCaption(getString(R.string.hashtag))
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .setShareHashtag(new ShareHashtag.Builder()
                        .setHashtag(getString(R.string.hashtag))
                        .build())
                .setShareHashtag(new ShareHashtag.Builder()
                        .setHashtag(hashtag)
                        .build())
                .build();

        ///registercallback
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        shareDialog.show(content);
    }


    public void obtenerpost(String postId){
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                postId,
                null,
                com.facebook.HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        try {
                            JSONArray rawData = response.getJSONObject().getJSONArray("data");

                            for(int j=0; j<rawData.length();j++){
                                String foto = ((JSONObject)rawData.get(j)).get("picture").toString();
                                //String TAG = "FOTO--->";
                                //Log.v(TAG, foto );
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();
    }

    @Override
    public void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(getApplicationContext());
    }

    @Override
    public void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(getApplicationContext());
    }


    public class SalirUsertoReto extends AsyncTask<String, String, String> {

        String json;
        String IdUser;
        String Reto;

        public SalirUsertoReto(String Reto, String IdUser) {

            this.IdUser = IdUser;
            this.Reto = Reto;

        }


        @Override
        protected String doInBackground(String... params) {

            String result = makeRequest("http://ingenieria.uaq.mx/humui/api/token/humui2016token/reto/unsuscribe",
                    this.Reto, this.IdUser);
            String TAG2 = "USER--->";
            Log.v(TAG2, this.IdUser );
            String TAG3 = "USER->RETO->Uns-->";
            Log.v(TAG3, result );
            String TAG = "RETO--->";
            Log.v(TAG, this.Reto );
            return result;
        }

    }

    public static String makeRequest(String url,String Reto, String IdUser) {
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("usuario", IdUser);
            jsonObject.accumulate("reto", Reto);


            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";


        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }


    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

}
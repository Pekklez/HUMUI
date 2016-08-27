package com.uaq.HUMUI.Activities.retos;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

import io.fabric.sdk.android.Fabric;


public class DetailActivity extends AppCompatActivity {


    private static final String EXTRA_NAME = "com.uaq.toolbarapp.name";
    private static final String EXTRA_DRAWABLE = "com.uaq.toolbarapp.drawable";
    private CallbackManager callbackManager;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


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
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new AlertDialog.Builder(DetailActivity.this)
                                .setTitle("Aceptar Reto")
                                .setMessage(NAME)
                                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        RegistrarUsertoReto reg = new RegistrarUsertoReto(_ID,_IDUSER);
                                        reg.execute();
                                        try {
                                            String result = reg.get();
                                            if(result.equals("Created")){
                                                new AlertDialog.Builder(DetailActivity.this)
                                                        .setTitle("Reto "+ NAME)
                                                        .setMessage("Inscrito Correctamente el reto aparece en inscritos la proxima vez que abras la app")
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
                                .setIcon(android.R.drawable.ic_dialog_info)
                                .show();
                    }
                }
        );
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

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);


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

    public class RegistrarUsertoReto extends AsyncTask<String, String, String> {

        String json;
        String IdUser;
        String Reto;
        String result;

        public RegistrarUsertoReto(String Reto, String IdUser) {

            this.IdUser = IdUser;
            this.Reto = Reto;
        }

        @Override
        protected String doInBackground(String... params) {

            result = makeRequest("http://ingenieria.uaq.mx/humui/api/token/humui2016token/reto/suscribe",
                    this.Reto, this.IdUser);
            String TAG2 = "USER--->";
            Log.v(TAG2, this.IdUser );
            String TAG = "USER->RETO--->";
            Log.v(TAG, this.Reto );
            String TAG3 = "USER->RETO->Uns-->";
            Log.v(TAG3, result );

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            if (result.equals("Conflict")) {
                new AlertDialog.Builder(DetailActivity.this)
                        .setTitle("Reto lleno")
                        .setMessage("Lo sentimos :(")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
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
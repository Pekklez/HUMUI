    package com.uaq.HUMUI.Activities;

    import android.app.Activity;
    import android.app.ProgressDialog;
    import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.uaq.HUMUI.Activities.categorias.educacionActivity;
import com.uaq.HUMUI.Activities.categorias.inclusionActivity;
import com.uaq.HUMUI.Activities.categorias.infraestructuraActivity;
import com.uaq.HUMUI.Activities.categorias.sustentabilidadActivity;
import com.uaq.HUMUI.Activities.categorias.vinculacionActivity;
import com.uaq.HUMUI.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;

import io.fabric.sdk.android.Fabric;

    public class MainActivity extends Activity {

        // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
        private static final String TWITTER_KEY = "GGphQaBOU0qFliTaAmGjTMvGI";
        private static final String TWITTER_SECRET = "QDX3fS71CzBVEtG2DpS2AraFuHPgOGZBJ6R3q9948LnGMGUib0";

        private ImageButton btnConnect;

        private CallbackManager callbackManager;
        private TwitterAuthClient client;

        private String id_user;
        /**
         * ATTENTION: This was auto-generated to implement the App Indexing API.
         * See https://g.co/AppIndexing/AndroidStudio for more information.
         */
        private GoogleApiClient client2;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            //---Facebook
            FacebookSdk.sdkInitialize(this.getApplicationContext());
            callbackManager = CallbackManager.Factory.create();
            //--Twitter
            TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
            Fabric.with(this, new Twitter(authConfig));
            client = new TwitterAuthClient();
            setContentView(R.layout.activity_main);

            //---------------Facebook.---------------------
            LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    final AccessToken accessToken = loginResult.getAccessToken();
                    //---------------------------------

                    GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {

                            JSONObject json = response.getJSONObject();
                            try {
                                if (json != null) {
                                    String email = json.getString("email");
                                    Profile profile = Profile.getCurrentProfile();
                                    String idFacebook = profile.getId();
                                    String nameFacebook = profile.getName();
                                    String pictureFacebook = (profile.getProfilePictureUri(200, 200)).toString();

                                    //REGISTRAR USER
                                    new RegistrarUser(idFacebook, AccessToken.getCurrentAccessToken().toString(),
                                            email, nameFacebook, pictureFacebook,0).execute();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id,name,link,email,picture");
                    request.setParameters(parameters);
                    request.executeAsync();

                    //-----------------------

                }

                @Override
                public void onCancel() {
                    Toast.makeText(MainActivity.this, "Login Cancel", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(FacebookException error) {

                }
            });

            ImageView imageViewFacebook_login = (ImageView) findViewById(R.id.imageViewFacebook_login);
            imageViewFacebook_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Collection<String> permissions = Arrays.asList("public_profile", "user_friends", "email");
                    LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, permissions);
                }
            });
            //--------------Twitter------------------------
            ImageView imageViewTwitter_login = (ImageView) findViewById(R.id.imageViewTwitter_Login);
            imageViewTwitter_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    client.authorize(MainActivity.this, new Callback<TwitterSession>() {
                        @Override
                        public void success(Result<TwitterSession> result) {

                            TwitterSession session = result.data;

                            String idTW = String.valueOf(session.getUserId());

                            new RegistrarUser(idTW, session.getAuthToken().toString(),
                                    "", session.getUserName(), "https://twitter.com/"+session.getUserName()+"/profile_image?size=bigger",1).execute();


                        }

                        @Override
                        public void failure(TwitterException exception) {
                            Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });


            //------------------------Bottom-----------
            ImageView imageViewSustentabilidad = (ImageView) findViewById(R.id.imageViewSustentabilidad);
            imageViewSustentabilidad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentMenuActivity = new Intent(MainActivity.this, sustentabilidadActivity.class);
                    startActivity(intentMenuActivity);
                }
            });
            ImageView imageViewEducacion = (ImageView) findViewById(R.id.imageViewEducacion);
            imageViewEducacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentMenuActivity = new Intent(MainActivity.this, educacionActivity.class);
                    startActivity(intentMenuActivity);
                }
            });
            ImageView imageViewVinculacion = (ImageView) findViewById(R.id.imageViewVinculacion);
            imageViewVinculacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentMenuActivity = new Intent(MainActivity.this, vinculacionActivity.class);
                    startActivity(intentMenuActivity);
                }
            });
            ImageView imageViewInclusion = (ImageView) findViewById(R.id.imageViewInlcusion);
            imageViewInclusion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentMenuActivity = new Intent(MainActivity.this, inclusionActivity.class);
                    startActivity(intentMenuActivity);
                }
            });
            ImageView imageViewInfraestructura = (ImageView) findViewById(R.id.imageViewInfraestructura);
            imageViewInfraestructura.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentMenuActivity = new Intent(MainActivity.this, infraestructuraActivity.class);
                    startActivity(intentMenuActivity);
                }
            });

            // ATTENTION: This was auto-generated to implement the App Indexing API.
            // See https://g.co/AppIndexing/AndroidStudio for more information.
            client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        }

        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            client.onActivityResult(requestCode, resultCode, data);
            if (callbackManager.onActivityResult(requestCode, resultCode, data)) {
                return;
            }
        }

        @Override
        protected void onStart() {
            super.onStart();

        }

        @Override
        protected void onResume() {
            super.onResume();
            AppEventsLogger.activateApp(this);
        }

        @Override
        protected void onPause() {
            super.onPause();
            // Logs 'app deactivate' App Event.
            AppEventsLogger.deactivateApp(this);
        }


        public class RegistrarUser extends AsyncTask<String, String, String> {

            String json;
            String Id;
            String Token;
            String Email;
            String Name;
            String Picture;
            String result;
            int twofb;
            ProgressDialog progressDialog;


            public RegistrarUser(String Id, String Token, String Email, String Name, String Picture, int TwoFb) {

                this.Id = Id;
                this.Token = Token;
                this.Email = Email;
                this.Name = Name;
                this.Picture = Picture;
                this.twofb = TwoFb;

            }

            @Override
            protected void onPreExecute() {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Obteniendo Datos...");
                progressDialog.setCancelable(false);
                progressDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                result = makeRequest("http://ingenieria.uaq.mx/humui/api/token/humui2016token/user/put",
                        this.Id,this.Token, this.Email, this.Name, this.Picture, this.twofb);
                String TAG = "Result ID-->";
                Log.v(TAG, result );
                id_user = result;
                return result;
            }

            protected void onPostExecute(String result) {

                progressDialog.cancel();
                if(!result.equals("{\"error\":\"username already taken\"}"))
                    iniciar_Loggued(result, this.Id, this.Email, this.Name, this.twofb);
                else
                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
            }
        }


        public static String makeRequest(String url, String Id,String Token, String Email, String Name, String Picture, int twofb) {
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
                //-------Register From Facebook
                jsonObject.accumulate("id", "");
                if (twofb == 0){
                    jsonObject.accumulate("fbId", Id);
                    jsonObject.accumulate("fbName", Name);
                    jsonObject.accumulate("fbToken", Token);
                    jsonObject.accumulate("fbEmail", Email);
                    jsonObject.accumulate("fbPicture", Picture);

                    jsonObject.accumulate("twId", "");
                    jsonObject.accumulate("twUsername", "");
                    jsonObject.accumulate("twToken", "");
                    jsonObject.accumulate("twPicture", "");
                }

                //-------Register From Twitter
                if (twofb == 1){
                    jsonObject.accumulate("fbId", "");
                    jsonObject.accumulate("fbName", "");
                    jsonObject.accumulate("fbToken", "");
                    jsonObject.accumulate("fbEmail", "");
                    jsonObject.accumulate("fbPicture", "");

                    jsonObject.accumulate("twId", Id);
                    jsonObject.accumulate("twUsername", Name);
                    jsonObject.accumulate("twToken", Token);
                    jsonObject.accumulate("twPicture", Picture);
                }


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
                if(inputStream != null) {
                    result = convertInputStreamToString(inputStream);
                }else {
                    result = "Did not work!";
                }


            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());
            }

            // 11. return result
            return result;
        }

        private static String convertInputStreamToString(InputStream inputStream) throws IOException{
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
            String line = "";
            String result = "";
            while((line = bufferedReader.readLine()) != null)
                result += line;

            inputStream.close();

            return result;

        }

        private void iniciar_Loggued(String result, String id, String Email, String Name, int twofb){

            while(result==""){
            }
                Intent intentMenuActivity = new Intent(MainActivity.this, loggedActivity.class);
                intentMenuActivity.putExtra("idUser", formatId(result));
            if (twofb == 0) {
                intentMenuActivity.putExtra("nameFacebook", Name);
                intentMenuActivity.putExtra("emailFacebook", Email);
                intentMenuActivity.putExtra("idFacebook", id);
            }if (twofb == 1) {
                intentMenuActivity.putExtra("nameTwitter", Name);
                intentMenuActivity.putExtra("idTwitter", id);
            }
                startActivity(intentMenuActivity);
        }

        private String formatId(String id){
            String idFormated="";
            String text = id.substring(9, (id.length()-2));
            idFormated = text;
            return idFormated;
        }

    }

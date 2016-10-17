package com.uaq.HUMUI.Activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
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
import java.util.ArrayList;

public class LogrosActivity extends AppCompatActivity {

    LayoutParams params;
    int viewWidth;
    GestureDetector gestureDetector = null;
    HorizontalScrollView horizontalScrollView;


    ImageView imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10
            ,imageView11,imageView12,imageView13,imageView14,imageView15,imageView16,imageView17,imageView18,imageView19,imageView20,
            imageView21,imageView22;


    ArrayList<LinearLayout> layouts;
    int mWidth;
    int currPosition;

    private static String url = "http://ingenieria.uaq.mx/humui/api/token/humui2016token/publication/list/";
    private static String url_image = "http://ingenieria.uaq.mx/humui/";

    public String[] images_array_url;
    public String[] images_array_comentario;

    public Integer numProducts;
    String imagen, mpadis, categoria, descripcion, nombre;

    public ProgressDialog progressDialog;

    JSONArray recompensa = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logros);

        progressDialog = new ProgressDialog(LogrosActivity.this);
        progressDialog.setMessage("Cargando Retos...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        //---GALERIA
        new JSONParse().execute();

        //-------------REDONDEAR FOTOS
        imageView3 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView3);
        imageView4 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView4);
        imageView5 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView5);
        imageView6 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView6);
        imageView7 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView7);
        imageView8 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView8);
        imageView9 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView9);
        imageView10 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView10);
        imageView11 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView11);
        imageView12 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView12);
        imageView13 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView13);
        imageView14 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView14);
        imageView15 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView15);
        imageView16 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView16);
        imageView17 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView17);
        imageView18 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView18);
        imageView19 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView19);
        imageView20 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView20);
        imageView21 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView21);
        imageView22 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView22);

        //-------------GALERIA

        LinearLayout prev = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.prev);
        LinearLayout next = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.next);
        horizontalScrollView =(HorizontalScrollView)findViewById(com.uaq.HUMUI.R.id.horizontalScrollView);
        LinearLayout linearLayout1 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild1);
        LinearLayout linearLayout2 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild2);
        LinearLayout linearLayout3 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild3);
        LinearLayout linearLayout4 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild4);
        LinearLayout linearLayout5 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild5);
        LinearLayout linearLayout6 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild6);
        LinearLayout linearLayout7 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild7);
        LinearLayout linearLayout8 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild8);
        LinearLayout linearLayout9 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild9);
        LinearLayout linearLayout10 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild10);
        LinearLayout linearLayout11 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild11);
        LinearLayout linearLayout12 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild12);
        LinearLayout linearLayout13 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild13);
        LinearLayout linearLayout14 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild14);
        LinearLayout linearLayout15 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild15);
        LinearLayout linearLayout16 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild16);
        LinearLayout linearLayout17 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild17);
        LinearLayout linearLayout18 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild18);
        LinearLayout linearLayout19 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild19);
        LinearLayout linearLayout20 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild20);


        Display display = getWindowManager().getDefaultDisplay();
        mWidth = display.getWidth();
        viewWidth = mWidth/3;
        layouts = new ArrayList<LinearLayout>();
        params = new LayoutParams(viewWidth, LayoutParams.WRAP_CONTENT);

        linearLayout1.setLayoutParams(params);
        linearLayout2.setLayoutParams(params);
        linearLayout3.setLayoutParams(params);
        linearLayout4.setLayoutParams(params);
        linearLayout5.setLayoutParams(params);
        linearLayout6.setLayoutParams(params);
        linearLayout7.setLayoutParams(params);
        linearLayout8.setLayoutParams(params);
        linearLayout9.setLayoutParams(params);
        linearLayout10.setLayoutParams(params);
        linearLayout11.setLayoutParams(params);
        linearLayout12.setLayoutParams(params);
        linearLayout13.setLayoutParams(params);
        linearLayout14.setLayoutParams(params);
        linearLayout15.setLayoutParams(params);
        linearLayout16.setLayoutParams(params);
        linearLayout17.setLayoutParams(params);
        linearLayout18.setLayoutParams(params);
        linearLayout19.setLayoutParams(params);
        linearLayout20.setLayoutParams(params);

        layouts.add(linearLayout1);
        layouts.add(linearLayout2);
        layouts.add(linearLayout3);
        layouts.add(linearLayout4);
        layouts.add(linearLayout5);
        layouts.add(linearLayout6);
        layouts.add(linearLayout7);
        layouts.add(linearLayout8);
        layouts.add(linearLayout9);
        layouts.add(linearLayout10);
        layouts.add(linearLayout11);
        layouts.add(linearLayout12);
        layouts.add(linearLayout13);
        layouts.add(linearLayout14);
        layouts.add(linearLayout15);
        layouts.add(linearLayout16);
        layouts.add(linearLayout17);
        layouts.add(linearLayout18);
        layouts.add(linearLayout19);
        layouts.add(linearLayout20);

        //------------POP-UP
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView3.getDrawable());
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView4.getDrawable());
            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView5.getDrawable());
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView6.getDrawable());
            }
        });
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView7.getDrawable());
            }
        });
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView8.getDrawable());
            }
        });
        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView9.getDrawable());
            }
        });
        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView10.getDrawable());
            }
        });
        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView11.getDrawable());
            }
        });
        imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView12.getDrawable());
            }
        });
        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView13.getDrawable());
            }
        });
        imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView14.getDrawable());
            }
        });
        imageView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView15.getDrawable());
            }
        });
        imageView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView16.getDrawable());
            }
        });
        imageView17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView17.getDrawable());
            }
        });

        imageView18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView18.getDrawable());
            }
        });

        imageView19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView19.getDrawable());
            }
        });
        imageView20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView20.getDrawable());
            }
        });
        imageView21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView21.getDrawable());
            }
        });
        imageView22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView22.getDrawable());
            }
        });

    }

    public void cargarFotos(){
        int y = 0;
        for (int i = 0 ; i < numProducts ; i++)
        {
            int[] imv_id= {com.uaq.HUMUI.R.id.imageView3,
                    com.uaq.HUMUI.R.id.imageView4,
                    com.uaq.HUMUI.R.id.imageView5,
                    com.uaq.HUMUI.R.id.imageView6,
                    com.uaq.HUMUI.R.id.imageView7,
                    com.uaq.HUMUI.R.id.imageView8,
                    com.uaq.HUMUI.R.id.imageView9,
                    com.uaq.HUMUI.R.id.imageView10,
                    com.uaq.HUMUI.R.id.imageView11,
                    com.uaq.HUMUI.R.id.imageView12,
                    com.uaq.HUMUI.R.id.imageView13,
                    com.uaq.HUMUI.R.id.imageView14,
                    com.uaq.HUMUI.R.id.imageView15,
                    com.uaq.HUMUI.R.id.imageView16,
                    com.uaq.HUMUI.R.id.imageView17,
                    com.uaq.HUMUI.R.id.imageView18,
                    com.uaq.HUMUI.R.id.imageView19,
                    com.uaq.HUMUI.R.id.imageView20,
                    com.uaq.HUMUI.R.id.imageView21,
                    com.uaq.HUMUI.R.id.imageView22};

            int[] nombre_id= {R.id.nombre3,
                    R.id.nombre4,
                    com.uaq.HUMUI.R.id.nombre5,
                    com.uaq.HUMUI.R.id.nombre6,
                    com.uaq.HUMUI.R.id.nombre7,
                    com.uaq.HUMUI.R.id.nombre8,
                    com.uaq.HUMUI.R.id.nombre9,
                    com.uaq.HUMUI.R.id.nombre10,
                    com.uaq.HUMUI.R.id.nombre11,
                    com.uaq.HUMUI.R.id.nombre12,
                    com.uaq.HUMUI.R.id.nombre13,
                    com.uaq.HUMUI.R.id.nombre14,
                    com.uaq.HUMUI.R.id.nombre15,
                    com.uaq.HUMUI.R.id.nombre16,
                    com.uaq.HUMUI.R.id.nombre17,
                    com.uaq.HUMUI.R.id.nombre18,
                    com.uaq.HUMUI.R.id.nombre19,
                    com.uaq.HUMUI.R.id.nombre20,
                    com.uaq.HUMUI.R.id.nombre21,
                    com.uaq.HUMUI.R.id.nombre22};

            if(i<20) {
                ImageView imv = (ImageView) findViewById(imv_id[y]);
                TextView txvn = (TextView) findViewById(nombre_id[y]);
                new getUserPicFB(imv).execute(url_image + images_array_url[i]);
                txvn.setText(images_array_comentario[i]);
                y++;
            }else {
                break;
            }
        }
    }

    public void showImage(Drawable imagen) {
        Dialog builder = new Dialog(this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                //nothing;
            }
        });

        ImageView imageView = new ImageView(this);
        imageView.setImageDrawable(imagen);

        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
    }

    public RoundedBitmapDrawable getRounded(Drawable drawable){
        Bitmap image =((BitmapDrawable)drawable).getBitmap();
        RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(Resources.getSystem() , image);
        roundedDrawable.setCornerRadius(10);
        return roundedDrawable;
    }


    class getUserPicFB extends AsyncTask<String, Void, Bitmap> {
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

            WindowManager wm = (WindowManager) getBaseContext().getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            DisplayMetrics metrics = new DisplayMetrics();
            display.getMetrics(metrics);


            int width = result.getWidth();
            int height = result.getHeight();
            int newW = 1;
            int newH = 1;

            if(metrics.widthPixels > metrics.heightPixels){
                newH = metrics.heightPixels-20;
                newW = metrics.heightPixels-20;
            }else {
                if(metrics.widthPixels < metrics.heightPixels) {
                    newH = metrics.widthPixels-20;
                    newW = metrics.widthPixels-20;
                }
            }

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

    public  class JSONParse extends AsyncTask<String, String, JSONObject>{

        private JSONParse instance;


        public JSONParse(){}

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected JSONObject doInBackground(String... params) {

            String fullString = "";
            URL url_url = null;
            JSONObject jsonObject = null;
            JSONParser jsonParser;
            try {
                url_url = new URL(url);

                BufferedReader reader = new BufferedReader(new InputStreamReader(url_url.openStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    fullString += line;
                }
                reader.close();


                jsonParser = new JSONParser();
                jsonObject = new JSONObject("{\"logros\":"+fullString+"}");

            } catch (java.io.IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {

            // Getting JSON Array
            try {
                recompensa = jsonObject.getJSONArray("logros");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            images_array_url = new String[recompensa.length()];
            images_array_comentario = new String[recompensa.length()];
            numProducts = recompensa.length();


            for (int i = 0; i < recompensa.length(); i++) {
                JSONObject json = null;
                try {
                    json = recompensa.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    imagen = new String(json.getString("image"));
                    nombre = new String(json.getString("text"));

                    images_array_url[i] = imagen;
                    images_array_comentario[i] = nombre;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            cargarFotos();
            if(progressDialog.isShowing()) {
                progressDialog.cancel();
            }
        }
    }
}



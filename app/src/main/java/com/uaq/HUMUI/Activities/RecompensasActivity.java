package com.uaq.HUMUI.Activities;

import android.app.Dialog;
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
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

public class RecompensasActivity extends AppCompatActivity {

    LayoutParams params;
    int viewWidth;
    GestureDetector gestureDetector = null;
    HorizontalScrollView horizontalScrollView;


    ImageView imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10
            ,imageView11,imageView12,imageView13,imageView14,imageView15;

    TextView electronicos,escolares,libros, artuaq;

    ArrayList<LinearLayout> layouts;
    int mWidth;
    int currPosition;

    private static String url = "http://ingenieria.uaq.mx/humui/api/token/humui2016token/recompensa/list";
    private static String url_image = "http://ingenieria.uaq.mx/humui";

    public String[] images_array_url;
    public String[] images_array_categoria;
    public String[] images_array_descripcion;
    public String[] images_array_nombre;
    public String[] images_array_mpadis;

    public Integer numProducts;
    String imagen, mpadis, categoria, descripcion, nombre;

    JSONArray recompensa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.uaq.HUMUI.R.layout.activity_recompensas);

        //---GALERIA
        new JSONParse().execute();

        //---GETMPADIS
        String idUser = getIntent().getExtras().getString("idUser");
        new getMpadis(idUser).execute();


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


        //------- CATEGORIAS DE PRODUCTOS --------------------


        electronicos = (TextView)findViewById(R.id.child1);
        artuaq = (TextView)findViewById(R.id.child2);
        escolares = (TextView)findViewById(R.id.child3);
        libros = (TextView)findViewById(R.id.child4);

        final RelativeLayout relativeLayoutElectronicos  = (RelativeLayout)findViewById(R.id.relativeLayoutElectronicos);

        electronicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayoutElectronicos.setVisibility(View.VISIBLE);
                int y = 0;

                for (int i = 0 ; i < numProducts ; i++)
                {
                    if(images_array_categoria[i].equals("Electrónicos")){

                        int[] imv_id= {com.uaq.HUMUI.R.id.imageView3,com.uaq.HUMUI.R.id.imageView4, com.uaq.HUMUI.R.id.imageView5, com.uaq.HUMUI.R.id.imageView6,com.uaq.HUMUI.R.id.imageView7,
                                com.uaq.HUMUI.R.id.imageView8, com.uaq.HUMUI.R.id.imageView9, com.uaq.HUMUI.R.id.imageView10,com.uaq.HUMUI.R.id.imageView11,
                                com.uaq.HUMUI.R.id.imageView12,com.uaq.HUMUI.R.id.imageView13,com.uaq.HUMUI.R.id.imageView14,com.uaq.HUMUI.R.id.imageView15};
                        int[] nombre_id= {R.id.nombre3,R.id.nombre4, com.uaq.HUMUI.R.id.nombre5, com.uaq.HUMUI.R.id.nombre6,com.uaq.HUMUI.R.id.nombre7,
                                com.uaq.HUMUI.R.id.nombre8, com.uaq.HUMUI.R.id.nombre9, com.uaq.HUMUI.R.id.nombre10,com.uaq.HUMUI.R.id.nombre11,
                                com.uaq.HUMUI.R.id.nombre12,com.uaq.HUMUI.R.id.nombre13,com.uaq.HUMUI.R.id.nombre14,com.uaq.HUMUI.R.id.nombre15};
                        int[] padis_id= {R.id.mpadis3,R.id.mpadis4, com.uaq.HUMUI.R.id.mpadis5, com.uaq.HUMUI.R.id.mpadis6,com.uaq.HUMUI.R.id.mpadis7,
                                com.uaq.HUMUI.R.id.mpadis8, com.uaq.HUMUI.R.id.mpadis9, com.uaq.HUMUI.R.id.mpadis10,com.uaq.HUMUI.R.id.mpadis11,
                                com.uaq.HUMUI.R.id.mpadis12,com.uaq.HUMUI.R.id.mpadis13,com.uaq.HUMUI.R.id.mpadis14,com.uaq.HUMUI.R.id.mpadis15};

                        ImageView imv = (ImageView)findViewById(imv_id[y]);
                        TextView  txv = (TextView)findViewById(padis_id[y]);
                        TextView  txvn = (TextView)findViewById(nombre_id[y]);
                        new getUserPicFB(imv).execute(url_image + images_array_url[i]);
                        txv.setText(images_array_mpadis[i] + " MPADIS");
                        txvn.setText(images_array_nombre[i]);

                        y++;
                    }
                }
            }
        });
        artuaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayoutElectronicos.setVisibility(View.VISIBLE);
                int y = 0;

                for (int i = 0 ; i < numProducts ; i++)
                {
                    if(images_array_categoria[i].equals("Productos UAQ - Ropa 100% UAQ") || images_array_categoria[i].equals("Productos UAQ - Souvenir UAQ") || images_array_categoria[i].equals("Productos UAQ - Artículos escolares") ){

                        int[] imv_id= {com.uaq.HUMUI.R.id.imageView3,com.uaq.HUMUI.R.id.imageView4, com.uaq.HUMUI.R.id.imageView5, com.uaq.HUMUI.R.id.imageView6,com.uaq.HUMUI.R.id.imageView7,
                                com.uaq.HUMUI.R.id.imageView8, com.uaq.HUMUI.R.id.imageView9, com.uaq.HUMUI.R.id.imageView10,com.uaq.HUMUI.R.id.imageView11,
                                com.uaq.HUMUI.R.id.imageView12,com.uaq.HUMUI.R.id.imageView13,com.uaq.HUMUI.R.id.imageView14,com.uaq.HUMUI.R.id.imageView15};
                        int[] nombre_id= {R.id.nombre3,R.id.nombre4, com.uaq.HUMUI.R.id.nombre5, com.uaq.HUMUI.R.id.nombre6,com.uaq.HUMUI.R.id.nombre7,
                                com.uaq.HUMUI.R.id.nombre8, com.uaq.HUMUI.R.id.nombre9, com.uaq.HUMUI.R.id.nombre10,com.uaq.HUMUI.R.id.nombre11,
                                com.uaq.HUMUI.R.id.nombre12,com.uaq.HUMUI.R.id.nombre13,com.uaq.HUMUI.R.id.nombre14,com.uaq.HUMUI.R.id.nombre15};
                        int[] padis_id= {R.id.mpadis3,R.id.mpadis4, com.uaq.HUMUI.R.id.mpadis5, com.uaq.HUMUI.R.id.mpadis6,com.uaq.HUMUI.R.id.mpadis7,
                                com.uaq.HUMUI.R.id.mpadis8, com.uaq.HUMUI.R.id.mpadis9, com.uaq.HUMUI.R.id.mpadis10,com.uaq.HUMUI.R.id.mpadis11,
                                com.uaq.HUMUI.R.id.mpadis12,com.uaq.HUMUI.R.id.mpadis13,com.uaq.HUMUI.R.id.mpadis14,com.uaq.HUMUI.R.id.mpadis15};

                        ImageView imv = (ImageView)findViewById(imv_id[y]);
                        TextView  txv = (TextView)findViewById(padis_id[y]);
                        TextView  txvn = (TextView)findViewById(nombre_id[y]);
                        new getUserPicFB(imv).execute(url_image + images_array_url[i]);
                        txv.setText(images_array_mpadis[i] + " MPADIS");
                        txvn.setText(images_array_nombre[i]);

                        y++;
                    }
                }
            }
        });
        escolares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayoutElectronicos.setVisibility(View.VISIBLE);
                int y = 0;

                for (int i = 0 ; i < numProducts ; i++)
                {
                    if(images_array_categoria[i].equals("Artículos académicos")){

                        int[] imv_id= {com.uaq.HUMUI.R.id.imageView3,com.uaq.HUMUI.R.id.imageView4, com.uaq.HUMUI.R.id.imageView5, com.uaq.HUMUI.R.id.imageView6,com.uaq.HUMUI.R.id.imageView7,
                                com.uaq.HUMUI.R.id.imageView8, com.uaq.HUMUI.R.id.imageView9, com.uaq.HUMUI.R.id.imageView10,com.uaq.HUMUI.R.id.imageView11,
                                com.uaq.HUMUI.R.id.imageView12,com.uaq.HUMUI.R.id.imageView13,com.uaq.HUMUI.R.id.imageView14,com.uaq.HUMUI.R.id.imageView15};
                        int[] nombre_id= {R.id.nombre3,R.id.nombre4, com.uaq.HUMUI.R.id.nombre5, com.uaq.HUMUI.R.id.nombre6,com.uaq.HUMUI.R.id.nombre7,
                                com.uaq.HUMUI.R.id.nombre8, com.uaq.HUMUI.R.id.nombre9, com.uaq.HUMUI.R.id.nombre10,com.uaq.HUMUI.R.id.nombre11,
                                com.uaq.HUMUI.R.id.nombre12,com.uaq.HUMUI.R.id.nombre13,com.uaq.HUMUI.R.id.nombre14,com.uaq.HUMUI.R.id.nombre15};
                        int[] padis_id= {R.id.mpadis3,R.id.mpadis4, com.uaq.HUMUI.R.id.mpadis5, com.uaq.HUMUI.R.id.mpadis6,com.uaq.HUMUI.R.id.mpadis7,
                                com.uaq.HUMUI.R.id.mpadis8, com.uaq.HUMUI.R.id.mpadis9, com.uaq.HUMUI.R.id.mpadis10,com.uaq.HUMUI.R.id.mpadis11,
                                com.uaq.HUMUI.R.id.mpadis12,com.uaq.HUMUI.R.id.mpadis13,com.uaq.HUMUI.R.id.mpadis14,com.uaq.HUMUI.R.id.mpadis15};

                        ImageView imv = (ImageView)findViewById(imv_id[y]);
                        TextView  txv = (TextView)findViewById(padis_id[y]);
                        TextView  txvn = (TextView)findViewById(nombre_id[y]);
                        new getUserPicFB(imv).execute(url_image + images_array_url[i]);
                        txv.setText(images_array_mpadis[i] + " MPADIS");
                        txvn.setText(images_array_nombre[i]);

                        y++;
                    }
                }
            }
        });
        libros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayoutElectronicos.setVisibility(View.VISIBLE);
                int y = 0;

                for (int i = 0 ; i < numProducts ; i++)
                {
                    if(images_array_categoria[i].equals("Libros")){

                        int[] imv_id= {com.uaq.HUMUI.R.id.imageView3,com.uaq.HUMUI.R.id.imageView4, com.uaq.HUMUI.R.id.imageView5, com.uaq.HUMUI.R.id.imageView6,com.uaq.HUMUI.R.id.imageView7,
                                com.uaq.HUMUI.R.id.imageView8, com.uaq.HUMUI.R.id.imageView9, com.uaq.HUMUI.R.id.imageView10,com.uaq.HUMUI.R.id.imageView11,
                                com.uaq.HUMUI.R.id.imageView12,com.uaq.HUMUI.R.id.imageView13,com.uaq.HUMUI.R.id.imageView14,com.uaq.HUMUI.R.id.imageView15};
                        int[] nombre_id= {R.id.nombre3,R.id.nombre4, com.uaq.HUMUI.R.id.nombre5, com.uaq.HUMUI.R.id.nombre6,com.uaq.HUMUI.R.id.nombre7,
                                com.uaq.HUMUI.R.id.nombre8, com.uaq.HUMUI.R.id.nombre9, com.uaq.HUMUI.R.id.nombre10,com.uaq.HUMUI.R.id.nombre11,
                                com.uaq.HUMUI.R.id.nombre12,com.uaq.HUMUI.R.id.nombre13,com.uaq.HUMUI.R.id.nombre14,com.uaq.HUMUI.R.id.nombre15};
                        int[] padis_id= {R.id.mpadis3,R.id.mpadis4, com.uaq.HUMUI.R.id.mpadis5, com.uaq.HUMUI.R.id.mpadis6,com.uaq.HUMUI.R.id.mpadis7,
                                com.uaq.HUMUI.R.id.mpadis8, com.uaq.HUMUI.R.id.mpadis9, com.uaq.HUMUI.R.id.mpadis10,com.uaq.HUMUI.R.id.mpadis11,
                                com.uaq.HUMUI.R.id.mpadis12,com.uaq.HUMUI.R.id.mpadis13,com.uaq.HUMUI.R.id.mpadis14,com.uaq.HUMUI.R.id.mpadis15};

                        ImageView imv = (ImageView)findViewById(imv_id[y]);
                        TextView  txv = (TextView)findViewById(padis_id[y]);
                        TextView  txvn = (TextView)findViewById(nombre_id[y]);
                        new getUserPicFB(imv).execute(url_image + images_array_url[i]);
                        txv.setText(images_array_mpadis[i] + " MPADIS");
                        txvn.setText(images_array_nombre[i]);

                        y++;
                    }
                }
            }
        });
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
        roundedDrawable.setCornerRadius(50);
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

            int width = result.getWidth();
            int height = result.getHeight();
            int newW = 300;
            int newH = 300;
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
                jsonObject = new JSONObject("{\"recompensa\":"+fullString+"}");

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
                recompensa = jsonObject.getJSONArray("recompensa");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            images_array_url = new String[recompensa.length()];
            images_array_mpadis = new String[recompensa.length()];
            images_array_categoria = new String[recompensa.length()];
            images_array_descripcion = new String[recompensa.length()];
            images_array_nombre = new String[recompensa.length()];
            numProducts = recompensa.length();


            for (int i = 0; i < recompensa.length(); i++) {
                JSONObject json = null;
                try {
                    json = recompensa.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    imagen = new String(json.getString("imagen"));
                    mpadis = new String(json.getString("mpadis"));
                    categoria = new String(json.getString("categoria"));
                    descripcion = new String(json.getString("descripcion"));
                    nombre = new String(json.getString("nombre"));

                    images_array_url[i] = imagen;
                    images_array_mpadis[i] = mpadis;
                    images_array_categoria[i] = categoria;
                    images_array_descripcion[i] = descripcion;
                    images_array_nombre[i] = nombre;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //------------------GET MPADIS
    class getMpadis extends AsyncTask<String, Void, JSONObject>{
        TextView imageView = (TextView)findViewById(R.id.textView1);
        String IdUser;
        public getMpadis(String idUser){
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
            String mpadis="";
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

                    mpadis = new Integer(json.getInt("mpadis")).toString();

                    String TAG3 = "MPADIS--------->";
                    Log.v(TAG3, mpadis );

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            imageView.setText(mpadis);
        }

    }


}



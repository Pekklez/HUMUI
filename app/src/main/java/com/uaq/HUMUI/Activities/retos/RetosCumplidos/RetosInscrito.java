package com.uaq.HUMUI.Activities.retos.RetosCumplidos;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.uaq.HUMUI.Activities.retos.JSONParser;
import com.uaq.HUMUI.Activities.retos.Reto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

public class RetosInscrito {
    private ArrayList<Reto> mRetos;
    private static RetosInscrito sRetosLista;
    private Reto ret;

    private static String url = "http://ingenieria.uaq.mx/humui/api/token/humui2016token/reto/list/by/user/";

    String id,nombre,categoria,descripcion,historia,contacto,vigencia,logistica,notas,link, hashtag;
    int limite, mpadis;
    boolean approved;

    JSONArray retoregistrado = null;
    JSONArray reto = null;
    private RetosInscrito(String correo){

        new JSONParse(correo).execute();
        mRetos = new ArrayList<Reto>();
    }
    public static RetosInscrito get(String correo){
        if(sRetosLista == null){
            sRetosLista = new RetosInscrito(correo);
        }
        return sRetosLista;
    }


    public ArrayList<Reto> getRetos(){
        return mRetos;
    }

    public Reto getRetos(UUID id){
        for(Reto c : mRetos){
            if(c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }


    public  class JSONParse extends AsyncTask<String, String, JSONObject> {

        private JSONParse instance;
        private ProgressDialog dialog;
        private String Correo;
        private  String fullString = "";


        public JSONParse(String correo){
            this.Correo = correo;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... params) {

            fullString = "";
            URL url_url = null;
            JSONObject jsonObject = null;
            JSONParser jsonParser;
            try {
                url = url+this.Correo;
                url_url = new URL(url);

                String TAG = "MyActivity";
                Log.v(TAG, url );

                BufferedReader reader = new BufferedReader(new InputStreamReader(url_url.openStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    fullString += line;
                }
                reader.close();

                if (fullString != "") {
                    jsonParser = new JSONParser();
                    jsonObject = new JSONObject("{\"retoregistrado\":" + fullString + "}");
                }

            } catch (java.io.IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            if (fullString != "") {
                // Getting JSON Array
                try {
                    retoregistrado = jsonObject.getJSONArray("retoregistrado");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                for (int i = 0; i < retoregistrado.length(); i++) {
                    JSONObject json = null;
                    JSONObject jsonreto = null;
                    try {
                        json = retoregistrado.getJSONObject(i);
                        reto = json.getJSONArray("reto");
                        jsonreto = reto.getJSONObject(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        id = new String(jsonreto.getString("_id"));
                        nombre = new String(jsonreto.getString("nombre"));
                        categoria= new String(jsonreto.getString("categoria"));
                        descripcion = new String(jsonreto.getString("descripcion"));
                        historia = new String(jsonreto.getString("historia"));
                        contacto = new String(jsonreto.getString("contacto"));
                        vigencia = new String(jsonreto.getString("vigencia"));
                        logistica = new String(jsonreto.getString("logistica"));
                        notas = new String(jsonreto.getString("notas"));
                        mpadis = new Integer(jsonreto.getInt("mpadis"));
                        link = new String(jsonreto.getString("link"));
                        limite= new Integer(jsonreto.getInt("limit"));
                        approved= new Boolean(jsonreto.getBoolean("approved"));
                        hashtag= new String(jsonreto.getString("hashtag"));



                        if(approved) {

                            ret = new Reto();
                            ret.set_id(id);
                            ret.setNombre(nombre);
                            ret.setCategoria(categoria);
                            ret.setDescripcion(descripcion);
                            ret.setHistoria(historia);
                            ret.setContacto(contacto);
                            ret.setVigencia(vigencia);
                            ret.setLogistica(logistica);
                            ret.setNotas(notas);
                            ret.setMPADIS(mpadis);
                            ret.setLink(link);
                            ret.setLimite(limite);
                            ret.setHashtag(hashtag);
                            mRetos.add(ret);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}

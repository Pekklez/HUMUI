package com.uaq.HUMUI.Activities.retos;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;

import com.uaq.HUMUI.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

public class Retos {
    private ArrayList<Reto> mRetos;
    private static Retos sRetosLista;
    private Reto ret;

    private static String url = "http://ingenieria.uaq.mx/humui/api/token/humui2016token/reto/list";

    String id,nombre,categoria,descripcion,historia,contacto,vigencia,logistica,notas,link;
    int limite, mpadis;
    boolean approved;

    JSONArray reto = null;
    JSONParse data;
    private Retos(){

        data = new JSONParse();
        data.execute();

        mRetos = new ArrayList<Reto>();
    }
    public static Retos get(){
        if(sRetosLista == null){
            sRetosLista = new Retos();
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


    public  class JSONParse extends AsyncTask<String, String, JSONObject>{

        private JSONParse instance;


        public JSONParse(){

        }


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
            jsonObject = new JSONObject("{\"reto\":"+fullString+"}");

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
                reto = jsonObject.getJSONArray("reto");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            for (int i = 0; i < reto.length(); i++) {
                JSONObject json = null;
                try {
                    json = reto.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    approved= new Boolean(json.getBoolean("approved"));

                    if(approved) {
                        id = new String(json.getString("_id"));
                        nombre = new String(json.getString("nombre"));
                        categoria= new String(json.getString("categoria"));
                        descripcion = new String(json.getString("descripcion"));
                        historia = new String(json.getString("historia"));
                        contacto = new String(json.getString("contacto"));
                        vigencia = new String(json.getString("vigencia"));
                        logistica = new String(json.getString("logistica"));
                        notas = new String(json.getString("notas"));
                        mpadis = new Integer(json.getInt("mpadis"));
                        link = new String(json.getString("link"));
                        limite= new Integer(json.getInt("limit"));

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
                        mRetos.add(ret);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

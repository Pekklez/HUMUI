package com.uaq.HUMUI.Activities.retos;

import android.os.AsyncTask;
import android.util.Log;

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
                        if(!json.isNull("_id")) {
                            id = new String(json.getString("_id"));
                        }else
                            id = "";
                        if(!json.isNull("nombre"))
                            nombre = new String(json.getString("nombre"));
                        else
                            nombre = "";
                        if(!json.isNull("categoria"))
                            categoria= new String(json.getString("categoria"));
                        else
                            categoria = "";
                        if(!json.isNull("descripcion"))
                            descripcion = new String(json.getString("descripcion"));
                        else
                            descripcion = "";
                        if(!json.isNull("historia"))
                            historia = new String(json.getString("historia"));
                        else
                            historia = "";
                        if(!json.isNull("contacto"))
                            contacto = new String(json.getString("contacto"));
                        else
                            contacto = "";
                        if(!json.isNull("vigencia"))
                            vigencia = new String(json.getString("vigencia"));
                        else
                            vigencia = "";
                        if(!json.isNull("logistica"))
                            logistica = new String(json.getString("logistica"));
                        else
                            logistica = "";
                        if(!json.isNull("notas"))
                            notas = new String(json.getString("notas"));
                        else
                            notas = "";
                        if(!json.isNull("mpadis"))
                            mpadis = new Integer(json.getInt("mpadis"));
                        else
                            mpadis = 0;
                        if(json.isNull("link") || json.getString("link").equals(""))
                            link = "(:";
                        else
                            link = json.getString("link");
                        if(!json.isNull("limit"))
                            limite= new Integer(json.getInt("limit"));
                        else
                            limite = 0;

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

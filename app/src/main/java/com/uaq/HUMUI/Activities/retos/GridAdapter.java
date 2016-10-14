package com.uaq.HUMUI.Activities.retos;


import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import com.uaq.HUMUI.Activities.retos.RetosCumplidos.DetailRetoInscritoActivity;
import com.uaq.HUMUI.R;

public class GridAdapter extends ArrayAdapter<Reto> {

    private final Context mContext;
    private static String IdUser = "";
    private static int Num;

    public GridAdapter(Context c, ArrayList<Reto> items, String idUser) {
        super(c,0,items);
        IdUser = idUser;
        mContext = c;

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }

        ImageButton imageButton = (ImageButton) view.findViewById(R.id.buttonIngresarReto);

        final Reto item = getItem(position);

        // Seteando Nombre
        final TextView name = (TextView) view.findViewById(R.id.nombre);
        name.setText(item.getNombre());

        // Seteando Descripción
        TextView descripcion = (TextView) view.findViewById(R.id.descripcion);
        descripcion.setText(item.getDescripcion());

        // Seteando Precio
        final TextView date = (TextView) view.findViewById(R.id.date);
        date.setText(item.getMPADIS().toString());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, DetailActivity.class);
                myIntent.putExtra("EXTRA__IDUSER", IdUser);
                myIntent.putExtra("EXTRA__ID", item.get_id());
                myIntent.putExtra("EXTRA_NAME", name.getText());
                myIntent.putExtra("EXTRA_CATEGORIA", item.getCategoria());
                myIntent.putExtra("EXTRA_DESCRIPCION", item.getDescripcion());
                myIntent.putExtra("EXTRA_HISTORIA", item.getHistoria());
                myIntent.putExtra("EXTRA_CONTACTO", item.getContacto());
                myIntent.putExtra("EXTRA_VIGENCIA", item.getVigencia());
                myIntent.putExtra("EXTRA_LOGISTICA", item.getLogistica());
                myIntent.putExtra("EXTRA_NOTAS", item.getNotas());
                myIntent.putExtra("EXTRA_MPADIS", item.getMPADIS());
                myIntent.putExtra("EXTRA_LINK", item.getLink());
                myIntent.putExtra("EXTRA_LIMITE", item.getLimite());

                v.getContext().startActivity(myIntent);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, DetailActivity.class);
                myIntent.putExtra("EXTRA__IDUSER", IdUser);
                myIntent.putExtra("EXTRA__ID", item.get_id());
                myIntent.putExtra("EXTRA_NAME", name.getText());
                myIntent.putExtra("EXTRA_CATEGORIA", item.getCategoria());
                myIntent.putExtra("EXTRA_DESCRIPCION", item.getDescripcion());
                myIntent.putExtra("EXTRA_HISTORIA", item.getHistoria());
                myIntent.putExtra("EXTRA_CONTACTO", item.getContacto());
                myIntent.putExtra("EXTRA_VIGENCIA", item.getVigencia());
                myIntent.putExtra("EXTRA_LOGISTICA", item.getLogistica());
                myIntent.putExtra("EXTRA_NOTAS", item.getNotas());
                myIntent.putExtra("EXTRA_MPADIS", item.getMPADIS());
                myIntent.putExtra("EXTRA_LINK", item.getLink());
                myIntent.putExtra("EXTRA_LIMITE", item.getLimite());

                v.getContext().startActivity(myIntent);
            }
        });

        return view;
    }
}

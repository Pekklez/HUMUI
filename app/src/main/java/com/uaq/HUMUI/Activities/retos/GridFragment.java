package com.uaq.HUMUI.Activities.retos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import in.srain.cube.views.GridViewWithHeaderAndFooter;

import com.uaq.HUMUI.Activities.retos.RetosCumplidos.GridAdapterCumplidos;
import com.uaq.HUMUI.Activities.retos.RetosCumplidos.RetosInscrito;
import com.uaq.HUMUI.R;



public class GridFragment extends Fragment {
    /**
     * Argumento que representa el número sección al que pertenece
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static String IdUser = "";

    /**
     * Creación prefabricada de un {@link GridFragment}
     */
    public static GridFragment newInstance(int sectionNumber,String idUser) {
        IdUser = idUser;
        GridFragment fragment = new GridFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public GridFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_fragment, container, false);

        // Obtención del grid view
        GridViewWithHeaderAndFooter grid =
                (GridViewWithHeaderAndFooter) rootView.findViewById(R.id.gridview);

        // Inicializar el grid view
        setUpGridView(grid);

        return rootView;
    }

    /**
     * Infla el grid view del fragmento dependiendo de la sección
     *
     * @param grid Instancia del grid view
     */
    private void setUpGridView(GridViewWithHeaderAndFooter grid) {
        int section_number = getArguments().getInt(ARG_SECTION_NUMBER);
        switch (section_number) {
            case 1:
                ArrayList<Reto> mRetos = Retos.get().getRetos();
                grid.setAdapter(new GridAdapter(getActivity(), mRetos, IdUser));
                break;
            case 2:
                ArrayList<Reto> mRetos2 = RetosInscrito.get(IdUser).getRetos();
                grid.setAdapter(new GridAdapterCumplidos(getActivity(), mRetos2, IdUser));
                break;
        }
    }
}
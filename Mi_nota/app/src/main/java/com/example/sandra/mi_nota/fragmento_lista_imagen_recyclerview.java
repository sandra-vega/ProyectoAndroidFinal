package com.example.sandra.mi_nota;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import Modelo.DAOImagen;
import Modelo.DAONota;
import Modelo.Imagen;
import Modelo.Notas;

/**
 * Created by alcohonsilver on 18/09/17.
 */

public class fragmento_lista_imagen_recyclerview extends Fragment {

    private Activity actividad;
    RecyclerView rcy_notas;
    ArrayList<Imagen> lista_Imagen;
    Adapter_Imagen adater_Imagen;
    DAOImagen objetoDAO;

    Comunicador comunicacion;

    @Override
    public void onAttach(Context contexto) {
        super.onAttach(contexto);
        if (contexto instanceof Activity) {
            this.actividad = (Activity) contexto;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup contenedor, Bundle savedInstanceState) {
        View vista = inflador.inflate(R.layout.fragmento_lista, contenedor, false);

        objetoDAO = new DAOImagen(getContext());

        rcy_notas =  vista.findViewById(R.id.rcly_lista_notas);
        comunicacion = (Comunicador) getActivity();
        inicializarRecicler();

        adater_Imagen.setOnItemLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(final View v) {
                final int id = rcy_notas.getChildAdapterPosition(v);
                AlertDialog.Builder menu = new AlertDialog.Builder(getContext());
                CharSequence[] opciones = { "Editar", "Borrar " };
                menu.setItems(opciones, new  DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int opcion) {
                        switch (opcion) {
                            case 0:
                               /* Notas nota =(Notas)lista_Imagen.get(id) ;
                                comunicacion.responder(""+nota.getId(),nota.getTitulo_nota(),
                                        nota.getDescripcion_nota(),nota.getFecha_nota());*/
                                /*FragmentManager fm = getFragmentManager();
                                fm.popBackStack();*/
                                break;

                            case 1:
                               /* Snackbar.make(v,"¿Estás seguro?", Snackbar.LENGTH_LONG)
                                        .setAction("SI", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Notas nota =(Notas)lista_Notas.get(id) ;
                                                objetoDAO.delete(nota.getId());
                                                confirmacion(nota.getId());
                                                FragmentManager fm = getFragmentManager();
                                                fm.popBackStack();

                                            }
                                        }).show();*/

                                break;
                        }
                    }
                });
                menu.create().show();
                return true;
            }
        });


        adater_Imagen.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*final int id = rcy_notas.getChildAdapterPosition(v);
                Toast.makeText(actividad, "Seleccionado el elemento: "
                        + rcy_notas.getChildAdapterPosition(v), Toast.LENGTH_SHORT).show();
                Notas nota =(Notas)lista_Imagen.get(id) ;
                comunicacion.responder(""+nota.getId(),nota.getTitulo_nota(),
                        nota.getDescripcion_nota(),nota.getFecha_nota());*/
            } });

        return vista;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }

    public void inicializarRecicler() {

        lista_Imagen =new ArrayList<>();
        rcy_notas.setLayoutManager(new LinearLayoutManager(getContext()));
        //rcy_notas.setHasFixedSize(true);
        setHasOptionsMenu(true);
        lista_Imagen =  objetoDAO.getAll();
        adater_Imagen=new Adapter_Imagen(lista_Imagen);
        rcy_notas.setAdapter(adater_Imagen);
        //rcy_notas.setItemAnimator(new DefaultItemAnimator());


    }

    public void confirmacion(long id){

        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(getActivity());
        dlgAlert.setMessage(""+id);
        dlgAlert.setTitle("Se ha eliminado satisfactoriamente!!!");
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
}

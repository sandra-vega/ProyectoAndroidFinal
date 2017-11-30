package com.example.sandra.mi_nota;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemLongClickListener.*;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

import Modelo.DAONota;
import Modelo.Notas;

/**
 * Created by alcohonsilver on 18/09/17.
 */

public class fragmento_lista_recyclerview  extends Fragment {

    private Activity actividad;
    RecyclerView rcy_notas;
    ArrayList<Notas> lista_Notas;
    Adapter_Nota adater_Nota;
    DAONota objetoDAO;

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

        objetoDAO = new DAONota(getContext());

        rcy_notas =  vista.findViewById(R.id.rcly_lista_notas);
        comunicacion = (Comunicador) getActivity();
        inicializarRecicler();

        adater_Nota.setOnItemLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(final View v) {
                final int id = rcy_notas.getChildAdapterPosition(v);
                AlertDialog.Builder menu = new AlertDialog.Builder(getContext());
                CharSequence[] opciones = { "Editar", "Borrar " };
                menu.setItems(opciones, new  DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int opcion) {
                        switch (opcion) {
                            case 0:
                                Notas nota =(Notas)lista_Notas.get(id) ;
                                comunicacion.responder(""+nota.getId(),nota.getTitulo_nota(),
                                        nota.getDescripcion_nota(),nota.getFecha_nota());
                                /*FragmentManager fm = getFragmentManager();
                                fm.popBackStack();*/
                                break;

                            case 1:
                                Snackbar.make(v,"¿Estás seguro?", Snackbar.LENGTH_LONG)
                                        .setAction("SI", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Notas nota =(Notas)lista_Notas.get(id) ;
                                                objetoDAO.delete(nota.getId());
                                                confirmacion(nota.getId());
                                                FragmentManager fm = getFragmentManager();
                                                fm.popBackStack();

                                            }
                                        }).show();

                                break;
                        }
                    }
                });
                menu.create().show();
                return true;
            }
        });


        adater_Nota.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int id = rcy_notas.getChildAdapterPosition(v);
                Toast.makeText(actividad, "Seleccionado el elemento: "
                        + rcy_notas.getChildAdapterPosition(v), Toast.LENGTH_SHORT).show();
                Notas nota =(Notas)lista_Notas.get(id) ;
                comunicacion.responder(""+nota.getId(),nota.getTitulo_nota(),
                        nota.getDescripcion_nota(),nota.getFecha_nota());
            } });

        return vista;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }

    public void inicializarRecicler() {

        lista_Notas =new ArrayList<>();
        rcy_notas.setLayoutManager(new LinearLayoutManager(getContext()));
        //rcy_notas.setHasFixedSize(true);
        setHasOptionsMenu(true);
        lista_Notas =  objetoDAO.getAll();
        adater_Nota=new Adapter_Nota(lista_Notas);
        rcy_notas.setAdapter(adater_Nota);
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

package com.example.sandra.finalminota;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import Modelo.Fotos;


public class Fragment_Nota extends Fragment {
    private static final int COD_SELECCION =10 ;
    private static final int COD_FOTO=20 ;
    FloatingActionButton fab_foto;
    View layaut;
    private static final int SOLICITUD_PERMISO_CAMERA = 0;
    private static final int SOLICITUD_PERMISO_ESCRIBIR_MEMORIA = 1;
    private static final int SOLICITUD_PERMISO_LEER_MEMORIA = 2;
    RecyclerView rcy_fotos;
    ArrayList<Fotos> lista_fotos;
    Adapter_Foto adater;
    ImageView imgFoto;

private void llenar_lista_fotos(Uri img){
    lista_fotos.add(new Fotos("                  ",img));
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layaut = inflater.inflate(R.layout.fragment_nota, container, false);
        fab_foto = (FloatingActionButton) layaut.findViewById(R.id.img_camara);
        rcy_fotos = (RecyclerView) layaut.findViewById(R.id.rcy_lista_fotos);
        lista_fotos =new  ArrayList<>();
        rcy_fotos.setLayoutManager(new LinearLayoutManager(getContext()));
        adater=new Adapter_Foto(lista_fotos);


        fab_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamara();
                leerMemoria();
                escribirMemoria();
            }
        });

        return layaut;
    }

    void abrirCamara() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
           mosatrarDialogoOpciones();
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    SOLICITUD_PERMISO_CAMERA);
        }
    }

    private void mosatrarDialogoOpciones() {
        final CharSequence [] Opciones={"Tomar foto","Elegir foto","Cancelar"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Elegir una opcion");
        builder.setItems(Opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(Opciones[i].equals("Tomar foto")){
                    Toast.makeText(getActivity(),"tomar foto", Toast.LENGTH_SHORT).show();
                }else if(Opciones[i].equals("Elegir foto")){
                    Intent intent= new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/");
                    startActivityForResult(intent.createChooser(intent,"Seleccione"),COD_SELECCION);

                }else{
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case COD_SELECCION:
                Uri miPath=data.getData();
                 llenar_lista_fotos(miPath);
            rcy_fotos.setAdapter(adater);
                //imgFoto.setImageURI(miPath);
        }
    }

    void leerMemoria() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    SOLICITUD_PERMISO_LEER_MEMORIA);
        }
    }
    void escribirMemoria() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission. WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    SOLICITUD_PERMISO_ESCRIBIR_MEMORIA);
        }
    }
}

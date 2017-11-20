package com.example.sandra.finalminota;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;

import Modelo.Fotos;


public class Fragment_Nota extends Fragment {

    FloatingActionButton fab_foto;
    View layaut;

    private static final int SOLICITUD_PERMISO_CAMERA = 0;
    private static final int SOLICITUD_PERMISO_ESCRIBIR_MEMORIA = 1;
    private static final int SOLICITUD_PERMISO_LEER_MEMORIA = 2;

    RecyclerView rcy_fotos;
    ArrayList<Fotos> lista_fotos;
    Adapter_Foto_Seleccionar adater;

    private static final int COD_SELECCION =10 ;
    private static final int COD_FOTO=20 ;

    private static final String CARPETA_PRINCIPAL="misImagenesApp";
    private static final String CARPETA_IMAGEN = "imagenes";
    private static final String DIRECCTORIO_IMAGEN = CARPETA_PRINCIPAL + CARPETA_IMAGEN;
    private String path;
    File fileImagen;
    Bitmap bitmap;

    RecyclerView rcy_fotos_t;
    ArrayList<Fotos> lista_fotos_t;
    Adapter_Foto_Tomar adater_t;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layaut = inflater.inflate(R.layout.fragment_nota, container, false);
        fab_foto = (FloatingActionButton) layaut.findViewById(R.id.img_camara);
        rcy_fotos = (RecyclerView) layaut.findViewById(R.id.rcy_lista_fotos);
        lista_fotos =new  ArrayList<>();
        rcy_fotos.setLayoutManager(new LinearLayoutManager(getContext()));
        adater=new Adapter_Foto_Seleccionar(lista_fotos);

        rcy_fotos_t = (RecyclerView) layaut.findViewById(R.id.rcy_lista_fotos_t);
        lista_fotos_t =new  ArrayList<>();
        rcy_fotos_t.setLayoutManager(new LinearLayoutManager(getContext()));
        adater_t=new Adapter_Foto_Tomar(lista_fotos_t);


        fab_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamaraPermiso();
                leerMemoria();
                escribirMemoria();
            }
        });

        return layaut;
    }

    void abrirCamaraPermiso() {
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
                    abrirCamara();
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

    private void abrirCamara() {
        File miFile = new File(Environment.getExternalStorageDirectory(),DIRECCTORIO_IMAGEN);
        boolean isCreada= miFile.exists();

        if(!isCreada){
            isCreada=miFile.mkdir();
        }if(isCreada){
            Long consecutivo= System.currentTimeMillis()/1000;
            String nombre = consecutivo.toString()+".jpg";

            path= Environment.getExternalStorageDirectory()+File.separator+DIRECCTORIO_IMAGEN
                    +File.separator+nombre;
            fileImagen= new File(path);

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(fileImagen));

            startActivityForResult(intent,COD_FOTO);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case COD_SELECCION:
                Uri miPath=data.getData();
                llenar_lista_fotos(miPath);
                rcy_fotos.setAdapter(adater);
                break;
            case COD_FOTO:
                MediaScannerConnection.scanFile(getContext(), new String[]{path}, null,
                        new MediaScannerConnection.OnScanCompletedListener(){

                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Log.i("Path",""+path);
                            }
                        });
                    bitmap= BitmapFactory.decodeFile(path);
                llenar_lista_fotosT(bitmap);
                rcy_fotos_t.setAdapter(adater_t);

                break;


        }
    }

    private void llenar_lista_fotos(Uri img){
        lista_fotos.add(new Fotos("                  ",img));
    }
    private void llenar_lista_fotosT(Bitmap img){
        lista_fotos_t.add(new Fotos("                  ",img));
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

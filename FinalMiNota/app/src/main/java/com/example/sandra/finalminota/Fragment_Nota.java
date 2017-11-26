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
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import Modelo.Fotos;
import Modelo.Videos;


public class Fragment_Nota extends Fragment {
    View layaut;
    /*Veriables para permisos*/
    private static final int SOLICITUD_PERMISO_CAMERA = 0;
    private static final int SOLICITUD_PERMISO_ESCRIBIR_MEMORIA = 1;
    private static final int SOLICITUD_PERMISO_LEER_MEMORIA = 2;
    private static final int SOLICITUD_PERMISO_RECORD_AUDIO=3;
    private static final int SOLICITUD_PERMISO_CAPTURE_VIDEO_OUTPUT=4;
    private static final int SOLICITUD_PERMISO_CAPTURE_SECURE_VIDEO_OUTPUT=5;

    /*---------------------Fotos------------------*/
    /*imagen de Camara*/
    FloatingActionButton fab_foto;
    /*Variables para indicar que es lo que se va a utilizar elegir o tomar foto*/
    private static final int COD_SELECCION_CAMARA =10 ;
    private static final int COD_FOTO_CAMARA=20 ;

    /*inicializar el recyclerview, el Array, el adapter para elegir*/
    RecyclerView rcy_fotos;
    ArrayList<Fotos> lista_fotos;
    Adapter_Foto_Seleccionar adater;

    /*inicializar el recyclerview, el Array, el adapter para Tomar foto*/
    RecyclerView rcy_fotos_t;
    ArrayList<Fotos> lista_fotos_t;
    Adapter_Foto_Tomar adater_t;
    /*Veriables para tomar fotos */
    private static final String CARPETA_PRINCIPAL="misImagenesApp";
    private static final String CARPETA_IMAGEN = "imagenes";
    private static final String DIRECCTORIO_IMAGEN = CARPETA_PRINCIPAL + CARPETA_IMAGEN;
    private String path;
    File fileImagen;
    Bitmap bitmap;

    /*---------------------Videos------------------*/
    FloatingActionButton fab_video;

    /*Variables para indicar que es lo que se va a utilizar elegir o tomar foto*/
    private static final int COD_SELECCION_CAMARA_V =30 ;
    private static final int COD_FOTO_CAMARA_V=40;

    /*inicializar el recyclerview, el Array, el adapter para elegir*/
    RecyclerView rcy_video;
    ArrayList<Videos> lista_video;
    Adapter_Video_Seleccionar adater_V;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layaut = inflater.inflate(R.layout.fragment_nota, container, false);
        fab_foto = (FloatingActionButton) layaut.findViewById(R.id.img_camara);

        Seleccionar_foto();
        Tomar_foto();

        fab_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamaraPermiso();
                leerMemoria();
                escribirMemoria();
            }
        });

        fab_video = (FloatingActionButton) layaut.findViewById(R.id.img_video);

        Seleccionar_video();

        fab_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamaraVPermiso();
                abrirCamaraReproducirAudioPermiso();
                leerMemoria();
                escribirMemoria();
            }
        });

        return layaut;
    }


    private void Tomar_foto() {
        rcy_fotos_t = (RecyclerView) layaut.findViewById(R.id.rcy_lista_fotos_t);
        lista_fotos_t =new  ArrayList<>();
        rcy_fotos_t.setLayoutManager(new LinearLayoutManager(getContext()));
        adater_t=new Adapter_Foto_Tomar(lista_fotos_t);
    }

    private void Seleccionar_foto() {
        rcy_fotos = (RecyclerView) layaut.findViewById(R.id.rcy_lista_fotos);
        lista_fotos =new  ArrayList<>();
        rcy_fotos.setLayoutManager(new LinearLayoutManager(getContext()));
        adater=new Adapter_Foto_Seleccionar(lista_fotos);
    }

    void abrirCamaraPermiso() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
           mosatrarDialogoOpcionesCamara();
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    SOLICITUD_PERMISO_CAMERA);
        }
    }

    private void mosatrarDialogoOpcionesCamara() {
        final CharSequence [] Opciones={"Tomar foto","Elegir foto","Cancelar"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Elegir una opcion");
        builder.setItems(Opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(Opciones[i].equals("Tomar foto")){
                    abrirCamara_Tomar();
                }else if(Opciones[i].equals("Elegir foto")){
                    Intent intent= new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/");
                    startActivityForResult(intent.createChooser(intent,"Seleccione"),COD_SELECCION_CAMARA);

                }else{
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void abrirCamara_Tomar() {
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

            startActivityForResult(intent,COD_FOTO_CAMARA);
        }
    }

    void abrirCamaraVPermiso() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            mosatrarDialogoOpcionesCamaraV();
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    SOLICITUD_PERMISO_CAMERA);
        }
    }

    void abrirCamaraReproducirAudioPermiso() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    SOLICITUD_PERMISO_RECORD_AUDIO);
            //Toast.makeText(getContext(),"Permiso audio",Toast.LENGTH_SHORT).show();
        }
    }

    private void mosatrarDialogoOpcionesCamaraV() {
        final CharSequence [] Opciones={"Grabar video","Elegir video","Cancelar"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Elegir una opcion");
        builder.setItems(Opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(Opciones[i].equals("Grabar video")){
                    Toast.makeText(getContext(),"Grabar Video",Toast.LENGTH_SHORT).show();
                }else if(Opciones[i].equals("Elegir video")){

                    Intent intent= new Intent(Intent.ACTION_PICK,
                            MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("video/");
                    startActivityForResult(intent.createChooser(intent,"Seleccione"),COD_SELECCION_CAMARA_V);

                }else{
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void Seleccionar_video() {
        rcy_video = (RecyclerView) layaut.findViewById(R.id.rcy_lista_videos);
        lista_video =new  ArrayList<>();
        rcy_video.setLayoutManager(new LinearLayoutManager(getContext()));
        adater_V=new Adapter_Video_Seleccionar(lista_video);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case COD_SELECCION_CAMARA:
                /*Uri miPath=data.getData();
                llenar_lista_fotos(miPath);
                rcy_fotos.setAdapter(adater);*/
                break;
            case COD_FOTO_CAMARA:
                MediaScannerConnection.scanFile(getContext(), new String[]{path}, null,
                        new MediaScannerConnection.OnScanCompletedListener(){
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Log.i("Path",""+path);
                            }
                        });
               /* bitmap= BitmapFactory.decodeFile(path);
                llenar_lista_fotosT(bitmap);
                rcy_fotos_t.setAdapter(adater_t);*/
                break;
            case COD_SELECCION_CAMARA_V:
                Uri miPathV=data.getData();
                llenar_lista_video(miPathV);
                rcy_video.setAdapter(adater_V);
                break;

        }
    }

    private void llenar_lista_video(Uri vid) {
        lista_video.add(new Videos("                  ",vid));
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

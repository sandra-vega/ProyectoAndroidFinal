package com.example.sandra.mi_nota;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.sandra.mi_nota.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class Fragment_Video extends Fragment {
        Button fab_video;
        private VideoView viw_Video;

    private static final String CARPETA_PRINCIPAL="misVideosApp";
    private static final String CARPETA_VIDEO = "video";
    private static final String DIRECCTORIO_VIDEO = CARPETA_PRINCIPAL + CARPETA_VIDEO;
    private String path;
    File fileVideo;
    Bitmap bitmap;

    private static final int COD_SELECCION_VIDEO =10 ;
    private static final int COD_VIDEO=40;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_video, container, false);
        fab_video = view.findViewById(R.id.btn_video_add);
        viw_Video = view.findViewById(R.id.viw_video_lista);

        fab_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mosatrarDialogoOpcionesCamaraV();
            }
        });
        return view;
    }
    private void mosatrarDialogoOpcionesCamaraV() {
        final CharSequence [] Opciones={"Grabar video","Elegir video","Cancelar"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Elegir una opcion");
        builder.setItems(Opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(Opciones[i].equals("Grabar video")){
                    abrirCamara_Tomar_Video();
                }else if(Opciones[i].equals("Elegir video")){

                    Intent intent = new Intent();
                    intent.setType("video/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(
                            Intent.createChooser(intent, "Seleccione un video"),
                            COD_SELECCION_VIDEO);
                   //abrirCamara_Seleccionar_Video();


                }else{
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void abrirCamara_Seleccionar_Video() {
        File miFile = new File(Environment.getExternalStorageDirectory(),DIRECCTORIO_VIDEO);
        boolean isCreada= miFile.exists();

        if(!isCreada){
            isCreada=miFile.mkdir();
        }if(isCreada){
            Long consecutivo= System.currentTimeMillis()/1000;
            String nombre = consecutivo.toString()+".mp4";

            path= Environment.getExternalStorageDirectory()+File.separator+DIRECCTORIO_VIDEO
                    +File.separator+nombre;
            fileVideo= new File(path);

            Intent intent = new Intent();
            intent.setType("video/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            /*startActivityForResult(
                    Intent.createChooser(intent, "Seleccione un video"),
                    COD_SELECCION_VIDEO);*/

            intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(fileVideo));

            startActivityForResult(intent,COD_VIDEO);
        }
    }

    private void abrirCamara_Tomar_Video() {
        File miFile = new File(Environment.getExternalStorageDirectory(),DIRECCTORIO_VIDEO);
        boolean isCreada= miFile.exists();

        if(!isCreada){
            isCreada=miFile.mkdir();
        }if(isCreada){
            Long consecutivo= System.currentTimeMillis()/1000;
            String nombre = consecutivo.toString()+".mp4";

            path= Environment.getExternalStorageDirectory()+File.separator+DIRECCTORIO_VIDEO
                    +File.separator+nombre;
            fileVideo= new File(path);

            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(fileVideo));

            startActivityForResult(intent,COD_VIDEO);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case COD_SELECCION_VIDEO:
                Uri miPathV=data.getData();
                viw_Video.setVideoPath(""+miPathV);
                viw_Video.setMinimumHeight(480);
                viw_Video.setMinimumWidth(800);
                viw_Video.setMediaController(new MediaController(getActivity()));
                viw_Video.requestFocus();
                viw_Video.start();

                break;
            case COD_VIDEO:
                MediaScannerConnection.scanFile(getContext(), new String[]{path}, null,
                        new MediaScannerConnection.OnScanCompletedListener(){
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Log.i("Path",""+path);
                            }
                        });
                viw_Video.setVideoPath(path);
                viw_Video.setMinimumHeight(480);
                viw_Video.setMinimumWidth(800);
                viw_Video.setMediaController(new MediaController(getActivity()));
                viw_Video.requestFocus();
                viw_Video.start();
                break;
            }
        }
    }

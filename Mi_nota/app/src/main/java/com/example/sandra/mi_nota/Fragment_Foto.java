package com.example.sandra.mi_nota;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Fragment_Foto extends Fragment {
    private static final int COD_SELECCION_CAMARA =10 ;
    private static final int COD_FOTO_CAMARA=40;

    /*Veriables para tomar fotos */
    private static final String CARPETA_PRINCIPAL="misImagenesApp";
    private static final String CARPETA_IMAGEN = "imagenes";
    private static final String DIRECCTORIO_IMAGEN = CARPETA_PRINCIPAL + CARPETA_IMAGEN;
    private String path;
    File fileImagen;
    Bitmap bitmap;

    ImageView seleccion_foto;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foto, container, false);

        seleccion_foto = view.findViewById(R.id.img_fotos_lista);

        seleccion_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mosatrarDialogoOpcionesCamara();
                //Toast.makeText(getActivity(),"hola",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void mosatrarDialogoOpcionesCamara() {
        final CharSequence [] Opciones={"Tomar foto","Elegir foto"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Elegir una opcion");
        builder.setItems(Opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(Opciones[i].equals("Tomar foto")){
                    abrirCamara_Tomar();
                }else if(Opciones[i].equals("Elegir foto")){

                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(
                            Intent.createChooser(intent, "Seleccione una imagen"),
                            COD_SELECCION_CAMARA);
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImageUri = null;
        Uri selectedImage;
        String filePath = null;

        switch (requestCode){
            case COD_SELECCION_CAMARA:
                if (resultCode == Activity.RESULT_OK) {
                    selectedImage = data.getData();
                    String selectedPath=selectedImage.getPath();
                    if (requestCode == COD_SELECCION_CAMARA) {

                        if (selectedPath != null) {
                            InputStream imageStream = null;
                            try {
                               imageStream = getContext().getContentResolver().openInputStream(
                                        selectedImage);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            // Transformamos la URI de la imagen a inputStream y este a un Bitmap
                            Bitmap bmp = BitmapFactory.decodeStream(imageStream);
                            // Ponemos nuestro bitmap en un ImageView que tengamos en la vista
                            seleccion_foto.setImageBitmap(bmp);
                        }
                    }
                }
                break;
            case COD_FOTO_CAMARA:
                MediaScannerConnection.scanFile(getContext(), new String[]{path}, null,
                        new MediaScannerConnection.OnScanCompletedListener(){
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Log.i("Path",""+path);
                            }
                        });
                bitmap= BitmapFactory.decodeFile(path);
                seleccion_foto.setImageBitmap(bitmap);
                break;
        }
    }
}

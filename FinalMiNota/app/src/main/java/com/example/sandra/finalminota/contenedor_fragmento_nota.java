package com.example.sandra.finalminota;

import android.content.pm.PackageManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class contenedor_fragmento_nota extends AppCompatActivity {
    private static final int SOLICITUD_PERMISO_CAMERA = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor_fragmento_nota);

        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        Fragment_Nota fl=new Fragment_Nota();
        ft.add(R.id.frag_contenedor_nota,fl);
        ft.commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case SOLICITUD_PERMISO_CAMERA: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this,"acción de camara", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(this, "Sin el permiso, no puedo realizar la " +
                            "acción de camara", Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }

    }
}

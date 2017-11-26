package com.example.sandra.mi_nota;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class contenedor_fragment_foto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor_fragment_foto);
        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        Fragment_Foto fl=new Fragment_Foto();
        ft.add(R.id.frag_contenedor_foto_s,fl);
        ft.commit();




    }
}

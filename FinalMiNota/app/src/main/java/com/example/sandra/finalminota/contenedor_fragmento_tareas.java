package com.example.sandra.finalminota;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class contenedor_fragmento_tareas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor_fragmento_tareas);
        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        Fragment_Tarea fl=new Fragment_Tarea();
        ft.add(R.id.frag_contenedor_tarea,fl);
        ft.commit();
    }
}

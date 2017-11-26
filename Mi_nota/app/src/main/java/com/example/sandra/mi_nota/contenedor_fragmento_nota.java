package com.example.sandra.mi_nota;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class contenedor_fragmento_nota extends AppCompatActivity {
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

}

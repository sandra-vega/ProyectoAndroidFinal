package com.example.sandra.mi_nota;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class contenedor_fragment_video extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor_fragment_video);
        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        Fragment_Video fl=new Fragment_Video();
        ft.add(R.id.frag_contenedor_video,fl);
        ft.commit();
    }
}

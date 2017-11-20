package com.example.sandra.finalminota;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        Fragment_Lista fl=new Fragment_Lista();
        ft.add(R.id.frag_lista,fl);
        ft.commit();

        final FloatingActionButton fab_add = (FloatingActionButton) findViewById(R.id.fab_add);
        FloatingActionButton fab_nota = (FloatingActionButton) findViewById(R.id.fab_nota_add);
        FloatingActionButton fab_tarea = (FloatingActionButton) findViewById(R.id.fab_tarea_add);
        final LinearLayout lnl_nota=(LinearLayout) findViewById(R.id.lnl_nota_add);
        final LinearLayout lnl_tarea=(LinearLayout) findViewById(R.id.lnl_tarea_add);
        final Animation show_Button = AnimationUtils.loadAnimation(MainActivity.this, R.anim.show_button);
        final Animation hide_Button = AnimationUtils.loadAnimation(MainActivity.this, R.anim.hide_button);
        final Animation show_layout = AnimationUtils.loadAnimation(MainActivity.this, R.anim.show_layout);
        final Animation hide_layout = AnimationUtils.loadAnimation(MainActivity.this, R.anim.hide_layout);


        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lnl_nota.getVisibility()== View.VISIBLE && lnl_tarea.getVisibility()== View.VISIBLE ){
                    lnl_nota.setVisibility(View.GONE);
                    lnl_tarea.setVisibility(View.GONE);
                    lnl_nota.startAnimation(hide_layout);
                    lnl_tarea.startAnimation(hide_layout);
                    fab_add.startAnimation(hide_Button);
                }else{
                    lnl_nota.setVisibility(View.VISIBLE);
                    lnl_tarea.setVisibility(View.VISIBLE);
                    lnl_nota.startAnimation(show_layout);
                    lnl_tarea.startAnimation(show_layout);
                    fab_add.startAnimation(show_Button);
                }
            }
        });
        fab_nota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lnl_nota.setVisibility(View.GONE);
                lnl_tarea.setVisibility(View.GONE);
                lnl_nota.startAnimation(hide_layout);
                lnl_tarea.startAnimation(hide_layout);
                fab_add.startAnimation(hide_Button);

                Intent i =new Intent(getBaseContext(), contenedor_fragmento_nota.class);
                startActivity(i);
            }
        });
        fab_tarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lnl_nota.setVisibility(View.GONE);
                lnl_tarea.setVisibility(View.GONE);
                lnl_nota.startAnimation(hide_layout);
                lnl_tarea.startAnimation(hide_layout);
                fab_add.startAnimation(hide_Button);

                Intent i =new Intent(getBaseContext(), contenedor_fragmento_tareas.class);
                startActivity(i);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
         if (id == R.id.itm_buscar) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

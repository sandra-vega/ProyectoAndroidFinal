package com.example.sandra.mi_nota;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int SOLICITUD_PERMISO_CAMERA = 0;
    private static final int SOLICITUD_PERMISO_ESCRIBIR_MEMORIA = 1;
    private static final int SOLICITUD_PERMISO_LEER_MEMORIA = 2;
    private static final int SOLICITUD_PERMISO_RECORD_AUDIO=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.itm_buscar) {
            return true;
        }else if(id == R.id.itm_camara){
            abrirCamaraPermiso();
            leerMemoria();
            escribirMemoria();
        }else if(id == R.id.itm_video){
            abrirCamaraVideoPermiso();
            leerMemoria();
            escribirMemoria();
        }

        return super.onOptionsItemSelected(item);
    }

    private void abrirCamaraVideoPermiso() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            Intent j =new Intent(getBaseContext(), contenedor_fragment_video.class);
            startActivity(j);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    SOLICITUD_PERMISO_CAMERA);
        }
    }

    void abrirCamaraPermiso() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            Intent j =new Intent(getBaseContext(), contenedor_fragment_foto.class);
            startActivity(j);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    SOLICITUD_PERMISO_CAMERA);
        }
    }

    void leerMemoria() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    SOLICITUD_PERMISO_LEER_MEMORIA);
        }
    }

    void escribirMemoria() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission. WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    SOLICITUD_PERMISO_ESCRIBIR_MEMORIA);
        }
    }

    void audio(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    SOLICITUD_PERMISO_RECORD_AUDIO);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case SOLICITUD_PERMISO_CAMERA: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent j =new Intent(getBaseContext(), contenedor_fragment_foto.class);
                    startActivity(j);
                } else {
                    Toast.makeText(this, "Sin el permiso, no puedo realizar la " +
                            "acción de camara", Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_tarea) {
            // Handle the camera action
        } else if (id == R.id.nav_nota) {

        } else if (id == R.id.nav_foto) {

        } else if (id == R.id.nav_video) {

        } else if (id == R.id.nav_compartir) {

        } else if(id == R.id.nav_configurar) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

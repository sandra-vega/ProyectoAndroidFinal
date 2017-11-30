package com.example.sandra.mi_nota;

import android.content.Context;

import java.util.Vector;

import Modelo.Notas;

/**
 * Created by alcohonsilver on 13/09/17.
 */

public class InfoGlobal {
    private Vector<Notas> vectorNotas;

    public Vector<Notas> getVectorNota() {
        return vectorNotas;
    }

    public Adapter_Nota getAdaptador() {
        return adaptador;
    }

    private Adapter_Nota adaptador;

    private static InfoGlobal INSTANCIA = new InfoGlobal();
    private InfoGlobal() {}

    public static InfoGlobal getInstancia(){
        return INSTANCIA;
    }




}

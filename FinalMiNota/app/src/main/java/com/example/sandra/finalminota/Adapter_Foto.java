package com.example.sandra.finalminota;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

import Modelo.Fotos;

/**
 * Created by sandra on 19/11/2017.
 */

public class Adapter_Foto extends RecyclerView.Adapter<Adapter_Foto .ViewHolder>{

    ArrayList<Fotos> listaFotos;

    public Adapter_Foto(ArrayList<Fotos> listaFotos) {
        this.listaFotos = listaFotos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foto,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.descripcion_foto.setText(listaFotos.get(position).getDescripcion());
        holder.foto_lista.setImageURI(listaFotos.get(position).getImagenId());
    }

    @Override
    public int getItemCount() {
        return listaFotos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText descripcion_foto;
        ImageView foto_lista;
        public ViewHolder(View itemView) {
            super(itemView);
            descripcion_foto= (EditText) itemView.findViewById(R.id.txt_descripcion_fotos);
            foto_lista= (ImageView) itemView.findViewById(R.id.img_fotos_lista);
        }

    }
}

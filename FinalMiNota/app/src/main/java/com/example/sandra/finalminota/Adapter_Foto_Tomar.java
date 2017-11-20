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

public class Adapter_Foto_Tomar extends RecyclerView.Adapter<Adapter_Foto_Tomar.ViewHolder>{

    ArrayList<Fotos> listaFotosT;

    public Adapter_Foto_Tomar(ArrayList<Fotos> listaFotosT) {
        this.listaFotosT = listaFotosT;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tomar_foto,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.descripcion_foto_t.setText(listaFotosT.get(position).getDescripcion());
        holder.foto_lista_t.setImageBitmap(listaFotosT.get(position).getImagenIdT());

    }

    @Override
    public int getItemCount() {
        return listaFotosT.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText descripcion_foto_t;
        ImageView foto_lista_t;
        public ViewHolder(View itemView) {
            super(itemView);
            descripcion_foto_t= (EditText) itemView.findViewById(R.id.txt_descripcion_fotos_t);
            foto_lista_t= (ImageView) itemView.findViewById(R.id.img_fotos_lista_t);
        }

    }
}

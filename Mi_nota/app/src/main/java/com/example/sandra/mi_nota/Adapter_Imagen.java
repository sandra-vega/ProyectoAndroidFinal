package com.example.sandra.mi_nota;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.ArrayList;

import Modelo.Imagen;
import Modelo.Notas;


/**
 * Created by sandra on 26/11/2017.
 */

public class Adapter_Imagen extends RecyclerView.Adapter<Adapter_Imagen.ViewHolder>{

    ArrayList<Imagen>listaImagen;


    public Adapter_Imagen(ArrayList<Imagen> listaNotas) {
        this.listaImagen = listaImagen;
    }

    private View.OnLongClickListener onLongClickListener;
    private View.OnClickListener onClickListener;


    public void setOnItemClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
    public void setOnItemLongClickListener(View.OnLongClickListener
                                                   onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_imagen,null,false);
        view.setOnClickListener(onClickListener);
        view.setOnLongClickListener(onLongClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.descripcion.setText(listaImagen.get(position).getDescripcion());
        holder.fecha_Imagen.setText(listaImagen.get(position).getFecha_Imagen());
        holder.foto.setImageBitmap(listaImagen.get(position).getImagenIdT());
    }

    @Override
    public int getItemCount() {
        return listaImagen.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView descripcion;
        TextView fecha_Imagen;
        ImageView foto;
        public ViewHolder(View itemView) {
            super(itemView);
            descripcion = (TextView) itemView.findViewById(R.id.txt_descripcion_imagen);
            fecha_Imagen = (TextView) itemView.findViewById(R.id.txt_fecha_imagen);
            foto = (ImageView) itemView.findViewById(R.id.img_foto_mostrar);
        }
    }
}

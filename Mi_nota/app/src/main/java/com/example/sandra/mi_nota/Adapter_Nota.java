package com.example.sandra.mi_nota;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import Modelo.Notas;


/**
 * Created by sandra on 26/11/2017.
 */

public class Adapter_Nota extends RecyclerView.Adapter<Adapter_Nota.ViewHolder>{

    ArrayList<Notas>listaNotas;


    public Adapter_Nota(ArrayList<Notas> listaNotas) {
        this.listaNotas = listaNotas;
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
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_notas,null,false);
        view.setOnClickListener(onClickListener);
        view.setOnLongClickListener(onLongClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titulo.setText(listaNotas.get(position).getTitulo_nota());
        holder.descripcion.setText(listaNotas.get(position).getDescripcion_nota());
        holder.fecha.setText(listaNotas.get(position).getFecha_nota());
    }

    @Override
    public int getItemCount() {
        return listaNotas.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView descripcion;
        TextView fecha;
        public ViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.txt_titulo_nota);
            descripcion = (TextView) itemView.findViewById(R.id.txt_descripcion_nota);
            fecha = (TextView) itemView.findViewById(R.id.txt_fecha_nota);
        }
    }
}

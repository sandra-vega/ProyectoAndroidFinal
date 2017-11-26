package com.example.sandra.finalminota;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

import Modelo.Fotos;
import Modelo.Videos;

/**
 * Created by sandra on 19/11/2017.
 */

public class Adapter_Video_Seleccionar extends RecyclerView.Adapter<Adapter_Video_Seleccionar.ViewHolder>{

    ArrayList<Videos> listaVideo;

    public Adapter_Video_Seleccionar(ArrayList<Videos> listaVideo) {
        this.listaVideo = listaVideo;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seleccionar_video,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.descripcion_video.setText(listaVideo.get(position).getDescripcion());
        holder.video_lista.setVideoURI(listaVideo.get(position).getVideoId());

    }

    @Override
    public int getItemCount() {
        return listaVideo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText descripcion_video;
        VideoView video_lista;
        public ViewHolder(View itemView) {
            super(itemView);
            descripcion_video= (EditText) itemView.findViewById(R.id.txt_descripcion_video);
            video_lista= (VideoView) itemView.findViewById(R.id.img_video_lista);
        }

    }
}

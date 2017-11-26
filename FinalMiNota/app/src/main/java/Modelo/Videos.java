package Modelo;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Created by sandra on 22/11/2017.
 */

public class Videos {
    private String descripcion;
    private Uri VideoId;
    private Bitmap imagenIdT;

    public Videos(String descripcion, Uri videoId) {
        this.descripcion = descripcion;
        VideoId = videoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Uri getVideoId() {
        return VideoId;
    }

    public void setVideoId(Uri videoId) {
        VideoId = videoId;
    }
}

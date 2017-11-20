package Modelo;

import android.net.Uri;

/**
 * Created by sandra on 19/11/2017.
 */

public class Fotos {
    private String descripcion;
    private Uri imagenId;

    public Fotos(String descripcion, Uri imagenId) {
        this.descripcion = descripcion;
        this.imagenId = imagenId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Uri getImagenId() {
        return imagenId;
    }

    public void setImagenId(Uri imagenId) {
        this.imagenId = imagenId;
    }
}

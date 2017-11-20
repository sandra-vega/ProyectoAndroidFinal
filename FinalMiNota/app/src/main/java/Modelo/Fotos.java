package Modelo;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Created by sandra on 19/11/2017.
 */

public class Fotos {
    private String descripcion;
    private Uri imagenId;
    private Bitmap imagenIdT;

    public Fotos(String descripcion, Uri imagenId) {
        this.descripcion = descripcion;
        this.imagenId = imagenId;
    }

    public Fotos(String descripcion, Bitmap imagenIdT) {
        this.descripcion = descripcion;
        this.imagenIdT = imagenIdT;
    }

    public Bitmap getImagenIdT() {
        return imagenIdT;
    }

    public void setImagenIdT(Bitmap imagenIdT) {
        this.imagenIdT = imagenIdT;
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

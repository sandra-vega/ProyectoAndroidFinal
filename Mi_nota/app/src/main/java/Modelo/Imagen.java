package Modelo;

import android.graphics.Bitmap;

/**
 * Created by sandra on 30/11/2017.
 */

public class Imagen {
    private String descripcion;
    private Bitmap imagenIdT;
    private byte[] imagenSQL;
    private long  id;
    private String fecha_Imagen;

    public Imagen(String descripcion, byte[] imagenSQL, long id, String fecha_Imagen) {
        this.descripcion = descripcion;
        this.imagenSQL = imagenSQL;
        this.id = id;
        this.fecha_Imagen = fecha_Imagen;
    }

    public Imagen(String descripcion, byte[] imagenSQL, String fecha_Imagen) {
        this.descripcion = descripcion;
        this.imagenSQL = imagenSQL;
        this.fecha_Imagen = fecha_Imagen;
    }

    public Imagen() {
    }

    public byte[] getImagenSQL() {
        return imagenSQL;
    }

    public void setImagenSQL(byte[] imagenSQL) {
        this.imagenSQL = imagenSQL;
    }

    public Bitmap getImagenIdT() {
        return imagenIdT;
    }

    public void setImagenIdT(Bitmap imagenIdT) {
        this.imagenIdT = imagenIdT;
    }

    public String getFecha_Imagen() {
        return fecha_Imagen;
    }

    public void setFecha_Imagen(String fecha_Imagen) {
        this.fecha_Imagen = fecha_Imagen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

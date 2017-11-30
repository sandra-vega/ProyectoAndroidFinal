package Modelo;

/**
 * Created by sandra on 26/11/2017.
 */

public class Notas {
    private long  id;
    private String titulo_nota;
    private String descripcion_nota;
    private String fecha_nota;

    public Notas(long id, String titulo_nota, String descripcion_nota, String fecha_nota) {
        this.id = id;
        this.titulo_nota = titulo_nota;
        this.descripcion_nota = descripcion_nota;
        this.fecha_nota = fecha_nota;
    }

    public Notas(String titulo_nota, String descripcion_nota, String fecha_nota) {
        this.titulo_nota = titulo_nota;
        this.descripcion_nota = descripcion_nota;
        this.fecha_nota = fecha_nota;
    }

    public Notas() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo_nota() {
        return titulo_nota;
    }

    public void setTitulo_nota(String titulo_nota) {
        this.titulo_nota = titulo_nota;
    }

    public String getDescripcion_nota() {
        return descripcion_nota;
    }

    public void setDescripcion_nota(String descripcion_nota) {
        this.descripcion_nota = descripcion_nota;
    }

    public String getFecha_nota() {
        return fecha_nota;
    }

    public void setFecha_nota(String fecha_nota) {
        this.fecha_nota = fecha_nota;
    }

    @Override
    public String toString() {
        return this.titulo_nota + "\n" + this.descripcion_nota + "\n" + this.fecha_nota;
    }
}

package Modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by sandra on 30/11/2017.
 */

public class DAOImagen {

    Context _contexto;
    SQLiteDatabase _midb;
    public ArrayList<Imagen> lstC ;


    public DAOImagen(Context contexto){
        _contexto = contexto;
        _midb =
                new MiSQLiteOpenHelper(contexto)
                        .getWritableDatabase();


    }


    public long insert(Imagen c){
        ContentValues cv = new ContentValues();

        cv.put(MiSQLiteOpenHelper.COLUMNS_NAME_TABLE_IMAGEN[1], c.getImagenSQL());
        cv.put(MiSQLiteOpenHelper.COLUMNS_NAME_TABLE_IMAGEN[2], c.getDescripcion());
        cv.put(MiSQLiteOpenHelper.COLUMNS_NAME_TABLE_IMAGEN[3], c.getFecha_Imagen());


        return  _midb.insert(MiSQLiteOpenHelper.TABLE_IMAGEN_NAME,null,cv);
    }

    public long update(Imagen c){
        ContentValues cv = new ContentValues();

        cv.put(MiSQLiteOpenHelper.COLUMNS_NAME_TABLE_IMAGEN[1], c.getImagenSQL());
        cv.put(MiSQLiteOpenHelper.COLUMNS_NAME_TABLE_IMAGEN[2], c.getDescripcion());

        return _midb.update(MiSQLiteOpenHelper.TABLE_IMAGEN_NAME, cv, "_idn=?", new String[] { String.valueOf( c.getId())});
    }

    public long delete(final long id){
        return _midb.delete(MiSQLiteOpenHelper.TABLE_IMAGEN_NAME,"_idn='"+id+"'",null);
    }

    public ArrayList<Imagen> getAll(){

        lstC = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + MiSQLiteOpenHelper.TABLE_IMAGEN_NAME;
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor cursor = db.rawQuery(selectQuery, null);
        Bitmap bitmap = null;
        if (cursor.moveToFirst()) {
            do {


                Imagen c = new Imagen();

                c.setId(cursor.getLong(0));
                byte[] blob = cursor.getBlob(1);
                ByteArrayInputStream bais = new ByteArrayInputStream(blob);
                bitmap = BitmapFactory.decodeStream(bais);
                c.setImagenIdT(bitmap);
                c.setDescripcion(cursor.getString (2));
                c.setFecha_Imagen(cursor.getString (3));
                lstC.add(c);
            } while (cursor.moveToNext());
        }


        return lstC;
    }

   /* //BUSQUEDA ORDENADOS POR EL ID;
    public ArrayList<Contacto> obtenercontacto(String id) {

        ArrayList<Contacto> studentsArrayList = new ArrayList<Contacto>();
        String selectQuery = "select * from contactos where _id='"+id+"'";
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {

            do {

                Contacto students = new Contacto();
                students.nombre = c.getString(c.getColumnIndex("nombre"));
                students.correo_electronico = c.getString(c.getColumnIndex("correo_electronico"));
                students.twitter = c.getString(c.getColumnIndex("twitter"));
                students.telefono = c.getString(c.getColumnIndex("telefono"));
                students.fecha_nacimiento = c.getString(c.getColumnIndex("fecha_nacimiento"));
                studentsArrayList.add(students);

            } while (c.moveToNext());

        }

        return studentsArrayList;

    }
    //BUSQUEDA ORDENADOS POR EL NOMBRE;
    public List<Contacto> buscarcontacto(String name) {

        List<Contacto> studentsArrayList = new ArrayList<Contacto>();
        String selectQuery = "SELECT  * FROM contactos WHERE nombre LIKE '"+name+"%'";
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {

            do {

                Contacto students = new Contacto();
                students.nombre = c.getString(c.getColumnIndex("nombre"));
                students.correo_electronico = c.getString(c.getColumnIndex("correo_electronico"));
                students.twitter = c.getString(c.getColumnIndex("twitter"));
                students.telefono = c.getString(c.getColumnIndex("telefono"));
                students.fecha_nacimiento = c.getString(c.getColumnIndex("fecha_nacimiento"));
                studentsArrayList.add(students);

            } while (c.moveToNext());

        }

        return studentsArrayList;

    }*/

}

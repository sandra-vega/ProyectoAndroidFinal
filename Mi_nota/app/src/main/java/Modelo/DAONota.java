package Modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.sandra.mi_nota.Adapter_Nota;
import com.example.sandra.mi_nota.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandra on 11/10/2017.
 */

public class DAONota {
    Context _contexto;
    SQLiteDatabase _midb;
    public ArrayList<Notas> lstC ;


    public DAONota(Context contexto){
        _contexto = contexto;
        _midb =
                new MiSQLiteOpenHelper(contexto)
                        .getWritableDatabase();


    }


    public long insert(Notas c){
        ContentValues cv = new ContentValues();

        cv.put(MiSQLiteOpenHelper.COLUMNS_NAME_TABLE_NOTAS[1], c.getTitulo_nota());
        cv.put(MiSQLiteOpenHelper.COLUMNS_NAME_TABLE_NOTAS[2], c.getDescripcion_nota());
        cv.put(MiSQLiteOpenHelper.COLUMNS_NAME_TABLE_NOTAS[3], c.getFecha_nota());

        return  _midb.insert(MiSQLiteOpenHelper.TABLE_NOTAS_NAME,null,cv);
    }

    public long update(Notas c){
        ContentValues cv = new ContentValues();

        cv.put(MiSQLiteOpenHelper.COLUMNS_NAME_TABLE_NOTAS[1], c.getTitulo_nota());
        cv.put(MiSQLiteOpenHelper.COLUMNS_NAME_TABLE_NOTAS[2], c.getDescripcion_nota());
        cv.put(MiSQLiteOpenHelper.COLUMNS_NAME_TABLE_NOTAS[3], c.getFecha_nota());

        return _midb.update(MiSQLiteOpenHelper.TABLE_NOTAS_NAME, cv, "_idn=?", new String[] { String.valueOf( c.getId())});
    }

    public long delete(final long id){
        return _midb.delete(MiSQLiteOpenHelper.TABLE_NOTAS_NAME,"_idn='"+id+"'",null);
    }

    public ArrayList<Notas> getAll(){

        lstC = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + MiSQLiteOpenHelper.TABLE_NOTAS_NAME;
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                Notas c = new Notas();

                c.setId(cursor.getLong(0));
                c.setTitulo_nota (cursor.getString (1));
                c.setDescripcion_nota(cursor.getString (2));
                c.setFecha_nota(cursor.getString (3));
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

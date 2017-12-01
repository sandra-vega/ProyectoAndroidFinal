package Modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sandra on 11/10/2017.
 */

public class MiSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String  TABLE_NOTAS_NAME="notas";
    public static final String[] COLUMNS_NAME_TABLE_NOTAS = {"_idn", "titulo","descripcion"
            , "fecha_creacion"};

    private  final String SCRIPT_TABLE_NOTAS="create table " + TABLE_NOTAS_NAME + "(" +
            COLUMNS_NAME_TABLE_NOTAS[0] + " integer primary key autoincrement, " +
            COLUMNS_NAME_TABLE_NOTAS[1] + " varchar(20) not null, " +
            COLUMNS_NAME_TABLE_NOTAS[2] + " varchar(100) not null, " +
            COLUMNS_NAME_TABLE_NOTAS[3] + " date null " +
            ");";
    public static final String  TABLE_TAREAS_NAME="tareas";
    public static final String[] COLUMNS_NAME_TABLE_TAREAS = {"_idt", "titulo","fecha_vencimiento",
            "repeticion","repetir_hasta","descipcion","fecha_aviso", "hora_aviso"};

    private  final String SCRIPT_TABLE_TAREAS="create table " + TABLE_TAREAS_NAME+ "(" +
            COLUMNS_NAME_TABLE_TAREAS[0] + " integer primary key autoincrement, " +
            COLUMNS_NAME_TABLE_TAREAS[1] + " varchar(20) not null, " +
            COLUMNS_NAME_TABLE_TAREAS[2] + " date null, " +
            COLUMNS_NAME_TABLE_TAREAS[3] + " varchar(20) not null, " +
            COLUMNS_NAME_TABLE_TAREAS[4] + " varchar(20) null, " +
            COLUMNS_NAME_TABLE_TAREAS[5] + " varchar(100) not null, " +
            COLUMNS_NAME_TABLE_TAREAS[6] + " date null, " +
            COLUMNS_NAME_TABLE_TAREAS[7] + " varchar(20) null " +
            ");";

    public static final String  TABLE_IMAGEN_NAME="imagen";
    public static final String[] COLUMNS_NAME_TABLE_IMAGEN = {"_idi", "foto","descripcion"
            , "fecha_creacion"};

    private  final String SCRIPT_TABLE_IMAGEN="create table " + TABLE_IMAGEN_NAME + "(" +
            COLUMNS_NAME_TABLE_IMAGEN[0] + " integer primary key autoincrement, " +
            COLUMNS_NAME_TABLE_IMAGEN[1] + " varchar(200) not null, " +
            COLUMNS_NAME_TABLE_IMAGEN[2] + " blob not null, " +
            COLUMNS_NAME_TABLE_IMAGEN[3] + " date null " +
            ");";
    public static final String  TABLE_VIDEO_NAME="video";
    public static final String[] COLUMNS_NAME_TABLE_VIDEO = {"_idf", "foto","descripcion"
            , "fecha_creacion"};

    private  final String SCRIPT_TABLE_VIDEO="create table " + TABLE_VIDEO_NAME + "(" +
            COLUMNS_NAME_TABLE_VIDEO[0] + " integer primary key autoincrement, " +
            COLUMNS_NAME_TABLE_VIDEO[1] + " varchar(200) not null, " +
            COLUMNS_NAME_TABLE_VIDEO[2] + " varchar(100) not null, " +
            COLUMNS_NAME_TABLE_VIDEO[3] + " date null " +
            ");";

    private static final int VERSION_DB = 1;

    public MiSQLiteOpenHelper(Context contexto){

        super(contexto, "miBD", null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SCRIPT_TABLE_NOTAS);
        sqLiteDatabase.execSQL(SCRIPT_TABLE_TAREAS);
        sqLiteDatabase.execSQL(SCRIPT_TABLE_IMAGEN);
        sqLiteDatabase.execSQL(SCRIPT_TABLE_VIDEO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

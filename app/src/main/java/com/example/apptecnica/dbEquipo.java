package com.example.apptecnica;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbEquipo {
    public static final String Tabla_Equipo = "Equipo";
    public static final String idEquipo = "idEquipo";
    public static final String NombreEquipo = "NombreEquipo";
    public static final String Codigo = "Codigo";
    public static final String Meger = "Meger";
    public static final String Tierra = "Tierra";


    public static final String Crear_Tabla_Equipo = "Create Table "+Tabla_Equipo+" ("+idEquipo+" Integer PRIMARY KEY AUTOINCREMENT , " +
            ""+NombreEquipo+" Text, "+Codigo+" Text, "+Meger+" double, "+Tierra+" double)";

    public static class ConexionSQliteHelper extends SQLiteOpenHelper {
        public ConexionSQliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Crear_Tabla_Equipo);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int VersionAntigua, int VersionNueva) {
            db.execSQL("DROP TABLE IF EXISTS Equipo");
        }
    }
}


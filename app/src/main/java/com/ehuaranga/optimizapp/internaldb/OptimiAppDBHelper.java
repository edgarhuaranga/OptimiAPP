package com.ehuaranga.optimizapp.internaldb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ehuaranga.optimizapp.model.Equipo;
import com.ehuaranga.optimizapp.model.Proyecto;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.ehuaranga.optimizapp.internaldb.OptimiAPPContract.DBRintisa.*;


public class OptimiAppDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "optimizapp.db";

    private static final String SQL_CREATE_TABLE_EQUIPOS =
            "CREATE TABLE " + TABLE_EQUIPOS+ " (" +
                    COLUMN_NAME_ID_EQUIPO+ " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_CODIGO_EQUIPO + " TEXT," +
                    COLUMN_NAME_TIPO_SUELO_EQUIPO + " TEXT," +
                    COLUMN_NAME_ANCHO_EFECTIVO_EQUIPO + " TEXT," +
                    COLUMN_NAME_VELOCIDAD_OPERACION_EQUIPO + " TEXT," +
                    COLUMN_NAME_PORC_PROCTOR_EQUIPO + " TEXT," +
                    COLUMN_NAME_NUMERO_PASADAS_EQUIPO + " TEXT," +
                    COLUMN_NAME_ANCHO_EQUIPO + " TEXT," +
                    COLUMN_NAME_LARGO_EQUIPO + " TEXT," +
                    COLUMN_NAME_DISTANCIA_LATERAL_EQUIPO + " TEXT," +
                    COLUMN_NAME_DISTANCIA_FRONTAL_EQUIPO + " TEXT," +
                    COLUMN_NAME_COSTO_EQUIPO + " TEXT)";

    private static final String SQL_CREATE_TABLE_PROYECTOS =
            "CREATE TABLE " + TABLE_PROYECTO + " ("+
                    COLUMN_NAME_ID_PROYECTO + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_TITULO_PROYECTO + " TEXT, " +
                    COLUMN_NAME_DESCRIPCION_PROYECTO + " TEXT, " +
                    COLUMN_NAME_ESPESOR_PROYECTO + " TEXT, " +
                    COLUMN_NAME_FACTOR_EFICIENCIA_PROYECTO + " TEXT, " +
                    COLUMN_NAME_ALTURA_PROYECTO + " TEXT, " +
                    COLUMN_NAME_AREA_PROYECTO + " TEXT, " +
                    COLUMN_NAME_MCC_PROYECTO + " TEXT, " +
                    COLUMN_NAME_HE_PROYECTO + " TEXT, " +
                    COLUMN_NAME_T_PROYECTO + " TEXT, " +
                    COLUMN_NAME_JORNADAS_PROYECTO + " TEXT, " +
                    COLUMN_NAME_PROCTOR_PROYECTO + " TEXT, " +
                    COLUMN_NAME_TIPO_SUELO_PROYECTO + " TEXT)";

    private static final String SQL_CREATE_TABLE_EQUIPOS_PROYECTO =
            "CREATE TABLE "+ TABLE_EQUIPOS_X_PROYECTO + " (" +
            COLUMN_NAME_ID_EQUIPOS_PROYECTO + " INTEGER PRIMARY KEY,"+
            COLUMN_NAME_IDEQUIPO + " INTEGER, " +
            COLUMN_NAME_PRECIO_EQUIPO + " TEXT, " +
            COLUMN_NAME_IDPROYECTO + " INTEGER)" ;

    public static final String SQL_DELETE_TABLE_EQUIPOS = "DROP TABLE IF EXISTS "+ TABLE_EQUIPOS;
    public static final String SQL_DELETE_TABLE_PROYECTOS = "DROP TABLE IF EXISTS "+TABLE_PROYECTO;
    public static final String SQL_DELETE_TABLE_EQUIPOS_PROYECTO = "DROP TABLE IF EXISTS "+ TABLE_EQUIPOS_X_PROYECTO;


    public OptimiAppDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_EQUIPOS);
        db.execSQL(SQL_CREATE_TABLE_PROYECTOS);
        db.execSQL(SQL_CREATE_TABLE_EQUIPOS_PROYECTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE_EQUIPOS);
        db.execSQL(SQL_DELETE_TABLE_PROYECTOS);
        db.execSQL(SQL_DELETE_TABLE_EQUIPOS_PROYECTO);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public ArrayList<Equipo> getAllEquipos(){
        ArrayList<Equipo> equipos = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_EQUIPOS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Equipo equipo = new Equipo();
                equipo.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ID_EQUIPO)));
                equipo.setCodigo(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CODIGO_EQUIPO)));
                equipo.setTipoSuelo(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TIPO_SUELO_EQUIPO)));
                equipo.setAnchoEfectivo(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ANCHO_EFECTIVO_EQUIPO))));
                equipo.setVelocidadOperacion(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_VELOCIDAD_OPERACION_EQUIPO))));
                equipo.setPorcentajeProctor(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_PORC_PROCTOR_EQUIPO))));
                equipo.setNumeroPasadas(Math.round(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_NUMERO_PASADAS_EQUIPO)))));
                equipo.setAncho(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ANCHO_EQUIPO))));
                equipo.setLargo(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LARGO_EQUIPO))));
                equipo.setDistanciaLateral(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DISTANCIA_LATERAL_EQUIPO))));
                equipo.setDistanciaFrontal(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DISTANCIA_FRONTAL_EQUIPO))));
                equipo.setCosto(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_COSTO_EQUIPO))));
                equipos.add(equipo);
            } while (cursor.moveToNext());
        }

        return equipos;
    }

    public ArrayList<Equipo> getAllEquiposAvailableFor(Proyecto proyecto){
        ArrayList<Equipo> equipos = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_EQUIPOS
                + " WHERE "+COLUMN_NAME_TIPO_SUELO_EQUIPO+ " = '"+proyecto.getTipoSuelo()+"'"
                + " AND "+COLUMN_NAME_PORC_PROCTOR_EQUIPO+ " = '"+proyecto.getProctor()
                +"' ORDER BY "+COLUMN_NAME_CODIGO_EQUIPO ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Equipo equipo = new Equipo();
                equipo.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ID_EQUIPO)));
                equipo.setCodigo(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CODIGO_EQUIPO)));
                equipo.setTipoSuelo(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TIPO_SUELO_EQUIPO)));
                equipo.setAnchoEfectivo(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ANCHO_EFECTIVO_EQUIPO))));
                equipo.setVelocidadOperacion(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_VELOCIDAD_OPERACION_EQUIPO))));
                equipo.setPorcentajeProctor(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_PORC_PROCTOR_EQUIPO))));
                equipo.setNumeroPasadas(Math.round(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_NUMERO_PASADAS_EQUIPO)))));
                equipo.setAncho(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ANCHO_EQUIPO))));
                equipo.setLargo(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LARGO_EQUIPO))));
                equipo.setDistanciaLateral(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DISTANCIA_LATERAL_EQUIPO))));
                equipo.setDistanciaFrontal(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DISTANCIA_FRONTAL_EQUIPO))));
                equipo.setCosto(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_COSTO_EQUIPO))));
                equipos.add(equipo);
            } while (cursor.moveToNext());
        }

        return equipos;

    }

    public float getCostoEquipoProyecto(Proyecto proyecto, Equipo equipo){
        String selectQuery = "SELECT  * FROM " + TABLE_EQUIPOS_X_PROYECTO + " WHERE "+ COLUMN_NAME_IDPROYECTO + " =  "+ proyecto.getId() + " AND " + COLUMN_NAME_IDEQUIPO + " = " + equipo.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            return Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_PRECIO_EQUIPO)));

        }

        return (float) 0.0;
    }

    public Equipo getEquipoById(int id){
        String selectQuery = "SELECT  * FROM " + TABLE_EQUIPOS + " WHERE "+ COLUMN_NAME_ID_EQUIPO + " =  "+ id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Equipo equipo = new Equipo();
                equipo.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ID_EQUIPO)));
                equipo.setCodigo(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CODIGO_EQUIPO)));
                equipo.setTipoSuelo(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TIPO_SUELO_EQUIPO)));
                equipo.setAnchoEfectivo(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ANCHO_EFECTIVO_EQUIPO))));
                equipo.setVelocidadOperacion(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_VELOCIDAD_OPERACION_EQUIPO))));
                equipo.setPorcentajeProctor(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_PORC_PROCTOR_EQUIPO))));
                equipo.setNumeroPasadas(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_NUMERO_PASADAS_EQUIPO))));
                equipo.setAncho(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ANCHO_EQUIPO))));
                equipo.setLargo(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LARGO_EQUIPO))));
                equipo.setDistanciaLateral(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DISTANCIA_LATERAL_EQUIPO))));
                equipo.setDistanciaFrontal(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DISTANCIA_FRONTAL_EQUIPO))));
                equipo.setCosto(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_COSTO_EQUIPO))));
                return equipo;
            } while (cursor.moveToNext());
        }

        return null;
    }

    public Proyecto getProyectoById(int id){
        ArrayList<Proyecto> proyectos = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PROYECTO + " WHERE "+ COLUMN_NAME_ID_PROYECTO + " =  "+ id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Proyecto proyecto = new Proyecto();
                proyecto.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ID_PROYECTO)));
                proyecto.setTitulo(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TITULO_PROYECTO)));
                proyecto.setDescripcion(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DESCRIPCION_PROYECTO)));
                proyecto.setEspesor(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ESPESOR_PROYECTO))));
                proyecto.setFactorEficiencia(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_FACTOR_EFICIENCIA_PROYECTO))));
                proyecto.setAltura(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ALTURA_PROYECTO))));
                proyecto.setArea(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_AREA_PROYECTO))));
                proyecto.setMcc(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_MCC_PROYECTO))));
                proyecto.setHe(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_HE_PROYECTO))));
                proyecto.setJornadas(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_JORNADAS_PROYECTO))));
                proyecto.setT(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_T_PROYECTO))));
                proyecto.setTipoSuelo(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TIPO_SUELO_PROYECTO)));
                proyecto.setProctor(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_PROCTOR_PROYECTO))));
                return proyecto;
            }while(cursor.moveToNext());
        }
        return null;
    }

    public ArrayList<Proyecto> getAllProyectos(){
        ArrayList<Proyecto> proyectos = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PROYECTO;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Proyecto proyecto = new Proyecto();
                proyecto.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ID_PROYECTO)));
                proyecto.setTitulo(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TITULO_PROYECTO)));
                proyecto.setDescripcion(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DESCRIPCION_PROYECTO)));
                proyecto.setEspesor(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ESPESOR_PROYECTO))));
                proyecto.setFactorEficiencia(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_FACTOR_EFICIENCIA_PROYECTO))));
                proyecto.setAltura(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ALTURA_PROYECTO))));
                proyecto.setArea(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_AREA_PROYECTO))));
                proyecto.setMcc(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_MCC_PROYECTO))));
                proyecto.setHe(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_HE_PROYECTO))));
                proyecto.setJornadas(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_JORNADAS_PROYECTO))));
                proyecto.setT(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_T_PROYECTO))));
                proyecto.setTipoSuelo(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TIPO_SUELO_PROYECTO)));
                proyecto.setTipoSuelo(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_PROCTOR_PROYECTO)));
                proyectos.add(proyecto);
            }while(cursor.moveToNext());
        }
        return proyectos;
    }

    public ArrayList<Equipo> getEquiposByProyecto(Proyecto proyecto){
        ArrayList<Equipo> equipos = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_EQUIPOS_X_PROYECTO + " WHERE " + COLUMN_NAME_IDPROYECTO + " = " + proyecto.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int idEquipo = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_IDEQUIPO));
                Equipo equipo = getEquipoById(idEquipo);
                equipo.setCosto(getCostoEquipoProyecto(proyecto, equipo));
                equipos.add(equipo);
            } while (cursor.moveToNext());
        }

        return equipos;
    }

    public void insertarEquipo(JSONObject jsonEquipo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        try {
            values.put(COLUMN_NAME_CODIGO_EQUIPO,jsonEquipo.getString(COLUMN_NAME_CODIGO_EQUIPO));
            values.put(COLUMN_NAME_TIPO_SUELO_EQUIPO,jsonEquipo.getString(COLUMN_NAME_TIPO_SUELO_EQUIPO));
            values.put(COLUMN_NAME_PORC_PROCTOR_EQUIPO,jsonEquipo.getString(COLUMN_NAME_PORC_PROCTOR_EQUIPO));
            values.put(COLUMN_NAME_ANCHO_EFECTIVO_EQUIPO,jsonEquipo.getString(COLUMN_NAME_ANCHO_EFECTIVO_EQUIPO));
            values.put(COLUMN_NAME_VELOCIDAD_OPERACION_EQUIPO,jsonEquipo.getString(COLUMN_NAME_VELOCIDAD_OPERACION_EQUIPO));
            values.put(COLUMN_NAME_NUMERO_PASADAS_EQUIPO,jsonEquipo.getString(COLUMN_NAME_NUMERO_PASADAS_EQUIPO));
            values.put(COLUMN_NAME_ANCHO_EQUIPO,jsonEquipo.getString(COLUMN_NAME_ANCHO_EQUIPO));
            values.put(COLUMN_NAME_LARGO_EQUIPO,jsonEquipo.getString(COLUMN_NAME_LARGO_EQUIPO));
            values.put(COLUMN_NAME_DISTANCIA_LATERAL_EQUIPO,jsonEquipo.getString(COLUMN_NAME_DISTANCIA_LATERAL_EQUIPO));
            values.put(COLUMN_NAME_DISTANCIA_FRONTAL_EQUIPO,jsonEquipo.getString(COLUMN_NAME_DISTANCIA_FRONTAL_EQUIPO));
            values.put(COLUMN_NAME_COSTO_EQUIPO,jsonEquipo.getString(COLUMN_NAME_COSTO_EQUIPO));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        db.insert(TABLE_EQUIPOS, null, values);
    }

    public void insertarProyecto(JSONObject jsonEquipo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
            values.put(COLUMN_NAME_TITULO_PROYECTO, jsonEquipo.getString(COLUMN_NAME_TITULO_PROYECTO));
            values.put(COLUMN_NAME_DESCRIPCION_PROYECTO, jsonEquipo.getString(COLUMN_NAME_DESCRIPCION_PROYECTO));
            values.put(COLUMN_NAME_ESPESOR_PROYECTO, jsonEquipo.getString(COLUMN_NAME_ESPESOR_PROYECTO));
            values.put(COLUMN_NAME_FACTOR_EFICIENCIA_PROYECTO, jsonEquipo.getString(COLUMN_NAME_FACTOR_EFICIENCIA_PROYECTO));
            values.put(COLUMN_NAME_ALTURA_PROYECTO, jsonEquipo.getString(COLUMN_NAME_ALTURA_PROYECTO));
            values.put(COLUMN_NAME_AREA_PROYECTO, jsonEquipo.getString(COLUMN_NAME_AREA_PROYECTO));
            values.put(COLUMN_NAME_MCC_PROYECTO, jsonEquipo.getString(COLUMN_NAME_MCC_PROYECTO));
            values.put(COLUMN_NAME_HE_PROYECTO, jsonEquipo.getString(COLUMN_NAME_HE_PROYECTO));
            values.put(COLUMN_NAME_T_PROYECTO, jsonEquipo.getString(COLUMN_NAME_T_PROYECTO));
            values.put(COLUMN_NAME_JORNADAS_PROYECTO, jsonEquipo.getString(COLUMN_NAME_JORNADAS_PROYECTO));
            values.put(COLUMN_NAME_TIPO_SUELO_PROYECTO, jsonEquipo.getString(COLUMN_NAME_TIPO_SUELO_PROYECTO));
            values.put(COLUMN_NAME_PROCTOR_PROYECTO, jsonEquipo.getString(COLUMN_NAME_PROCTOR_PROYECTO));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        db.insert(TABLE_PROYECTO, null, values);

    }

    public void insertarEquipoEnProyecto(Proyecto proyecto, Equipo equipo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_IDEQUIPO, equipo.getId());
        values.put(COLUMN_NAME_IDPROYECTO, proyecto.getId());
        values.put(COLUMN_NAME_PRECIO_EQUIPO, equipo.getCosto());
        db.insert(TABLE_EQUIPOS_X_PROYECTO, null, values);
    }

    public void updateEquipoEnProyecto(Proyecto proyecto, Equipo equipo, Float costo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_IDEQUIPO, equipo.getId());
        values.put(COLUMN_NAME_IDPROYECTO, proyecto.getId());
        values.put(COLUMN_NAME_PRECIO_EQUIPO, costo);
        db.update(TABLE_EQUIPOS_X_PROYECTO, values, COLUMN_NAME_IDEQUIPO+" = ? AND " + COLUMN_NAME_IDPROYECTO + " = ?", new String[]{equipo.getId()+"", proyecto.getId()+""});
    }


    public void deleteProyecto(Proyecto proyecto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete(TABLE_PROYECTO,  COLUMN_NAME_ID_PROYECTO+" = ?", new String[]{proyecto.getId()+""});
        db.delete(TABLE_EQUIPOS_X_PROYECTO, COLUMN_NAME_IDPROYECTO + " = ?", new String[]{proyecto.getId()+""});
    }
}

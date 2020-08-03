package com.ehuaranga.optimizapp.internaldb;

import android.provider.BaseColumns;

public class OptimiAPPContract {
    public OptimiAPPContract(){
    }

    public static class DBRintisa implements BaseColumns{
        public static final String TABLE_EQUIPOS = "equipos";
        public static final String TABLE_PROYECTO = "proyectos";
        public static final String TABLE_EQUIPOS_X_PROYECTO= "equipos_proyecto";

        public static final String COLUMN_NAME_ID_EQUIPO = "id";
        public static final String COLUMN_NAME_CODIGO_EQUIPO = "codigo";
        public static final String COLUMN_NAME_TIPO_SUELO_EQUIPO = "tipo_suelo";
        public static final String COLUMN_NAME_ANCHO_EFECTIVO_EQUIPO = "ancho_efectivo";
        public static final String COLUMN_NAME_VELOCIDAD_OPERACION_EQUIPO = "velocidad_operacion";
        public static final String COLUMN_NAME_PORC_PROCTOR_EQUIPO = "porcentaje_proctor";
        public static final String COLUMN_NAME_NUMERO_PASADAS_EQUIPO = "numero_pasadas";
        public static final String COLUMN_NAME_ANCHO_EQUIPO = "ancho";
        public static final String COLUMN_NAME_LARGO_EQUIPO = "largo";
        public static final String COLUMN_NAME_DISTANCIA_LATERAL_EQUIPO = "distancia_lateral";
        public static final String COLUMN_NAME_DISTANCIA_FRONTAL_EQUIPO = "distancia_frontal";
        public static final String COLUMN_NAME_COSTO_EQUIPO = "costo";

        public static final String COLUMN_NAME_ID_PROYECTO =  "id";
        public static final String COLUMN_NAME_TITULO_PROYECTO =  "titulo";
        public static final String COLUMN_NAME_DESCRIPCION_PROYECTO =  "descripcion";
        public static final String COLUMN_NAME_ESPESOR_PROYECTO =  "espesor";
        public static final String COLUMN_NAME_FACTOR_EFICIENCIA_PROYECTO =  "factorEficiencia";
        public static final String COLUMN_NAME_ALTURA_PROYECTO =  "altura";
        public static final String COLUMN_NAME_AREA_PROYECTO =  "area";
        public static final String COLUMN_NAME_MCC_PROYECTO =  "mcc";
        public static final String COLUMN_NAME_HE_PROYECTO =  "he";
        public static final String COLUMN_NAME_T_PROYECTO =  "t";
        public static final String COLUMN_NAME_JORNADAS_PROYECTO =  "jornadas";
        public static final String COLUMN_NAME_TIPO_SUELO_PROYECTO =  "tipoSuelo";
        public static final String COLUMN_NAME_PROCTOR_PROYECTO =  "proctor";


        public static final String COLUMN_NAME_ID_EQUIPOS_PROYECTO = "id";
        public static final String COLUMN_NAME_IDEQUIPO = "id_equipo";
        public static final String COLUMN_NAME_PRECIO_EQUIPO = "precio_equipo";
        public static final String COLUMN_NAME_IDPROYECTO = "id_proyecto";


    }
}

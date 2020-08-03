package com.ehuaranga.optimizapp.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import static com.ehuaranga.optimizapp.internaldb.OptimiAPPContract.DBRintisa.*;

public class Equipo {
    int id;
    String codigo;
    String tipoSuelo;// Granular, Semicohesivo, Cohesivo
    float porcentajeProctor; // 90 - 95%
    float anchoEfectivo; // Ancho del rodillo
    float velocidadOperacion; //km/h para mostrar pero almacenar metros/hora
    int numeroPasadas;
    float ancho;
    float largo;
    float distanciaLateral;
    float distanciaFrontal;
    float costo; //USD x hora
    float areaEquipoTrabajo; //calculado

    public Equipo() {
    }

    public Equipo(String codigo, String tipoSuelo, float porcentajeProctor, float ancho, float largo,
                  float distanciaFrontal, float  distanciaLateral, float areaEquipoTrabajo, float anchoEfectivo,
                    float velocidadOperacion, int numeroPasadas, int costo) {
        this.codigo = codigo;
        this.tipoSuelo = tipoSuelo;
        this.porcentajeProctor = porcentajeProctor;
        this.ancho = ancho;
        this.largo = largo;
        this.distanciaFrontal = distanciaFrontal;
        this.distanciaLateral = distanciaLateral;
        this.anchoEfectivo = anchoEfectivo;
        this.velocidadOperacion = velocidadOperacion;
        this.numeroPasadas = numeroPasadas;
        this.costo = costo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipoSuelo() {
        return tipoSuelo;
    }

    public void setTipoSuelo(String tipoSuelo) {
        this.tipoSuelo = tipoSuelo;
    }

    public float getPorcentajeProctor() {
        return porcentajeProctor;
    }

    public void setPorcentajeProctor(float porcentajeProctor) {
        this.porcentajeProctor = porcentajeProctor;
    }

    public float getAnchoEfectivo() {
        return anchoEfectivo;
    }

    public void setAnchoEfectivo(float anchoEfectivo) {
        this.anchoEfectivo = anchoEfectivo;
    }

    public float getVelocidadOperacion() {
        return velocidadOperacion;
    }

    public void setVelocidadOperacion(float velocidadOperacion) {
        this.velocidadOperacion = velocidadOperacion;
    }

    public int getNumeroPasadas() {
        return numeroPasadas;
    }

    public void setNumeroPasadas(int numeroPasadas) {
        this.numeroPasadas = numeroPasadas;
    }

    public float getAncho() {
        return ancho;
    }

    public void setAncho(float ancho) {
        this.ancho = ancho;
    }

    public float getLargo() {
        return largo;
    }

    public void setLargo(float largo) {
        this.largo = largo;
    }

    public float getDistanciaLateral() {
        return distanciaLateral;
    }

    public void setDistanciaLateral(float distanciaLateral) {
        this.distanciaLateral = distanciaLateral;
    }

    public float getDistanciaFrontal() {
        return distanciaFrontal;
    }

    public void setDistanciaFrontal(float distanciaFrontal) {
        this.distanciaFrontal = distanciaFrontal;
    }

    public float getAreaEquipoTrabajo() {
        return (this.largo+2*this.distanciaFrontal)*(this.ancho+2*this.distanciaLateral);
    }

    public void setAreaEquipoTrabajo(float areaEquipoTrabajo) {
        this.areaEquipoTrabajo = areaEquipoTrabajo;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", tipoSuelo='" + tipoSuelo + '\'' +
                ", porcentajeProctor=" + porcentajeProctor +
                ", anchoEfectivo=" + anchoEfectivo +
                ", velocidadOperacion=" + velocidadOperacion +
                ", numeroPasadas=" + numeroPasadas +
                ", ancho=" + ancho +
                ", largo=" + largo +
                ", distanciaLateral=" + distanciaLateral +
                ", distanciaFrontal=" + distanciaFrontal +
                ", areaEquipoTrabajo=" + areaEquipoTrabajo +
                '}';
    }


    public JSONObject toJSON(){
        JSONObject jsonEquipo = new JSONObject();

        try {
            jsonEquipo.put(COLUMN_NAME_CODIGO_EQUIPO, this.codigo);
            jsonEquipo.put(COLUMN_NAME_TIPO_SUELO_EQUIPO, this.tipoSuelo);
            jsonEquipo.put(COLUMN_NAME_PORC_PROCTOR_EQUIPO, this.porcentajeProctor);
            jsonEquipo.put(COLUMN_NAME_ANCHO_EFECTIVO_EQUIPO, this.anchoEfectivo);
            jsonEquipo.put(COLUMN_NAME_VELOCIDAD_OPERACION_EQUIPO, this.velocidadOperacion);
            jsonEquipo.put(COLUMN_NAME_ANCHO_EQUIPO, this.ancho);
            jsonEquipo.put(COLUMN_NAME_LARGO_EQUIPO, this.largo);
            jsonEquipo.put(COLUMN_NAME_DISTANCIA_LATERAL_EQUIPO, this.distanciaLateral);
            jsonEquipo.put(COLUMN_NAME_DISTANCIA_FRONTAL_EQUIPO, this.distanciaFrontal);
            jsonEquipo.put(COLUMN_NAME_NUMERO_PASADAS_EQUIPO, this.numeroPasadas);
            jsonEquipo.put(COLUMN_NAME_COSTO_EQUIPO, this.costo);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonEquipo;
    }

    public float getPc(Proyecto proyecto){
        float W = this.anchoEfectivo;// - (float)0.15;
        float V = this.velocidadOperacion;
        float e = (float)proyecto.getEspesor()/(float)100.0;
        float E = proyecto.getFactorEficiencia();
        int N = this.numeroPasadas;
        float h = (float)proyecto.getAltura();
        if( h > 1000){
            h = (h-(float)1000.0)/(float)10000.0;
        }
        else{
            h = (float) 0.0;
        }


        Log.d("Equipo", "\n"+this.codigo+":\nW: "+ W+
                "\nV: " +V+
                "\ne: " +e+
                "\nE: " +E+
                "\nN: " + N+
                "\nh: " + h);
        float X = W*V*e*E/(N*(1+h));
        Log.d("Equipo", " Coeficiente: "+X);
        return X;
    }
}

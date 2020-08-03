package com.ehuaranga.optimizapp.model;

import java.util.ArrayList;

public class Proyecto {
    int id;
    String titulo;
    String descripcion;
    int espesor;
    float factorEficiencia;
    int altura;//altura en metros
    float area;
    float mcc;
    float he;
    int jornadas;
    float t;
    String tipoSuelo;
    float proctor;
    ArrayList<Equipo> equipos;

    public Proyecto() {
    }

    public float getProctor() {
        return proctor;
    }

    public void setProctor(float proctor) {
        this.proctor = proctor;
    }

    public int getJornadas() {
        return jornadas;
    }

    public void setJornadas(int jornadas) {
        this.jornadas = jornadas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEspesor() {
        return espesor;
    }

    public void setEspesor(int espesor) {
        this.espesor = espesor;
    }

    public float getFactorEficiencia() {
        return factorEficiencia;
    }

    public void setFactorEficiencia(float factorEficiencia) {
        this.factorEficiencia = factorEficiencia;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public float getMcc() {
        return mcc;
    }

    public void setMcc(float mcc) {
        this.mcc = mcc;
    }

    public float getHe() {
        return he;
    }

    public void setHe(float he) {
        this.he = he;
    }

    public float getT() {
        return t;
    }

    public void setT(float t) {
        this.t = t;
    }

    public String getTipoSuelo() {
        return tipoSuelo;
    }

    public void setTipoSuelo(String tipoSuelo) {
        this.tipoSuelo = tipoSuelo;
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", espesor=" + espesor +
                ", factorEficiencia=" + factorEficiencia +
                ", altura=" + altura +
                ", area=" + area +
                ", mcc=" + mcc +
                ", he=" + he +
                ", jornadas=" + jornadas +
                ", t=" + t +
                ", tipoSuelo='" + tipoSuelo + '\'' +
                ", proctor=" + proctor +
                ", equipos=" + equipos +
                '}';
    }
}

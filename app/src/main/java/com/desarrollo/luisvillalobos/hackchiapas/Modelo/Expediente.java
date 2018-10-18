package com.desarrollo.luisvillalobos.hackchiapas.Modelo;

public class Expediente {
    private String asunto, tratamiento, receta;
    private double peso,temp;
    private int presion;

    public Expediente() {
    }

    public Expediente(String asunto, String tratamiento, String receta, double peso, double temp, int presion) {
        this.asunto = asunto;
        this.tratamiento = tratamiento;
        this.receta = receta;
        this.peso = peso;
        this.temp = temp;
        this.presion = presion;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getReceta() {
        return receta;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getPresion() {
        return presion;
    }

    public void setPresion(int presion) {
        this.presion = presion;
    }
}

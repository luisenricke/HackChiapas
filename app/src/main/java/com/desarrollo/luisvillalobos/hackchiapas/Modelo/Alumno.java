package com.desarrollo.luisvillalobos.hackchiapas.Modelo;

public class Alumno {
    private String mail, nombre, paterno, materno, carrera;
    private int nip, semestre, nss;
    private boolean sexo;

    public Alumno() {
    }

    public Alumno(String mail, String nombre, String paterno, String materno, String carrera, int nip, int semestre, int nss, boolean sexo) {
        this.mail = mail;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.carrera = carrera;
        this.nip = nip;
        this.semestre = semestre;
        this.nss = nss;
        this.sexo = sexo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getNip() {
        return nip;
    }

    public void setNip(int nip) {
        this.nip = nip;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getNss() {
        return nss;
    }

    public void setNss(int nss) {
        this.nss = nss;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }
}

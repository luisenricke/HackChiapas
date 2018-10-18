package com.desarrollo.luisvillalobos.hackchiapas.Modelo;

public class Doctor {
    private String mail, nombre, paterno, materno, cedula;
    private int nip;
    private boolean sexo, activo;

    public Doctor() {
    }

    public Doctor(String mail, String nombre, String paterno, String materno, String cedula, int nip, boolean sexo, boolean activo) {
        this.mail = mail;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.cedula = cedula;
        this.nip = nip;
        this.sexo = sexo;
        this.activo = activo;
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getNip() {
        return nip;
    }

    public void setNip(int nip) {
        this.nip = nip;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}

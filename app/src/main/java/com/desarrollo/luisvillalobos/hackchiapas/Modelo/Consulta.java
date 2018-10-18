package com.desarrollo.luisvillalobos.hackchiapas.Modelo;

import java.sql.Time;
import java.util.Date;

public class Consulta {
    private String fecha;
    private boolean concreto;
    private int fk_alumno,fk_doctor,fk_expediente;

    public Consulta() {
    }

    public Consulta(String fecha, boolean concreto, int fk_alumno, int fk_doctor, int fk_expediente) {
        this.fecha = fecha;
        this.concreto = concreto;
        this.fk_alumno = fk_alumno;
        this.fk_doctor = fk_doctor;
        this.fk_expediente = fk_expediente;
    }

    public Consulta(String fecha, boolean concreto, int fk_alumno, int fk_doctor) {
        this.fecha = fecha;
        this.concreto = concreto;
        this.fk_alumno = fk_alumno;
        this.fk_doctor = fk_doctor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isConcreto() {
        return concreto;
    }

    public void setConcreto(boolean concreto) {
        this.concreto = concreto;
    }

    public int getFk_alumno() {
        return fk_alumno;
    }

    public void setFk_alumno(int fk_alumno) {
        this.fk_alumno = fk_alumno;
    }

    public int getFk_doctor() {
        return fk_doctor;
    }

    public void setFk_doctor(int fk_doctor) {
        this.fk_doctor = fk_doctor;
    }

    public int getFk_expediente() {
        return fk_expediente;
    }

    public void setFk_expediente(int fk_expediente) {
        this.fk_expediente = fk_expediente;
    }
}

package com.desarrollo.luisvillalobos.hackchiapas.Controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.desarrollo.luisvillalobos.hackchiapas.Modelo.Alumno;
import com.desarrollo.luisvillalobos.hackchiapas.Modelo.Consulta;
import com.desarrollo.luisvillalobos.hackchiapas.Modelo.Doctor;
import com.desarrollo.luisvillalobos.hackchiapas.Modelo.Expediente;

import java.util.Date;


public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    private Context context;

    private final String T_ALUMNO = "Alumno";
    private final String RA_MAIL = "mail";
    private final String RA_NIP = "nip";
    private final String RA_NOMBRE = "nombre";
    private final String RA_APATERNO = "aPaterno";
    private final String RA_AMATERNO = "aMaterno";
    private final String RA_SEXO = "sexo";
    private final String RA_CARRERA = "carrera";
    private final String RA_SEMESTRE = "semestre";
    private final String RA_NSS = "nss";

    private final String T_DOCTOR = "Doctor";
    private final String RD_MAIL = "mail";
    private final String RD_NIP = "nip";
    private final String RD_NOMBRE = "nombre";
    private final String RD_APATERNO = "aPaterno";
    private final String RD_AMATERNO = "aMaterno";
    private final String RD_SEXO = "sexo";
    private final String RD_CEDULA = "cedula";
    private final String RD_ACTIVO = "activo";

    private final String T_EXPEDIENTE = "Expediente";
    private final String RE_ASUNTO = "asunto";
    private final String RE_TRATAMIENTO = "tratamiento";
    private final String RE_PESO = "peso";
    private final String RE_PRESION = "presion";
    private final String RE_TEMP = "temperatura";
    private final String RE_RECETA = "receta";

    private final String T_CONSULTA = "Consulta";
    private final String RC_FECHA = "fecha";
    private final String RC_CONCRETO = "concreto";
    private final String RCF_ALUMNO = "id_alumno";
    private final String RCF_DOCTOR = "id_doctor";
    private final String RCF_EXPEDIENTE = "id_expediente";

    private DatabaseAccess(Context context) {

        this.openHelper = new DatabaseOpenHelper(context);
        this.context = context;
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public Consulta getUltimaConsultaAlumno(int fk) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + T_CONSULTA + " WHERE " + RCF_ALUMNO + " = '" + fk + "' ORDER BY _id DESC LIMIT 1;", null);
        cursor.moveToFirst();
        Consulta consulta = new Consulta();
        consulta.setFecha(cursor.getString(1));
        consulta.setConcreto(cursor.getInt(2)>0);
        consulta.setFk_alumno(cursor.getInt(3));
        consulta.setFk_doctor(cursor.getInt(4));
        consulta.setFk_expediente(cursor.getInt(5));
        return consulta;
    }

    public Cursor getConsultasAlumno(int fk) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + T_CONSULTA + " WHERE " + RCF_ALUMNO + " = '" + fk + "';", null);
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor getConsultaDoctor(int fk) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + T_CONSULTA + " WHERE " + RCF_DOCTOR + " = '" + fk + "';", null);
        cursor.moveToFirst();
        return cursor;
    }

    public String getConsulta(String fecha, boolean concreto, int fk_alumno, int fk_doctor, int fk_expediente) {
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + T_CONSULTA + " WHERE " + RC_FECHA + " = '" + fecha + "' AND " + RC_CONCRETO + " = '" + concreto + "' AND " + RCF_ALUMNO + " = '" + fk_alumno + "' AND " + RCF_DOCTOR + " = '" + fk_doctor + "' AND " + RCF_EXPEDIENTE + " = '" + fk_expediente + "'", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                return (cursor.getString(cursor.getColumnIndexOrThrow("_id")));
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public Consulta getConsulta(String _id) {
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + T_CONSULTA + " WHERE _id = '" + _id + "'", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Consulta consulta = new Consulta();
                consulta.setFecha(cursor.getString(1));
                consulta.setConcreto(cursor.getExtras().getBoolean(String.valueOf(2)));
                consulta.setFk_alumno(cursor.getInt(3));
                consulta.setFk_doctor(cursor.getInt(4));
                consulta.setFk_doctor(cursor.getInt(5));
                return consulta;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public void setConsulta(String fecha, boolean concreto, int fk_alumno, int fk_doctor, int fk_expediente) {
        try {
            ContentValues newRow = new ContentValues();
            newRow.put(RC_FECHA, fecha);
            newRow.put(RC_CONCRETO, concreto);
            newRow.put(RCF_ALUMNO, fk_alumno);
            newRow.put(RCF_DOCTOR, fk_doctor);
            newRow.put(RCF_EXPEDIENTE, fk_expediente);
            database.insertWithOnConflict(T_CONSULTA, null, newRow, SQLiteDatabase.CONFLICT_ROLLBACK);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateConsulta(int _id, String fecha, boolean concreto, int fk_alumno, int fk_doctor, int fk_expediente) {
        try {
            ContentValues updateRow = new ContentValues();
            updateRow.put(RC_FECHA, fecha);
            updateRow.put(RC_CONCRETO, concreto);
            updateRow.put(RCF_ALUMNO, fk_alumno);
            updateRow.put(RCF_DOCTOR, fk_doctor);
            updateRow.put(RCF_EXPEDIENTE, fk_expediente);
            database.updateWithOnConflict(T_CONSULTA, updateRow, "_id=" + _id, null, SQLiteDatabase.CONFLICT_ROLLBACK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deleteConsulta(String _id) {
        return database.delete(T_CONSULTA, "_id" + "=" + _id, null) > 0;
    }

    public void setAlumno(String mail, int nip, String nombre, String paterno, String materno, boolean sexo, String carrera, int semestre, int nss) {
        try {
            ContentValues newRow = new ContentValues();
            newRow.put(RA_MAIL, mail);
            newRow.put(RA_NIP, nip);
            newRow.put(RA_NOMBRE, nombre);
            newRow.put(RA_APATERNO, paterno);
            newRow.put(RA_AMATERNO, materno);
            newRow.put(RA_SEXO, sexo);
            newRow.put(RA_CARRERA, carrera);
            newRow.put(RA_SEMESTRE, semestre);
            newRow.put(RA_NSS, nss);
            database.insertWithOnConflict(T_ALUMNO, null, newRow, SQLiteDatabase.CONFLICT_ROLLBACK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAlumno(String mail, int nip, String nombre, String paterno, String materno, boolean sexo, String carrera, int semestre, int nss) {
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + T_ALUMNO + " WHERE " + RA_MAIL + " = '" + mail + "' AND " + RA_NIP + "= '" + nip + "' AND " + RA_NOMBRE + "= '" + nombre + "' AND " + RA_APATERNO + "= '" + paterno + "' AND " + RA_AMATERNO + "= '" + materno + "' AND " + RA_SEXO + "= '" + sexo + "' AND " + RA_CARRERA + "= '" + carrera + "' AND " + RA_SEMESTRE + "= '" + semestre + "' AND " + RA_NSS + "= '" + nss + "'", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                return (cursor.getString(cursor.getColumnIndexOrThrow("_id")));
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public String getAlumno(String mail, int nip) {
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + T_ALUMNO + " WHERE " + RA_MAIL + " = '" + mail + "' AND " + RA_NIP + "= '" + nip + "'", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                return (cursor.getString(cursor.getColumnIndexOrThrow("_id")));
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public Alumno getAlumno(String _id) {
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + T_ALUMNO + " WHERE _id = '" + _id + "'", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Alumno alumno = new Alumno();
                alumno.setMail(cursor.getString(1));
                alumno.setNip(cursor.getInt(2));
                alumno.setNombre(cursor.getString(3));
                alumno.setPaterno(cursor.getString(4));
                alumno.setMaterno(cursor.getString(5));
                alumno.setSexo(cursor.getExtras().getBoolean(String.valueOf(6)));
                alumno.setCarrera(cursor.getString(7));
                alumno.setSemestre(cursor.getInt(8));
                alumno.setNss(cursor.getInt(9));
                return alumno;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public void setDoctor(String mail, int nip, String nombre, String paterno, String materno, boolean sexo, String cedula, boolean activo) {
        try {
            ContentValues newRow = new ContentValues();
            newRow.put(RD_MAIL, mail);
            newRow.put(RD_NIP, nip);
            newRow.put(RD_NOMBRE, nombre);
            newRow.put(RD_APATERNO, paterno);
            newRow.put(RD_AMATERNO, materno);
            newRow.put(RD_SEXO, sexo);
            newRow.put(RD_CEDULA, cedula);
            newRow.put(RD_ACTIVO, activo);
            database.insertWithOnConflict(T_DOCTOR, null, newRow, SQLiteDatabase.CONFLICT_ROLLBACK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDoctor(String mail, int nip, String nombre, String paterno, String materno, boolean sexo, String cedula, boolean activo) {
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + T_DOCTOR + " WHERE " + RD_MAIL + " = '" + mail + "' AND " + RD_NIP + "= '" + nip + "' AND " + RD_NOMBRE + "= '" + nombre + "' AND " + RD_APATERNO + "= '" + paterno + "' AND " + RD_AMATERNO + "= '" + materno + "' AND " + RD_SEXO + "= '" + sexo + "' AND " + RD_CEDULA + "= '" + cedula + "' AND " + RD_ACTIVO + "= '" + activo + "'", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                return (cursor.getString(cursor.getColumnIndexOrThrow("_id")));
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public String getDoctor(String mail, int nip) {
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + T_DOCTOR + " WHERE " + RD_MAIL + " = '" + mail + "' AND " + RD_NIP + "= '" + nip + "'", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                return (cursor.getString(cursor.getColumnIndexOrThrow("_id")));
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public Doctor getDoctor(String _id) {
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + T_DOCTOR + " WHERE _id = '" + _id + "'", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Doctor doctor = new Doctor();
                doctor.setMail(cursor.getString(1));
                doctor.setNip(cursor.getInt(2));
                doctor.setNombre(cursor.getString(3));
                doctor.setPaterno(cursor.getString(4));
                doctor.setMaterno(cursor.getString(5));
                doctor.setSexo(cursor.getExtras().getBoolean(String.valueOf(6)));
                doctor.setCedula(cursor.getString(7));
                doctor.setActivo(cursor.getExtras().getBoolean(String.valueOf(8)));
                return doctor;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public void setExpediente(String asunto, String tratamiento, double peso, int presion, double temp, String receta) {
        try {
            ContentValues newRow = new ContentValues();
            newRow.put(RE_ASUNTO, asunto);
            newRow.put(RE_TRATAMIENTO, tratamiento);
            newRow.put(RE_PESO, peso);
            newRow.put(RE_PRESION, presion);
            newRow.put(RE_TEMP, temp);
            newRow.put(RE_RECETA, receta);
            database.insertWithOnConflict(T_EXPEDIENTE, null, newRow, SQLiteDatabase.CONFLICT_ROLLBACK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getExpediente(String asunto, String tratamiento, double peso, int presion, double temp, String receta) {
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + T_EXPEDIENTE + " WHERE " + RE_ASUNTO + " = '" + asunto + "' AND " + RE_TRATAMIENTO + "= '" + tratamiento + "' AND " + RE_PESO + "= '" + peso + "' AND " + RE_PRESION + "= '" + presion + "' AND " + RE_TEMP + "= '" + temp + "' AND " + RE_RECETA + "= '" + receta + "'", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                return (cursor.getString(cursor.getColumnIndexOrThrow("_id")));
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public Expediente getExpediente(String _id) {
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + T_EXPEDIENTE + " WHERE _id = '" + _id + "'", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Expediente expediente = new Expediente();
                expediente.setAsunto(cursor.getString(1));
                expediente.setTratamiento(cursor.getString(2));
                expediente.setPeso(cursor.getDouble(3));
                expediente.setPresion(cursor.getInt(4));
                expediente.setTemp(cursor.getDouble(5));
                expediente.setReceta(cursor.getString(6));
                return expediente;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public Cursor getExpedientes(int id_alumno){
        //SELECT Expediente._id,Expediente.peso,Expediente.presion,Expediente.temp,Consulta.fecha FROM Expediente INNER JOIN Consulta ON Expediente._id = Consulta.id_expediente;
        Cursor cursor = database.rawQuery("SELECT Alumno._id, Consulta._id AS id_alumno, \n" +
                        "Consulta.id_expediente AS id_expediente,  \n" +
                        "Expediente._id AS _id_expediente,  \n" +
                        "Consulta.fecha AS fecha, \n" +
                        "Expediente.asunto AS asunto,\n" +
                        "Expediente.peso AS peso,\n" +
                        "Expediente.presion AS presion,\n" +
                        "Expediente.temperatura AS temp\n" +
                        "FROM Alumno \n" +
                        "INNER JOIN Consulta ON Alumno._id = Consulta.id_alumno\n" +
                        "INNER JOIN Expediente ON  Consulta.id_expediente = Expediente._id \n" +
                        "WHERE Alumno._id=1;", null);


        cursor.moveToFirst();
        return cursor;
    }



}

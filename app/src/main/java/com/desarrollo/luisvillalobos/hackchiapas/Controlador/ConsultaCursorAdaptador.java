package com.desarrollo.luisvillalobos.hackchiapas.Controlador;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.desarrollo.luisvillalobos.hackchiapas.R;

public class ConsultaCursorAdaptador extends CursorAdapter {
    public ConsultaCursorAdaptador(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_consulta_alumno, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView lblAsunto = (TextView) view.findViewById(R.id.lblAsunto);
        lblAsunto.setText(cursor.getString(cursor.getColumnIndexOrThrow("asunto")));

        TextView lblTemp = (TextView) view.findViewById(R.id.lblTemp);
        lblTemp.setText("Temp: "+cursor.getDouble(cursor.getColumnIndexOrThrow("temp"))+"");

        TextView lblPeso = (TextView) view.findViewById(R.id.lblPeso);
        lblPeso.setText("Peso: "+cursor.getInt(cursor.getColumnIndexOrThrow("peso"))+"");





        TextView lblFecha = (TextView) view.findViewById(R.id.lblFecha);
        lblFecha.setText(cursor.getString(cursor.getColumnIndexOrThrow("fecha")));
    }
}

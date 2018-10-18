package com.desarrollo.luisvillalobos.hackchiapas.Vista;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.constraint.Guideline;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.desarrollo.luisvillalobos.hackchiapas.Controlador.ConsultaCursorAdaptador;
import com.desarrollo.luisvillalobos.hackchiapas.Modelo.Consulta;
import com.desarrollo.luisvillalobos.hackchiapas.Modelo.Doctor;
import com.desarrollo.luisvillalobos.hackchiapas.R;
import com.desarrollo.luisvillalobos.hackchiapas.Controlador.DatabaseAccess;
import com.desarrollo.luisvillalobos.hackchiapas.Controlador.SetUpActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ListConsultaAlumno extends AppCompatActivity {

    //private TextView lblFecha, lblDoctor, lblPendiente;
    private Guideline line;
    private ListView lvConsulta;
    private FloatingActionButton btnLogOut, btnAdd;
    //private LinearLayout l_gone;

    private Context context;
    private DatabaseAccess databaseAccess;
    public static final String PREFS_NAME = "HackChiapas";
    private int fk;

    protected Calendar calendarioHacia;
    protected Calendar calendarioPara;
    protected String dateFormat;
    protected SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_consulta_alumno);

        context = getBaseContext();

        try {
            fk = Integer.parseInt(getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).getString("_id", null));
        } catch (NumberFormatException ex) {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("logged", false);
            editor.remove("_id");
            editor.commit();

            Intent intent = new Intent(context, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }

        SetUpActivity.hiderActionBar(this);
        SetUpActivity.hideStatusBar(this);
        SetUpActivity.hideSoftKeyboard(this);

        lvConsulta = (ListView) findViewById(R.id.lvConsulta);
        btnLogOut = (FloatingActionButton) findViewById(R.id.btn_logout);
        btnAdd = (FloatingActionButton) findViewById(R.id.btn_add);
        //lblFecha = (TextView) findViewById(R.id.lbl_fecha);
        //lblDoctor = (TextView) findViewById(R.id.lbl_doctor);
        //lblPendiente = (TextView) findViewById(R.id.lbl_pendiente);
        line = (Guideline) findViewById(R.id.guideline);
        //l_gone = (LinearLayout) findViewById(R.id.l_gone);

        //calendarioPara = Calendar.getInstance();
        //calendarioHacia.setTimeZone(TimeZone.getTimeZone("GMT"));
        //dateFormat = "yyyy-dd-MM HH:mm";
        //sdf = new SimpleDateFormat(dateFormat);


      /*  Consulta checkUltimo = databaseAccess.getUltimaConsultaAlumno(fk);
        Log.d("Prueba", checkUltimo.getFecha());
        Date now = Calendar.getInstance().getTime();
        Log.d("Prueba", now.toString());

        try {
            Date registroFecha = sdf.parse(checkUltimo.getFecha());
            if (registroFecha.after(now)) {
                lblFecha.setText(checkUltimo.getFecha());
                Doctor doctorCheco = databaseAccess.getDoctor(String.valueOf(checkUltimo.getFk_doctor()));
                lblDoctor.setText(doctorCheco.getNombre() + " " + doctorCheco.getPaterno());
            } else {
                line.setGuidelinePercent(0.15f);
                l_gone.setVisibility(View.GONE);
                lblFecha.setVisibility(View.GONE);
                lblDoctor.setVisibility(View.GONE);
                lblPendiente.setVisibility(View.GONE);
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
*/
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        Cursor cursor = databaseAccess.getExpedientes(fk);
        cursor.moveToFirst();
        ConsultaCursorAdaptador adapter = new ConsultaCursorAdaptador(context, cursor);
        lvConsulta.setAdapter(adapter);
        databaseAccess.close();

        //lvConsulta.setOnItemClickListener(new lvDeviceClick());
        //lvConsulta.setOnItemLongClickListener(new lvDeviceLongClick());*/
        btnAdd.setOnClickListener(new btnAdd());
        btnLogOut.setOnClickListener(new btnLogOut());
    }


    class btnAdd implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, FormConsultaAgendar.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            startActivityForResult(intent, 1);
        }
    }

    class btnLogOut implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("logged", false);
            editor.remove("_id");
            editor.remove("AoD");
            editor.commit();
            Intent intent = new Intent(context, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                /*Device device = data.getParcelableExtra("object");
                databaseAccess = DatabaseAccess.getInstance(this);
                databaseAccess.open();
                databaseAccess.setDevice(device.getDescripcion(), device.getApiKey(), device.getDevice(), device.getUser(), fk);

                Cursor cursor = databaseAccess.getDevices(fk);
                DeviceCursorAdapter adapter = new DeviceCursorAdapter(context, cursor);
                lvDevice.setAdapter(adapter);
                databaseAccess.close();
                recreate();
                Toast.makeText(context, "Se ha agredado satisfactoriamente", Toast.LENGTH_LONG).show();
                */
            } else {
                Toast.makeText(context, "No se rellenaron los campos correctamente", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(context, "No se rellenaron los campos correctamente", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        //Para no usar el boton hacia atras
        //super.onBackPressed();
        //overridePendingTransition( 0, 0);
        //System.exit(0);
        moveTaskToBack(true);
    }
}

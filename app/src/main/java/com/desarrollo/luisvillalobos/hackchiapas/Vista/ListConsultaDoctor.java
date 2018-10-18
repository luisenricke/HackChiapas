package com.desarrollo.luisvillalobos.hackchiapas.Vista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.desarrollo.luisvillalobos.hackchiapas.Controlador.DatabaseAccess;
import com.desarrollo.luisvillalobos.hackchiapas.Controlador.SetUpActivity;
import com.desarrollo.luisvillalobos.hackchiapas.R;

public class ListConsultaDoctor extends AppCompatActivity {

    private ListView lvConsulta;
    private FloatingActionButton btnLogOut;

    private Context context;
    private DatabaseAccess databaseAccess;
    public static final String PREFS_NAME = "HackChiapas";
    private int fk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_consulta_doctor);

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

        /*databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        Cursor cursor = databaseAccess.getDevices(fk);
        cursor.moveToFirst();
        DeviceCursorAdapter adapter = new DeviceCursorAdapter(context, cursor);
        lvDevice.setAdapter(adapter);

        lvDevice.setOnItemClickListener(new lvDeviceClick());
        lvDevice.setOnItemLongClickListener(new lvDeviceLongClick());*/
        btnLogOut.setOnClickListener(new btnLogOut());
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
}

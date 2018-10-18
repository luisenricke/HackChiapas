package com.desarrollo.luisvillalobos.hackchiapas.Vista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.desarrollo.luisvillalobos.hackchiapas.Controlador.DatabaseAccess;
import com.desarrollo.luisvillalobos.hackchiapas.Controlador.SetUpActivity;
import com.desarrollo.luisvillalobos.hackchiapas.R;


public class Login extends AppCompatActivity {

    private EditText inName, inPassword;
    private Button btnAction;
    private Resources resources;
    private Context context;
    private DatabaseAccess databaseAccess;
    public static final String PREFS_NAME = "HackChiapas";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        resources = getResources();
        context = getApplicationContext();//context = getBaseContext();

        SetUpActivity.hiderActionBar(this);
        SetUpActivity.hideStatusBar(this);
        SetUpActivity.hideSoftKeyboard(this);
        SetUpActivity.setWindowPortrait(this);

        inName = (EditText) findViewById(R.id.in_name);
        inPassword = (EditText) findViewById(R.id.in_password);

        btnAction = (Button) findViewById(R.id.btn_action);


        btnAction.setOnClickListener(new actionBtnClick());

        /*SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("logged", false);
        editor.remove("_id");
        editor.commit();*/

       SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (settings.getBoolean("logged", true)) {
            if(settings.getBoolean("AoD",true)) {
                Intent intent = new Intent(context, ListConsultaAlumno.class);
                Log.d("Prueba", "paso");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }else{
                Intent intent = new Intent(context, ListConsultaDoctor.class);
                Log.d("Prueba", "paso");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        }

    }

    class actionBtnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            if (inName.getText().toString().trim().length() != 0 &&
                    inPassword.getText().toString().trim().length() != 0) {

                databaseAccess = DatabaseAccess.getInstance(context);
                databaseAccess.open();
                String _idAlumno = databaseAccess.getAlumno(inName.getText().toString().trim(), Integer.parseInt(inPassword.getText().toString().trim()));
                String _idDoctor = databaseAccess.getDoctor(inName.getText().toString().trim(), Integer.parseInt(inPassword.getText().toString().trim()));
                databaseAccess.close();

                if (_idAlumno != null && _idDoctor == null) {
                    SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("logged", true);
                    editor.putBoolean("AoD",true);
                    editor.putString("_id", _idAlumno);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Ha iniciado correctamente sesión", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ListConsultaAlumno.class);
                    startActivity(intent);
                } else if (_idDoctor != null && _idAlumno == null) {
                    SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("logged", true);
                    editor.putString("_id", _idDoctor);
                    editor.putBoolean("AoD",true);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Ha iniciado correctamente sesión", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ListConsultaDoctor.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Los datos son incorrectos", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Falta ingresar el usuario y/o contraseña", Toast.LENGTH_LONG).show();
            }
        }
    }

}

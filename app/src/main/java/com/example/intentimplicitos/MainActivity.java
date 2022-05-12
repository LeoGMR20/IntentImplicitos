package com.example.intentimplicitos;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Atributos visuales

    private EditText etTelefono;
    private ImageButton btnLlamar, btnCamara;

    //Atributos de clase - primitivos

    private String numeroTelefonico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarVistas();
        btnLlamar.setOnClickListener(view -> {
            activarLlamada();
        });
    }

    private void activarLlamada() {
        numeroTelefonico = etTelefono.getText().toString();
        //Validar que el usuario un número
        if(!numeroTelefonico.isEmpty()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            }
            else {
                configuracionVersionesAntiguas();
            }
        }
    }

    private void configuracionVersionesAntiguas() {
        //Intent implicito para que un componente
        //Realize una acción, el sistema busca el mejor para hacerlo
        Intent intentCall = new Intent(Intent.ACTION_CALL,
                Uri.parse("tel:"+ numeroTelefonico));
        if (revisarPermisos(Manifest.permission.CALL_PHONE)){
            startActivity(intentCall);
        }
        else{
            Toast.makeText(this,"Permission Denied", Toast.LENGTH_LONG).show();
        }
    }

    private void inicializarVistas() {
        etTelefono = findViewById(R.id.etTelefono);
        btnLlamar = findViewById(R.id.btnLlamar);
        btnCamara = findViewById(R.id.btnCamara);
    }

    private boolean revisarPermisos(String permiso){
        int valorPermiso = this.checkCallingOrSelfPermission(permiso);
        return valorPermiso == PackageManager.PERMISSION_GRANTED;
    }
}
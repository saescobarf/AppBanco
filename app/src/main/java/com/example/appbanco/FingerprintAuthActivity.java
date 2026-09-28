package com.example.appbanco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FingerprintAuthActivity extends AppCompatActivity {

    private RelativeLayout btnSensorArea;
    private LinearLayout btnUsePassword;
    private TextView btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint_auth);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btnSensorArea = findViewById(R.id.btnSensorArea);
        btnUsePassword = findViewById(R.id.btnUsePassword);
        btnCancel = findViewById(R.id.btnCancel);

        // Simular lectura de huella exitosa al hacer clic en el sensor
        btnSensorArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FingerprintAuthActivity.this, "Huella reconocida correctamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(FingerprintAuthActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Ir a Login con contraseña
        btnUsePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FingerprintAuthActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Cancelar y cerrar o regresar
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

package com.example.appbanco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private LinearLayout btnLogout;
    private LinearLayout btnNavHome; // Declaramos el botón de navegación a Home

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btnLogout = findViewById(R.id.btnLogout);
        btnNavHome = findViewById(R.id.btnNavHome); // Inicializamos el botón

        // Evento Ir a Home / Inicio
        btnNavHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
                finish(); // Opcional: cierra perfil para evitar acumular actividades
            }
        });

        // Evento Cerrar Sesión -> Regresar al Login
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "Sesión cerrada", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
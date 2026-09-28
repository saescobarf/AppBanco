package com.example.appbanco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class LoginActivity extends AppCompatActivity {

    private EditText etUser, etPassword;
    private AppCompatButton btnLogin;
    private LinearLayout btnBiometric;
    private TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        etUser = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnBiometric = findViewById(R.id.btnBiometric);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        // Evento Iniciar Sesión -> Ir a HomeActivity
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = etUser.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (usuario.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Iniciando sesión...", Toast.LENGTH_SHORT).show();

                    // Abrir HomeActivity
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish(); // Cierra el Login para que no se pueda regresar presionando 'Atrás'
                }
            }
        });

        // Evento Biometría -> Ir a FingerprintAuthActivity
        btnBiometric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, FingerprintAuthActivity.class);
                startActivity(intent);
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Recuperar contraseña", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
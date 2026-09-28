package com.example.appbanco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;

public class FingerprintAuthActivity extends AppCompatActivity {

    private RelativeLayout btnSensorArea;
    private LinearLayout btnUsePassword;
    private TextView btnCancel;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

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

        // 1. Configurar el ejecutor
        executor = ContextCompat.getMainExecutor(this);

        // 2. Configurar las respuestas de la autenticación biométrica
        biometricPrompt = new BiometricPrompt(FingerprintAuthActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                // Ocurrió un error o el usuario canceló
                Toast.makeText(getApplicationContext(),
                        "Error de autenticación: " + errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                // ¡Lectura exitosa!
                Toast.makeText(getApplicationContext(),
                        "¡Autenticación biométrica exitosa!", Toast.LENGTH_SHORT).show();

                // Navegar a Home
                Intent intent = new Intent(FingerprintAuthActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                // La huella no coincidió
                Toast.makeText(getApplicationContext(),
                        "Huella no reconocida. Intenta de nuevo.", Toast.LENGTH_SHORT).show();
            }
        });

        // 3. Configurar el diálogo que muestra el sistema operativo
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Acceso con huella")
                .setSubtitle("Confirma que eres tú para entrar de forma segura.")
                .setNegativeButtonText("Usar contraseña")
                .build();

        // Lanzar el lector biométrico al tocar la huella en la pantalla
        btnSensorArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biometricPrompt.authenticate(promptInfo);
            }
        });

        // Opción: Usar contraseña
        btnUsePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FingerprintAuthActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Opción: Cancelar
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // (Opcional) Desplegar el lector biométrico automáticamente al entrar a la pantalla
        biometricPrompt.authenticate(promptInfo);
    }
}

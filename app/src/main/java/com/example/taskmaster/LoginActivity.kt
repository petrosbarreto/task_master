package com.example.taskmaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

// LoginActivity — será integrada ao fluxo de autenticação em aula futura
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etSenha = findViewById<TextInputEditText>(R.id.etSenha)
        val btnEntrar = findViewById<Button>(R.id.btnEntrar)

        btnEntrar.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val senha = etSenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha e-mail e senha", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Aula futura: validar credenciais com Firebase / banco local
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}

package com.example.taskmaster

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NovaTarefaActivity : AppCompatActivity() {

    private lateinit var editTitulo: EditText
    private lateinit var editPrazo: EditText
    private lateinit var btnSalvar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_tarefa)

        editTitulo = findViewById(R.id.etTitulo)
        editPrazo = findViewById(R.id.etPrazo)
        btnSalvar = findViewById(R.id.btnSalvar)

        btnSalvar.setOnClickListener {

            val titulo = editTitulo.text.toString()
            val prazo = editPrazo.text.toString()

            if (titulo.isEmpty()) {
                Toast.makeText(this, "Digite um título", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Tarefa salva!", Toast.LENGTH_SHORT).show()

            // Aqui futuramente você pode salvar no banco
            finish()
        }
    }
}
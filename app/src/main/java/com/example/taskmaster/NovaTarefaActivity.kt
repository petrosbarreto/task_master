package com.example.taskmaster

import Tarefa
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class NovaTarefaActivity : AppCompatActivity() {

    private lateinit var etTitulo: TextInputEditText
    private lateinit var etDescricao: TextInputEditText
    private lateinit var etPrazo: TextInputEditText
    private lateinit var btnSalvar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_tarefa)

        etTitulo = findViewById(R.id.etTitulo)
        etDescricao = findViewById(R.id.etDescricao)
        etPrazo = findViewById(R.id.etPrazo)
        btnSalvar = findViewById(R.id.btnSalvar)

        btnSalvar.setOnClickListener {

            val titulo = etTitulo.text.toString().trim()
            val descricao = etDescricao.text.toString().trim()
            val prazo = etPrazo.text.toString().trim()

            if (titulo.isEmpty()) {
                Toast.makeText(this, "Digite o título da tarefa", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Aqui depois você pode salvar no banco (Room / SQLite)
            val novaTarefa = Tarefa(
                id = (0..1000).random(),
                titulo = titulo,
                descricao = descricao,
                prazo = prazo,
                prioridade = Prioridade.MEDIA
            )

            Toast.makeText(this, "Tarefa criada: $titulo", Toast.LENGTH_SHORT).show()

            finish()
        }
    }
}
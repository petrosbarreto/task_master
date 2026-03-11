package com.example.taskmaster

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class NovaTarefaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_tarefa)

        val etTitulo     = findViewById<TextInputEditText>(R.id.etTitulo)
        val etDescricao  = findViewById<TextInputEditText>(R.id.etDescricao)
        val etPrazo      = findViewById<TextInputEditText>(R.id.etPrazo)
        val rgPrioridade = findViewById<RadioGroup>(R.id.rgPrioridade)
        val btnSalvar    = findViewById<Button>(R.id.btnSalvar)

        btnSalvar.setOnClickListener {
            val titulo = etTitulo.text.toString().trim()

            if (titulo.isEmpty()) {
                etTitulo.error = "Informe o título da tarefa"
                return@setOnClickListener
            }

            val prioridade = when (rgPrioridade.checkedRadioButtonId) {
                R.id.rbBaixa -> Prioridade.BAIXA
                R.id.rbAlta  -> Prioridade.ALTA
                else         -> Prioridade.MEDIA
            }

            val intent = Intent().apply {
                putExtra("titulo",    titulo)
                putExtra("descricao", etDescricao.text.toString().trim())
                putExtra("prazo",     etPrazo.text.toString().trim())
                putExtra("prioridade", prioridade.name)
            }

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}

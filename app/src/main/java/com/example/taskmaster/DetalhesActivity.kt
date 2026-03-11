package com.example.taskmaster

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalhesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        val tvTitulo     = findViewById<TextView>(R.id.tvDetalhesTitulo)
        val tvDescricao  = findViewById<TextView>(R.id.tvDetalhesDescricao)
        val tvPrazo      = findViewById<TextView>(R.id.tvDetalhesPrazo)
        val tvPrioridade = findViewById<TextView>(R.id.tvDetalhesPrioridade)
        val tvStatus     = findViewById<TextView>(R.id.tvDetalhesStatus)

        val titulo     = intent.getStringExtra("titulo")    ?: ""
        val descricao  = intent.getStringExtra("descricao") ?: ""
        val prazo      = intent.getStringExtra("prazo")     ?: ""
        val prioridade = Prioridade.valueOf(
            intent.getStringExtra("prioridade") ?: Prioridade.MEDIA.name
        )
        val concluida  = intent.getBooleanExtra("concluida", false)

        tvTitulo.text     = titulo
        tvDescricao.text  = descricao.ifEmpty { "Sem descrição" }
        tvPrazo.text      = if (prazo.isNotEmpty()) "Prazo: $prazo" else "Sem prazo"
        tvPrioridade.text = "Prioridade: ${prioridade.label}"
        tvPrioridade.setTextColor(Color.parseColor(prioridade.corHex))
        tvStatus.text     = if (concluida) "Status: Concluída ✓" else "Status: Pendente"
    }
}

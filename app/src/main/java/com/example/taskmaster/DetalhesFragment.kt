package com.example.taskmaster

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetalhesFragment : Fragment(R.layout.fragment_detalhes) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvTitulo = view.findViewById<TextView>(R.id.tvDetalhesTitulo)
        val tvDescricao = view.findViewById<TextView>(R.id.tvDetalhesDescricao)
        val tvPrazo = view.findViewById<TextView>(R.id.tvDetalhesPrazo)
        val tvPrioridade = view.findViewById<TextView>(R.id.tvDetalhesPrioridade)
        val tvStatus = view.findViewById<TextView>(R.id.tvDetalhesStatus)

        val argumentos = requireArguments()
        val titulo = argumentos.getString(ARG_TITULO).orEmpty()
        val descricao = argumentos.getString(ARG_DESCRICAO).orEmpty()
        val prazo = argumentos.getString(ARG_PRAZO).orEmpty()
        val prioridade = Prioridade.valueOf(
            argumentos.getString(ARG_PRIORIDADE) ?: Prioridade.MEDIA.name
        )
        val concluida = argumentos.getBoolean(ARG_CONCLUIDA, false)

        tvTitulo.text = titulo
        tvDescricao.text = descricao.ifEmpty { "Sem descrição" }
        tvPrazo.text = if (prazo.isNotEmpty()) "Prazo: $prazo" else "Sem prazo"
        tvPrioridade.text = "Prioridade: ${prioridade.label}"
        tvPrioridade.setTextColor(Color.parseColor(prioridade.corHex))
        tvStatus.text = if (concluida) "Status: Concluída ✓" else "Status: Pendente"
    }

    companion object {
        const val ARG_TITULO = "titulo"
        const val ARG_DESCRICAO = "descricao"
        const val ARG_PRAZO = "prazo"
        const val ARG_PRIORIDADE = "prioridade"
        const val ARG_CONCLUIDA = "concluida"
    }
}


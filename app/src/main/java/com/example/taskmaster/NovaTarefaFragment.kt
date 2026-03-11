package com.example.taskmaster

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText

class NovaTarefaFragment : Fragment(R.layout.fragment_nova_tarefa) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etTitulo = view.findViewById<TextInputEditText>(R.id.etTitulo)
        val etDescricao = view.findViewById<TextInputEditText>(R.id.etDescricao)
        val etPrazo = view.findViewById<TextInputEditText>(R.id.etPrazo)
        val rgPrioridade = view.findViewById<RadioGroup>(R.id.rgPrioridade)
        val btnSalvar = view.findViewById<Button>(R.id.btnSalvar)

        btnSalvar.setOnClickListener {
            val titulo = etTitulo.text.toString().trim()
            if (titulo.isEmpty()) {
                etTitulo.error = "Informe o título da tarefa"
                return@setOnClickListener
            }

            val prioridade = when (rgPrioridade.checkedRadioButtonId) {
                R.id.rbBaixa -> Prioridade.BAIXA
                R.id.rbAlta -> Prioridade.ALTA
                else -> Prioridade.MEDIA
            }

            parentFragmentManager.setFragmentResult(
                REQUEST_KEY,
                bundleOf(
                    BUNDLE_TITULO to titulo,
                    BUNDLE_DESCRICAO to etDescricao.text.toString().trim(),
                    BUNDLE_PRAZO to etPrazo.text.toString().trim(),
                    BUNDLE_PRIORIDADE to prioridade.name
                )
            )

            findNavController().navigateUp()
        }
    }

    companion object {
        const val REQUEST_KEY = "nova_tarefa_request"
        const val BUNDLE_TITULO = "titulo"
        const val BUNDLE_DESCRICAO = "descricao"
        const val BUNDLE_PRAZO = "prazo"
        const val BUNDLE_PRIORIDADE = "prioridade"
    }
}


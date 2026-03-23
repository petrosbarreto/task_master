package com.example.taskmaster

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Exibe apenas as tarefas PENDENTES (concluida = false).
 * Aula 07 — Abas Pendentes / Concluídas
 */
class PendentesFragment : Fragment(R.layout.fragment_aba_tarefas) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvVazio: TextView
    private lateinit var adapter: TarefaAdapter

    private val listaTarefasFragment: ListaTarefasFragment
        get() = requireParentFragment() as ListaTarefasFragment

    private val mainActivity: MainActivity
        get() = requireActivity() as MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewAba)
        tvVazio = view.findViewById(R.id.tvVazioAba)

        adapter = TarefaAdapter(
            tarefas = tarefasPendentes().toMutableList(),
            onItemClick = { tarefa ->
                // Navega para detalhes usando o NavController do host de navegação
                findNavController().navigate(
                    R.id.action_listaTarefasFragment_to_detalhesFragment,
                    bundleOf(
                        DetalhesFragment.ARG_TITULO to tarefa.titulo,
                        DetalhesFragment.ARG_DESCRICAO to tarefa.descricao,
                        DetalhesFragment.ARG_PRAZO to tarefa.prazo,
                        DetalhesFragment.ARG_PRIORIDADE to tarefa.prioridade.name,
                        DetalhesFragment.ARG_CONCLUIDA to tarefa.concluida
                    )
                )
            },
            onCheckClick = { tarefa, checked ->
                // Marca como concluída e avisa as abas para refrescarem
                tarefa.concluida = checked
                listaTarefasFragment.notificarAbas()
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Escuta o sinal de "tarefas atualizadas" vindo do ListaTarefasFragment
        parentFragmentManager.setFragmentResultListener(
            ListaTarefasFragment.CHAVE_ATUALIZAR,
            viewLifecycleOwner
        ) { _, _ -> refreshLista() }

        refreshLista()
    }

    private fun refreshLista() {
        val pendentes = tarefasPendentes()
        adapter.atualizarDados(pendentes)
        tvVazio.visibility = if (pendentes.isEmpty()) View.VISIBLE else View.GONE
        recyclerView.visibility = if (pendentes.isEmpty()) View.GONE else View.VISIBLE
    }

    private fun tarefasPendentes() =
        mainActivity.obterTarefas().filter { !it.concluida }
}


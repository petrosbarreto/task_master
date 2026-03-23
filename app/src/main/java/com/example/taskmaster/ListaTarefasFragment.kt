package com.example.taskmaster

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaTarefasFragment : Fragment(R.layout.fragment_lista_tarefas) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvVazio: TextView
    private lateinit var adapter: TarefaAdapter

    private val mainActivity: MainActivity
        get() = requireActivity() as MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        tvVazio = view.findViewById(R.id.tvVazio)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)

        adapter = TarefaAdapter(
            tarefas = mainActivity.obterTarefas(),
            onItemClick = { tarefa ->
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
                tarefa.concluida = checked
                adapter.notifyItemChanged(mainActivity.obterTarefas().indexOf(tarefa))
                atualizarEstadoVazio()
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        parentFragmentManager.setFragmentResultListener(
            NovaTarefaFragment.REQUEST_KEY,
            viewLifecycleOwner
        ) { _, bundle ->
            val titulo = bundle.getString(NovaTarefaFragment.BUNDLE_TITULO) ?: return@setFragmentResultListener
            val descricao = bundle.getString(NovaTarefaFragment.BUNDLE_DESCRICAO).orEmpty()
            val prazo = bundle.getString(NovaTarefaFragment.BUNDLE_PRAZO).orEmpty()
            val prioridade = Prioridade.valueOf(
                bundle.getString(NovaTarefaFragment.BUNDLE_PRIORIDADE) ?: Prioridade.MEDIA.name
            )

            mainActivity.adicionarTarefa(titulo, descricao, prazo, prioridade)
            adapter.notifyItemInserted(mainActivity.obterTarefas().lastIndex)
            recyclerView.scrollToPosition(mainActivity.obterTarefas().lastIndex)
            atualizarEstadoVazio()
        }

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_listaTarefasFragment_to_novaTarefaFragment)
        }

        atualizarEstadoVazio()
    }

    private fun atualizarEstadoVazio() {
        val listaVazia = mainActivity.obterTarefas().isEmpty()
        tvVazio.visibility = if (listaVazia) View.VISIBLE else View.GONE
        recyclerView.visibility = if (listaVazia) View.GONE else View.VISIBLE
    }
}


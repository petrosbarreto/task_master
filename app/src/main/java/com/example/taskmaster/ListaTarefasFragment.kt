package com.example.taskmaster

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ListaTarefasFragment : Fragment(R.layout.fragment_lista_tarefas) {

    private val mainActivity: MainActivity
        get() = requireActivity() as MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)

        // Configura o ViewPager2 com o adapter de abas
        val pagerAdapter = AbasViewPagerAdapter(this)
        viewPager.adapter = pagerAdapter

        // Liga as abas ao ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Pendentes"
                else -> "Concluídas"
            }
        }.attach()

        // Abre tela de nova tarefa ao tocar no FAB
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_listaTarefasFragment_to_novaTarefaFragment)
        }

        // Recebe resultado da NovaTarefaFragment e adiciona a tarefa
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

            // Avisa os fragments de aba para refrescarem a lista
            notificarAbas()
        }
    }

    /** Envia sinal para PendentesFragment e ConcluidasFragment via childFragmentManager. */
    fun notificarAbas() {
        childFragmentManager.setFragmentResult(CHAVE_ATUALIZAR, bundleOf())
    }

    companion object {
        const val CHAVE_ATUALIZAR = "tarefas_atualizadas"
    }
}

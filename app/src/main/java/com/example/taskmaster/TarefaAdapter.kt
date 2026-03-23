package com.example.taskmaster

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TarefaAdapter(
    private val tarefas: MutableList<Tarefa>,
    private val onItemClick: (Tarefa) -> Unit,
    private val onCheckClick: (Tarefa, Boolean) -> Unit
) : RecyclerView.Adapter<TarefaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tarefa, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tarefas[position])
    }

    override fun getItemCount(): Int = tarefas.size

    /** Substitui toda a lista e notifica o RecyclerView. */
    fun atualizarDados(novaLista: List<Tarefa>) {
        tarefas.clear()
        tarefas.addAll(novaLista)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitulo: TextView = itemView.findViewById(R.id.tvTitulo)
        private val tvPrazo: TextView = itemView.findViewById(R.id.tvPrazo)
        private val tvPrioridade: TextView = itemView.findViewById(R.id.tvPrioridade)
        private val checkConcluida: CheckBox = itemView.findViewById(R.id.checkConcluida)

        fun bind(tarefa: Tarefa) {
            tvTitulo.text = tarefa.titulo
            tvPrazo.text  = if (tarefa.prazo.isNotEmpty()) "Prazo: ${tarefa.prazo}" else "Sem prazo"
            tvPrioridade.text = tarefa.prioridade.label

            // Cor do badge de prioridade (mantém o border-radius do drawable)
            val badge = tvPrioridade.background as? GradientDrawable
                ?: GradientDrawable().also { it.cornerRadius = 12f }
            badge.setColor(Color.parseColor(tarefa.prioridade.corHex))
            tvPrioridade.background = badge

            // Risco no texto se concluída
            tvTitulo.paintFlags = if (tarefa.concluida)
                tvTitulo.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            else
                tvTitulo.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()

            checkConcluida.isChecked = tarefa.concluida

            checkConcluida.setOnCheckedChangeListener(null)
            checkConcluida.setOnCheckedChangeListener { _, checked ->
                onCheckClick(tarefa, checked)
            }

            itemView.setOnClickListener { onItemClick(tarefa) }
        }
    }
}

package com.vitor_moura48.taskmaster

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// TarefaAdapter.kt
class `TarefaAdapter`(
    private val tarefas: MutableList<Tarefa>,
    private val onItemClick: (Tarefa) -> Unit,
    private val onCheckClick: (Tarefa, Boolean) -> Unit
) : RecyclerView.Adapter<`TarefaAdapter`.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tarefa, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tarefas[position])
    }

    override fun getItemCount(): Int = tarefas.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitulo: TextView = itemView.findViewById(R.id.tvTitulo)
        private val tvPrazo: TextView = itemView.findViewById(R.id.tvPrazo)
        private val tvPrioridade: TextView = itemView.findViewById(R.id.tvPrioridade)
        private val checkConcluida: CheckBox = itemView.findViewById(R.id.checkConcluida)

        fun bind(tarefa: Tarefa) {
            tvTitulo.text = tarefa.titulo
            tvPrazo.text  = if (tarefa.prazo.isNotEmpty()) "Prazo: ${tarefa.prazo}" else "Sem prazo"
            tvPrioridade.text = tarefa.prioridade.label

            // Risco no texto se concluída
            tvTitulo.paintFlags = if (tarefa.concluida)
                tvTitulo.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            else
                tvTitulo.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()

            checkConcluida.setOnCheckedChangeListener(null)

            checkConcluida.isChecked = tarefa.concluida

            itemView.setOnClickListener { onItemClick(tarefa) }
            checkConcluida.setOnCheckedChangeListener { _, checked ->
                onCheckClick(tarefa, checked)
            }
        }
    }
}
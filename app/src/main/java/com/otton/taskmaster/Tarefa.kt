package com.otton.taskmaster

// Tarefa.kt
data class Tarefa(
    val id: Int,
    val titulo: String,
    val descricao: String = "",
    val prazo: String = "",
    val prioridade: Prioridade = Prioridade.MEDIA,
    var concluida: Boolean = false
)

enum class Prioridade(val label: String, val corHex: String) {
    BAIXA("Baixa", "#10B981"),
    MEDIA("Média", "#F59E0B"),
    ALTA("Alta",   "#EF4444")
}

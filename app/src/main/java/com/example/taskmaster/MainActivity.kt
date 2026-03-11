package com.example.taskmaster

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TarefaAdapter
    private val tarefas = mutableListOf<Tarefa>()
    private var proximoId = 4

    // Aula 06: ActivityResultLauncher substitui o startActivityForResult depreciado
    private val novaTarefaLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data ?: return@registerForActivityResult
            val titulo     = data.getStringExtra("titulo")    ?: return@registerForActivityResult
            val descricao  = data.getStringExtra("descricao") ?: ""
            val prazo      = data.getStringExtra("prazo")     ?: ""
            val prioridade = Prioridade.valueOf(
                data.getStringExtra("prioridade") ?: Prioridade.MEDIA.name
            )
            val novaTarefa = Tarefa(proximoId++, titulo, descricao, prazo, prioridade)
            tarefas.add(novaTarefa)
            adapter.notifyItemInserted(tarefas.lastIndex)
            atualizarEstadoVazio()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        // Mock data — substituído por banco na Aula 09
        tarefas.addAll(listOf(
            Tarefa(1, "Estudar RecyclerView", prazo = "10/03/2026", prioridade = Prioridade.ALTA),
            Tarefa(2, "Fazer exercício da aula", prazo = "12/03/2026", prioridade = Prioridade.MEDIA),
            Tarefa(3, "Revisar Layouts XML", concluida = true, prioridade = Prioridade.BAIXA)
        ))

        adapter = TarefaAdapter(
            tarefas = tarefas,
            onItemClick = { tarefa ->
                // Aula 06: navega para DetalhesActivity
                val intent = Intent(this, DetalhesActivity::class.java).apply {
                    putExtra("titulo",    tarefa.titulo)
                    putExtra("descricao", tarefa.descricao)
                    putExtra("prazo",     tarefa.prazo)
                    putExtra("prioridade", tarefa.prioridade.name)
                    putExtra("concluida", tarefa.concluida)
                }
                startActivity(intent)
            },
            onCheckClick = { tarefa, checked ->
                tarefa.concluida = checked
                adapter.notifyItemChanged(tarefas.indexOf(tarefa))
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        atualizarEstadoVazio()

        // FAB para nova tarefa
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            novaTarefaLauncher.launch(Intent(this, NovaTarefaActivity::class.java))
        }
    }

    private fun atualizarEstadoVazio() {
        val tvVazio = findViewById<View>(R.id.tvVazio)
        tvVazio.visibility = if (tarefas.isEmpty()) View.VISIBLE else View.GONE
        recyclerView.visibility = if (tarefas.isEmpty()) View.GONE else View.VISIBLE
    }
}

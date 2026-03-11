package com.example.taskmaster

import Tarefa
import TarefaAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TarefaAdapter
    private val tarefas = mutableListOf<Tarefa>()

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
                Toast.makeText(this, "Tarefa: ${tarefa.titulo}", Toast.LENGTH_SHORT).show()
                // Aula 06: startActivity para DetalhesActivity
            },
            onCheckClick = { tarefa, checked ->
                tarefa.concluida = checked
                adapter.notifyItemChanged(tarefas.indexOf(tarefa))
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // FAB para nova tarefa
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            startActivity(Intent(this, NovaTarefaActivity::class.java))
        }
    }
}

class NovaTarefaActivity(activity: MainActivity, java: Any) {


}

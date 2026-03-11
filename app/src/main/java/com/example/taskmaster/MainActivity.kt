package com.example.taskmaster

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TarefaAdapter
    private val tarefas = mutableListOf<Tarefa>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        tarefas.addAll(listOf(
            Tarefa(
                id = 1,
                titulo = "Estudar RecyclerView",
                prazo = "10/03/2026",
                concluida = false,
                prioridade = Prioridade.ALTA
            ),
            Tarefa(
                id = 2,
                titulo = "Fazer exercício da aula",
                prazo = "12/03/2026",
                concluida = false,
                prioridade = Prioridade.MEDIA
            ),
            Tarefa(
                id = 3,
                titulo = "Revisar Layouts XML",
                prazo = "",
                concluida = true,
                prioridade = Prioridade.BAIXA
            )
        ))

        adapter = TarefaAdapter(
            tarefas = tarefas,
            onItemClick = { tarefa ->
                Toast.makeText(this, "Tarefa: ${tarefa.titulo}", Toast.LENGTH_SHORT).show()
            },
            onCheckClick = { tarefa, checked ->
                tarefa.concluida = checked
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            startActivity(Intent(this, NovaTarefaActivity::class.java))
        }
    }
}
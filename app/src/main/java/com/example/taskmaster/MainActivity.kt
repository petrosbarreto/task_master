package com.example.taskmaster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    private val tarefas = mutableListOf<Tarefa>()
    private var proximoId = 4
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (tarefas.isEmpty()) {
            carregarMockInicial()
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(setOf(R.id.listaTarefasFragment))
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        return navHostFragment.navController.navigateUp(appBarConfiguration) ||
            super.onSupportNavigateUp()
    }

    fun obterTarefas(): MutableList<Tarefa> = tarefas

    fun adicionarTarefa(
        titulo: String,
        descricao: String,
        prazo: String,
        prioridade: Prioridade
    ): Tarefa {
        val novaTarefa = Tarefa(
            id = proximoId++,
            titulo = titulo,
            descricao = descricao,
            prazo = prazo,
            prioridade = prioridade
        )
        tarefas.add(novaTarefa)
        return novaTarefa
    }

    private fun carregarMockInicial() {
        tarefas.clear()
        tarefas.addAll(
            listOf(
                Tarefa(1, "Estudar RecyclerView", prazo = "10/03/2026", prioridade = Prioridade.ALTA),
                Tarefa(2, "Fazer exercício da aula", prazo = "12/03/2026", prioridade = Prioridade.MEDIA),
                Tarefa(3, "Revisar Layouts XML", concluida = true, prioridade = Prioridade.BAIXA)
            )
        )
    }
}

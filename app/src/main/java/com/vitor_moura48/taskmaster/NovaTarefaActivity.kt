package com.vitor_moura48.taskmaster // Certifique-se de que o package está aqui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
// O import do android.R SUMIU daqui!
// Se precisar de import manual, use: import com.vitor_moura48.taskmaster.R

class NovaTarefaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_tarefa)
    }
}
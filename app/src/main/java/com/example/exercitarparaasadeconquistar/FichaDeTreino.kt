package com.example.exercitarparaasadeconquistar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FichaDeTreino : AppCompatActivity() {

    private lateinit var userName: String
    private lateinit var taskList: List<Task>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ficha_de_treino)

        userName = intent.getStringExtra("Nome") ?: ""
        val tvUser = findViewById<TextView>(R.id.tvUser)
        tvUser.text = userName

        val recyclerView = findViewById<RecyclerView>(R.id.RecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val imc = intent.getStringExtra("IMC")?.toDoubleOrNull() ?: 0.0
        taskList = selectTaskList(imc)

        recyclerView.adapter = TaskAdapter(taskList)
    }

    fun Perfil(view:View){
        val Peso = intent.getStringExtra("Weight")
        val Altura = intent.getStringExtra("Height")
        val IMC = intent.getStringExtra("IMC")
        val Nome = intent.getStringExtra("Nome")
        val perfil = Intent(this,PerfildoUsuario::class.java)
        perfil.putExtra("UserName",Nome)
        perfil.putExtra("IMC",IMC)
        perfil.putExtra("Weight",Peso)
        perfil.putExtra("Height",Altura)
        startActivity(perfil)
    }

    private fun selectTaskList(imc: Double): List<Task> {
        return when {
            imc <= 24.9 -> Level.IMC_1.taskList
            imc <= 29.9 -> Level.IMC_2.taskList
            else -> Level.IMC_3.taskList
        }
    }

    private enum class Level(val taskList: List<Task>) {
        IMC_1(
            listOf(
                Task("Segunda-feira", "Treino de força com foco nos membros inferiores, com exercícios como agachamentos, leg press, extensão de pernas, entre outros. Realize 3 séries de 8 a 12 repetições de cada exercício."),
                Task("Quarta-feira", "Treino de força com foco nos membros superiores, com exercícios como supino, rosca direta, desenvolvimento de ombros, entre outros. Realize 3 séries de 8 a 12 repetições de cada exercício."),
                Task("Sexta-feira", "Treino aeróbico com duração de 30 a 45 minutos, em que você pode optar por atividades como corrida, ciclismo, elíptico, natação ou caminhada em ritmo moderado.")
            )
        ),
        IMC_2(
            listOf(
                Task("Segunda-feira", "Treino de força com foco nos membros inferiores, com exercícios como agachamentos, leg press, extensão de pernas, entre outros. Realize 3 séries de 12 a 15 repetições de cada exercício."),
                Task("Quarta-feira", "Treino de força com foco nos membros superiores, com exercícios como supino, rosca direta, desenvolvimento de ombros, entre outros. Realize 3 séries de 12 a 15 repetições de cada exercício."),
                Task("Sexta-feira", "Treino aeróbico com duração de 45 a 60 minutos, em que você pode optar por atividades como corrida, ciclismo, elíptico, natação ou caminhada em ritmo moderado.")
            )
        ),
        IMC_3(
            listOf(
                Task("Segunda-feira", "Treino de força com foco nos membros inferiores, com exercícios como agachamentos, leg press, extensão de pernas, entre outros. Realize 3 séries de 15 a 20 repetições de cada exercício."),
                Task("Quarta-feira", "Treino de força com foco nos membros superiores, com exercícios como supino, rosca direta, desenvolvimento de ombros, entre outros. Realize 3 séries de 15 a 20 repetições de cada exercício."),
                Task("Sexta-feira", "Treino aeróbico com duração de 60 a 90 minutos, em que você pode optar por atividades como corrida, ciclismo, elíptico, natação ou caminhada em ritmo moderado.")
            )
        )
    }
}

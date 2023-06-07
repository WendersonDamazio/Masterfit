package com.example.exercitarparaasadeconquistar

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class PerfildoUsuario : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfildo_usuario)

        val Peso = intent.getStringExtra("Weight")
        val Altura = intent.getStringExtra("Height")
        val Nome = intent.getStringExtra("UserName")

        val tvW = findViewById<TextView>(R.id.tvW)
        val tvH = findViewById<TextView>(R.id.tvH)
        val tvN = findViewById<TextView>(R.id.tvN)

        tvW.setText(getString(R.string.weight__) +" $Peso")
        tvH.setText(getString(R.string.height__) +" $Altura")
        tvN.setText(getString(R.string.user) +" $Nome")
    }
    fun MostreOIMC(view: View){
        val IMC = intent.getStringExtra("IMC")
        val BMI = findViewById<TextView>(R.id.BMI)
        BMI.setText("$IMC")
    }
    fun Mudança(view: View){
        val MudarUser= Intent(this,RegistroUsuario::class.java)
        startActivity(MudarUser)
    }
}
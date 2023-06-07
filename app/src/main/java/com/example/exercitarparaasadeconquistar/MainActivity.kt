package com.example.exercitarparaasadeconquistar

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var edName: EditText
    private lateinit var edSenha: EditText
    private lateinit var tvErro: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linkText = findViewById<TextView>(R.id.SignUp)
        linkText.setOnClickListener {
            val intent = Intent(this,RegistroUsuario::class.java)
            startActivity(intent)
        }
        edName = findViewById(R.id.edName)
        edSenha = findViewById(R.id.edSenha)
        tvErro = findViewById(R.id.tvErro)
    }

    fun PrxPG(view: View){
        val Peso = intent.getStringExtra("Weight")
        val Altura = intent.getStringExtra("Height")
        val IMC = intent.getStringExtra("IMC")
        val Nome = intent.getStringExtra("UserName")
        val Senha = intent.getStringExtra("UserPassword")
        val Ficha = Intent(this,FichaDeTreino::class.java)
        if (edName.text.toString()=="Professor" &&  edSenha.text.toString()=="123"){
            val Dev = Intent(this,ListadeUsuarios::class.java)
            startActivity(Dev)
        }else{
            if (edName.text.toString()==Nome &&  edSenha.text.toString()==Senha){
                val texto = edName.text.toString()
                Ficha.putExtra("Nome",texto)
                Ficha.putExtra("IMC",IMC)
                Ficha.putExtra("Weight",Peso)
                Ficha.putExtra("Height",Altura)
                startActivity(Ficha)
            }else if(edName.text.toString()==Nome){
                edSenha.setText("")
                tvErro.visibility = View.VISIBLE
            }else{
                edName.setText("")
                edSenha.setText("")
                tvErro.visibility = View.VISIBLE
            }
        }
    }
}
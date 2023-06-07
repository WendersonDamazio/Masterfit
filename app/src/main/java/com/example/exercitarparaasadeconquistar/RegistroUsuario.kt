package com.example.exercitarparaasadeconquistar

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*


class RegistroUsuario : AppCompatActivity() {
    private lateinit var edN: EditText
    private lateinit var edS: EditText
    private lateinit var ConfirmaS: EditText
    private lateinit var Erro: TextView
    private lateinit var Weight : EditText
    private lateinit var Height : EditText
    private lateinit var dbRef : DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)

        Erro = findViewById(R.id.Erro)
        edN = findViewById(R.id.edN)
        edS = findViewById(R.id.edS)
        ConfirmaS = findViewById(R.id.ConfirmaS)
        Weight = findViewById(R.id.Weight)
        Height = findViewById(R.id.Height)

        /*val Nome = edN.text.toString()
        val Senha = edS.text.toString()
        val Peso = Weight.text.toString()
        val Altura = Height.text.toString()
        val IMC = String.format(Locale.US, "%.2f",(Peso.toDouble()/(Altura.toDouble()*2)))*/
    }

    fun signup(view:View){
        if(edN.text.toString().isEmpty()||edS.text.toString().isEmpty()/*||ConfirmaS.text.toString().isEmpty()*/||Weight.text.toString().isEmpty()||Height.text.toString().isEmpty()){
            Erro.setText(getString(R.string.mensagem_erro))
            Erro.visibility = View.VISIBLE
        }else if (ConfirmaS.text.toString() == edS.text.toString()){
            val Nome = edN.text.toString()
            val Senha = edS.text.toString()
            val Altura = Height.text.toString()
            val Peso = Weight.text.toString()
            val IMC = String.format(Locale.US, "%.2f",(Weight.text.toString().toDouble()/(Height.text.toString().toDouble()*2)))
            val Form = Intent(this,MainActivity::class.java)
            Form.putExtra("Weight",Peso)
            Form.putExtra("Height",Altura)
            Form.putExtra("IMC",IMC)
            Form.putExtra("UserName",Nome)
            Form.putExtra("UserPassword",Senha)
            startActivity(Form)

            dbRef = FirebaseDatabase.getInstance().getReference("Usuario")
            val userId = dbRef.push().key!!
            val Usuario = CadastroUsuario(userId, Nome, Senha, Peso, Altura, IMC)
            dbRef.child(userId).setValue(Usuario)
                .addOnCompleteListener{
                    Toast.makeText(this, "Cadastro realizado", Toast.LENGTH_SHORT).show()

                    edN.setText("")
                    edS.setText("")
                    ConfirmaS.setText("")
                    Weight.setText("")
                    Height.setText("")

                }.addOnFailureListener{err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
                }

        } else{
            edS.setText("")
            ConfirmaS.setText("")
            Erro.setText(getString(R.string.unconfirmed_password))
            Erro.visibility = View.VISIBLE
        }


    }

}
package com.daniel.electrixdrive

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class VisualizeEditeDadosActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualize_edite_dados)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        if (auth.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        val modelo = intent.getStringExtra("modelo") ?: ""
        val ano = intent.getIntExtra("ano", 0)
        val eletrico = intent.getStringExtra("eletrico") ?: ""
        val economia = intent.getStringExtra("economia") ?: ""
        val beneficios = intent.getStringExtra("beneficios") ?: ""
        val impactoAmbiental = intent.getStringExtra("impacto_ambiental") ?: ""
        val combustivel = intent.getStringExtra("combustivel") ?: ""

        val btnVoltar: TextView = findViewById(R.id.btnVoltar)
        btnVoltar.setOnClickListener {
            finish()
        }

        val etVeiculoModeloAno: EditText = findViewById(R.id.etVeiculoModeloAno)
        val etVeiculoCombustivel: EditText = findViewById(R.id.etVeiculoCombustivel)
        val etModeloCarroEletrico: EditText = findViewById(R.id.etModeloCarroEletrico)
        val etEconomiaCarroEletrico: EditText = findViewById(R.id.etEconomiaCarroEletrico)
        val etBeneficiosCarroEletrico: EditText = findViewById(R.id.etBeneficiosCarroEletrico)
        val etImpactoAmbiental: EditText = findViewById(R.id.etImpactoAmbiental)

        etVeiculoModeloAno.setText("$modelo, $ano")
        etVeiculoCombustivel.setText(combustivel)
        etModeloCarroEletrico.setText(eletrico)
        etEconomiaCarroEletrico.setText(economia)
        etBeneficiosCarroEletrico.setText(beneficios)
        etImpactoAmbiental.setText(impactoAmbiental)

        val btnConfirmar: Button = findViewById(R.id.btnConfirmar)
        btnConfirmar.setOnClickListener {
            salvarDadosFirestore(
                etVeiculoModeloAno.text.toString(),
                etVeiculoCombustivel.text.toString(),
                etModeloCarroEletrico.text.toString(),
                etEconomiaCarroEletrico.text.toString(),
                etBeneficiosCarroEletrico.text.toString(),
                etImpactoAmbiental.text.toString()
            )
        }
    }

    private fun salvarDadosFirestore(
        modeloAno: String,
        combustivel: String,
        eletrico: String,
        economia: String,
        beneficios: String,
        impactoAmbiental: String
    ) {
        val carroData = hashMapOf(
            "modelo_ano" to modeloAno,
            "combustivel" to combustivel,
            "eletrico" to eletrico,
            "economia" to economia,
            "beneficios" to beneficios,
            "impacto_ambiental" to impactoAmbiental
        )

        val userId = auth.currentUser!!.uid
        val veiculosCollection =
            firestore.collection("users").document(userId).collection("veiculos")

        val carroDocument = veiculosCollection.document()

        carroDocument.set(carroData)
            .addOnSuccessListener {
                Toast.makeText(this, "Dados salvos com sucesso.", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Erro ao salvar os dados: ${it.message}", Toast.LENGTH_LONG)
                    .show()
            }
    }
}

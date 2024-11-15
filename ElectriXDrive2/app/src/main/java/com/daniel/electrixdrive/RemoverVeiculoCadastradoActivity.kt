package com.daniel.electrixdrive

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RemoverVeiculoCadastradoActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var veiculosAdapter: ArrayAdapter<String>
    private var veiculosList = mutableListOf<String>()
    private var veiculosKeys = mutableListOf<String>()
    private var selectedVehicleIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remover_veiculo_cadastrado)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        if (auth.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        val topGifImage: ImageView = findViewById(R.id.topGifImage)
        Glide.with(this)
            .asGif()
            .load(R.drawable.telalistar)
            .into(topGifImage)

        val btnVoltar: TextView = findViewById(R.id.btnVoltar)
        btnVoltar.setOnClickListener {
            finish()
        }

        listView = findViewById(R.id.carListView)
        veiculosAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, veiculosList)
        listView.adapter = veiculosAdapter
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE

        fetchVeiculosFromFirestore()

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            selectedVehicleIndex = position
        }

        val btnRemoverVeiculo: Button = findViewById(R.id.btnRemoverVeiculo)
        btnRemoverVeiculo.setOnClickListener {
            if (selectedVehicleIndex != -1) {
                removerVeiculo(selectedVehicleIndex)
            } else {
                Toast.makeText(
                    this,
                    "Por favor, selecione um veículo para remover.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun fetchVeiculosFromFirestore() {
        val userId = auth.currentUser?.uid ?: return

        firestore.collection("users").document(userId).collection("veiculos")
            .get()
            .addOnSuccessListener { querySnapshot ->
                veiculosList.clear()
                veiculosKeys.clear()

                for (document in querySnapshot) {
                    val modeloAno = document.getString("modelo_ano") ?: "Sem modelo/ano"
                    val key = document.id

                    veiculosList.add(modeloAno)
                    veiculosKeys.add(key)
                }

                veiculosAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Erro ao carregar dados: ${it.message}", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    private fun removerVeiculo(index: Int) {
        val userId = auth.currentUser?.uid ?: return
        val veiculoKey = veiculosKeys[index]

        firestore.collection("users").document(userId).collection("veiculos").document(veiculoKey)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(this, "Veículo removido com sucesso.", Toast.LENGTH_SHORT).show()

                veiculosList.removeAt(index)
                veiculosKeys.removeAt(index)
                veiculosAdapter.notifyDataSetChanged()
                selectedVehicleIndex = -1
            }
            .addOnFailureListener {
                Toast.makeText(this, "Erro ao remover o veículo: ${it.message}", Toast.LENGTH_SHORT)
                    .show()
            }
    }
}

package com.daniel.electrixdrive

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.bumptech.glide.Glide
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream

class CompararEficienciaActivity : AppCompatActivity() {

    lateinit var carrosArray: JSONArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comparar_eficiencia)

        try {
            val inputStream: InputStream = assets.open("carros.json")
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            carrosArray = JSONObject(jsonString).getJSONArray("carros")
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(
                this,
                "Erro ao carregar os dados dos carros. Verifique o arquivo JSON.",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        val mainView = findViewById<View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(
                left = systemBars.left,
                top = systemBars.top,
                right = systemBars.right,
                bottom = systemBars.bottom
            )
            insets
        }

        val btnVoltar: TextView = findViewById(R.id.btnVoltar)
        btnVoltar.setOnClickListener {
            finish()
        }

        val topGifImage: ImageView = findViewById(R.id.topGifImage)
        Glide.with(this)
            .asGif()
            .load(R.drawable.car)
            .into(topGifImage)

        val spModeloCarro: Spinner = findViewById(R.id.spModeloCarro)
        ArrayAdapter.createFromResource(
            this,
            R.array.modelos_carros,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spModeloCarro.adapter = adapter
        }

        val spAnoCarro: Spinner = findViewById(R.id.spAnoCarro)
        ArrayAdapter.createFromResource(
            this,
            R.array.anos_carros,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spAnoCarro.adapter = adapter
        }

        val btnSugerirCarro: Button = findViewById(R.id.btnSugerirCarro)
        btnSugerirCarro.setOnClickListener {
            val modeloCarro = spModeloCarro.selectedItem?.toString()?.trim()
            val anoCarro = spAnoCarro.selectedItem?.toString()?.toIntOrNull()

            if (modeloCarro != null && modeloCarro.isNotEmpty() && anoCarro != null) {
                val sugestao = buscarCarroEletrico(modeloCarro, anoCarro)

                if (sugestao != null) {
                    val eletricoEquivalente = sugestao.getString("eletrico_equivalente")
                    val beneficiosConsumo = sugestao.getString("beneficios_consumo")
                    val beneficiosManutencao = sugestao.getString("beneficios_manutencao")
                    val impactoAmbiental = sugestao.getString("impacto_ambiental")

                    val intent = Intent(this, VisualizeEditeDadosActivity::class.java)
                    intent.putExtra("modelo", modeloCarro)
                    intent.putExtra("ano", anoCarro)
                    intent.putExtra("eletrico", eletricoEquivalente)
                    intent.putExtra("economia", beneficiosConsumo)
                    intent.putExtra("beneficios", beneficiosManutencao)
                    intent.putExtra("impacto_ambiental", impactoAmbiental)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this,
                        "Nenhuma sugestão encontrada para o modelo e ano informados.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(
                    this,
                    "Por favor, selecione um modelo e um ano válidos.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun buscarCarroEletrico(modelo: String, ano: Int): JSONObject? {
        try {
            for (i in 0 until carrosArray.length()) {
                val carro = carrosArray.getJSONObject(i)
                if (carro.getString("modelo")
                        .equals(modelo, ignoreCase = true) && carro.getInt("ano") == ano
                ) {
                    return carro
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}

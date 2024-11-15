package com.daniel.electrixdrive

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth

class MenuActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        auth = FirebaseAuth.getInstance()

        val topGifImage: ImageView = findViewById(R.id.topGifImage)
        Glide.with(this)
            .asGif()
            .load(R.drawable.carmenu)
            .into(topGifImage)

        val btnVoltar: TextView = findViewById(R.id.btnVoltar)
        btnVoltar.setOnClickListener {
            finish()
        }

        val compareEfficiencyCard: CardView = findViewById(R.id.compareEfficiencyCard)
        compareEfficiencyCard.setOnClickListener {
            val intent = Intent(this, CompararEficienciaActivity::class.java)
            startActivity(intent)
        }

        val removeVehicleCard: CardView = findViewById(R.id.removeVehicleCard)
        removeVehicleCard.setOnClickListener {
            val intent = Intent(this, RemoverVeiculoCadastradoActivity::class.java)
            startActivity(intent)
        }

        val changePasswordCard: CardView = findViewById(R.id.changePasswordCard)
        changePasswordCard.setOnClickListener {
            val intent = Intent(this, AlterarSenhaActivity::class.java)
            startActivity(intent)
        }

        val btnLogout: Button = findViewById(R.id.btnLogout)
        btnLogout.setOnClickListener {
            auth.signOut()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

package com.daniel.electrixdrive

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val carGif: ImageView = findViewById(R.id.btnCarGif)
        Glide.with(this)
            .asGif()
            .load(R.drawable.carcapa)
            .into(carGif)

        val continueButton: Button = findViewById(R.id.btnContinue)
        continueButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}

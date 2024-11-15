package com.daniel.electrixdrive

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class AlterarSenhaActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_alterar_senha)

        auth = FirebaseAuth.getInstance()

        val topImage: ImageView = findViewById(R.id.topGifImage)
        Glide.with(this)
            .asGif()
            .load(R.drawable.trocarsenha)
            .into(topImage)

        val btnVoltar: TextView = findViewById(R.id.btnVoltar)
        btnVoltar.setOnClickListener {
            finish()
        }

        val etSenhaAtual: EditText = findViewById(R.id.currentPasswordField)
        val etNovaSenha: EditText = findViewById(R.id.passwordField)
        val etConfirmeSenha: EditText = findViewById(R.id.confirmPasswordField)

        val btnConfirmar: Button = findViewById(R.id.btnConfirmar)
        btnConfirmar.setOnClickListener {
            val senhaAtual = etSenhaAtual.text.toString().trim()
            val novaSenha = etNovaSenha.text.toString().trim()
            val confirmeSenha = etConfirmeSenha.text.toString().trim()

            if (senhaAtual.isEmpty() || novaSenha.isEmpty() || confirmeSenha.isEmpty()) {
                Toast.makeText(this, "Todos os campos devem ser preenchidos.", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            if (novaSenha != confirmeSenha) {
                Toast.makeText(this, "As senhas não coincidem.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (novaSenha.length < 6) {
                Toast.makeText(this, "A senha deve ter no mínimo 6 caracteres.", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val user = auth.currentUser
            if (user != null) {
                val credential = EmailAuthProvider.getCredential(user.email!!, senhaAtual)
                user.reauthenticate(credential)
                    .addOnCompleteListener { authTask ->
                        if (authTask.isSuccessful) {
                            user.updatePassword(novaSenha)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        showSuccessDialog()
                                    } else {
                                        Toast.makeText(
                                            this,
                                            "Erro ao atualizar a senha: ${task.exception?.message}",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                        } else {
                            Toast.makeText(this, "Senha atual incorreta.", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(
                    this,
                    "Usuário não encontrado. Faça login novamente.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun showSuccessDialog() {
        AlertDialog.Builder(this)
            .setTitle("Senha Alterada")
            .setMessage("Sua senha foi alterada com sucesso. Faça login com a nova senha.")
            .setPositiveButton("OK") { _, _ ->
                auth.signOut()
                goToLogin()
            }
            .setCancelable(false)
            .show()
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}

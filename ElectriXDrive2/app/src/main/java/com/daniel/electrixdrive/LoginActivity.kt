package com.daniel.electrixdrive

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        if (auth.currentUser != null) {
            goToMenu()
        }

        val topImage: ImageView = findViewById(R.id.topImage)
        Glide.with(this)
            .asGif()
            .load(R.drawable.carlogin)
            .into(topImage)

        val enterButton: Button = findViewById(R.id.btnEntrar)
        val etEmail: EditText = findViewById(R.id.emailField)
        val etPassword: EditText = findViewById(R.id.passwordField)

        enterButton.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (!validateCredentials(email, password)) return@setOnClickListener

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        saveUserToFirestoreIfNew()
                        goToMenu()
                    } else {
                        createNewUser(email, password)
                    }
                }
        }
    }

    private fun createNewUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    saveUserToFirestoreIfNew()
                    Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT)
                        .show()
                    goToMenu()
                } else {
                    Toast.makeText(
                        this,
                        "Falha ao cadastrar: ${task.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun goToMenu() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun saveUserToFirestoreIfNew() {
        val user = auth.currentUser
        if (user != null) {
            val userId = user.uid
            val email = user.email ?: ""

            firestore.collection("users").document(userId).get()
                .addOnSuccessListener { document ->
                    if (!document.exists()) {
                        val userMap = hashMapOf(
                            "userId" to userId,
                            "email" to email
                        )

                        firestore.collection("users").document(userId).set(userMap)
                            .addOnSuccessListener {
                                Toast.makeText(
                                    this,
                                    "Informações do usuário salvas com sucesso!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            .addOnFailureListener {
                                Toast.makeText(
                                    this,
                                    "Falha ao salvar informações do usuário no Firestore.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(
                        this,
                        "Erro ao verificar usuário no Firestore.",
                        Toast.LENGTH_LONG
                    ).show()
                }
        }
    }

    private fun validateCredentials(email: String, password: String): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Por favor, insira um email válido", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.length < 6) {
            Toast.makeText(this, "A senha deve ter no mínimo 6 caracteres", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }
}

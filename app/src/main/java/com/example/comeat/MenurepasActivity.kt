package com.example.comeat

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenurepasActivity : AppCompatActivity() {

    private var idUtilisateur: Int = -1

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menupage)  // Vérifie que c'est bien ton layout

        idUtilisateur = intent.getIntExtra("idUtilisateur", -1)

        Log.d("MenurepasActivity", "ID Utilisateur reçu : $idUtilisateur")

        // Trouver les boutons après setContentView()
        val boutonListeRepas: Button = findViewById(R.id.liste_repas)
        val boutonRepasAvenir: Button = findViewById(R.id.repas_avenir)

        // Simule une condition de connexion (à remplacer par ta logique réelle)


        // Ajouter des écouteurs de clics pour les boutons
        boutonListeRepas.setOnClickListener {
            val intent = Intent(this, RepasActivity::class.java)
            intent.putExtra("idUtilisateur", idUtilisateur)
            startActivity(intent)
        }

        boutonRepasAvenir.setOnClickListener {
            Log.d("MenurepasActivity", "Bouton repas avenir cliqué")
            val intent = Intent(this, RechercheRepasActivity::class.java)
            intent.putExtra("idUtilisateur", idUtilisateur)
            startActivity(intent)
        }

    }



}

package com.example.comeat

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RepasActivity : AppCompatActivity() {

    private var idUtilisateur: Int = -1

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_repas)

        // Récupérer l'idUtilisateur passé depuis l'activité précédente
        idUtilisateur = intent.getIntExtra("idUtilisateur", -1)

        Log.d("RepasActivity", "ID Utilisateur reçu : $idUtilisateur")

        // Vérifier que l'idUtilisateur n'est pas -1
        if (idUtilisateur != -1) {
            val rvRepas = findViewById<RecyclerView>(R.id.rv_repas)
            rvRepas.layoutManager = LinearLayoutManager(this)

            // Récupérer les repas associés à l'utilisateur
            val repasList = Modele.getSesRepas(idUtilisateur)

            // Vérifier que des repas sont récupérés
            Log.d("RepasActivity", "Repas pour l'utilisateur $idUtilisateur : ${repasList.size}")

            rvRepas.adapter = RepasAdapter(repasList)  // Assure-toi que RepasAdapter est bien configuré
        }
    }
}

package com.example.comeat

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate

class ListeRepasActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_repas_propose) // ici c'est ton vrai layout de l'écran

        val specialiteRepas = intent.getStringExtra("specialite_repas") ?: ""
        val dateString = intent.getStringExtra("date_repas") ?: ""

        val dateRepas = if (dateString.isNotEmpty()) {
            LocalDate.parse(dateString)
        } else {
            LocalDate.now()
        }

        val idUtilisateur = Modele.utilisateurConnecte.id

        val rvRepas = findViewById<RecyclerView>(R.id.rv_repas_proposes)
        rvRepas.layoutManager = LinearLayoutManager(this)

        val adapterRepas = RepasProposesAdapter(
            Modele.getRepasByDateSpecialite(specialiteRepas, dateRepas, idUtilisateur)
        ) { repas ->
            Toast.makeText(this, "Repas sélectionné: $repas", Toast.LENGTH_SHORT).show()
        }

        rvRepas.adapter = adapterRepas
    }
}

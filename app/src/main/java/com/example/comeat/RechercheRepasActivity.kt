package com.example.comeat

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RechercheRepasActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selection_date_repas)

        // Récupérer le Spinner
        val spnSpecialite: Spinner = findViewById(R.id.select_specialite)

        // Récupérer la liste des spécialités
        val specialites = Modele.getSpecialites().map { it.libelle }

        // Créer l'adaptateur pour le Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, specialites)

        // Définir le style du menu déroulant
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Associer l'adaptateur au Spinner
        spnSpecialite.adapter = adapter

        // Ajouter un écouteur pour récupérer l'élément sélectionné
        spnSpecialite.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedSpecialite = specialites[position]
                println("Spécialité sélectionnée : $selectedSpecialite")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Ne rien faire
            }
        }

        val btnDate: Button = findViewById(R.id.select_date)
        val tvDate: TextView = findViewById(R.id.aff_date)

        btnDate.setOnClickListener {
            val dateCourante = LocalDate.now()
            val annee = dateCourante.year
            val mois = dateCourante.monthValue - 1
            val jour = dateCourante.dayOfMonth
            val datePickerDialog = DatePickerDialog(
                this,
                { view, anneeSelect, moisSelect, jourSelect ->

                    val dateSelectionnee = LocalDate.of(
                        anneeSelect,
                        moisSelect + 1,
                        jourSelect
                    )
                    val formateur = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    val dateFormatee = dateSelectionnee.format(formateur)
                    tvDate.text = dateFormatee
                    // TRAITEMENT :
                    // - Affectation de la date formatée à l'attribut dateRepas
                    // - Si libelleSpecialite != "" :
                    // Activer le bouton de validation
                },
                annee, mois, jour
            )
            datePickerDialog.show()
        }
//
//        val btnValider: Button = findViewById(R.id.valider)
//
//        btnValider.setOnClickListener {

////            val intent = Intent(this, ListeRepasActivity::class.java)
////
////            intent.putExtra("specialite_repas", specialitesRepas)
////            intent.putExtra("date_repas", dateRepas.toString())
////
////            startActivity(intent)
////        }
//    }
//    }










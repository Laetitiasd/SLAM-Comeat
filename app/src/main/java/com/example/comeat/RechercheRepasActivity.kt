package com.example.comeat

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
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

    private var specialitesRepas: String = ""
    private var dateRepas: LocalDate? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selection_date_repas)

        val spnSpecialite: Spinner = findViewById(R.id.select_specialite)
        val btnDate: Button = findViewById(R.id.select_date)
        val tvDate: TextView = findViewById(R.id.aff_date)
        val btnValider: Button = findViewById(R.id.valider)

        val specialites = Modele.getSpecialites().map { it.libelle }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, specialites)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnSpecialite.adapter = adapter

        spnSpecialite.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                specialitesRepas = specialites[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        btnDate.setOnClickListener {
            val dateCourante = LocalDate.now()
            val datePickerDialog = DatePickerDialog(
                this,
                { view, anneeSelect, moisSelect, jourSelect ->
                    dateRepas = LocalDate.of(anneeSelect, moisSelect + 1, jourSelect)
                    val dateFormatee = dateRepas!!.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    tvDate.text = dateFormatee
                },
                dateCourante.year,
                dateCourante.monthValue - 1,
                dateCourante.dayOfMonth
            )
            datePickerDialog.show()
        }

        btnValider.setOnClickListener {
            if (specialitesRepas.isNotEmpty() && dateRepas != null) {
                val intent = Intent(this, ListeRepasActivity::class.java)
                intent.putExtra("specialite_repas", specialitesRepas)
                intent.putExtra("date_repas", dateRepas.toString())
                startActivity(intent)
            }
        }
    }
}










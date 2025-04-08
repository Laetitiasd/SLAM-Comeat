package com.example.comeat


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Appliquer les marges pour éviter que le contenu soit sous la barre de statut
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Récupération des éléments graphiques
        val saisieEmail: EditText = findViewById(R.id.email)
        val saisieMdp: EditText = findViewById(R.id.mdp)
        val boutonConnecter: Button = findViewById(R.id.connecter)
        val boutonAnnuler: Button = findViewById(R.id.annuler)

        // Listener sur le bouton "Connecter"
        boutonConnecter.setOnClickListener {
            val email: String = saisieEmail.text.toString()
            val mdp: String = saisieMdp.text.toString()

            Log.d("ACT_CONN", "Connexion : $email / $mdp")

            // Vérification si l'utilisateur existe
            val utilisateur = Modele.findUtilisateur(email, mdp)
            if (utilisateur != null) {
                // Ouvrir la session pour l'utilisateur
                Session.ouvrir(utilisateur)

                // Aller à com.example.comeat.MenurepasActivity
                val intent = Intent(this, MenurepasActivity::class.java)
                intent.putExtra("idUtilisateur", utilisateur.id)
                startActivity(intent)
            } else {
                Log.d("ACT_CONN", "Échec de connexion : email ou mot de passe incorrect")
                saisieEmail.error = "Email ou mot de passe incorrect"
                saisieMdp.error = "Email ou mot de passe incorrect"
            }
        }

        // Listener sur le bouton "Annuler"
        boutonAnnuler.setOnClickListener {
            saisieEmail.text = Editable.Factory.getInstance().newEditable("")
            saisieMdp.text = Editable.Factory.getInstance().newEditable("")
            Log.d("ACT_CONN", "Annulation")
        }
    }




}

package com.example.comeat

// Importation des classes nécessaires pour la vue et le recyclage d'éléments
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adaptateur permettant de lier une liste de repas (lesRepas) à un RecyclerView
class RepasProposesAdapter(
    private val lesRepas: List<Repas>, // Liste des repas à afficher
    private val onItemClickListener: (Repas) -> Unit // Fonction à exécuter quand on clique sur un repas
) : RecyclerView.Adapter<RepasProposesAdapter.RepasViewHolder>() {

    // Classe qui représente chaque élément (card / ligne) de la liste RecyclerView
    class RepasViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        // Récupération des TextView de l'item XML (item_repas_propose.xml)
        val tvIdRepas: TextView = view.findViewById(R.id.tv_id_repas)
        val tvHote: TextView = view.findViewById(R.id.tv_hote)

        // Fonction pour lier les données d'un repas à un item de la liste
        fun bind(repas: Repas, onItemClickListener: (Repas) -> Unit) {
            // Affiche l'id du repas
            tvIdRepas.text = repas.id.toString()
            // Affiche le nom de l'hôte qui propose le repas
            tvHote.text = repas.hote.nom

            // Quand on clique sur un item → déclenche l'action passée en paramètre
            view.setOnClickListener {
                onItemClickListener(repas)
            }
        }
    }

    // Crée et retourne un ViewHolder (une carte / ligne) à partir du layout item_repas_propose
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_repas_propose, parent, false
        )
        return RepasViewHolder(view)
    }

    // Retourne le nombre d'éléments à afficher dans la liste
    override fun getItemCount(): Int = lesRepas.size

    // Remplit les données d'un ViewHolder à une position donnée
    override fun onBindViewHolder(holder: RepasViewHolder, position: Int) {
        val repas = lesRepas[position] // Récupère le repas à cette position
        holder.bind(repas, onItemClickListener) // Lie les données et le clic
    }
}

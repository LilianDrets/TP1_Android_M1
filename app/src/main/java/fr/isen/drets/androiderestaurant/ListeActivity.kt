package fr.isen.drets.androiderestaurant

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListeActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste)

        val Titre = intent.getStringExtra("Titre")
        val textViewTitre = findViewById<TextView>(R.id.TitreListe)

        var Liste: List<String> = emptyList()
        if(Titre == "Entrees") {
            Liste = resources.getStringArray(R.array.Entrees).toList()
            textViewTitre.setText("Entrées")

        }
        else if (Titre == "Desserts") {
            Liste = resources.getStringArray(R.array.Desserts).toList()
            textViewTitre.setText("Desserts")
        }
        else if (Titre == "Plats") {
            Liste = resources.getStringArray(R.array.Plats).toList()
            textViewTitre.setText("Plats")
        }

        val recyclerViewListe = findViewById<RecyclerView>(R.id.Liste)
        val adapterListe = ListeAdapter(Liste)
        recyclerViewListe.adapter = adapterListe
    }
}
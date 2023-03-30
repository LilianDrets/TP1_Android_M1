package fr.isen.drets.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.gson.Gson
import java.io.File

class Panier_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pannier)

        val gson = Gson()
        val file = File(this.filesDir, "panier.json")
        val json = file.readText()
        val commandes = gson.fromJson(json, Array<DetailsActivity.Commande>::class.java)

        val affichage = findViewById<TextView>(R.id.listeChariot)

        var txt = ""

        for (commande in commandes) {
            txt = txt+commande.id+"\t"+commande.nb+"\n"
        }

        affichage.setText(txt)

    }
}
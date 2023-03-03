package fr.isen.drets.androiderestaurant

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Request
import com.android.volley.Response
import com.google.gson.Gson
import org.json.JSONObject

class ListeActivity : AppCompatActivity() {

    data class ItemMenu(val name: String, val category: String)

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

        /*val url = "http://test.api.catering.bluecodegames.com/menu"

        val queue = Volley.newRequestQueue(this)
        val jsonBody = JSONObject().put("id_shop", 1)
        val request = JsonObjectRequest(Request.Method.POST, url, jsonBody,
            Response.Listener { response ->

                val gson = Gson()
                val menu = gson.fromJson(response.toString(), Array<ItemMenu>::class.java)

                Liste = menu.filter { it.category == Titre }.map { it.name }

                val recyclerViewListe = findViewById<RecyclerView>(R.id.Liste)
                val adapterListe = ListeAdapter(Liste)
                recyclerViewListe.adapter = adapterListe
            },
            Response.ErrorListener { error ->

            })
        queue.add(request)*/
        val recyclerViewListe = findViewById<RecyclerView>(R.id.Liste)
        val adapterListe = ListeAdapter(Liste)
        recyclerViewListe.adapter = adapterListe
    }
}

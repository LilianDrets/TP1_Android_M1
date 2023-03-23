package fr.isen.drets.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.google.gson.Gson
import org.json.JSONObject



class ListeActivity : AppCompatActivity() {
    data class Ingredient(
        val id: String,
        val id_shop: String,
        val name_fr: String,
        val name_en: String,
        val create_date: String,
        val update_date: String,
        val id_pizza: String
    )

    data class Price(
        val id: String,
        val id_pizza: String,
        val id_size: String,
        val price: String,
        val create_date: String,
        val update_date: String,
        val size: String
    )

    data class Item(
        val id: String,
        val name_fr: String,
        val name_en: String,
        val id_category: String,
        val categ_name_fr: String,
        val categ_name_en: String,
        val images: List<String>,
        val ingredients: List<Ingredient>,
        val prices: List<Price>
    )

    data class Data(
        val name_fr: String,
        val name_en: String,
        val items: List<Item>
    )

    data class Response(
        val data: List<Data>
    )

    data class ItemMenu(val name: String, val category: String, )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste)

        val Titre = intent.getStringExtra("Titre")
        val textViewTitre = findViewById<TextView>(R.id.TitreListe)

        var ListeNom: MutableList<String> = mutableListOf()
        val ListePrix : MutableList<List<Price>> = mutableListOf()
        val ListeURLImage : MutableList<List<String>> = mutableListOf()

        if(Titre == "Entrées") {
            //Liste = resources.getStringArray(R.array.Entrees).toList()
            textViewTitre.setText("Entrées")
        }
        else if (Titre == "Desserts") {
            //Liste = resources.getStringArray(R.array.Desserts).toList()
            textViewTitre.setText("Desserts")
        }
        else if (Titre == "Plats") {
            //Liste = resources.getStringArray(R.array.Plats).toList()
            textViewTitre.setText("Plats")
        }

        val queue: RequestQueue = Volley.newRequestQueue(applicationContext)

        val jsonReq = JSONObject().put("id_shop", 1)

        val request = JsonObjectRequest(Request.Method.POST, "http://test.api.catering.bluecodegames.com/menu", jsonReq, { response ->
            Log.w("TAG", "RESPONSE IS $response")
            val gson = Gson()
            val rep = gson.fromJson(response.toString(), Response::class.java)
            //val dishes = data.dtata[0].items.map{it.categNameFr ?: ""}.toList() as ArrayList
            //adapter.updateDishes(dishs)
            for (data in rep.data) {
                if (data.name_fr == Titre) {
                    for (item in data.items) {
                        ListeNom.add(item.name_fr);
                        ListePrix.add(item.prices)
                        ListeURLImage.add(item.images)
                    }

                }
            }
            val recyclerViewListe = findViewById<RecyclerView>(R.id.Liste)
            val adapterListe = ListeAdapter(ListeNom, ListePrix, ListeURLImage)
            recyclerViewListe.adapter = adapterListe
        }, { error ->
            Log.w("TAG", "RESPONSE IS $error")
        })
        queue.add(request)
    }
}

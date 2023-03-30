package fr.isen.drets.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
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
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: ""
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(id_shop)
            parcel.writeString(name_fr)
            parcel.writeString(name_en)
            parcel.writeString(create_date)
            parcel.writeString(update_date)
            parcel.writeString(id_pizza)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Ingredient> {
            override fun createFromParcel(parcel: Parcel): Ingredient {
                return Ingredient(parcel)
            }

            override fun newArray(size: Int): Array<Ingredient?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class Price(
        val id: String,
        val id_pizza: String,
        val id_size: String,
        val price: String,
        val create_date: String,
        val update_date: String,
        val size: String
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: ""
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(id_pizza)
            parcel.writeString(id_size)
            parcel.writeString(price)
            parcel.writeString(create_date)
            parcel.writeString(update_date)
            parcel.writeString(size)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Price> {
            override fun createFromParcel(parcel: Parcel): Price {
                return Price(parcel)
            }

            override fun newArray(size: Int): Array<Price?> {
                return arrayOfNulls(size)
            }
        }
    }


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
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.createStringArrayList() ?: emptyList(),
            parcel.createTypedArrayList(Ingredient.CREATOR) ?: emptyList(),
            parcel.createTypedArrayList(Price.CREATOR) ?: emptyList()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(name_fr)
            parcel.writeString(name_en)
            parcel.writeString(id_category)
            parcel.writeString(categ_name_fr)
            parcel.writeString(categ_name_en)
            parcel.writeStringList(images)
            parcel.writeTypedList(ingredients)
            parcel.writeTypedList(prices)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Item> {
            override fun createFromParcel(parcel: Parcel): Item {
                return Item(parcel)
            }

            override fun newArray(size: Int): Array<Item?> {
                return arrayOfNulls(size)
            }
        }
    }


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

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val btnChariot = findViewById<View>(R.id.chariot)
        btnChariot.setOnClickListener{
            val pannierActivite = Intent(this, Panier_Activity::class.java)
            startActivity(pannierActivite)
        }

        val Titre = intent.getStringExtra("Titre")
        val textViewTitre = findViewById<TextView>(R.id.TitreListe)

        //var ListeNom: MutableList<String> = mutableListOf()
        //val ListePrix : MutableList<List<Price>> = mutableListOf()
        //val ListeURLImage : MutableList<List<String>> = mutableListOf()
        val listeItem : MutableList<Item> = mutableListOf()

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
            for (data in rep.data) {
                if (data.name_fr == Titre) {
                    for (item in data.items) {
                        //ListeNom.add(item.name_fr);
                        //ListePrix.add(item.prices)
                        //ListeURLImage.add(item.images)
                        listeItem.add(item)
                    }

                }
            }
            val recyclerViewListe = findViewById<RecyclerView>(R.id.Liste)
            //val adapterListe = ListeAdapter(ListeNom, ListePrix, ListeURLImage)
            val adapterListe = ListeAdapter(listeItem)
            recyclerViewListe.adapter = adapterListe
        }, { error ->
            Log.w("TAG", "RESPONSE IS $error")
        })
        queue.add(request)
    }
}

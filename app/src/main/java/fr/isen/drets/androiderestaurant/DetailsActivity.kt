package fr.isen.drets.androiderestaurant

import ImageAdapter
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import java.io.File


class DetailsActivity : AppCompatActivity() {

    class Commande(val id: String, val nb: Int)

    var nb = 0
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val item = intent.getParcelableExtra<ListeActivity.Item>("item")
        val nomPlatView = findViewById<TextView>(R.id.NomPlat)
        if (item != null) {
            nomPlatView.setText(item.name_fr)
        }
        val viewPager2 = findViewById<ViewPager2>(R.id.Carrousel)
        val adapter = item?.let { ImageAdapter(this, it.images) }
        viewPager2.adapter = adapter

        val nbView = findViewById<TextView>(R.id.nb)
        val btnMoins = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.btnMoins)
        val btnPlus = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.btnPlus)
        val btnTotal = findViewById<Button>(R.id.totalBtn)

        btnMoins.setOnClickListener {
            if(nb != 0) {
                --nb
                nbView.setText(nb.toString())
                if (item != null) {
                    var coutStr = item.prices[0].price
                    if (coutStr.contains('.')) {
                        var cout = coutStr.toFloat()
                        cout = cout*nb
                        btnTotal.setText("Total: "+cout.toString()+" €")
                    } else {
                        var cout = coutStr.toInt()
                        cout = cout*nb
                        btnTotal.setText("Total: "+cout.toString()+" €")
                    }
                }

            }
        }

        btnPlus.setOnClickListener {
            ++nb
            nbView.setText(nb.toString())
            if (item != null) {
                var coutStr = item.prices[0].price
                if (coutStr.contains('.')) {
                    var cout = coutStr.toFloat()
                    cout = cout*nb
                    btnTotal.setText("Total: "+cout.toString()+" €")
                } else {
                    var cout = coutStr.toInt()
                    cout = cout*nb
                    btnTotal.setText("Total: "+cout.toString()+" €")
                }
            }
        }

        btnTotal.setOnClickListener {
            if (item != null) {
                val commande = Commande(item.id, nb)
                val gson = Gson()
                val jsonString = gson.toJson(commande)
                val fichier = File(this.filesDir, "panier.json")
                fichier.appendText("$jsonString\n")
            }
            val snackbar = Snackbar.make(btnTotal, "Commande enregistrée dans le panier", Snackbar.LENGTH_LONG)
            snackbar.show()
        }

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val btnChariot = findViewById<View>(R.id.chariot)
        btnChariot.setOnClickListener{
            val pannierActivite = Intent(this, Panier_Activity::class.java)
            startActivity(pannierActivite)
        }
    }




}
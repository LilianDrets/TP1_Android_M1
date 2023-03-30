package fr.isen.drets.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

//import fr.isen.drets.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityHomeBinding

    fun createListeActivity(Titre : String) {
        val listeActivity = Intent(this, ListeActivity::class.java)
        listeActivity.putExtra("Titre", Titre)
        startActivity(listeActivity)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //binding = ActivityHomeBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        val textEntrees = findViewById<TextView>(R.id.textEntrees)
        val textPlats = findViewById<TextView>(R.id.textPlats)
        val textDesserts = findViewById<TextView>(R.id.textDesserts)

        //binding.starters.setOnClickListener {

        textEntrees.setOnClickListener {
            createListeActivity("Entrées")
        }

        textPlats.setOnClickListener {
            createListeActivity("Plats")
        }

        textDesserts.setOnClickListener {
            createListeActivity("Desserts")
        }

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val btnChariot = findViewById<View>(R.id.chariot)
        btnChariot.setOnClickListener{
            val pannierActivite = Intent(this, Panier_Activity::class.java)
            startActivity(pannierActivite)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("HomeActivity", "Destruction H de HomeActivity");
    }
}
package fr.isen.drets.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
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
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("HomeActivity", "Destruction H de HomeActivity");
    }
}
package fr.isen.drets.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val NomPlat = intent.getStringExtra("NomPlat")
        val nomPlatView = findViewById<TextView>(R.id.NomPlat)
        nomPlatView.setText(NomPlat)
    }




}
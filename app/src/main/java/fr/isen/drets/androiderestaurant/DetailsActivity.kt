package fr.isen.drets.androiderestaurant

import ImageAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2


class DetailsActivity : AppCompatActivity() {
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

    }




}
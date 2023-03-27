package fr.isen.drets.androiderestaurant

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class ListeAdapter(private val listeItem: List<ListeActivity.Item>) :
    RecyclerView.Adapter<ListeAdapter.ListeHolder>() {
    class ListeHolder(vueItemListe: View) : RecyclerView.ViewHolder(vueItemListe) {
        val titreListe: TextView
        val imageListe: ImageView
        val prixListe: TextView
        init {
            titreListe = itemView.findViewById(R.id.itemListe)
            imageListe = itemView.findViewById(R.id.imagePlat)
            prixListe = itemView.findViewById(R.id.Textprix)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListeHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemliste, parent, false)
        return ListeHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListeHolder, position: Int) {
        val item = listeItem[position]
        holder.titreListe.text = item.name_fr

        var prix = ""
        for (price in item.prices) {
            prix = prix+"Taille: "+price.size+" - Prix: "+price.price+"\n"
        }
        holder.prixListe.text = prix

        try {
            Picasso.get().load(item.images[0]).into(holder.imageListe)
        } catch (e: Exception) {
            e.printStackTrace()
        }


        holder.itemView.setOnClickListener {
            val detailsActivityIntent = Intent(holder.itemView.context, DetailsActivity::class.java)
            detailsActivityIntent.putExtra("item", item)
            holder.itemView.context.startActivity(detailsActivityIntent)
        }
    }

    override fun getItemCount(): Int = listeItem.size

}
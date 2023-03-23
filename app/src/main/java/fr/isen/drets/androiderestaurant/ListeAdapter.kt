package fr.isen.drets.androiderestaurant

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class ListeAdapter(private val ListeTitre: List<String>, private val ListePrix: List<List<ListeActivity.Price>>, private val ListeURLImage: List<List<String>>) :
    RecyclerView.Adapter<ListeAdapter.ListeHolder>() {
    class ListeHolder(vueItemListe: View) : RecyclerView.ViewHolder(vueItemListe) {
        val itemListe: TextView
        val imageListe: ImageView
        val prixListe: TextView
        init {
            itemListe = itemView.findViewById(R.id.itemListe)
            imageListe = itemView.findViewById(R.id.imagePlat)
            prixListe = itemView.findViewById(R.id.Textprix)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListeHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemliste, parent, false)
        return ListeHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListeHolder, position: Int) {
        val item = ListeTitre[position]
        holder.itemListe.text = item
        var prix = ""
        for (price in ListePrix[position]) {
            prix = "Taille: "+price.size+" - Prix: "+price.price+"\n"
        }
        holder.prixListe.text = prix

        try {
            Picasso.get().load(ListeURLImage[position][0]).into(holder.imageListe)
        } catch (e: Exception) {
            e.printStackTrace()
        }



        holder.itemView.setOnClickListener {
            val detailsActivityIntent = Intent(holder.itemView.context, DetailsActivity::class.java)
            detailsActivityIntent.putExtra("NomPlat", item)
            holder.itemView.context.startActivity(detailsActivityIntent)
        }
    }

    override fun getItemCount(): Int = ListeTitre.size

}
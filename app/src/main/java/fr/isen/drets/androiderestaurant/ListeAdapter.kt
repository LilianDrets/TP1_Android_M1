package fr.isen.drets.androiderestaurant

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class ListeAdapter(private val stringList: List<String>) :
    RecyclerView.Adapter<ListeAdapter.ListeHolder>() {
    class ListeHolder(vueItemListe: View) : RecyclerView.ViewHolder(vueItemListe) {
        val itemListe: TextView

        init {
            itemListe = itemView.findViewById(R.id.itemListe)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListeHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemliste, parent, false)
        return ListeHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListeHolder, position: Int) {
        val item = stringList[position]
        holder.itemListe.text = item
        holder.itemView.setOnClickListener {
            val detailsActivityIntent = Intent(holder.itemView.context, DetailsActivity::class.java)
            detailsActivityIntent.putExtra("NomPlat", item)
            holder.itemView.context.startActivity(detailsActivityIntent)
        }
    }

    override fun getItemCount(): Int = stringList.size

}
package fr.isen.drets.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
    }

    override fun getItemCount(): Int = stringList.size

}
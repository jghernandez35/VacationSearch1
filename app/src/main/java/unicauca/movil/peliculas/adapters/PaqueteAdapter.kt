package unicauca.movil.peliculas.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import unicauca.movil.peliculas.R
import unicauca.movil.peliculas.models.Paquetes
import unicauca.movil.peliculas.util.inflate

class PaqueteAdapter : RecyclerView.Adapter<PaqueteViewHolder>() {

    var datap: List<Paquetes> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: PaqueteViewHolder, position: Int) {
        holder.binding.paquete = datap[position]
        holder.binding.root.tag = position
        //holder.binding.handler = this
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaqueteViewHolder
            = PaqueteViewHolder(parent.inflate(R.layout.template_pelicula))

    override fun getItemCount(): Int = datap.size

    //constructor() : this()

}

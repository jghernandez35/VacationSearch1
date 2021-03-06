package unicauca.movil.peliculas.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import unicauca.movil.peliculas.R
import unicauca.movil.peliculas.databinding.TemplatePeliculaBinding
import unicauca.movil.peliculas.models.Paquetes
import unicauca.movil.peliculas.models.Pelicula
import unicauca.movil.peliculas.util.inflate

class PeliculaAdapter(val callback:(pos:Int)->Unit) : RecyclerView.Adapter<PaqueteViewHolder>() {

    var datap: List<Paquetes> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: PaqueteViewHolder, position: Int) {
        holder.binding.paquete = datap[position]
        holder.binding.root.tag = position
        holder.binding.handler = this
    }

    fun onPaqueteClick(pos:Int){
        callback(pos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            :PaqueteViewHolder = PaqueteViewHolder(parent.inflate(R.layout.template_pelicula))

    override fun getItemCount(): Int = datap.size
}

class PaqueteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding: TemplatePeliculaBinding = DataBindingUtil.bind(view)
}

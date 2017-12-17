package unicauca.movil.peliculas

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.util.Log
import android.view.Window
import android.view.WindowManager
import com.squareup.picasso.Picasso
import com.squareup.picasso.Callback
import kotlinx.android.synthetic.main.activity_detail.*
import unicauca.movil.peliculas.databinding.ActivityDetailBinding

import unicauca.movil.peliculas.adapters.PaqueteAdapter
import unicauca.movil.peliculas.adapters.PeliculaAdapter
import unicauca.movil.peliculas.db.AppDB
import unicauca.movil.peliculas.db.PaqueteDao
import unicauca.movil.peliculas.models.Paquetes
import unicauca.movil.peliculas.util.Data2
import kotlin.concurrent.thread

class DetailActivity : AppCompatActivity(), Callback {

    lateinit var binding:ActivityDetailBinding
    //manejo de Base de Datos
    val dao: PaqueteDao = AppDB.db.paqueteDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.handler = this

        setSupportActionBar(toolbar)

        val pos = intent.extras.getInt("pos", 0)
        val paquete = Data2.paquetes[pos]

        binding.paquete = paquete

        collapsing.title = paquete.nombre
        Picasso.with(this)
                .load(Uri.parse(paquete.imagen))
                .into(img, this)
    }

    fun save(){
        Log.d("jose", "INICIO En metodo Save() de MainActivity ----------------------------------**-----------");

        val paquete = Paquetes("prueba","123","no foto","sin descripcion")

        thread{
            dao.insert(paquete)
            runOnUiThread {
                finish()
            }
        }
        Log.d("jose", "FIN En metodo Save() de MainActivity ----------------------------------**-----------");
    }

    override fun onSuccess() {
        val drawable = img.drawable as BitmapDrawable
        val palette = Palette.from(drawable.bitmap).generate()
        val default = ContextCompat.getColor(this, R.color.colorPrimary)

        val color = palette.getVibrantColor(default)
        collapsing.setContentScrimColor(color)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = getStatusColor(color)
        }

    }

    fun getStatusColor(color:Int):Int{
        var red =  Color.red(color) - 40
        var blue = Color.blue(color) - 40
        var green = Color.green(color) - 40

        red = if(red > 0)  red else 0
        blue = if(blue > 0)  blue else 0
        green = if(green > 0)  green else 0

        return Color.rgb(red, green, blue)
    }

    override fun onError() {}
}

package com.tallercmovil.mercadito.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.tallercmovil.mercadito.R
import com.tallercmovil.mercadito.databinding.ActivityMainBinding
import com.tallercmovil.mercadito.model.ProductApi
import com.tallercmovil.mercadito.model.Producto
import com.tallercmovil.mercadito.view.adapter.Adaptador
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), Adaptador.OnItemListener{

    private var BASE_URL: String? = null
    private var LOGTAG : String? = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        BASE_URL=getString(R.string.base_url)
        LOGTAG=getString(R.string.logecat)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val productApi:ProductApi=retrofit.create(ProductApi::class.java)

        val call: Call<List<Producto>> = productApi.getProducts(getString(R.string.complement_url))

        call.enqueue(object: Callback<List<Producto>>{
            override fun onResponse(
                call: Call<List<Producto>>,
                response: Response<List<Producto>>
            ) {
                //La respuesta es correcta

                Log.d(LOGTAG,getString(R.string.respuesta_servidor,response.toString()))
                Log.d(LOGTAG,getString(R.string.datos_logecat,response.body().toString()))

                binding.pbConexion.visibility=View.INVISIBLE

                /*val productoTMP:Producto
                for (productoTMP in response.body()!!){
                    Toast.makeText(this@MainActivity,"Nombre: ${productoTMP.name}",Toast.LENGTH_LONG).show()
                }*/

                val adaptador=Adaptador(this@MainActivity,response.body()!!,this@MainActivity)

                val recyclerView=binding.rvMenu

                recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)

                recyclerView.adapter=adaptador
            }

            override fun onFailure(call: Call<List<Producto>>, t: Throwable) {
                Log.d(LOGTAG, R.string.mensaje_error.toString())

                binding.pbConexion.visibility=View.INVISIBLE

                Toast.makeText(this@MainActivity,R.string.conexion_fallida, Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onItemClick(producto: Producto) {
        val parametros = Bundle()

        parametros.putString("id",producto.id)

        val intent = Intent(this,Detalles::class.java)

        intent.putExtras(parametros)

        startActivity(intent)

        Animatoo.animateSlideUp(this)
    }
}
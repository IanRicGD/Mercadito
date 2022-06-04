package com.tallercmovil.mercadito.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.tallercmovil.mercadito.R
import com.tallercmovil.mercadito.databinding.ActivityDetallesBinding
import com.tallercmovil.mercadito.model.ProductApi
import com.tallercmovil.mercadito.model.ProductDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Detalles : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesBinding

    private var BASE_URL: String? = null
    private var LOGTAG : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        BASE_URL=getString(R.string.base_url)
        LOGTAG=getString(R.string.logecat)

        super.onCreate(savedInstanceState)
        binding= ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        val id = bundle?.getString("id","0")

        Log.d(LOGTAG,id.toString())

        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val productApi: ProductApi = retrofit.create(ProductApi::class.java)

        val call: Call<ProductDetail> = productApi.getProductDetail(id)

        call.enqueue(object: Callback<ProductDetail>{
            override fun onResponse(call: Call<ProductDetail>, response: Response<ProductDetail>) {
                with(binding){
                    pbConexion.visibility=View.INVISIBLE

                    tvNameP.text=response.body()?.name
                    Glide.with(this@Detalles)
                        .load(response.body()?.imagUrl)
                        .into(ivImage)

                    tvLongDesc.text=response.body()?.desc
                }
            }

            override fun onFailure(call: Call<ProductDetail>, t: Throwable) {
                Log.d(LOGTAG, R.string.mensaje_error.toString())

                binding.pbConexion.visibility= View.INVISIBLE

                Toast.makeText(this@Detalles,getString(R.string.conexion_fallida), Toast.LENGTH_LONG).show()
            }

        })


    }
}
package com.tallercmovil.mercadito.view.adapter

import android.content.Context
import android.content.res.Resources
import android.provider.Settings.System.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tallercmovil.mercadito.R
import com.tallercmovil.mercadito.databinding.ElementoVistaBinding
import com.tallercmovil.mercadito.model.Producto

class Adaptador(context: Context, productos:List<Producto>,onItemListener:OnItemListener): RecyclerView.Adapter<Adaptador.ViewHolder>() {

    private val productos=productos
    private val mOnItemListener=onItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adaptador.ViewHolder {
        val layoutInflater=LayoutInflater.from(parent?.context)
        val binding=ElementoVistaBinding.inflate(layoutInflater)

        return ViewHolder(binding,mOnItemListener)
    }

    override fun onBindViewHolder(holder: Adaptador.ViewHolder, position: Int) {
        holder.bindData(productos[position])
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    interface OnItemListener{
        fun onItemClick(producto:Producto)
    }

    class ViewHolder(binding: ElementoVistaBinding, onItemListener:OnItemListener):RecyclerView.ViewHolder(binding.root),View.OnClickListener{
        private val ivThumbnail=binding.ivThumbnail
        private val tvName=binding.tvName
        private val tvProveedor=binding.tvProveedor
        private val tvPrecio=binding.tvPrecio
        private val tvDelivery=binding.tvDelivery

        private val context=binding.root.context
        private val onItemListener=onItemListener
        private lateinit var producto: Producto

        init {
            binding.root.setOnClickListener(this)
        }

        fun bindData(item:Producto){
            tvName.text=item.name
            tvProveedor.text=context.getString(R.string.proveedor,item.provider)
            tvPrecio.text=context.getString(R.string.precio,item.price)
            if (item.delivery=="0.00"){
                tvDelivery.text=context.getString(R.string.precio_delivery_gratis)
            }
            else{
                tvDelivery.text=context.getString(R.string.precio_delivery,item.delivery)
            }

            Glide.with(context)
                .load(item.thumbnailUrl)
                .into(ivThumbnail)

            producto=item

        }

        override fun onClick(p0: View?) {
            onItemListener.onItemClick(producto)
        }

    }

}
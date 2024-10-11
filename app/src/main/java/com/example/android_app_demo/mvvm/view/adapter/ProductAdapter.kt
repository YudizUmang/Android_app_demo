package com.example.android_app_demo.mvvm.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.example.android_app_demo.R
import com.example.android_app_demo.mvvm.model.data.Product


class ProductAdapter(val context: Context, val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.product_img)
        val title = itemView.findViewById<TextView>(R.id.text_title)
        val price = itemView.findViewById<TextView>(R.id.text_price)
        val description = itemView.findViewById<TextView>(R.id.text_description)
        val rating = itemView.findViewById<TextView>(R.id.text_rating)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.product_holder, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.title.text = product.title
        holder.price.text = product.price.toString()
        holder.description.text = product.description
        holder.rating.text = product.rating.toString()
        holder.img.load(product.image, imageLoader = ImageLoader(context))
        // holder.img.setImageURI(product.image)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
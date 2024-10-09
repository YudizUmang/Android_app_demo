package com.example.android_app_demo.recyclerview.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.android_app_demo.R
import com.example.android_app_demo.recyclerview.main.DetailsActivity
import com.example.android_app_demo.recyclerview.model.Person

class PersonAdapter(
    val context: Context,
    val personList: List<Person>
) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.text_name)
        val cityTextView: TextView = itemView.findViewById(R.id.text_city)
        val moreDetailsButton: Button = itemView.findViewById(R.id.button_more_details)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = personList[position]
        holder.nameTextView.text = person.name
        holder.cityTextView.text = person.city

        holder.moreDetailsButton.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java).apply {
                putExtra("NAME", person.name)
                putExtra("NUMBER", person.number)
                putExtra("EMAIL", person.email)
                putExtra("CITY", person.city)
            }
            startActivity(context, intent, null)
        }
    }

    override fun getItemCount(): Int = personList.size
}

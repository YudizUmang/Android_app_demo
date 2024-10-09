package com.example.android_app_demo.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.android_app_demo.R
import com.example.android_app_demo.menu.model.MenuPerson

class PersonCardAdapter(
    val context: Context,
    val personList: List<MenuPerson>
) : RecyclerView.Adapter<PersonCardAdapter.PersonViewHolder>() {

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
            val popupMenu = PopupMenu(context, holder.moreDetailsButton)

            popupMenu.menuInflater.inflate(R.menu.option_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                Toast.makeText(
                    context,
                    "You Clicked " + menuItem.title,
                    Toast.LENGTH_SHORT
                ).show()
                true
            }
            popupMenu.show()

        }
    }

    override fun getItemCount(): Int = personList.size
}

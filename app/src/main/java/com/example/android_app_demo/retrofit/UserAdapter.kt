package com.example.android_app_demo.retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.example.android_app_demo.R

class UserAdapter(
    val context: Context,
    private var userList: MutableList<User>
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private lateinit var editListener: (position: Int, user: User) -> Unit
    private lateinit var deleteListener: (position: Int, user: User) -> Unit

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.text_name)
        val companyTextView: TextView = itemView.findViewById(R.id.text_company)
        val ageTextView: TextView = itemView.findViewById(R.id.text_age)
        val editUser: Button = itemView.findViewById(R.id.edit_user_btn)
        val deleteUser: Button = itemView.findViewById(R.id.delete_user_btn)
        val userImage: ImageView = itemView.findViewById(R.id.user_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_holder, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.nameTextView.text = user.name
        holder.companyTextView.text = user.companyName
        holder.ageTextView.text = user.age.toString()
        holder.userImage.load(user.img, imageLoader = ImageLoader(context))
        holder.editUser.setOnClickListener {
            editListener.invoke(position, user)
            //editListener.setUserListener(holder.itemView, position, user)
        }

        holder.deleteUser.setOnClickListener {
            deleteListener.invoke(position, user)
            //deleteListener.setUserListener(holder.itemView, position, user)
        }


    }

    override fun getItemCount(): Int = userList.size

    fun updateUserList(newUserList: MutableList<User>) {
        userList = newUserList
        notifyDataSetChanged()
    }

    fun onEditClick(listener: ItemClick) {
        this.editListener = listener
    }

    fun onDeleteClick(listener: ItemClick) {
        this.deleteListener = listener
    }

}
typealias ItemClick = (position: Int, user: User) -> Unit
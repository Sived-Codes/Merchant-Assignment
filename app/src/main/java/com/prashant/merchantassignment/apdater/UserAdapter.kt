package com.prashant.merchantassignment.apdater

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.prashant.merchantassignment.R
import com.prashant.merchantassignment.model.UsersModel
import com.prashant.merchantassignment.view.UserListFragment

class UserAdapter(
    private val context: Context,
    private var userList: List<UsersModel>,
    private var navController: NavController?
) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.userName)
        val userEmail: TextView = itemView.findViewById(R.id.userEmail)
        val userMobile: TextView = itemView.findViewById(R.id.userMobile)
        val userImage: ImageView = itemView.findViewById(R.id.userImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cl_user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]

        holder.userName.text = user.firstName + " " +user.lastName
        holder.userEmail.text = user.email
        holder.userMobile.text = user.phone

       holder.userImage.load(user.image) {
            crossfade(true)
            placeholder(R.drawable.icn_user)
        }
        holder.itemView.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("id", user.id.toString())
            Toast.makeText(context, user.id.toString()+ "", Toast.LENGTH_SHORT).show()
            navController?.navigate(R.id.action_userListFragment_to_userDetailFragment, bundle)

        }
    }

//    fun UpdateUserList(newData : List<UsersModel>){
//
//    }

    override fun getItemCount(): Int {
      return  userList.size
    }
}
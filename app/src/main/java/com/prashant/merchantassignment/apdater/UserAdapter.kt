package com.prashant.merchantassignment.apdater

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.prashant.merchantassignment.R
import com.prashant.merchantassignment.model.UserModel
import com.prashant.merchantassignment.viewmodel.RoomViewModel

class UserAdapter(
    private var roomViewModel: RoomViewModel,
    private var userList: List<UserModel>,
    private var navController: NavController?
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.userName)
        val userEmail: TextView = itemView.findViewById(R.id.userEmail)
        val userMobile: TextView = itemView.findViewById(R.id.userMobile)
        val userImage: ImageView = itemView.findViewById(R.id.userImage)
        val deleteBtn: ImageView = itemView.findViewById(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cl_user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]

        holder.userName.text = user.firstName + " " + user.lastName
        holder.userEmail.text = user.email
        holder.userMobile.text = user.phone

        holder.deleteBtn.setOnClickListener {
            roomViewModel.delete(user.id)
            notifyItemRemoved(position)

        }
        holder.userImage.load(user.image) {
            crossfade(true)
            placeholder(R.drawable.icn_user)
        }
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("id", user.id)
            navController?.navigate(R.id.action_userListFragment_to_userDetailFragment, bundle)

        }
    }

    fun updateUserList(userList: List<UserModel>) {
        this.userList = userList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return userList.size
    }
}
package com.ankit.listapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ankit.listapplication.R
import com.ankit.listapplication.databinding.ItemRowBinding
import com.ankit.listapplication.model.UserModel

class UserDataAdapter  : RecyclerView.Adapter<UserDataAdapter.UserViewHolder>() {

    private var userList: List<UserModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val newsItemRowBinding: ItemRowBinding  = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_row,
            parent,
            false
        )
        return UserViewHolder(newsItemRowBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userInfo = userList!![position]
        if (userInfo != null) {
            holder.itemRowBinding!!.userModel= (userInfo)
        }
    }

    override fun getItemCount(): Int {
        return userList?.size ?: 0
    }

    fun setUsersList(newsList: List<UserModel>) {
        this.userList = newsList
        notifyDataSetChanged()
    }

    inner class UserViewHolder(itemRowBinding: ItemRowBinding) :
        RecyclerView.ViewHolder(itemRowBinding.getRoot()) {
        var itemRowBinding: ItemRowBinding? = null

        init {
            this.itemRowBinding = itemRowBinding
        }
    }
}
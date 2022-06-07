package com.example.testtask.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testtask.R
import com.example.testtask.model.user.UserModel
import com.example.testtask.ui.main.MainFragment


class MainRecyclerViewAdapter (private val userModel: UserModel?, private val mainFragment: MainFragment) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nickname: TextView = view.findViewById(R.id.tv_nickname)
        val profilePicture: ImageView = view.findViewById(R.id.iv_profile_picture)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = userModel!![position].login
        val avatarUrl = userModel[position].avatar_url
        holder.nickname.text = name
        holder.itemView.setOnClickListener {
            mainFragment.onClick(name, avatarUrl)
        }
        Glide.with(mainFragment.requireContext())
            .load(avatarUrl)
            .placeholder(R.drawable.profile_placeeholder)
            .centerCrop()
            .into(holder.profilePicture)
    }

    override fun getItemCount(): Int {
        return userModel!!.size
    }
}
package com.example.testtask.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testtask.R
import com.example.testtask.model.user.UserModel
import com.example.testtask.ui.main.MainFragment


class MainRecyclerViewAdapter(
    userModel: UserModel?,
    private val mainFragment: MainFragment
) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {
    private var user = userModel

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nickname: TextView = view.findViewById(R.id.tv_nickname)
        val profilePicture: ImageView = view.findViewById(R.id.iv_profile_picture)
        val redDot: ConstraintLayout = view.findViewById(R.id.red_dot)
        val redDotText: TextView = view.findViewById(R.id.red_dot_text)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = user!![position].login
        val avatarUrl = user!![position].avatar_url
        val id = user!![position].id
        val changesCount = user!![position].changesCount
        if (changesCount != 0) {
            holder.redDot.visibility = View.VISIBLE
            holder.redDotText.text = changesCount.toString()
        } else
            holder.redDot.visibility = View.GONE
        holder.nickname.text = name
        holder.itemView.setOnClickListener {
            mainFragment.onClick(name, avatarUrl, id)
        }
        Glide.with(mainFragment.requireContext())
            .load(avatarUrl)
            .placeholder(R.drawable.profile_placeeholder)
            .centerCrop()
            .into(holder.profilePicture)
    }

    override fun getItemCount(): Int {
        return user!!.size
    }

    fun updateUsers(updatedUser: UserModel?){
        user = updatedUser
        this.notifyDataSetChanged()
    }
}
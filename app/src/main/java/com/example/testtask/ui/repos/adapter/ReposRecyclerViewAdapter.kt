package com.example.testtask.ui.repos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.model.repository.RepositoryModel
import com.example.testtask.ui.repos.ReposFragment

class ReposRecyclerViewAdapter (private val repositoryModel: RepositoryModel?, private val reposFragment: ReposFragment) :
        RecyclerView.Adapter<ReposRecyclerViewAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val repositoryName: TextView = view.findViewById(R.id.tv_repository_name)
            val programmingLanguage: TextView = view.findViewById(R.id.tv_programming_language)
            val starCounter: TextView = view.findViewById(R.id.tv_star_count)
            val programmingLanguageIcon: ImageView = view.findViewById(R.id.programming_lang_icon)
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.repository_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val programmingLanguage: String? = repositoryModel!![position].language
            holder.repositoryName.text = repositoryModel[position].name
            holder.starCounter.text = repositoryModel[position].stargazers_count.toString()
            holder.programmingLanguage.text = repositoryModel[position].language
            if (programmingLanguage == null)
                holder.programmingLanguageIcon.visibility = View.GONE
            else
                holder.programmingLanguageIcon.visibility = View.VISIBLE
            holder.itemView.setOnClickListener {
                reposFragment.openUrl(repositoryModel[position].html_url)
            }
        }

        override fun getItemCount(): Int {
            return repositoryModel!!.size
        }

    }
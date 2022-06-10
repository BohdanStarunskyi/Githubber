package com.example.testtask.ui.repos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.testtask.R
import com.example.testtask.databinding.FragmentReposBinding
import com.example.testtask.ui.repos.adapter.ReposRecyclerViewAdapter
import com.example.testtask.ui.repos.interfaces.ReposOnClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReposFragment : Fragment(), ReposOnClick {
    private lateinit var binding: FragmentReposBinding
    private lateinit var reposViewModel: ReposViewModel
    private lateinit var name: String
    private lateinit var avatarUrl: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReposBinding.inflate(inflater, container, false)
        reposViewModel = ViewModelProvider(this)[ReposViewModel::class.java]
        name = arguments?.getSerializable("name") as String
        avatarUrl = arguments?.getSerializable("picture") as String
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            reposViewModel.requestRepositoriesFromDatabase(name)
            reposViewModel.requestRepositoriesFromApi(name)
        } catch (e: Exception){
            e.printStackTrace()
        }
        init()
    }

    private fun init() {
        binding.tvUsername.text = name

        Glide.with(requireContext())
            .load(avatarUrl)
            .placeholder(R.drawable.profile_placeeholder)
            .centerCrop()
            .into(binding.ivProfilePicture)

        reposViewModel.getUserRepositories().observe(viewLifecycleOwner) {
            it.let {
                if (it!!.size != 0) {
                    binding.rvRepos.adapter = ReposRecyclerViewAdapter(it, this)
                    binding.noData.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
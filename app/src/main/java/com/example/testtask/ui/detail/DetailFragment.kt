package com.example.testtask.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.testtask.R
import com.example.testtask.databinding.FragmentDetailBinding
import com.example.testtask.ui.detail.adapter.DetailRecyclerViewAdapter
import com.example.testtask.ui.detail.interfaces.DetailRVOnClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(), DetailRVOnClick {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var name: String
    private lateinit var avatarUrl: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        name = arguments?.getSerializable("name") as String
        avatarUrl = arguments?.getSerializable("picture") as String
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        binding.tvUsername.text = name
        Glide.with(requireContext())
            .load(avatarUrl)
            .placeholder(R.drawable.profile_placeeholder)
            .centerCrop()
            .into(binding.ivProfilePicture)
        detailViewModel.requestRepositories(name)
        detailViewModel.getUserRepositories().observe(viewLifecycleOwner) {
            if(it == null)
                Toast.makeText(requireContext(), "null", Toast.LENGTH_SHORT).show()
            else
                binding.rvDetail.adapter = DetailRecyclerViewAdapter(it, this)
        }
    }

    override fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
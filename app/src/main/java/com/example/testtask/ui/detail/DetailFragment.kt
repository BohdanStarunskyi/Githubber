package com.example.testtask.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.testtask.R
import com.example.testtask.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var name: String
    private lateinit var avatarUrl: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        name = arguments?.getSerializable("name") as String
        avatarUrl = arguments?.getSerializable("picture") as String
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.username.text = name
        Glide.with(requireContext())
            .load(avatarUrl)
            .placeholder(R.drawable.profile_placeeholder)
            .centerCrop()
            .into(binding.profilePicture)
    }
}
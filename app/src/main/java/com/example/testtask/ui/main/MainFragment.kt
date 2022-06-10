package com.example.testtask.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.testtask.R
import com.example.testtask.databinding.FragmentMainBinding
import com.example.testtask.ui.main.adapter.MainRecyclerViewAdapter
import com.example.testtask.ui.main.interfaces.MainRVOnClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), MainRVOnClick {
    private lateinit var binding: FragmentMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.getUsersFromApi()
        init()
    }

    private fun init() {
        mainViewModel.getUsers().observe(viewLifecycleOwner) {
            if (it!!.size != 0) {
                binding.rvMainFragment.adapter = MainRecyclerViewAdapter(it, this)
                binding.noData.visibility = View.INVISIBLE
            }
        }
    }

    override fun onClick(nickname: String, profilePicture: String, id: Int?) {
        val bundle = Bundle()
        bundle.putSerializable("name", nickname)
        bundle.putSerializable("picture", profilePicture)
        requireActivity().findNavController(R.id.fragment_container)
            .navigate(R.id.action_mainFragment_to_detailFragment, bundle)
    }

}
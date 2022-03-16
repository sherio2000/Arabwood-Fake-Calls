package com.example.awfc

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.awfc.adapters.ArtistsAdapter
import com.example.awfc.databinding.FragmentVideoCallHomeBinding
import com.todkars.shimmer.ShimmerRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class VideoCallHomeFragment : Fragment() {
    private lateinit var mainViewModel: MainViewModel
    private val mAdapter by lazy { ArtistsAdapter() }
    private lateinit var mView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_video_call_home, container, false)
        val shimmer = mView.findViewById<ShimmerRecyclerView>(R.id.recycler_view)
        shimmer.showShimmer()
        setupRecyclerView()
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        lifecycle.coroutineScope.launch {
            mainViewModel.getArtists().collect {
                mAdapter.setData(it)
                shimmer.hideShimmer()
            }
        }

        return mView
    }

    private suspend fun getArtists() {

    }

    @SuppressLint("CutPasteId")
    private fun setupRecyclerView()
    {
        mView.findViewById<RecyclerView>(R.id.recycler_view).adapter = mAdapter
        mView.findViewById<RecyclerView>(R.id.recycler_view).layoutManager = LinearLayoutManager(requireContext())

    }

}
package com.example.awfc.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.awfc.R
import com.example.awfc.adapters.ArtistsAdapter
import com.example.awfc.data.Artist
import com.example.awfc.viewmodels.MainViewModel
import com.todkars.shimmer.ShimmerRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class VideoCallHomeFragment : Fragment() , ArtistsAdapter.OnArtistListener {
    private lateinit var mainViewModel: MainViewModel
    private val mAdapter by lazy { ArtistsAdapter(this)}
    private lateinit var mView: View
    private lateinit var artists: List<Artist>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
                artists = it
                mAdapter.setData(it)
                shimmer.hideShimmer()
            }
        }

        return mView
    }


    @SuppressLint("CutPasteId")
    private fun setupRecyclerView()
    {
        mView.findViewById<RecyclerView>(R.id.recycler_view).adapter = mAdapter
        mView.findViewById<RecyclerView>(R.id.recycler_view).layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onArtistClick(artist: Artist, position: Int) {
        artists[position]
        val intent = Intent(this.context, ArtistDetailsActivity::class.java)
        intent.putExtra("artistName", artist.name)
        intent.putExtra("artistDesc", artist.description)
        intent.putExtra("artistImage", artist.image)
        intent.putExtra("artistVideo1", artist.videoUrl1)
        intent.putExtra("artistVideo2", artist.videoUrl2)
        intent.putExtra("artistVideo3", artist.videoUrl3)
        startActivity(intent)
    }

}
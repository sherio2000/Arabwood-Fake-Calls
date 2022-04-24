package com.example.awfc.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_video_call_home, container, false)
        setHasOptionsMenu(true)

        setupRecyclerView()
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        mainViewModel.artistsResponse.observe(viewLifecycleOwner, Observer {
            mAdapter.setData(it)
        })

        lifecycle.coroutineScope.launch {
            mainViewModel.getArtists().collect {
                mainViewModel.artistsResponse.value = it
                artists = it
                mAdapter.setData(it)
            }
        }

        return mView
    }



    @SuppressLint("CutPasteId")
    fun setupRecyclerView()
    {
        mView.findViewById<RecyclerView>(R.id.recycler_view).adapter = mAdapter
        mView.findViewById<RecyclerView>(R.id.recycler_view).layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }

    override fun onArtistClick(artist: Artist, position: Int) {
        artists[position]
        val intent = Intent(this.context, ArtistDetailsActivity::class.java)
        intent.putExtra("artistName", artist.name)
        intent.putExtra("arabicName", artist.name_arabic)
        intent.putExtra("arabicDesc", artist.description_arabic)
        intent.putExtra("artistDesc", artist.description)
        intent.putExtra("artistImage", artist.image)
        intent.putExtra("artistVideo1", artist.videoUrl1)
        intent.putExtra("artistVideo2", artist.videoUrl2)
        intent.putExtra("artistVideo3", artist.videoUrl3)
        startActivity(intent)
    }




}
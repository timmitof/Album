package com.timmitof.album.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.arellomobile.mvp.MvpAppCompatActivity
import com.timmitof.album.database.entity.Image
import com.timmitof.album.databinding.ActivityMainBinding
import com.timmitof.album.presentation.adapters.GalleryAdapter
import com.timmitof.album.presentation.mvpview.IMainView
import com.timmitof.album.presentation.presenter.MainPresenter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : MvpAppCompatActivity(), IMainView {
    private lateinit var binding: ActivityMainBinding

    var presenter: MainPresenter = MainPresenter(null)

    lateinit var adapter: GalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        adapter = GalleryAdapter()
        binding.recyclerPhotos.adapter = adapter
        setContentView(binding.root)
        setupListeners()
        presenter.getAllImages()
    }

    private fun setupListeners() {
        binding.cameraBtn.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }
    }

    override fun setImages(list: List<Image>?) {
        Log.e("Images", "Images: $list")
        adapter.addList(list)
        adapter.notifyDataSetChanged()
    }

}
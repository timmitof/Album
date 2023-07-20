package com.timmitof.album.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.timmitof.album.database.entity.Image
import com.timmitof.album.databinding.ActivityMainBinding
import com.timmitof.album.presentation.adapters.GalleryAdapter
import com.timmitof.album.presentation.mvpview.IMainView
import com.timmitof.album.presentation.presenter.MainPresenter
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), IMainView {
    private lateinit var binding: ActivityMainBinding

    @InjectPresenter
    lateinit var presenter: MainPresenter

    private val adapter: GalleryAdapter by lazy { GalleryAdapter(onDeleteClick) }
    private lateinit var onDeleteClick: (Image) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
    }

    override fun setImages(list: List<Image>?) { adapter.addList(list) }

    override fun showToast(message: String?) { Toast.makeText(this, message, Toast.LENGTH_SHORT).show() }

    private fun setupListeners() {
        onDeleteClick = { presenter.deleteImage(it) }
        binding.recyclerPhotos.adapter = adapter

        binding.cameraBtn.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }
    }
}
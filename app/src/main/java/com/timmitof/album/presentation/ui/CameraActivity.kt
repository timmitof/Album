package com.timmitof.album.presentation.ui

import android.Manifest.permission.CAMERA
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.permissionx.guolindev.PermissionX
import com.timmitof.album.databinding.ActivityCameraBinding
import com.timmitof.album.presentation.mvpview.ICameraView
import com.timmitof.album.presentation.presenter.CameraPresenter
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val REQUIRED_PERMISSIONS = listOf(CAMERA)

class CameraActivity : MvpAppCompatActivity(), ICameraView {

    private lateinit var binding: ActivityCameraBinding

    @InjectPresenter
    lateinit var presenter: CameraPresenter

    lateinit var imageCapture: ImageCapture

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imageCapture = ImageCapture.Builder().build()
        requirePermissions(REQUIRED_PERMISSIONS)
        binding.takePhotoButton.setOnClickListener {
            takePhoto()
        }
    }

    private fun requirePermissions(permissions: List<String>) {
        PermissionX.init(this)
            .permissions(permissions)
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    startCamera()
                } else {
                    requirePermissions(deniedList)
                }
            }
    }

    private fun startCamera() {
        val cameraProvider = ProcessCameraProvider.getInstance(this)

        cameraProvider.addListener({
            val cameraProviderFeature: ProcessCameraProvider = cameraProvider.get()

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            val preview = Preview.Builder().build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }
            try {
                cameraProviderFeature.unbindAll()
                cameraProviderFeature.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            } catch (e: Exception) {
                Log.e("CAMERA_ERROR", "$e")
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun takePhoto() {
        val photoFile = createPhotoFile()
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    presenter.savePhoto(image = outputFileResults.savedUri?.toBitmap())
                }

                override fun onError(e: ImageCaptureException) {
                    Log.e("IMAGE_SAVE_ERROR", "$e")
                }
            }
        )
    }

    private fun createPhotoFile(): File {
        val timeStamp = SimpleDateFormat("dd.MM.yyyy_HH:mm:ss", Locale.getDefault()).format(Date())
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "AlbumApp_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }

    override fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun Uri.toBitmap(): Bitmap {
        return MediaStore.Images.Media.getBitmap(getContentResolver(), this)
    }
}
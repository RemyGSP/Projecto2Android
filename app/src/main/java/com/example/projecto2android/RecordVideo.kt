package com.example.projecto2android


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class RecordVideo: AppCompatActivity() {
    val REQUEST_VIDEO_CAPTURE = 1
    fun grabarVideo(view: View){
        val REQUEST_VIDEO_CAPTURE = 1
        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { video->
            video.resolveActivity(packageManager)?.also {
                startActivityForResult(video,REQUEST_VIDEO_CAPTURE)
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == Activity.RESULT_OK) {
            val videoUri: Uri? = data?.data
            // Aquí puedes utilizar videoUri para obtener la ubicación del video grabado
            Log.d("VideoUri", videoUri.toString())
        }
    }
}
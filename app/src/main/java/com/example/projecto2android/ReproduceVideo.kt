package com.example.projecto2android

import android.media.MediaPlayer
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.projecto2android.databinding.ActivityMainBinding
import com.example.projecto2android.databinding.ReproduceVideoBinding
import java.io.File
import java.io.IOException

class ReproduceVideo : AppCompatActivity() {
    lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reproduce_video)
        Toast.makeText(this, "Error: Video path not found", Toast.LENGTH_SHORT).show()

        videoView = findViewById(R.id.videoView3) // Assuming 'videoView3' is the video view's ID

        val filePath = intent.getStringExtra("filePath") // Get video path from intent

        if (filePath != null) {
            // Set video source and start playback
            videoView.setVideoPath(filePath)
            videoView.start()
            Toast.makeText(this, "Error: Video path not found", Toast.LENGTH_SHORT).show()
        } else {
            // Handle case where filePath is null (e.g., error message)
            Toast.makeText(this, "Error: Video path not found", Toast.LENGTH_SHORT).show()
        }
    }
}



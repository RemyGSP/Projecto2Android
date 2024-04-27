package com.example.projecto2android

import FileAdapter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.projecto2android.databinding.ActivityMainBinding
import android.Manifest
import android.content.Intent
import android.os.Environment
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var REQUEST_RECORD_AUDIO_PERMISSION = 200
    private var permissions = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA

    )
    private var permissionToRecordAccepted = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView : RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val movieDirectory = File("/storage/emulated/0/Android/data/com.example.projecto2android/files/Movies/")
        val musicDirectory = File("/storage/emulated/0/Android/data/com.example.projecto2android/files/Music/")
        if (!movieDirectory.exists()) {
            movieDirectory.mkdirs()
        }
        if (!musicDirectory.exists()) {
            musicDirectory.mkdirs()
        }

        // Access files only if directories exist
        val audioVideoFiles = if (musicDirectory.exists()) {
            musicDirectory.listFiles { file -> file.isFile && (file.extension == "mp3" || file.extension == "mp4") }
        } else {
            emptyArray()
        }
        val videoFiles = if (movieDirectory.exists()) {
            movieDirectory.listFiles { file -> file.isFile && (file.extension == "mp4" || file.extension == "mp4") }
        } else {
            emptyArray()
        }
        audioVideoFiles.plus(videoFiles)
        val adapter = FileAdapter(this, audioVideoFiles.toList())
        recyclerView.adapter = adapter
        ActivityCompat.requestPermissions(this, permissions,100)

        binding.PlusButton.setOnClickListener() {
            val intent = Intent(this, RecordScreenBinding::class.java)
            startActivity(intent)

        }

        binding.bottomNavBar.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.goBackButton -> {
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}
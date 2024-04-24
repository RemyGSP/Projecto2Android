package com.example.projecto2android

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.projecto2android.databinding.ActivityMainBinding
import android.Manifest
import android.content.Intent

class MainActivity : AppCompatActivity() {
    private var REQUEST_RECORD_AUDIO_PERMISSION: Int = 200
    public var permissionToRecordAccepted: Boolean = false
    private var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionToRecordAccepted = if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        } else {
            false
        }
        if (!permissionToRecordAccepted) {
            finish()
        }
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION)
        binding.PlusButton.setOnClickListener() {
            val intent = Intent(this, RecordScreenBinding::class.java)
            startActivity(intent)
        }
    }



}
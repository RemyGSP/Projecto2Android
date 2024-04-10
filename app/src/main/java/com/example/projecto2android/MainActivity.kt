package com.example.projecto2android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projecto2android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
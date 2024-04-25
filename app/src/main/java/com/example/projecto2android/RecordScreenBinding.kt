package com.example.projecto2android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projecto2android.databinding.ActivityMainBinding
import com.example.projecto2android.databinding.RecordScreenBinding

class RecordScreenBinding: AppCompatActivity() {
    private lateinit var binding: RecordScreenBinding
    private var isRecording :Boolean = false
    private lateinit var recordAudio: RecordAudio
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = RecordScreenBinding.inflate(layoutInflater)
            setContentView(binding.root)
            recordAudio = RecordAudio()
            binding.audioRecordButton.setOnClickListener() {
                if (!isRecording){
                    recordAudio.StartRecording(this,binding.path.text.toString())

                }
                else{
                    recordAudio.StopRecording()

            }
            }
        }

}
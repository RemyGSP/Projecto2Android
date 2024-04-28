package com.example.projecto2android


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projecto2android.databinding.ActivityMainBinding
import com.example.projecto2android.databinding.RecordScreenBinding

class RecordScreenBinding: AppCompatActivity() {
    private lateinit var binding: RecordScreenBinding
    private var isRecording :Boolean = false
    private var isRecordingVideo : Boolean = false
    private lateinit var recordAudio: RecordAudio
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = RecordScreenBinding.inflate(layoutInflater)
            setContentView(binding.root)
            recordAudio = RecordAudio()
            binding.audioRecordButton.setOnClickListener() {
                if (!isRecording){
                    recordAudio.StartRecording(this,binding.path.text.toString())
                    isRecording = true
                    Toast.makeText(this,"Recording",Toast.LENGTH_LONG).show()
                }
                else{
                    recordAudio.StopRecording()
                    isRecording = false
                    Toast.makeText(this,"Stop Recording",Toast.LENGTH_LONG).show()

                }
            }
            binding.videoRecordButton.setOnClickListener() {
                if (!isRecordingVideo){
                    val intent = Intent(this, RecordVideo::class.java)
                    startActivity(intent)
                    Toast.makeText(this,"Recording",Toast.LENGTH_LONG).show()

                }
                else{

                    Toast.makeText(this,"Stop Recording",Toast.LENGTH_LONG).show()

                }
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
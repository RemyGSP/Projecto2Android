package com.example.projecto2android

import android.app.Activity
import android.hardware.Camera
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Surface
import android.view.SurfaceHolder
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import com.example.projecto2android.databinding.MainBinding
import java.io.File
import java.io.IOException

class RecordVideo : AppCompatActivity() {
    private lateinit var binding: MainBinding
    private lateinit var mediaRecorder: MediaRecorder
    private var isRecording = false
    private var fileName = "prueba"
    private lateinit var videoDirectory: File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnRecord.setOnClickListener {
            toggleRecording()
        }
        binding.btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun toggleRecording() {
        if (isRecording) {
            stopRecording()
        } else {
            startRecording()
        }
    }

    private fun isExternalStorageWritable(): Boolean {
        val state = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == state
    }
    private fun startRecording() {

        if (!isExternalStorageWritable()) {
            Toast.makeText(this, "Storage not available", Toast.LENGTH_SHORT).show()
            return
        }

        val externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_MOVIES)
        if (externalFilesDir == null) {
            Toast.makeText(this, "Failed to get a valid directory", Toast.LENGTH_SHORT).show()
            return
        }

        videoDirectory = externalFilesDir



        val videoFile = File(videoDirectory, "$fileName.mp4")


        videoDirectory = getExternalFilesDir(Environment.DIRECTORY_MOVIES)!!



        mediaRecorder = MediaRecorder()
        mediaRecorder.apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setVideoSource(MediaRecorder.VideoSource.CAMERA)

            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP)
            mediaRecorder.setVideoSize(1280, 720) // 720p
            setVideoFrameRate(60)


            setOutputFile(videoFile.absolutePath)
            setPreviewDisplay(binding.btnVideo.holder.surface)
        }


        try {
            mediaRecorder.prepare()
            mediaRecorder.start()
            isRecording = true
            binding.btnRecord.text = "Stop"
        } catch (e: IOException) {
            Log.e("VideoRecorder", "Error preparando MediaRecorder: ${e.message}")
            mediaRecorder.release()
        }
    }

    private fun stopRecording() {
        if (isRecording) {
            try {
                mediaRecorder.stop()
                mediaRecorder.reset()
            } catch (e: RuntimeException) {
                Log.e("VideoRecorder", "Error al detener la grabación: ${e.message}")
            } finally {
                mediaRecorder.release()
            }

            isRecording = false
            val savedFilePath = File(videoDirectory, "$fileName.mp4").absolutePath
            Log.i("VideoRecorder", "Recorder released and video saved to: $savedFilePath")

            binding.btnRecord.text = "Start"
            finish()
        }
    }
    fun setCameraDisplayOrientation(activity: Activity, cameraId: Int, camera: Camera) : Int{
        val info = Camera.CameraInfo()
        Camera.getCameraInfo(cameraId, info)

        val rotation = activity.windowManager.defaultDisplay.rotation
        var degrees = 0

        when (rotation) {
            Surface.ROTATION_0 -> degrees = 0
            Surface.ROTATION_90 -> degrees = 90
            Surface.ROTATION_180 -> degrees = 180
            Surface.ROTATION_270 -> degrees = 270
        }

        var result: Int
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360
            result = (360 - result) % 360  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360
        }
        return result
    }
}
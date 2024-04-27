package com.example.projecto2android

import android.app.Activity
import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.IOException
import android.media.MediaRecorder
import android.util.DisplayMetrics
import android.view.TextureView

class RecordVideo(private val activity: Activity, private val previewView: TextureView) {

    private var mediaRecorder: MediaRecorder? = null

    fun startRecording() {
        mediaRecorder = MediaRecorder()
        mediaRecorder?.apply {
            // Configurar MediaRecorder
            setAudioSource(MediaRecorder.AudioSource.CAMCORDER)
            setVideoSource(MediaRecorder.VideoSource.CAMERA) // Cambiar a CAMERA
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setOutputFile(getOutputFilePath())
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            val screenWidth = displayMetrics.widthPixels
            val screenHeight = displayMetrics.heightPixels

            // Calcular el tamaño del video (manteniendo la relación de aspecto)
            val videoWidth = if (screenWidth > screenHeight) screenHeight else screenWidth
            val videoHeight = if (screenWidth > screenHeight) screenWidth else screenHeight

            // Configurar el tamaño del video en MediaRecorder
            setVideoEncoder(MediaRecorder.VideoEncoder.H264)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            mediaRecorder?.setVideoSize(videoWidth, videoHeight)
            // Preparar MediaRecorder
            try {
                prepare()
                Log.d("EstadoMediaRecorder", mediaRecorder.toString())
            } catch (e: IOException) {
                e.printStackTrace()
                return
            }
            // Iniciar la grabación
            start()

        }

        // Actualizar la interfaz de usuario (opcional)
        // ...
    }

    fun stopRecording() {
        mediaRecorder?.let {
            it.stop()
            it.release()
            mediaRecorder = null
        }

        // Actualizar la interfaz de usuario (opcional)
        // ...
    }

    private fun getOutputFilePath(): String {
        // Crear un nombre de archivo único para el video
        val fileName = "Video_${System.currentTimeMillis()}.mov"
        val filePath = activity.getExternalFilesDir(Environment.DIRECTORY_MUSIC)!!.path + "/" + fileName
        return filePath
    }
}
package com.example.projecto2android

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.IOException
import android.media.MediaRecorder
class RecordAudio() {
    private lateinit var recorder : MediaRecorder
    //Este metodo devolvera el path hacia el archivo que ha guardado
    public fun StartRecording(context: Context): String{
        var aux : String = ""
            recorder = MediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setOutputFile(getOutputFilePath(context))
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                try {
                    prepare()
                } catch (e: IOException) {
                    Log.e("RecordFailed", "prepare() failed")
                }
                start()
            }

        return aux
    }

    public fun StopRecording(){
        recorder?.apply {
            try {
                stop()
            } catch (e: RuntimeException) {
                Log.e("RecordVideo", "Error stopping recording: ${e.message}")
            }
            release()
        }

    }
    private fun getOutputFilePath(context: Context): String {
        // Crear un nombre de archivo único para el video
        val fileName = "Audio_${System.currentTimeMillis()}.mp3"
        val filePath = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC)!!.path + "/" + fileName
        return filePath
    }
}

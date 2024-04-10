package com.example.projecto2android

import android.os.Environment
import android.util.Log
import java.io.File
import java.io.IOException
import android.media.MediaRecorder
class RecordAudio() {
    //Este metodo devolvera el path hacia el archivo que ha guardado
    public fun Record(): String{
        var aux : String = ""
            val outputFile = File(getExternalFilesDir(Environment.DIRECTORY_MUSIC), "recording.3gp")
            fileName = outputFile.absolutePath
            recorder = MediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setOutputFile(fileName)
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
}
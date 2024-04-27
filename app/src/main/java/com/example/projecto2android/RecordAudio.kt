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
    public fun StartRecording(context: Context, filePath : String): String{
        var aux : String = ""
            val outputFile = File(context.getExternalFilesDir(Environment.DIRECTORY_MUSIC), filePath)
            var fileName = outputFile.absolutePath + ".mp3"
            recorder = MediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                Log.d("Filename", fileName)
                setOutputFile(fileName)
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                try {
                    prepare()
                    aux = filePath
                } catch (e: IOException) {
                    Log.e("RecordFailed", "prepare() failed")
                }
                start()
            }

        return aux
    }

    public fun StopRecording(){
        recorder.stop()
    }
}

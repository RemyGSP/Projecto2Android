package com.example.projecto2android

import android.media.MediaPlayer
import android.util.Log
import java.io.IOException

class ReproduceAudio {
    lateinit var player : MediaPlayer
    public fun PlayAudio(fileName: String ) : MediaPlayer{
        player = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                prepare()

            } catch (e: IOException) {
                Log.e("PlaySoundFailed", "prepare() failed")
            }
        }
        return player;
    }
}
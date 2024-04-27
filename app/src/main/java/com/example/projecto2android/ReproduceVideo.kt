import android.media.MediaPlayer
import android.util.Log
import java.io.IOException

class ReproduceVideo {
    lateinit var player: MediaPlayer

    fun playVideo(filePath: String): MediaPlayer {
        player = MediaPlayer().apply {
            try {
                setDataSource(filePath)
                prepare()
                start() // Start playing
            } catch (e: IOException) {
                Log.e("ReproduceVideo", "prepare() failed")
            }
        }
        return player
    }

    fun pauseVideo() {
        player.pause()
    }

    fun resumeVideo() {
        player.start()
    }

    fun stopVideo() {
        player.stop()
        player.release()
    }
}
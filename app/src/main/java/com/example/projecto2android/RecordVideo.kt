import android.content.Context
import android.media.CamcorderProfile
import android.media.MediaRecorder
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.IOException

class RecordVideo {
    private lateinit var recorder: MediaRecorder

    // This method will start recording video and return the file path
    fun startRecording(context: Context, fileName: String): String {
        val outputFile = File(context.getExternalFilesDir(Environment.DIRECTORY_MOVIES), fileName)
        val filePath = outputFile.absolutePath + ".mp4"

        recorder = MediaRecorder()
        recorder.setVideoSource(MediaRecorder.VideoSource.SURFACE)

        recorder.apply {
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT)
            setVideoEncodingBitRate(100) // Adjust as per your requirements
            setVideoFrameRate(30) // Adjust as per your requirements
            setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH))

            setOutputFile(filePath)

            try {
                prepare()
            } catch (e: IOException) {
                Log.e("RecordVideo", "prepare() failed")
            }

            start() // Start recording
        }

        return filePath
    }

    // This method will stop the recording
    fun stopRecording() {
        recorder.apply {
            stop()
            release()
        }
    }
}
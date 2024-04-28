import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.projecto2android.R
import com.example.projecto2android.ReproduceAudio
import java.io.File

class FileAdapter(private val context: Context, private val files: List<File>) : RecyclerView.Adapter<FileAdapter.FileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_file, parent, false)
        return FileViewHolder(view)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val file = files[position]
        val filePath = file.path
        val fileExtension = getFileExtension(filePath)

        if (fileExtension.equals("mp4", ignoreCase = true)) {
            holder.bindVideo(file)
        } else {
            holder.bindAudio(file)
        }
    }

    override fun getItemCount(): Int {
        return files.size
    }

    inner class FileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val fileNameTextView: TextView = itemView.findViewById(R.id.fileNameTextView)
        private val videoView: VideoView = itemView.findViewById(R.id.videoView)
        val startButton: Button = itemView.findViewById(R.id.playButton)
        val stopButton: Button = itemView.findViewById(R.id.stopButton)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val file = files[adapterPosition]
            val filePath = file.path
            val fileExtension = getFileExtension(filePath)

            if (fileExtension.equals("mp4", ignoreCase = true)) {
                val intent = Intent(context, ReproduceVideo::class.java)
                intent.putExtra("video_path", Uri.fromFile(file).toString())
                context.startActivity(intent)
            } else {
                val mediaPlayer = ReproduceAudio().PlayAudio(filePath)
                mediaPlayer.start()
            }
        }

        fun bindAudio(file: File) {
            fileNameTextView.text = file.name
            videoView.visibility = View.GONE
            startButton.visibility = View.VISIBLE
            stopButton.visibility = View.VISIBLE
0
            val mediaPlayer = ReproduceAudio().PlayAudio(file.path)
            startButton.setOnClickListener {
                mediaPlayer.start()
            }
            stopButton.setOnClickListener {
                mediaPlayer.stop()
            }
        }
        fun bindVideo(file: File) {
            fileNameTextView.text = file.name
            videoView.visibility = View.VISIBLE
            startButton.visibility = View.GONE
            stopButton.visibility = View.GONE

            // Configurar el VideoView para reproducir el video
            videoView.setVideoURI(Uri.fromFile(file))
            videoView.setOnPreparedListener { mediaPlayer ->
                // Ajustar el tamaño del VideoView para que se ajuste al tamaño del video
                val videoWidth = mediaPlayer.videoWidth
                val videoHeight = mediaPlayer.videoHeight
                val videoProportion = videoWidth.toFloat() / videoHeight.toFloat()
                val screenWidth = videoView.resources.displayMetrics.widthPixels
                val screenHeight = videoView.resources.displayMetrics.heightPixels
                val screenProportion = screenWidth.toFloat() / screenHeight.toFloat()
                val lp = videoView.layoutParams
                if (videoProportion > screenProportion) {
                    lp.width = screenWidth
                    lp.height = (screenWidth / videoProportion).toInt()
                } else {
                    lp.width = (videoProportion * screenHeight).toInt()
                    lp.height = screenHeight
                }
                videoView.layoutParams = lp

                // Iniciar la reproducción del video
                mediaPlayer.start()
            }

            // Mostrar los botones de reproducción (opcional)
            startButton.visibility = View.VISIBLE
            stopButton.visibility = View.VISIBLE
        }
    }

    private fun getFileExtension(filePath: String): String {
        return filePath.substringAfterLast(".", "")
    }
}
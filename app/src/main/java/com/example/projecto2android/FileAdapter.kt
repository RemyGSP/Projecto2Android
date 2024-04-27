import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
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
        val mediaPlayer : MediaPlayer = ReproduceAudio().PlayAudio(filePath)
        holder.startButton.setOnClickListener {
            mediaPlayer.start()
        }
        holder.stopButton.setOnClickListener {
            mediaPlayer.stop()
            }
        holder.bind(file)
    }

    override fun getItemCount(): Int {
        return files.size
    }

    inner class FileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val fileNameTextView: TextView = itemView.findViewById(R.id.fileNameTextView)
        val startButton: Button = itemView.findViewById(R.id.playButton)
        val stopButton: Button = itemView.findViewById(R.id.stopButton)
        fun bind(file: File) {
            fileNameTextView.text = file.name
            itemView.setOnClickListener {
                val uri = Uri.fromFile(file)
                // Handle click event, e.g., play the audio/video file
            }
        }
    }
}
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.projecto2android.R
import com.example.projecto2android.ReproduceAudio
import com.example.projecto2android.ReproduceVideo
import java.io.File

class FileAdapter(private val context: Context, private val files: List<File>) : RecyclerView.Adapter<FileAdapter.FileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_file, parent, false)
        return FileViewHolder(view)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val file = files[position]
        val filePath = file.path
        holder.fileNameTextView.text = file.name
        val audio : MediaPlayer = ReproduceAudio().PlayAudio(file.absolutePath)
        holder.startButton.setOnClickListener(){
            val extension = getFileExtension(file.absolutePath)

            if (extension == "mp3") {
                audio.start()


            } else if (extension == "mov") {
                val intent = Intent(context, ReproduceVideo::class.java)
                intent.putExtra("filePath", file.absolutePath) // Pass file path to activity
                context.startActivity(intent)
            } else {
                // Handle unsupported file types
                Toast.makeText(context, "Unsupported file type", Toast.LENGTH_SHORT).show()
            }
        }
        holder.stopButton.setOnClickListener(){
            val extension = getFileExtension(file.absolutePath)

            if (extension == "mp3") {
                audio.stop()


            }else {
                // Handle unsupported file types
                Toast.makeText(context, "Unsupported file type", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return files.size
    }

    inner class FileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val fileNameTextView: TextView = itemView.findViewById(R.id.fileNameTextView)

        var startButton: Button = itemView.findViewById<Button?>(R.id.playButton)

        val stopButton: Button = itemView.findViewById<Button?>(R.id.stopButton)

    }



    private fun getFileExtension(filePath: String): String {
        return filePath.substringAfterLast(".", "")
    }
}

private fun getFileExtension(filePath: String): String {
        return filePath.substringAfterLast(".", "")
    }


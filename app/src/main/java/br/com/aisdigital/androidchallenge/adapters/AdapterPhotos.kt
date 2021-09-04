

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

import com.example.myapplication.R
import com.example.myapplication.model.TeamModel
import com.squareup.picasso.Picasso

class AdapterPhotos() : RecyclerView.Adapter<AdapterPhotos.AdapterHolder>() {

    private var listTeam: MutableList<TeamModel> = mutableListOf()

    inner class AdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val itemImage: AppCompatImageView = itemView.findViewById(R.id.itemImage)
        private val itemTitle: AppCompatTextView = itemView.findViewById(R.id.itemTitle)


        fun bind(team: TeamModel) {

            Picasso.get().load(team.teamImageUrl).into(itemImage)
            itemTitle.text = team.name.capitalize()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {
        return AdapterHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_photos, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {
        holder.bind(listTeam[position])
    }

    override fun getItemCount(): Int {
        return listTeam.size
    }

    fun addAll(item: MutableList<TeamModel>) {
        listTeam = item

        print(item)
        notifyDataSetChanged()
    }
}
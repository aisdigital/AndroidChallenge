package br.com.aisdigital.androidchallenge.ui.teams

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.data.responses.TeamListResponse
import com.bumptech.glide.Glide

class TeamListAdapter : RecyclerView.Adapter<TeamListAdapter.TeamListViewHolder>() {
    private var data: List<TeamListResponse>? = null

    fun setData(list: List<TeamListResponse>) {
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamListViewHolder {
        return TeamListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_team_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TeamListViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bindView(item)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    class TeamListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: TeamListResponse?) {
            val nameTeam: TextView = itemView.findViewById(R.id.name_team)
            val cityTeam: TextView = itemView.findViewById(R.id.city_team)
            val conferenceTeam: TextView = itemView.findViewById(R.id.conference_team)
            val descriptionTeam: TextView = itemView.findViewById(R.id.description_team)
            val imageTeam: ImageView = itemView.findViewById(R.id.image_team)
            val context = nameTeam.context

            nameTeam.text = context.getString(R.string.name_team, item?.name)
            cityTeam.text = context.getString(R.string.city, item?.city)
            descriptionTeam.text = context.getString(R.string.description, item?.description)
            conferenceTeam.text = context.getString(R.string.conference, item?.conference)
            Glide.with(context)
                .load(item?.teamImageUrl)
                .error(R.drawable.ic_no_image)
                .into(imageTeam)
        }
    }
}
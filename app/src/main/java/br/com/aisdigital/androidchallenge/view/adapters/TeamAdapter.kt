package br.com.aisdigital.androidchallenge.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.domain.model.TeamModel
import com.bumptech.glide.Glide

class TeamAdapter(
    private val responseList: List<TeamModel>,
    private val onItemClick: (TeamModel) -> Unit
) : RecyclerView.Adapter<TeamAdapter.TeamHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.team_item, parent, false)

        return TeamHolder(view)
    }

    override fun getItemCount(): Int {
        return responseList.size
    }

    override fun onBindViewHolder(holder: TeamHolder, position: Int) {
        holder.bind(responseList[position])
    }

    inner class TeamHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(team: TeamModel){

            val imageView = itemView.findViewById<ImageView>(R.id.photoTeam)
            val teamName =  itemView.findViewById<TextView>(R.id.teamName)
            val cityTeam = itemView.findViewById<TextView>(R.id.cityTeam)
            val conferenceTeam = itemView.findViewById<TextView>(R.id.conferenceTeam)
            val descriptionTeam = itemView.findViewById<TextView>(R.id.descriptionTeam)

            Glide.with(itemView)
                .load(team.teamImageUrl)
                .into(imageView)

            teamName.text = team.name

            cityTeam.text = team.city

            conferenceTeam.text = team.conference

            descriptionTeam.text = team.description
        }
    }
}
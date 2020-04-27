package br.com.aisdigital.androidchallenge.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.TeamPresentation
import br.com.aisdigital.androidchallenge.domain.teams.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class TeamListAdapter : RecyclerView.Adapter<TeamListAdapter.TeamViewHolder>() {

    var teams: List<TeamPresentation> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder = TeamViewHolder(parent)

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) = holder.bind(teams[position])

    inner class TeamViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)) {

        fun bind(presentation: TeamPresentation) = with(itemView) {
            Picasso.get()
                .load(presentation.image)
                .resize(96, 96)
                .centerCrop()
                .into(itemView.imageView)

            teamName.text = presentation.name
            teamCity.text = presentation.city
        }
    }
}
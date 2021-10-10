package br.com.aisdigital.androidchallenge.ui.activities.teams

import Team
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.databinding.AdapterTeamBinding
import com.squareup.picasso.Picasso

class TeamAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var teams = mutableListOf<Team>()

    fun setTeamsList(teams: List<Team>) {
        this.teams = teams.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterTeamBinding.inflate(inflater, parent, false)
        return MainViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val team = teams[position]
        holder.binding.name.text = team.name
        holder.binding.city.text = team.city
        holder.binding.conference.text = team.conference
        holder.binding.description.text = team.description
        Picasso.get().load(team.teamImageUrl).into(holder.binding.image);

    }

    override fun getItemCount(): Int {
        return teams.size
    }
}

class MainViewHolder(val binding: AdapterTeamBinding) : RecyclerView.ViewHolder(binding.root)
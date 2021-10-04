package br.com.aisdigital.androidchallenge.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.databinding.ItemTeamBinding
import br.com.aisdigital.androidchallenge.domain.model.teams.TeamsResponse
import com.bumptech.glide.Glide

class TeamViewHolder(private val binding: ItemTeamBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(team: TeamsResponse) {
        binding.run {
            item = team
            Glide.with(itemView)
                .load(team.imageUrl)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.nba_logo)
                .fallback(R.drawable.nba_logo)
                .into(imageTeam)
        }
    }

}
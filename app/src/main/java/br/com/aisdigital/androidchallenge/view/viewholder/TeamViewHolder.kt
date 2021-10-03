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
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .fallback(R.mipmap.ic_launcher)
                .into(imageTeam)
        }
    }

}
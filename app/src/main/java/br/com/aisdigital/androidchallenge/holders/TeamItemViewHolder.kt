package br.com.aisdigital.androidchallenge.holders

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.BR
import br.com.aisdigital.androidchallenge.model.Team

class TeamItemViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun binding(team: Team) {
        binding.apply {
            setVariable(BR.imageUrl, team.teamImageUrl)
            setVariable(BR.title, team.name)
            setVariable(BR.subtitle, team.description)
        }
    }
}

package br.com.aisdigital.androidchallenge.holder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.BR
import br.com.aisdigital.androidchallenge.domain.model.Team

class TeamsViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun binding(team: Team) {
        binding.apply {
            setVariable(BR.teamItems, team)
        }
    }
}

package br.com.aisdigital.androidchallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.databinding.TeamItemBinding
import br.com.aisdigital.androidchallenge.model.Team

class TeamAdapter(private var teams: List<Team>) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: TeamItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.team_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(teams[position])

    fun setData(teams: List<Team>) {
        this.teams = teams
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: TeamItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(team: Team) {
            binding.team = team
            binding.executePendingBindings()
        }
    }
}
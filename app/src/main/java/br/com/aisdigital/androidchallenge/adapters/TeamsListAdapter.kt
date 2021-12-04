package br.com.aisdigital.androidchallenge.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.databinding.TeamsListItemBinding
import br.com.aisdigital.androidchallenge.entities.Team

class TeamsListAdapter : RecyclerView.Adapter<TeamsListViewHolder>() {

    var teams = emptyList<Team>()
        set(value) {
            val result = DiffUtil.calculateDiff(TeamsListDiffCallback(field, value))
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsListViewHolder {
        return TeamsListViewHolder(
            TeamsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TeamsListViewHolder, position: Int) {
        val team = teams[position]
        holder.bind(team)
    }

    override fun getItemCount(): Int = teams.size
}
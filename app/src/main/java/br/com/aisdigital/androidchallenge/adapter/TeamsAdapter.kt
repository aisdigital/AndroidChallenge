package br.com.aisdigital.androidchallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.domain.model.Team
import br.com.aisdigital.androidchallenge.holder.TeamsViewHolder

class TeamsAdapter(private val itemClickAction: (Team) -> Unit):
    RecyclerView.Adapter<TeamsViewHolder>() {

    private var teamList: List<Team> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder =
        TeamsViewHolder(
            DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_teams_viewholder,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        val team = teamList[position]

        holder.apply {
            binding(team)

            itemView.setOnClickListener {
                itemClickAction.invoke(team)
            }
        }
        holder.binding(teamList[position])
    }

    override fun getItemCount(): Int = teamList.count()

    fun setTeamList(list: List<Team>) {
        this.teamList = list
        notifyDataSetChanged()
    }
}
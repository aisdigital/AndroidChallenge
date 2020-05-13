package br.com.aisdigital.androidchallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.holders.TeamItemViewHolder
import br.com.aisdigital.androidchallenge.internal.AppRouter
import br.com.aisdigital.androidchallenge.model.Team

class TeamListAdapter(private val router: AppRouter) :
    RecyclerView.Adapter<TeamItemViewHolder>() {

    private var teamList: MutableList<Team> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamItemViewHolder =
        TeamItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_team_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TeamItemViewHolder, position: Int) {
        val team = teamList[position]

        holder.apply {
            binding(team)

            itemView.setOnClickListener {
                router.goToDetail(team)
            }
        }
    }

    override fun getItemCount(): Int = teamList.count()

    fun setList(list: List<Team>) {
        this.teamList = list.toMutableList()
        notifyDataSetChanged()
    }
}

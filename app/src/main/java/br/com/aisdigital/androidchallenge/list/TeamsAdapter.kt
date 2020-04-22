package br.com.aisdigital.androidchallenge.list

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.data.model.Team
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_teams.view.*

class TeamsAdapter (var finishAction: ((Team) -> Unit)) : RecyclerView.Adapter<TeamsAdapter.ToolViewHolder>() {
    var list: List<Team> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_teams, parent, false)
        return ToolViewHolder(contactView, finishAction)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ToolViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ToolViewHolder(itemView: View, var clickAction: ((Team) -> Unit )) : RecyclerView.ViewHolder(itemView) {

        fun bind(team: Team) {
            description.text = team.description
            conference.text = team.conference
            name.text = team.name
            city.text = team.city
            Glide.with(itemView.context).load(team.teamImageUrl).into(icon)
            content.setOnClickListener{
                clickAction.invoke(team)
            }
        }

        private val description = itemView.description
        private val conference = itemView.conference
        private val name = itemView.name
        private val city = itemView.city
        private val icon = itemView.icon
        private val content = itemView.content
    }
}
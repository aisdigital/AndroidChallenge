package br.com.aisdigital.androidchallenge.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.core.domain.model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_list_item.view.*

class TeamListAdapter(private val teams: List<Team>, private val clickListener: (Team) -> Unit) :
    RecyclerView.Adapter<TeamListAdapter.TeamsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.team_list_item, parent, false)

        return TeamsViewHolder(layout)
    }

    override fun getItemCount() = teams.size

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) =
        holder.bind(teams[position], clickListener)

    class TeamsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(item: Team, clickListener: (Team) -> Unit) = with(itemView) {
            Picasso.get()
                .load(item.teamImageUrl)
                .into(image)
            name.text = item.name
            city.text = item.city
            setOnClickListener { clickListener(item) }
        }
    }
}
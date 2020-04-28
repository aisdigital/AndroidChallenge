package br.com.aisdigital.androidchallenge.teams

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.TeamPresentation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class TeamListAdapter : RecyclerView.Adapter<TeamListAdapter.TeamViewHolder>() {

    var teams: List<TeamPresentation> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder = TeamViewHolder(parent)

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) = holder.bind(teams[position])

    inner class TeamViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)) {

        fun bind(presentation: TeamPresentation) = with(itemView) {
            Picasso.get()
                .load(presentation.image)
                .fit()
                .into(itemView.image)

            name.text = presentation.name
            description.text = presentation.descr

            bindClick(presentation)
        }

        private fun bindClick(presentation: TeamPresentation) = with(itemView) {
            itemView.lyt_parent.setOnClickListener {
                val bundle = Bundle()
                bundle.putParcelable("team", presentation)

                val intent = Intent(context, TeamDetailsActivity::class.java)
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
        }
    }
}
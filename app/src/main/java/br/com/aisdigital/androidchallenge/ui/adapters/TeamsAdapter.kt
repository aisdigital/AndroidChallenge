package br.com.aisdigital.androidchallenge.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.model.Team
import coil.api.load
import coil.size.Scale
import kotlinx.android.synthetic.main.item_team.view.*


class TeamsAdapter(
    val items: MutableList<Team>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        return TeamViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TeamViewHolder) {
            val item = items[holder.adapterPosition]

            if (item.teamImageUrl.isNotEmpty()) {
                holder.tvImage.load(item.teamImageUrl) {
                    crossfade(750)
                    placeholder(R.drawable.ic_image_placeholder)
                    error(R.drawable.ic_image_placeholder)
                    scale(Scale.FILL)
                }
            }
            holder.tvName.text = item.name
            holder.tvDescription.text = item.conference

        }
    }

    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvImage: ImageView by lazy { itemView.iv_image }
        val tvName: TextView by lazy { itemView.tv_name }
        val tvDescription: TextView by lazy { itemView.tv_description }
    }
}
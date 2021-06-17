package br.com.aisdigital.androidchallenge.fragment

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.model.Team
import com.bumptech.glide.Glide

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class TeamRecyclerViewAdapter(
    private val values: List<Team>,
    private val fragment: Fragment
) : RecyclerView.Adapter<TeamRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.team_item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.name
        holder.contentView.text = item.description

        Glide.with(fragment).load(item.teamImageUrl).into(holder.logo);
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)
        val logo : ImageView = view.findViewById(R.id.logo)
        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}
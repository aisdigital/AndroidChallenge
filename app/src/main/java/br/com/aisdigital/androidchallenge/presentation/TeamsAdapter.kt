package br.com.aisdigital.androidchallenge.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.databinding.TeamItemListBinding
import com.squareup.picasso.Picasso

class TeamsAdapter(private val teams: List<Team>) : RecyclerView.Adapter<TeamsAdapter.ViewHolder>() {

    private lateinit var parentContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        parentContext = parent.context
        val binding = TeamItemListBinding.inflate(LayoutInflater.from(parentContext), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(teams[position])

    override fun getItemCount(): Int = teams.size

    inner class ViewHolder(private val binding: TeamItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(team: Team) = with(binding) {
            teamName.text = team.name
            teamCity.text = team.city
            Picasso.get().load(team.teamImageUrl).placeholder(R.drawable.placeholder_image).into(logo)
            binding.root.setOnClickListener { openDetails(team) }
        }

    }

    fun openDetails(team: Team) {
        val dialog = TeamDetailModal()
        dialog.arguments = Bundle().apply {
            putString("teamDescription", team.description)
        }
        dialog.show((parentContext as FragmentActivity).supportFragmentManager, "categoryPicker")
    }
}
package br.com.aisdigital.androidchallenge.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.databinding.ItemTeamBinding
import br.com.aisdigital.androidchallenge.domain.model.teams.TeamsResponse
import br.com.aisdigital.androidchallenge.view.viewholder.TeamViewHolder

class TeamAdapter(context: Context) : RecyclerView.Adapter<TeamViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private val mList: MutableList<TeamsResponse> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = DataBindingUtil.inflate<ItemTeamBinding>(inflater, viewType, parent, false)
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    fun updateList(list: List<TeamsResponse>) {
        mList.addAll(list)
        notifyItemInserted(0)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_team
    }

}
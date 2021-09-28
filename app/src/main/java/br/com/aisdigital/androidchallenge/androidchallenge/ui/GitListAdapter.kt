package br.com.aisdigital.androidchallenge.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.model.Team
import coil.load
import coil.transform.CircleCropTransformation
import kotlinx.android.synthetic.main.item_rv.view.*

class GitListAdapter() : RecyclerView.Adapter<GitListAdapter.GitViewHolder>() {

    private var list = ArrayList<Team>()

    fun setData(list: ArrayList<Team>) {
        this.list.addAll(list)
    }

    class GitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtName = itemView.txtName
        val txtDesc = itemView.txtDesc
        val txtCity = itemView.txtCity
        val imgAvatar = itemView.teamImageUrl
        val txtConf = itemView.txtConf

        fun bind(data: Team) {
            txtName.text = data.name
            txtCity.text = "City: " + data.city
            txtDesc.text = data.description
            txtConf.text = "Conference: " + data.conference
            val url = data.teamImageUrl

            imgAvatar.load(url) {
                crossfade(true)
                placeholder(R.drawable.ic_image_place_holder)
                transformations(CircleCropTransformation())
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitViewHolder {
        return GitViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rv, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: GitViewHolder, position: Int) {
        holder.bind(list[position])


    }


}
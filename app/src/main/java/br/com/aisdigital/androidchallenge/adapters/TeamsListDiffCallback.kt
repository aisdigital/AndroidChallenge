package br.com.aisdigital.androidchallenge.adapters

import androidx.recyclerview.widget.DiffUtil
import br.com.aisdigital.androidchallenge.entities.Team

class TeamsListDiffCallback(private val oldList: List<Team>, private val newList: List<Team>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
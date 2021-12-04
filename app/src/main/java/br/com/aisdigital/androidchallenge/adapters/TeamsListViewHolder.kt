package br.com.aisdigital.androidchallenge.adapters

import android.content.Context
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.databinding.TeamsListItemBinding
import br.com.aisdigital.androidchallenge.entities.Team
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import java.util.*

@GlideModule
class MyAppGlideModule : AppGlideModule()

class TeamsListViewHolder(private var binding: TeamsListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(team: Team) {
        binding.teamName.text = team.name
        binding.teamCity.text = team.city
        binding.teamConference.text = team.conference.lowercase()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        binding.teamDescription.text = team.description.subSequence(0, 90).toString().plus("...")

        val imgUri = team.teamImageUrl.toUri().buildUpon().scheme("https").build()
        GlideApp.with(binding.teamImage)
            .load(imgUri)
            .placeholder(getLoadingDrawable(binding.root.context))
            .error(R.drawable.ic_error)
            .into(binding.teamImage)

        binding.root.setOnClickListener {
            if (team.descriptionCollapsed) {
                binding.teamDescription.text = team.description
                team.descriptionCollapsed = false
            } else {
                binding.teamDescription.text =
                    team.description.subSequence(0, 90).toString().plus("...")
                team.descriptionCollapsed = true
            }
        }
    }

    private fun getLoadingDrawable(
        context: Context,
        width: Float = 5f,
        radius: Float = 30f
    ): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = width
        circularProgressDrawable.centerRadius = radius
        circularProgressDrawable.setColorSchemeColors(android.R.attr.progressBarStyle)
        circularProgressDrawable.start()
        return circularProgressDrawable
    }
}
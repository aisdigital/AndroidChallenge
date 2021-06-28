package br.com.aisdigital.androidchallenge.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.model.Team


class TeamAdapter : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    var teams: List<Team> = ArrayList<Team>();

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.team_recycler_item, parent, false)
        );
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = teams[position];

        holder.title.text = team.name;
        holder.description.text = team.description;

//        val url = URL(team.teamImageUrl);
//        val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//        holder.image.setImageBitmap(bmp);
    }

    override fun getItemCount(): Int {
        return teams.size;
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.image_team);
        var title: TextView = itemView.findViewById(R.id.text_team_title);
        var description: TextView = itemView.findViewById(R.id.text_team_description);
    }
}
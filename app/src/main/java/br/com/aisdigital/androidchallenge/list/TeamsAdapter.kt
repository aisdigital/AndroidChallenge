package br.com.aisdigital.androidchallenge.list

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.data.model.Team
import com.bumptech.glide.Glide

class TeamsAdapter (var finishAction: ((Team) -> Unit)) : RecyclerView.Adapter<TeamsAdapter.ToolViewHolder>() {
    var list: List<Team> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_teams, parent, false)
        return ToolViewHolder(contactView, finishAction)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ToolViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ToolViewHolder(itemView: View, var finishAction: ((Team) -> Unit )) : RecyclerView.ViewHolder(itemView) {

        fun bind(team: Team) {
            /*
            tvName.text = Tool.nome
            //tvToolName.text = Tool.tool?.nome
            tvToolNumber.text = "Ferramenta # ${Tool?.numero}"
            tvAddress.text = "Endereço: ${Tool.local}"
            tvStatus.text = Tool.status
            tvSpecifications.text = "Especificações: ${Tool.especificacoes}"

            when (Tool.status) {
                "disponivel" -> {
                    tvStatus.setTextColor(itemView.context.resources.getColor(R.color.active))
                    cvContent.setBackgroundColor(itemView.context.resources.getColor(R.color.bgActive))
                    btCall.text = "P/ Manutenção"
                    btCall.visibility = View.VISIBLE
                }
                "manutencao" -> {
                    tvStatus.setTextColor(itemView.context.resources.getColor(R.color.inactive))
                    cvContent.setBackgroundColor(itemView.context.resources.getColor(R.color.bgInactive))
                    btCall.text = "P/ Disponivel"
                    btCall.visibility = View.VISIBLE
                }
                else -> {
                    tvStatus.setTextColor(itemView.context.resources.getColor(R.color.inactive))
                    cvContent.setBackgroundColor(itemView.context.resources.getColor(R.color.bgInactive))
                    btCall.visibility = View.GONE
                }
            }

            ivMapIcone.setOnClickListener {
                val gmmIntentUri: Uri = Uri.parse("geo:0,0?q=${Tool.local}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                itemView.context.startActivity(mapIntent)
            }

            btCall.setOnClickListener {
                finishAction.invoke(Tool)
            }

            when (Tool?.tipo) {
                "container" -> {
                    Glide.with(itemView.context).load(R.drawable.icon_container).into(tvIcon)
                }
                "betoneira" -> {
                    Glide.with(itemView.context).load(R.drawable.icon_container).into(tvIcon)
                }
                "andaime" -> {

                }
                "banheiroquimico" -> {

                }
                else -> {
                    Glide.with(itemView.context).load(R.drawable.icon_container).into(tvIcon);
                }
            }*/
        }


    }
}
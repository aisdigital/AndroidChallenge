package br.com.aisdigital.androidchallenge.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import br.com.aisdigital.androidchallenge.databinding.DialogTeamDetailBinding
import br.com.aisdigital.androidchallenge.domain.model.Team

class TeamDetailDialog: DialogFragment() {

    companion object {
        const val EXTRA_TEAM: String = "team_detail_to_show"

        fun getDialog(team: Team): TeamDetailDialog {
            val dialog = TeamDetailDialog()
            val arguments = Bundle()
            arguments.putSerializable(EXTRA_TEAM, team)
            dialog.arguments = arguments
            dialog.setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar)

            return dialog
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.setCanceledOnTouchOutside(true)

        val detailFragmentBinding = DialogTeamDetailBinding.inflate(inflater, container, false)
        val team = arguments?.getSerializable(EXTRA_TEAM) as Team?

        detailFragmentBinding.teamDetail = team

        return detailFragmentBinding.root
    }

}
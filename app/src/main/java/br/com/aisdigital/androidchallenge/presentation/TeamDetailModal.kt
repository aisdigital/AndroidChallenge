package br.com.aisdigital.androidchallenge.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.aisdigital.androidchallenge.databinding.TeamDetailModalBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TeamDetailModal : BottomSheetDialogFragment() {

    private lateinit var binding: TeamDetailModalBinding

    private lateinit var teamDescription: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        teamDescription = requireArguments().getString("teamDescription", "")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TeamDetailModalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.description.text = teamDescription
    }
}
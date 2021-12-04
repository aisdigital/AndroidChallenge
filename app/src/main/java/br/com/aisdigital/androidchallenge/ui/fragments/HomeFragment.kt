package br.com.aisdigital.androidchallenge.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.adapters.TeamsListAdapter
import br.com.aisdigital.androidchallenge.databinding.FragmentHomeBinding
import br.com.aisdigital.androidchallenge.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private val adapter = TeamsListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater)

        binding.teamsList.adapter = adapter
        binding.teamsList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.user.observe(viewLifecycleOwner, { user ->
            if (user != null) {
                binding.greetingText.text = getString(R.string.greeting_text, user.name)
            }
        })

        viewModel.teams.observe(viewLifecycleOwner, {
            adapter.teams = it
        })

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                viewModel.logout()
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
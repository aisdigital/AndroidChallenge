package br.com.aisdigital.androidchallenge.ui.home_user

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.data.network.AuthApi
import br.com.aisdigital.androidchallenge.data.network.Resource
import br.com.aisdigital.androidchallenge.data.repository.HomeRepository
import br.com.aisdigital.androidchallenge.databinding.FragmentHomeUserBinding
import br.com.aisdigital.androidchallenge.ui.ActivityLifeCycleObserver
import br.com.aisdigital.androidchallenge.ui.base.BaseFragment
import br.com.aisdigital.androidchallenge.ui.teams.TeamListAdapter
import br.com.aisdigital.androidchallenge.ui.visible

class HomeUserFragment : BaseFragment<HomeViewModel, FragmentHomeUserBinding, HomeRepository>() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.lifecycle?.addObserver(ActivityLifeCycleObserver {
            viewModel.login(userPreferences.authToken.toString())
            val adapter = TeamListAdapter()
            binding.recyclerViewTeams.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerViewTeams.adapter = adapter
            viewModel.getTeamList()
            binding.progressbar.visible(true)
            hideView()

            viewModel.loginResponse.observe(viewLifecycleOwner, {
                binding.progressbar.visible(false)
                when (it) {
                    is Resource.Success -> {
                        //  Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                        binding.nameUser.text =
                            requireContext().getString(R.string.name, it.value.name)
                        binding.ageUser.text =
                            requireContext().getString(R.string.age, it.value.age)
                        binding.genderUser.text =
                            requireContext().getString(R.string.gender, it.value.gender)
                    }
                    is Resource.Failure -> {
                        Toast.makeText(requireContext(), "User Failure", Toast.LENGTH_LONG).show()
                    }
                }
            })

            viewModel.teamResponse.observe(viewLifecycleOwner, {
                when (it) {
                    is Resource.Success -> {
                        //  Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                        showView()
                        adapter.setData(it.value)
                    }
                    is Resource.Failure -> {
                        Toast.makeText(requireContext(), "Team List Failure", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            })
        })
    }

    private fun hideView() {
        binding.recyclerViewTeams.visible(false)
        binding.nameUser.visible(false)
        binding.ageUser.visible(false)
        binding.divider.visible(false)
        binding.genderUser.visible(false)
        binding.subtitle.visible(false)
    }

    private fun showView() {
        binding.recyclerViewTeams.visible(true)
        binding.divider.visible(true)
        binding.nameUser.visible(true)
        binding.ageUser.visible(true)
        binding.genderUser.visible(true)
        binding.subtitle.visible(true)
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeUserBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): HomeRepository =
        HomeRepository(remoteDataSource.buildApi(AuthApi::class.java))
}
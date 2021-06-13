package br.com.aisdigital.androidchallenge.ui.home_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.data.network.AuthApi
import br.com.aisdigital.androidchallenge.data.network.Resource
import br.com.aisdigital.androidchallenge.data.repository.HomeRepository
import br.com.aisdigital.androidchallenge.databinding.FragmentHomeUserBinding
import br.com.aisdigital.androidchallenge.ui.base.BaseFragment
import br.com.aisdigital.androidchallenge.ui.visible

class HomeUserFragment : BaseFragment<HomeViewModel, FragmentHomeUserBinding, HomeRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.login(userPreferences.authToken.toString())
        binding.progressbar.visible(true)

        viewModel.loginResponse.observe(viewLifecycleOwner, {
            binding.progressbar.visible(false)
            when (it) {
                is Resource.Success -> {
                    //  Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                    binding.nameUser.text = requireContext().getString(R.string.name, it.value.name)
                    binding.ageUser.text = requireContext().getString(R.string.age, it.value.age)
                    binding.genderUser.text = requireContext().getString(R.string.gender, it.value.gender)

                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Home Failure", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeUserBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): HomeRepository =
        HomeRepository(remoteDataSource.buildApi(AuthApi::class.java))
}
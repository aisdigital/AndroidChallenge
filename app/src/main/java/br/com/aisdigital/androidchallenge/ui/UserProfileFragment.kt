package br.com.aisdigital.androidchallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.core.domain.model.User
import kotlinx.android.synthetic.main.fragment_user_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserProfileFragment : Fragment() {

    private val viewModel: UserProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewState()
        getUserProfile()
    }

    private fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            renderView(it)
        })
    }

    private fun getUserProfile() {
        viewModel.userProfile()
    }

    private fun renderView(viewState: UserProfileViewState) {
        when (viewState) {
            is UserProfileViewState.ShowLogin -> findNavController().navigate(R.id.loginFragment)
            is UserProfileViewState.ShowUserProfile -> showUserProfile(viewState.user)
        }
    }

    private fun showUserProfile(user: User?) {
        user?.let {
            name.text = it.name
            other.text = getString(R.string.other, it.gender, it.age)
        }
    }
}
package br.com.aisdigital.androidchallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.extensions.hideKeyboard
import br.com.aisdigital.androidchallenge.ui.adapters.TeamsAdapter
import br.com.aisdigital.androidchallenge.viewmodel.TeamsViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.fragment_teams.*

class TeamsFragment : Fragment() {

    private val adapterStatements by lazy {
        TeamsAdapter(
            mutableListOf()
        )
    }

    private val viewModel: TeamsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.hideKeyboard()

        observerError()
        observerSuccess()
        observerLoading()

        setupRecyclerView()

    }

    private fun observerError(){
        viewModel.error.observe(viewLifecycleOwner, Observer {
            it?.let {
                SweetAlertDialog(requireContext(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(getString(R.string.atencao))
                    .setContentText(it)
                    .setConfirmText(getString(R.string.ok))
                    .show()
            }
        })
    }

    private fun observerSuccess(){
        viewModel.success.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapterStatements.items.clear()
                adapterStatements.items.addAll(it)
                adapterStatements.notifyDataSetChanged()
            }
        })
    }

    private fun observerLoading(){
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            pb_loading.isVisible = it
        })
    }

    private fun setupRecyclerView(){
        rv_statements.apply {
            layoutManager = LinearLayoutManager(this@TeamsFragment.activity, LinearLayoutManager.VERTICAL,false)
            adapter = this@TeamsFragment.adapterStatements
        }
    }
}

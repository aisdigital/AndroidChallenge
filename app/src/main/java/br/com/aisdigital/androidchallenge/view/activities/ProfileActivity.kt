package br.com.aisdigital.androidchallenge.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import br.com.aisdigital.androidchallenge.BR
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.extensions.bindingContentView
import br.com.aisdigital.androidchallenge.model.UserInfo
import br.com.aisdigital.androidchallenge.viewmodel.ProfileViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProfileActivity : AppCompatActivity() {

    private val viewModel: ProfileViewModel by viewModel {
        parametersOf(intent?.getParcelableExtra(EXTRA_USER_INFO))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
    }

    private fun setupBinding() {
        bindingContentView(R.layout.activity_profile).apply {
            setVariable(BR.closeClick, View.OnClickListener { onBackPressed() })
            setVariable(BR.viewModel, viewModel)
            lifecycleOwner = this@ProfileActivity
        }
    }

    companion object {
        const val EXTRA_USER_INFO = "extra_user_info"
    }
}

fun FragmentActivity.startProfileActivity(userInfo: UserInfo) {
    this.startActivity(
        Intent(
            this,
            ProfileActivity::class.java
        ).putExtra(ProfileActivity.EXTRA_USER_INFO, userInfo)
    )
}

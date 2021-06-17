package br.com.aisdigital.androidchallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.aisdigital.androidchallenge.events.TeamsLoadedEvent
import br.com.aisdigital.androidchallenge.events.UserLoginEvent
import br.com.aisdigital.androidchallenge.fragment.LoginFragment
import br.com.aisdigital.androidchallenge.fragment.TeamFragment
import br.com.aisdigital.androidchallenge.model.Option
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addLoginFragment()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private fun addLoginFragment(){
        val loginFragment : LoginFragment  = LoginFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, loginFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUserEvent(event: UserLoginEvent) {
        render(event.userOption)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onTeamEvent(event: TeamsLoadedEvent) {
        render(event.teamsOption)
    }

    private fun render(op: Option) {
        when (op) {
            is Option.SomeUser -> addTeamsFragment(op)
            is Option.None -> showMessage(getString(op.errorId))
            else -> showMessage("Undefined option " + op.javaClass.name)
        }
    }

    private fun addTeamsFragment(user : Option.SomeUser) {
        val teamFragment : TeamFragment  = TeamFragment.newInstance(1, user)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, teamFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun showMessage(msg : String){
        Snackbar
            .make(mainLayout, msg, Snackbar.LENGTH_LONG)
            .setAnimationMode(Snackbar.ANIMATION_MODE_FADE)
            .setDuration(6_000)
            .show()
    }
}

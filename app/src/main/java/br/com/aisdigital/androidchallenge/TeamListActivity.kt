package br.com.aisdigital.androidchallenge

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.adapter.TeamAdapter
import br.com.aisdigital.androidchallenge.retrofit.RequestService
import br.com.aisdigital.androidchallenge.util.SharedPrefUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TeamListActivity : BaseActivity() {

    @Inject
    lateinit var requestService: RequestService;

    lateinit var recyclerView: RecyclerView;
    lateinit var adapter: TeamAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);

        recyclerView = findViewById(R.id.recycler_teams);
        recyclerView.layoutManager = LinearLayoutManager(this@TeamListActivity);
        recyclerView.setHasFixedSize(true);

        adapter = TeamAdapter();
        recyclerView.adapter = adapter;
    }

    override fun onResume() {
        super.onResume();

        showProgressDialog("Baixando a lsita de times", "");

        val doTeam = Runnable {
            val response = requestService.getTeams().execute();
            if (response.isSuccessful) {
                adapter.teams = response.body()!!;
                refresh();

            } else {
                makeToast("Erro ao tentar baixar a lista. Tente novamente mais tarde");
            }
        };

        handlerThread = HandlerThread("Team");
        handlerThread!!.start();
        handler = Handler(handlerThread!!.looper);
        handler!!.post(doTeam);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_team_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_exit) {
            exit();
        }
        return super.onOptionsItemSelected(item);
    }

    private fun refresh() {
        val doRefresh = Runnable {
            adapter.notifyDataSetChanged();
            closeProgressDialog();
        };
        runOnUiThread(doRefresh);
    }

    private fun exit() {
        SharedPrefUtil.removeToken(this@TeamListActivity);
        val intent = Intent(this@TeamListActivity, MainActivity::class.java);
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
        startActivity(intent);
    }
}
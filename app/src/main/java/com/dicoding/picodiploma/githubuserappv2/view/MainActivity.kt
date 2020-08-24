package com.dicoding.picodiploma.githubuserappv2.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.githubuserappv2.viewmodel.ListUserAdapter
import com.dicoding.picodiploma.githubuserappv2.viewmodel.MainViewModel
import com.dicoding.picodiploma.githubuserappv2.R
import com.dicoding.picodiploma.githubuserappv2.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    private var list: ArrayList<User> = arrayListOf()
    private lateinit var listAdapter : ListUserAdapter
    private lateinit var mainViewModel : MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listAdapter =
            ListUserAdapter(
                list
            )

        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        searchData()
        viewConfig()
        runGetDataGit()
        configMainViewModel(listAdapter)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.change_language) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)

        }
        return super.onOptionsItemSelected(item)
    }


    private fun viewConfig() {
        rv_users.layoutManager = LinearLayoutManager(this)
        rv_users.setHasFixedSize(true)

        listAdapter.notifyDataSetChanged()
        rv_users.adapter = listAdapter
    }

    private fun runGetDataGit() {
        mainViewModel.getDataGit(applicationContext)
        progressBar.visibility = View.VISIBLE
    }

    private fun configMainViewModel(adapter: ListUserAdapter) {
        mainViewModel.getListUsers().observe(this, Observer { listUsers ->
            if (listUsers != null) {
                adapter.setData(listUsers)
                progressBar.visibility = View.INVISIBLE
            }
        })
    }

    private fun searchData() {
        user_search.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()) {
                    list.clear()
                    viewConfig()
                    mainViewModel.getDataGitSearch(query, applicationContext)
                    progressBar.visibility = View.VISIBLE
                    configMainViewModel(listAdapter)
                } else {
                    return true
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

        })
    }
}

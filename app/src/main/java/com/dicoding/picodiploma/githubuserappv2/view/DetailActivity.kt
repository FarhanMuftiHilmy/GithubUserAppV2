package com.dicoding.picodiploma.githubuserappv2.view


import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.githubuserappv2.viewmodel.DetailAdapter
import com.dicoding.picodiploma.githubuserappv2.R
import com.dicoding.picodiploma.githubuserappv2.model.User
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            viewpager.layoutParams.height = resources.getDimension(R.dimen.height).toInt()
        } else {
            viewpager.layoutParams.height = resources.getDimension(R.dimen.height).toInt()
        }

        if (supportActionBar != null) {
            supportActionBar?.title = "Detail User"
        }
        setData()
        viewPagerConfig()
    }

    private fun viewPagerConfig() {
        val viewPagerDetail =
            DetailAdapter(
                this,
                supportFragmentManager
            )
        viewpager.adapter = viewPagerDetail
        tabs.setupWithViewPager(viewpager)
        supportActionBar?.elevation = 0f
    }

    private fun setData() {
        val user = intent.getParcelableExtra(EXTRA_DETAIL) as User
        Glide.with(this)
            .load(user.avatar)
            .apply(RequestOptions())
            .into(img_item_avatar)  
        tv_item_name.text = user.name
        tv_item_username.text = user.username
        tv_item_company.text = user.company
        tv_item_location.text = user.location
        tv_item_following.text = user.following
        tv_item_followers.text = user.follower
        tv_item_repository.text = user.repository


    }


    }




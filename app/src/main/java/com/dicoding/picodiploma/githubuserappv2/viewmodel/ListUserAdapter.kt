package com.dicoding.picodiploma.githubuserappv2.viewmodel

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.githubuserappv2.R
import com.dicoding.picodiploma.githubuserappv2.model.User
import com.dicoding.picodiploma.githubuserappv2.view.DetailActivity
import kotlinx.android.synthetic.main.item_row_users.view.*

class ListUserAdapter(private val listUserGithub: ArrayList<User>) : RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {


    fun setData(items: ArrayList<User>) {
        listUserGithub.clear()
        listUserGithub.addAll(items)
        notifyDataSetChanged()
    }
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("StringFormatInvalid")
        fun bind(dataUsers: User) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(dataUsers.avatar)
                    .apply(RequestOptions().override(100, 100))
                    .into(img_item_avatar)

                tv_item_name.text = dataUsers.name
                tv_item_username.text = itemView.context.getString(R.string.user_name, dataUsers.username)
                tv_item_repository.text = itemView.context.getString(R.string.repository, dataUsers.repository)
                tv_item_followers.text = itemView.context.getString(R.string.follower, dataUsers.follower)
                tv_item_following.text = itemView.context.getString(R.string.followings, dataUsers.following)

            }
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_users, viewGroup, false)
        return ListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUserGithub[position])
        val user = listUserGithub[position]
        holder.itemView.setOnClickListener {
            val dataUserIntent =
                User(
                    user.name,
                    user.username,
                    user.company,
                    user.location,
                    user.avatar,
                    user.repository,
                    user.follower,
                    user.following
                )
            val mIntent = Intent(it.context, DetailActivity::class.java)
            mIntent.putExtra(DetailActivity.EXTRA_DETAIL, dataUserIntent)
            it.context.startActivity(mIntent)
        }


    }

    override fun getItemCount(): Int {
        return listUserGithub.size
    }



}
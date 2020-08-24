package com.dicoding.picodiploma.githubuserappv2.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.githubuserappv2.R
import com.dicoding.picodiploma.githubuserappv2.model.DataFollowers
import kotlinx.android.synthetic.main.activity_detail.view.*

class ListFollowerAdapter(private val listDataFollower: ArrayList<DataFollowers>) :
    RecyclerView.Adapter<ListFollowerAdapter.ListDataHolder>() {

    fun setData(items: ArrayList<DataFollowers>) {
        listDataFollower.clear()
        listDataFollower.addAll(items)
        notifyDataSetChanged()
    }

    inner class ListDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataFollowers: DataFollowers) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(dataFollowers.avatar)
                    .apply(RequestOptions().override(100, 100))
                    .into(img_item_avatar)

                tv_item_name.text = dataFollowers.name
                tv_item_username.text =
                    itemView.context.getString(R.string.user_name, dataFollowers.username)
                tv_item_repository.text =
                    itemView.context.getString(R.string.repository, dataFollowers.repository)
                tv_item_followers.text =
                    itemView.context.getString(R.string.follower, dataFollowers.follower)
                tv_item_following.text =
                    itemView.context.getString(R.string.followings, dataFollowers.following)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDataHolder {
        return ListDataHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_users, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listDataFollower.size
    }

    override fun onBindViewHolder(holder: ListDataHolder, position: Int) {
        holder.bind(listDataFollower[position])
    }
}
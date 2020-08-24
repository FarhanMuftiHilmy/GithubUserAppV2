package com.dicoding.picodiploma.githubuserappv2.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.githubuserappv2.R
import com.dicoding.picodiploma.githubuserappv2.model.DataFollowing
import kotlinx.android.synthetic.main.activity_detail.view.*

class ListFollowingAdapter(private val listDataFollowing: ArrayList<DataFollowing>) :
    RecyclerView.Adapter<ListFollowingAdapter.ListDataHolder>() {

    fun setData(item: ArrayList<DataFollowing>) {
        listDataFollowing.clear()
        listDataFollowing.addAll(item)
        notifyDataSetChanged()
    }

    inner class ListDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataFollowing: DataFollowing) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(dataFollowing.avatar)
                    .apply(RequestOptions().override(100, 100))
                    .into(img_item_avatar)

                tv_item_name.text = dataFollowing.name
                tv_item_username.text =
                    itemView.context.getString(R.string.user_name, dataFollowing.username)
                tv_item_repository.text =
                    itemView.context.getString(R.string.repository, dataFollowing.repository)
                tv_item_followers.text =
                    itemView.context.getString(R.string.follower, dataFollowing.follower)
                tv_item_following.text =
                    itemView.context.getString(R.string.followings, dataFollowing.following)

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListDataHolder {
        return ListDataHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_users, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listDataFollowing.size
    }

    override fun onBindViewHolder(holder: ListDataHolder, position: Int) {
        holder.bind(listDataFollowing[position])
    }
}
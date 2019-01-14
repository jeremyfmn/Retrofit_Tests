package com.example.jfalck.retrofit_tests

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class ResultsAdapter(context: Context) : RecyclerView.Adapter<ResultsAdapter.MyViewHolder>() {

    private val mContext = context;
    private var posts: List<ApiModel.Post> = emptyList()

    fun setPosts(newPosts: List<ApiModel.Post>) {
        posts = newPosts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.getContext())
        val view = inflater.inflate(R.layout.result_item, parent, false)
        return MyViewHolder(view, mContext)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, postion: Int) {
        holder.display(posts[postion])
    }

    class MyViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView)
    {
        private var title: TextView? = null
        private var url: TextView? = null

        private var currentPost: ApiModel.Post? = null

        init {
            title = itemView.findViewById<View>(R.id.result_title) as TextView
            url = (itemView.findViewById<View>(R.id.result_link) as TextView)

            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(currentPost!!.url)
                context.startActivity(intent)
            }
        }

        fun display(post: ApiModel.Post) {
            currentPost = post
            title!!.text = post.title
            url!!.text = post.url
        }

    }


}

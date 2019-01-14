package com.example.jfalck.retrofit_tests

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.*


class ResultsAdapter(context: Context) : RecyclerView.Adapter<ResultsAdapter.MyViewHolder>() {

    private val mContext = context;
    private val posts = Arrays.asList(Post("test", "https://i.redd.it/4qtq32jxe6a21.jpg"), Post("test2", "https://i.redd.it/tkcsikkmeca21.jpg"))

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

        private var currentPost: Post? = null

        init {
            title = itemView.findViewById<View>(R.id.result_title) as TextView
            url = (itemView.findViewById<View>(R.id.result_link) as TextView)

            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(currentPost!!.mUrl)
                context.startActivity(intent)
            }
        }

        fun display(post: Post) {
            currentPost = post
            title!!.text = post.mTitle
            url!!.text = post.mUrl
        }

    }


}

package com.example.jfalck.retrofit_tests

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), IOnResultCatched {

    override fun onResultCatched(result: String) {
        if (!TextUtils.isEmpty(result))
            txt_search_result!!.text = result
    }


    private lateinit var txt_search_result: TextView
    private lateinit var searchText: EditText
    private lateinit var searchButton: Button
    private lateinit var resultsList: RecyclerView

    private val mViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        txt_search_result = findViewById(R.id.text_search_result)
        searchText = findViewById(R.id.edit_search_keyword)
        searchButton = findViewById(R.id.begin_search)
        resultsList = findViewById(R.id.result_list)

        searchButton.setOnClickListener {
            mViewModel.onResultCatchedListener = this
            mViewModel.beginSearch(searchText.text.toString(), this)
        }

        resultsList.layoutManager = LinearLayoutManager(this)
        resultsList.adapter = ResultsAdapter(this)
    }



    override fun onPause() {
        super.onPause()
        mViewModel.disposeDisposable()
    }

}
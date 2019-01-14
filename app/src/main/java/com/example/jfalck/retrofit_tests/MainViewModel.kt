package com.example.jfalck.retrofit_tests

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainViewModel: ViewModel() {

    private val wikiApiServe by lazy {
        WikiApiService.create()
    }
    private var disposable: Disposable? = null

    lateinit var onResultCatchedListener: IOnResultCatched

    fun beginSearch(searchString: String, context: Context) {

        disposable =
                wikiApiServe.hitCountCheck("query", "json", "search", searchString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            run {
                                onResultCatchedListener.onResultCatched("${result.query.searchinfo.totalhits} results found")
                            }
                        },
                        { error -> Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show() }
                )
    }

    fun disposeDisposable() {
        disposable?.dispose()
    }


}
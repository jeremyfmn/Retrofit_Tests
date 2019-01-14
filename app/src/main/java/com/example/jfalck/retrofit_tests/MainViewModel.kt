package com.example.jfalck.retrofit_tests

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainViewModel: ViewModel() {

    private val apiService by lazy {
        ApiService.create()
    }

    private val wikipediaApiService by lazy {
        WikiApiService.create()
    }

    private var disposable: Disposable? = null

    lateinit var onResultCatchedListener: IOnResultCatched

    fun beginWikipediaSearch(searchString: String, context: Context) {

        disposable =
                wikipediaApiService.hitCountCheck("query", "json", "search", searchString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            run {
                                onResultCatchedListener.onStringResultCatched("${result.query.searchinfo.totalhits} results found")
                            }
                        },
                        { error -> Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show() }
                )
    }

    fun beginApiSearch(context: Context) {

        disposable =
                apiService.getPosts()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    run {
                                        onResultCatchedListener.onApiObjectCatched(result)
                                    }
                                },
                                { error -> Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show() }
                        )
    }

    fun disposeDisposable() {
        disposable?.dispose()
    }


}
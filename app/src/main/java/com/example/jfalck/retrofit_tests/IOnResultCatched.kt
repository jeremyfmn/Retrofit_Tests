package com.example.jfalck.retrofit_tests

interface IOnResultCatched {

    fun onStringResultCatched(result: String)

    fun onApiObjectCatched(result: List<ApiModel.Post>)
}
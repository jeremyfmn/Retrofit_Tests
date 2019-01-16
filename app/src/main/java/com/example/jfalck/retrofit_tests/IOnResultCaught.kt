package com.example.jfalck.retrofit_tests

import com.example.jfalck.retrofit_tests.models.ApiModel

interface IOnResultCaught {

    fun onStringResultCaught(result: String)

    fun onApiObjectCaught(result: List<ApiModel.Post>)
}
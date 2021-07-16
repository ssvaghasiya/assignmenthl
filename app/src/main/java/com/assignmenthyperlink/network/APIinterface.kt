package com.assignmenthyperlink.network

import com.assignmenthyperlink.ui.home.datamodel.MemeData
import retrofit2.Call
import retrofit2.http.GET


interface APIinterface {
    @GET("get_memes")
    fun getMemeData(): Call<MemeData>
}
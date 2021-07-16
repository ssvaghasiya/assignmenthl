package com.assignmenthyperlink.ui.home.datamodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.assignmenthyperlink.datasource.UserRepository
import com.assignmenthyperlink.network.APIClient
import com.assignmenthyperlink.network.APIinterface

class MemeDataModel {
    fun getMemeData(context: Context): MutableLiveData<MemeData> {
        val apInterface: APIinterface =
            APIClient.newRequestRetrofit(context).create(APIinterface::class.java)
        val sportsRepository = UserRepository(apInterface)
        return sportsRepository.getMemeData()
    }
}
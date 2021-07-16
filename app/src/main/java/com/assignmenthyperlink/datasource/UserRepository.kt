package com.assignmenthyperlink.datasource

import androidx.lifecycle.MutableLiveData
import com.assignmenthyperlink.apputils.Constant
import com.assignmenthyperlink.apputils.Debug
import com.assignmenthyperlink.network.APIinterface
import com.assignmenthyperlink.ui.home.datamodel.MemeData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(apiInterface: APIinterface) {

    private var apiInterface: APIinterface? = apiInterface

    fun getMemeData(): MutableLiveData<MemeData> {
        val data = MutableLiveData<MemeData>()
        val call = apiInterface!!.getMemeData()
        Debug.e("API", call.request().url.encodedPath)
        call.enqueue(object : Callback<MemeData> {
            override fun onFailure(call: Call<MemeData>, t: Throwable) {
                data.value = MemeData(Constant.getFailureCode())
                Debug.e("getMemeData isFailed", t.message)
            }

            override fun onResponse(call: Call<MemeData>, response: Response<MemeData>) {
                val loginStatus = response.code()
                try {
                    if (response.isSuccessful) {
                        val memeData = response.body()
                        memeData!!.statusCode = Constant.RESPONSE_SUCCESS_CODE
                        data.value = memeData
                        Debug.e("getMemeData isSuccessful", Gson().toJson(memeData))
                    } else {
                        try {
                            val inputAsString =
                                response.errorBody()!!.source().inputStream().bufferedReader()
                                    .use { it.readText() }
                            Debug.e("Input", inputAsString)
                            val sb = StringBuilder()
                            sb.append(inputAsString)
                            val memeData = Gson().fromJson<MemeData>(
                                JSONObject(inputAsString).toString(),
                                object : TypeToken<MemeData>() {}.type
                            )
                            memeData.statusCode = Constant.RESPONSE_FAILURE_CODE
                            data.value = memeData
                        } catch (e: Exception) {
                            e.printStackTrace()
                            data.value = MemeData(Constant.RESPONSE_FAILURE_CODE)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    data.value = MemeData(Constant.RESPONSE_FAILURE_CODE)
                }
            }
        })
        return data
    }

}
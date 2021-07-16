package com.assignmenthyperlink.ui.home.viewmodel


import android.app.Application
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.assignmenthyperlink.apputils.Debug
import com.assignmenthyperlink.base.viewmodel.BaseViewModel
import com.assignmenthyperlink.databinding.ActivityHomeBinding
import com.assignmenthyperlink.interfaces.CallbackListener
import com.assignmenthyperlink.ui.home.datamodel.MemeData
import com.assignmenthyperlink.ui.home.datamodel.MemeDataModel
import com.assignmenthyperlink.ui.home.utils.MemeDataAdapter


class HomeViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityHomeBinding
    private lateinit var mContext: Context
    lateinit var memeDataModel: MemeDataModel
    lateinit var memeDataAdapter: MemeDataAdapter


    fun setBinder(binder: ActivityHomeBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        this.binder.viewModel = this
        this.binder.viewClickHandler = ViewClickHandler()
        memeDataModel = MemeDataModel()
        init()
    }

    private fun init() {
        memeDataAdapter = MemeDataAdapter(mContext)
        binder.rvMeme.adapter = memeDataAdapter
        binder.rvMeme.layoutManager = GridLayoutManager(mContext, 2)

        memeDataAdapter.setEventListener(object : MemeDataAdapter.EventListener {
            override fun onItemClick(pos: Int, item: MemeData.Meme) {

            }

        })
        getMemeData()

    }

    fun getMemeData() {
        isInternetAvailable(mContext, object : CallbackListener {
            override fun onSuccess() {
                try {
                    memeDataModel.getMemeData(mContext).observeForever { memeData ->
                        onCallResult(memeData)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onCancel() {
            }

            override fun onRetry() {
                getMemeData()
            }
        })
    }

    private fun onCallResult(memeData: MemeData) = try {
        when (memeData.success) {
            true -> {
                Debug.e("meme", memeData.data?.memes?.size.toString())
                memeDataAdapter.addAll(memeData.data?.memes)
            }
            false -> {
                showToast("Fail")
            }
            else -> {
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    inner class ViewClickHandler {
        fun onCreateTask(view: View) {
            try {
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}


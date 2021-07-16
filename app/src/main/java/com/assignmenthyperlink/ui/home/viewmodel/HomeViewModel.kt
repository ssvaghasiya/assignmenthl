package com.assignmenthyperlink.ui.home.viewmodel


import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.assignmenthyperlink.R
import com.assignmenthyperlink.apputils.Debug
import com.assignmenthyperlink.apputils.Utils
import com.assignmenthyperlink.base.viewmodel.BaseViewModel
import com.assignmenthyperlink.databinding.ActivityHomeBinding
import com.assignmenthyperlink.interfaces.CallbackListener
import com.assignmenthyperlink.interfaces.TopBarClickListener
import com.assignmenthyperlink.ui.createaccount.view.CreateAccountActivity
import com.assignmenthyperlink.ui.home.datamodel.MemeData
import com.assignmenthyperlink.ui.home.datamodel.MemeDataModel
import com.assignmenthyperlink.ui.home.utils.MemeDataAdapter
import com.assignmenthyperlink.ui.task3.view.Task3Activity


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
        this.binder.topbar.topBarClickListener = SlideMenuClickListener()
        this.binder.topbar.isTextEdit = true
        this.binder.topbar.isBackShow = true
        this.binder.topbar.tvEditText.text = "Click Me"
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

    inner class SlideMenuClickListener : TopBarClickListener {
        override fun onTopBarClickListener(view: View?, value: String?) {
            Utils.hideKeyBoard(getContext(), view!!)
            if (value.equals(getLabelText(R.string.back))) {
                try {
                    onBackClicked(view)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            if (value.equals(getLabelText(R.string.click))) {
                try {
                    val i = Intent(mContext, Task3Activity::class.java)
                    mContext.startActivity(i)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        override fun onBackClicked(view: View?) {
            (mContext as Activity).finish()
        }
    }
}


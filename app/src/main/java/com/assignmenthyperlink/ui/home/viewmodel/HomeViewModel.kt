package com.assignmenthyperlink.ui.home.viewmodel


import android.app.Application
import android.content.Context
import android.view.View
import com.assignmenthyperlink.base.viewmodel.BaseViewModel
import com.assignmenthyperlink.databinding.ActivityHomeBinding


class HomeViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityHomeBinding
    private lateinit var mContext: Context

    fun setBinder(binder: ActivityHomeBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        this.binder.viewModel = this
        this.binder.viewClickHandler = ViewClickHandler()
        init()
    }

    private fun init() {

    }

    fun onResume() {

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


package com.assignmenthyperlink.ui.createaccount.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.View
import com.assignmenthyperlink.R
import com.assignmenthyperlink.apputils.Utils
import com.assignmenthyperlink.base.viewmodel.BaseViewModel
import com.assignmenthyperlink.databinding.ActivityCreateAccountBinding
import com.assignmenthyperlink.databinding.ActivityHomeBinding
import com.assignmenthyperlink.interfaces.TopBarClickListener

class CreateAccountViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityCreateAccountBinding
    private lateinit var mContext: Context

    fun setBinder(binder: ActivityCreateAccountBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        this.binder.viewModel = this
        this.binder.viewClickHandler = ViewClickHandler()
        this.binder.topbar.topBarClickListener = SlideMenuClickListener()
        this.binder.topbar.isTextShow = false
        this.binder.topbar.isBackShow = true
        init()
    }

    private fun init() {

    }

    fun onResume() {

    }

    inner class ViewClickHandler {
        fun onRegister(view: View) {
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
        }

        override fun onBackClicked(view: View?) {
            (mContext as Activity).finish()
        }
    }

}


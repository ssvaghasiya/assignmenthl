package com.assignmenthyperlink.ui.login.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import com.assignmenthyperlink.R
import com.assignmenthyperlink.base.viewmodel.BaseViewModel
import com.assignmenthyperlink.databinding.ActivityLoginBinding
import com.assignmenthyperlink.ui.home.view.HomeActivity
import com.assignmenthyperlink.validator.EmailValidator
import com.assignmenthyperlink.validator.PasswordValidator


class LoginViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityLoginBinding
    private lateinit var mContext: Context


    fun setBinder(binder: ActivityLoginBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        this.binder.viewModel = this
        this.binder.viewClickHandler = ViewClickHandler()
        init()
    }

    private fun init() {

    }

    inner class ViewClickHandler {
        fun onSignin(view: View) {
            try {
                val i = Intent(mContext, HomeActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                mContext.startActivity(i)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun onSignup(view: View) {
            try {
//                val i = Intent(mContext, SignupActivity::class.java)
//                mContext.startActivity(i)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun isValidate(): Boolean {
//        val emailValidator = EmailValidator(binder.edtEmail.text.toString().trim())
//        val passwordValidator = PasswordValidator(mContext, binder.edtPassword.text.toString())
//        if (binder.edtEmail.text.toString().trim().isNullOrEmpty()) {
//            showToast((mContext as Activity).getString(R.string.incorrect_email_address_or_password))
//            return false
//        } else if (binder.edtPassword.text.toString()
//                .isNullOrEmpty()
//        ) {
//            showToast((mContext as Activity).getString(R.string.incorrect_email_address_or_password))
//            return false
//        }
        return true
    }


}

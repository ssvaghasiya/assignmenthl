package com.assignmenthyperlink.ui.login.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import com.assignmenthyperlink.R
import com.assignmenthyperlink.apputils.Debug
import com.assignmenthyperlink.base.viewmodel.BaseViewModel
import com.assignmenthyperlink.databinding.ActivityLoginBinding
import com.assignmenthyperlink.ui.createaccount.view.CreateAccountActivity
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
        if (Debug.DEBUG) {
            binder.edtEmail.setText("abc@gmail.com")
            binder.edtPassword.setText("Abc@123")
        }
    }

    inner class ViewClickHandler {
        fun onSignin(view: View) {
            try {
                if (isValidate()) {
                    val i = Intent(mContext, HomeActivity::class.java)
                    i.flags =
                        Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    mContext.startActivity(i)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun onSignup(view: View) {
            try {
                val i = Intent(mContext, CreateAccountActivity::class.java)
                mContext.startActivity(i)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun isValidate(): Boolean {
        val emailValidator = EmailValidator(binder.edtEmail.text.toString().trim())
        val passwordValidator = PasswordValidator(mContext, binder.edtPassword.text.toString())
        if (!emailValidator.isValid()) {
            showToast((mContext as Activity).getString(R.string.incorrect_email_address_or_password))
            return false
        } else if (!passwordValidator.isValid()) {
            showToast((mContext as Activity).getString(R.string.password_error))
            return false
        }
        return true
    }


}


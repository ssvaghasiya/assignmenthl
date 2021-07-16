package com.assignmenthyperlink.ui.login.view

import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.assignmenthyperlink.R
import com.assignmenthyperlink.base.view.BaseActivity
import com.assignmenthyperlink.databinding.ActivityLoginBinding
import com.assignmenthyperlink.ui.login.viewmodel.LoginViewModel

class LoginActivity : BaseActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(activity, R.color.white)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(activity).get(LoginViewModel::class.java)
        viewModel.setBinder(binding)
    }
}
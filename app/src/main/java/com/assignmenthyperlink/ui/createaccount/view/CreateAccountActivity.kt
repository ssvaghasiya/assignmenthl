package com.assignmenthyperlink.ui.createaccount.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.assignmenthyperlink.R
import com.assignmenthyperlink.base.view.BaseActivity
import com.assignmenthyperlink.databinding.ActivityCreateAccountBinding
import com.assignmenthyperlink.databinding.ActivityHomeBinding
import com.assignmenthyperlink.ui.createaccount.viewmodel.CreateAccountViewModel
import com.assignmenthyperlink.ui.home.viewmodel.HomeViewModel

class CreateAccountActivity : BaseActivity() {
    lateinit var binding: ActivityCreateAccountBinding
    lateinit var viewModel: CreateAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(activity, R.color.blue1)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_account)
        viewModel = ViewModelProvider(activity).get(CreateAccountViewModel::class.java)
        viewModel.setBinder(binding)
    }
}
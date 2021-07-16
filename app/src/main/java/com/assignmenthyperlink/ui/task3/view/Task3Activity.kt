package com.assignmenthyperlink.ui.task3.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.assignmenthyperlink.R
import com.assignmenthyperlink.base.view.BaseActivity
import com.assignmenthyperlink.databinding.ActivityHomeBinding
import com.assignmenthyperlink.databinding.ActivityTask3Binding
import com.assignmenthyperlink.ui.home.viewmodel.HomeViewModel
import com.assignmenthyperlink.ui.task3.viewmodel.Task3ViewModel

class Task3Activity : BaseActivity() {

    lateinit var binding: ActivityTask3Binding
    lateinit var viewModel: Task3ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(activity, R.color.blue1)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task3)
        viewModel = ViewModelProvider(activity).get(Task3ViewModel::class.java)
        viewModel.setBinder(binding)
    }
}
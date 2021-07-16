package com.assignmenthyperlink.ui.home.view

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.assignmenthyperlink.R
import com.assignmenthyperlink.apputils.ExitStrategy
import com.assignmenthyperlink.base.view.BaseActivity
import com.assignmenthyperlink.databinding.ActivityHomeBinding
import com.assignmenthyperlink.ui.home.viewmodel.HomeViewModel


class HomeActivity : BaseActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(activity, R.color.blue1)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel = ViewModelProvider(activity).get(HomeViewModel::class.java)
        viewModel.setBinder(binding)
    }

    override fun onBackPressed() {
        try {
            if (ExitStrategy.canExit()) {
                super.onBackPressed()
            } else {
                ExitStrategy.startExitDelay(2000)
                Toast.makeText(this, getString(R.string.exit_msg), Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
    }

}
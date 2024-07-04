package com.zoe.wan.android.example.activity

import android.content.Context
import android.content.Intent
import com.blankj.utilcode.util.SPUtils
import com.youth.banner.util.LogUtils
import com.zoe.wan.android.example.R
import com.zoe.wan.android.example.databinding.ActivityLoginBinding
import com.zoe.wan.base.BaseActivity
import com.zoe.wan.android.example.BR
import com.zoe.wan.android.example.Constants

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    companion object {
        //0:登录 1:注册
        const val Intent_Type_Value = 0
        const val Intent_Type_name = "Intent_Type_name"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getViewModelId(): Int {
        return BR.loginVm
    }

    override fun initViewData() {
        val type = intent.getIntExtra(Intent_Type_name, -1)
        if (type == Intent_Type_Value) {
            //登录
            binding?.inputPasswordTwice?.visibility = android.view.View.GONE
            binding?.loginOrRegisterBtn?.text = "登录"
            binding?.registerBtn?.visibility = android.view.View.VISIBLE
        } else {
            //注册
            binding?.inputPasswordTwice?.visibility = android.view.View.VISIBLE
            binding?.registerBtn?.visibility = android.view.View.GONE
            binding?.loginOrRegisterBtn?.text = "注册"
        }

        binding?.loginOrRegisterBtn?.setOnClickListener {
            if (type == Intent_Type_Value) {
                login()
            } else {
                register()
            }
        }
    }

    fun login() {
        val sp = getSharedPreferences("wanAndroidSP", Context.MODE_PRIVATE)
        viewModel?.login { username ->
            println("登录成功")
            SPUtils.getInstance().put(Constants.SP_USR_NAME, username)
            println("SP存值 ${sp.getString(Constants.SP_USR_NAME, "")}")
//            LogUtils.d("SP存值 ${sp.getString(Constants.SP_USR_NAME, "")}")
            finish()
            startActivity(Intent(this, TabActivity::class.java))
        }
    }

    fun register() {
        val sp = getSharedPreferences("wanAndroidSP", Context.MODE_PRIVATE)
        viewModel?.register { username ->
            println("注册成功")
            SPUtils.getInstance().put(Constants.SP_USR_NAME, username)
            println("SP存值 ${sp.getString(Constants.SP_USR_NAME, "")}")
        }
    }
}
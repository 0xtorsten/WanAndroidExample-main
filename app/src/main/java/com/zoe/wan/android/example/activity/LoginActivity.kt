package com.zoe.wan.android.example.activity

import android.content.Context
import android.content.Intent
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.youth.banner.util.LogUtils
import com.zoe.wan.android.example.R
import com.zoe.wan.android.example.databinding.ActivityLoginBinding
import com.zoe.wan.base.BaseActivity
import com.zoe.wan.android.example.BR
import com.zoe.wan.android.example.Constants

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    companion object {
        //0:登录
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

        //进入注册页面
        binding?.registerBtn?.setOnClickListener {
            startIntent(LoginActivity::class.java, false)
        }
    }

    /**
     * 登录
     */
    fun login() {
        //登录成功后存储用户名 并跳转到主页
        val sp = getSharedPreferences("wanAndroidSP", Context.MODE_PRIVATE)
        viewModel?.login { username ->
//            sp.edit().putString(Constants.SP_USR_NAME, username).apply()
            SPUtils.getInstance().put(Constants.SP_USR_NAME, username)
            ToastUtils.showShort("登录成功,SP：" + SPUtils.getInstance().getString(Constants.SP_USR_NAME))
            startIntent(TabActivity::class.java, false)
        }
    }

    /**
     * 注册
     */
    fun register() {
        //注册成功后跳转到登录页面开始登陆
        viewModel?.register { username ->
            startIntent(LoginActivity::class.java, true)
        }
    }

    fun startIntent(clazz: Class<*>, hasIntent: Boolean) {
        finish()
        val intent = Intent(this, clazz)
        if (hasIntent) {
            intent.putExtra(Intent_Type_name, Intent_Type_Value)
        }
        startActivity(intent)
    }
}
package com.zoe.wan.android.example.fragment.personal

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableField
import com.blankj.utilcode.util.SPUtils
import com.zoe.wan.android.example.Constants
import com.zoe.wan.base.BaseViewModel
import  android.content.ContextWrapper
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.ToastUtils
import com.zoe.wan.android.example.repository.Repository
import kotlinx.coroutines.launch

class FragPersonalModel(application: Application) : BaseViewModel(application) {
    val username = ObservableField<String>()
    val showLogoutBtn = ObservableField<Boolean>()


    init {
        val name = SPUtils.getInstance().getString(Constants.SP_USR_NAME)
        if (name.isNullOrEmpty()) {
            // 未登录
            username.set("未登录")
            showLogoutBtn.set(false)
        } else {
            username.set(name)
            showLogoutBtn.set(true)
        }
    }

    fun logout() {
        viewModelScope.launch {
            val success = Repository.logout()
            if (success) {
                //登出成功 清理缓存 改变界面状态
                SPUtils.getInstance().clear()
                showLogoutBtn.set(false)
                username.set("未登录")
                ToastUtils.showShort("退出登录成功")
            }else{
                ToastUtils.showShort("退出登录失败")
            }
        }
    }

}
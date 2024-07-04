package com.zoe.wan.android.example.activity

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.ToastUtils
import com.zoe.wan.android.example.repository.Repository
import com.zoe.wan.base.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : BaseViewModel(application) {
    val usernameInput = ObservableField<String>()
    val passwordInput = ObservableField<String>()
    val passwordTwiceInput = ObservableField<String>()

    fun login(callback: (username: String) -> Unit) {
        val username = usernameInput.get()
        val password = passwordInput.get()
        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            ToastUtils.showShort("用户名或密码不能为空")
            return
        }
        viewModelScope.launch {
            val data = Repository.login(username, password)
            if(data!=null){
                callback.invoke(data.username?:"")
            }
        }
    }

    fun register(callback: (username: String) -> Unit) {
        val username = usernameInput.get()
        val password = passwordInput.get()
        val passwordTwice = passwordTwiceInput.get()
        if (username.isNullOrEmpty() || password.isNullOrEmpty() || passwordTwice.isNullOrEmpty()) {
            ToastUtils.showShort("输入不能为空")
            return
        }
        if (password != passwordTwice) {
            ToastUtils.showShort("两次输入的密码不一致")
            return
        }
        viewModelScope.launch {
            val data = Repository.register(username, password, passwordTwice)
            if(data!=null){
                callback.invoke(data.username?:"")
            }
        }
    }
}
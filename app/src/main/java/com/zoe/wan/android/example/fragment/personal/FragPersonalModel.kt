package com.zoe.wan.android.example.fragment.personal

import android.app.Application
import androidx.databinding.ObservableField
import com.blankj.utilcode.util.SPUtils
import com.zoe.wan.base.BaseViewModel

class FragPersonalModel(application: Application):BaseViewModel(application) {
    val username = ObservableField<String>()

    init{
        val name = SPUtils.getInstance().getString("username")
        if(name.isNullOrEmpty()){
            username.set("请登录")
        }else{
            username.set(name)
        }
    }
}
package com.zoe.wan.android.example

import android.app.Application
import com.zoe.wan.android.example.repository.Repository

class WanAPP:Application() {
    override fun onCreate() {
        super.onCreate()
        Repository.init(this)
    }
}
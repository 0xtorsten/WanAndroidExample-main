package com.zoe.wan.android.example.repository

import android.content.Context
import android.content.Intent
import com.blankj.utilcode.util.ToastUtils
import com.zoe.wan.android.example.activity.LoginActivity
import com.zoe.wan.android.example.repository.data.HomeBannerData
import com.zoe.wan.android.example.repository.data.HomeListData
import com.zoe.wan.android.example.repository.data.UserData
import com.zoe.wan.android.http.BaseResponse
import com.zoe.wan.android.http.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

object Repository {

    private const val SUCCESS_CODE = 0
    private const val NEED_LOGIN_CODE = -1001
    private var context: WeakReference<Context>? = null

    /**
     * 初始化
     */
    fun init(context: Context) {
        this.context = WeakReference(context)
    }

    /**
     * 获取首页列表数据
     */
    suspend fun getHomeList(pageCount: String): HomeListData? {
        val data: BaseResponse<HomeListData?>? =
            getDefault().homeList(pageCount)
        return responseCall(data)
    }

    /**
     * 获取Banner数据
     */
    suspend fun homeBanner(): HomeBannerData? {
        val data: BaseResponse<HomeBannerData?>? = getDefault().homeBanner()
        return responseCall(data)
    }

    /**
     * 登录
     */
    suspend fun login(username: String, password: String): UserData? {
        val data = getDefault().login(username, password)
        return responseCall(data)
    }

    /**
     * 注册
     */
    suspend fun register(username: String, password: String, repassword: String): UserData? {
        val data = getDefault().register(username, password, repassword)
        return responseCall(data)
    }

    /**
     * 退出登录
     */
    suspend fun logout(): Boolean {
        val data = getDefault().logout()
        return data?.getErrCode() == 0
    }

    /**
     * 1.code = 0 返回业务数据
     * 2.code = -1001 跳转到登录页
     */
    private fun <T> responseCall(response: BaseResponse<T?>?): T? {
        if (response == null) {
            GlobalScope.launch(Dispatchers.Main) {
                ToastUtils.showShort("请求异常！")
            }
            return null
        }
        //请求正常，返回数据
        if (response.getErrCode() == SUCCESS_CODE) {
            return response.getData()
        } else if (response.getErrCode() == NEED_LOGIN_CODE) {
            //需要登陆，跳转到登录页
            context?.get()?.applicationContext?.let {
                val intent = Intent(it, LoginActivity::class.java)
                intent.putExtra(LoginActivity.Intent_Type_name, LoginActivity.Intent_Type_Value)
                it.startActivity(intent)
            }
        } else {
            GlobalScope.launch(Dispatchers.Main) {
                ToastUtils.showShort(response.getErrMsg() ?: "请求异常！")
            }
        }
        return null
    }


    private fun getDefault(): ApiService {
        return RetrofitClient.getInstance().getDefault(ApiService::class.java)
    }
}
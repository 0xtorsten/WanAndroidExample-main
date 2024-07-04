package com.zoe.wan.android.example.repository

import com.zoe.wan.android.example.repository.data.HomeBannerData
import com.zoe.wan.android.example.repository.data.HomeListData
import com.zoe.wan.android.example.repository.data.UserData
import com.zoe.wan.android.http.ApiAddress.Article_List
import com.zoe.wan.android.http.ApiAddress.Home_Banner
import com.zoe.wan.android.http.ApiAddress.Login
import com.zoe.wan.android.http.ApiAddress.Register
import com.zoe.wan.android.http.BaseResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    /**
     * suspend fun
     * 挂起函数, 允许函数在执行过程中暂停，而不阻塞调用线程。
     * 这意味着挂起函数可以在需要的时候释放线程资源，在稍后恢复执行，而不会影响其他代码的并发执行。
     * 挂起函数必须在另一个挂起函数或者协程体内执行。
     */
    /**
     * 获取首页列表数据
     */
    @GET("$Article_List{pageCount}/json")
    suspend fun homeList(@Path("pageCount") pageCount: String): BaseResponse<HomeListData>?

    /**
     * 获取Banner数据
     */
    @GET(Home_Banner)
    suspend fun homeBanner(): BaseResponse<HomeBannerData>?

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST(Login)
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): BaseResponse<UserData?>?

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST(Register)
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): BaseResponse<UserData?>?
}
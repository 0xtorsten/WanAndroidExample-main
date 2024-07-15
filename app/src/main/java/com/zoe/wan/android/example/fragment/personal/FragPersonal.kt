package com.zoe.wan.android.example.fragment.personal

import android.content.Intent
import com.blankj.utilcode.util.SPUtils
import com.zoe.wan.android.example.BR
import com.zoe.wan.android.example.Constants
import com.zoe.wan.android.example.R
import com.zoe.wan.android.example.activity.LoginActivity
import com.zoe.wan.android.example.databinding.FragmentKnowledgeBinding
import com.zoe.wan.android.example.databinding.FragmentPersonalBinding
import com.zoe.wan.android.example.fragment.knowledge.FragKnowledgeModel
import com.zoe.wan.base.BaseFragment

class FragPersonal : BaseFragment<FragmentPersonalBinding, FragPersonalModel>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_personal
    }

    override fun getViewModelId(): Int {
        return BR.personalVm
    }

    override fun initViewData() {
        binding?.personalHead?.setOnClickListener { shouldLogin() }
        binding?.personalUsername?.setOnClickListener { shouldLogin() }
        // 退出登录
        binding?.personalLogout?.setOnClickListener { viewModel?.logout() }
    }

    /**
     * 是否跳转到登录页面
     */
    private fun shouldLogin() {
        if (viewModel?.showLogoutBtn?.get() == true) {
            return
        }
        val intent = Intent(context, LoginActivity::class.java)
        intent.putExtra(LoginActivity.Intent_Type_name, LoginActivity.Intent_Type_Value)
        startActivity(intent)
    }
}
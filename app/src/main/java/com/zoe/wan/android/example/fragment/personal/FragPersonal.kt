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
        val username = SPUtils.getInstance().getString(Constants.SP_USR_NAME)
        if (username.isNullOrEmpty()) {
            binding?.personalTv?.text = "未登录"
        } else {
            binding?.personalTv?.text = "已登录"
        }
        binding?.personalTv?.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            if (username.isNullOrEmpty()) {
                intent.putExtra(LoginActivity.Intent_Type_name, LoginActivity.Intent_Type_Value)
            }
            //intent.putExtra(LoginActivity.Intent_Type_name, LoginActivity.Intent_Type_Value)
            startActivity(intent)
        }
    }
}
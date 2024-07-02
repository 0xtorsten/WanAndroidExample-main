package com.zoe.wan.android.example.fragment.personal

import com.zoe.wan.android.example.BR
import com.zoe.wan.android.example.R
import com.zoe.wan.android.example.databinding.FragmentKnowledgeBinding
import com.zoe.wan.android.example.databinding.FragmentPersonalBinding
import com.zoe.wan.android.example.fragment.knowledge.FragKnowledgeModel
import com.zoe.wan.base.BaseFragment

class FragPersonal: BaseFragment<FragmentPersonalBinding,FragPersonalModel>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_personal
    }

    override fun getViewModelId(): Int {
        return BR.personalVm
    }

    override fun initViewData() {
    }
}
package com.zoe.wan.android.example.fragment.hotkey

import com.zoe.wan.android.example.BR
import com.zoe.wan.android.example.R
import com.zoe.wan.android.example.databinding.FragmentHomeBinding
import com.zoe.wan.android.example.databinding.FragmentHotKeyBinding
import com.zoe.wan.base.BaseFragment

class FragHotKey:BaseFragment<FragmentHotKeyBinding, FragHotKeyViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_hot_key
    }

    override fun getViewModelId(): Int {
        return BR.hotKeyVm
    }

    override fun initViewData() {
    }
}
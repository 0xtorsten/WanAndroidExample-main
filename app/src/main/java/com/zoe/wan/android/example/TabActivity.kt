package com.zoe.wan.android.example

import android.graphics.BitmapFactory
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.zoe.wan.android.example.databinding.ActivityTabBinding
import com.zoe.wan.base.BaseActivity
import com.zoe.wan.android.example.BR
import com.zoe.wan.android.example.fragment.home.FragHome
import com.zoe.wan.android.example.fragment.hotkey.FragHotKey
import com.zoe.wan.android.example.fragment.knowledge.FragKnowledge
import com.zoe.wan.android.example.fragment.personal.FragPersonal
import com.zoe.wan.base.adapter.Pager2Adapter
import com.zoe.wan.base.tab.NavigationBottomBar

class TabActivity : BaseActivity<ActivityTabBinding, TabViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_tab
    }

    override fun getViewModelId(): Int {
        return BR.tabVm
    }

    override fun initViewData() {
        initPages()
        //选中状态图片数组
        val icons = arrayOf(
            BitmapFactory.decodeResource(resources, R.drawable.icon_home_selected),
            BitmapFactory.decodeResource(resources, R.drawable.icon_hot_key_selected),
            BitmapFactory.decodeResource(resources, R.drawable.icon_knowledge_selected),
            BitmapFactory.decodeResource(resources, R.drawable.icon_personal_selected),
        )
        //未选中状态图片数组
        val unSelectIcons = arrayOf(
            BitmapFactory.decodeResource(resources, R.drawable.icon_home_grey),
            BitmapFactory.decodeResource(resources, R.drawable.icon_hot_key_grey),
            BitmapFactory.decodeResource(resources, R.drawable.icon_knowledge_grey),
            BitmapFactory.decodeResource(resources, R.drawable.icon_personal_grey),
        )
        //bottomBar标题数组
        val tableText = arrayOf("首页", "热门", "体系", "个人")

        binding?.tabBottomBar?.setSelectedIcons(icons.toList())
        binding?.tabBottomBar?.setUnselectIcons(unSelectIcons.toList())
        binding?.tabBottomBar?.setTabText(tableText.toList())
        binding?.tabBottomBar?.setupViewpager(binding?.tabViewPager2)
        binding?.tabBottomBar?.start()

        binding?.tabBottomBar?.registerTabClickListener(object :
            NavigationBottomBar.OnBottomTabClickListener {
            override fun tabClick(position: Int) {
                Log.d("Tab切换", "tabClick: $position")
            }
        })

    }

    private fun initPages() {
        val tabpages = mutableListOf<Fragment>()
        tabpages.add(FragHome())
        tabpages.add(FragHotKey())
        tabpages.add(FragKnowledge())
        tabpages.add(FragPersonal())

        val pager2Adapter = Pager2Adapter(this)
        pager2Adapter.setData(tabpages)
        binding?.tabViewPager2?.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        binding?.tabViewPager2?.adapter = pager2Adapter


    }
}

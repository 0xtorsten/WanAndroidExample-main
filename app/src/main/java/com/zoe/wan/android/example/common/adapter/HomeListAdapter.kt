package com.zoe.wan.android.example.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.listener.OnPageChangeListener
import com.zoe.wan.android.example.R
import com.zoe.wan.android.example.databinding.ItemHomeBannerBinding
import com.zoe.wan.android.example.databinding.ItemHomeListBinding
import com.zoe.wan.android.example.repository.data.HomeBannerData
import com.zoe.wan.android.example.repository.data.HomeBannerDataItem
import com.zoe.wan.android.example.repository.data.HomeListItemData

//import com.youth.banner.adapter.BannerAdapter

class HomeListAdapter : RecyclerView.Adapter<ViewHolder>() {
    private var dataList: List<HomeListItemData?> = mutableListOf()
    private var bannerData: HomeBannerData? = null

    companion object {
        private val BannerItemType = 0
        private val NormalItemType = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0)
            BannerItemType
        else
            NormalItemType
    }

    //设置列表数据
    fun setData(list: List<HomeListItemData>?) {
        if (!list.isNullOrEmpty()) {
            dataList = list
            notifyDataSetChanged()
        }
    }

    //设置Banner数据
    fun setBannerData(data: HomeBannerData?) {
        if (!data.isNullOrEmpty()) {
            bannerData = data
            notifyDataSetChanged()
        }
    }

    class HomeBannerViewHolder(binding: ItemHomeBannerBinding) : RecyclerView.ViewHolder(binding.root) {
        var bannerBinding: ItemHomeBannerBinding

        init {
            bannerBinding = binding
        }
    }

    class HomeListViewHolder(binding: ItemHomeListBinding) : RecyclerView.ViewHolder(binding.root) {
        var itemBinding: ItemHomeListBinding

        init {
            itemBinding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == BannerItemType)
            return HomeBannerViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_home_banner,
                    parent,
                    false
                )
            )
        else
            return HomeListViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_home_list,
                    parent,
                    false
                )
            )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is HomeBannerViewHolder) {
            holder.bannerBinding.itemHomeBanner.setAdapter(HomeBannerDataAdapter(bannerData))
                .setIndicator(CircleIndicator(holder.bannerBinding.itemHomeBanner.context)).addOnPageChangeListener(
                    object : OnPageChangeListener {
                        override fun onPageScrolled(
                            position: Int,
                            positionOffset: Float,
                            positionOffsetPixels: Int
                        ) {
                        }

                        override fun onPageSelected(position: Int) {
                        }

                        override fun onPageScrollStateChanged(state: Int) {
                        }
                    }
                ).setOnBannerListener(object: OnBannerListener<HomeBannerDataItem?>{
                    override fun OnBannerClick(data: HomeBannerDataItem?, position: Int) {
//                        Toast.makeText(
//                            holder.bannerBinding.itemHomeBanner.context,
//                            "Banner被点击了",
//                            Toast.LENGTH_LONG
//                        ).show()
                        ToastUtils.showShort("Banner被点击了")
                    }
                })

        } else if (holder is HomeListViewHolder) {
            val item = dataList[position]
            holder.itemBinding.item = item
            if (item != null) {
                if (item.collect) {
                    holder.itemBinding.homeItemCollect.setBackgroundResource(R.drawable.img_collect)
                } else
                    holder.itemBinding.homeItemCollect.setBackgroundResource(R.drawable.img_collect_grey)
            }
        }

    }

    class HomeBannerDataAdapter(bannerData: HomeBannerData?) :
        BannerImageAdapter<HomeBannerDataItem>(bannerData) {
        override fun onBindView(
            holder: BannerImageHolder?,
            data: HomeBannerDataItem?,
            position: Int,
            size: Int
        ) {
            holder?.imageView?.let {
                Glide.with(it).load(data?.imagePath).into(it)
            }
        }
    }
}
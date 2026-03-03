package com.bw.a2307a1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bw.a2307a1.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class BroadcastFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var btnCalendar: ImageButton
    private lateinit var btnScan: ImageButton

    private val tabTitles = listOf("关注", "精选", "附近")
    private val fragments = listOf(FollowFragment(), FeaturedFragment(), NearbyFragment())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_broadcast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 初始化视图
        tabLayout = view.findViewById(R.id.tab_layout)
        viewPager = view.findViewById(R.id.view_pager)
        btnCalendar = view.findViewById(R.id.btn_calendar)
        btnScan = view.findViewById(R.id.btn_scan)

        // 设置 ViewPager2 适配器
        val adapter = ViewPagerAdapter(this, fragments)
        viewPager.adapter = adapter

        // 默认选择第二个 Fragment (精选)
        viewPager.currentItem = 1

        // 连接 TabLayout 和 ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        // 添加 Tab 选中监听器，改变字体大小
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // 选中时字体变大
                tab?.customView?.findViewById<View>(android.R.id.text1)?.scaleX = 1.2f
                tab?.customView?.findViewById<View>(android.R.id.text1)?.scaleY = 1.2f
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // 未选中时恢复原状
                tab?.customView?.findViewById<View>(android.R.id.text1)?.scaleX = 1.0f
                tab?.customView?.findViewById<View>(android.R.id.text1)?.scaleY = 1.0f
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // 重新选中时的处理
            }
        })

        // 日历按钮点击事件
        btnCalendar.setOnClickListener {
            Toast.makeText(requireContext(), "日历功能", Toast.LENGTH_SHORT).show()
        }

        // 扫描按钮点击事件
        btnScan.setOnClickListener {
            Toast.makeText(requireContext(), "扫描功能", Toast.LENGTH_SHORT).show()
        }
    }
}

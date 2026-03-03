package com.bw.a2307a1

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.bw.a2307a1.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    
    private val broadcastFragment = BroadcastFragment()
    private val goodsFragment = GoodsFragment()
    private val messageFragment = MessageFragment()
    private val mineFragment = MineFragment()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        // 设置底部导航栏
        setupBottomNavigation()
    }
    
    private fun setupBottomNavigation() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        
        // 默认加载第一个 Fragment
        loadFragment(broadcastFragment)
        
        bottomNavigation.setOnItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.nav_broadcast -> {
                    loadFragment(broadcastFragment)
                    true
                }
                R.id.nav_goods -> {
                    loadFragment(goodsFragment)
                    true
                }
                R.id.nav_add -> {
                    // 添加按钮点击事件 - 跳转到发布页面
                    val intent = Intent(this@MainActivity, PublishActivity::class.java)
                    startActivity(intent)
                    // 保持之前的选中状态
                    bottomNavigation.menu.findItem(R.id.nav_broadcast)?.isChecked = true
                    false
                }
                R.id.nav_message -> {
                    loadFragment(messageFragment)
                    true
                }
                R.id.nav_mine -> {
                    loadFragment(mineFragment)
                    true
                }
                else -> false
            }
        }
    }
    
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
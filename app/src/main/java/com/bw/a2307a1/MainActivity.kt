package com.bw.a2307a1

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
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
    private lateinit var teenModeDialog: Dialog
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        // 设置底部导航栏
        setupBottomNavigation()
        
        // 检查是否需要显示青少年模式提示
        if (!PreferenceManager.isTeenModeShown(this)) {
            showTeenModeDialog()
        }
    }
    
    // 显示青少年模式提示对话框
    private fun showTeenModeDialog() {
        teenModeDialog = Dialog(this, R.style.DialogTeenMode)
        teenModeDialog.setContentView(R.layout.dialog_teen_mode)
        teenModeDialog.setCancelable(false)
        teenModeDialog.setCanceledOnTouchOutside(false)
        
        val btnIKnow = teenModeDialog.findViewById<Button>(R.id.btn_i_know)
        val tvSetTeenMode = teenModeDialog.findViewById<TextView>(R.id.tv_set_teen_mode)
        
        // 我知道了按钮点击事件
        btnIKnow.setOnClickListener {
            // 保存已显示状态
            PreferenceManager.setTeenModeShown(this, true)
            teenModeDialog.dismiss()
        }
        
        // 设置青少年模式文字点击事件
        tvSetTeenMode.setOnClickListener {
            // 跳转到青少年模式设置页面
            val intent = Intent(this@MainActivity, TeenModeSettingActivity::class.java)
            startActivity(intent)
            PreferenceManager.setTeenModeShown(this, true)
            teenModeDialog.dismiss()
        }
        
        teenModeDialog.show()
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
    
    override fun onDestroy() {
        super.onDestroy()
        if (::teenModeDialog.isInitialized && teenModeDialog.isShowing) {
            teenModeDialog.dismiss()
        }
    }
}
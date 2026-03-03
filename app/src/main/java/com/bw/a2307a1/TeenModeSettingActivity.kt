package com.bw.a2307a1

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bw.a2307a1.R

class TeenModeSettingActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var btnEnableTeenMode: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teen_mode_setting)

        btnBack = findViewById(R.id.btn_back)
        btnEnableTeenMode = findViewById(R.id.btn_enable_teen_mode)

        // 返回按钮点击事件
        btnBack.setOnClickListener {
            finish()
        }

        // 开启青少年模式按钮点击事件
        btnEnableTeenMode.setOnClickListener {
            // 保存开启状态
            PreferenceManager.setTeenModeEnabled(this, true)
            Toast.makeText(this, "青少年模式已开启", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}

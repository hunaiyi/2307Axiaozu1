package com.bw.a2307a1

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bw.a2307a1.R

class PrivacyDetailActivity : AppCompatActivity() {

    private lateinit var tvPrivacyContent: TextView
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_detail)

        tvPrivacyContent = findViewById(R.id.tv_privacy_content)
        btnBack = findViewById(R.id.btn_back)

        // 设置隐私政策内容
        val content = """
            欢迎使用我们的服务！在使用本产品前，请您仔细阅读以下隐私政策：
            
            一、信息收集
            为了向您提供更好的服务，我们可能会收集以下信息：
            1. 设备信息：用于优化应用性能和兼容性
            2. 位置信息：用于提供基于位置的服务
            3. 相机和麦克风权限：用于发布内容和直播功能
            4. 存储权限：用于缓存和上传文件
            
            二、信息使用
            我们收集的信息将用于：
            1. 提供、维护和改进我们的服务
            2. 开发新的功能和服务
            3. 保护用户的安全和权益
            4. 遵守法律法规要求
            
            三、信息保护
            我们采取严格的安全措施保护您的个人信息：
            1. 使用加密技术保护数据传输
            2. 建立数据保护制度规范
            3. 严格控制数据访问权限
            
            四、用户权利
            您对自己的个人信息享有以下权利：
            1. 访问和更正您的个人信息
            2. 删除您的个人信息
            3. 撤回授权同意
            4. 注销账户
            
            五、政策更新
            我们可能会不时更新本隐私政策，更新后的政策将在应用内公布。
            
            如您有任何疑问，请联系我们的客服团队。
        """.trimIndent()

        tvPrivacyContent.text = content

        btnBack.setOnClickListener {
            finish()
        }
    }
}

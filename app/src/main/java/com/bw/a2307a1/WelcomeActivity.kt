package com.bw.a2307a1

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WelcomeActivity : AppCompatActivity() {
    
    private var isAgreed = false
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var privacyDialog: Dialog
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        
        // 检查是否已同意隐私协议
        if (PreferenceManager.isPrivacyAgreed(this)) {
            // 已同意，直接跳转
            startCountdown()
        } else {
            // 首次或未同意，显示隐私协议
            showPrivacyAgreementDialog()
        }
    }
    
    // 显示隐私协议对话框
    private fun showPrivacyAgreementDialog() {
        privacyDialog = Dialog(this, R.style.DialogPrivacy)
        privacyDialog.setContentView(R.layout.dialog_privacy_agreement)
        privacyDialog.setCancelable(false)
        privacyDialog.setCanceledOnTouchOutside(false)
        
        val tvAgreementContent = privacyDialog.findViewById<TextView>(R.id.tv_agreement_content)
        val btnDisagree = privacyDialog.findViewById<Button>(R.id.btn_disagree)
        val btnAgree = privacyDialog.findViewById<Button>(R.id.btn_agree)
        
        // 设置协议内容，支持富文本点击
        val agreementText = """1. 为了向您提供直播服务、精选购物等功能，我们需要您在个人信息、您可以在线相关页面阅读，更正您的个人信息。

2. 基于您的授权同意，我们可能会收集您的相机权限（发布商品或开播）、麦克风权限（发布商品或直播），存储权限（缓存文件、上传文件等）及位置权限等，您需要授权我们收集上述信息。

3. 请您阅读完整的《隐私政策》，了解我们如何收集、使用和保护您的信息。""".trimIndent()
        
        val spannableString = SpannableStringBuilder(agreementText)
        
        // 添加《隐私政策》可点击链接
        val privacyPolicyText = "《隐私政策》"
        val startIndex = agreementText.indexOf(privacyPolicyText)
        if (startIndex != -1) {
            val endIndex = startIndex + privacyPolicyText.length
            spannableString.setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    // 跳转到隐私政策详情页
                    val intent = Intent(this@WelcomeActivity, PrivacyDetailActivity::class.java)
                    startActivity(intent)
                }
            }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        
        tvAgreementContent.text = spannableString
        tvAgreementContent.movementMethod = LinkMovementMethod.getInstance()
        
        // 不同意按钮点击事件
        btnDisagree.setOnClickListener {
            finish() // 退出应用
        }
        
        // 同意按钮点击事件
        btnAgree.setOnClickListener {
            isAgreed = true
            // 保存同意状态
            PreferenceManager.setPrivacyAgreed(this, true)
            PreferenceManager.setFirstLaunch(this, false)
            // 隐藏对话框
            privacyDialog.dismiss()
            // 开始倒计时跳转
            startCountdown()
        }
        
        privacyDialog.show()
    }
    
    // 开始倒计时跳转
    private fun startCountdown() {
        var countdown = 5 // 5 秒倒计时
        
        // 这里可以添加倒计时 UI 显示
        // 例如更新某个 TextView 显示 "5 秒后自动跳转..."
        
        handler.postDelayed({
            if (!isFinishing) {
                val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 5000) // 5 秒延迟
    }
    
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
        if (::privacyDialog.isInitialized && privacyDialog.isShowing) {
            privacyDialog.dismiss()
        }
    }
}
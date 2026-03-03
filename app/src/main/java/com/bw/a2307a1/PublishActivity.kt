package com.bw.a2307a1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PublishActivity : AppCompatActivity() {

    private lateinit var etContent: EditText
    private lateinit var etHashtag: EditText
    private lateinit var btnPublish: Button
    private lateinit var btnCancel: ImageButton
    private lateinit var btnAddImage: Button
    private lateinit var btnAddVideo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish)

        // 初始化视图
        etContent = findViewById(R.id.et_content)
        etHashtag = findViewById(R.id.et_hashtag)
        btnPublish = findViewById(R.id.btn_publish)
        btnCancel = findViewById(R.id.btn_cancel)
        btnAddImage = findViewById(R.id.btn_add_image)
        btnAddVideo = findViewById(R.id.btn_add_video)

        // 发布按钮点击事件
        btnPublish.setOnClickListener {
            publishContent()
        }

        // 取消按钮点击事件
        btnCancel.setOnClickListener {
            finish()
        }

        // 添加图片按钮点击事件
        btnAddImage.setOnClickListener {
            Toast.makeText(this, "选择图片功能", Toast.LENGTH_SHORT).show()
        }

        // 添加视频按钮点击事件
        btnAddVideo.setOnClickListener {
            Toast.makeText(this, "拍摄视频功能", Toast.LENGTH_SHORT).show()
        }
    }

    private fun publishContent() {
        val content = etContent.text.toString().trim()
        val hashtag = etHashtag.text.toString().trim()

        if (content.isEmpty()) {
            Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show()
            return
        }

        // 模拟发布操作
        Toast.makeText(
            this,
            "发布成功！\n内容：$content\n话题：$hashtag",
            Toast.LENGTH_LONG
        ).show()

        // 关闭页面
        finish()
    }
}

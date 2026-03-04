package com.bw.a2307a1.mvvm.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * 带 ViewBinding 的 Activity 基类（可选）
 * 子类指定 VB 类型并实现 binding 即可
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = createBinding()
        setContentView(binding.root)
        initView()
        observeData()
    }

    protected abstract fun createBinding(): VB
    protected open fun initView() {}
    protected open fun observeData() {}

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}

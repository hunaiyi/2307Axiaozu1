package com.bw.a2307a1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bw.a2307a1.R

class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 添加按钮 Fragment 不显示实际内容，点击时触发操作
        Toast.makeText(requireContext(), "点击了添加按钮", Toast.LENGTH_SHORT).show()
        return null
    }
}

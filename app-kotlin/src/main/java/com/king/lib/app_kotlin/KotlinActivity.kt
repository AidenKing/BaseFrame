package com.king.lib.app_kotlin

import android.os.Bundle
import com.king.lib.app_kotlin.databinding.ActivityKotlinBinding
import com.king.lib.frame_kotlin.mvvm.EmptyViewModel
import com.king.lib.frame_kotlin.mvvm.MvvmActivity

/**
 * Desc:
 * @author：Jing Yang
 * @date: 2020/7/7 14:23
 */
class KotlinActivity:MvvmActivity<ActivityKotlinBinding, EmptyViewModel>() {
    override fun getContentView(): Int = R.layout.activity_kotlin

    override fun createViewModel(): EmptyViewModel = generateViewModel(EmptyViewModel::class.java)

    override fun onCreatePage(savedInstanceState: Bundle?) {

    }

}
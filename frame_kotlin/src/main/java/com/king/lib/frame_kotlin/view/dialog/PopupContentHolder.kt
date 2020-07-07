package com.king.lib.frame_kotlin.view.dialog

import com.king.lib.frame_kotlin.IFragmentHolder

/**
 * @desc
 * @auth 景阳
 * @time 2018/3/24 0024 23:18
 */
interface PopupContentHolder : IFragmentHolder {
    fun dismiss()
    fun dismissAllowingStateLoss()
}
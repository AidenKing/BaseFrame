package com.king.lib.frame_kotlin.view.dialog

import androidx.databinding.ViewDataBinding
import com.king.lib.frame_kotlin.mvvm.BaseViewModel
import com.king.lib.frame_kotlin.mvvm.MvvmFragment

/**
 * Desc:
 *
 * @author：Jing Yang
 * @date: 2018/6/7 10:05
 */
abstract class PopupContent<T : ViewDataBinding, VM : BaseViewModel> :
    MvvmFragment<T, VM>() {
    private var dialogHolder: PopupContentHolder? = null
    fun setDialogHolder(dialogHolder: PopupContentHolder?) {
        this.dialogHolder = dialogHolder
    }

    protected fun dismiss() {
        dialogHolder!!.dismiss()
    }

    protected fun dismissAllowingStateLoss() {
        dialogHolder!!.dismissAllowingStateLoss()
    }

}
package com.king.lib.frame_kotlin.mvvm

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProviders
import com.king.lib.frame_kotlin.R

/**
 * 描述:DialogFragment基类
 *
 * <br></br>创建时间: 2020/1/21
 */
abstract class MvvmDialogFragment<T : ViewDataBinding, VM: BaseViewModel> : DialogFragment() {

    lateinit var mBinding: T

    lateinit var mModel: VM

    private var windowParams: WindowManager.LayoutParams? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BaseDialogFragment)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        windowParams = dialog!!.window!!.attributes
        mBinding = getBinding(inflater)
        mModel = createViewModel()
        val view = mBinding.root
        onCreate(view)
        return mBinding.root
    }

    protected abstract fun getBinding(inflater: LayoutInflater): T

    protected abstract fun createViewModel(): VM

    fun generateViewModel(vm: Class<VM>) = ViewModelProviders.of(this).get(vm)

    fun <AVM: AndroidViewModel> getActivityViewModel(vm: Class<AVM>): AVM = ViewModelProviders.of(activity!!).get(vm)

    protected abstract fun onCreate(view: View)

    fun setWidth(width: Int) {
        windowParams!!.width = width
        dialog!!.window!!.attributes = windowParams
    }

    fun setHeight(height: Int) {
        windowParams!!.height = height
        dialog!!.window!!.attributes = windowParams
    }

    /**
     * 设置dialog的偏移位置
     *
     * @param x 负数向左，正数向右
     * @param y 负数向上，正数向下
     */
    fun setPositionOffset(x: Int, y: Int) {

        windowParams!!.x = x
        windowParams!!.y = y
        dialog!!.window!!.attributes = windowParams
    }

    /**
     * move dialog
     *
     * @param x
     * @param y
     */
    protected fun move(x: Int, y: Int) {

        windowParams!!.x += x
        windowParams!!.y += y
        dialog!!.window!!.attributes = windowParams//must have
    }

}

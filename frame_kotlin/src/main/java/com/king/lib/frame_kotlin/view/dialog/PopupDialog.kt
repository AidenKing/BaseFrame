package com.king.lib.frame_kotlin.view.dialog

import android.content.DialogInterface
import android.graphics.Point
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import com.king.lib.frame_kotlin.R
import com.king.lib.frame_kotlin.bingding.BindingDialogFragment
import com.king.lib.frame_kotlin.databinding.DialogBaseBinding
import com.king.lib.frame_utils.DebugLog
import com.king.lib.frame_utils.ScreenUtils

/**
 * Desc:
 * @author：Jing Yang
 * @date: 2020/4/17 10:39
 */
class PopupDialog : BindingDialogFragment<DialogBaseBinding>(), PopupContentHolder {

    var title: String? = ""

    var backgroundColor = 0

    var hideClose = false

    var showDelete = false

    var onDeleteListener: View.OnClickListener? = null

    var content: PopupContent<*, *>? = null

    var maxDialogHeight = 0

    var forceHeight = 0

    var onDismissListener: DialogInterface.OnDismissListener? = null
    
    override fun getBinding(inflater: LayoutInflater): DialogBaseBinding = DialogBaseBinding.inflate(inflater)
    
    override fun onCreate(view: View) {
        if (title != null) {
            mBinding.tvTitle.text = title;
        }
        if (backgroundColor != 0) {
            var drawable = mBinding.groupDialog.background as GradientDrawable;
            drawable.setColor(backgroundColor);
        }
        if (hideClose) {
            mBinding.ivClose.visibility = View.GONE;
        }
        if (showDelete) {
            mBinding.ivDelete.visibility = View.VISIBLE;
            mBinding.ivDelete.setOnClickListener(onDeleteListener);
        }

        initDragParams();

        if (content != null) {
            content!!.setDialogHolder(this);
            replaceContentFragment(content, "ContentView");
        }

        mBinding.groupFtContainer.post{
            DebugLog.e("groupFtContent height=" + mBinding.groupFtContainer.height);
            limitMaxHeight();
        };

        mBinding.ivClose.setOnClickListener { dismissAllowingStateLoss() };
    }

    private fun replaceContentFragment(
        target: PopupContent<*, *>?,
        tag: String?
    ) {
        if (target != null) {
            val ft =
                childFragmentManager.beginTransaction()
            ft.replace(R.id.group_ft_container, target, tag)
            ft.commit()
        }
    }

    private fun initDragParams() {
        mBinding.groupDialog.setOnTouchListener(DialogTouchListener())
    }

    private fun limitMaxHeight() {
        if (forceHeight > 0) {
            val params = mBinding.groupFtContainer.layoutParams
            params.height = forceHeight
            mBinding.groupFtContainer.layoutParams = params
        }
        else{
            val maxContentHeight: Int = getMaxHeight()
            if (mBinding.groupFtContainer.height > maxContentHeight) {
                val params = mBinding.groupFtContainer.layoutParams
                params.height = maxContentHeight
                mBinding.groupFtContainer.layoutParams = params
            }
        }
    }

    /**
     * 子类可选择覆盖
     * @return
     */
    open fun getMaxHeight(): Int {
        return if (maxDialogHeight != 0) {
            maxDialogHeight
        } else {
            ScreenUtils.getScreenHeight(context) * 3 / 5
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissListener?.onDismiss(dialog)
    }

    /**
     * move dialog
     */
    inner class DialogTouchListener : OnTouchListener {

        var startPoint = Point()
        var touchPoint = Point()

        override fun onTouch(v: View, event: MotionEvent): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    val x = event.rawX //
                    val y = event.rawY
                    startPoint.x = x.toInt()
                    startPoint.y = y.toInt()
                    DebugLog.d("ACTION_DOWN x=$x, y=$y")
                }
                MotionEvent.ACTION_MOVE -> {
                    val x = event.rawX
                    val y = event.rawY
                    touchPoint.x = x.toInt()
                    touchPoint.y = y.toInt()
                    val dx = touchPoint.x - startPoint.x
                    val dy = touchPoint.y - startPoint.y
                    move(dx.toInt(), dy.toInt())
                    startPoint.x = x.toInt()
                    startPoint.y = y.toInt()
                }
                MotionEvent.ACTION_UP -> {
                }
                else -> {
                }
            }
            return true
        }
    }
}
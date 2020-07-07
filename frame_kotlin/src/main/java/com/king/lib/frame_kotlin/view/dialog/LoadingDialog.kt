package com.king.lib.frame_kotlin.view.dialog

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.king.lib.frame_kotlin.R

/**
 * 描述:
 *
 * 作者：景阳
 *
 * 创建时间: 2020/1/21 13:37
 */
class LoadingDialog : DialogFragment() {

    private var message: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        isCancelable = true
        setStyle(STYLE_NORMAL, R.style.FrameLoadingDialog)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frame_dialog_loading, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog!!.window!!.setGravity(Gravity.CENTER)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        val ft = manager.beginTransaction()
        if (isAdded) {
            ft.show(this)
        } else {
            ft.add(this, tag)
        }
        ft.commitAllowingStateLoss()
    }
}

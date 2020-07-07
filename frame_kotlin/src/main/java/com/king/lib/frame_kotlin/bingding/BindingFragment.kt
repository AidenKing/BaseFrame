package com.king.lib.frame_kotlin.bingding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Desc:
 * @author：Jing Yang
 * @date: 2020/1/22 14:22
 */
abstract class BindingFragment<T : ViewDataBinding>: Fragment() {

    lateinit var mBinding: T

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = getBinding(inflater)
        val view = mBinding.root
        onCreate(view)
        return mBinding.root
    }

    protected abstract fun getBinding(inflater: LayoutInflater): T

    abstract fun onCreate(view: View)

}
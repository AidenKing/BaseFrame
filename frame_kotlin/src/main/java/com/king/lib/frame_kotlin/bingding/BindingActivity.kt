package com.king.lib.frame_kotlin.bingding

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.king.lib.frame_kotlin.BaseCompactActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * 描述:
 *
 * 作者：景阳
 *
 * 创建时间: 2020/1/21 15:50
 */
abstract class BindingActivity<T : ViewDataBinding> : BaseCompactActivity() {

    lateinit var mBinding: T

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, getContentView())

        onCreatePage(savedInstanceState)
    }

    abstract fun getContentView(): Int

    abstract fun onCreatePage(savedInstanceState: Bundle?)

}

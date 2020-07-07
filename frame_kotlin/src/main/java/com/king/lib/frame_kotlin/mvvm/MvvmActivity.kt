package com.king.lib.frame_kotlin.mvvm

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
abstract class MvvmActivity<T : ViewDataBinding, VM : BaseViewModel> : BaseCompactActivity() {

    lateinit var mBinding: T

    lateinit var mModel: VM

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, getContentView())
        mModel = createViewModel()
        if (mModel != null) {
            mModel!!.loadingObserver.observe(this, Observer { show ->
                if (show) {
                    showProgress()
                } else {
                    dismissProgress()
                }
            })
//            mModel!!.messageObserver.observe(this, Observer { message -> showMessageShort(message) })
        }

        onCreatePage(savedInstanceState)
    }

    abstract fun getContentView(): Int

    protected abstract fun createViewModel(): VM

    fun generateViewModel(vm: Class<VM>) = ViewModelProviders.of(this).get(vm)

    abstract fun onCreatePage(savedInstanceState: Bundle?)

    override fun onDestroy() {
        mModel?.onDestroy()
        super.onDestroy()
    }
}

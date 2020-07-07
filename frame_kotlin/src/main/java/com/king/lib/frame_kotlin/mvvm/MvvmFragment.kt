package com.king.lib.frame_kotlin.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProviders
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Desc:
 * @author：Jing Yang
 * @date: 2020/1/22 14:22
 */
abstract class MvvmFragment<T : ViewDataBinding, VM: BaseViewModel>: Fragment() {

    lateinit var mBinding: T

    lateinit var mModel: VM

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
        mModel = createViewModel()
        if (mModel != null) {
//            mModel!!.loadingObserver.observe(this, Observer { show ->
//                if (show) {
//                    showProgress("loading...")
//                } else {
//                    dismissProgress()
//                }
//            })
//            mModel!!.messageObserver.observe(this, Observer { message -> showMessageShort(message) })
        }
        val view = mBinding.root
        onCreate(view)
        return mBinding.root
    }

    protected abstract fun getBinding(inflater: LayoutInflater): T

    protected abstract fun createViewModel(): VM

    fun generateViewModel(vm: Class<VM>) = ViewModelProviders.of(this).get(vm)

    fun <AVM: AndroidViewModel> getActivityViewModel(vm: Class<AVM>): AVM = ViewModelProviders.of(activity!!).get(vm)

    abstract fun onCreate(view: View)

}
package com.king.lib.frame.mvvm;

import android.os.Bundle;

import com.king.lib.frame.BaseCompactActivity;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

/**
 * Desc:
 *
 * @author：Jing Yang
 * @date: 2020/7/3 10:27
 */
public abstract class MvvmActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseCompactActivity {

    protected V mBinding;

    protected VM mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getContentView());
        mModel = createViewModel();
        if (mModel != null) {
            mModel.loadingObserver.observe(this, show -> {
                showLoading(show);
            });
//            mModel.messageObserver.observe(this, message -> showMessageLong(message));
        }
        onCreatePage(savedInstanceState);
    }

    protected void showLoading(Boolean show) {
        if (show) {
            showProgress();
        }
        else {
            dismissProgress();
        }
    }

    protected abstract int getContentView();

    protected abstract VM createViewModel();

    protected VM generateViewModel(Class<VM> vm) {
        return ViewModelProviders.of(this).get(vm);
    }

    protected abstract void onCreatePage(Bundle savedInstanceState);

}

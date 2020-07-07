package com.king.lib.frame.mvvm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProviders;

/**
 * Desc:
 *
 * @author：Jing Yang
 * @date: 2020/7/3 10:33
 */
public abstract class MvvmFragment <V extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {

    protected V mBinding;

    protected VM mModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = getBinding(inflater);
        mModel = createViewModel();

        View view = mBinding.getRoot();
        onCreate(view);
        return view;
    }

    protected abstract V getBinding(LayoutInflater inflater);

    protected abstract VM createViewModel();

    protected VM generateViewModel(Class<VM> vm) {
        return ViewModelProviders.of(this).get(vm);
    }

    protected <AVM extends AndroidViewModel> AVM getActivityViewModel(Class<AVM> vm) {
        return ViewModelProviders.of(getActivity()).get(vm);
    }

    protected abstract void onCreate(View view);

}

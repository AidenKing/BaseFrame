package com.king.lib.frame.binding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

/**
 * Desc:
 *
 * @author：Jing Yang
 * @date: 2020/7/3 10:33
 */
public abstract class BindingFragment<V extends ViewDataBinding> extends Fragment {

    protected V mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = getBinding(inflater);

        View view = mBinding.getRoot();
        onCreate(view);
        return view;
    }

    protected abstract V getBinding(LayoutInflater inflater);

    protected abstract void onCreate(View view);

}

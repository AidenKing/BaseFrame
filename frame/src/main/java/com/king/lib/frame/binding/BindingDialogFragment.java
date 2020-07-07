package com.king.lib.frame.binding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;

/**
 * Desc:
 *
 * @author：Jing Yang
 * @date: 2020/7/3 10:40
 */
public abstract class BindingDialogFragment<V extends ViewDataBinding> extends DialogFragment {

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

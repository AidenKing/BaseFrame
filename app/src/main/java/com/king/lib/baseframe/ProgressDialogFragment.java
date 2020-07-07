package com.king.lib.baseframe;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.king.lib.baseframe.databinding.DialogLoadingBinding;
import com.king.lib.frame.annotations.AnnotationConstants;
import com.king.lib.frame.binding.BindingDialogFragment;
import com.king.lib.frame_annotations.Loading;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Desc:
 *
 * @author：Jing Yang
 * @date: 2020/7/3 11:20
 */
@Loading(AnnotationConstants.DEFAULT)
public class ProgressDialogFragment extends BindingDialogFragment<DialogLoadingBinding> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setCancelable(true);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.LoadingDialog);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected DialogLoadingBinding getBinding(LayoutInflater inflater) {
        return DialogLoadingBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(View view) {
        mBinding.tvMessage.setText("Loading...");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setGravity(Gravity.CENTER);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        if (isAdded()) {
            ft.show(this);
        } else {
            ft.add(this, tag);
        }
        ft.commitAllowingStateLoss();
    }
}

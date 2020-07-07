package com.king.lib.frame.view;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.king.lib.frame.R;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


/**
 * 描述:loading对话框
 * <p/>作者：景阳
 * <br/>创建时间: 2017/12/24
 */

public class LoadingDialog extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setCancelable(false);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FrameLoadingDialog);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_dialog_loading, null);
        return view;
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
        }
        else {
            ft.add(this, tag);
        }
        ft.commitAllowingStateLoss();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}

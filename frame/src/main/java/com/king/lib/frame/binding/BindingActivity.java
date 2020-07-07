package com.king.lib.frame.binding;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * Desc:
 *
 * @author：Jing Yang
 * @date: 2020/7/3 10:47
 */
public abstract class BindingActivity<V extends ViewDataBinding> extends AppCompatActivity {

    protected V mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getContentView());
        onCreatePage(savedInstanceState);
    }

    protected abstract int getContentView();

    protected abstract void onCreatePage(Bundle savedInstanceState);

}

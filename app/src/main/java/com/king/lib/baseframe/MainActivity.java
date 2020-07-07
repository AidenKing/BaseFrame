package com.king.lib.baseframe;

import android.os.Bundle;

import com.king.lib.baseframe.databinding.ActivityMainBinding;
import com.king.lib.frame.CustomProvider;
import com.king.lib.frame.annotations.LoadingFactory;
import com.king.lib.frame.mvvm.EmptyViewModel;
import com.king.lib.frame.mvvm.MvvmActivity;

public class MainActivity extends MvvmActivity<ActivityMainBinding, EmptyViewModel> {

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected EmptyViewModel createViewModel() {
        return generateViewModel(EmptyViewModel.class);
    }

    @Override
    protected void onCreatePage(Bundle savedInstanceState) {
        LoadingFactory.create(new CustomProvider());
        mBinding.btnShow.setOnClickListener(e -> showProgress());
    }

    @Override
    protected String getLoadingIdentity() {
        return "Loading1";
    }
}
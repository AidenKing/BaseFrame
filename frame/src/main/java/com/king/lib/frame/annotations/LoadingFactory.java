package com.king.lib.frame.annotations;

import com.king.lib.frame.view.LoadingDialog;

import androidx.fragment.app.DialogFragment;

/**
 * Desc:
 *
 * @author：Jing Yang
 * @date: 2020/7/3 11:20
 */
public class LoadingFactory {

    private static UiProvider uiProvider;

    public static void create(UiProvider factory) {
        uiProvider = factory;
    }

    public static DialogFragment createLoading(String identity) {
        if (uiProvider == null) {
            return new LoadingDialog();
        }
        else {
            return uiProvider.createLoading(identity);
        }
    }
}

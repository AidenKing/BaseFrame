package com.king.lib.frame.annotations;

import androidx.fragment.app.DialogFragment;

/**
 * Desc:
 *
 * @author：Jing Yang
 * @date: 2020/7/3 11:27
 */
public abstract class UiProvider {
    public abstract DialogFragment createLoading(String identity);
}

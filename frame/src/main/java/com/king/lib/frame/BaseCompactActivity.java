package com.king.lib.frame;

import android.text.TextUtils;

import com.king.lib.frame.annotations.AnnotationConstants;
import com.king.lib.frame.annotations.LoadingFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

/**
 * Desc:
 *
 * @author：Jing Yang
 * @date: 2020/7/3 11:17
 */
public class BaseCompactActivity extends AppCompatActivity {

    private DialogFragment loadingDialog;

    public void showProgress() {
        if (loadingDialog == null) {
            loadingDialog = LoadingFactory.createLoading(getLoadingIdentity());
        }
        if (loadingDialog != null) {
            loadingDialog.show(getSupportFragmentManager(), "loadingDialog");
        }
    }

    protected String getLoadingIdentity() {
        return AnnotationConstants.DEFAULT;
    }

    public void dismissProgress() {
        if (loadingDialog != null) {
            loadingDialog.dismissAllowingStateLoss();
        }
    }

}

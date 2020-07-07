package com.king.lib.frame_kotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Desc:
 * @author：Jing Yang
 * @date: 2020/7/7 13:23
 */
abstract class BaseCompactActivity: AppCompatActivity() {

    companion object {
        val KEY_BUNDLE = "bundle"
    }

    fun showProgress() {

    }

    fun dismissProgress() {

    }

    open fun<AC> startPage(target: Class<AC>) {
        startActivity(Intent().setClass(this, target))
    }

    open fun<AC> startPage(target: Class<AC>, bundle: Bundle) {
        var intent = Intent().setClass(this, target)
        intent.putExtra(KEY_BUNDLE, bundle)
        startActivity(intent)
    }

    open fun<AC> startPageForResult(target: Class<AC>, bundle: Bundle, requestCode: Int) {
        var intent = Intent().setClass(this, target)
        intent.putExtra(KEY_BUNDLE, bundle)
        startActivityForResult(intent, requestCode)
    }

    open fun getIntentBundle(): Bundle? {
        return getIntentBundle(intent)
    }

    open fun getIntentBundle(intent: Intent): Bundle? {
        return intent.getBundleExtra(KEY_BUNDLE)
    }
}
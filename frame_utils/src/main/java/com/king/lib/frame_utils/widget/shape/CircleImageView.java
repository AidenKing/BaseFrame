package com.king.lib.frame_utils.widget.shape;

import android.content.Context;
import android.util.AttributeSet;

import com.king.lib.frame_utils.widget.shape.shader.CircleShader;

/**
 * Desc:
 *
 * @author：Jing Yang
 * @date: 2020/7/9 9:49
 */
public class CircleImageView extends BaseShaderImageView {

    private CircleShader shader;

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public ShaderHelper createImageViewHelper() {
        shader = new CircleShader();
        return shader;
    }

    public float getBorderRadius() {
        if(shader != null) {
            return shader.getBorderRadius();
        }
        return 0;
    }

    public void setBorderRadius(final float borderRadius) {
        if(shader != null) {
            shader.setBorderRadius(borderRadius);
            invalidate();
        }
    }
}

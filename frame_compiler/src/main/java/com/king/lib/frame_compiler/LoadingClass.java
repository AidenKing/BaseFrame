package com.king.lib.frame_compiler;

import com.squareup.javapoet.ClassName;

/**
 * Desc:
 *
 * @author：Jing Yang
 * @date: 2020/7/3 16:52
 */
public class LoadingClass {

    private ClassName className;

    private String identity;

    public LoadingClass(ClassName className, String identity) {
        this.className = className;
        this.identity = identity;
    }

    public ClassName getClassName() {
        return className;
    }

    public void setClassName(ClassName className) {
        this.className = className;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}

package com.yinge.opengl.egl;

public class EGLRender {
    private EGLHelper mEGLHelper;


    public EGLRender(int width, int height) {
        mEGLHelper = new EGLHelper(width, height);
    }

    public void destroy() {
        mEGLHelper.destroyEglContext();
    }

}

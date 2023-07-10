package com.yinge.opengl;

import android.app.Application;

public class OpenGlApplication extends Application {

    private static OpenGlApplication app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static OpenGlApplication getInstance() {
        return app;
    }
}

package com.example.qtiegl14;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EGLDisplay eglDisplay;
    private EGLConfig eglConfig;
    private EGLContext eglContext;
    private EGLSurface eglSurface;
    private Button init;
    private Button release;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init = findViewById(R.id.init);
        release = findViewById(R.id.release);

        init.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
                Toast.makeText(MainActivity.this, "init", Toast.LENGTH_SHORT).show();
            }
        });

        release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                release();
                Toast.makeText(MainActivity.this, "Release", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //  EGL initial
    private void init() {
        eglDisplay = EGL14.eglGetDisplay(EGL14.EGL_DEFAULT_DISPLAY);
        int[] version = new int[2];
        EGL14.eglInitialize(eglDisplay, version, 0, version, 1);

        int[] configAttributes = {
                EGL14.EGL_RENDERABLE_TYPE, EGL14.EGL_OPENGL_ES2_BIT,
                EGL14.EGL_RED_SIZE, 8,
                EGL14.EGL_GREEN_SIZE, 8,
                EGL14.EGL_BLUE_SIZE, 8,
                EGL14.EGL_ALPHA_SIZE, 8,
                EGL14.EGL_NONE
        };
        EGLConfig[] configs = new EGLConfig[1];
        int[] numConfigs = new int[1];
        EGL14.eglChooseConfig(eglDisplay, configAttributes, 0, configs, 0, 1, numConfigs, 0);
        eglConfig = configs[0];

        int[] contextAttributes = {
                EGL14.EGL_CONTEXT_CLIENT_VERSION, 2,
                EGL14.EGL_NONE
        };

        int[] surfaceAttributes = {
                EGL14.EGL_WIDTH, 1,
                EGL14.EGL_HEIGHT, 1,
                EGL14.EGL_NONE
        };

        eglContext = EGL14.eglCreateContext(eglDisplay, eglConfig, EGL14.EGL_NO_CONTEXT, contextAttributes, 0);

        eglSurface = EGL14.eglCreatePbufferSurface(eglDisplay, eglConfig, surfaceAttributes, 0);

        EGL14.eglMakeCurrent(eglDisplay, eglSurface, eglSurface, eglContext);
    }

    // EGL destroy
    private void release() {
        EGL14.eglMakeCurrent(eglDisplay, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
        EGL14.eglDestroySurface(eglDisplay, eglSurface);
        EGL14.eglDestroyContext(eglDisplay, eglContext);
        EGL14.eglTerminate(eglDisplay);
    }
}

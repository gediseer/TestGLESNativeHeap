package com.yinge.opengl.egl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.yinge.opengl.R;

import java.util.ArrayList;

/**
 * EGL Env
 */
public class EGLRenderActivity extends AppCompatActivity {
    private ArrayList<EGLRender> mRenderList = new ArrayList();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egl);
    }

    public void onClick(View view){
        mRenderList.add(new EGLRender(500,500));
    }
    public void onClick2(View view){
        //调用相册
//        mEGLRender.destroy();
        if (mRenderList.size() > 0) {
            mRenderList.get(0).destroy();
            mRenderList.remove(0);
        }
        Toast.makeText(this,"remaining " + mRenderList.size(),Toast.LENGTH_SHORT).show();
    }

}

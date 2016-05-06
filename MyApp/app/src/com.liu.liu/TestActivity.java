package com.liu.liu;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by yulong.liu on 2016/4/29 0029.
 */
public class TestActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            DexClassLoader classLoader = new DexClassLoader("","","",ClassLoader.getSystemClassLoader());
            Class<?> klass =classLoader.loadClass("");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        AssetManager assetManager = null;
        try {
            assetManager = AssetManager.class.newInstance();
           Method method =  assetManager.getClass().getDeclaredMethod("addAssetPath",String.class);
            method.invoke(assetManager,"");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Resources resources = new Resources(assetManager,getResources().getDisplayMetrics(),getResources().getConfiguration());

        String name = getResources().getString(getResources().getIdentifier("","",""));
    }
}

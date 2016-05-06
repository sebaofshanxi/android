package com.liu.liu;

import android.util.Log;

/**
 * Created by yulong.liu on 2016/3/31 0031.
 */
public class Foo {

    public Foo(){

    }
    public void onBack(){
        Log.i("info", "调用了onback无参数方法");
    }
    public void onBack(String text){
        Log.i("info", "调用了onback有参数方法:"+text);
    }

    public void onClosed(String text){
        Log.i("info", "调用了onClosed:有参数方法"+text);
    }
    public void onClosed(){
        Log.i("info", "调用了onClosed无参数方法");
    }
}

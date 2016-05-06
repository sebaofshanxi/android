package com.liu.liu;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yulong.liu on 2016/3/31 0031.
 */
public class JsInterface implements JsMethodListener{

    private Foo foo;
    public void setFoo(Foo foo1){
        this.foo = foo1;
    }

    @Override
    public void method(String json) {

        try {
            Log.i("info",json);
            JSONObject jsonObject = new JSONObject(json);
            String method = jsonObject.getString("method");
            String parmas = jsonObject.getString("parmas");
            Class clazz = foo.getClass();
            if(!TextUtils.isEmpty(parmas)){
                Method m1 = clazz.getDeclaredMethod(method,String.class);
                m1.invoke(foo,new Object[]{parmas});

            }else{
                Method m1 = clazz.getDeclaredMethod(method);
                m1.invoke(foo);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}

package com.dominic.demos.dynamicproxy;

import android.util.Log;
import android.view.View;

import com.dominic.demo.annotations.JKPoint;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@JKPoint(desc = "java动态代理demo",
        link="https://www.imooc.com/article/254455")
public class ClickListenerInvocation<T extends View.OnClickListener> implements InvocationHandler {

    private T target;
    public ClickListenerInvocation(T realObj) {
        this.target = realObj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
        Log.d("java_reflect", "before invoke method: " + method.getName() + " proxy is " + proxy.getClass());
        return method.invoke(target, objects);
    }

    /**
     * this will return a proxy instance name formated like "$Proxy111"
     * which will extends Proxy and implements interface specified by in @Param T
     */
    public static <T extends View.OnClickListener>  T newProxyInstance(T listener) {
        return (T)Proxy.newProxyInstance(ClickListenerInvocation.class.getClassLoader(),
                listener.getClass().getInterfaces(), new ClickListenerInvocation(listener));
    }
}

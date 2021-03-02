package com.dominic.demos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.dominic.demo.annotations.JKPoint;
import com.dominic.demos.dynamicproxy.ClickListenerInvocation;
import com.dominic.demos.util.LogUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FirstFragment extends Fragment {

    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_first, container, false);
        hookViewClick((ViewGroup) rootView.findViewById(R.id.content_root));
        return rootView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        view.findViewById(R.id.textview_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.log("aoyo, I got you click event");
            }
        });
    }

    private void hookViewClick(ViewGroup rootView) {
        int viewCount = rootView.getChildCount();
        for (int i = 0; i < viewCount; i++) {
            View child =  rootView.getChildAt(i);
            LogUtil.log("child view: " + child);
            if (child instanceof TextView) {
                hookTextView(child);
            }
        }
    }

    @JKPoint(desc = "java hook sample",
            link = "https://blog.csdn.net/gdutxiaoxu/article/details/81459830")
    private void hookTextView(View target) {
        LogUtil.log("try to hook view:" + target);
        try {
            Method mListenerInfo = View.class.getDeclaredMethod("getListenerInfo");
            mListenerInfo.setAccessible(true);

            Object listenerInfoObj = mListenerInfo.invoke(target);
            if (listenerInfoObj == null) {
                return;
            }

            Class<?> listenerClz = Class.forName("android.view.View$ListenerInfo");
            Field clickListenerField = listenerClz.getDeclaredField("mOnClickListener");
            clickListenerField.setAccessible(true);

            View.OnClickListener originListener = (View.OnClickListener) clickListenerField.get(listenerInfoObj);
            if (originListener == null) {
                LogUtil.log("hook view failed view does not have a click listener in " + target);
                return;
            }
            View.OnClickListener hookListener = ClickListenerInvocation.newProxyInstance(originListener);
            clickListenerField.set(listenerInfoObj, hookListener);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
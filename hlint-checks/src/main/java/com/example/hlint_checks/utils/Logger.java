package com.example.hlint_checks.utils;

import com.google.common.base.Strings;

public class Logger {
    public static void list(Object... args) {
        System.out.println("---------------Hlog list-----------------" );
        for (Object item: args) {
            System.out.println(String.valueOf(item));
        }

    }

}

package com.dominic.toolbox.util

class DLog {

    static boolean logEnable = true
    static void init() {

    }

    static void println(Object msg) {
        if (logEnable) {
            println(msg)
        }
    }
}
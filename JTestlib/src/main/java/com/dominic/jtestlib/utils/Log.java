package com.dominic.jtestlib.utils;

import com.dominic.jtestlib.models.LinkNode;

public class Log {

    public static String d(Object msg) {
        System.out.println("hgl " + String.valueOf(msg));
        return String.valueOf(msg);
    }


    public static void dLink(LinkNode node) {
        if (node == null) {
            return;

        }

        StringBuilder sb = new StringBuilder();
        while (node != null) {
            sb.append(node.value + ", ");
            node = node.next;
        }
        System.out.println(sb.toString());
    }

    public static String d(Object[] msg) {

        if (msg == null || msg.length == 0) {
            return "[]";
        }
        StringBuffer sb = new StringBuffer();
        for (Object item: msg) {
            sb.append(String.valueOf(item)).append(" ");
        }

        return String.valueOf(sb.toString());
    }

}

package com.dominic.jtestlib.models;

import java.io.IOError;
import java.io.IOException;
import java.util.Arrays;

public class ArrayTest {


    private ArrayTest() {}

    private static volatile  ArrayTest mTest;

    public static ArrayTest getInstance() throws IOError {
        if (mTest == null) {
            synchronized (ArrayTest.class) {
                if (mTest == null) {
                    mTest = new ArrayTest();
                }
            }
        }

        return mTest;
    }

    private void methodA() throws IOException {

    }

    public static int[]  merge(int[] a, int[] b) {

        //new ArrayTest().methodA();

        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        int[] ret = new int[a.length + b.length];

        int retIndex = 0;
        int i = 0, j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                ret[retIndex] = a[i];
                i++;
            } else {
                ret[retIndex] = b[j];
                j++;
            }
            retIndex++;
        }

        if (i <= a.length - 1) {
            for (int start = i; start < a.length; start++) {
                ret[retIndex] = a[start];
                retIndex++;
            }
        }

        if (j <= b.length - 1) {
            for (int start = j; start < b.length; start++) {
                ret[retIndex] = b[start];
                retIndex++;
            }
        }



        printArr(ret);
        return ret;
    }


    public static void printArr(int[] a) {
        if (a == null) {
            System.out.println("a = null");
            return;
        }


        Arrays.fill(new int[10], -1);
        StringBuffer sb = new StringBuffer();
        for (int i =0; i< a.length; i++) {
            sb.append(a[i]+ " ");
        }
        System.out.println("a == " + sb.toString());
    }

}

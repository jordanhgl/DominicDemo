package com.dominic.jtestlib;

import com.dominic.jtestlib.models.ArrayTest;
import com.dominic.jtestlib.models.TreeNode;

import static com.dominic.jtestlib.models.TreeTest.sPrintTreeNode;


public class MyClass {

    /**
     * 求给定字符串的最大回文子子串
     * @param origin
     * @return
     */
    private static String maxSubString(String origin) {
        if (origin == null || origin.length() <= 0) {
            return null;
        }

        String maxRet = "";
        int maxL = 0;

        for (int i = 0; i < origin.length(); i++) {
            int centerIndex = i;
            int step = 1;
            int length4CurrIndex = 0;

            /**
             * 检查以位置i处字符为中心的回文子串
             * 形如aba
             */
            while (centerIndex - step >= 0 && centerIndex + step < origin.length()) {
                if (origin.charAt(centerIndex - step) == origin.charAt(centerIndex + step)) {
                    step++;
                    length4CurrIndex = Math.max(length4CurrIndex, (step - 1) * 2 + 1);
                    continue;
                }
                break;
            }

            if (length4CurrIndex > maxL && step - 1 > 0) {  //有匹配到回文子串
                maxL = length4CurrIndex;
                maxRet = origin.substring(i - step + 1, i + step);
            }


            step = 0;
            length4CurrIndex = 0;
            if (i + 1 >= origin.length() || origin.charAt(i) != origin.charAt(i + 1)) {
                continue;
            }

            /**
             * 检查以位置[i, i+1]处字符为中心的回文子串
             * 形如acbbca
             */
            while ((i - step) >= 0 && (i + 1 + step) < origin.length()) {    //左右都不越界
                if (origin.charAt((int) (i - step)) == origin.charAt((int) (i + 1 + step))) {   //i, i+1为中心左右对称
                    length4CurrIndex = Math.max(length4CurrIndex, 2 + step * 2);
                    step++;
                    continue;
                }
                break;
            }

            if (length4CurrIndex > maxL && step - 1 >= 0) { //匹配到回文子串
                maxL = length4CurrIndex;
                maxRet = origin.substring(i - (step - 1), i + 1 + step);
            }
        }
        System.out.println("Max length is " + maxL + " and ret string:");
        System.out.println(maxRet);
        return maxRet;
    }

    public static void main(String[] args) {
        int i = (int) (10 + 0.9);
        i = (int) (1 - 0.5f);
        System.out.println(i);
        System.out.println(Math.floor(10.5));
        System.out.println(Math.floor(0.5));

        String a = "abcb";
        System.out.println(a.substring(2 - 1, 2 + 1 + 1));


        String origin = "abaddabaaaaaaccaaaaaaaaaas";
        maxSubString(origin);

        ArrayTest.merge(new int[]{1,2,3,4, 9}, new int[]{5, 6, 7});
        ArrayTest.merge(new int[]{1,2,3,4}, new int[]{5, 6, 7});
        ArrayTest.merge(new int[]{2, 5, 6, 7}, new int[]{1, 3,4, 10});

    }
}
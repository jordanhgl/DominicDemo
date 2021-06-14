package com.dominic.jtestlib.models;

import com.dominic.jtestlib.utils.Log;

public class DpTest {


    /**
     * dp方法求从方格中左上角到右下角的所有路径,
     * 每次只能向右或者向下移动一格
     */
    public int findAllRoutes(int m, int n) {
        //1、定义dp数组，dp[i][j]表示从左上角位置到位置（i，j）的所有路径
        int[][] dp = new int[m][n];

        //边界条件
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                /**
                 * 对于i == 0， 所有到达i, j位置的路径只有一个
                 */
                if (i > 0 && j > 0) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                } else if (i > 0) {
                    //如果只移动行号，j为0
                    dp[i][j] = 1;
                } else if (j > 0) {
                    //如果只移动列号，i为0
                    dp[i][j] = 1;
                }
            }
        }
        return dp[m-1][n-1];
    }

    /**
     *  求两个字符串text1和text2的最大公共子序列长度
     * */
    public int searchMaxSubSequ(String text1, String text2) {
        if (text1 == null || text1.length() == 0) {
            return 0;
        }

        if (text2 == null || text2.length() == 0) {
            return 0;
        }

        /**
         * 定义dp[i][j]表示text1长度为i的子串和text2长度为j的子串的最大公共子序列
         * 其中有三个基础case如下：
         *      1、dp[0][0] = 0
         *      2、dp[0][j] = 0
         *      3、dp[i][0] = 0
         *
         * 转台转移方程
         *  if text1.charAt(i - 1) = text2.charAt(j -1)
         *      dp[i][j] = dp[i-1][j-1] + 1,
         *  else
         *      dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1])
         * */

        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];

        int i = 0, j = 0;
        while (i < m + 1) {
            dp[i][0] = 0;
            i++;
        }

        while (j < n + 1) {
            dp[0][j] = 0;
            j++;
        }

        //填充dp数组，套用转移方程
        for (i = 1; i <= m; i++) {
            int charA = text1.charAt(i - 1);    //char in text1 at index:i

            for (j = 1; j <= n; j++) {
                int charB = text2.charAt(j - 1);    //char in text2 at index:j
                if (charA == charB) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    Log.d(String.format("char = %c and dp[%d][%d]) == %d", (char)charA, i,j, dp[i][j]));
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }


    /**
     * 0-1背包问题
     * @param capacity 背包最大容量
     *
     */
    public int maxValue(int capacity, int[] weight, int[] value) {

        if (weight == null || weight.length == 0) {
            return 0;
        }

        if (value == null || value.length == 0) {
            return 0;
        }
        if (weight.length != value.length) {
            return 0;
        }

        if (capacity <=0) {
            return 0;
        }

        int count = weight.length;
        int[][] dp = new int[count + 1][capacity + 1];

        //确定边界条件
        /**
         * 1、如果背包容量为0，那么结果为0
         * 2、如果背包放入0件物品，那么结果为0
         * 使用二维数组表示背包容量为j,放入物品为i件时的最大价值用dp[i][j]表示
         */

        //        初始化边界case
        for (int k = 0; k <= count; k++) {
            dp[k][0] = 0;       //背包容量为0时，其存放的最大价值为0
        }

        for (int k = 0; k <= capacity; k++) {
            dp[0][k] = 0;       //不放入任何物品时，任意容量背包存放的最大价值为0
        }

        int maxI = 0, maxJ = 0;
        int maxVal = 0;
        for (int i = 1; i <= count; i++) {   //物品件数
            for (int j = 1; j <= capacity; j++) {    //背包(剩余)容量
                /**
                 * 对于一件物品i，不放入时:
                 *      可以用dp[i-1][j]来表示，此时dp[i][j] = dp[i-1][j]
                 * 放入时:
                 *      dp[i][j] = dp[i-1][j = weight[i]] + value[i]
                 *
                 * 而求最大值就可以表示为
                 *      dp[i][j] = max(dp[i-1][j], dp[i-1][j = weight[i]] + value[i])
                 */
                if (j < weight[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }

                int notChooseOpt = dp[i-1][j];
                int chooseOpt = dp[i-1][j - weight[i - 1]] + value[i - 1];  //选择第i件商品， 放入背包 减去i的重量， 同时加上i的价值
                dp[i][j] = Math.max(notChooseOpt, chooseOpt);
                if (maxVal <= dp[i][j]) {
                    maxVal = dp[i][j];
                    maxI = i;
                    maxJ = j;
                }
            }
        }

        return dp[maxI][maxJ];
    }




    public static void main(String[] args) {
        DpTest dpTest = new DpTest();
        int ret = dpTest.findAllRoutes(5, 4);
        Log.d("total route is " + ret);
        ret = dpTest.findAllRoutes(2, 3);
        Log.d("total route is " + ret);

        ret = dpTest.findAllRoutes(3, 3);
        Log.d("total route is " + ret);

        String text1 = "abcde";
        String text2 = "fbhdge";
        int maxSubLength = dpTest.searchMaxSubSequ(text1, text2);
        Log.d("max sub seq for text1 and text2 is "+ maxSubLength);

        int maxVal = dpTest.maxValue(20, new int[]{1, 2, 20, 5}, new int[]{5, 4, 16, 2});
        Log.d("max value is " + maxVal);

    }
}

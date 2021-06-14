package com.dominic.jtestlib.models;

import com.dominic.jtestlib.utils.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 回溯算法
 */
public class BackTrackTest {

    /**
     * 从一个数组中筛选出能相加最终结果等于target的组合
     */
    public List<List<Integer>> findAllCombine(int[] values, int target) {

        List<List<Integer>> resultList = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(values);

        dfs(values, 0, target, resultList, path);
        return resultList;
    }

    private void dfs(int[] arr, int index, int targetLeft,
             List<List<Integer>> result, List<Integer> path) {

        if (targetLeft < 0) {  //停止继续遍历
            return;
        }

        if (targetLeft == 0) { //
            result.add(new ArrayList<Integer>(path));
            return;
        }

        for (int i = index; i< arr.length; i++) {
            path.add(arr[i]);
            dfs(arr, i + 1, targetLeft - arr[i], result, path);
            path.remove(path.size() - 1);
        }
    }


    /**
     * N quene
     * @param args
     */
    public List<List<String>> solveNQueue(int n) {
        List<List<String>> ret = new ArrayList<>();
        return ret;
    }


    public static void main(String[] args) {
        int[] values = new int[]{1, 2, 2, 2, 4, 5, 8, 10};
        BackTrackTest trackTest = new BackTrackTest();
        List<List<Integer>> ret = trackTest.findAllCombine(values, 20);
        Log.d(ret);
        //hgl [[1, 2, 4, 5, 8], [1, 4, 5, 10], [2, 8, 10]]
    }
}

package com.dominic.jtestlib.models;

import com.dominic.jtestlib.utils.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;



public class TreeTest {

    public static class DfsTree {

        /**
         * 1、遍历顺序 前序(self-left-right)  中序（left-self-right）  后续(left-right-self)
         * 2、求解顺序  Bottom Up  & Top to Bottom
         *
         */
        /***
         *  dfs 模板  ———— 前序遍历 preorder
         * @param root
         * @return
         */
        public void preOrderTree(TreeNode root) {
            if (root == null) {
                return;
            }

            Log.d(root.value);
            preOrderTree(root.left);
            preOrderTree(root.right);
        }

        /**
         * 中序遍历 先遍历左侧 ，在中间，在右侧
         * @param root
         */
        public void inOrderTree(TreeNode root) {
            if (root == null) {
                return;
            }
            inOrderTree(root.left);
            Log.d(root.value);
            inOrderTree(root.right);
        }

        /**
         * 后序遍历 先遍历左侧 ，在中间，在右侧
         * @param root
         */
        public void postOrderTree(TreeNode root) {
            if (root == null) {
                return;
            }
            postOrderTree(root.left);
            postOrderTree(root.right);
            Log.d(root.value);
        }


        public  int dfsForMaxDept(TreeNode root, int mode) {
            if (mode == 1) {
                return getDeptFromTop2Bottom(root, 0);
            }

            return getDeptFromBtmToTop(root);
        }

        private int getDeptFromTop2Bottom(TreeNode node, int parentLevelDept) {
            if (node == null) {
                return parentLevelDept;
            }

            if (node.left == null && node.right == null) {
                return parentLevelDept + 1;
            }

            int leftDept = getDeptFromTop2Bottom(node.left, parentLevelDept + 1);
            int rightDept = getDeptFromTop2Bottom(node.right, parentLevelDept + 1);

            return Math.max(leftDept, rightDept);
        }

        /**
         * 最大深度  —— 自底向上
         * @param node
         * @return
         */
        private int getDeptFromBtmToTop(TreeNode node) {
            if (node == null) {
                return 0;
            }
            return Math.max(getDeptFromBtmToTop(node.left),
                        getDeptFromBtmToTop(node.right)) + 1;  //increse 1
        }


        /**
         * 二叉树的最大路径
         */

        int res = 0;
        public int caculateMaxSumPath(TreeNode node) {
            dfs4MaxSum(node);
            return res;
        }

        private int dfs4MaxSum(TreeNode node) {
            //base case
            if (node == null) {
                return 0;
            }
            int sumLeft = Math.max(dfs4MaxSum(node.left), 0);
            int sumRight = Math.max(dfs4MaxSum(node.right), 0);
            res = Math.max(res, node.value + sumLeft + sumRight);
            return Math.max(sumLeft, sumRight) + node.value;
        }




        /**
         * leetcode No. 129
         * sum of {Root to leaf numbers}
         */
        int globalSum = 0;

        public int caculateSumOfRootToLeaf(TreeNode root) {
            if (root == null) {
                return globalSum;
            }

            root2LeafNums(root, 0);
            return globalSum;
        }
        private void root2LeafNums(TreeNode node, int parentVal) {
            /**
             * 1、 base case
             */
            if (node.left == null && node.right == null) {  //leaf node
                int newNum = parentVal * 10 + node.value;
                Log.d("GlobalSum = " + globalSum + " + new Number: " + newNum);
                globalSum += newNum;
                return;
            }

            if (node.left !=null) {
                root2LeafNums(node.left, parentVal * 10 + node.value);
            }

            if (node.right != null) {
                root2LeafNums(node.right, parentVal * 10 + node.value);
            }
        }


        private TreeNode parentNode;
        public TreeNode findDirectParent(TreeNode node, TreeNode child1, TreeNode child2) {
            if (node == null) {
                return null;
            }

//            Set<Integer> rootSet = bottomupCollectChild(node, child1, child2);
//            Log.d("     rootset size " + rootSet.size());
//            int ret = parentNode != null ? parentNode.value : -1;
//            Log.d(String.format("parentNodeVal for (%d, %d) = ", child1, child2) + ret);
//            return ret;


            List<TreeNode> path1 = nodePath(node, child1);
            Log.d(path1);

            List<TreeNode> path2 = nodePath(node, child2);
            Log.d(path2);
            if (path1 == null || path1.size() == 0
                    || path2 == null || path2.size() == 0) {
                return null;
            }


            TreeNode retNode = null;
            int index = 0;
            int p1Size = path1.size();
            int p2Size = path2.size();
            while (p1Size - 1 - index >= 0 && p2Size - 1 - index >= 0
                    && path1.get(p1Size - 1 - index).value == path2.get(p2Size - 1 - index).value) {
                retNode = path1.get(p1Size - 1 - index);
                index++;
            }

            int parentNodeVal = retNode != null ? retNode.value: -999;
            Log.d(String.format("parentNodeVal for (%d, %d) = ", child1.value, child2.value) + parentNodeVal);
            return retNode;
        }

        private Set<Integer> bottomupCollectChild(TreeNode node, int child1, int child2) {
            Set<Integer> nodeSet = new HashSet<>();

            if (node.left == null && node.right == null) {  //叶子节点
                 nodeSet.add(node.value);
                 return nodeSet;
            }

            //get children set for left node
            if (node.left != null) {
               Set<Integer> leftSet = bottomupCollectChild(node.left, child1, child2);
               nodeSet.addAll(leftSet);
            }

            //get child set for right node
            if (node.right != null) {
                Set<Integer> rightSet = bottomupCollectChild(node.right, child1, child2);
                nodeSet.addAll(rightSet);
            }

            if (nodeSet.contains(child1) && nodeSet.contains(child2) && parentNode == null) {
                parentNode = node;
                return nodeSet;
            }

            nodeSet.add(node.value);
            if (nodeSet.contains(child1) && nodeSet.contains(child2) && parentNode == null) {
                parentNode = node;
                return nodeSet;
            }
            return nodeSet;
        }


        public List<TreeNode> nodePath(TreeNode node, TreeNode targetVal) {
            List<TreeNode> path = null;
            if (node != null && node.value == targetVal.value) {
                path = new ArrayList<>();
                path.add(node);
                return path;
            }

            if (node.left != null) {
                List<TreeNode> leftPath = nodePath(node.left, targetVal);
                if (leftPath != null) {
                    leftPath.add(node);
                    return leftPath;
                }
            }

            if (node.right != null) {
                List<TreeNode> rightPath = nodePath(node.right, targetVal);
                if (rightPath != null) {
                    rightPath.add(node);
                    return rightPath;
                }
            }

            return path;
        }

    }

    /**
     * ******************广度优先遍历******************
     * 打印tree
     * @return
     */
    public static int bfsTreeForMaxDeepth(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int dept = 0;
        while (!queue.isEmpty()) {
            dept += 1;
            List<Integer> currLevelVals = new ArrayList<>();
            int size4CurrLevel = queue.size();
            for (int i = 0; i < size4CurrLevel; i++) {
                TreeNode tmpNode = queue.poll();
                currLevelVals.add(tmpNode.value);
                if (tmpNode.left != null) {
                    queue.offer(tmpNode.left);
                }
                if (tmpNode.right != null) {
                    queue.offer(tmpNode.right);
                }
            }
        }
        return dept;
    }


    /**
     * ******************广度优先遍历******************
     * 打印tree
     * @return
     */
    public static List<Integer> bfsTreeShadowInRight(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            List<Integer> currLevelVals = new ArrayList<>();
            int size4CurrLevel = queue.size();
            for (int i = 0; i < size4CurrLevel; i++) {
                TreeNode tmpNode = queue.poll();
                currLevelVals.add(tmpNode.value);
                if (tmpNode.left != null) {
                    queue.offer(tmpNode.left);
                }
                if (tmpNode.right != null) {
                    queue.offer(tmpNode.right);
                }

                if (i == size4CurrLevel - 1) {
                    result.add(tmpNode.value);
                }
            }

        }
        return result;
    }


    /**
     * ******************广度优先遍历******************
     * 打印tree
     * @return
     */
    public static List<List<Integer>> bfsPrintTree(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> currLevelVals = new ArrayList<>();
            int size4CurrLevel = queue.size();
            for (int i = 0; i < size4CurrLevel; i++) {

                TreeNode tmpNode = queue.poll();
                currLevelVals.add(tmpNode.value);

                if (tmpNode.left != null) {
                    queue.offer(tmpNode.left);
                }

                if (tmpNode.right != null) {
                    queue.offer(tmpNode.right);
                }
            }
            result.add(currLevelVals);
        }
        return result;
    }


    /**
     * ****************广度优先遍历********************
     * 蛇形打印二叉树
     * @param root
     */
    public static String sPrintTreeNode(TreeNode root, boolean dir) {
        Stack<TreeNode> stackLoop = new Stack<>();
        stackLoop.push(root);

        boolean direction = dir;

        StringBuilder sb = new StringBuilder();

        List<TreeNode> tmp = new ArrayList<>();
        while (!stackLoop.isEmpty()) {
            TreeNode ele = stackLoop.pop();
            sb.append(ele.value);

            if (direction) {
                if (ele.left != null) {
                    tmp.add(ele.left);
                }
                if (ele.right != null) {
                    tmp.add(ele.right);
                }
            } else {
                if (ele.right != null) {
                    tmp.add(ele.right);
                }
                if (ele.left != null) {
                    tmp.add(ele.left);
                }
            }

            if (stackLoop.isEmpty()) {
                direction = !direction; //switch direction
                while (!tmp.isEmpty()) {
                    stackLoop.push(tmp.remove(0));
                }
            }
        }
        return sb.toString();
    }

    public List<List<Integer>> printAllRoot2leafRoutes(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        dfsNode(root, new ArrayList<Integer>(), ret);
        Log.d("All route for current tree:\n");
        Log.d(ret);
        return ret;
    }

    private void dfsNode(TreeNode node, List<Integer> routePrefix,
                         List<List<Integer>> ret) {
        if (node == null) {
            return;
        }

        //case leaf
        if (node.left == null && node.right == null) {
            Log.d("     add leaf node " + node.value);
            routePrefix.add(node.value);
            ret.add(new ArrayList<Integer>(routePrefix));   //find one route
            routePrefix.remove(routePrefix.size() - 1);
            return;
        }

        //case left child
        if (node.left != null) {
            Log.d("dfs left child("+node.left.value+") for node: " + node.value);
            routePrefix.add(node.value);
            dfsNode(node.left, routePrefix, ret);
            routePrefix.remove(routePrefix.size() - 1);
        }

        //case right child
        if (node.right != null) {
            Log.d("dfs right child("+node.right.value+") for node: " + node.value);
            routePrefix.add(node.value);
            dfsNode(node.right,  routePrefix, ret);
            routePrefix.remove(routePrefix.size() - 1);
        }

        //        if (node.left != null) {
        //            routePrefix.add(node.left.value);
        //            dfsNode(node.left, routePrefix, ret);
        //            routePrefix.remove(routePrefix.size() - 1);
        //        }
        //
        //        if (node.right != null) {
        //            routePrefix.add(node.right.value);
        //            dfsNode(node.right, routePrefix, ret);
        //            routePrefix.remove(routePrefix.size() - 1);
        //        }
    }

    public static void main(String[] args) {
        /**
         *       1
         *      / \
         *     /   \
         *     2    3
         *    /\    /
         *   /  \  /
         *  4   5  6
         *          \
         *           7
         *
         */

        TreeNode node7 = new TreeNode(7, null, null);
//        TreeNode node6 = new TreeNode(6, null, new TreeNode(7));
        TreeNode node6 = new TreeNode(6, null, node7);
        TreeNode node2 = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        TreeNode node3 = new TreeNode(3, node6, null);
        TreeNode root = new TreeNode(1, node2, node3);
        String sprintRet = sPrintTreeNode(root, true);
        System.out.println("print in s: " + sprintRet);

        sprintRet = sPrintTreeNode(root, false);


        System.out.println("print in s: " + sprintRet);

        TreeTest instance = new TreeTest();
        List<List<Integer>> result = bfsPrintTree(root);
        Log.d("------------------BFS tree------------------");
        Log.d(result);


        List<Integer> result2 = bfsTreeShadowInRight(root);
        Log.d("------------------BFS tree print shadown in right------------------");
        Log.d(result2);

        node3.left = null;  //for another test
        result2 = bfsTreeShadowInRight(root);
        Log.d("------------------BFS tree print shadown in right------------------");
        Log.d(result2);


        node3.left = node6;
        int dept = bfsTreeForMaxDeepth(root);
        Log.d("------------------BFS tree max dept------------------");
        Log.d("max dept  = "+ dept);

        TreeTest.DfsTree dfsIns = new TreeTest.DfsTree();
        dept = dfsIns.dfsForMaxDept(root, 1);
        Log.d("------------------DFS tree max dept(T-t-B)------------------");
        Log.d("max dept  = "+ dept);

        dept = dfsIns.dfsForMaxDept(root, 2);
        Log.d("------------------DFS tree max dept(B-t-T)------------------");
        Log.d("max dept  = "+ dept);

        dfsIns.caculateMaxSumPath(root);
        Log.d("max path sum " + dfsIns.res);

        dfsIns.caculateSumOfRootToLeaf(root);
        Log.d("max path sum " + dfsIns.globalSum);


        TreeNode parentNodeVal = dfsIns.findDirectParent(root, node2, node6);
        dfsIns.parentNode = null;
//        parentNodeVal = dfsIns.findDirectParent(root, 2, 6);
//        dfsIns.parentNode = null;
//        parentNodeVal = dfsIns.findDirectParent(root, 2, 4);
//        dfsIns.parentNode = null;
//        parentNodeVal = dfsIns.findDirectParent(root, 2, 40);
//
//        dfsIns.parentNode = null;
//        parentNodeVal = dfsIns.findDirectParent(root, 6, 7);
//
//
//        dfsIns.parentNode = null;
//        parentNodeVal = dfsIns.findDirectParent(root, 3, 7);
//
//        dfsIns.parentNode = null;
//        parentNodeVal = dfsIns.findDirectParent(root, 4, 7);


        List<TreeNode> path6 = dfsIns.nodePath(root, node6);
        Log.d(path6);


        //node3.right = node6;
        instance.printAllRoot2leafRoutes(root);

        int a = 10;
        Log.d("a++ " + a++);
        Log.d("a " + a);
    }

}

package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    /**
     * 回溯算法
     * 先写遍历，在看终止条件
     * void backtrack(){
     *      if(终止条件) {
     *          保存结果;
     *          return;
     *      }
     *      for(选择: 本层集合中元素 (树中结点孩子的数量就是集合的大小)){
     *          处理节点；
     *          backtrack(路径， 选择列表);
     *          回溯， 撤销处理结果
     *      }
     *
     * }
     */

    /**
     * 回溯组合问题
     *
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * 你可以按 任何顺序 返回答案。
     *
     * 示例 1：
     * 输入：n = 4, k = 2
     * 输出：
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     *
     * 先写遍历，在看终止条件
     *            1                            2
     *     2   3   4   5   ...         3   4   5   ...
     * 3 4 5 ...
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int start  = 1;
        backtrack(n, k, start, list, res);
        return res;
    }

    private void backtrack(int n, int k, int start, List<Integer> list, List<List<Integer>> res) {
        if (list.size() == k){ // 终止条件
            List<Integer> tempList = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                tempList.add(list.get(i));
            }
            res.add(tempList);  //存放结果
            return;  //返回
        }
        for (int i = start; i <= n; i++) {  // 遍历同一层节点
            list.add(i);  //处理节点
            backtrack(n, k, i+1, list, res);    //递归
            list.remove(list.size()-1);     // 回溯
        }
    }


    /**
     * 回溯排列问题
     */
    public List<List<Integer>> rank(int n, int k) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        boolean used[] = new boolean[n];
        dfs(n, k, list, res, used);
        return res;
    }

    private void dfs(int n, int k, List<Integer> list, List res, boolean used[]){
        if(list.size() == k){ // 终止条件
            List<Integer> tempList = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                tempList.add(list.get(i));
            }
            res.add(tempList); //存放结果
            return;
        }
        for(int i = 0; i < n; i++){ // 遍历同一层节点
            if(used[i] == true){ //处理节点
                continue;
            }
            used[i] = true;
            list.add(i + 1);
            dfs(n, k, list, res, used); //递归
            used[i] = false; // 回溯
            list.remove(list.size() - 1);
        }
    }
}

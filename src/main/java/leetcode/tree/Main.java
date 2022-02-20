package leetcode.tree;

import leetcode.util.TreeNode;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        TreeNode node2 = root.left;
        TreeNode node3 = root.right;
        node2.left = new TreeNode(4);
        node2.right = new TreeNode(5);
        node3.left = new TreeNode(6);

        int num = (new Main()).countNodes1(root);
        System.out.println(num);
    }

    /**
     * 经典：
     *  中序遍历
     *  BFS深度
     *  层序遍历
     *  统计节点个数
     */

    /**
     * 94. 二叉树的中序遍历，递归
     */
    List<Integer> res = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root != null){
            inorderTraversal(root.left);
            res.add(root.val);
            inorderTraversal(root.right);
        }
        return res;
    }

    /**
     * 94. 二叉树的中序遍历，非递归
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack();
        TreeNode cur = root;
        // 栈不为空或者当前节点不为null时候循环
        while (cur != null || !stack.isEmpty()){
            // 遍历左子树，不为空，添加到栈中
            if(cur != null){
                stack.push(cur);
                // 遍历左子树
                cur = cur.left;
            }
            // 遍历到节点为空时，出栈，继续遍历出栈节点的右子树
            else {
                cur = stack.pop();
                res.add(cur.val);
                // 遍历右子树
                cur = cur.right;
            }
        }
        return res;
    }

    /**
     * BFS 104. 二叉树的最大深度
     */
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }

    /**
     * BFS 111. 二叉树的最小深度
     */
    public int minDepth(TreeNode root) {
        if(root == null){ return 0;}
        if(root.left == null && root.right != null){
            return 1 + minDepth(root.right);
        }
        if(root.right == null && root.left != null){
            return 1 + minDepth(root.left);
        }
        return Math.min(minDepth(root.left) + 1, minDepth(root.right) + 1);
    }


    /**
     * 102. 二叉树的层序遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        // 节点为空时，特殊处理
        if(root != null){ queue.add(root); }
        List<List<Integer>> res = new ArrayList<>();
        // 队列不为空，则一直循环
        while (!queue.isEmpty()){
            List<Integer> level_res = new ArrayList<>();
            int n = queue.size();
            // 树的单层循环（按照当前层的节点个数）
            for (int i = 0; i < n; i++) {
                // 出栈访问当前层的节点
                TreeNode pollNode = queue.poll();
                level_res.add(pollNode.val);
                // 入栈下一层的节点
                if(pollNode.left != null){
                    queue.add(pollNode.left);
                }
                if(pollNode.right != null){
                    queue.add(pollNode.right);
                }
            }
            res.add(level_res);
        }
        return res;
    }

    /**
     * 222. 完全二叉树的节点个数
     */
    int num = 0;
    public int countNodes(TreeNode root) {
        if (root == null){
            return 0;
        }
        if (root != null){
            num++;
            countNodes(root.left);
            countNodes(root.right);
        }
        return num;
    }

    /**
     * 222. 完全二叉树的节点个数
     */
    public int countNodes1(TreeNode root) {
        if(root == null){ return 0; }
        int level = 0;
        TreeNode node = root;
        while(node.left != null){
            level++;
            node = node.left;
        }
        int low = 1 << level;
        int high = (1 << (level + 1)) - 1;
        while (low <= high){
            int mid = low + (high - low) / 2;
            if (exists(root, level, mid)){
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return low - 1;
    }

    public boolean exists(TreeNode root, int level, int k) {
        int bits = 1 << (level - 1);
        TreeNode node = root;
        // 遍历到最底层，查看是否有叶子节点
        while (node != null && bits > 0) {
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }

}

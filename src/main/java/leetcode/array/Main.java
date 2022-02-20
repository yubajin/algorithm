package leetcode.array;

public class Main {
    public static void main(String[] args) {
        int[][] res = (new Main()).generateMatrix(4);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 经典：
     *  冒泡排序
     *  二分查找
     *  快慢指针移除元素
     *
     */

    /**
     * 912. 排序数组
     */
    public int[] bubbleSortArray(int[] nums) {
        for(int i = 0; i < nums.length - 1; i++){
            for (int j = 0; j < nums.length - i - 1; j++){
                if(nums[j] > nums[j+1]){
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
        return nums;
    }

    /**
     * 堆排序
     */
    public int[] heapSortArray(int[] nums) {

        return nums;
    }

    /**
     * 快排
     */
    public void quickSortArray(int[] nums, int low, int high) {
        if(low < high){
            int pivot = partition(nums, low, high);
            quickSortArray(nums, low, pivot - 1);
            quickSortArray(nums, pivot + 1, high);
        }
    }

    public int partition(int[] nums, int low, int high){
        int pivot = nums[low];
        while (low < high){
            while (nums[high] >= pivot) { --high; }
            nums[low] = nums[high];
            while (nums[low] <= pivot) { ++low; }
            nums[high] = nums[low];
        }
        nums[low] = pivot;
        return low;
    }

    /**
     * 折半查找
     */
    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) >> 1;
            if (target == nums[mid]){
                left = mid;
            }else if (target < nums[mid]){
                right = mid - 1;
            }else if(target > nums[mid]){
                left = mid + 1;
            }
        }
        if(nums[left] == target){
            return left;
        }else {
            return -1;
        }
    }

    /**
     * 27. 快慢指针移除元素
     * 输入：nums = [3,2,2,3], val = 3
     * 输出：2, nums = [2,2]
     * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
     *
     */
    public int removeElement(int[] nums, int val) {
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if(nums[fastIndex] != val){
                nums[slowIndex++] = nums[fastIndex];
            }
        }
        return slowIndex;
    }

    /**
     * 螺旋数组
     */
    public int[][] generateMatrix(int n) {
        int res[][] = new int[n][n];
        int sqrt = n * n;
        int num = 0;
        int left = 0, right = n - 1, top = 0, bottom = n - 1;
        while (num < sqrt){
            for (int i = left; i <= right; i++){
                res[top][i] = ++num;
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                res[i][right] = ++num;
            }
            right--;
            for (int i = right; i >= left ; i--) {
                res[bottom][i] = ++num;
            }
            bottom--;
            for (int i = bottom; i >= top ; i--) {
                res[i][left] = ++num;
            }
            left++;
        }
        return res;

    }

    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0
     *
     * 示例 1：
     * 输入：target = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     */
    public int minSubArrayLen(int target, int[] nums) {
        // 滑动窗口内连续子数组之和
        int sum = 0;
        // 滑动窗口内连续子数组得长度
        int substrlen = 0;
        int res = Integer.MAX_VALUE;
        // 滑动窗口起始序号
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum >= target){
                substrlen = j - i + 1;
                res = substrlen < res ? substrlen : res;
                sum -= nums[i];
                i++;
            }
        }
        if (res == Integer.MAX_VALUE) { res = 0; }
        return res;
    }
}

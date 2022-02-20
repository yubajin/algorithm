package leetcode.dynamicprogram;

public class Base {
    public static void main(String[] args) {
//        new Base().test_bag_problem();

        System.out.println(new Base().test_bag_problem1());
    }
    /**
     *          重量      价值
     * 物品0      2         15
     * 物品1      3         20
     * 物品2      5         30
     * bag size = 5
     *
     * 1、定义：
     *  dp[i][j] 物品0-i中任选，放入容量为j的背包中，最大价值为dp[i][j]
     * 2、递推公式
     *  dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i]] + value[i])
     * 3、初始化
     *  dp[i][0] = 0
     *  dp[0][j] = dp[0][j-weight[i]] + value[i]
     *
     * 倒序放入
     * dp[0][5] = dp[0][3] + 15; 15
     * dp[0][4] = dp[0][2] + 15; 15
     * dp[0][3] = dp[0][1] + 15; 15
     * dp[0][2] = dp[0][0] + 15; 15
     * dp[0][1] = 0;

     * 4、遍历
     *      * 物品0  	2	15
     *      * 物品1	    3	20
     *      * 物品2	    5	30
     *      5
     * i= 1 (0,1物体任选)
     * j=0	dp[1][0]=0
     * j=1	dp[1][1]=dp[0][1] = 0
     * j=2	dp[1][2]=dp[0][2] = 15
     * j=3	dp[1][3]=max(dp[0][3], dp[0][0]+20) = max(15, 20) = 20
     * j=4	dp[1][4]=max(dp[0][4], dp[0][1]+20) = max(15, 20) = 20
     * j=5  dp[1][5]=max(dp[0][5], dp[0][2]+20) = max(15, 35) = 35
     */

    private int test_bag_problem(){
        int [] weight = new int[]{2, 3, 6};
        int [] value = new int[]{15, 20, 30};
        int bagSize = 5;
        // 1、dp[i][j]表示在物品0-i之间任选，背包空间为j的情况下，背包能够装的最大价值
        int dp[][] = new int[weight.length + 1][bagSize + 1];
        // 2、dp初始化
//        for (int i = 0; i < weight.length; i++) {
//            dp[i][0] = 0;
//        }
        for (int j = bagSize; j >= weight[0]; j--) {
            dp[0][j] = dp[0][j-weight[0]] + value[0];
        }

        // 4、遍历顺序
        // 遍历物品
        for (int i = 1; i < weight.length; i++) {
            // 遍历背包容量
            for (int j = 1; j <= bagSize ; j++) {
                if(j > weight[i]){
                    // 3、递推公式
                    dp[i][j] =Math.max(dp[i-1][j], dp[i-1][j-weight[i]] + value[i]);
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        //5、验证
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j <=  bagSize; j++) {
                System.out.print(dp[i][j] + ",");
            }
            System.out.println();
        }
        return dp[weight.length-1][bagSize];
    }

    /**
     * 滚动数组
     * bagsize = 5
     *      * 物品0  	2	15
     *      * 物品1	    3	20
     *      * 物品2	    5	30
     *      dp[0] = {00, 00, 15, 15, 15, 15}
     *      dp[1] = {00, 00, 15, 00+20, 00+20, 15+20} = {00, 00, 15, 20, 20, 35}
     *      dp[2] = {00, 00, 15, 15, 15, max(35,00+30} = {00, 00, 15, 20, 20, 35}
     */
    private int test_bag_problem1(){
        int [] weight = new int[]{2, 3, 6};
        int [] value = new int[]{15, 20, 30};
        int bagSize = 5;

        // 1、dp[i][j]表示在物品0-i之间任选，背包空间为j的情况下，背包能够装的最大价值
        int dp[] = new int[bagSize + 1];
        // 2、dp初始化
        for (int i = bagSize; i >= weight[0]; i--) {
            dp[i] = dp[i-weight[0]] + value[0];
        }
        // 4、遍历顺序
        // 遍历物品
        for (int i = 1; i < weight.length; i++) {
            // 遍历背包
            for (int j = bagSize; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j-weight[i]] + value[i]);
            }
        }
        //5、验证
        return dp[bagSize];
    }
}

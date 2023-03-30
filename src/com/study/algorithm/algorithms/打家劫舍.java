package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

public class 打家劫舍 {

    @Test
    public void 打家劫舍() {
        int[] ints = {2, 1, 6, 8, 3};
        System.out.println("打家劫舍:" + rob(ints));
    }

    // 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
    //
    // 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
    //
    //
    //
    // 示例 1：
    //
    // 输入：[1,2,3,1]
    // 输出：4
    // 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
    //      偷窃到的最高金额 = 1 + 3 = 4 。
    // 示例 2：
    //
    // 输入：[2,7,9,3,1]
    // 输出：12
    // 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
    //      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/house-robber
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 代表上一间选中后的劫舍结果
        int lastCheck = nums[0];
        // 代表上一间未选中后的劫舍结果
        int lastUncheck = 0;

        int temp;

        // 思路：DP.
        // 状态方程：f(x)=max(lastCheck,lastUncheck+nums[i])
        // 当前状态为上一间选中和上一间未选中加上当前一间金额的最大值。更新思路如下：
        for (int i = 1, l = nums.length; i < l; i++) {

            // 若上一间选中后的金额大于上一间未选中加上当前一间金额，则当前这一间直接跳过（没有必要再获取了）
            // 并使得上一间未选中的金额等于上一间选中的金额（因为这一间跳过后，相当于未选中）
            if (lastCheck > (lastUncheck + nums[i])) {
                lastUncheck = lastCheck;
            } else {
                // 若上一间选中后的金额小于上一间未选中加上当前一间金额
                // 则获取这一间（上一间未选中加上当前一间金额），使上一间选中的金额等于此值
                // 上一间未选中的金额值就为之前上一间选中的金额金额值（用temp做中间变量）
                temp = lastCheck;
                lastCheck = lastUncheck + nums[i];
                lastUncheck = temp;
            }
        }

        return Math.max(lastCheck, lastUncheck);
    }

    //你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
    //
    //给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
    //
    // 
    //
    //示例 1：
    //
    //输入：nums = [2,3,2]
    //输出：3
    //解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
    //示例 2：
    //
    //输入：nums = [1,2,3,1]
    //输出：4
    //解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
    //     偷窃到的最高金额 = 1 + 3 = 4 。
    //示例 3：
    //
    //输入：nums = [0]
    //输出：0
    // 
    //
    //提示：
    //
    //1 <= nums.length <= 100
    //0 <= nums[i] <= 1000
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/house-robber-ii
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    @Test
    public void 打家劫舍II() {
        int[] ints = {2, 3, 2};
        System.out.println("打家劫舍II:" + robII(ints));
    }

    public int robII(int[] nums) {
        //既然收尾相连：
        //那么我们选择首位时，末位将不能选择；
        //我们选择末位时，首位将不能选择。
        //所以只要计算数组两部分即可，第一步计算数组nums（0...len-2），不计算末位数；第二步计算数组nums(1...len-1)，不计算首位数
        //状态转移方程即跟Ⅰ是一样的
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }

        int select   = nums[0];
        int unSelect = 0;
        int result   = 0;
        int temp     = 0;
        for (int i = 1, l = len - 1; i < l; i++) {
            temp = unSelect;
            unSelect = Math.max(select, unSelect);
            select = temp + nums[i];
        }

        result = Math.max(select, unSelect);
        select = nums[1];
        unSelect = 0;
        for (int i = 2; i < len; i++) {
            temp = unSelect;
            unSelect = Math.max(select, unSelect);
            select = temp + nums[i];
        }

        result = Math.max(result, Math.max(select, unSelect));
        return result;
    }

    @Test
    public void 打家劫舍Ⅲ() {
        TreeNode treeNode = new TreeNode(1);
        treeNode.setLeft(new TreeNode(2));
        treeNode.setRight(new TreeNode(2));
        treeNode.getLeft().setLeft(new TreeNode(3));
        treeNode.getLeft().setRight(new TreeNode(2));
        treeNode.getLeft().getRight().setRight(new TreeNode(4));
        treeNode.getRight().setRight(new TreeNode(3));
        System.out.println("打家劫舍Ⅲ:" + rob(treeNode));
    }

    // 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。
    // 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
    // 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
    //
    // 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
    //
    // 示例 1:
    //
    // 输入: [3,2,3,null,3,null,1]
    //
    //     3
    //    / \
    //   2   3
    //    \   \
    //     3   1
    //
    // 输出: 7
    // 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
    // 示例 2:
    //
    // 输入: [3,4,5,1,3,null,1]
    //
    //     3
    //    / \
    //   4   5
    //  / \   \
    // 1   3   1
    //
    // 输出: 9
    // 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/house-robber-iii
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int rob(TreeNode root) {

        // 思路，跟“打家劫舍”思路差不多，只不过多了一条路共选择（二叉树）
        int[] ints = robDFS(root);
        return Math.max(ints[0], ints[1]);
    }

    // 返回一个数组，两位，第一位代表上一个打劫后的金额，第二位代表上一个未打劫后的金额
    public int[] robDFS(TreeNode root) {

        // 前置条件
        if (root == null) {
            return new int[]{0, 0};
        }

        // 前置条件
        if (root.left == null && root.right == null) {
            return new int[]{root.val, 0};
        }

        int[] left;
        int[] right;

        // 获取左节点打家劫舍后的结果
        left = robDFS(root.left);

        // 获取右节点打家劫舍后的结果
        right = robDFS(root.right);

        int[] result = new int[2];

        // 左节点中上一个未打劫的金额+右节点中上一个未打劫的金额+当前节点的金额（因为只有左右子节点都未打劫时，当前节点才能打劫）
        int add = left[1] + right[1] + root.val;
        // 上一个打劫的金额，等于（左打劫金额+右打劫金额，左未打劫金额+右打劫金额，左打劫金额+右未打劫金额）中的最大值（当左右节点中有一个打劫了，当前节点就不能进行打劫）
        int lastCheck =
                Math.max(Math.max(left[0] + right[0], left[0] + right[1]), left[1] + right[0]);
        int lastUncheck;

        // 若上一个打劫后的金额大于add（也就是上一个未打劫的金额+当前节点金额）
        if (lastCheck > add) {
            // 因为到此相当于跳过此节点，所以另上一个未打劫的金额等于上一个打劫的金额
            lastUncheck = lastCheck;
        } else {
            // 到此说明选择此节点后的值比较大，则另上一个打劫后的金额等于相加后的值（add），上一个未打劫的金额则等于上一个打劫后的金额
            lastUncheck = lastCheck;
            lastCheck = add;
        }

        result[0] = lastCheck;
        result[1] = lastUncheck;

        return result;
    }
}

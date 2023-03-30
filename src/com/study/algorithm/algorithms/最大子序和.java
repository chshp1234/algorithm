package com.study.algorithm.algorithms;

import org.junit.Test;

//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//
// 
//
//示例 1：
//
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
//示例 2：
//
//输入：nums = [1]
//输出：1
//示例 3：
//
//输入：nums = [0]
//输出：0
//示例 4：
//
//输入：nums = [-1]
//输出：-1
//示例 5：
//
//输入：nums = [-100000]
//输出：-100000
// 
//
//提示：
//
//1 <= nums.length <= 3 * 104
//-105 <= nums[i] <= 105
// 
//
//进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/maximum-subarray
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最大子序和 {

    @Test
    public void 最大子序和() {

        System.out.println("最大子序和：" + maxSubArray(new int[]{4, 3, 3, 1, 1, 1, 1, 1, 1, 1}));
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        // 结果
        int result = nums[0];
        // 正数合，负数合
        int positive = -1, negative = 0;
        if (result < 0) {
            negative = result;
        } else {
            positive = result;
        }

        for (int i = 1, l = nums.length; i < l; i++) {
            if (nums[i] < 0) {
                // 如果当前数小于0，负数合相加
                negative += nums[i];
                // 判断最大值（用于比较全负数的数组）
                result = Math.max(nums[i], result);
            } else {
                // 如果负数合小于0（表示有负数计算）
                if (negative < 0 && (positive + negative < 0)) {
                    // 如果正数合的绝对值小于负数合的绝对值，
                    // 则舍弃次数之前的计算，重新从此位置开始计算，
                    // 首先判断当前的正数合和当前结果的最大值
                    result = Math.max(positive, result);
                    positive = 0;
                    negative = 0;
                } else if (negative < 0) {
                    // 如果正数合的绝对值大于于负数合的绝对值，
                    // 则把之前的正数合和负数合相加（此值大于0），
                    // 首先判断并保存之前的正数合和结果的最大值
                    result = Math.max(positive, result);
                    positive += negative;
                    negative = 0;
                }
                // 正数累加
                positive += nums[i];
            }
        }

        // 是否有正数
        return positive >= 0 ? Math.max(positive, result) : result;
    }

    public int maxSubArrayByDp(int[] nums) {
        //动态规划
        //设dp[i]表示，以下标i为结尾（选中），最长子序和
        //那么,如果dp[i-1]>0,dp[i]=dp[i-1]+nums[i];如果dp[i-1]<=0,那么dp[i]=nums[i]
        int len    = nums.length;
        int dp     = nums[0];
        int result = dp;
        for (int i = 1; i < len; i++) {
            if (dp <= 0) {
                dp = nums[i];
            } else {
                dp = dp + nums[i];
            }
            result = Math.max(dp, result);
        }
        return result;
    }

    public class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    public int maxSubArrayByLeetCode(int[] nums) {
        //分治
        //这个分治方法类似于「线段树求解最长公共上升子序列问题」的 pushUp 操作。
        //也许读者还没有接触过线段树，没有关系，方法二的内容假设你没有任何线段树的基础。
        //当然，如果读者有兴趣的话，推荐阅读线段树区间合并法解决多次询问的「区间最长连续上升序列问题」和「区间最大子段和问题」，还是非常有趣的。
        //
        //我们定义一个操作 get(a, l, r) 表示查询 a 序列 [l,r] 区间内的最大子段和，
        //那么最终我们要求的答案就是 get(nums, 0, nums.size() - 1)。
        //如何分治实现这个操作呢？对于一个区间 [l,r]，我们取 m = (l+r)/2，对区间 [l,m] 和 [m+1,r] 分治求解。
        //当递归逐层深入直到区间长度缩小为 1 的时候，递归「开始回升」。
        //这个时候我们考虑如何通过 [l,m] 区间的信息和 [m+1,r] 区间的信息合并成区间 [l,r] 的信息。
        //最关键的两个问题是：我们要维护区间的哪些信息呢？我们如何合并这些信息呢？
        //对于一个区间 [l,r][l,r]，我们可以维护四个量：
        //lSum 表示 [l,r] 内以 l 为左端点的最大子段和
        //rSum 表示 [l,r] 内以 r 为右端点的最大子段和
        //mSum 表示 [l,r] 内的最大子段和
        //iSum 表示 [l,r] 的区间和
        //以下简称 [l,m] 为 [l,r] 的「左子区间」，[m+1,r] 为 [l,r] 的「右子区间」。
        //我们考虑如何维护这些量呢（如何通过左右子区间的信息合并得到 [l,r] 的信息）？
        //对于长度为 1 的区间 [i,i]，四个量的值都和 nums[i] 相等。对于长度大于 1 的区间：
        //
        //1.首先最好维护的是 iSum，区间 [l,r] 的 iSum 就等于「左子区间」的 iSum 加上「右子区间」的 iSum。
        //2.对于 [l,r] 的 lSum，存在两种可能，它要么等于「左子区间」的 lSum，要么等于「左子区间」的 iSum 加上「右子区间」的 lSum，二者取大。
        //3.对于 [l,r] 的 rSum，同理，它要么等于「右子区间」的 rSum，要么等于「右子区间」的 iSum 加上「左子区间」的 rSum，二者取大。
        //4.当计算好上面的三个量之后，就很好计算 [l,r] 的 mSum 了。我们可以考虑 [l,r] 的 mSum 对应的区间是否跨越 m——它可能不跨越 m，
        //也就是说 [l,r] 的 mSum 可能是「左子区间」的 mSum 和 「右子区间」的 mSum 中的一个；
        //它也可能跨越 m，可能是「左子区间」的 rSum 和 「右子区间」的 lSum 求和。三者取大。这样问题就得到了解决。
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    public Status getInfo(int[] a, int l, int r) {
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        int    m    = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }

    public Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }
}

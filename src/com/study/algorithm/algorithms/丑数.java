package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class 丑数 {
    // 编写一个程序判断给定的数是否为丑数。
    //
    // 丑数就是只包含质因数 2, 3, 5 的正整数。
    //
    // 示例 1:
    //
    // 输入: 6
    // 输出: true
    // 解释: 6 = 2 × 3
    // 示例 2:
    //
    // 输入: 8
    // 输出: true
    // 解释: 8 = 2 × 2 × 2
    // 示例 3:
    //
    // 输入: 14
    // 输出: false
    // 解释: 14 不是丑数，因为它包含了另外一个质因数 7。
    // 说明：
    //
    // 1 是丑数。
    // 输入不会超过 32 位有符号整数的范围: [−231,  231 − 1]。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/ugly-number
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    @Test
    public void 丑数() {
        int num = 8;
        System.out.printf("数字%d是否是丑数：" + ugly(num), num);

        int n = 1690;
        System.out.printf("\n" + "第%d个丑数:" + nthUglyNumberByHeap(n), n);
    }

    public boolean ugly(int num) {
        // 根据丑数定义，依次除以2，3，5，直到余数不为0时（不能整除时），判断结果是否为1
        // 小于等于0的数都不是

        if (num <= 0) {
            return false;
        }
        while (num % 2 == 0) {
            num = num / 2;
        }
        while (num % 3 == 0) {
            num = num / 3;
        }
        while (num % 5 == 0) {
            num = num / 5;
        }
        return num == 1;
    }

    public int nthUglyNumber(int n) {
        // 暴力法
        // 判断每个数是否是丑数，如果不是，当前数+1

        int num = 1;
        for (int i = 0; i < n; i++) {
            while (!ugly(num)) {
                num++;
            }
            num++;
        }
        return num - 1;
    }

    public int nthUglyNumberByHeap(int n) {

        // 堆
        // 从优先队列中取出最小的数，依次*2、*3、*5，再把结果加入优先队列中
        // 重复上述操作n次后，再取出的数即为第 n 个丑数。
        // 注意，防止重复往堆中添加丑数，应用一个set集合，判断该数是否已添加过

        PriorityQueue<Long> heap = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();

        int[] ugly = {2, 3, 5};

        heap.offer((long) 1);
        set.add((long) 1);

        for (int i = 1; i < n; i++) {
            long top = heap.poll();

            for (int k = 0; k < 3; k++) {
                if (set.add(top * ugly[k])) {
                    heap.offer(top * ugly[k]);
                }
            }
        }
        long result = heap.peek();
        return (int) result;
    }

    public int nthUglyNumberByDp(int n) {

        // 动态规划
        // 方法一中的预计算操作较为繁琐，可以通过动态规划优化。
        //
        // 让我们从数组中只包含一个丑数数字 1 开始，使用三个指针 i2, i3和 i5，标记所指向丑数要乘以的因子(*2、*3、*5)。
        //
        // 算法很简单：在 2×nums[i2]，3×nums[i3] 和 5×nums[i5] 选出最小的丑数并添加到数组中。
        // 并将该丑数对应的因子指针往前走一步。重复该步骤直到计算完 1690 个丑数。

        // 算法：
        //
        // 预计算 1690 个丑数：
        // 初始化数组 nums 和三个指针 i2，i3，i5 。
        // 循环计算所有丑数。每一步：
        // 在 nums[i2] * 2，nums[i3] * 3 和 nums[i5] * 5 选出最小的数字添加到数组 nums 中。
        // 将该数字对应的因子指针向前移动一步。
        // 在数组中返回所需的丑数。
        //
        // 作者：LeetCode
        // 链接：https://leetcode-cn.com/problems/ugly-number-ii/solution/chou-shu-ii-by-leetcode/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        int[] nums = new int[n];
        nums[0] = 1;
        int ugly, i2 = 0, i3 = 0, i5 = 0;

        for (int i = 1; i < n; ++i) {
            ugly = Math.min(Math.min(nums[i2] * 2, nums[i3] * 3), nums[i5] * 5);
            nums[i] = ugly;

            if (ugly == nums[i2] * 2) ++i2;
            if (ugly == nums[i3] * 3) ++i3;
            if (ugly == nums[i5] * 5) ++i5;
        }

        return nums[n - 1];
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        //同1，优先队列（最小堆），哈希表（存储重复计算值）
        PriorityQueue<Long> heap = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();

        heap.offer((long) 1);
        set.add((long) 1);

        for (int i = 1; i < n; i++) {
            long top = heap.poll();

            for (int prime : primes) {
                if (set.add(top * prime)) {
                    heap.offer(top * prime);
                }
            }
        }
        long result = heap.peek();
        return (int) result;
    }

    //同“丑数Ⅱ”中的动态规划解法
    public int nthSuperUglyNumberByDp(int n, int[] primes) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int m = primes.length;
        int[] pointers = new int[m];
        Arrays.fill(pointers, 1);
        for (int i = 2; i <= n; i++) {
            int[] nums = new int[m];
            int minNum = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                nums[j] = dp[pointers[j]] * primes[j];
                minNum = Math.min(minNum, nums[j]);
            }
            dp[i] = minNum;
            for (int j = 0; j < m; j++) {
                if (minNum == nums[j]) {
                    pointers[j]++;
                }
            }
        }
        return dp[n];
    }

}

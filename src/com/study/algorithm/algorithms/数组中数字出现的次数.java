package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 数组中数字出现的次数 {

    @Test
    public void 数组中数字出现的次数() {
        int[] ints = {1, 2, 10, 4, 1, 4, 3, 3};
        System.out.println("数组中数字出现的次数:" + Arrays.toString(singleNumbers(ints)));
    }

    /**
     * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
     *
     * <p>
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [4,1,4,6] 输出：[1,6] 或 [6,1] 示例 2：
     *
     * <p>输入：nums = [1,2,10,4,1,4,3,3] 输出：[2,10] 或 [10,2]
     *
     * <p>来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[] singleNumbers(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        int[] result = new int[2];

        Set<Integer> set = new HashSet<>();

        for (int i = 0, l = nums.length; i < l; i++) {
            if (!set.add(nums[i])) {
                set.remove(nums[i]);
            }
        }
        Object[] objects = set.toArray();
        result[0] = (int) objects[0];
        result[1] = (int) objects[1];

        return result;
    }

    //
    // 答案很简单：全员进行异或操作即可。考虑异或操作的性质：对于两个操作数的每一位，相同结果为 0，不同结果为 1。那么在计算过程中，
    // 成对出现的数字的所有位会两两抵消为 0，最终得到的结果就是那个出现了一次的数字。
    //
    // 那么这一方法如何扩展到找出两个出现一次的数字呢？
    // 如果我们可以把所有数字分成两组，使得：
    // 1.两个只出现一次的数字在不同的组中；
    // 2.相同的数字会被分到相同的组中。
    //
    // 那么对两个组分别进行异或操作，即可得到答案的两个数字。这是解决这个问题的关键。
    // 那么如何实现这样的分组呢？
    //
    // 记这两个只出现了一次的数字为 a 和 b，那么所有数字抑或的结果就等于 a 和 b 异或的结果，我们记为 x。
    // 如果我们把 x 写成二进制的形式xk xk−1 ⋯x2 x1 x0 ，其中xi∈{0,1}，我们考虑一下 xi=0 和 xi=1 的含义是什么？
    // 它意味着如果我们把 a 和 b 写成二进制的形式，ai 和bi 的关系——xi=1 表示 ai 和 bi 不等，xi=0 表示 ai 和 bi相等。
    // 假如我们任选一个不为 0 的 xi ，按照第 i 位给原来的序列分组，如果该位为 0 就分到第一组，否则就分到第二组，这样就能满足以上两个条件，为什么呢？
    //
    // 首先，两个相同的数字的对应位都是相同的，所以一个被分到了某一组，另一个必然被分到这一组，所以满足了条件 2。
    //
    // 这个方法在 xi=1 的时候 a 和 b 不被分在同一组，因为 xi=1 表示 ai 和 bi 不等，
    // 根据这个方法的定义「如果该位为 0 就分到第一组，否则就分到第二组」可以知道它们被分进了两组，所以满足了条件 1。
    //
    // 在实际操作的过程中，我们拿到序列的抑或和 x 之后，对于这个「位」是可以任取的，只要它满足 xi=1。
    // 但是为了方便，这里的代码选取的是「不为 0的最低位」，当然你也可以选择其他不为 0 的位置。
    //
    // 至此，答案已经呼之欲出了。
    //
    // 作者：LeetCode-Solution
    // 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/solution/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-by-leetcode/
    // 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    //
    /*public int[] singleNumbers(int[] nums) {
            //先对所有数字进行一次异或，得到两个出现一次的数字的异或值。
            //
            //在异或结果中找到任意为 1 的位。
            //
            //根据这一位对所有的数字进行分组。
            //
            //在每个组内进行异或操作，得到两个数字。
            int sum = 0;
            int[] res = new int[2];
            for(int num : nums){
                sum ^= num;
            }

            //获取 sum二进制最低位‘1’
            （1）int lowbit = sum & (-sum);
    //      （2）int lowbit = sum & (~sum+1);
    //      （3）int lowbit = sum&(sum^(sum-1));
    //      （4）while ((div & sum) == 0) {
    //              div <<= 1;
    //          }

            for(int num : nums){
                if((num & lowbit) == 0){
                    res[0] ^= num;
                }else{
                    res[1] ^= num;
                }
            }
            return res;
        }*/
}

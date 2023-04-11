package com.study.algorithm.algorithms.algorithmsKT

//给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
//
//以任意顺序返回这两个数字均可。
//
//示例 1:
//
//输入: [1]
//输出: [2,3]
//示例 2:
//
//输入: [2,3]
//输出: [1,4]
//提示：
//
//nums.length <= 30000
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/missing-two-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 消失的两个数字 {
    fun missingTwo(nums: IntArray): IntArray {
        //哈希表
        //因为数组只包含1..n,那么可以用一个n+1大小的数组记录每个数字
        //遍历数组,当数字出现在nums中时,令哈希表中的对应下标的数字为1表示出现过
        //再遍历哈希表,当数字为0时,说明此下标对应的数字没出现在原数组中,那么加入结果
        val result = IntArray(2)
        val map = IntArray(nums.size + 3)

        for (n in nums) {
            map[n] = 1
        }

        var index = 0
        for (n in 1 until map.size) {
            if (map[n] == 0) {
                result[index++] = n
            }
        }

        return result
    }

    //假设数组 nums 中消失的两个数字分别是 x1,x2。
    // 如果把上述 2n−2 个数字全部异或起来，得到结果 x，那么一定有：
    //x=x1 ⊕ x2 ,其中⊕ 表示异或运算。
    // 这是因为 nums 中出现两次的元素都会因为异或运算的性质 a⊕b⊕b=a 抵消掉，那么最终的结果就只剩下x1和 x2 的异或和。
    //
    //显然 x!=0，因为如果 x=0，那么说明 x1 =x2 ，这样 x1 和x2 就不是在上述 2n−2 个数字中只出现一次的数字了。
    // 因此，我们可以使用位运算 x&-x 取出xx 的二进制表示中最低位那个 1，设其为第 l 位，
    // 那么x1 和 x2中的某一个数的二进制表示的第 l 位为 0，另一个数的二进制表示的第 l 位为 1。
    // 在这种情况下，x1 ⊕x2的二进制表示的第 l 位才能为 1。
    //
    //这样一来，我们就可以把从 1 到 n 的所有整数分成两类，
    // 其中一类包含所有二进制表示的第 l 位为 0 的数，另一类包含所有二进制表示的第 l 位为 1 的数。
    // 可以发现：
    //对于任意一个在数组 nums 中出现一次的数字，这些数字在上述 2n−2 个数字中出现两次，两次出现会被包含在同一类中；
    //对于任意一个在数组 nums 中消失的数字，即 x1和 x2，这些数字在上述 2n−2 个数字中出现一次，会被包含在不同类中。
    //
    //因此，如果我们将每一类的元素全部异或起来，那么其中一类会得到 x1，另一类会得到 x2。
    // 这样我们就找出了这两个只出现一次的元素。
    //
    //public int[] missingTwo(int[] nums) {
    //        int xorsum = 0;
    //        int n = nums.length + 2;
    //        for (int num : nums) {
    //            xorsum ^= num;
    //        }
    //        for (int i = 1; i <= n; i++) {
    //            xorsum ^= i;
    //        }
    //        // 防止溢出
    //        int lsb = (xorsum == Integer.MIN_VALUE ? xorsum : xorsum & (-xorsum));
    //        int type1 = 0, type2 = 0;
    //        for (int num : nums) {
    //            if ((num & lsb) != 0) {
    //                type1 ^= num;
    //            } else {
    //                type2 ^= num;
    //            }
    //        }
    //        for (int i = 1; i <= n; i++) {
    //            if ((i & lsb) != 0) {
    //                type1 ^= i;
    //            } else {
    //                type2 ^= i;
    //            }
    //        }
    //        return new int[]{type1, type2};
    //    }
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode.cn/problems/missing-two-lcci/solution/xiao-shi-de-liang-ge-shu-zi-by-leetcode-zuwq3/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    //寻求前缀和，找到缺失的两个数的和值。
    //再对和值进行对半计算得到对半计算值，缺失数中某一个必在对半计算值内。
    //同理求该值，也可以通过前缀和进行，得到值即为其中缺失数1。
    //再根据和值计算缺失数2。
    //public int[] missingTwo(int[] nums) {
    //        int n = nums.length + 2, cur = n * (1 + n) / 2;
    //        for (int x : nums) cur -= x;
    //        int sum = cur, t = cur / 2;
    //        cur = t * (1 + t) / 2;
    //        for (int x : nums) {
    //            if (x <= t) cur -= x;
    //        }
    //        return new int[]{cur, sum - cur};
    //    }
    //
    //作者：AC_OIer
    //链接：https://leetcode.cn/problems/missing-two-lcci/solution/by-ac_oier-pgeh/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
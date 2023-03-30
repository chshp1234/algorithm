package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
//
//
// 示例 1：
//
// 输入：nums = [3,4,3,3]
// 输出：4
// 示例 2：
//
// 输入：nums = [9,1,7,9,7,9,7]
// 输出：1
//
//
// 限制：
//
// 1 <= nums.length <= 10000
// 1 <= nums[i] < 2^31
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 数组中数字出现的次数II {

    @Test
    public void 数组中数字出现的次数() {
        int[] ints = {3, 3, 3, 4};
        System.out.println("数组中数字出现的次数:" + singleNumberBySort(ints));
    }

    public int singleNumber(int[] nums) {
        // 哈希表记录每个数字出现的次数
        // 若出现三次，则从哈希表中移出
        // 最后哈希表中将会剩下一个值

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0, len = nums.length; i < len; i++) {
            if (map.getOrDefault(nums[i], 0) == 2) {
                map.remove(nums[i]);
            } else {
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            }
        }

        return (int) map.keySet().toArray()[0];
    }

    public int singleNumberBySort(int[] nums) {
        // 先对数组进行排序
        // 若元素前后相同，则让下标+3（因为其他相同数字都出现3次，所以下标+3后，跳至下一个不同的数字）
        // 遍历到数组最后，或者前后数字不一样时，则跳出循环，并返回此下标的元素
        Arrays.sort(nums);

        int len = nums.length - 1;
        int index = 0;
        while (index < len) {
            if (nums[index] == nums[index + 1]) {
                index += 3;
            } else {
                break;
            }
        }
        return nums[index];
    }

    public int singleNumberByBinary(int[] nums) {
        // 考虑数字的二进制形式，对于出现三次的数字，各 二进制位 出现的次数都是 3 的倍数。
        // 因此，统计所有数字的各二进制位中 1 的出现次数，并对 3 求余，结果则为只出现一次的数字。

        // 各二进制位的 位运算规则相同 ，因此只需考虑一位即可。
        // 对于所有数字中的某二进制位 1 的个数，存在 3 种状态，即对 3 余数为 0,1,2 。
        //
        // 若输入二进制位 1 ，则状态按照以下顺序转换；
        // 若输入二进制位 0 ，则状态不变。
        // 0→1→2→0→⋯
        //
        // 由于二进制只能表示 0,1 ，因此需要使用两个二进制位来表示 3 个状态。设此两位分别为 two , one ，则状态转换变为：
        // 00→01→10→00→⋯
        //
        // 以上是对数字的二进制中 “一位” 的分析，而 int 类型的其他 31 位具有相同的运算规则，因此可将以上公式直接套用在 32 位数上。
        //
        // 遍历完所有数字后，各二进制位都处于状态 00 和状态 01 （取决于 “只出现一次的数字” 的各二进制位是 11 还是 00 ），
        // 而此两状态是由 one来记录的（此两状态下 twos 恒为 0 ），因此返回 ones 即可。
        //
        // 作者：jyd
        // 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/solution/mian-shi-ti-56-ii-shu-zu-zhong-shu-zi-chu-xian-d-4/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        /*int ones = 0, twos = 0;
        for(int num : nums){
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;*/

        // 建立一个长度为 32 的数组 counts ，通过以上方法可记录所有数字的各二进制位的 1 的出现次数。
        // 将 counts 各元素对 3 求余，则结果为 “只出现一次的数字” 的各二进制位。
        // 利用 左移操作 和 或运算 ，可将 counts 数组中各二进位的值恢复到数字 res 上（循环区间是 i∈[0,31] ）。
        //
        // 作者：jyd
        // 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/solution/mian-shi-ti-56-ii-shu-zu-zhong-shu-zi-chu-xian-d-4/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        int[] counts = new int[32];
        for (int num : nums) {
            for (int j = 0; j < 32; j++) {
                counts[j] += num & 1;
                num >>>= 1;
            }
        }
        int res = 0, m = 3;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res |= counts[31 - i] % m;
        }
        return res;
    }
}

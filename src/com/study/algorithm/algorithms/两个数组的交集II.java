package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 两个数组的交集II {

    @Test
    public void 两个数组的交集II() {
        System.out.println(
                "两个数组的交集II:"
                        + Arrays.toString(
                                intersectByHashMap(new int[] {1, 2, 2, 1}, new int[] {2})));
    }

    // 给定两个数组，编写一个函数来计算它们的交集。
    //
    // 示例 1:
    //
    // 输入: nums1 = [1,2,2,1], nums2 = [2,2]
    // 输出: [2,2]
    // 示例 2:
    //
    // 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    // 输出: [4,9]
    // 说明：
    //
    // 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
    // 我们可以不考虑输出结果的顺序。
    // 进阶:
    //
    // 如果给定的数组已经排好序呢？你将如何优化你的算法？
    // 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
    // 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int[] intersectByTwoPointer(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> result = new ArrayList<>();

        // 两个指针，分别指向两个数组下标
        int i = 0, j = 0;

        // 遍历到其中一个数组结尾时，跳出
        while (i < nums1.length && j < nums2.length) {
            // 由于此时两个数组都已经是有序的
            // ·当两值相等时，加入结果集，对两个指针+1
            // ·当num1中的值大于num2中的值时，对指向num2的指针+1
            // ·当num1中的值小于num2中的值时，对指向num1的指针+1
            if (nums1[i] == nums2[j]) {
                result.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }

        // 输出到结果数组中
        int[] res = new int[result.size()];
        int index = 0;
        for (int num : result) {
            res[index++] = num;
        }

        return res;
    }

    public int[] intersectByHashMap(int[] nums1, int[] nums2) {
        // 由于同一个数字在两个数组中都可能出现多次，因此需要用哈希表存储每个数字出现的次数。对于一个数字，其在交集中出现的次数等于该数字在两个数组中出现次数的最小值。
        //
        // 首先遍历第一个数组，并在哈希表中记录第一个数组中的每个数字以及对应出现的次数，然后遍历第二个数组，对于第二个数组中的每个数字，如果在哈希表中存在这个数字，则将该数字添加到答案，并减少哈希表中该数字出现的次数。
        //
        // 为了降低空间复杂度，首先遍历较短的数组并在哈希表中记录每个数字以及对应出现的次数，然后遍历较长的数组得到交集。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/solution/liang-ge-shu-zu-de-jiao-ji-ii-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        if (nums1.length > nums2.length) {
            return intersectByHashMap(nums2, nums1);
        }

        // 将较小的数组数据加入哈希表中，降低空间复杂度
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }
        int[] intersection = new int[nums1.length];
        int index = 0;

        // 遍历第二个数组，判断是否在哈希表中
        for (int num : nums2) {
            // count表示该数在第一个数组中出现的次数（剩余次数）
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                // 如果次数大于0，则加入目标数组，并且讲此数-1（代表还剩余几个）
                intersection[index++] = num;
                count--;
                if (count > 0) {
                    map.put(num, count);
                } else {
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }
}

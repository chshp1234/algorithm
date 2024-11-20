package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 两个数组的交集 {

    @Test
    public void 两个数组的交集() {
        System.out.println("两个数组的交集:" + Arrays.toString(intersectionByBinarySearch(new int[]{2}, new int[]{1, 2, 2, 1})));
    }

    //给定两个数组，编写一个函数来计算它们的交集。
    //
    //
    //
    //示例 1：
    //
    //输入：nums1 = [1,2,2,1], nums2 = [2,2]
    //输出：[2]
    //示例 2：
    //
    //输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    //输出：[9,4]
    //
    //
    //说明：
    //
    //输出结果中的每个元素一定是唯一的。
    //我们可以不考虑输出结果的顺序。
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int[] intersectionByMap(int[] nums1, int[] nums2) {

        // Set，可方便判断元素是否存在
        Set<Integer> set = new HashSet<>();

        for (int i = 0, l = nums1.length; i < l; i++) {
            set.add(nums1[i]);
        }

        List<Integer> temp = new ArrayList<>();

        for (int i = 0, l = nums2.length; i < l; i++) {
            // 如果集合中包含目标值
            if (set.contains(nums2[i])) {
                // 加入结果集
                temp.add(nums2[i]);
                // 移出集合，重复元素不添加（这里也可以再使用一个Set集合加入另一个数组的数据）
                set.remove(nums2[i]);
            }
        }

        // 通过stream使list转换成int[]
        return temp.stream().mapToInt(Integer::valueOf).toArray();
    }

    public int[] intersectionByBinarySearch(int[] nums1, int[] nums2) {
        // 思路：先对两数组排序，再使用二分查找.
        // 因为返回的结果不含重复元素，所以，对第一个数组进行排序后，可以快速判重（判断当前值num[i]和上一个值num[i-1]是否相等）.
        // 加入一个指针进行剪枝判断
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> result = new ArrayList<>();

        int low = 0;
        int len = nums2.length;

        for (int i = 0, l = nums1.length; i < l; i++) {
            // 去重
            if (i > 0 && nums1[i - 1] == nums1[i]) {
                continue;
            }

            // 使用内置api进行二分查找，其中low为上次一次查找时nums1中值在num2中的位置（应该存在的位置，由于此时的两个数组都是有序的，所以下一次遍历时，num1中的值肯定大于等于该位置的值）
            // 结果index代表：
            // ·如果目标数组存值该元素，则为该元素的位置下标
            // ·如果目标数组不存在该元素，切该元素大于所有目标数组的值，则为目标数组的大小
            // ·如果目标数组不存在该元素，则为目标数组中第一个大于该值的元素位置，取负数后-1
            int index = Arrays.binarySearch(nums2, low, len, nums1[i]);

            // 表示遍历到此时nums1中的剩余所有元素都大于nums2，则直接退出
            if (index == len) {
                break;
            }

            if (index >= 0) {
                // 存在该值，添加，并使low等于该位置
                result.add(nums1[i]);
                low = index;
            } else {
                // 如果不存在，则根据api求值公式，此位置为-index - 1(如果将要插入该元素，则该元素应插入的位置)
                low = -index - 1;
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


    //排序，双指针
    //先对两个数组进行排序，再用两个变量i1、i2指向两个数组下标，对数组进行遍历判断，并用一个数last记录上一个加入结果的数（去重）
    //1.如果两个元素相同，两个下标+1，判断是否等于上一个加入的数last，如果不相等，则加入结果中，并将last更新为当前元素
    //2.如果nums1[i1] > nums2[i2]，i1++；否则i2++;
    //遍历到其中一个数组结尾时，即可退出循环，并将当前的列表转换成数组，返回
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i1 = 0, i2 = 0, last = -1;
        List<Integer> list = new ArrayList<>();

        while (i1 < nums1.length && i2 < nums2.length) {
            if (nums1[i1] == nums2[i2]) {
                if (last != nums1[i1]) {
                    list.add(nums1[i1]);
                    last = nums1[i1];
                }
                i1++;
                i2++;
            } else if (nums1[i1] > nums2[i2]) {
                i2++;
            } else {
                i1++;
            }
        }

        int[] res = new int[list.size()];
        i1 = 0;
        for (int i : list) {
            res[i1++] = i;
        }
        return res;
    }
}

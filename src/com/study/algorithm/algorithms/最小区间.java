package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Deprecated
public class 最小区间 {
    @Test
    public void 最佳观光组合() {

        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        list1.add(4);
        list1.add(10);
        list1.add(15);
        list1.add(24);
        list1.add(26);
        list2.add(0);
        list2.add(9);
        list2.add(12);
        list2.add(20);
        list3.add(5);
        list3.add(18);
        list3.add(22);
        list3.add(30);
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        System.out.println("最佳观光组合:" + Arrays.toString(smallestRange(lists)));
    }

    // 你有 k 个升序排列的整数数组。找到一个最小区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
    //
    // 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
    //
    // 示例 1:
    //
    // 输入:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
    // 输出: [20,24]
    // 解释:
    // 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
    // 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
    // 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
    // 注意:
    //
    // 给定的列表可能包含重复元素，所以在这里升序表示 >= 。
    // 1 <= k <= 3500
    // -105 <= 元素的值 <= 105
    // 对于使用Java的用户，请注意传入类型已修改为List<List<Integer>>。重置代码模板后可以看到这项改动。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/smallest-range-covering-elements-from-k-lists
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int[] smallestRange(List<List<Integer>> nums) {

        int low = nums.get(0).get(nums.get(0).size() - 1), len = nums.size();
        List<Integer> temp;

        for (int i = 1; i < len; i++) {

            temp = nums.get(i);
            int listMax = temp.get(temp.size() - 1);
            if (listMax < low) {
                low = listMax;
            }
        }

        int high = 0;

        for (int i = 0; i < len; i++) {

            temp = nums.get(i);

            int listMin = temp.get(temp.size() - 1);

            for (int j = temp.size() - 1; j >= 0; j--) {
                if (temp.get(j) > low) {
                    listMin = temp.get(j);
                } else {
                    break;
                }
            }

            if (listMin > high) {
                high = listMin;
            }
        }

        return new int[] {low, high};
    }
}

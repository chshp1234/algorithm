package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 三角形最小路径和 {

    @Test
    public void 三角形最小路径和() {

        /*List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(2);
        lists.add(list);
        list = new ArrayList<>();
        list.add(3);
        list.add(4);
        lists.add(list);
        list = new ArrayList<>();
        list.add(6);
        list.add(5);
        list.add(7);
        lists.add(list);
        list = new ArrayList<>();
        list.add(4);
        list.add(1);
        list.add(8);
        list.add(3);
        lists.add(list);*/

        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(-1);
        lists.add(list);
        list = new ArrayList<>();
        list.add(-2);
        list.add(-3);
        lists.add(list);
        System.out.println("三角形最小路径和:" + minimumTotal(lists));
    }

    // 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
    //
    // 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
    //
    //
    //
    // 例如，给定三角形：
    //
    // [
    //     [2],
    //    [3,4],
    //   [6,5,7],
    //  [4,1,8,3]
    // ]
    // 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
    //
    //
    //
    // 说明：
    //
    // 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/triangle
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int minimumTotal(List<List<Integer>> triangle) {
        // 动态规划+空间优化
        // f(i)表示从三角形顶部开始走到底部第(i)下标时的最小值
        // 由于每一步只能移动到下一行「相邻的节点」上，因此走到i的位置的最小值由上一层中第i-1和第i的值决定
        // 注意：当i=0或者i=len时（也就是最左侧和最右侧），情况有变
        // 当我们在最左侧时，我们只能从上一行的最左侧移动过来。也就是只由上一行的位置0处决定.
        // 当我们在最右侧时，我们只能从上一行的最右侧移动过来。也就是只由上一行的位置i-1处决定.
        int len = triangle.size();
        int[] result = new int[len];
        result[0] = triangle.get(0).get(0);

        // 临时变量，保存上一行中第i项的值（由于我们比对的是上一层中第i-1和第i的值，所以当第i项的值变动时，会影响i+1项值的计算。）
        int lastTemp;

        for (int i = 1; i < len; i++) {
            lastTemp = result[0];

            // 最左侧的值
            result[0] = result[0] + triangle.get(i).get(0);
            for (int j = 1; j < i /*triangle.get(i).size() - 1*/; j++) {
                int temp = result[j];
                // 第i项的值为上一行中第i-1项和i项的值中最小值加上三角形中当前位置i处的值
                result[j] = Math.min(lastTemp, result[j]) + triangle.get(i).get(j);
                lastTemp = temp;
            }

            // 优化，从每行中最右侧开始往左遍历，可省去零时变量的保存
            /*for (int j = i - 1; j > 0; --j) {
                result[j] = Math.min(result[j - 1], result[j]) + triangle.get(i).get(j);
            }*/

            // 最右侧的值
            result[i] = lastTemp + triangle.get(i).get(i);
        }

        // 从结果中找出最小值
        int min = result[0];
        for (int i = 1; i < len; i++) {
            min = Math.min(result[i], min);
        }

        return min;
    }
}

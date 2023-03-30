package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.PriorityQueue;

//给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
//
//注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
//
// 
//
//示例 1：
//
//输入：nums = [10,2]
//输出："210"
//示例 2：
//
//输入：nums = [3,30,34,5,9]
//输出："9534330"
//示例 3：
//
//输入：nums = [1]
//输出："1"
//示例 4：
//
//输入：nums = [10]
//输出："10"
// 
//
//提示：
//
//1 <= nums.length <= 100
//0 <= nums[i] <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/largest-number
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最大数 {
    @Test
    public void 最大数() {
        System.out.println("最大数：" + largestNumber(new int[]{3, 9, 34, 5, 30}));
    }

    public String largestNumber(int[] nums) {
        //排序
        //比较两个数不同的拼接顺序的结果，进而决定它们在结果中的排列顺序。
        if (nums.length == 1) {
            return String.valueOf(nums[0]);
        }
        int len = nums.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>(len, (x, y) -> {
            long sx = 10, sy = 10;
            while (sx <= x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            return (int) (-sy * x - y + sx * y + x);
        });

        //通过字符串拼接后再转换成数字
       /* PriorityQueue<Integer> queue = new PriorityQueue<>(len,
                                                           (t1, t2) -> Long.compare(Long.parseLong(
                                                                   t2 + String.valueOf(t1)),
                                                                                    Long.parseLong(
                                                                                            t1 + String.valueOf(
                                                                                                    t2))
                                                                                   )
        );*/
        for (int num : nums) {
            queue.offer(num);
        }
        if (queue.peek() == 0) {
            return "0";
        }
        StringBuilder stringBuilder = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            stringBuilder.append(queue.poll());
        }
        return stringBuilder.toString();
    }
}

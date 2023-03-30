package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

//给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
//
//示例 1:
//
//输入: [1,2,1]
//输出: [2,-1,2]
//解释: 第一个 1 的下一个更大的数是 2；
//数字 2 找不到下一个更大的数；
//第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
//注意: 输入数组的长度不会超过 10000。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/next-greater-element-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 下一个更大元素II {
    @Test
    public void 下一个更大元素II() {
        int[] newInterval = new int[]{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 100};

        System.out.println("下一个更大元素II：" + Arrays.toString(nextGreaterElements(newInterval)));
    }

    public int[] nextGreaterElements(int[] nums) {
        //单调栈
        //栈中存储元素的下标，并且栈中元素根据原数组是单调递减的。
        //遍历时，若元素比栈顶元素（根据下标在原数组中对应的元素）小或等于，则将下标加入栈中；
        //若元素比栈顶元素大，说明，此元素时栈顶元素的下一个更大的元素，则弹出栈顶元素，在返回数组中将此值放入栈顶元素下标位置，
        //循环弹出栈顶元素，直到此元素比栈顶元素小或等于，或者栈为空，再讲此元素加入栈中，等待查找下一个更大的元素来替换此元素。
        //因为是循环数组，遍历到结尾时，继续从头开始，直到栈內只存在最大元素（一个数组中，至少有一个元素的值时最大的）。

        //分三步进行以上操作：
        int   len    = nums.length;
        int[] result = new int[len];
        if (len == 0) {
            return result;
        }
        Deque<Integer> deque = new LinkedList<>();

        int tempIndex = 0;
        deque.offer(0);
        int max = nums[0];

        //1.第一趟遍历，替换所有递增的元素，加入所有递减元素到栈。
        for (int i = 1; i < len; i++) {
            if (nums[tempIndex] < nums[i]) {
                //查找栈中最大值
                max = Math.max(nums[i], max);
                do {
                    //若当前元素比栈顶元素大，则替换栈顶元素下标出的值
                    result[tempIndex] = nums[i];
                    deque.pollLast();
                    //循环弹出栈顶元素，直到栈为空，或者栈顶元素小于等于当前元素
                } while (!deque.isEmpty() && nums[tempIndex = deque.peekLast()] < nums[i]);
            }
            deque.offer(i);
            tempIndex = i;
        }

        //2.第二趟遍历，查找栈中单调递减的元素的下一个更大值
        tempIndex = deque.pollLast();
        for (int num : nums) {
            if (nums[tempIndex] == max) {
                //因为一个数组中肯定有个最大值，而这个最大值也必将在栈底，所以遍历到此时，退出循环
                break;
            }

            //同步骤一，此步骤是从数组下标0开始重新遍历，所以剩余元素中，除了最大值，其余值肯定可以找到下一个更大的元素。
            if (num > nums[tempIndex]) {
                do {
                    result[tempIndex] = num;
                } while (nums[tempIndex = deque.pollLast()] < num);
            }
        }
        result[tempIndex] = -1;

        //3.第三趟遍历，将栈中剩余元素，至为-1，此时栈中若还存在元素，那么这些元素都将是数组中的最大值，不会找到下一个更大的值了，所以都设为-1.
        while (!deque.isEmpty()) {
            result[deque.poll()] = -1;
        }
        return result;
    }

}

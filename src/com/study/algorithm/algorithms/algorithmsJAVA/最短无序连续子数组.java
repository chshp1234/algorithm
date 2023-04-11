package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

//给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
//
//请你找出符合题意的 最短 子数组，并输出它的长度。
//
// 
//
//示例 1：
//
//输入：nums = [2,6,4,8,10,9,15]
//输出：5
//解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
//示例 2：
//
//输入：nums = [1,2,3,4]
//输出：0
//示例 3：
//
//输入：nums = [1]
//输出：0
// 
//
//提示：
//
//1 <= nums.length <= 104
//-105 <= nums[i] <= 105
// 
//
//进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最短无序连续子数组 {
    @Test
    public void 最短无序连续子数组() {
        int[] ints = {1, 3, 2, 3, 3};
        System.out.println("最短无序连续子数组:" + findUnsortedSubarrayByStack(ints));
    }

    public int findUnsortedSubarray(int[] nums) {
        //排序，双指针
        //最简单的方法，我们只要对数组进行拷贝，并对拷贝数组进行排序，那么去掉头尾连续相同的元素后，剩下的就是需要改变的子数组。

        //拷贝数组，排序
        int len = nums.length;
        int[] copy = new int[len];
        System.arraycopy(nums, 0, copy, 0, len);
        Arrays.sort(copy);
        int start = 0, end = len - 1;

        //去掉首部连续相同子数组，得出start
        while (start < len) {
            if (copy[start] == nums[start]) {
                start++;
            } else {
                break;
            }
        }

        //去掉尾部连续相同子数组，得到end
        while (end > start) {
            if (copy[end] == nums[end]) {
                end--;
            } else {
                break;
            }
        }

        return end > start ? end - start + 1 : 0;
    }

    public int findUnsortedSubarrayByStack(int[] nums) {
        //单调栈
        //两轮遍历
        //第一轮：单调递增栈，首先从首位将连续递增子数组加入栈中；后续遍历，如果遇到元素比栈中的小，弹出栈；最后栈中剩余的元素数量即为区间左端点。
        //第二轮：单调递减栈，此时将栈看成单调递减栈，思路同上，只不过此时是从右往左遍历，找出区间右端点。
        //最后答案即为左右断点区间元素和。
        int len = nums.length;
        if (len == 1) {
            return 0;
        }
        Deque<Integer> stack = new LinkedList<>();
        stack.push(nums[0]);
        int intdex = 1;

        int temp;
        while (intdex < len) {
            temp = nums[intdex - 1];
            if (temp <= nums[intdex]) {
                stack.push(nums[intdex]);
                intdex++;
            } else {
                break;
            }
        }
        while (intdex < len && !stack.isEmpty()) {
            temp = stack.peek();
            if (temp > nums[intdex]) {
                stack.pop();
            } else {
                intdex++;
            }
        }
        int start = stack.size();
        if (start == len) {
            return 0;
        }
        if (start == 0 && intdex == (len - 1)) {
            return len;
        }

        stack.clear();
        intdex = len - 1;
        stack.push(nums[intdex--]);
        while (intdex >= start) {
            temp = nums[intdex + 1];
            if (temp >= nums[intdex]) {
                stack.push(nums[intdex]);
                intdex--;
            } else {
                break;
            }
        }
        while (intdex >= start && !stack.isEmpty()) {
            temp = stack.peek();
            if (temp < nums[intdex]) {
                stack.pop();
            } else {
                intdex--;
            }
        }
        int end = stack.size();

        return len - end - start;

    }

    //假设 numsB在 nums 中对应区间为 [left,right]。
    //
    //注意到 numsB和numsC中任意一个数都大于等于numsA中任意一个数。
    //因此有 numsA中每一个数 nums[i]都满足：(查看)
    //https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/solution/zui-duan-wu-xu-lian-xu-zi-shu-zu-by-leet-yhlf/
    public int findUnsortedSubarrayByLeetCode(int[] nums) {
        int n = nums.length;
        int maxn = Integer.MIN_VALUE, right = -1;
        int minn = Integer.MAX_VALUE, left = -1;
        for (int i = 0; i < n; i++) {

            //维护右端点
            if (maxn > nums[i]) {
                right = i;
            } else {
                maxn = nums[i];
            }

            //维护左端点
            if (minn < nums[n - i - 1]) {
                left = n - i - 1;
            } else {
                minn = nums[n - i - 1];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }

}

package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
//
//不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
//
//元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
//
// 
//
//说明:
//
//为什么返回数值是整数，但输出的答案是数组呢?
//
//请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
//
//你可以想象内部操作如下:
//
//// nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
//int len = removeElement(nums, val);
//
//// 在函数里修改输入数组对于调用者是可见的。
//// 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
//for (int i = 0; i < len; i++) {
//    print(nums[i]);
//}
// 
//
//示例 1：
//
//输入：nums = [3,2,2,3], val = 3
//输出：2, nums = [2,2]
//解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
//示例 2：
//
//输入：nums = [0,1,2,2,3,0,4,2], val = 2
//输出：5, nums = [0,1,4,0,3]
//解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
// 
//
//提示：
//
//0 <= nums.length <= 100
//0 <= nums[i] <= 50
//0 <= val <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/remove-element
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 移除元素 {
    @Test
    public void 移除元素() {
        int[] ints = {0, 1, 2, 2, 2, 0, 4, 2};
        System.out.println("移除元素:" + removeElement(ints, 2));
    }

    public int removeElement(int[] nums, int val) {
        //快慢双指针
        //右指针不为目标值val时，将右指针处的元素移至左指针处，并左右指针各加1；否则仅右指针加1（这个元素不处理），左指针不动。
        /*int len = nums.length;
        if (len == 0 || val > 50) {
            return len;
        }

        int index   = 0;
        int current = 0;
        while (index < len) {
            if (nums[index] != val) {
                nums[current++] = nums[index];
            }
            index++;
        }
        return current;*/

        //高低指针
        //快慢指针，最差情况下，相当于两个指针都遍历一遍数组，这里可以进一步优化
        //因为不需要考虑长度后面的元素，那么也可以仅把后面的元素放置前面来，而前面部分符合条件的元素不动
        //1.若高位指针为目标值，高位指针减一
        //2.若低位指针为目标值，将高位值放置低位（经过第一步，高位值肯定不为目标值），并且高位减一，低位加一
        //3.若高低位都不为目标值，低位指针加1即可
        int left  = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[right] == val) {
                right--;
            } else if (nums[left] == val) {
                nums[left] = nums[right];
                right--;
                left++;
            } else {
                left++;
            }
        }
        return left;
    }
}

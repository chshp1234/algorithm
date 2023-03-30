package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

public class 删除排序数组中的重复项 {

    @Test
    public void 删除排序数组中的重复项() {
        int[] ints = {1, 1, 2};
        System.out.println("删除排序数组中的重复项:" + removeDuplicates(ints));
        System.out.println("删除排序数组中的重复项:" + Arrays.toString(ints));
    }

    // 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
    //
    // 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
    //
    //
    //
    // 示例 1:
    //
    // 给定数组 nums = [1,1,2],
    //
    // 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
    //
    // 你不需要考虑数组中超出新长度后面的元素。
    // 示例 2:
    //
    // 给定 nums = [0,0,1,1,1,2,2,3,3,4],
    //
    // 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
    //
    // 你不需要考虑数组中超出新长度后面的元素。
    //
    //
    // 说明:
    //
    // 为什么返回数值是整数，但输出的答案是数组呢?
    //
    // 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
    //
    // 你可以想象内部操作如下:
    //
    //// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
    // int len = removeDuplicates(nums);
    //
    //// 在函数里修改输入数组对于调用者是可见的。
    //// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
    // for (int i = 0; i < len; i++) {
    //     print(nums[i]);
    // }
    //
    // 作者：力扣 (LeetCode)
    // 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2gy9m/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int removeDuplicates(int[] nums) {
        // index记录当前不重复的下标，之后不重复项填充此下标
        int index = 1;
        for (int i = 1, l = nums.length; i < l; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
}

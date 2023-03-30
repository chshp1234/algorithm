package com.study.algorithm.algorithms;

import org.junit.Test;

//给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
//
//不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
//
// 
//
//说明：
//
//为什么返回数值是整数，但输出的答案是数组呢？
//
//请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
//
//你可以想象内部操作如下:
//
//// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
//int len = removeDuplicates(nums);
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
//输入：nums = [1,1,1,2,2,3]
//输出：5, nums = [1,1,2,2,3]
//解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
//示例 2：
//
//输入：nums = [0,0,1,1,1,1,2,3,3]
//输出：7, nums = [0,0,1,1,2,3,3]
//解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 不需要考虑数组中超出新长度后面的元素。
// 
//
//提示：
//
//1 <= nums.length <= 3 * 104
//-104 <= nums[i] <= 104
//nums 已按升序排列
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 删除有序数组中的重复项II {
    @Test
    public void 删除有序数组中的重复项II() {

        System.out.println("删除有序数组中的重复项II：" + removeDuplicates(new int[]{
                0, 0, 1, 1, 1, 1, 2, 3, 3
        }));
        //        System.out.println("有效括号的嵌套深度：" + calculateDepth(seq, seq.length()));
    }

    public int removeDuplicates(int[] nums) {
        //双指针
        //第一次循环找到不符合要求的下标出low
        //第二次循环，找到所有符合要求的元素，并令nums[low++] = nums[index]，result=result+1；
        int len     = nums.length;
        int current = nums[0];
        int count   = 1;
        int result  = 1;
        int index   = 1;
        int low     = 1;

        //第一次循环找到不符合要求的下标出low
        while (index < len) {
            if (nums[index] == current) {
                if (++count == 3) {
                    //元素数量为3时，不符合要求，退出循环，之后将所有符合要求的元素放置在位置low处
                    index++;
                    break;
                }
            } else {
                current = nums[index];
                count = 1;
            }
            result++;
            index++;
            low++;
        }
        while (index < len) {
            if (nums[index] == current) {
                if (++count <= 2) {
                    //若当前元素和之前元素相同，且元素数量小于等于2，符合要求
                    result++;
                    nums[low++] = nums[index];
                }
            } else {
                //若当前元素和之前元素不相同，则符合要求
                current = nums[index];
                result++;
                count = 1;
                nums[low++] = nums[index];
            }
            index++;
        }
        return result;
    }
}

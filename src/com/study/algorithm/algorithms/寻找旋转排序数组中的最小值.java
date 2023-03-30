package com.study.algorithm.algorithms;

//已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
//若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
//若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
//注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
//
//给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
//
// 
//
//示例 1：
//
//输入：nums = [3,4,5,1,2]
//输出：1
//解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
//示例 2：
//
//输入：nums = [4,5,6,7,0,1,2]
//输出：0
//解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
//示例 3：
//
//输入：nums = [11,13,15,17]
//输出：11
//解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
// 
//
//提示：
//
//n == nums.length
//1 <= n <= 5000
//-5000 <= nums[i] <= 5000
//nums 中的所有整数 互不相同
//nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 寻找旋转排序数组中的最小值 {

    public int findMin(int[] numbers) {
        //有序数组（不重复）旋转过后将会有两段有序上升子序列（也许只有一段）
        //我们以数组最右端元素为目标（也可以是最左端）target，对整个数组进行二分查找判断
        //1.如果中间值大于最右端元素target，那么此中间值的位置必然位于左侧的上升序列，将left=mid+1
        //2.如果中间值小于target，那么此中间值的位置必然位于右侧的上升序列中，将right=mid-1
        //最后循环结束时，numbers[left]即为最小值

        //这里的target可以取numbers[right]，因为left和right必然位于最小值两侧
        int left = 0, right = numbers.length - 1;

        int target = numbers.length - 1;

        while (left <= right) {
            int mi = (left + right) >>> 1;
            if (numbers[mi] > numbers[target]) {
                left = mi + 1;
            } else {
                right = mi - 1;
            }
        }
        return numbers[left];
    }

}

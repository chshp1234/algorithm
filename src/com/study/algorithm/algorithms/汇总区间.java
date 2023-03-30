package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//给定一个无重复元素的有序整数数组 nums 。
//
//返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
//
//列表中的每个区间范围 [a,b] 应该按如下格式输出：
//
//"a->b" ，如果 a != b
//"a" ，如果 a == b
// 
//
//示例 1：
//
//输入：nums = [0,1,2,4,5,7]
//输出：["0->2","4->5","7"]
//解释：区间范围是：
//[0,2] --> "0->2"
//[4,5] --> "4->5"
//[7,7] --> "7"
//示例 2：
//
//输入：nums = [0,2,3,4,6,8,9]
//输出：["0","2->4","6","8->9"]
//解释：区间范围是：
//[0,0] --> "0"
//[2,4] --> "2->4"
//[6,6] --> "6"
//[8,9] --> "8->9"
//示例 3：
//
//输入：nums = []
//输出：[]
//示例 4：
//
//输入：nums = [-1]
//输出：["-1"]
//示例 5：
//
//输入：nums = [0]
//输出：["0"]
// 
//
//提示：
//
//0 <= nums.length <= 20
//-231 <= nums[i] <= 231 - 1
//nums 中的所有值都 互不相同
//nums 按升序排列
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/summary-ranges
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 汇总区间 {
    @Test
    public void 汇总区间() {
        int[] ints = {0, 2, 3, 4, 6, 8, 9};
        System.out.println("汇总区间:" + summaryRanges(ints));
    }

    public List<String> summaryRanges(int[] nums) {
        //我们从数组的位置 0 出发，向右遍历。
        //每次遇到相邻元素之间的差值大于 1 时，我们就找到了一个区间。
        //遍历完数组之后，就能得到一系列的区间的列表。
        //我们需要维护两个指针start，end
        //当差值大于1时，并且end-1>start，则说明两个区间有多个元素，生成“start->end”
        //若end-1=start，说明此区间只有一个数，则生成“start”
        //讲生成的字符串加入列表，最后返回列表即可
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        StringBuilder stringBuilder;
        List<String> result = new ArrayList<>();
        int start = 0;
        for (int i = 1, len = nums.length; i < len; i++) {
            if (nums[i] - nums[i - 1] != 1) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(nums[start]);
                if (i - 1 > start) {
                    stringBuilder.append("->").append(nums[i - 1]);
                }
                start = i;
                result.add(stringBuilder.toString());
            }
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(nums[start]);
        if (start < nums.length - 1) {
            stringBuilder.append("->").append(nums[nums.length - 1]);
        }
        result.add(stringBuilder.toString());
        return result;
    }
}

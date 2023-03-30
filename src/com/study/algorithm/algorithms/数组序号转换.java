package com.study.algorithm.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//给你一个整数数组 arr ，请你将数组中的每个元素替换为它们排序后的序号。
//
//序号代表了一个元素有多大。序号编号的规则如下：
//
//序号从 1 开始编号。
//一个元素越大，那么序号越大。如果两个元素相等，那么它们的序号相同。
//每个数字的序号都应该尽可能地小。
// 
//
//示例 1：
//
//输入：arr = [40,10,20,30]
//输出：[4,1,2,3]
//解释：40 是最大的元素。 10 是最小的元素。 20 是第二小的数字。 30 是第三小的数字。
//示例 2：
//
//输入：arr = [100,100,100]
//输出：[1,1,1]
//解释：所有元素有相同的序号。
//示例 3：
//
//输入：arr = [37,12,28,9,100,56,80,5,12]
//输出：[5,3,4,2,8,6,7,1,3]
// 
//
//提示：
//
//0 <= arr.length <= 105
//-109 <= arr[i] <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/rank-transform-of-an-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 数组序号转换 {
    public int[] arrayRankTransform(int[] arr) {
        //排序+哈希表
        //先对数组进行排序，在将排序后的数字以及对应的排序后的大小加入哈希表中
        //再遍历原数组，根据在哈希表中对应的大小，加入结果数组中
        if (arr.length == 0) {
            return new int[0];
        }
        if (arr.length == 1) {
            return new int[]{1};
        }
        int[] result = Arrays.copyOf(arr, arr.length);
        Arrays.sort(result);
        Map<Integer, Integer> map = new HashMap<>();
        map.put(result[0], 1);
        int sort = 2;
        for (int i = 1; i < result.length; i++) {
            if (result[i] != result[i - 1]) {
                map.put(result[i], sort++);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            result[i] = map.get(arr[i]);
        }
        return result;
    }
}

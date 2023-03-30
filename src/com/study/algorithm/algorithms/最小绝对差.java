package com.study.algorithm.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//给你个整数数组 arr，其中每个元素都 不相同。
//
//请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
//
// 
//
//示例 1：
//
//输入：arr = [4,2,1,3]
//输出：[[1,2],[2,3],[3,4]]
//示例 2：
//
//输入：arr = [1,3,6,10,15]
//输出：[[1,3]]
//示例 3：
//
//输入：arr = [3,8,-10,23,19,-4,-14,27]
//输出：[[-14,-10],[19,23],[23,27]]
// 
//
//提示：
//
//2 <= arr.length <= 10^5
//-10^6 <= arr[i] <= 10^6
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-absolute-difference
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最小绝对差 {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        //排序
        //最小差值的两个数，肯定是在排序数组中连续的两个数
        //那么先对数组进行排序，使得方便找出最小绝对差值
        //然后在遍历一遍数组，将最小绝度差值的两个数加入结果列表中
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i] - arr[i - 1]);
        }
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] == min) {
                ArrayList<Integer> pair = new ArrayList<>();
                pair.add(arr[i - 1]);
                pair.add(arr[i]);
                result.add(pair);
            }
        }
        return result;
    }
}

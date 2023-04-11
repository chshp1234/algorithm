package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 子集II {

    @Test
    public void 子集II() {
        int[] ints = {1, 2, 2, 3};
        System.out.println("子集II:" + subsetsWithDup(ints));
    }

    List<List<Integer>> result    = new ArrayList<>();
    List<Integer>       temp      = new ArrayList<>();
    int                 len;
    int                 sizeIndex = -1;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //回溯
        //对于求子集，可以使用回溯算法
        //当前位置的元素有两种情况：1.选中；2.不选中
        //那么在回溯算法中，就可以对这两种状态都进行回溯计算
        //但注意此题，元素会重复，并且不能出现重复的子集
        //那么我们就需要对数组先进性一次排序，使得重复的元素相邻，方便遍历比较
        //使用一个布尔值变量判断上一个元素是否选中
        //只有当上一个元素是选中的，或者当前元素和上一个元素不相同，那么当前元素才可以被“选中”
        len = nums.length;
        Arrays.sort(nums);
        backTrack(nums, 0, true);
        return result;
    }

    public void backTrack(int[] nums, int index, boolean isLastChoose) {
        if (index == len) {
            result.add(new ArrayList<>(temp));
            return;
        }
        //只有当上一个元素是选中的，或者当前元素和上一个元素不相同，那么当前元素才可以被“选中”
        if (isLastChoose || nums[index] != nums[index - 1]) {
            temp.add(nums[index]);
            sizeIndex++;
            backTrack(nums, index + 1, true);
            temp.remove(sizeIndex);
            sizeIndex--;
        }
        //不选当前元素
        backTrack(nums, index + 1, false);
    }
}

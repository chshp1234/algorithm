package com.study.algorithm.algorithms;

//索引从0开始长度为N的数组A，包含0到N - 1的所有整数。找到最大的集合S并返回其大小，其中 S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }且遵守以下的规则。
//
//假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，之后是A[A[A[i]]]... 以此类推，不断添加直到S出现重复的元素。
//
// 
//
//示例 1:
//
//输入: A = [5,4,0,3,1,6,2]
//输出: 4
//解释:
//A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
//
//其中一种最长的 S[K]:
//S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
// 
//
//提示：
//
//N是[1, 20,000]之间的整数。
//A中不含有重复的元素。
//A中的元素大小在[0, N-1]之间。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/array-nesting
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 数组嵌套 {
    public int arrayNesting(int[] nums) {
        //图
        //根据搜索条件和规律可发现，这个数组可以转换成几个环链，我们只需要找出链条最长的一条环即可
        //深度优先搜索数组，并用一个visit数组记录每个元素对应的环链中的长度
        //当visit数组中元素不为0时，说明元素已经遍历过，返回当前长度，并更新链上所有元素对应的长度
        //最后返回最长的链的长度

        //标记数组，记录每个下标其对应的链条的长度
        int[] visit = new int[nums.length];
        int result = 0;
        for (int i = 0, l = nums.length; i < l; i++) {
            result = Math.max(result, dfs(nums, visit, i, 0));
        }
        return result;
    }

    public int dfs(int[] nums, int[] visit, int index, int count) {
        if (visit[index] != 0) {
            //当前下标的元素已经遍历过，返回长度
            return count;
        }
        //标记当前元素已经遍历
        visit[index] = 1;
        //更新当前元素的链条的长度
        visit[index] = dfs(nums, visit, nums[index], count + 1);
        //返回此长度
        return visit[index];
    }
}

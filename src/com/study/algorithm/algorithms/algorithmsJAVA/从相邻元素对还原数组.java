package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.*;

//存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。
//
//给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 nums 中相邻。
//
//题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i], nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。
//
//返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。
//
// 
//
//示例 1：
//
//输入：adjacentPairs = [[2,1],[3,4],[3,2]]
//输出：[1,2,3,4]
//解释：数组的所有相邻元素对都在 adjacentPairs 中。
//特别要注意的是，adjacentPairs[i] 只表示两个元素相邻，并不保证其 左-右 顺序。
//示例 2：
//
//输入：adjacentPairs = [[4,-2],[1,4],[-3,1]]
//输出：[-2,4,1,-3]
//解释：数组中可能存在负数。
//另一种解答是 [-3,1,4,-2] ，也会被视作正确答案。
//示例 3：
//
//输入：adjacentPairs = [[100000,-100000]]
//输出：[100000,-100000]
// 
//
//提示：
//
//nums.length == n
//adjacentPairs.length == n - 1
//adjacentPairs[i].length == 2
//2 <= n <= 105
//-105 <= nums[i], ui, vi <= 105
//题目数据保证存在一些以 adjacentPairs 作为元素对的数组 nums
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/restore-the-array-from-adjacent-pairs
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 从相邻元素对还原数组 {
    @Test
    public void 从相邻元素对还原数组() {
        System.out.println("从相邻元素对还原数组" + Arrays.toString(restoreArray(
                new int[][]{{4, -10}, {-1, 3}, {4, -3}, {-3, 3}})));
    }

    public int[] restoreArray(int[][] adjacentPairs) {
        //哈希表
        //1.注意，元二维数组中，只出现一次的数字肯定是两端的数字
        //2.用哈希表记录每个数字左右两端的数字
        //3.基于‘1’找出一个端点，从此出发，用‘2’中记录的信息深度优先搜索找到下一个节点
        if (adjacentPairs.length == 1) {
            return new int[]{adjacentPairs[0][0], adjacentPairs[0][1]};
        }
        Map<Integer, int[]> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        int len = adjacentPairs.length;
        int[] result = new int[len + 1];

        int[] temp;
        for (int[] ints : adjacentPairs) {
            temp = map.get(ints[0]);
            if (temp == null) {
                temp = new int[]{ints[1], 106};
                map.put(ints[0], temp);
                set.add(ints[0]);
            } else {
                set.remove(ints[0]);
                temp[1] = ints[1];
            }

            temp = map.get(ints[1]);
            if (temp == null) {
                temp = new int[]{ints[0], 106};
                map.put(ints[1], temp);
                result[0] = ints[1];
                set.add(ints[1]);
            } else {
                set.remove(ints[1]);
                temp[1] = ints[0];
            }
        }
        Iterator<Integer> iterator = set.iterator();
        result[0] = iterator.next();
        result[len--] = iterator.next();
        temp = map.get(result[0]);
        result[1] = temp[0];
        int index = 1;


        while (index < len) {
            temp = map.get(result[index]);
            for (int next : temp) {
                if (next != result[index - 1]) {
                    result[++index] = next;
                    break;
                }
            }
        }

        return result;
    }

}

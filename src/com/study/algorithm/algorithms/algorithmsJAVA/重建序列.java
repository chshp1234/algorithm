package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.*;

//给定一个长度为 n 的整数数组 nums ，其中 nums 是范围为 [1，n] 的整数的排列。还提供了一个 2D 整数数组 sequences ，其中 sequences[i] 是 nums 的子序列。
//检查 nums 是否是唯一的最短 超序列 。最短 超序列 是 长度最短 的序列，并且所有序列 sequences[i] 都是它的子序列。对于给定的数组 sequences ，可能存在多个有效的 超序列 。
//
//例如，对于 sequences = [[1,2],[1,3]] ，有两个最短的 超序列 ，[1,2,3] 和 [1,3,2] 。
//而对于 sequences = [[1,2],[1,3],[1,2,3]] ，唯一可能的最短 超序列 是 [1,2,3] 。[1,2,3,4] 是可能的超序列，但不是最短的。
//如果 nums 是序列的唯一最短 超序列 ，则返回 true ，否则返回 false 。
//子序列 是一个可以通过从另一个序列中删除一些元素或不删除任何元素，而不改变其余元素的顺序的序列。
//
// 
//
//示例 1：
//
//输入：nums = [1,2,3], sequences = [[1,2],[1,3]]
//输出：false
//解释：有两种可能的超序列：[1,2,3]和[1,3,2]。
//序列 [1,2] 是[1,2,3]和[1,3,2]的子序列。
//序列 [1,3] 是[1,2,3]和[1,3,2]的子序列。
//因为 nums 不是唯一最短的超序列，所以返回false。
//示例 2：
//
//输入：nums = [1,2,3], sequences = [[1,2]]
//输出：false
//解释：最短可能的超序列为 [1,2]。
//序列 [1,2] 是它的子序列：[1,2]。
//因为 nums 不是最短的超序列，所以返回false。
//示例 3：
//
//输入：nums = [1,2,3], sequences = [[1,2],[1,3],[2,3]]
//输出：true
//解释：最短可能的超序列为[1,2,3]。
//序列 [1,2] 是它的一个子序列：[1,2,3]。
//序列 [1,3] 是它的一个子序列：[1,2,3]。
//序列 [2,3] 是它的一个子序列：[1,2,3]。
//因为 nums 是唯一最短的超序列，所以返回true。
// 
//
//提示：
//
//n == nums.length
//1 <= n <= 104
//nums 是 [1, n] 范围内所有整数的排列
//1 <= sequences.length <= 104
//1 <= sequences[i].length <= 104
//1 <= sum(sequences[i].length) <= 105
//1 <= sequences[i][j] <= n
//sequences 的所有数组都是 唯一 的
//sequences[i] 是 nums 的一个子序列
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/ur2n8P
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 重建序列 {
    @Test
    public void 重建序列() {
        System.out.println("重建序列:" + sequenceReconstruction(
                new int[]{1, 2, 3},
                new int[][]{
                        {1, 2},
                        {1, 3},
                        {2, 3}
                }));
    }

    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        //拓扑排序
        //根据sequences 构建一张有向图graph
        //对图进行拓扑排序
        //在遍历时，若某一时刻入度为0的节点数量为0或者大于1时，说明最短子路径不止1个，那么将不符合条件
        //如果遍历时，当前节点和数组中对应下标的节点值不用，也说明不是其中的一个子序列，不符合条件

        //每个节点的入度数
        Map<Integer, Integer> in = new HashMap<>();
        //每个节点的出度节点集合
        Map<Integer, Set<Integer>> out = new HashMap<>();

        //构图
        for (int[] ints : sequences) {

            //设置出度集合
            Set<Integer> numOut = out.get(ints[0]);
            if (numOut == null) {
                numOut = new HashSet<>();
                out.put(ints[0], numOut);
            }
            //若此节点没设置入度数，则设置入度数为0
            if (in.get(ints[0]) == null) {
                in.put(ints[0], 0);
            }
            for (int i = 1; i < ints.length; i++) {
                //将当前节点添加到上一个节点的出度集合中
                if (numOut.add(ints[i])) {
                    //若当前节点没添加到出度节点集合中
                    in.put(ints[i], in.getOrDefault(ints[i], 0) + 1);
                }

                numOut = out.get(ints[i]);
                if (numOut == null) {
                    numOut = new HashSet<>();
                    out.put(ints[i], numOut);
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        //寻找入度为0的节点
        for (Map.Entry<Integer, Integer> entry : in.entrySet()) {
            if (entry.getValue() == null || entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }

        int index = 0;
        while (index < nums.length) {
            //此图成环
            if (queue.size() == 0) {
                return false;
            }

            //入度节点为的数量大于1
            if (queue.size() > 1) {
                return false;
            }
            int top = queue.poll();
            //判断值是否相等
            if (nums[index] != top) {
                return false;
            }

            //根据出度节点的集合，更新入度节点的数量
            Set<Integer> numOut = out.get(top);
            if (numOut != null) {
                for (int k : numOut) {
                    int inNum = in.get(k) - 1;
                    if (inNum == 0) {
                        queue.offer(k);
                    }
                    in.put(k, inNum);
                }
            }

            index++;
        }

        return index == nums.length;
    }
}

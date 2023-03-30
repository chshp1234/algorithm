package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.*;

//给你一个 m x n 的二元矩阵 matrix ，且所有值被初始化为 0 。请你设计一个算法，随机选取一个满足 matrix[i][j] == 0 的下标 (i, j) ，并将它的值变为 1 。所有满足 matrix[i][j] == 0 的下标 (i, j) 被选取的概率应当均等。
//
//尽量最少调用内置的随机函数，并且优化时间和空间复杂度。
//
//实现 Solution 类：
//
//Solution(int m, int n) 使用二元矩阵的大小 m 和 n 初始化该对象
//int[] flip() 返回一个满足 matrix[i][j] == 0 的随机下标 [i, j] ，并将其对应格子中的值变为 1
//void reset() 将矩阵中所有的值重置为 0
// 
//
//示例：
//
//输入
//["Solution", "flip", "flip", "flip", "reset", "flip"]
//[[3, 1], [], [], [], [], []]
//输出
//[null, [1, 0], [2, 0], [0, 0], null, [2, 0]]
//
//解释
//Solution solution = new Solution(3, 1);
//solution.flip();  // 返回 [1, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同
//solution.flip();  // 返回 [2, 0]，因为 [1,0] 已经返回过了，此时返回 [2,0] 和 [0,0] 的概率应当相同
//solution.flip();  // 返回 [0, 0]，根据前面已经返回过的下标，此时只能返回 [0,0]
//solution.reset(); // 所有值都重置为 0 ，并可以再次选择下标返回
//solution.flip();  // 返回 [2, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同
// 
//
//提示：
//
//1 <= m, n <= 104
//每次调用flip 时，矩阵中至少存在一个值为 0 的格子。
//最多调用 1000 次 flip 和 reset 方法。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/random-flip-matrix
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
@Deprecated
public class 随机翻转矩阵 {

    @Test
    public void 随机翻转矩阵() {
        Solution 随机翻转矩阵 = new Solution(3, 3);
        System.out.println(Arrays.toString(随机翻转矩阵.flip()));
        System.out.println(Arrays.toString(随机翻转矩阵.flip()));
        System.out.println(Arrays.toString(随机翻转矩阵.flip()));
        System.out.println(Arrays.toString(随机翻转矩阵.flip()));
        System.out.println(Arrays.toString(随机翻转矩阵.flip()));
        System.out.println(Arrays.toString(随机翻转矩阵.flip()));
        System.out.println(Arrays.toString(随机翻转矩阵.flip()));
        System.out.println(Arrays.toString(随机翻转矩阵.flip()));
        System.out.println(Arrays.toString(随机翻转矩阵.flip()));
    }

    //二维数组展开
    //将二维数组展开为一维数组
    //翻转k次后，我们可以将[0..(count-k-1)]的元素置为0，将[count-k...count-1]的元素置为1
    //那么当翻转的下标为i时，我们将[count-k-1]处的元素移至下标i处，[i]的元素移至count-k-1下标处
    //因为对于下标肯定都是唯一的，那么我们将当前下标和原始下标作为一个键值对来存储，而由于初始化时，下标i和原始下标i都是相同的，固map不需要处理
    //只需要在翻转的时候，令原始下标[zeroCount]的元素重新置于待翻转的下标i处即可
    static class Solution {

        Map<Integer, Integer> map = new HashMap<>();

        int zeroCount;
        int totalCount;
        int col;
        Random random = new Random();

        public Solution(int row, int col) {
            this.col = col;
            totalCount = zeroCount = row * col;
        }

        public int[] flip() {
            int index = random.nextInt(zeroCount--);

            int originalIndex = map.getOrDefault(index, index);

            int[] result = new int[]{originalIndex / col, originalIndex % col};
            map.put(index, map.getOrDefault(zeroCount, zeroCount));
            return result;
        }

        public void reset() {
            zeroCount = totalCount;
            map.clear();
        }
    }

    //方法一，直接维护一个包含所有元素的链表，每次随机选取并移除链表中的一个元素返回
//    static class Solution{
//        List<int[]> matrix = new LinkedList<>();
//
//
//        List<int[]> randomMatrix;
//
//        Random random = new Random();
//
//        public Solution(int m, int n) {
//
//            m--;
//            n--;
//
//            while (m >= 0) {
//                int col = n;
//                while (col >= 0) {
//                    matrix.add(new int[]{m, col});
//                    col--;
//                }
//                m--;
//            }
//            randomMatrix = new LinkedList<>(matrix);
//        }
//
//        public int[] flip() {
//            return randomMatrix.remove(random.nextInt(randomMatrix.size()));
//        }
//
//        public void reset() {
//            randomMatrix = new LinkedList<>(matrix);
//        }
}

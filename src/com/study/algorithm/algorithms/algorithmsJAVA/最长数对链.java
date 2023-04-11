package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

//给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
//
//现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
//
//给定一个数对集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
//
// 
//
//示例：
//
//输入：[[1,2], [2,3], [3,4]]
//输出：2
//解释：最长的数对链是 [1,2] -> [3,4]
// 
//
//提示：
//
//给出数对的个数在 [1, 1000] 范围内。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-length-of-pair-chain
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最长数对链 {
    @Test
    public void 最长数对链() {
        int[][] graph = {{-10, -8}, {8, 9}, {-5, 0}, {6, 10}, {-6, -4}, {1, 7}, {9, 10}, {-4, 7}};
        System.out.println("最长数对链:" + findLongestChain(graph));
    }

    public int findLongestChain(int[][] pairs) {
        //排序，贪心
        //先对数对进行排序，按照数对中的左区间从小到大排序
        //遍历数对，贪心的选取右区间小的数作为当前链的右区间，因为右区间值尽量小，后面的数对才越有机会连接在当前链（也就是后面数对的左区间小大于当前链的右区间）
        //数对的左区间小大于当前链的右区间时，连接上，数对链的数量+1
        //遍历完成，返回链数
        Arrays.sort(pairs, Comparator.comparingInt(o -> o[0]));

        int r = pairs[0][1];
        int result = 1;
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][1] < r) {
                r = pairs[i][1];
            } else if (pairs[i][0] > r) {
                result++;
                r = pairs[i][1];
            }
        }
        return result;
    }
}

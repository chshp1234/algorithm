package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
//
//返回平面上所有回旋镖的数量。
//
// 
//示例 1：
//
//输入：points = [[0,0],[1,0],[2,0]]
//输出：2
//解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
//示例 2：
//
//输入：points = [[1,1],[2,2],[3,3]]
//输出：2
//示例 3：
//
//输入：points = [[1,1]]
//输出：0
// 
//
//提示：
//
//n == points.length
//1 <= n <= 500
//points[i].length == 2
//-104 <= xi, yi <= 104
//所有点都 互不相同
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/number-of-boomerangs
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 回旋镖的数量 {

    @Test
    public void 回旋镖的数量() {
        int n = 5;

        System.out.println("回旋镖的数量" + numberOfBoomerangs(new int[][]{{0, 0}, {1, 0}, {2, 0}, {1, 1}}));
    }

    public int numberOfBoomerangs(int[][] points) {
        //枚举+哈希表
        //用哈希表存储每两个点之间的距离的数量，若已存在此距离的数量count，则结果+count*2
        int len = points.length;
        if (len < 3) {
            return 0;
        }
        Map<Integer, Integer>[] maps = new HashMap[len];

        int result = 0;
        Map<Integer, Integer> temp;
        for (int i = 0; i < len; i++) {
            temp = maps[i];
            if (temp == null) {
                temp = new HashMap<>();
            }

            //一趟遍历，同时判断以i为折点和以j为折点时，是否存在可构成回旋镖的点，所以这里只需要j为i+1开始遍历即可
            for (int j = i + 1; j < len; j++) {
                //两点间距离
                int distance = (int) (Math.pow(Math.abs(points[i][0] - points[j][0]), 2) + Math.pow(Math.abs(points[i][1] - points[j][1]), 2));
                Integer count = temp.get(distance);

                //以i为折点时，是否存在可构成回旋镖的3个点
                if (count == null) {
                    temp.put(distance, 1);
                } else {
                    //已存在相同距离的一个点时，那么这3个点可构成两个回旋镖，所以结果加上存在点的数量*2
                    result += count * 2;
                    temp.put(distance, count + 1);
                }

                //以j为折点时，是否存在可构成回旋镖的3个点
                if (maps[j] == null) {
                    maps[j] = new HashMap<>();
                    maps[j].put(distance, 1);
                } else {
                    //同上
                    count = maps[j].get(distance);
                    if (count == null) {
                        maps[j].put(distance, 1);
                    } else {
                        result += count * 2;
                        maps[j].put(distance, count + 1);
                    }
                }
            }
        }

        return result;

    }
}

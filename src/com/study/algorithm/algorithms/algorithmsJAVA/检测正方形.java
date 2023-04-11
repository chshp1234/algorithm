package com.study.algorithm.algorithms.algorithmsJAVA;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//给你一个在 X-Y 平面上的点构成的数据流。设计一个满足下述要求的算法：
//
//添加 一个在数据流中的新点到某个数据结构中。可以添加 重复 的点，并会视作不同的点进行处理。
//给你一个查询点，请你从数据结构中选出三个点，使这三个点和查询点一同构成一个 面积为正 的 轴对齐正方形 ，统计 满足该要求的方案数目。
//轴对齐正方形 是一个正方形，除四条边长度相同外，还满足每条边都与 x-轴 或 y-轴 平行或垂直。
//
//实现 DetectSquares 类：
//
//DetectSquares() 使用空数据结构初始化对象
//void add(int[] point) 向数据结构添加一个新的点 point = [x, y]
//int count(int[] point) 统计按上述方式与点 point = [x, y] 共同构造 轴对齐正方形 的方案数。
// 
//
//示例：
//
//
//输入：
//["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
//[[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
//输出：
//[null, null, null, null, 1, 0, null, 2]
//
//解释：
//DetectSquares detectSquares = new DetectSquares();
//detectSquares.add([3, 10]);
//detectSquares.add([11, 2]);
//detectSquares.add([3, 2]);
//detectSquares.count([11, 10]); // 返回 1 。你可以选择：
//                               //   - 第一个，第二个，和第三个点
//detectSquares.count([14, 8]);  // 返回 0 。查询点无法与数据结构中的这些点构成正方形。
//detectSquares.add([11, 2]);    // 允许添加重复的点。
//detectSquares.count([11, 10]); // 返回 2 。你可以选择：
//                               //   - 第一个，第二个，和第三个点
//                               //   - 第一个，第三个，和第四个点
// 
//
//提示：
//
//point.length == 2
//0 <= x, y <= 1000
//调用 add 和 count 的 总次数 最多为 5000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/detect-squares
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 检测正方形 {
    //哈希表
//哈希表记录没一个点,以及每一个坐标存在的点的数量
//判断时,根据给定点,找到当前纵坐标全部存在的坐标
//并计算每个坐标与给定点的距离len
//再根据距离,判断与给定点左边相距len长度与右边相距len长度的横坐标的所有点,
//最后再根据原点的纵坐标与给定点的纵坐标,判断是否存在上一步判断出的点中,若存在,根据这3点坐标各个的数量计算总共可构成的正方形数量
    class DetectSquares {

        Map<Integer, Map<Integer, Integer>> rowCounter = new HashMap<>();

        public DetectSquares() {
        }

        public void add(int[] point) {
            Map<Integer, Integer> counter = rowCounter.get(point[0]);
            Integer count;
            if (counter == null) {
                counter = new HashMap<>();
                rowCounter.put(point[0], counter);
            }
            count = counter.get(point[1]);
            if (count == null) {
                count = 0;
            }
            counter.put(point[1], count + 1);

        }

        public int count(int[] point) {
            Map<Integer, Integer> row = rowCounter.get(point[0]);
            if (row == null) {
                return 0;
            }

            Set<Map.Entry<Integer, Integer>> rowEntries = row.entrySet();

            int result = 0;
            for (Map.Entry<Integer, Integer> c : rowEntries) {
                if (c.getKey() == point[1]) {
                    continue;
                }
                int len = Math.abs(point[1] - c.getKey());
                result += count(point[0] + len, c, point[1]);
                result += count(point[0] - len, c, point[1]);
            }
            return result;
        }

        public int count(int cLen, Map.Entry<Integer, Integer> c, int cp) {
            Map<Integer, Integer> col = rowCounter.get(cLen);
            if (col == null) {
                return 0;
            }
            Integer count1;
            Integer count2;
            count1 = col.get(cp);
            if (count1 == null) {
                return 0;
            }
            count2 = col.get(c.getKey());
            if (count2 == null) {
                return 0;
            }
            return count1 * count2 * c.getValue();
        }
    }
}

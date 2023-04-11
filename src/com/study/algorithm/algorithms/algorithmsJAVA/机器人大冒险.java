package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class 机器人大冒险 {
    @Test
    public void 机器人大冒险() {
        System.out.println("机器人大冒险,是否能通过障碍物到达终点：" + robot("URR", new int[][] {{2, 3}}, 3, 2));
    }
    /**
     * 力扣团队买了一个可编程机器人，机器人初始位置在原点(0, 0)。小伙伴事先给机器人输入一串指令command，机器人就会无限循环这条指令的步骤进行移动。指令有两种：
     *
     * <p>U: 向y轴正方向移动一格 R: 向x轴正方向移动一格。 不幸的是，在 xy 平面上还有一些障碍物，他们的坐标用obstacles表示。机器人一旦碰到障碍物就会被损毁。
     *
     * <p>给定终点坐标(x, y)，返回机器人能否完好地到达终点。如果能，返回true；否则返回false。
     *
     * <p>
     *
     * <p>示例 1：
     *
     * <p>输入：command = "URR", obstacles = [], x = 3, y = 2 输出：true 解释：U(0, 1) -> R(1, 1) -> R(2, 1)
     * -> U(2, 2) -> R(3, 2)。 示例 2：
     *
     * <p>输入：command = "URR", obstacles = [[2, 2]], x = 3, y = 2 输出：false 解释：机器人在到达终点前会碰到(2, 2)的障碍物。
     * 示例 3：
     *
     * <p>输入：command = "URR", obstacles = [[4, 2]], x = 3, y = 2 输出：true 解释：到达终点后，再碰到障碍物也不影响返回结果。
     *
     * <p>限制：
     *
     * <p>2 <= command的长度 <= 1000 command由U，R构成，且至少有一个U，至少有一个R 0 <= x <= 1e9, 0 <= y <= 1e9 0 <=
     * obstacles的长度 <= 1000 obstacles[i]不为原点或者终点
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/programmable-robot
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean robot(String command, int[][] obstacles, int x, int y) {

        // 思路：判断机器人行径的路径上，会不会碰到障碍物以及是否能到达终点即可
        // 障碍物和终点其实一样，判断是否在路径上，以x轴判断障碍物（终点）坐标x，y：
        // 1.计算机器人第一次到达x位置时y的坐标y1
        // 2.计算机器人到达x+1的位置时，y2的坐标
        // 3.若y1<=y<=y2，则说明机器人会遇到此障碍物（终点）
        // 所以难点就在于机器人第一次到达x坐标时，y的坐标
        // ·首先我们用一个map存储机器人指令中，第x次碰到向右（R）指令时，前面有多少个向上（U）指令，顺便计算出一次完整指令结束时R指令和U指令的数量
        // ·然后若有障碍物，我们按障碍物中x的顺序进行重新排序，在此处我们不用从小到大排序，只需要获取到最小的x的障碍物即可，所以可以用优先队列实现
        // ·最后就是根据上面的思路判断障碍物是否在路径上，以及终点是否在路径上即可

        Map<Integer, Integer> map = new HashMap<>();

        int RCount = 0;
        int UCount = 0;

        // 计算一次完整指令中有多少次向右和向上操作，并记录x次向右操作时有多少次向上操作（Map映射）
        for (int i = 0, len = command.length(); i < len; i++) {
            if (command.charAt(i) == 'R') {
                RCount++;
                map.put(RCount, UCount);
            } else {
                UCount++;
            }
        }

        // 多少次完整指令
        int step;

        // 判断障碍物是否在路径上
        if (obstacles != null && obstacles.length > 0) {
            PriorityQueue<int[]> queue =
                    new PriorityQueue<>(
                            obstacles.length,
                            new Comparator<int[]>() {
                                @Override
                                public int compare(int[] o1, int[] o2) {
                                    return o1[0] - o2[0];
                                }
                            });

            for (int i = 0, l = obstacles.length; i < l; i++) {
                // 只加入障碍物中坐标x和y值均小于等于终点坐标的点
                if (obstacles[i][0] <= x && obstacles[i][1] <= y) {
                    queue.offer(obstacles[i]);
                }
            }

            while (!queue.isEmpty()) {
                int[] poll = queue.poll();

                step = poll[0] / RCount;
                step = poll[0] % RCount == 0 ? step - 1 : step;

                int fY = step * UCount + map.get(poll[0] - step * RCount);

                step = (poll[0] + 1) / RCount;
                step = (poll[0] + 1) % RCount == 0 ? step - 1 : step;

                int sY = step * UCount + map.get(poll[0] + 1 - step * RCount);

                if (poll[1] >= fY && poll[1] <= sY) {
                    return false;
                }
            }
        }

        // 判断终点是否在路径上
        step = x / RCount;
        step = x % RCount == 0 ? step - 1 : step;
        int fY = step * UCount + map.get(x - step * RCount);
        step = (x + 1) / RCount;
        step = (x + 1) % RCount == 0 ? step - 1 : step;
        int sY = step * UCount + map.get(x + 1 - step * RCount);
        if (y >= fY && y <= sY) {
            return true;
        }

        return false;
    }
}

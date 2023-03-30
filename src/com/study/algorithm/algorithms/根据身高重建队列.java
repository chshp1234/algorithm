package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

// 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
//
// 注意：
// 总人数少于1100人。
//
// 示例
//
// 输入:
// [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
//
// 输出:
// [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
@Deprecated
public class 根据身高重建队列 {
    @Test
    public void 根据身高重建队列() {

        int[][] pillars = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};

        System.out.println("根据身高重建队列:" + Arrays.deepToString(reconstructQueue(pillars)));
    }

    public int[][] reconstructQueue(int[][] people) {

        // 由矮到高排
        // 先用优先队列排序原数组，按身高h从小到大排序，若身高相等，则按人数k排序
        // 使用一个辅助数组，保存队列中还未入队（未填充的坐标）的下标
        // 由于身高相同的排在前面，也要算入数字‘k’中，所以用last记录上一个人身高，lastCount记录和此身高相同的人数

        int len = people.length;
        if (len == 0) {
            return people;
        }

        PriorityQueue<int[]> priorityQueue =
                new PriorityQueue<>(
                        len,
                        (o1, o2) -> {
                            if (o1[0] == o2[0]) {
                                return o1[1] - o2[1];
                            }
                            return o1[0] - o2[0];
                        });
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            list.add(i);
            priorityQueue.offer(people[i]);
        }

        int[] top;
        int last = 0;
        int lastCount = 0;
        for (int i = 0; i < len; i++) {
            top = priorityQueue.poll();

            // 若此人身高和上一个人身高相同
            if (top[0] == last) {
                // 则此人应插入队列的位置为：k-lastCount
                // 比如例子中的[5,2]，前面已经排了[5,0],所以队伍位置0，已经使用，并从辅助数组中移除，数组中现在还为排入队伍的下标为1,2,3,5
                // 而此人前面应有2个人比他高（包括相同身高），所以index=k-lastCount=2-1=1
                // 这个‘1’代表的是在辅助队列中的下标，也就是2，而这个得到‘2’代表真正在队伍中的位置下标，所以[5,2]应排入原数组下标‘2’处
                int index = top[1] - lastCount;
                people[list.remove(index)] = top;

                // 相同身高人数+1
                lastCount++;
            }
            // 若此人身高和上一个人身高不相同
            else {
                // 则说明此人身高比之前的人都高，不用考虑身高相同的情况
                // 直接从辅助队列中取出比此人高的前面人数‘k’下标处的值
                // 比如[6,1],此时前面已经排了[5,0],[5,2],[4,4],辅助队列中下标信息还剩1,3,5
                // 在次队列中，要有1个人比他高，只能排在辅助队列下标‘1’处，也就是原数组真正下标‘3’

                // 重置上一个人身高，以及人数
                people[list.remove(top[1])] = top;
                last = top[0];
                lastCount = 1;
            }
        }

        return people;
    }
}

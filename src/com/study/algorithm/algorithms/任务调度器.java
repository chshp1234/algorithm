package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1
// 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
//
// 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
//
// 你需要计算完成所有任务所需要的 最短时间 。
//
//
//
// 示例 1：
//
// 输入：tasks = ['A','A','A','B','B','B'], n = 2
// 输出：8
// 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
//     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
// 示例 2：
//
// 输入：tasks = ['A','A','A','B','B','B'], n = 0
// 输出：6
// 解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
// ['A','A','A','B','B','B']
// ['A','B','A','B','A','B']
// ['B','B','B','A','A','A']
// ...
// 诸如此类
// 示例 3：
//
// 输入：tasks = ['A','A','A','A','A','A','B','C','D','E','F','G'], n = 2
// 输出：16
// 解释：一种可能的解决方案是：
//     A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A
//
//
// 提示：
//
// 1 <= task.length <= 104
// tasks[i] 是大写英文字母
// n 的取值范围为 [0, 100]
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/task-scheduler
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 任务调度器 {

    @Test
    public void 任务调度器() {
        System.out.println(leastInterval(new char[] {'A', 'B', 'C', 'D', 'A', 'B', 'V'}, 3));
    }

    public int leastInterval(char[] tasks, int n) {

        // 堆、队列
        // 先用一个辅助数组记统计不同任务的执行次数
        // 我们从剩余任务中，每次选出所需执行次数最多的任务，使待命时间尽可能的减少（这里可以使用优先队列）。
        // 每一轮选择中，按任务种类和待命时间中最小的数进行遍历，若任务种类少，则最后将会有剩余的待命时间需要加入结果步数中；若剩余任务种类多，则不需要加入待命时间。
        // 每选出一个任务时，这个任务可执行的剩余次数-1，若可执行次数依旧大于0，则继续加入堆中，进行下一轮判断（这里可使用普通队列，对执行过的任务进行零时存储）。

        // 如果待命时间为0，说明不需要等待，直接返回数组大小（任务数量）
        if (n == 0) {
            return tasks.length;
        }
        char[] counter = new char[26];
        // 统计不同任务的执行次数
        for (int i = 0, l = tasks.length; i < l; i++) {
            counter[tasks[i] - 'A']++;
        }

        PriorityQueue<Character> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        Queue<Character> temps = new LinkedList<>();

        for (int i = 0; i < 26; i++) {
            if (counter[i] != 0) {
                queue.offer(counter[i]);
            }
        }

        // 总执行步数
        int step = 0;

        // 待命时间
        int await = 0;

        while (!queue.isEmpty()) {
            // 在上一轮步骤中，是否需要有额外的待命时间（剩余任务种类过少时）
            step += await;
            // 加上刚加入的任务，总共时间间隔为n+1
            await = n + 1;
            int size = queue.size();
            // 按任务种类和待命时间中最小的数进行遍历
            for (int i = 0, l = Math.min(await, size); i < l; i++) {
                // 取出所需执行次数最多的任务，加入临时队列中
                temps.offer(queue.poll());
                // 任务步骤加1
                step++;
                // 待命时间-1
                await--;
            }

            // 从零时任务队列中取出任务，若次数-1>0，说明还有次数需要执行，再次加入优先队列中
            for (int i = 0, l = temps.size(); i < l; i++) {
                Character character = temps.poll();
                if ((character - 1) > 0) {
                    queue.offer((char) (character - 1));
                }
            }
        }

        return step;
    }
}

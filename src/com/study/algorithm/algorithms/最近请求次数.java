package com.study.algorithm.algorithms;

import java.util.LinkedList;
import java.util.Queue;

//写一个 RecentCounter 类来计算特定时间范围内最近的请求。
//
//请实现 RecentCounter 类：
//
//RecentCounter() 初始化计数器，请求数为 0 。
//int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 [t-3000, t] 内发生的请求数。
//保证 每次对 ping 的调用都使用比之前更大的 t 值。
//
// 
//
//示例：
//
//输入：
//inputs = ["RecentCounter", "ping", "ping", "ping", "ping"]
//inputs = [[], [1], [100], [3001], [3002]]
//输出：
//[null, 1, 2, 3, 3]
//
//解释：
//RecentCounter recentCounter = new RecentCounter();
//recentCounter.ping(1);     // requests = [1]，范围是 [-2999,1]，返回 1
//recentCounter.ping(100);   // requests = [1, 100]，范围是 [-2900,100]，返回 2
//recentCounter.ping(3001);  // requests = [1, 100, 3001]，范围是 [1,3001]，返回 3
//recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002]，范围是 [2,3002]，返回 3
// 
//
//提示：
//
//1 <= t <= 109
//保证每次对 ping 调用所使用的 t 值都 严格递增
//至多调用 ping 方法 104 次
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/H8086Q
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最近请求次数 {
    //队列

    //将数据依次入队
    //判断队列中的数据是否在当前数据的范围[t-3000,t]中
    //若不在范围，则将队列头部数据出队，并继续判断下一条数据；
    //若在范围中，则之后的数据也必然在范围内，将t入队，返回队列长度，即是当前范围内请求的总次数
    Queue<Integer> queue = new LinkedList<>();

    public 最近请求次数() {

    }

    public int ping(int t) {

        int min = t - 3000;
        while (!queue.isEmpty()) {
            if (min > queue.peek()) {
                queue.poll();
            } else {
                break;
            }
        }
        queue.offer(t);
        return queue.size();
    }
}

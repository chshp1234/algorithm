package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test
import java.util.Deque
import java.util.LinkedList
import java.util.Queue

//有两个水壶，容量分别为 jug1Capacity 和 jug2Capacity 升。水的供应是无限的。确定是否有可能使用这两个壶准确得到 targetCapacity 升。
//
//如果可以得到 targetCapacity 升水，最后请用以上水壶中的一或两个来盛放取得的 targetCapacity 升水。
//
//你可以：
//
//装满任意一个水壶
//清空任意一个水壶
//从一个水壶向另外一个水壶倒水，直到装满或者倒空
//
//
//示例 1:
//
//输入: jug1Capacity = 3, jug2Capacity = 5, targetCapacity = 4
//输出: true
//解释：来自著名的 "Die Hard"
//示例 2:
//
//输入: jug1Capacity = 2, jug2Capacity = 6, targetCapacity = 5
//输出: false
//示例 3:
//
//输入: jug1Capacity = 1, jug2Capacity = 2, targetCapacity = 3
//输出: true
//
//
//提示:
//
//1 <= jug1Capacity, jug2Capacity, targetCapacity <= 106
class 水壶问题 {

    @Test
    fun 水壶问题() {
        println(
            "水壶问题:${canMeasureWater(3, 5, 7)}"
        )
    }

    fun canMeasureWater(jug1Capacity: Int, jug2Capacity: Int, targetCapacity: Int): Boolean {
        //数学，广度优先搜索
        //对因为两个水壶大小不一，所以我们可以将其中一壶装满水，倒入另一壶中，此时另一壶，那么如果另一个水壶装满了，剩下的水量就是可以量出的水量；如果另一个水壶没装满，那此时的水量也将是可以量出的水量。、
        //用一个队列queue记录当前量出的水量，再用一个哈希表visit记录已经测量过的水量。
        //每次从队列中取出量出的水量last，如果该水量没有测量过，则将其进行下一步测量，并加入测量记录中：
        //1.将last装入jug2Capacity（假设jug1Capacity<jug2Capacity）中（测量的水量不会超过jug2Capacity）。
        //1.1.if (jug1Capacity + last <= jug2Capacity)，那么jug1Capacity可以将水全部倒入jug2Capacity中，那么此时jug2Capacity中水量就是可测出的水量
        //1.2.否则，jug2Capacity装满后，jug1Capacity中剩余的就是可测出的水量。
        //2.将last装入jug1Capacity中
        //2.1.if (last <= jug1Capacity)，那么将jug2Capacity中的水倒入jug1Capacity中，jug2Capacity中剩余的水量就是可测量的水量
        //3.将可测量出的水量再加入队列中
        //4.判断当前可测量的水量是否等于targetCapacity
        if (targetCapacity > jug1Capacity + jug2Capacity) {
            return false
        }
        if (jug1Capacity > jug2Capacity) {
            return canMeasureWater(jug2Capacity, jug1Capacity, targetCapacity)
        }

        val visit = HashSet<Int>()

        val queue: Queue<Int> = LinkedList()
        queue.add(0)

        while (!queue.isEmpty()) {
            val last = queue.poll()
            //判断当前可测量的水量是否等于targetCapacity
            if (last == targetCapacity
                || (last + jug1Capacity == targetCapacity)
                || (last <= jug1Capacity && last + jug2Capacity == targetCapacity)
            ) {
                return true
            }
            if (!visit.add(last)) {
                continue
            }
            //将last装入jug2Capacity（假设jug1Capacity<jug2Capacity）中（测量的水量不会超过jug2Capacity）。
            if (jug1Capacity + last <= jug2Capacity) {
                //那么jug1Capacity可以将水全部倒入jug2Capacity中，那么此时jug2Capacity中水量就是可测出的水量
                queue.offer(jug1Capacity + last)
            } else {
                //jug2Capacity装满后，jug1Capacity中剩余的就是可测出的水量。
                queue.offer(jug1Capacity - (jug1Capacity + last - jug2Capacity))
            }

            if (last <= jug1Capacity) {
                //那么将jug2Capacity中的水倒入jug1Capacity中，jug2Capacity中剩余的水量就是可测量的水量
                queue.offer(jug2Capacity - (jug1Capacity - last))
            } /*else {
                queue.offer(last - jug1Capacity)
            }*/

        }

        return false
    }

    //方法二：数学
    //思路及算法
    //
    //预备知识：贝祖定理
    //
    //我们认为，每次操作只会让桶里的水总量增加 x，增加 y，减少 x，或者减少 y。
    //
    //你可能认为这有问题：如果往一个不满的桶里放水，或者把它排空呢？那变化量不就不是 x 或者 y 了吗？接下来我们来解释这一点：
    //
    //首先要清楚，在题目所给的操作下，两个桶不可能同时有水且不满。因为观察所有题目中的操作，操作的结果都至少有一个桶是空的或者满的；
    //
    //其次，对一个不满的桶加水是没有意义的。因为如果另一个桶是空的，那么这个操作的结果等价于直接从初始状态给这个桶加满水；而如果另一个桶是满的，那么这个操作的结果等价于从初始状态分别给两个桶加满；
    //
    //再次，把一个不满的桶里面的水倒掉是没有意义的。因为如果另一个桶是空的，那么这个操作的结果等价于回到初始状态；而如果另一个桶是满的，那么这个操作的结果等价于从初始状态直接给另一个桶倒满。
    //
    //因此，我们可以认为每次操作只会给水的总量带来 x 或者 y 的变化量。因此我们的目标可以改写成：找到一对整数 a,b，使得
    //
    //ax+by=z
    //而只要满足 z≤x+y，且这样的 a,b 存在，那么我们的目标就是可以达成的。这是因为：
    //
    //若 a≥0,b≥0，那么显然可以达成目标。
    //
    //若 a<0，那么可以进行以下操作：
    //
    //1.往 y 壶倒水；
    //2.把 y 壶的水倒入 x 壶；
    //3.如果 y 壶不为空，那么 x 壶肯定是满的，把 x 壶倒空，然后再把 y 壶的水倒入 x 壶。
    //4.重复以上操作直至某一步时 x 壶进行了 aaa 次倒空操作，y 壶进行了 bbb 次倒水操作。
    //
    //若 b<0，方法同上，x 与 y 互换。
    //
    //而贝祖定理告诉我们，ax+by=z有解当且仅当 z 是 x,y 的最大公约数的倍数。因此我们只需要找到 x,y 的最大公约数并判断 z 是否是它的倍数即可。
    //
    //作者：力扣官方题解
    //链接：https://leetcode.cn/problems/water-and-jug-problem/solutions/161010/shui-hu-wen-ti-by-leetcode-solution/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    /*public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) {
            return false;
        }
        if (x == 0 || y == 0) {
            return z == 0 || x + y == z;
        }
        return z % gcd(x, y) == 0;
    }

    public int gcd(int x, int y) {
        int remainder = x % y;
        while (remainder != 0) {
            x = y;
            y = remainder;
            remainder = x % y;
        }
        return y;
    }*/
}
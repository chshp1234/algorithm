package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
//
//例如：
//
//[2,3,4]，中位数是 3
//[2,3]，中位数是 (2 + 3) / 2 = 2.5
//给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
//
// 
//
//示例：
//
//给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
//
//窗口位置                      中位数
//---------------               -----
//[1  3  -1] -3  5  3  6  7       1
// 1 [3  -1  -3] 5  3  6  7      -1
// 1  3 [-1  -3  5] 3  6  7      -1
// 1  3  -1 [-3  5  3] 6  7       3
// 1  3  -1  -3 [5  3  6] 7       5
// 1  3  -1  -3  5 [3  6  7]      6
// 因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
//
// 
//
//提示：
//
//你可以假设 k 始终有效，即：k 始终小于输入的非空数组的元素个数。
//与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/sliding-window-median
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 滑动窗口中位数 {
    @Test
    public void 滑动窗口中位数() {
        int[] ints = {-2147483648, -2147483648};
        System.out.println("滑动窗口中位数:" + Arrays.toString(medianSlidingWindow(ints, 2)));
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        //双优先队列
        //首先，暴力解法，每次窗口滑动，都对窗口内的数组进行排序。但这样，时间复杂度会很高。

        //可以使用两个优先队列（堆）数据结构，small和large。
        //其中，两个队列代表，在窗口数组中的数，small是大顶堆，代表数组中所有小的数，large是小顶堆，代表数组中所有大的数。

        //其中small和large大小对半，也就是：
        //如果k为偶数，那么small和large中的数的数量将相等；
        //如果k为奇数，那么small中的数将比large中的数多一个。
        //这样，我们可以非常快的求出窗口数组的中位数：要么为small的堆顶元素，要么为small和large的堆顶元素的平均数。

        //对于加入操作insert(num)，如果num<=small的堆顶元素，将那么num就属于small队列中，如果num>large的堆顶元素，那么num就属于large队列中，
        //但是这样加入操作之后，small队列和large队列元素将会“不平衡”，也就是不满足对半的性质，所以在加入操作之后，我们还要对队列进行一个“平衡”的操作：
        //如果small队列中的数量比large中的多了两个，则将small队列堆顶元素移至large队列中；
        //如果large队列中的元素比small中的多了一个，就将large中的堆顶元素移至small队列中。
        //这样就可以使两个队列重新“保持平衡”。

        //对于删除操作remove(num)，因为优先队列只能操作堆顶元素，没法操作中间元素，所以我们如果要删除的数num在队列中间，我们没法直接删除，
        //那么我们就采用“延迟删除”技巧，使用Map<Integer, Integer>记录待删除的元素的值和要删除的次数。
        //如果num<=small堆顶元素，就说明要删除small中的元素，反之要删除large中的元素。
        //因为使用了延迟删除，所以这两个队列中的元素的实际个数，与需要计算的“平衡”的个数将不一样，就需要额外的两个变量smallSize和largeSize来计算实操后的数量。

        //既然有延迟删除，那么肯定需要一个立即删除操作。
        //在这个方法中，我们判断堆顶的元素，是否在之前缓存的待删除项中，如果是，就弹出堆顶元素，继续循环判断；如果不是则可跳出循环。
        //此时这个队列顶部的元素不是需要删除的元素，在计算中位数时，可以“安全”的拿出来使用。
        //那么我们在什么时候需要“立即删除”呢？
        //1.在平衡操作后，堆顶元素发生变动（减少）的那个队列中，需要其重新堆调整后的堆顶元素是否需要移除；
        //2.在延迟删除中，也许会出现堆顶元素就是需要删除的元素，所以也需要对这个队列进行“立即删除”操作


        DualPriorityQueue dualPriorityQueue = new DualPriorityQueue(k);

        int len = nums.length - k + 1;
        double[] doubles = new double[len];
        for (int i = 0; i < k; i++) {
            dualPriorityQueue.insert(nums[i]);
        }
        doubles[0] = dualPriorityQueue.getMidNum();
        for (int i = 1; i < len; i++) {
            dualPriorityQueue.removeDelay(nums[i - 1]);
            dualPriorityQueue.insert(nums[i - 1 + k]);
            doubles[i] = dualPriorityQueue.getMidNum();
        }
        return doubles;

    }

    public static class DualPriorityQueue {
        // 大根堆，维护较小的一半元素
        PriorityQueue<Integer> large;
        // 小根堆，维护较大的一半元素
        PriorityQueue<Integer> small;
        // 哈希表，记录「延迟删除」的元素，key 为元素，value 为需要删除的次数
        Map<Integer, Integer>  removes;
        // small 和 large 当前包含的元素个数，需要扣除被「延迟删除」的元素，所以大小将会和队列元素实际大小不同
        int                    smallSize = 0;
        int                    largeSize = 0;

        public DualPriorityQueue(int k) {
            small = new PriorityQueue<>((t0, t1) -> t1.compareTo(t0));
            large = new PriorityQueue<>((integer, t1) -> integer.compareTo(t1));
            removes = new HashMap<>();
        }

        //O（1）的复杂度获取中位数
        public double getMidNum() {
            return (smallSize == largeSize) ? (double) small.peek() / 2 + (double) large.peek() / 2 : small.peek();
        }

        public void insert(int num) {
            //根据num和两个队列堆顶元素进行比较，判断num应放入哪个队列中
            if (smallSize == 0 || num <= small.peek()) {
                small.offer(num);
                smallSize++;
            } else {
                large.offer(num);
                largeSize++;
            }
            //重新平衡两个队列，使其中元素个数“对半”
            makeBalance();
        }

        // 调整 small 和 large 中的元素个数，使得二者的元素个数满足要求
        public void makeBalance() {
            // small 比 large 元素多 2 个
            if (smallSize - 1 > largeSize) {
                large.offer(small.poll());
                smallSize--;
                largeSize++;
                removeImmediately(small);
            }
            // large 比 small 元素多 1 个
            else if (largeSize > smallSize) {
                small.offer(large.poll());
                smallSize++;
                largeSize--;
                removeImmediately(large);
            }
        }

        public void removeDelay(int num) {
            removes.put(num, removes.getOrDefault(num, 0) + 1);
            if (smallSize != 0) {
                if (num <= small.peek()) {
                    smallSize--;
                    removeImmediately(small);
                } else {
                    largeSize--;
                    removeImmediately(large);
                }
            }
            makeBalance();
        }

        // 不断地弹出 queue 的堆顶元素，并且更新哈希表
        public void removeImmediately(PriorityQueue<Integer> queue) {
            while (!queue.isEmpty()) {
                Integer top = queue.peek();
                if (removes.containsKey(top)) {
                    removes.put(top, removes.get(top) - 1);
                    if (removes.get(top) == 0) {
                        removes.remove(top);
                    }
                    queue.poll();
                } else {
                    break;
                }
            }
        }
    }
}

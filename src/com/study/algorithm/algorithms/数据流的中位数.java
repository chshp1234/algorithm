package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.PriorityQueue;

public class 数据流的中位数 {
    @Test
    public void 数据流的中位数() {
        数据流的中位数 中位数 = new 数据流的中位数();
        中位数.addNum(1);
        中位数.addNum(2);
        中位数.findMedian();
        中位数.addNum(3);
        中位数.findMedian();
    }

    //双堆
    //用大顶堆表示数据流中较小的一半数
    //用小顶堆表示数据流中较大的一半数
    //添加元素时，保持两个堆的数量一致或者大顶堆数量比小顶堆多1即可
    //那么当两个堆的元素相同时，中位数是两个堆顶元素的平均数，否则中位数是大顶堆的堆顶元素

    /**
     * initialize your data structure here.
     */

    PriorityQueue<Integer> high = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
    PriorityQueue<Integer> low = new PriorityQueue<>(Integer::compare);

    int lowSize = 0;
    int highSize = 0;

    public 数据流的中位数() {

    }

    public void addNum(int num) {
        if (highSize == 0) {
            high.offer(num);
            highSize++;
            return;
        }
        if (num <= high.peek()) {
            high.offer(num);
            highSize++;
            balance();
            return;
        }
        low.offer(num);
        lowSize++;
        balance();
    }

    public void balance() {
        if (highSize - 1 > lowSize) {
            low.offer(high.poll());
            highSize--;
            lowSize++;
            return;
        }
        if (lowSize > highSize) {
            high.offer(low.poll());
            highSize++;
            lowSize--;
        }
    }

    public double findMedian() {
        return highSize > lowSize ? high.peek() : ((double) high.peek() + (double) low.peek()) / 2;
    }
}

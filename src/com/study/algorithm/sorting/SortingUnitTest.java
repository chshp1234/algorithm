package com.study.algorithm.sorting;

import org.junit.After;
import org.junit.Before;

public class SortingUnitTest {

    private long start = 0;

    @Before
    public void start() {
        start = System.nanoTime();
    }

    @After
    public void end() {
        System.out.println("耗时：" + ((float) (System.nanoTime() - start) / 1000_000) + "毫秒");
    }

    protected void swap(int[] arr, int src, int dst) {
        int temp = arr[src];
        arr[src] = arr[dst];
        arr[dst] = temp;
    }

}

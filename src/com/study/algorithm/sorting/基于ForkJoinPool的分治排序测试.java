package com.study.algorithm.sorting;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * 初步测试,当数组容量大于2^15-1(32_767)时,使用ForkJoinPool(当然,任务之间没有依赖关系,也可以使用线程池)对快排进行分治优化,可以提高排序算法的效率<br>
 * 当然我这测试的还是普通的快排,实际还可参考集合框架中的排序思路,对数据大小以及数据有序性进一步提高排序算法的效率<br>
 * 不止快排,归并排序也一样,既然用到了分治的思路,那为何还要串行的去执行任务呢,使用多线程分而治之才是分治的优势<br>
 */
public class 基于ForkJoinPool的分治排序测试 {

    //
    private long start = 0;
    int[] ints = new int[Integer.MAX_VALUE >> 15];

    {
        for (int i = 0; i < ints.length; i++) {
            ints[i] = ints.length - i;
        }
    }


    @Before
    public void start() {
        System.out.println("排序开始,数组大小=" + ints.length);
    }

    @Test
    public void sortWithCollections() {
        start = System.nanoTime();
        Arrays.sort(ints);
        System.out.println("耗时：" + ((float) (System.nanoTime() - start) / 1000_000) + "毫秒");
    }

    @Test
    public void sortWithForkJoinPool() {
        quickSort(ints);
    }

    private void quickSort(int[] arr) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<?> task = new RecursiveAction() {
            @Override
            protected void compute() {
                qSort(arr, 0, arr.length - 1);
            }
        };

        start = System.nanoTime();
        pool.invoke(task);
        System.out.println("耗时：" + ((float) (System.nanoTime() - start) / 1000_000) + "毫秒");
        pool.shutdownNow();
    }


    private void qSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot;
            // 计算分割关键字
            // 结束后，low位置左侧数据皆小于关键字，右侧数据皆大于关键字
            pivot = partition(arr, low, high);
            ForkJoinTask task1 = new RecursiveAction() {
                @Override
                protected void compute() {
                    // 以关键字左边继续排序
                    qSort(arr, low, pivot - 1);
                }
            };
            ForkJoinTask task2 = new RecursiveAction() {
                @Override
                protected void compute() {
                    // 以关键字右边继续排序
                    qSort(arr, pivot + 1, high);
                }
            };
            task1.fork();
            task2.fork();
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivotKey = arr[low];
        while (low < high) {
            // 高位右移
            while (low < high && arr[high] >= pivotKey) {
                high--;
            }
            arr[low] = arr[high];
            //            low++;
            //            swap(arr, low, high);

            // 低位左移
            while (low < high && arr[low] <= pivotKey) {
                low++;
            }
            arr[high] = arr[low];
            //            high--;
            //            swap(arr, low, high);
        }
        arr[low] = pivotKey;
        return low;
    }
}

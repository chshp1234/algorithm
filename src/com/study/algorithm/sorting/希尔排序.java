package com.study.algorithm.sorting;

import org.junit.Test;

import java.util.Arrays;

// 1959年Shell发明，第一个突破O(n2)的排序算法，是简单插入排序的改进版。
// 它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。
// 希尔排序的思想就是把大规模的数据拆分成多个小规模数据，然后每部分分别进行直接插入排序，最后再对全部数据进行整体排序.
public class 希尔排序 extends SortingUnitTest {

    // 希尔排序的核心在于间隔序列的设定。既可以提前设定好间隔序列，也可以动态的定义间隔序列。
    // 动态定义间隔序列的算法是《算法（第4版）》的合著者Robert Sedgewick提出的。
    @Test
    public void 希尔排序() {
        int[] ints = {0, 9, 1, 5, 8, 3, 7, 4, 6, 2, 11};
        shellSort(ints);
        /*todo 继续*/
        System.out.println("希尔排序：" + Arrays.toString(ints));
    }

    // 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，具体算法描述：
    //
    // 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
    // 按增量序列个数k，对序列进行k 趟排序；
    // 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
    // 仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
    public void shellSort(int[] arr) {
        int len = arr.length;
        int inc = len;
        // 设置间隔值
        for (inc = len / 2; inc > 0; inc /= 2) {
            // i 从inc走到len，j正好可以把所有子数组遍历一次
            // j会先比较每个子数组的第一个值，再第二个值，这样横向进行遍历
            for (int i = inc; i < len; i++) {
                for (int j = i; j >= inc && arr[j] < arr[j - inc]; j -= inc) {
                    swap(arr, j, j - inc);
                }
            }
        }
    }
    /**
     * Shell sort.
     *
     * @param arr the arr
     */
    public void shellSort2(int[] arr) {
        int len = arr.length;
        int inc = len;
        int indexTemp;
        // 设置间隔值
        for (inc = len / 2; inc > 0; inc /= 2) {
            // i 从inc走到len，j正好可以把所有子数组遍历一次
            // j会先比较每个子数组的第一个值，再第二个值，这样横向进行遍历

            for (int i = 0; i < inc; i++) {
                for (int j = i; j < len; j += inc) {
                    indexTemp = j;
                    for (int k = j; k >= i; k -= inc) {
                        if (arr[indexTemp] < arr[k]) {
                            swap(arr, indexTemp, k);
                            indexTemp = k;
                        }
                    }
                }
            }
        }
    }
}

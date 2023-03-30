package com.study.algorithm.sorting;

import org.junit.Test;

import java.util.Arrays;

// 简单选择排序法就是通过n-i次关键字间的比较，从n-i+1个记录中选出关键字最小的记录，并和第 i (0≤i≤n)个记录交换。
public class 简单选择排序 extends SortingUnitTest {

    // 表现最稳定的排序算法之一，因为无论什么数据进去都是O(n2)的时间复杂度，所以用到它的时候，数据规模越小越好。
    // 唯一的好处可能就是不占用额外的内存空间了吧。理论上讲，选择排序可能也是平时排序一般人想到的最多的排序方法了吧。
    @Test
    public void 简单选择排序() {
        int[] ints = {0, 9, 1, 5, 8, 3, 7, 4, 6, 2, 11};
        selectSort(ints);
        /*todo 继续*/
        System.out.println("简单选择排序：" + Arrays.toString(ints));
    }

    // n个记录的直接选择排序可经过n-1趟直接选择排序得到有序结果。具体算法描述如下：
    //
    // 初始状态：无序区为R[1..n]，有序区为空；
    // 第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。该趟排序从当前无序区中-选出关键字最小的记录
    // R[k]，将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
    // n-1趟结束，数组有序化了。
    private void selectSort(int[] arr) {
        int len = arr.length;
        int min;
        for (int i = 0; i < len; i++) {
            min = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (i != min) {
                swap(arr, i, min);
            }
        }
    }
}

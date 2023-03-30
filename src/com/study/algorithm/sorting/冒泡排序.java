package com.study.algorithm.sorting;

import org.junit.Test;

import java.util.Arrays;

// 冒泡排序（Bubble Sort）是一种交换排序，它的基本思想是：两两比较相邻记录的关键字，如果反序则交换，直到没有反序的记录为止。
public class 冒泡排序 extends SortingUnitTest {

    @Test
    public void 冒泡排序() {
        int[] ints = {0, 9, 1, 5, 8, 3, 7, 4, 6, 2, 11, 0, 9};
        bubbleSort(ints);
        /*todo 继续*/
        System.out.println("冒泡排序：" + Arrays.toString(ints));
    }

    // 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
    // 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
    // 针对所有的元素重复以上的步骤，除了最后一个；
    // 重复步骤1~3，直到排序完成。
    private void bubbleSort(int[] arr) {
        int len = arr.length;
        /*flag用来作标记，初始化为true用于第一次进入循环*/
        boolean flag = true;
        /*若flag为true则退出循环*/
        for (int i = 0; i < len - 1 && flag; i++) {
            flag = false;
            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    /*交换L->data[j]与L->data[j+1]的值*/
                    swap(arr, j, j + 1);
                    /*如果有数据交换，则flag为true*/
                    flag = true;
                }
            }
        }

    }
}

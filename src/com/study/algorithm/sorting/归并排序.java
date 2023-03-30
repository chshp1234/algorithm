package com.study.algorithm.sorting;

import org.junit.Test;

import java.util.Arrays;

// 归并排序也利用了完全二叉树，从而把时间复杂度降低到O(nlogn)。
// 归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
// 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。

public class 归并排序 extends SortingUnitTest {

    // 归并排序是一种稳定的排序方法。
    // 和选择排序一样，归并排序的性能不受输入数据的影响，但表现比选择排序好的多，因为始终都是O(nlogn）的时间复杂度。代价是需要额外的内存空间。
    @Test
    public void 归并排序() {
        int[] ints = {50, 20, 90, 30, 80, 40, 7, 60};
        mergeSort(ints);
        /*todo 继续*/
        System.out.println("归并排序：" + Arrays.toString(ints));
    }

    // 把长度为n的输入序列分成两个长度为n/2的子序列；
    // 对这两个子序列分别采用归并排序；
    // 将两个排序好的子序列合并成一个最终的排序序列。
    public void mergeSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int[] temp = new int[arr.length];
        //        mergeSort(arr, temp, 0, arr.length - 1);
        mergeSort2(arr, temp);
    }
    // 归并排序——递归法
    private void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        // 左边分到数组大小为1
        mergeSort(arr, temp, left, mid);
        // 右边分到数组大小为1
        mergeSort(arr, temp, mid + 1, right);
        // 合并
        merge(arr, temp, left, mid, right);
    }
    // 归并排序——线性迭代法
    private void mergeSort2(int[] arr, int[] temp) {

        // 初始比较长度从1开始
        for (int length = 1, l = arr.length; length <= l; length = length << 1) {

            // 当前长度下需要比较归并的循环
            for (int tempIndex = 0; tempIndex < l; tempIndex = length * 2 + tempIndex) {

                // 剩余数据长度小于length
                if ((tempIndex + length) <= (l - 1)) {
                    // 剩余数据长度还可用于两次归并
                    if ((tempIndex + length * 2) <= l) {
                        merge(
                                arr,
                                temp,
                                tempIndex,
                                tempIndex + length - 1,
                                tempIndex + length * 2 - 1);
                    } else {
                        merge(arr, temp, tempIndex, tempIndex + length - 1, l - 1);
                    }
                }
            }
        }
    }

    // 比较归并左右俩数组
    private void merge(int[] arr, int[] temp, int left, int mid, int right) {
        if (left >= right) {
            return;
        }
        int mLeft = left;
        int mRight = mid + 1;
        int tempIndex = 0;

        while (mLeft <= mid && mRight <= right) {
            if (arr[mLeft] < arr[mRight]) {
                temp[tempIndex++] = arr[mLeft++];
            } else {
                temp[tempIndex++] = arr[mRight++];
            }
        }

        while (mLeft <= mid) {
            temp[tempIndex++] = arr[mLeft++];
        }

        while (mRight <= right) {
            temp[tempIndex++] = arr[mRight++];
        }

        tempIndex = 0;
        while ((left + tempIndex) <= right) {
            arr[left + tempIndex] = temp[tempIndex++];
        }
    }
}

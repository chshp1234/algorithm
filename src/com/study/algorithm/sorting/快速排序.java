package com.study.algorithm.sorting;

import org.junit.Test;

import java.util.Arrays;

// 快速排序：通过一趟排序将待排记录分割成独立的两部分，其中一部分记录的关键字均比另一部分记录的关键字小，
// 则可分别对这两部分记录继续进行排序，以达到整个序列有序的目的。
public class 快速排序 extends SortingUnitTest {

    @Test
    public void 快速排序() {
        int[] ints = {50, 20, 90, 30, 80, 40, 7, 60, 110, 5};
        quickSort(ints);
        /*todo 继续*/
        System.out.println("快速排序：" + Arrays.toString(ints));
    }

    // 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
    //
    // 从数列中挑出一个元素，称为 “基准”（pivot）；
    // 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
    // 在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
    // 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
    private void quickSort(int[] arr) {
        qSort(arr, 0, arr.length - 1);
    }

    private void qSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot;
            // 计算分割关键字
            // 结束后，low位置左侧数据皆小于关键字，右侧数据皆大于关键字
            pivot = partition(arr, low, high);
            // 以关键字左边继续排序
            qSort(arr, low, pivot - 1);
            // 以关键字右边继续排序
            qSort(arr, pivot + 1, high);
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

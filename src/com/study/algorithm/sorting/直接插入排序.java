package com.study.algorithm.sorting;

import org.junit.Test;

import java.util.Arrays;

// 直接插入排序和我们排序扑克牌的道理一致，就是把新的一张牌插入到已经排好序的牌中，
// 它的基本操作是将一个记录插入到已经排好序的有序表中，从而得到一个新的、记录数增1的有序表.
public class 直接插入排序 extends SortingUnitTest {

    // 插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），
    // 因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
    @Test
    public void 直接插入排序() {
        int[] ints = {0, 9, 1, 5, 8, 3, 7, 4, 6, 2, 11};
        insertSort(ints);
        /*todo 继续*/
        System.out.println("直接插入排序：" + Arrays.toString(ints));
    }

    // 一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：
    //
    // 从第一个元素开始，该元素可以认为已经被排序；
    // 取出下一个元素，在已经排序的元素序列中从后向前扫描；
    // 如果该元素（已排序）大于新元素，将该元素移到下一位置；
    // 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
    // 将新元素插入到该位置后；
    // 重复步骤2~5。
    private void insertSort(int[] arr) {
        int len = arr.length;
        int temp;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                /*todo 太蠢，可优化*/
                if (arr[i] < arr[j]) {
                    temp = arr[i];
                    // j及以后的记录向后移动一位，然后把当前值存放在j的位置
                    System.arraycopy(arr, j, arr, j + 1, i - j);
                    arr[j] = temp;
                }
            }
        }
    }
}

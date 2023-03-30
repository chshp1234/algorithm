package com.study.algorithm.sorting;

import org.junit.Test;

import java.util.Arrays;

// 堆排序是对简单选择排序的优化，
// 在简单选择排序中，每排序一个数据，都要在剩余全部数据中寻找最小值，但是在这个寻找的过程中，没有对剩余的数据记录，
// 所以之后的寻找会进行多次重复操作。堆排序则是会把这些数据记录在堆中，之后的寻找只需要在堆中进行。
//
// 堆是具有下列性质的完全二叉树：
// 每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆；
// 每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆。
public class 堆排序 extends SortingUnitTest {

    @Test
    public void 堆排序() {
        int[] ints = {50, 20, 90, 30, 80, 40, 7, 60, 10};
        heapSort(ints);
        /*todo 继续*/
        System.out.println("堆排序：" + Arrays.toString(ints));
    }
    // 将初始待排序关键字序列(R1,R2….Rn)构建成大顶堆，此堆为初始的无序区；
    // 将堆顶元素R[1]与最后一个元素R[n]交换，此时得到新的无序区(R1,R2,……Rn-1)和新的有序区(Rn),且满足R[1,2…n-1]<=R[n]；
    // 由于交换后新的堆顶R[1]可能违反堆的性质，因此需要对当前无序区(R1,R2,……Rn-1)调整为新堆，
    // 然后再次将R[1]与无序区最后一个元素交换，得到新的无序区(R1,R2….Rn-2)和新的有序区(Rn-1,Rn)。
    // 不断重复此过程直到有序区的元素个数为n-1，则整个排序过程完成。
    public void heapSort(int[] arr) {
        int len = arr.length;

        // 从最后一个有孩子的结点开始，逐一进行堆的调整
        for (int i = (len - 2) / 2; i >= 0; i--) {
            heapAdjust(arr, i, len);
        }

        // 对于一个堆，最大值一定在根结点，也就是在数组位置0，把它换到数组最后，然后对剩余的数据再进行一次堆的调整
        for (int i = len - 1; i > 0; i--) {
            // 把最大值放在数组的最后
            swap(arr, 0, i);

            // 剩余的值进行堆的调整
            heapAdjust(arr, 0, i);
        }
    }
    /** 堆的调整 root：子树的根结点位置 len：当前排序数组的长度 */
    private void heapAdjust(int[] arr, int root, int len) {
        if (len <= 0) return;
        int temp;
        // 根结点的值先保存
        temp = arr[root];
        // i是这个结点的左孩子，或者是它孩子的左孩子
        for (int i = 2 * root + 1; i < len; i = 2 * i + 1) {
            if (i < len - 1 && arr[i] < arr[i + 1]) {
                // 寻找到两个孩子的较大者
                i++;
            }
            // 根结点的值比两个孩子都大，就不需要再调整了
            if (temp >= arr[i]) {
                break;
            }
            // 把根结点的值记为这个较大的孩子的值
            arr[root] = arr[i];
            // 再向下一级子树遍历
            root = i;
        }
        // 最后把temp的值存放在空置的位置
        arr[root] = temp;
    }
}

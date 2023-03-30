package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//给你两个数组，arr1 和 arr2，
//
//arr2 中的元素各不相同
//arr2 中的每个元素都出现在 arr1 中
//对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
//
// 
//
//示例：
//
//输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//输出：[2,2,2,1,4,3,3,9,6,7,19]
// 
//
//提示：
//
//arr1.length, arr2.length <= 1000
//0 <= arr1[i], arr2[i] <= 1000
//arr2 中的元素 arr2[i] 各不相同
//arr2 中的每个元素 arr2[i] 都出现在 arr1 中
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/relative-sort-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 数组的相对排序 {
    @Test
    public void 数组的相对排序() {
        int[] ints = {9, 9, 9, 9, 9, 9};
        int[] ints2 = {2, 1, 4, 3, 9, 6};
        System.out.println("2,3,1,3,2,4,6,7,9,2,19：" + Arrays.toString(ints));
        System.out.println("数组的相对排序：" + Arrays.toString(relativeSortArray(ints, ints2)));
    }

    Map<Integer, Integer> map = new HashMap<>();

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // 自定义排序
        // 使用哈希表记录arr2数组中每个元素以及对应的位置
        // 对arr1自定义排序规则;
        // 若元素在arr2中（可在哈希表中找到值），则按哈希表中返回的值比较
        // 若不在，则按升序排序
        // 若一个在一个不在，则不在哈希表的元素始终大于在哈希表的元素
        for (int i = 0, l = arr2.length; i < l; i++) {
            map.put(arr2[i], i);
        }

        quickSort(arr1);
        return arr1;
    }

    private void quickSort(int[] arr) {
        qSort(arr, 0, arr.length - 1);
    }

    private void qSort(int[] arr, int low, int high) {
        int pivot;
        if (low < high) {
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
            while (low < high && compare(arr[high], pivotKey) >= 0) {
                high--;
            }
            arr[low] = arr[high];
            //            swap(arr, low, high);

            // 低位左移
            while (low < high && compare(arr[low], pivotKey) <= 0) {
                low++;
            }
            arr[high] = arr[low];
            //            swap(arr, low, high);
        }
        arr[low] = pivotKey;
        return low;
    }

    public int compare(int i1, int i2) {
        Integer integer1 = map.get(i1);
        Integer integer2 = map.get(i2);
        if (integer1 == null && integer2 == null) {
            return i1 - i2;
        } else if (integer1 == null) {
            return 1;
        } else if (integer2 == null) {
            return -1;
        } else {
            return integer1 - integer2;
        }
    }

    //方法二：计数排序
    //思路与算法
    //
    //注意到本题中元素的范围为 [0,1000]，这个范围不是很大，我们也可以考虑不基于比较的排序，例如「计数排序」。
    //
    //具体地，我们使用一个长度为 1001（下标从 0 到 1000）的数组 frequency，记录每一个元素在数组 arr1中出现的次数。
    // 随后我们遍历数组 arr2 ，当遍历到元素 x 时，我们将frequency[x] 个 x 加入答案中，并将 frequency[x] 清零。
    // 当遍历结束后，所有在 arr2中出现过的元素就已经有序了。
    //
    //此时还剩下没有在arr2中出现过的元素，因此我们还需要对整个数组 frequency 进行一次遍历。
    // 当遍历到元素 x 时，如果 \frequency[x] 不为 0，我们就将 frequency[x] 个 x 加入答案中。
    //
    // 细节：
    //我们可以对空间复杂度进行一个小优化。
    // 实际上，我们不需要使用长度为 1001 的数组，而是可以找出数组 arr1中的最大值upper，使用长度为 upper+1 的数组即可。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/relative-sort-array/solution/shu-zu-de-xiang-dui-pai-xu-by-leetcode-solution/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    /*public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int upper = 0;
        for (int x : arr1) {
            upper = Math.max(upper, x);
        }
        int[] frequency = new int[upper + 1];
        for (int x : arr1) {
            ++frequency[x];
        }
        int[] ans = new int[arr1.length];
        int index = 0;
        for (int x : arr2) {
            for (int i = 0; i < frequency[x]; ++i) {
                ans[index++] = x;
            }
            frequency[x] = 0;
        }
        for (int x = 0; x <= upper; ++x) {
            for (int i = 0; i < frequency[x]; ++i) {
                ans[index++] = x;
            }
        }
        return ans;
    }*/

}

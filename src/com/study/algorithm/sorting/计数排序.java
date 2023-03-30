package com.study.algorithm.sorting;

import org.junit.Test;

import java.util.Arrays;

// 计数排序是一个非基于比较的排序算法，该算法于1954年由 Harold H. Seward提出。
// 它的优势在于在对一定范围内的整数排序时，它的复杂度为Ο(n+k)（其中k是整数的范围），快于任何比较排序算法。
// 当然这是一种牺牲空间换取时间的做法，而且当O(k)>O(n*log(n))的时候其效率反而不如基于比较的排序
// （基于比较的排序的时间复杂度在理论上的下限是O(n*log(n)),如归并排序，堆排序）
//
// 虽然它可以将排序算法的时间复杂度降低到O(N)，但是有两个前提需要满足：一是需要排序的元素必须是整数
// 二是排序元素的取值要在一定范围内，并且比较集中。只有这两个条件都满足，才能最大程度发挥计数排序的优势。
public class 计数排序 extends SortingUnitTest {

    // 计数排序是一个稳定的排序算法。
    // 当输入的元素是 n 个 0到 k之间的整数时，时间复杂度是O(n+k)，空间复杂度也是O(n+k)，其排序速度快于任何比较排序算法。
    // 当k不是很大并且序列比较集中时，计数排序是一个很有效的排序算法。
    @Test
    public void 计数排序() {
        int[] ints = {50, 20, 90, 30, 80, 40, 7, 60, 110, 5};
        /*todo 继续*/
        System.out.println("计数排序：" + Arrays.toString(countSort(ints)));
    }

    // 找出待排序的数组中最大和最小的元素；
    // 统计数组中每个值为i的元素出现的次数，存入数组C的第i项；
    // 对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；
    // 反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1。
    public int[] countSort(int[] A) {
        // 找出数组A中的最大值、最小值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : A) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        // 初始化计数数组count
        // 长度为最大值减最小值加1
        int[] count = new int[max - min + 1];
        // 对计数数组各元素赋值
        for (int num : A) {
            // A中的元素要减去最小值，再作为新索引
            count[num - min]++;
        }
        // 创建结果数组
        int[] result = new int[A.length];
        /*
        // 方法一：遍历计数数组，从中选出数量>0（count[i] > 0）的元素，依次加入结果数组中

        // 创建结果数组的起始索引
        int index = 0;
        // 遍历计数数组，将计数数组的索引填充到结果数组中
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                // 再将减去的最小值补上
                result[index++] = i + min;
                count[i]--;
            }
        }*/

        // 方法二：对count数组变形，新元素的值是前面元素累加之和的值，即count[i+1] = count[i+1] + count[i];
        // 此时count[i]代表count[i]+min这个元素，应在结果数组中的位置，因为此时count[i]统计了小于等于count[i]+min的所有元素的个数。
        // 注：此时向结果数组中添加时，count[i]--,所以可以发现，结果数组中的元素若有相同时，将按原数组中相同元素逆序排序，如果要保持相同元素
        // 的原本顺序，用倒序的方式遍历原始数组，即从后往前遍历A数组。

        // 计数数组变形，新元素的值是前面元素累加之和的值
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        // 遍历A中的元素，填充到结果数组中去
        for (int j = A.length - 1; j >= 0; j--) {
            result[count[A[j] - min] - 1] = A[j];
            count[A[j] - min]--;
        }

        // 返回结果数组
        return result;
    }
}

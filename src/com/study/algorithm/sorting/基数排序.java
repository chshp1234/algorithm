package com.study.algorithm.sorting;

import org.junit.Test;

import java.util.Arrays;

// 数排序是一种非比较型整数排序算法，其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较、排序、收集。
// 有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序。最后的次序就是高优先级高的在前，高优先级相同的低优先级高的在前。
// 于整数也可以表达字符串（比如名字或日期）和特定格式的浮点数，所以基数排序也不是只能使用于整数。
public class 基数排序 extends SortingUnitTest {

    // 基数排序基于分别排序，分别收集，所以是稳定的。
    // 但基数排序的性能比桶排序要略差，每一次关键字的桶分配都需要O(n)的时间复杂度，而且分配之后得到新的关键字序列又需要O(n)的时间复杂度。
    // 假如待排数据可以分为d个关键字，则基数排序的时间复杂度将是O(d*2n) ，当然d要远远小于n，因此基本上还是线性级别的。
    // 基数排序的空间复杂度为O(n+k)，其中k为桶的数量。一般来说n>>k，因此额外空间需要大概n个左右。
    @Test
    public void 基数排序() {
        // 算法解析：
        // 基数排序的思想就是先排好各位，然后排好各位的基础上排十位，以此类推，直到遍历最高位 次，排序结束（仔细理解最后一句话）
        // 基数排序不是比较排序，而是通过分配和收集的过程来实现排序
        // 初始化10个桶(固定的)，桶下标为0-9
        // 通过得到待排序数字的个十百等位的数字，把这个数字对应的item放到对应的桶中
        // 基数排序有两种排序方式：LSD和MSD，最小位优先(从右边开始)和最大位优先(从左边开始)
        //
        // 作者：流1江春水
        // 链接：https://www.jianshu.com/p/8340dfaea3af
        // 来源：简书
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        // 代码实现
        //
        // （1）MSD法实现
        //
        // 最高位优先法通常是一个递归的过程：
        //
        // <1>先根据最高位关键码K1排序，得到若干对象组，对象组中每个对象都有相同关键码K1。
        //
        // <2>再分别对每组中对象根据关键码K2进行排序，按K2值的不同，再分成若干个更小的子组，每个子组中的对象具有相同的K1和K2值。
        //
        // <3>依此重复，直到对关键码Kd完成排序为止。
        //
        // <4> 最后，把所有子组中的对象依次连接起来，就得到一个有序的对象序列。
        //
        // （2）LSD法实现
        //
        // 最低位优先法首先依据最低位关键码Kd对所有对象进行一趟排序，
        //
        // 再依据次低位关键码Kd-1对上一趟排序的结果再排序，
        //
        // 依次重复，直到依据关键码K1最后一趟排序完成，就可以得到一个有序的序列。
        //
        // 使用这种排序方法对每一个关键码进行排序时，不需要再分组，而是整个对象组。
        int[] ints = {50, 20, 90, 30, 80, 40, 7, 60, 111, 110, 5};
        radixSort(ints);
        /*todo 继续*/
        System.out.println("基数排序：" + Arrays.toString(ints));
    }

    // 取得数组中的最大数，并取得位数；
    // arr为原始数组，从最低位开始取每个位组成radix数组；
    // 对radix进行计数排序（利用计数排序适用于小范围数的特点）；
    private void radixSort(int[] array) {
        int n = 1; // 代表位数对应的数：1,10,100...
        int k = 0; // 保存每一位排序后的结果用于下一位的排序输入
        int d = (int) Math.pow(10, maxLength(array)); // 一个数组中最大数字的位数
        int length = array.length;
        int[][] bucket = new int[10][length]; // 排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
        int[] order = new int[length]; // 用于保存每个桶里有多少个数字
        while (n < d) {
            for (int num : array) // 将数组array里的每个数字放在相应的桶里
            {
                int digit = (num / n) % 10;
                bucket[digit][order[digit]] = num;
                order[digit]++;
            }
            for (int i = 0; i < length; i++) // 将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
            {
                if (order[i] != 0) // 这个桶里有数据，从上到下遍历这个桶并将数据保存到原数组中
                {
                    for (int j = 0; j < order[i]; j++) {
                        array[k] = bucket[i][j];
                        k++;
                    }
                }
                order[i] = 0; // 将桶里计数器置0，用于下一次位排序
            }
            n *= 10;
            k = 0; // 将k置0，用于下一轮保存位排序结果
        }
    }
    /*
     * 一个数组中最大数字的位数
     *
     * @param array
     * @return
     */
    private int maxLength(int[] array) {
        int maxLength = 0;
        int arrayLength = array.length;
        for (int i = 0; i < arrayLength; i++) {
            int currentLength = length(array[i]);
            if (maxLength < currentLength) {
                maxLength = currentLength;
            }
        }

        return maxLength;
    }

    /*
     * 计算一个数字共有多少位
     *
     * @param number
     * @return
     */
    protected int length(long num) {
        if (num == 0) {
            return 1;
        }
        int lenght = 0;
        for (long temp = num; temp != 0; temp /= 10) {
            lenght++;
        }
        return lenght;
    }
}

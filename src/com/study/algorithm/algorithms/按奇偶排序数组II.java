package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

// 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
//
// 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
//
// 你可以返回任何满足上述条件的数组作为答案。
//
//
//
// 示例：
//
// 输入：[4,2,5,7]
// 输出：[4,5,2,7]
// 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
//
//
// 提示：
//
// 2 <= A.length <= 20000
// A.length % 2 == 0
// 0 <= A[i] <= 1000
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 按奇偶排序数组II {
    @Test
    public void 按奇偶排序数组II() {
        int[] ints = {2, 3, 1, 1, 4, 0, 0, 4, 3, 3};
        System.out.println("按奇偶排序数组II:" + Arrays.toString(sortArrayByParityII(ints)));
    }

    public int[] sortArrayByParityII(int[] A) {
        int len = A.length;

        // 方法一：
        // 把原数组中奇数和偶数分别放入两个数组中
        // 再对原数组按奇偶进行填充
        int[] odd = new int[len >> 1];
        int[] even = new int[len >> 1];
        int o = 0, e = 0;
        for (int i = 0; i < len; i++) {

            if (A[i] % 2 == 0) {
                even[e++] = A[i];
            } else {
                odd[o++] = A[i];
            }
        }
        for (int i = 0; i < len; ) {
            A[i++] = even[--e];
            A[i++] = odd[--o];
        }

        // 方法二：
        // 如果当前位置数字不符合条件，则把当前数字和后一位交换，若还不符合条件，再和1+2位置交换...和2n+1位置交换

        /*for (int i = 0; i < len; i++) {
            for (int j = i + 1; ; j = j + 2) {
                if (!(i % 2 == 0 && A[i] % 2 == 0) && !(i % 2 != 0 && A[i] % 2 != 0)) {
                    A[i] ^= A[j];
                    A[j] ^= A[i];
                    A[i] ^= A[j];
                } else {
                    break;
                }
            }
        }*/

        // 方法三：双指针
        // 如果原数组可以修改，则可以使用就地算法求解。
        //
        // 为数组的偶数下标部分和奇数下标部分分别维护指针 i,j。
        // 随后，在每一步中，如果 A[i] 为奇数，则不断地向前移动j（每次移动两个单位），直到遇见下一个偶数。
        // 此时，可以直接将 A[i] 与 A[j] 交换。我们不断进行这样的过程，最终能够将所有的整数放在正确的位置上。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii/solution/an-qi-ou-pai-xu-shu-zu-ii-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        /*int j = 1;
        for (int i = 0; i < n; i += 2) {
            if (A[i] % 2 == 1) {
                while (A[j] % 2 == 1) {
                    j += 2;
                }
                swap(A, i, j);
            }
        }*/

        return A;
    }
}

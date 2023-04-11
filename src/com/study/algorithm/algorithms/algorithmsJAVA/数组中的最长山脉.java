package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 数组中的最长山脉 {
    @Test
    public void 数组中的最长山脉() {
        int[] ints = {
            0, 1, 1, 3, 0, 3, 2, 2, 2, 1, 1, 0, 1, 2, 3, 2, 1, 0, 1, 2, 1, 1, 3, 0, 1, 2, 2, 1
        };
        System.out.println("数组中的最长山脉:" + longestMountain(ints));
    }

    // 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
    //
    // B.length >= 3
    // 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
    // （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
    //
    // 给出一个整数数组 A，返回最长 “山脉” 的长度。
    //
    // 如果不含有 “山脉” 则返回 0。
    //
    //
    //
    // 示例 1：
    //
    // 输入：[2,1,4,7,3,2,5]
    // 输出：5
    // 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
    // 示例 2：
    //
    // 输入：[2,2,2]
    // 输出：0
    // 解释：不含 “山脉”。
    //
    //
    // 提示：
    //
    // 0 <= A.length <= 10000
    // 0 <= A[i] <= 10000
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/longest-mountain-in-array
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int longestMountain(int[] A) {

        // 双指针
        // 根据题意判断即可
        // 双指针指向山脉数组起止位置
        // 1.若数组前后元素相等则需重新计算山脉数组起始位置
        // 2.山脉数组起始位置必须在后面一个元素大于前面一个元素时
        // 3.山脉数组结束位置必须在数组下降到最底部时（后面一个元素出现大于等于前面一个元素时）
        // 为判断‘3’，我们还需一个布尔值判断当前山脉是上升还是下降，只有在下降时才能判断结束位置，并更新最大值

        int len = A.length;
        if (len == 0) {
            return 0;
        }

        boolean up = true;
        int pre = 0;
        int end = 0;
        int result = 0;

        while (end < len) {
            if (pre == end) {
                end++;
                while (end < len && A[end] <= A[end - 1]) {
                    end++;
                }
                pre = end - 1;
                end++;
                up = true;
                continue;
            }
            if (up) {
                if (A[end] > A[end - 1]) {
                    end++;
                } else if (A[end] == A[end - 1]) {
                    pre = end;
                } else {
                    up = false;
                    end++;
                }
                continue;
            }
            if (A[end] < A[end - 1]) {
                end++;
            } else if (A[end] == A[end - 1]) {
                result = Math.max(result, end - pre);
                pre = end;
            } else {
                result = Math.max(result, end - pre);
                pre = end - 1;
                up = true;
                end++;
            }
        }

        if (!up) {
            result = Math.max(result, end - pre);
        }

        return result;
    }

    public int longestMountainByDp(int[] A) {
        // 方法一：枚举山顶
        // 思路与算法
        //
        // 对于一座「山脉」，我们称首元素 B[0] 和尾元素 B[len(B)−1] 为「山脚」，
        // 满足题目要求B[i−1]<B[i]>B[i+1] 的元素 B[i] 为「山顶」。
        // 为了找出数组 A 中最长的山脉，我们可以考虑枚举山顶，再从山顶向左右两侧扩展找到山脚。
        //
        // 由于从左侧山脚到山顶的序列是严格单调递增的，而从山顶到右侧山脚的序列是严格单调递减的，
        // 因此我们可以使用动态规划（也可以理解为递推）的方法，计算出从任意一个元素开始，向左右两侧最多可以扩展的元素数目。
        //
        // 我们用 left[i] 表示 A[i] 向左侧最多可以扩展的元素数目。
        // 如果 A[i−1]<A[i]，那么A[i] 可以向左扩展到 A[i−1]，再扩展 left[i] 个元素，
        // 因此有left[i]=left[i−1]+1
        //
        // 如果 A[i−1]≥A[i]，那么 A[i] 无法向左扩展，因此有left[i]=0
        //
        // 特别地，当 i=0 时，A[i] 为首元素，无法向左扩展，因此同样有left[0]=0
        //
        // 同理，我们用 right[i] 表示 A[i] 向右侧最多可以扩展的元素数目，那么有类似的状态转移方程（递推式）
        //
        // right[i]=
        // right[i+1]+1（A[i]>A[i+1]）；
        // 0（A[i]≤A[i+1] 或 i=n−1）
        //
        //  其中 n 是数组 A 的长度。
        //
        // 在计算出所有的 left[] 以及 right[] 之后，我们就可以枚举山顶。
        // 需要注意的是，只有当left[i] 和 right[i] 均大于 0 时，A[i] 才能作为山顶，
        // 并且山脉的长度为left+right[i]+1。
        //
        // 在所有的山脉中，最长的即为答案。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/longest-mountain-in-array/solution/shu-zu-zhong-de-zui-chang-shan-mai-by-leetcode-sol/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        int n = A.length;
        if (n == 0) {
            return 0;
        }
        int[] left = new int[n];
        for (int i = 1; i < n; ++i) {
            left[i] = A[i - 1] < A[i] ? left[i - 1] + 1 : 0;
        }
        int[] right = new int[n];
        for (int i = n - 2; i >= 0; --i) {
            right[i] = A[i + 1] < A[i] ? right[i + 1] + 1 : 0;
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (left[i] > 0 && right[i] > 0) {
                ans = Math.max(ans, left[i] + right[i] + 1);
            }
        }
        return ans;
    }
}

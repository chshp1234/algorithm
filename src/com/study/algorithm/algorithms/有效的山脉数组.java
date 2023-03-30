package com.study.algorithm.algorithms;

import org.junit.Test;

// 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
//
// 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
//
// A.length >= 3
// 在 0 < i < A.length - 1 条件下，存在 i 使得：
// A[0] < A[1] < ... A[i-1] < A[i]
// A[i] > A[i+1] > ... > A[A.length - 1]
//
//
//
//
//
//
// 示例 1：
//
// 输入：[2,1]
// 输出：false
// 示例 2：
//
// 输入：[3,5,5]
// 输出：false
// 示例 3：
//
// 输入：[0,3,2,1]
// 输出：true
//
//
// 提示：
//
// 0 <= A.length <= 10000
// 0 <= A[i] <= 10000
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/valid-mountain-array
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 有效的山脉数组 {
    @Test
    public void 有效的山脉数组() {
        int[] ints = {0, 1, 2, 3, 4, 8, 9, 10, 11, 12, 11};

        System.out.println("有效的山脉数组:" + validMountainArray(ints));
    }

    public boolean validMountainArray(int[] A) {
        // 从低位0开始扫描，直到A[i]<A[i-1],说明此时已越过山顶，另i=i-1,i为山顶
        // 再从高位开始扫描，直到A[j]<A[j+1]，说明此时已越过山顶，另j=j+1,j为山顶
        // 判断山顶i和j是否相等，相等则此数组为山脉数组

        int len = A.length;
        if (len < 3) {
            return false;
        }

        int low = 1;
        while (low < len && A[low] > A[low - 1]) {
            low++;
        }

        // low=1,说明此数组单调递减，不是山脉数组
        // low=len,说明此数组单调递增，不是山脉数组
        // A[low] = A[low - 1]，也不符合山脉数组
        if (low == 1 || low == (len) || A[low] == A[low - 1]) {
            return false;
        }

        len = len - 2;
        while (len > 0 && A[len] > A[len + 1]) {
            len--;
        }

        // 返回山顶
        low--;
        len++;

        // 若山顶指针相同，则是山脉数组
        return len == low;
    }
}

package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

@Deprecated
public class 行相等的最少多米诺旋转 {
    /*todo 未完成*/
    @Test
    public void 行相等的最少多米诺旋转() {
        int[] a = {1, 1, 1, 2, 2, 2, 2, 1, 1};
        int[] b = {2, 1, 2, 1, 1, 1, 1, 1, 2};
        //        int[] b = {5, 2, 6, 2, 3, 2};
        System.out.println("行相等的最少多米诺旋转：" + minDominoRotations(a, b));
    }
    //
    // 在一排多米诺骨牌中，A[i] 和 B[i] 分别代表第 i 个多米诺骨牌的上半部分和下半部分。（一个多米诺是两个从 1 到 6 的数字同列平铺形成的 ——
    // 该平铺的每一半上都有一个数字。）
    //
    // <p>我们可以旋转第 i 张多米诺，使得 A[i] 和 B[i] 的值交换。
    //
    // <p>返回能使 A 中所有值或者 B 中所有值都相同的最小旋转次数。
    //
    // <p>如果无法做到，返回 -1.
    //
    // <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/minimum-domino-rotations-for-equal-row
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    //
    // @param top the top
    // @param bottom the bottom
    // @return the int
    //
    public int minDominoRotations(int[] top, int[] bottom) {
        int len = top.length;
        System.out.println("length=" + len);
        int index = 0;

        int top1;
        int top2;
        int bot1;
        int bot2;

        int rotateTop = 0;
        int rotateBottom = 0;

        for (; index < len; index++) {}

        System.out.println("rotateTop=" + rotateTop + " rotateBottom=" + rotateBottom);

        if (rotateTop == -1 && rotateBottom == -1) {
            return -1;
        } else if (rotateTop != -1 && rotateBottom != -1) {
            return Math.min(rotateTop, rotateBottom);
        } else if (rotateTop != -1) {
            return rotateTop;
        } else {
            return rotateBottom;
        }
    }
}

package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给定一个从1 到 n 排序的整数列表。
//首先，从左到右，从第一个数字开始，每隔一个数字进行删除，直到列表的末尾。
//第二步，在剩下的数字中，从右到左，从倒数第一个数字开始，每隔一个数字进行删除，直到列表开头。
//我们不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。
//返回长度为 n 的列表中，最后剩下的数字。
//
//示例：
//
//输入:
//n = 9,
//1 2 3 4 5 6 7 8 9
//2 4 6 8
//2 6
//6
//
//输出:
//6
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/elimination-game
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 消除游戏 {
    @Test
    public void 消除游戏() {
        System.out.println("消除游戏：" + lastRemaining(9));
    }

    public int lastRemaining(int n) {
        //设数列首尾元素为left和right。
        //返回为数列中只剩一个数时，也就是left=right时，返回当前结果。
        //设当前循环步长为step，我们可以先求出，从left到right（或right到left）所需最少的步骤count使其大于等于right（小于等于left）。
        //那么，当从left到right开始消除时，right = left + count * step - step / 2;left = left + step / 2;
        //从right到left消除时，left = right - count * step + step / 2;，right = right - step / 2;
        //每次循环后，调整消除方向，并让步长step=step*2。
        //当只剩一个数时，说明left=right，返回left（或right）。

        int left = 1, right = n;
        boolean forward = true;
        int step = 2;
        int diff;
        int count;
        while (left != right) {
            //首尾差
            diff = right - left;
            //当从一端到另一端所需的最少次数，left+x*step>=right，求x。
            //若能整除，说明消除首尾，若不能整除，消除尾部前面一个数。
            //而为方便后续计算，我们求出次数正好大于（小于）等于尾部的数，这样消除后的尾部的数可由总次数-（+）step/2求出。
            count = diff / step + (diff % step == 0 ? 0 : 1);

            //求出消除数列后的left和right
            if (forward) {
                right = left + count * step - step / 2;
                left = left + step / 2;
            } else {
                left = right - count * step + step / 2;
                right = right - step / 2;
            }

            //转向
            forward = !forward;
            //扩大步长
            step *= 2;
        }
        return left;
    }
}

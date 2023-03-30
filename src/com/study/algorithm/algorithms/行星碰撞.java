package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

//给定一个整数数组 asteroids，表示在同一行的行星。
//
//对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
//
//找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
//
// 
//
//示例 1：
//
//输入：asteroids = [5,10,-5]
//输出：[5,10]
//解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
//示例 2：
//
//输入：asteroids = [8,-8]
//输出：[]
//解释：8 和 -8 碰撞后，两者都发生爆炸。
//示例 3：
//
//输入：asteroids = [10,2,-5]
//输出：[10]
//解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
// 
//
//提示：
//
//2 <= asteroids.length <= 104
//-1000 <= asteroids[i] <= 1000
//asteroids[i] != 0
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/asteroid-collision
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 行星碰撞 {
    @Test
    public void 行星碰撞() {
        System.out.println("行星碰撞:" + Arrays.toString(asteroidCollision(
                new int[]{-2, -1, 1, -2})));
    }

    public int[] asteroidCollision(int[] asteroids) {
        //栈
        //注：小行星没有绕着某颗恒星转
        //向右运行的小小行星将一直加入栈中
        //当遇到向左运行的小行星时，判断和栈顶的行星的大小
        //若大于栈顶的行星则将栈顶行星出栈，继续比较下一个栈顶的行星，直到栈內没有其他行星，或者栈顶的行星也是向左运行
        //若等于栈顶的行星，则栈顶行星出栈，继续遍历下一个行星
        //若小于栈顶行星，则直接遍历下一个行星
        //最后返回栈內行星
        int SIGH = Integer.MIN_VALUE;
        Deque<Integer> deque = new LinkedList<>();
        deque.offerFirst(asteroids[0]);

        OUT:
        for (int i = 1, l = asteroids.length; i < l; i++) {
            Integer top = deque.peekFirst();
            if (asteroids[i] < 0) {
                do {
                    if (top == null || (asteroids[i] & SIGH) == (top & SIGH)) {
                        break;
                    }
                    if (Math.abs(asteroids[i]) == Math.abs(top)) {
                        deque.pollFirst();
                        continue OUT;
                    }
                    if (Math.abs(asteroids[i]) < Math.abs(top)) {
                        continue OUT;
                    }
                    deque.pollFirst();
                    top = deque.peekFirst();
                } while (!deque.isEmpty());
            }
            deque.offerFirst(asteroids[i]);
        }

        int[] result = new int[deque.size()];
        int index = 0;
        while (!deque.isEmpty()) {
            result[index++] = deque.pollLast();
        }
        return result;
    }
}

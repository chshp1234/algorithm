package com.study.algorithm.algorithms;

import org.junit.Test;

public class 接雨水 {
    @Test
    public void 接雨水() {
        int[] ints = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("接雨水:" + trap(ints));
    }
    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     *
     * <p>上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
     *
     * <p>示例:
     *
     * <p>输入: [0,1,0,2,1,0,1,3,2,1,2,1] 输出: 6
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/trapping-rain-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }
        int left = 0, right = height.length - 1, sum = 0, highest, pivot;
        if (height[left] < height[right]) {
            highest = right;
            pivot = left;
        } else {
            highest = left;
            pivot = right;
        }
        while (left < right - 1) {
            if (highest == right) {
                left++;
                if (height[left] < height[pivot]) {
                    sum += height[pivot] - height[left];
                } else {
                    //                    pivot = left;
                    if (height[left] > height[highest]) {
                        highest = left;
                        pivot = right;
                    } else {
                        pivot = left;
                    }
                }
            } else {
                right--;
                if (height[right] < height[pivot]) {
                    sum += height[pivot] - height[right];
                } else {
                    //                    pivot = right;
                    if (height[right] > height[highest]) {
                        highest = right;
                        pivot = left;
                    } else {
                        pivot = right;
                    }
                }
            }
        }
        return sum;
    }
    /**
     * 总体的原则就是，
     *
     * <p>当前高度小于等于栈顶高度，入栈，指针后移。
     *
     * <p>当前高度大于栈顶高度，出栈，计算出当前墙和栈顶的墙之间水的多少，然后计算当前的高度和新栈的高度的关系，重复第 2
     * 步。直到当前墙的高度不大于栈顶高度或者栈空，然后把当前墙入栈，指针后移。
     *
     * <p>作者：windliang
     * 链接：https://leetcode-cn.com/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
     * 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    /*public int trap(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < height.length) {
            // 如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            while (!stack.empty() && height[current] > height[stack.peek()]) {
                int h = height[stack.peek()]; // 取出要出栈的元素
                stack.pop(); // 出栈
                if (stack.empty()) { // 栈空就出去
                    break;
                }
                // 而对于计算 current 指向墙和新的栈顶之间的水，根据图的关系，我们可以直接把这两个墙当做之前解法三的 max_left 和
                // max_right，然后之前弹出的栈顶当做每次遍历的 height [ i ]。水量就是 Min ( max _ left ，max _ right ) -
                // height [ i ]，只不过这里需要乘上两个墙之间的距离。可以看下代码继续理解下。
                //
                // 作者：windliang
                // 链接：https://leetcode-cn.com/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
                // 来源：力扣（LeetCode）
                // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
                int distance = current - stack.peek() - 1; // 两堵墙之前的距离。
                int min = Math.min(height[stack.peek()], height[current]);
                sum = sum + distance * (min - h);
            }
            stack.push(current); // 当前指向的墙入栈
            current++; // 指针后移
        }
        return sum;
    }*/

}

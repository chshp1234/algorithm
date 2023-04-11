package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

//给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。
//
//
//
//上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。 感谢 Marcos 贡献此图。
//
//示例:
//
//输入: [0,1,0,2,1,0,1,3,2,1,2,1]
//输出: 6
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/volume-of-histogram-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 直方图的水量 {

    @Test
    public void 直方图的水量() {
        int[] ints = {6, 4, 2, 0, 3, 2, 0, 3, 1, 4, 5, 3, 2, 7, 5, 3, 0, 1, 2, 1, 3, 4, 6, 8, 1, 3};
        System.out.println("直方图的水量:" + trapByDoublePointer(ints));
    }

    public int trapByDoublePointer(int[] height) {
        //双指针
        //维护高低两个指针，
        //规定其中一个指针所指的是当前最大值，并静止不移动
        //另一端指针向着这个“最大”柱子移动
        //若一端指针移到更大的柱子处，则更改为另一个指针移动，此指针静止

        //在指针移动过程中，由于另一端指针所处的柱子是“最大”的（至少并当前的大）
        //遇到小一级的柱子时，可直接加上差值
        //遇到大一级的柱子，更新新的位置，并比较“最大值”
        if (height == null || height.length < 3) {
            return 0;
        }
        int low = 0, high = height.length - 1;
        while (low <= high - 1) {
            if (height[low] > height[low + 1]) {
                break;
            }
            low++;
        }
        while (low <= high - 1) {
            if (height[high] > height[high - 1]) {
                break;
            }
            high--;
        }

        boolean lowAdd   = false;
        int     lowWall  = height[low];
        int     highWall = height[high];
        if (lowWall < highWall) {
            lowAdd = true;
        }

        int result = 0;

        while (low < high - 1) {
            if (lowAdd) {
                low++;
                if (height[low] > lowWall) {
                    lowWall = height[low];
                    if (lowWall > highWall) {
                        lowAdd = false;
                    }
                } else {
                    result += lowWall - height[low];
                }

            } else {
                high--;
                if (height[high] > highWall) {
                    highWall = height[high];
                    if (highWall > lowWall) {
                        lowAdd = true;
                    }
                } else {
                    result += highWall - height[high];
                }
            }
        }

        return result;
    }

    public int trap(int[] height) {
        //栈
        //栈中只维护单调递减的柱子
        //直到遇到比栈顶大的元素时，开始依次弹出栈元素（并依次比较大小）
        //更新值时，更新两个柱子的差值
        //栈为空或者遇到更大的柱子时退出循环
        if (height == null || height.length < 3) {
            return 0;
        }

        Deque<Integer> stack = new LinkedList<>();

        int len    = height.length;
        int result = 0;
        int last   = 0;
        int index  = 1;

        stack.push(0);

        while (index < len) {
            if (height[index] > height[stack.peek()]) {
                last = stack.poll();
            }
            while (!stack.isEmpty()) {
                int wall = stack.peek();
                if (height[index] > height[wall]) {
                    result += (index - wall - 1) * (height[wall] - height[last]);
                    last = wall;
                    stack.poll();
                } else {
                    result += (index - wall - 1) * (height[index] - height[last]);
                    break;
                }
            }
            stack.push(index);
            index++;
        }

        return result;
    }
}

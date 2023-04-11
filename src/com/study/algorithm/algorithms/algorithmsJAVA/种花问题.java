package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
//
//给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
//
//示例 1:
//
//输入: flowerbed = [1,0,0,0,1], n = 1
//输出: True
//示例 2:
//
//输入: flowerbed = [1,0,0,0,1], n = 2
//输出: False
//注意:
//
//数组内已种好的花不会违反种植规则。
//输入的数组长度范围为 [1, 20000]。
//n 是非负整数，且不会超过输入数组的大小。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/can-place-flowers
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 种花问题 {

    @Test
    public void 种花问题() {
        int[] ints = {0, 0, 1, 0, 0, 0, 1};
        System.out.println("种花问题:" + canPlaceFlowers(ints, 0));
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        //贪心
        //只有当花坛中左右位置都没有花时，当前位置才能并且一定种下一朵花
        //一次遍历
        //过滤掉所有有花的位置
        //对没花的位置进行判断，需要左右位置都没有花（刚种下的也算）
        if (n == 0) {
            return true;
        }

        int len = flowerbed.length;
        if (len == 1) {
            return flowerbed[0] == 0 && n == 1;
        }
        if (flowerbed[0] == 0 && flowerbed[1] == 0) {
            if (--n == 0) {
                return true;
            }
        }

        int index = 1;
        while (index < len) {
            while (index < len && flowerbed[index] == 1) {
                index++;
            }
            while (++index < len && flowerbed[index] == 0 && (++index >= len || flowerbed[index] == 0)) {
                n--;
                if (n == 0) {
                    return true;
                }
            }
        }

        return false;
    }
}

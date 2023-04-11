package com.study.algorithm.algorithms.algorithmsJAVA;


import java.util.Arrays;

//冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
//
//在加热器的加热半径范围内的每个房屋都可以获得供暖。
//
//现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
//
//说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
//
// 
//
//示例 1:
//
//输入: houses = [1,2,3], heaters = [2]
//输出: 1
//解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
//示例 2:
//
//输入: houses = [1,2,3,4], heaters = [1,4]
//输出: 1
//解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
//示例 3：
//
//输入：houses = [1,5], heaters = [2]
//输出：3
// 
//
//提示：
//
//1 <= houses.length, heaters.length <= 3 * 104
//1 <= houses[i], heaters[i] <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/heaters
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
@Deprecated
public class 供暖器 {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int left = heaters[0];
        int right = heaters.length - 1;

        int hLen = houses.length - 1;

        int lm = 0;
        while (left <= right) {
            if (houses[0] >= heaters[left]) {
                lm = houses[0] - heaters[left];
            } else {
                lm = Math.max(lm, houses[left] - heaters[0]);
                if (houses[hLen] <= houses[left]) {
                    return lm;
                }
                break;
            }
            left++;
        }

        int rm = 0;
        while (left <= right) {
            if (houses[hLen] <= heaters[right]) {
                rm = houses[right] - heaters[hLen];
            } else {
                rm = Math.max(rm, houses[hLen] - heaters[rm]);
                break;
            }
            right--;
        }

        int result = Math.max(lm, rm);

        while (left < right) {

        }

        return result;
    }
}

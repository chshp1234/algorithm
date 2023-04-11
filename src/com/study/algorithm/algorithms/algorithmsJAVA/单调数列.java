package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//如果数组是单调递增或单调递减的，那么它是单调的。
//
//如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
//
//当给定的数组 A 是单调数组时返回 true，否则返回 false。
//
// 
//
//示例 1：
//
//输入：[1,2,2,3]
//输出：true
//示例 2：
//
//输入：[6,5,4,4]
//输出：true
//示例 3：
//
//输入：[1,3,2]
//输出：false
//示例 4：
//
//输入：[1,2,4,5]
//输出：true
//示例 5：
//
//输入：[1,1,1]
//输出：true
// 
//
//提示：
//
//1 <= A.length <= 50000
//-100000 <= A[i] <= 100000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/monotonic-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 单调数列 {
    @Test
    public void 单调数列() {
        int[] newInterval = new int[]{17, 1, 17};
        int[] ints = new int[0];

        System.out.println("单调数列：" + isMonotonic(newInterval));
    }

    public boolean isMonotonic(int[] A) {
        //一次遍历
        //如果数组长度小于3，则必定单调
        //遍历数组，判断数组理应是属于递增还是递减
        //继续遍历数组，若在递增的数组中出现递减元素，或在递减的数组中出现递增元素，则此数组不是单调数列，返回false
        //遍历结束，数列没问题，符合单调数列，返回true

        int len = A.length;
        if (len <= 2) {
            return true;
        }

        //标记此数组单调性
        boolean up    = false;
        int     index = 1;

        //判断数列单调性
        while (index < len) {
            //若两个元素相等，没法判断
            if (A[index] == A[index - 1]) {
                index++;
            } else {
                //判断数列递增还是递减
                up = A[index] > A[index - 1];
                break;
            }
        }

        //继续后续判断
        while (index < len) {
            if (up) {
                if (A[index] < A[index - 1]) {
                    //若在递增数列中出现元素递减，则不符合条件
                    return false;
                }
            } else {
                if (A[index] > A[index - 1]) {
                    //若在递减数列中出现元素递增，则不符合条件
                    return false;
                }
            }
            index++;
        }

        //遍历结束，符合条件
        return true;
    }
}
package com.study.algorithm.algorithms.algorithmsJAVA;

//给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
//
//注意：请不要在超过该数组长度的位置写入元素。
//
//要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
//
// 
//
//示例 1：
//
//输入：[1,0,2,3,0,4,5,0]
//输出：null
//解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
//示例 2：
//
//输入：[1,2,3]
//输出：null
//解释：调用函数后，输入的数组将被修改为：[1,2,3]
// 
//
//提示：
//
//1 <= arr.length <= 10000
//0 <= arr[i] <= 9
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/duplicate-zeros
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 复写零 {
    public void duplicateZeros(int[] arr) {
        //双指针
        //使用额外一个数组记录当前遍历的位置，遇到0时额外数组的指针快进一步
        //遍历到头时，将额外数组的值复制到原数组
        int len = arr.length;
        int[] subArr = new int[len];
        for (int i = 0, j = 0; j < len; i++, j++) {
            if (arr[i] == 0) {
                j++;
            } else {
                subArr[j] = arr[i];
            }
        }
        System.arraycopy(subArr, 0, arr, 0, len);
    }

    //双指针，模拟栈
    //双指针，当遇到0时，右指针快进一步
    //当右指针遍历到头时，反向（模拟出栈）填充数据到原数组，其中遇到0时，同理快进一步往左移动指针（出栈），直到数据填充完毕
    //public void duplicateZeros(int[] arr) {
    //        int n = arr.length, i = 0, j = 0;
    //        while (j < n) {
    //            if (arr[i] == 0) j++;
    //            i++; j++;
    //        }
    //        i--; j--;
    //        while (i >= 0) {
    //            if (j < n) arr[j] = arr[i];
    //            if (arr[i] == 0 && --j >= 0) arr[j] = 0;
    //            i--; j--;
    //        }
    //    }
    //
    //作者：AC_OIer
    //链接：https://leetcode.cn/problems/duplicate-zeros/solution/by-ac_oier-zivq/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}

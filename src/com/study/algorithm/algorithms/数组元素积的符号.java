package com.study.algorithm.algorithms;

//已知函数 signFunc(x) 将会根据 x 的正负返回特定值：
//
//如果 x 是正数，返回 1 。
//如果 x 是负数，返回 -1 。
//如果 x 是等于 0 ，返回 0 。
//给你一个整数数组 nums 。令 product 为数组 nums 中所有元素值的乘积。
//
//返回 signFunc(product) 。
//
// 
//
//示例 1：
//
//输入：nums = [-1,-2,-3,-4,3,2,1]
//输出：1
//解释：数组中所有值的乘积是 144 ，且 signFunc(144) = 1
//示例 2：
//
//输入：nums = [1,5,0,2,-3]
//输出：0
//解释：数组中所有值的乘积是 0 ，且 signFunc(0) = 0
//示例 3：
//
//输入：nums = [-1,1,-1,1,-1]
//输出：-1
//解释：数组中所有值的乘积是 -1 ，且 signFunc(-1) = -1
// 
//
//提示：
//
//1 <= nums.length <= 1000
//-100 <= nums[i] <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/sign-of-the-product-of-an-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 数组元素积的符号 {
    public int arraySign(int[] nums) {
        //### 计数
        //对于本题的结论，我们只需要考虑：
        //1. 两种元素，0和负数；
        //2. 两种结果，正数和负数；
        //
        //解题时，初始化一个哑巴元素dummyNum=1;
        //1. 当元素为0时，乘积肯定为0，则直接返回结果‘0’；
        //2. 当元素为负数时，令dummyNum=dummyNum*-1,相当于假装乘了一个负数；
        //3. 当元素为正数时，不用管，因为正数 * 正数=正数，负数 * 正数=负数，对结果没有影响；
        //4. 最后只要返回dummyNum的值即可。
        int dummyNum = 1;
        for (int num : nums) {
            if (num < 0) {
                dummyNum *= -1;
            } else if (num == 0) {
                return 0;
            }
        }
        return dummyNum;
    }
}

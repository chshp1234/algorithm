package com.study.algorithm.algorithms.algorithmsJAVA;

//泰波那契序列 Tn 定义如下： 
//
//T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
//
//给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
//
// 
//
//示例 1：
//
//输入：n = 4
//输出：4
//解释：
//T_3 = 0 + 1 + 1 = 2
//T_4 = 1 + 1 + 2 = 4
//示例 2：
//
//输入：n = 25
//输出：1389537
// 
//
//提示：
//
//0 <= n <= 37
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/n-th-tribonacci-number
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 第N个泰波那契数 {
    public int tribonacci(int n) {
        //递推
        //既然fn=f(n-1)+f(n-2)+f(n-3),那么初始化3个值0，1，1（临界值），每次递推遍历时，
        //令第三个值等于三个值相加，并使得第二个值等于第三值之前的值，第一个值等于第二个值之前的值。

        if (n == 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        int first = 0, second = 1, third = 1;

        for (int i = 3; i <= n; i++) {
            int temp = third;
            third = first + second + third;
            first = second;
            second = temp;
        }
        return third;
    }

    //方法二：矩阵快速幂 ：）
    //https://leetcode-cn.com/problems/n-th-tribonacci-number/solution/di-n-ge-tai-bo-na-qi-shu-by-leetcode-sol-kn16/
}

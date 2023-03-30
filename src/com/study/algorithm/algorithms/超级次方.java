package com.study.algorithm.algorithms;

import org.junit.Test;

//你的任务是计算 ab 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
//
// 
//
//示例 1：
//
//输入：a = 2, b = [3]
//输出：8
//示例 2：
//
//输入：a = 2, b = [1,0]
//输出：1024
//示例 3：
//
//输入：a = 1, b = [4,3,3,8,5,2]
//输出：1
//示例 4：
//
//输入：a = 2147483647, b = [2,0,0]
//输出：1198
// 
//
//提示：
//
//1 <= a <= 231 - 1
//1 <= b.length <= 2000
//0 <= b[i] <= 9
//b 不含前导 0
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/super-pow
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 超级次方 {
    @Test
    public void 超级次方() {

        System.out.println("超级次方:" + superPow(78267,
                new int[]{7, 7, 4, 3, 1, 7, 0, 1, 4, 4, 9, 0,
                        5, 4, 1, 1, 8, 0, 4, 2, 5, 8, 2, 4, 2, 7}));
    }

    public int superPow(int a, int[] b) {
        //快速幂
        //其实只需要知道简单的道理：
        //
        //n^m=(n^x)^y--->m=x*y
        //n^m=(n^x)*(x^y)--->m=x+y

        //遍历过程中，维护一个值prev，初始值为1，每遍历一次，prev=prev^10
        //那么，当前遍历第i个数的值是n，那么(a^10^i)=prev^i,并且到目前为止result=prev^i*result
        //注：需对各个计算结果对1337取模

        if (a == 1) {
            return 1;
        }


        int prev = a % 1337;
        int result = 1;
        for (int i = b.length - 1; i >= 0; i--) {
            result = (result * recursive(prev, b[i])) % 1337;
            prev = recursive(prev, 10);
        }

        return result;
    }

    //计算num^pow，并对结果对1337取模
    public int recursive(int num, int pow) {

        if (pow == 0) {
            return 1;
        }
        if (pow == 1) {
            return num;
        }

        //快速幂
        int halfPow = recursive(num, pow / 2);
        int result = (halfPow * halfPow) % 1337;
        if (pow % 2 == 0) {
            return result;
        } else {
            return (result * num) % 1337;
        }
    }
}

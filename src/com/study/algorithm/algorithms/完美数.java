package com.study.algorithm.algorithms;

//对于一个 正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」。
//
//给定一个 整数 n， 如果是完美数，返回 true，否则返回 false
//
// 
//
//示例 1：
//
//输入：num = 28
//输出：true
//解释：28 = 1 + 2 + 4 + 7 + 14
//1, 2, 4, 7, 和 14 是 28 的所有正因子。
//示例 2：
//
//输入：num = 6
//输出：true
//示例 3：
//
//输入：num = 496
//输出：true
//示例 4：
//
//输入：num = 8128
//输出：true
//示例 5：
//
//输入：num = 2
//输出：false
// 
//
//提示：
//
//1 <= num <= 108
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/perfect-number
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 完美数 {
    public boolean checkPerfectNumber(int num) {
        //遍历
        //求出所有可以整除的数的和，最后判断是否等于num即可
        //这里只需要遍历Math.sqrt(num)，因为若有大于此数的整数可以被整除，那么此数在更小的数的情况下肯定已经整除并计算过。
        if (num == 1) {
            return false;
        }

        int target = (int) Math.sqrt(num);

        int sum = 1;
        for (int i = 2; i < target; i++) {
            if (num % i == 0) {
                sum += i;

                sum += num / i;
                if (sum > num) {
                    return false;
                }
            }
        }
        //注意当 target * target=num 时这两个因数相同，此时不能重复计算。
        if (target * target == num) {
            sum += target;
        }
        return sum == num;
    }
}

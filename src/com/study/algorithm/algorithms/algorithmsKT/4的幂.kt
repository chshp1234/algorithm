package com.study.algorithm.algorithms.algorithmsKT

//给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
//
//整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4x
//
// 
//
//示例 1：
//
//输入：n = 16
//输出：true
//示例 2：
//
//输入：n = 5
//输出：false
//示例 3：
//
//输入：n = 1
//输出：true
// 
//
//提示：
//
//-231 <= n <= 231 - 1
// 
//
//进阶：
//
//你能不使用循环或者递归来完成本题吗？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/power-of-four
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class `4的幂` {
    fun isPowerOfFour(n: Int): Boolean {
        //是否是4的幂，首先，肯定是2的幂
        //其次，由于整数不超过2^31-1，那么可以构建一个标志0x5555_5555，此表示所有4的幂相加
        //若整数是4的幂，那么，其“1”的位数肯定在标志0x5555_5555中
        return n != 0 && ((n - 1) and n) == 0 && (0x5555_5555 or n) == 0x5555_5555

        //方法二：取模性质
        //思路与算法
        //
        //如果 n 是 4 的幂，那么它可以表示成 4^x的形式，我们可以发现它除以 3 的余数一定为 1，
        // 即：4^x ≡(3+1)^x≡1^x≡1(mod3)
        //
        //如果 n 是 2 的幂却不是 4 的幂，那么它可以表示成 4^x ×2 的形式，此时它除以 3 的余数一定为 2。
        //
        //因此我们可以通过 n 除以 3 的余数是否为 1 来判断 n 是否是 4 的幂。
        //
        //    bool isPowerOfFour(int n) {
        //        return n > 0 && (n & (n - 1)) == 0 && n % 3 == 1;
        //    }
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode-cn.com/problems/power-of-four/solution/4de-mi-by-leetcode-solution-b3ya/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }
}
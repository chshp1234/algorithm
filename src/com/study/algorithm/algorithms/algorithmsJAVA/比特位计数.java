package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

//给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
//
//示例 1:
//
//输入: 2
//输出: [0,1,1]
//示例 2:
//
//输入: 5
//输出: [0,1,1,2,1,2]
//进阶:
//
//给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
//要求算法的空间复杂度为O(n)。
//你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/counting-bits
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 比特位计数 {
    @Test
    public void 比特位计数() {
        System.out.println("比特位计数：" + Arrays.toString(countBits(20)));
    }


    public int[] countBits(int num) {
        //动态规划--最高有效位
        //方程：dp[i]=dp[i ^ highBit(i)]+1
        //其中方法highBit(i)代表，数字i中最高位1，
        //也就是数字i除去最高位的1后所得的数组i2，i2肯定比i小，
        //那么dp[i]所代表的数字i中‘1’的个数，就可以表示为i2中1的个数dp[i2]，再加上i中最高位的一位‘1’得来
        //所以dp[i]=dp[i ^ highBit(i)]+1
        int[] result = new int[num + 1];
        if (num == 0) {
            return result;
        }
        for (int i = 1; i <= num; i++) {
            result[i] = result[i ^ highestOneBit(i)] + 1;
        }

        //by LeetCode
        //由于从小到大递推，我们也可以实时的维护最高有效位,不用每次都对最高有效位进行计算
        /*int highBit = 0;
        for (int i = 1; i <= num; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
                result[i] = 1;
            } else {
                result[i] = result[i - highBit] + 1;
            }
        }*/

        return result;
    }

    private int highestOneBit(int num) {
        //获取数字的最高位的‘1’
        num |= num >>> 1;
        num |= num >>> 2;
        num |= num >>> 4;
        num |= num >>> 8;
        num |= num >>> 16;
        return num - (num >>> 1);
    }

    private int highestOneBit2(int num) {
        //获取数字的最高位的‘1’
        int res = 0;
        while (num != 0) {
            res = num;
            num = num & (num - 1);
        }
        return res;
    }

    private int highestOneBit3(int num) {
        //获取数字的最高位的‘1’
        return Integer.highestOneBit(num);
    }

    //方法三：动态规划——最低有效位
    //方法二需要实时维护最高有效位，当遍历到的数是 2 的整数次幂时，需要更新最高有效位。
    //如果再换一个思路，可以使用「最低有效位」计算「一比特数」。
    //
    //对于正整数 x，将其二进制表示右移一位，等价于将其二进制表示的最低位去掉，得到的数是 x/2。
    //如果 bits[x/2] 的值已知，则可以得到 bits[x] 的值：
    //如果 x 是偶数，则 bits[x]=bits[x/2]；
    //
    //如果 x 是奇数，则 bits[x]=bits[x/2]+1。
    //
    //上述两种情况可以合并成：bits[x] 的值等于 bits[x/2] 的值加上 x 除以 2 的余数。
    //
    //由于 x/2 可以通过 x >> 1 得到，x 除以 2 的余数可以通过 x&1 得到，因此有：bits[x]=bits[x>>1]+(x&1)。
    //
    //遍历从 1 到 num 的每个正整数 i，计算 bits 的值。最终得到的数组 bits 即为答案。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/counting-bits/solution/bi-te-wei-ji-shu-by-leetcode-solution-0t1i/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int[] countBits3(int num) {
        int[] bits = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }

    //方法四：动态规划——最低设置位
    //定义正整数 x 的「最低设置位」为 x 的二进制表示中的最低的 1 所在位。例如，10 的二进制表示是 1010(2)，
    //其最低设置位为 2，对应的二进制表示是10(2)。
    //
    //令 y=x&(x−1)，则 y 为将 x 的最低设置位从 1 变成 0 之后的数，显然 0≤y<x，bits[x]=bits[y]+1。
    //因此对任意正整数 x，都有 bits[x]=bits[x&(x−1)]+1。
    //
    //遍历从 1 到 num 的每个正整数 i，计算 bits 的值。最终得到的数组 bits 即为答案。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/counting-bits/solution/bi-te-wei-ji-shu-by-leetcode-solution-0t1i/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int[] countBits4(int num) {
        int[] bits = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            bits[i] = bits[i & (i - 1)] + 1;
        }
        return bits;
    }

}

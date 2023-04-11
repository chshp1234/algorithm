package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

// 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
//
// 示例 1:
//
// 输入: n = 1
// 输出: [1,2,3,4,5,6,7,8,9]
//
//
// 说明：
//
// 用返回一个整数列表来代替打印
// n 为正整数
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 打印从1到最大的n位数 {

    @Test
    public void 扑克牌顺序() {

        int[] ints = printNumbers(5);

        System.out.println("扑克牌顺序：" + Arrays.toString(ints));
    }

    // 首先，按照剑指offer设置这道题的目的，这道题绝对不可能是一道简单题，绝对是保medium冲hard的水平。
    // 之所以是简单题，是因为leetcode没有把它当作一道大数问题来考我们。
    //
    // 方法一：绝对不可以的解法！做的时候我就说大数问题怎么可能只是简单难度，难道是leetcode没有把这道题当作一道大数问题吗？
    // 于是用了傻瓜解法试了一下，果然可以AC！
    //
    // 方法二：用字符串模拟数字加法。
    //
    // 方法三：递归全排列解法。假设 n = 3，要输出的数其实就是三位数的全排列（000，001，002，...，999，当然 000 不能输出），
    // 我们用递归来表示出这个过程即可。
    //
    // 作者：superkakayong
    // 链接：https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/solution/fei-zi-jie-ti-ku-jian-17-jian-dan-da-yin-cong-1dao/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int[] printNumbers(int n) {

        // 最大位n，则存储的数据大小为10的n次方-1
        // 按序从1开始储存即可

        int[] result = new int[(int) (Math.pow(10, n) - 1)];

        // 第一层，代表开始存储第i位的数字
        for (int i = 1; i <= n; i++) {
            // 第二层，将第i位的所有数字存储进数组
            for (int j = (int) Math.pow(10, i - 1), l = (int) Math.pow(10, i); j < l; j++) {
                result[j - 1] = j;
            }
        }

        // exm？直接从1开始取值到结尾，存进数组即可
        // int end = (int)Math.pow(10, n) - 1;
        // int[] res = new int[end];
        // for(int i = 0; i < end; i++)
        //     res[i] = i + 1;
        // return res;

        return result;
    }
}

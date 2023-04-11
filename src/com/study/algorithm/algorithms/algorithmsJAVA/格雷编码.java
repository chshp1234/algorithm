package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.*;

//n 位格雷码序列 是一个由 2n 个整数组成的序列，其中：
//每个整数都在范围 [0, 2n - 1] 内（含 0 和 2n - 1）
//第一个整数是 0
//一个整数在序列中出现 不超过一次
//每对 相邻 整数的二进制表示 恰好一位不同 ，且
//第一个 和 最后一个 整数的二进制表示 恰好一位不同
//给你一个整数 n ，返回任一有效的 n 位格雷码序列 。
//
// 
//
//示例 1：
//
//输入：n = 2
//输出：[0,1,3,2]
//解释：
//[0,1,3,2] 的二进制表示是 [00,01,11,10] 。
//- 00 和 01 有一位不同
//- 01 和 11 有一位不同
//- 11 和 10 有一位不同
//- 10 和 00 有一位不同
//[0,2,3,1] 也是一个有效的格雷码序列，其二进制表示是 [00,10,11,01] 。
//- 00 和 10 有一位不同
//- 10 和 11 有一位不同
//- 11 和 01 有一位不同
//- 01 和 00 有一位不同
//示例 2：
//
//输入：n = 1
//输出：[0,1]
// 
//
//提示：
//
//1 <= n <= 16
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/gray-code
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 格雷编码 {
    @Test
    public void 格雷编码() {
        System.out.println("格雷编码:" + grayCodeByLeetcode(3));
    }

    List<Integer> result = new ArrayList<>();
    List<Integer> list = new LinkedList<>();

    public List<Integer> grayCode(int n) {
        //暴力，回溯
        //使用回溯，暴力求解每一种可能，加入结果，若无法将剩余的数加入结果，则回溯
        //超时
        for (int i = 1, len = (int) Math.pow(2, n); i < len; i++) {
            list.add(i);
        }
        result.add(0);
        backTrack(list, 0);
        return result;
    }

    public boolean backTrack(List<Integer> list, int last) {
        int len = list.size();
        if (len == 0) {
            return true;
        }
        for (int i = 0; i < len; i++) {
            int current = list.get(i);
            if (isNer(last, current)) {
                list.remove(i);
                result.add(current);
                if (backTrack(list, current)) {
                    return true;
                }
                result.remove(result.size() - 1);
                list.add(0, current);
            }
        }
        return false;
    }

    public boolean isNer(int l, int r) {
        int diff = l ^ r;
        return ((diff - 1) & diff) == 0;
    }

    public List<Integer> grayCodeByLeetcode(int n) {
        //对称转换
        //将n-1位求解格雷码的结果，翻转后，此时两个列表首尾相同，但其余部分都符合格雷码标准
        //我们再将翻转后的列表，每个数都加上2^(n-1)（|1<<(n-1)），那么两个列表首尾将只相差一个2^(n-1)这一位，其余的数依旧符合条件
        //设初始值为0，按上述规律方可求出一个格雷编码序列。
        result.add(0);
        for (int i = 0; i < n; i++) {
            for (int j = result.size() - 1; j >= 0; j--) {
                result.add(result.get(j) | (1 << i));
            }
        }
        return result;
    }

    //方法二：公式法
    //思路与算法
    //
    //格雷码也可以使用公式直接求出。
    //第i个格雷码为:g(i)=i ^ (i/2)
    public List<Integer> grayCodeByLeetcode2(int n) {
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < 1 << n; i++) {
            ret.add((i >> 1) ^ i);
        }
        return ret;
    }
}

package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
//
//返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。
//
// 
//
//示例 1：
//
//输入：[0,1,1]
//输出：[true,false,false]
//解释：
//输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此 answer[0] 为真。
//示例 2：
//
//输入：[1,1,1]
//输出：[false,false,false]
//示例 3：
//
//输入：[0,1,1,1,1,1]
//输出：[true,false,false,false,true,false]
//示例 4：
//
//输入：[1,1,1,0,1]
//输出：[false,false,false,false,false]
// 
//
//提示：
//
//1 <= A.length <= 30000
//A[i] 为 0 或 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/binary-prefix-divisible-by-5
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 可被5整除的二进制前缀 {
    @Test
    public void 可被5整除的二进制前缀() {
        int[] ints = {1, 1, 1, 1, 1, 1};
        System.out.println("可被5整除的二进制前缀:" + prefixesDivBy5(ints));
    }

    public List<Boolean> prefixesDivBy5(int[] A) {
        //每次获取一个二进制数Ni，相当于上一个二进制数Ni-1左移一位，相当于Ni-1*2，然后再加上A[i]
        //但是由于A数组长度过大，要计算保存每个Ni的肯定会导致结果数据太大而溢出，
        //又由于我们只需要知道每个数是否能被5整除，所以只需要保存每个数除以5后的余数remainder即可
        //而余数remainder的计算也和Ni一样，Ni=Ni-1*2+A[i]，计算后再对5求余
        int remainder = 0;
        List<Boolean> result = new ArrayList<>(A.length);

        for (int value : A) {
            //上一个余数为零且当前数为0时，说明0*2+0，余数还是0，可被5整除
            //上一个数为1且当前数为1，说明1*2+1=5，余数为5，可被5整除，并让余数归0
            result.add((value == 0 && remainder == 0) || (remainder == 2 && value == 1));
            remainder = (remainder * 2 + value) % 5;
        }

        return result;
    }
}

package com.study.algorithm.algorithms;

import org.junit.Test;

//求解一个给定的方程，将x以字符串 "x=#value" 的形式返回。该方程仅包含 '+' ， '-' 操作，变量 x 和其对应系数。
//
//如果方程没有解，请返回 "No solution" 。如果方程有无限解，则返回 “Infinite solutions” 。
//
//题目保证，如果方程中只有一个解，则 'x' 的值是一个整数。
//
// 
//
//示例 1：
//
//输入: equation = "x+5-3+x=6+x-2"
//输出: "x=2"
//示例 2:
//
//输入: equation = "x=x"
//输出: "Infinite solutions"
//示例 3:
//
//输入: equation = "2x=x"
//输出: "x=0"
// 
//
//提示:
//
//3 <= equation.length <= 1000
//equation 只有一个 '='.
//equation 方程由整数组成，其绝对值在 [0, 100] 范围内，不含前导零和变量 'x'
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/solve-the-equation
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 求解方程 {
    @Test
    public void 求解方程() {
        System.out.println("求解方程:" + solveEquation("0x=0"));
    }

    public String solveEquation(String equation) {
        //模拟
        //将字符串以“=”划分为左右两个子串，各代表左边的方程和右边的方程
        //我们只需要找出左右两边子串中，各自x的系数，以及常数，就可以求解这个一元一次方程了
        //合并左右两个方程后，当系数为0时：1.常数为0，那么有无限解。2.常数不为0，无解
        //否则就用常数除以x的系数，得到x的解
        String[] equations = equation.split("=");
        int[] leftEquation = getEquation(equations[0]);
        int[] rightEquation = getEquation(equations[1]);

        int variable = leftEquation[0] - rightEquation[0];
        int constant = rightEquation[1] - leftEquation[1];
        if (variable == 0) {
            return (constant == 0) ? "Infinite solutions" : "No solution";
        }

        return "x=" + constant / variable;

    }

    public int[] getEquation(String equations) {
        //字符串遍历
        //求得x的系数，和常数
        int num = 0;
        int variable = 0;
        int constant = 0;

        boolean isVar = false;

        char sign = 0;

        for (int i = 0, l = equations.length(); i < l; i++) {
            char c = equations.charAt(i);
            if (c == 'x') {

                //如果num==0，那么x的系数为1
                if (num == 0) {
                    num = 1;
                }

                isVar = true;
            } else if (c == '-' || c == '+') {

                if (sign == '-') {
                    if (isVar) {
                        variable -= num;
                    } else {
                        constant -= num;
                    }
                } else {
                    if (isVar) {
                        variable += num;
                    } else {
                        constant += num;
                    }
                }

                sign = c;
                num = 0;
                isVar = false;
            } else {
                //这里注意，若num=0，说明num值还未进行遍历，若此时c=='0'，那么后续肯定跟着x，则此x的系数将不计入计算中
                if (c == '0' && num == 0) {
                    i++;
                    continue;
                }
                num = num * 10 + c - '0';
            }
        }

        if (sign == '-') {
            if (isVar) {
                variable -= num;
            } else {
                constant -= num;
            }
        } else {
            if (isVar) {
                variable += num;
            } else {
                constant += num;
            }
        }

        return new int[]{variable, constant};
    }
}

package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
//
//生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 104 。
//
// 
//
//示例 1：
//
//输入：expression = "2-1-1"
//输出：[0,2]
//解释：
//((2-1)-1) = 0
//(2-(1-1)) = 2
//示例 2：
//
//输入：expression = "2*3-4*5"
//输出：[-34,-14,-10,-10,10]
//解释：
//(2*(3-(4*5))) = -34
//((2*3)-(4*5)) = -14
//((2*(3-4))*5) = -10
//(2*((3-4)*5)) = -10
//(((2*3)-4)*5) = 10
// 
//
//提示：
//
//1 <= expression.length <= 20
//expression 由数字和算符 '+'、'-' 和 '*' 组成。
//输入表达式中的所有整数值在范围 [0, 99] 
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/different-ways-to-add-parentheses
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 为运算表达式设计优先级 {
    @Test
    public void 为运算表达式设计优先级() {

        System.out.println("为运算表达式设计优先级:" + diffWaysToCompute("2*3-4*5"));
    }

    List<Integer>[][] memory;

    public List<Integer> diffWaysToCompute(String expression) {
        //递归+记忆化搜索
        //首先遍历字符串，统计出所有数字，以及对应的操作符
        //递归求出0...len-1范围内数字所有的表达式结果
        //那么，递归的参数就是start起始数字下标，end结束数字下标，以及数字元素数组，操作符元素数组
        //额外还有个List<Integer>[][] memory备忘录，其中memory[start][end]用于记录下标start...end范围内所有的表达式的结果。那么最后的结果即为memory[0][len-1],len代表数字数组大小
        //递归时，首先判断memory[start][end]是否有元素，有的话直接返回，否则计算并记录当前范围的List<Integer>
        //当start==end时，元素只有一个，直接加入memory并返回即可
        //否则从start~end进行遍历，List<Integer> left表示start~i的所有元素，List<Integer> right表示i+1~end的所有元素（范围表达式结果）
        //那么start~end范围的表达式的结果，即为left中的所有元素与right中的所有元素的做当前i下标的操作符的计算操作
        //当遍历结束时，即为start~end范围的所有表达式结果，加入emory[start][end]，并返回
        List<Integer> nums = new ArrayList<>();
        List<Character> signs = new ArrayList<>();

        //遍历字符串，统计出所有数字，以及对应的操作符
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, l = expression.length(); i < l; i++) {
            char c = expression.charAt(i);
            if (c >= '0' && c <= '9') {
                stringBuilder.append(c);
            } else {
                signs.add(c);
                nums.add(Integer.valueOf(stringBuilder.toString()));
                stringBuilder = new StringBuilder();
            }
        }
        nums.add(Integer.valueOf(stringBuilder.toString()));

        List<Integer> result;
        int len = nums.size();
        if (len == 1) {
            result = new ArrayList<>();
            result.add(nums.get(0));
            return result;
        }
        if (len == 2) {
            result = new ArrayList<>();
            switch (signs.get(0)) {
                case '+':
                    result.add(nums.get(0) + nums.get(1));
                    break;
                case '-':
                    result.add(nums.get(0) - nums.get(1));
                    break;
                case '*':
                    result.add(nums.get(0) * nums.get(1));
                    break;
            }
            return result;
        }

        memory = new List[len][len];
        return memorySearch(nums, signs, 0, len - 1);
    }

    public List<Integer> memorySearch(List<Integer> nums, List<Character> signs, int start, int end) {
        //记忆化搜索
        if (memory[start][end] != null) {
            return memory[start][end];
        }
        List<Integer> result = new ArrayList<>();
        if (start == end) {
            result.add(nums.get(start));
            memory[start][end] = result;
            return result;
        }

        for (int i = start; i < end; i++) {
            //start~i的所有元素
            List<Integer> left = memorySearch(nums, signs, start, i);
            //i+1~end的所有元素
            List<Integer> right = memorySearch(nums, signs, i + 1, end);

            //将左右两部分元素与对应操作符计算，结果即为start~end的某一表达式结果
            for (Integer l : left) {
                for (Integer r : right) {
                    switch (signs.get(i)) {
                        case '+':
                            result.add(l + r);
                            break;
                        case '-':
                            result.add(l - r);
                            break;
                        case '*':
                            result.add(l * r);
                            break;
                    }
                }
            }
        }
        memory[start][end] = result;
        return result;
    }

}

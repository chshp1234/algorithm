package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

//给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
//
//注意:
//
//num 的长度小于 10002 且 ≥ k。
//num 不会包含任何前导零。
//示例 1 :
//
//输入: num = "1432219", k = 3
//输出: "1219"
//解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
//示例 2 :
//
//输入: num = "10200", k = 1
//输出: "200"
//解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
//示例 3 :
//
//输入: num = "10", k = 2
//输出: "0"
//解释: 从原数字移除所有的数字，剩余为空就是0。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/remove-k-digits
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 移掉K位数字 {

    @Test
    public void 移掉K位数字() {
        System.out.println("移掉K位数字:" + removeKdigits("1100", 2));
    }

    private String removeKdigits(String num, int k) {
        //贪心 + 单调栈
        //对于两个相同长度的数字序列，最左边不同的数字决定了这两个数字的大小，例如，对于 A=1axxx，B=1bxxx，
        //如果 a > ba>b 则 A > BA>B。
        //
        //基于此，我们可以知道，若要使得剩下的数字最小，需要保证靠前的数字尽可能小。
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode-cn.com/problems/remove-k-digits/solution/yi-diao-kwei-shu-zi-by-leetcode-solution/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        //将每个符合条件的数字压入栈中。
        //其中条件为：若遍历的数字比栈顶数字小，则取出栈顶元素；循环直到当前的数字大于栈顶元素或栈为空；将此数字压入栈中。
        //这样即可满足，考前的数字尽可能小（返回的结果中，应从栈底开始取）

        int len = num.length();

        if (len == k) {
            return "0";
        }

        StringBuilder result = new StringBuilder();

        //已经移除的个数
        int remove = 0;
        //当前遍历的字符串下标
        int index = 0;
        Deque<Character> stack = new LinkedList<>();

        Character last;
        char curr;

        while (index < len) {
            curr = num.charAt(index);

            while (!stack.isEmpty()) {

                last = stack.pop();
                //如果当前数字大于等于栈顶元素，则退出循环，判断下个字符数字
                if (curr >= last) {
                    stack.push(last);
                    break;
                } else {
                    //否则取出栈顶元素，移除个数+1
                    remove++;
                    //若移除数字的个数等于k，则推出循环
                    if (remove == k) {
                        break;
                    }
                }
            }
            // 返回的结果不会包含任何前导零
            if (curr != '0' || stack.size() != 0) {
                stack.push(curr);
            }
            //小标+1
            index++;
            //若移除数字的个数等于k，则推出循环
            if (remove == k) {
                break;
            }
        }
        //如果此时栈为空，并且遍历到字符串尾部，则说明移除数字后，只剩下字符串“00..0”，因为不能包含前导零，所以返回"0"
        if (stack.isEmpty() && index == len) {
            return "0";
        }

        // 若移除的个数为k
        // 将栈中元素从栈底到栈顶的顺序取出，再拼接上字符串num下标index后的所有字符，返回
        if (remove == k) {
            while (!stack.isEmpty()) {
                result.append(stack.pollLast());
            }
            result.append(num.substring(index));
        }
        //若移除的个数不为k（小于k）
        //说明遍历到字符串结尾，都是符合“条件”的数字
        //此时将栈中元素从栈底到栈顶的顺序取出，直到栈中元素个数还剩（k-remove）个时（也就是将栈顶元素移除，直到remove==k为止）
        else {
            int left = k - remove;
            while (stack.size() > left) {
                result.append(stack.pollLast());
            }
        }

        return result.toString();
    }
}

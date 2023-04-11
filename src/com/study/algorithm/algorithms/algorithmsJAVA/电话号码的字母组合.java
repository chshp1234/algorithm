package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 电话号码的字母组合 {

    @Test
    public void 电话号码的字母组合() {
        System.out.println("电话号码的字母组合:" + letterCombinations(""));
    }

    // 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
    //
    // 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
    //
    //
    //
    // 示例:
    //
    // 输入："23"
    // 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
    // 说明:
    // 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    private Map<Character, char[]> map = new HashMap<>();
    private StringBuilder temp = new StringBuilder();
    private List<String> result = new ArrayList<>();
    private int len;

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return result;
        }

        // 映射号码和字母
        map.put('2', new char[] {'a', 'b', 'c'});
        map.put('3', new char[] {'d', 'e', 'f'});
        map.put('4', new char[] {'g', 'h', 'i'});
        map.put('5', new char[] {'j', 'k', 'l'});
        map.put('6', new char[] {'m', 'n', 'o'});
        map.put('7', new char[] {'p', 'q', 'r', 's'});
        map.put('8', new char[] {'t', 'u', 'v'});
        map.put('9', new char[] {'w', 'x', 'y', 'z'});

        len = digits.length();

        // 回溯算法
        backSearch(digits, 0);

        return result;
    }

    private void backSearch(String digits, int index) {

        // 如果搜索到字符串尾部，则搜索完毕，加入当前搜索的字符串
        if (index == len) {
            result.add(temp.toString());
            return;
        }

        char[] chars = map.get(digits.charAt(index));
        for (int i = 0, l = chars.length; i < l; i++) {
            // 加入当前字符
            temp.append(chars[i]);
            // 继续搜索
            backSearch(digits, index + 1);
            // 回溯（删除当前字符，准备寻找下一个字符）
            temp.deleteCharAt(index);
        }
    }
}

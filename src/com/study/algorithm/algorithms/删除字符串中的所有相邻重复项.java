package com.study.algorithm.algorithms;

import org.junit.Test;

//给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
//
//在 S 上反复执行重复项删除操作，直到无法继续删除。
//
//在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
//
// 
//
//示例：
//
//输入："abbaca"
//输出："ca"
//解释：
//例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
// 
//
//提示：
//
//1 <= S.length <= 20000
//S 仅由小写英文字母组成。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 删除字符串中的所有相邻重复项 {
    @Test
    public void 删除字符串中的所有相邻重复项() {

        System.out.println("删除字符串中的所有相邻重复项：" + removeDuplicates("sdiuhfwijsdsd"));
    }

    public String removeDuplicates(String S) {
        //方法一：模拟栈
        //本题有点像消消乐，两个相同的字符相遇就消除
        //可以使用数据结构栈来解决，遍历到与栈顶相同的字符，就删除栈顶元素，继续遍历
        //最后只要将栈內元素反向取出返回即可。
        char[]        chars         = S.toCharArray();
        int           len           = chars.length;
        StringBuilder stringBuilder = new StringBuilder(len);
        stringBuilder.append(chars[0]);
        int index = 0;
        for (int i = 1; i < len; i++) {
            if (index < 0 || chars[i] != stringBuilder.charAt(index)) {
                stringBuilder.append(chars[i]);
                index++;
            } else {
                stringBuilder.deleteCharAt(index);
                index--;
            }
        }

        return stringBuilder.toString();
    }
}

package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
//
//例如：
//
//A -> 1
//B -> 2
//C -> 3
//...
//Z -> 26
//AA -> 27
//AB -> 28
//...
// 
//
//示例 1：
//
//输入：columnNumber = 1
//输出："A"
//示例 2：
//
//输入：columnNumber = 28
//输出："AB"
//示例 3：
//
//输入：columnNumber = 701
//输出："ZY"
//示例 4：
//
//输入：columnNumber = 2147483647
//输出："FXSHRXW"
// 
//
//提示：
//
//1 <= columnNumber <= 231 - 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/excel-sheet-column-title
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Excel表列名称 {
    @Test
    public void Excel表列名称() {
        System.out.println("Excel表列名称:" + convertToTitle(703));
    }

    public String convertToTitle(int columnNumber) {
        //26进制运算
        //注：每一列序号都相当于1...26，所以计算每一列的值时，需要将序号-1
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G',
                'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z'};
        StringBuilder result = new StringBuilder();

        if (columnNumber > 26) {
            do {
                columnNumber--;
                result.append(chars[columnNumber % 26]);
                columnNumber = (columnNumber / 26);
            } while (columnNumber > 26);

            result.append(chars[columnNumber - 1]);

        } else {
            return String.valueOf(chars[columnNumber - 1]);
        }
        return result.reverse().toString();
    }
}

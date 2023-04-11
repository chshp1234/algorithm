package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给定一个表示数据的整数数组 data ，返回它是否为有效的 UTF-8 编码。
//
//UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
//
//对于 1 字节 的字符，字节的第一位设为 0 ，后面 7 位为这个符号的 unicode 码。
//对于 n 字节 的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为 0 ，后面字节的前两位一律设为 10 。剩下的没有提及的二进制位，全部为这个符号的 unicode 码。
//这是 UTF-8 编码的工作方式：
//
//   Char. number range  |        UTF-8 octet sequence
//      (hexadecimal)    |              (binary)
//   --------------------+---------------------------------------------
//   0000 0000-0000 007F | 0xxxxxxx
//   0000 0080-0000 07FF | 110xxxxx 10xxxxxx
//   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
//   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
//注意：输入是整数数组。只有每个整数的 最低 8 个有效位 用来存储数据。这意味着每个整数只表示 1 字节的数据。
//
// 
//
//示例 1：
//
//输入：data = [197,130,1]
//输出：true
//解释：数据表示字节序列:11000101 10000010 00000001。
//这是有效的 utf-8 编码，为一个 2 字节字符，跟着一个 1 字节字符。
//示例 2：
//
//输入：data = [235,140,4]
//输出：false
//解释：数据表示 8 位的序列: 11101011 10001100 00000100.
//前 3 位都是 1 ，第 4 位为 0 表示它是一个 3 字节字符。
//下一个字节是开头为 10 的延续字节，这是正确的。
//但第二个延续字节不以 10 开头，所以是不符合规则的。
// 
//
//提示:
//
//1 <= data.length <= 2 * 104
//0 <= data[i] <= 255
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/utf-8-validation
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class UTF8编码验证 {
    @Test
    public void UTF8编码验证() {
        System.out.println("UTF8编码验证:" + validUtf8(new int[]{228, 189, 160, 229, 165, 189, 13, 10}));
    }

    public boolean validUtf8(int[] data) {
        //位运算，状态机
        //首先判断当前字符是属于1-4字节中哪种字符
        //在判断其后续的字符是否符合规律：
        //1.若是1字节字符，则继续判断下一个字符是几字节字符
        //2.若是n字节字符，则后续n-1个数，二进制下前两位必须是‘10’开头，否则不合法
        //3.结束遍历时，若是n字节字符，则必须在遍历到结尾时，有n-1个数字相接

        int charCount = 0;
        int leftCount = 0;

        for (int c : data) {
            String b = Integer.toBinaryString(c);
            if (charCount == 0) {
                charCount = checkCharCount(c);
                if (charCount == 0) {
                    return false;
                }
                leftCount = charCount - 1;
            } else if (!checkLeftChar(c)) {
                return false;
            } else {
                leftCount--;
            }
            if (leftCount == 0) {
                charCount = 0;
            }
        }

        return leftCount == 0;
    }

    private int checkCharCount(int c) {
        int count = 0;
        if (c >> 7 == 0) {
            count = 1;
        } else if (c >> 5 == 6) {
            count = 2;
        } else if (c >> 4 == 14) {
            count = 3;
        } else if (c >> 3 == 30) {
            count = 4;
        }

        return count;
    }

    private boolean checkLeftChar(int c) {
        return c >> 6 == 2;
    }
}

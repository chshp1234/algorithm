package com.study.algorithm.algorithms;

import org.junit.Test;

public class 压缩字符串 {

    @Test
    public void 压缩字符串() {
        char[] chars = {'a', 'a', 'a', 'b', 'b'};
        System.out.println("压缩字符串:" + compress(chars));
    }

    public int compress(char[] chars) {
        //双指针
        //双指针记录连续相同字符的左右端点
        //计算连续字符的长度，将数字转换成字符加入字符数组中
        //注意，不能使用额外空间的话，依次计算长度count%10，加入数组中，最后再反转长度区间的字符即可得到正序的长度字符
        int result = -1;

        int l = 0, r = 1;

        int len = chars.length;
        while (r < len) {
            if (chars[r] != chars[l]) {
                chars[++result] = chars[l];
                int count = r - l;
                if (count != 1) {

                    int numL = result + 1;

                    while (count > 0) {
                        result++;
                        chars[result] = (char) (count % 10 + '0');
                        count = count / 10;
                    }


                    int numR = result;

                    while (numL < numR) {
                        chars[numL] ^= chars[numR];
                        chars[numR] ^= chars[numL];
                        chars[numL] ^= chars[numR];
                        numL++;
                        numR--;
                    }
                }

                l = r;
            }
            r++;
        }

        int count = r - l;
        chars[++result] = chars[l];
        if (count != 1) {
            int numL = result + 1;

            while (count > 0) {
                result++;
                chars[result] = (char) (count % 10 + '0');
                count = count / 10;
            }


            int numR = result;

            while (numL < numR) {
                chars[numL] ^= chars[numR];
                chars[numR] ^= chars[numL];
                chars[numL] ^= chars[numR];
                numL++;
                numR--;
            }
        }

        return result + 1;

    }

}

package com.study.algorithm.algorithms.algorithmsJAVA;

import java.util.PriorityQueue;

//如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
//
//给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
//
//s 是一个尽可能长的快乐字符串。
//s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
//s 中只含有 'a'、'b' 、'c' 三种字母。
//如果不存在这样的字符串 s ，请返回一个空字符串 ""。
//
// 
//
//示例 1：
//
//输入：a = 1, b = 1, c = 7
//输出："ccaccbcc"
//解释："ccbccacc" 也是一种正确答案。
//示例 2：
//
//输入：a = 2, b = 2, c = 1
//输出："aabbc"
//示例 3：
//
//输入：a = 7, b = 1, c = 0
//输出："aabaa"
//解释：这是该测试用例的唯一正确答案。
// 
//
//提示：
//
//0 <= a, b, c <= 100
//a + b + c > 0
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-happy-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最长快乐字符串 {
    public String longestDiverseString(int a, int b, int c) {

        //### 贪心
        //- 使用大顶堆，按字符数量存储三种字符；
        //- 每次优先从剩余最多数字的字符中添加，使其尽量达到连续输出两个字符（如果剩余数量>1）；
        //- 其次再从次多的数字的字符中，添加一个字符（用于分割）；若没有次多的数字了，则跳出循环（因为无法进行分割，若还从最多的字符中进行添加，势必会连续3个字符，将不符合条件），返回结果；
        //- 返回结果；
        PriorityQueue<TextNum> queue = new PriorityQueue<>(3, (o1, o2) -> Integer.compare(o2.num, o1.num));
        if (a > 0) {
            queue.offer(new TextNum('a', a));
        }
        if (b > 0) {
            queue.offer(new TextNum('b', b));
        }
        if (c > 0) {
            queue.offer(new TextNum('c', c));
        }

        StringBuilder result = new StringBuilder();

        //上一个添加的字符
        char last = 0;

        while (!queue.isEmpty()) {
            TextNum f = queue.poll();

            //首先添加最多剩余数字的字符
            if (f.num == 1) {
                //只剩一个则添加一个
                result.append(f.c);
                f.num -= 1;
            } else {
                if (f.c != last) {
                    //若当前字符和上一个添加的字符不相同，则可以连续添加两个
                    result.append(f.c).append(f.c);
                    f.num -= 2;
                } else {
                    //若和上一个添加的字符相同，则只能添加一个
                    result.append(f.c);
                    f.num -= 1;
                }
            }

            //其次添加次多剩余数字的字符
            TextNum s = queue.poll();
            if (s == null) {
                //若不存在次多的字符，则退出
                break;
            }

            //次多字符只添加一个，用于分割字符，使其不会输出连续3个相同的字符
            result.append(s.c);
            s.num -= 1;

            //指定上一个添加字符
            last = s.c;

            //将剩余数量大于0的字符继续加入优先队列中
            if (f.num > 0) {
                queue.offer(f);
            }
            if (s.num > 0) {
                queue.offer(s);
            }
        }
        return result.toString();
    }

    public class TextNum {
        char c;
        int num;

        public TextNum(char c, int num) {
            this.c = c;
            this.num = num;
        }
    }
}

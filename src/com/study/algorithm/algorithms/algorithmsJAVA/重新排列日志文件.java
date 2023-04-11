package com.study.algorithm.algorithms.algorithmsJAVA;

import java.util.Arrays;

//给你一个日志数组 logs。每条日志都是以空格分隔的字串，其第一个字为字母与数字混合的 标识符 。
//
//有两种不同类型的日志：
//
//字母日志：除标识符之外，所有字均由小写字母组成
//数字日志：除标识符之外，所有字均由数字组成
//请按下述规则将日志重新排序：
//
//所有 字母日志 都排在 数字日志 之前。
//字母日志 在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序。
//数字日志 应该保留原来的相对顺序。
//返回日志的最终顺序。
//
// 
//
//示例 1：
//
//输入：logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
//输出：["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
//解释：
//字母日志的内容都不同，所以顺序为 "art can", "art zero", "own kit dig" 。
//数字日志保留原来的相对顺序 "dig1 8 1 5 1", "dig2 3 6" 。
//示例 2：
//
//输入：logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
//输出：["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
// 
//
//提示：
//
//1 <= logs.length <= 100
//3 <= logs[i].length <= 100
//logs[i] 中，字与字之间都用 单个 空格分隔
//题目数据保证 logs[i] 都有一个标识符，并且在标识符之后至少存在一个字
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/reorder-data-in-log-files
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 重新排列日志文件 {
    public String[] reorderLogFiles(String[] logs) {
        //规律，排序
        //根据题目要求，编写排序规则即可

        //优化，由于排序过程中，每个字符串会被访问多次，那么字符串内容的解析也会进行多次，我们可以将解析过程提前提取出来。
        //可以自定义一个对象，用于存储每个字符串以及其解析出的标识，内容，在数组中的下标信息。

        Arrays.sort(logs, (o1, o2) -> {
            //排序规则

            //第一个空格的下标
            int index1 = o1.indexOf(' ');
            int index2 = o2.indexOf(' ');

            //标识
            String sign1 = o1.substring(0, index1 + 1);
            String sign2 = o2.substring(0, index2 + 1);

            //内容
            String content1 = o1.substring(index1 + 1);
            String content2 = o2.substring(index2 + 1);

            //因为字母日志内容都是小写字符，数字日志内容都是数字，所以只需要获取内容的第一个字符，即可判断该日志内容类型
            char c1 = content1.charAt(0);
            char c2 = content2.charAt(0);

            //如果两个内容都是数字字符，则不进行排序
            if (c1 <= '9' && c2 <= '9') {
                return 0;
            }

            //如果第一个日志是数字日志，则该日志应该排在后面（较大）
            if (c1 <= '9') {
                return 1;
            }

            //如果第二个日志是数字日志，则该日志应该排在后面
            if (c2 <= '9') {
                return -1;
            }

            //到此，两个日志都是字母日志
            //如果两个日志内容相同，则比较标识符
            if (content1.equals(content2)) {
                return sign1.compareTo(sign2);
            }

            //比较两个日志内容
            return content1.compareTo(content2);
        });

        //排序完毕，返回数组
        return logs;
    }
}

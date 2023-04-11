package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
//
//你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
//
//要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
//
//文本的最后一行应为左对齐，且单词之间不插入额外的空格。
//
//说明:
//
//单词是指由非空格字符组成的字符序列。
//每个单词的长度大于 0，小于等于 maxWidth。
//输入单词数组 words 至少包含一个单词。
//示例:
//
//输入:
//words = ["This", "is", "an", "example", "of", "text", "justification."]
//maxWidth = 16
//输出:
//[
//   "This    is    an",
//   "example  of text",
//   "justification.  "
//]
//示例 2:
//
//输入:
//words = ["What","must","be","acknowledgment","shall","be"]
//maxWidth = 16
//输出:
//[
//  "What   must   be",
//  "acknowledgment  ",
//  "shall be        "
//]
//解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
//     因为最后一行应为左对齐，而不是左右两端对齐。
//     第二行同样为左对齐，这是因为这行只包含一个单词。
//示例 3:
//
//输入:
//words = ["Science","is","what","we","understand","well","enough","to","explain",
//         "to","a","computer.","Art","is","everything","else","we","do"]
//maxWidth = 20
//输出:
//[
//  "Science  is  what we",
//  "understand      well",
//  "enough to explain to",
//  "a  computer.  Art is",
//  "everything  else  we",
//  "do                  "
//]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/text-justification
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 文本左右对齐 {
    @Test
    public void 文本左右对齐() {
        String[] strings = {"ask", "not", "what", "your", "country", "can", "do", "for",
                "you", "ask", "what", "you", "can", "do", "for", "your", "country"};
        List<String> strings1 = fullJustify(strings, 16);
        for (String s : strings1) {
            System.out.println(s);
        }

    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        //模拟
        //用一个队列存储一排的单词。
        //注意单词之间必须有最少一个空格字符，所以，一排中，总共单词的字符数量+空格的字符数量=maxWidth。
        //而对于单词间空格字符大小的计算，因为需要均分，所以用剩余可用字符数left/queue.size()，这里有3种情况：
        //1.这一排只有一个单词可以放下，那么填入一个单词后，剩余全是空格字符
        //2.left%queue.size()==0，说明剩余的空格完全可以均分，那么空格间距大小=left / queue.size()
        //3.left%queue.size()!=0，说明剩余的空格不可均分，那么空格间距大小=left / queue.size()+1
        //对于最后一行，保证每个单词间只空一个空格字符即可，这里还需注意若最后一个单词正好填满这一行，那么这个单词后面不跟空格字符
        Queue<String> queue = new LinkedList<>();
        int left = maxWidth;
        List<String> result = new ArrayList<>();
        StringBuilder builder;

        for (String word : words) {

            //如果单词字符数量>剩余可用字符数量+单词数量（因为单词之间必须有空格字符）
            if (word.length() > (left - queue.size())) {

                builder = new StringBuilder();
                while (!queue.isEmpty()) {

                    builder.append(queue.poll());
                    //计算这个单词后面跟着的空格字符的大小：
                    //1.这一排只有一个单词可以放下，那么填入一个单词后，剩余全是空格字符
                    //2.left%queue.size()==0，说明剩余的空格完全可以均分，那么空格间距大小=left / queue.size()
                    //3.left%queue.size()!=0，说明剩余的空格不可均分，那么空格间距大小=left / queue.size()+1
                    int blankCount = queue.isEmpty() ? left :
                            left % queue.size() == 0 ? left / queue.size() :
                                    (left / queue.size()) + 1;
                    left -= blankCount;
                    //填入空格字符
                    while (blankCount > 0) {
                        builder.append(' ');
                        blankCount--;
                    }
                }
                result.add(builder.toString());
                left = maxWidth;
            }
            queue.offer(word);
            left -= word.length();
        }

        //最后一行字符
        if (!queue.isEmpty()) {
            builder = new StringBuilder();
            while (!queue.isEmpty()) {

                builder.append(queue.poll());
                if (left > 0) {
                    builder.append(' ');
                    left--;
                }
            }
            while (left > 0) {
                builder.append(' ');
                left--;
            }

            result.add(builder.toString());
        }

        return result;
    }
}

package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。
//
//字谜的迷面 puzzle 按字符串形式给出，如果一个单词 word 符合下面两个条件，那么它就可以算作谜底：
//
//单词 word 中包含谜面 puzzle 的第一个字母。
//单词 word 中的每一个字母都可以在谜面 puzzle 中找到。
//例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）以及 "based"（其中的 "s" 没有出现在谜面中）。
//返回一个答案数组 answer，数组中的每个元素 answer[i] 是在给出的单词列表 words 中可以作为字谜迷面 puzzles[i] 所对应的谜底的单词数目。
//
// 
//
//示例：
//
//输入：
//words = ["aaaa","asas","able","ability","actt","actor","access"],
//puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
//输出：[1,1,3,2,4,0]
//解释：
//1 个单词可以作为 "aboveyz" 的谜底 : "aaaa"
//1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
//3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
//2 个单词可以作为 "absoryz" 的谜底 : "aaaa", "asas"
//4 个单词可以作为 "actresz" 的谜底 : "aaaa", "asas", "actt", "access"
//没有单词可以作为 "gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。
// 
//
//提示：
//
//1 <= words.length <= 10^5
//4 <= words[i].length <= 50
//1 <= puzzles.length <= 10^4
//puzzles[i].length == 7
//words[i][j], puzzles[i][j] 都是小写英文字母。
//每个 puzzles[i] 所包含的字符都不重复。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/number-of-valid-words-for-each-puzzle
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 猜字谜 {

    @Test
    public void 猜字谜() {
        String[] words   = {"apple", "pleas", "please"};
        String[] puzzles = {"aelwxyz", "aelpxyz", "aelpsxy", "saelpxy", "xaelpsy"};

        System.out.print("猜字谜:" + findNumOfValidWords(words, puzzles));
    }

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        //状态压缩+子集
        //根据条件：
        //1.谜面puzzle不重复
        //2.word和puzzle都只包含小写字母
        //3.不考虑word的重复元素
        //我们可以构造一个哈希值，对每一个word和每一个puzzle做一个哈希映射，代表，这个word就代表一个哈希值（状态压缩）。
        //使用一个二进制值表示一个字符串，因为不重复，且小写字母最多26个，所以，出现一个字母时，二进制中的某一位（-'a'）设置为‘1’。
        //而要满足这个字符串属于某个puzzle，只要这个字符串的二进制哈希值属于puzzle的二进制哈希值的子集即可。
        //注意：我们可以依次判断每一个word的哈希值是否属于每一个puzzle哈希值的子集，
        //但words.length长度有10^5，再和puzzles长度来个两层循环，绝对是要超时的。
        //但这里每个puzzle的字符串长度最多7个字符，而且规定了首字母必须出现在word中才算，
        //所以只需要判断puzzle中的后6个字母组成的二进制哈希值的子集即可，而2^6=64，和100000相比少了非常多。
        //要判断每个子集是否含有某个word时，就可以使用哈希表来对每个word的‘哈希值’做一个映射，
        //key为二进制哈希值，value就为这个哈希值在words中出现的次数。

        //二进制哈希值出现的次数
        Map<Integer, Integer> frequency = new HashMap<>();
        char[]                temp;
        int                   mask;
        for (String word : words) {
            temp = word.toCharArray();
            mask = 0;
            for (char c : temp) {
                mask |= 1 << c - 'a';
            }

            //优化，由于puzzle的字符数量最多为7且不重复，
            //所以这里计算后的二进制哈希值，若‘1’的数量大于7，说明此字符串中不同的字符数量为大于7，那么肯定不会属于任何一个puzzle。
            if (Integer.bitCount(mask) <= 7) {
                frequency.put(mask, frequency.getOrDefault(mask, 0) + 1);
            }
        }

        List<Integer> result = new ArrayList<>();
        //遍历每个puzzle，计算结果
        for (String puzzle : puzzles) {
            int count = 0;
            temp = puzzle.toCharArray();
            mask = 0;

            //枚举子集方法一：
            //枚举64种子集的每一种情况
            //for (int choose = 0; choose < (1 << 6); ++choose) {
            //    for (int i = 0; i < 6; ++i) {
            //           //将当前位的‘1’加入二进制哈希值中
            //        if ((choose & (1 << i)) != 0) {
            //            mask |= (1 << (puzzle.charAt(i + 1) - 'a'));
            //        }
            //    }
            //    mask |= (1 << (puzzle.charAt(0) - 'a'));
            //    if (frequency.containsKey(mask)) {
            //        total += frequency.get(mask);
            //    }
            //}

            //枚举子集方法二：
            for (int j = 1; j < 7; j++) {
                mask |= 1 << temp[j] - 'a';
            }

            int sub = mask;
            //            String maskBit = Integer.toBinaryString(mask);
            do {
                //                String subsiBit = Integer.toBinaryString(sub);

                //每次把最后一个1赋为0，并把后面剩下的0赋成1，并与原数取&
                //这样能做到枚举全每一个1位是0和1时的子集的情况。
                int s = sub | 1 << temp[0] - 'a';
                if (frequency.containsKey(s)) {
                    count += frequency.get(s);
                }
                sub = (sub - 1) & mask;
            } while (sub != mask);

            result.add(count);
        }
        return result;
    }

    //方法二：字典树
    //思路与算法
    //
    //由于题目中规定 word 和 puzzle 均只包含小写字母，我们也可以考虑使用字典树来表示需要的「数据结构」。
    //由于方法一中已经详细介绍了每一步的做法，因此方法二中只介绍与方法一不同的地方。
    //
    //对于每一个 word 对应的集合 Sw，我们将 Sw中的的字母按照字典序进行排序，组成一个字符串，加入字典树中。
    //与方法一中的哈希映射类似，我们需要统计每个字符串在字典树中的出现次数。
    //
    //对于每一个 puzzle 对应的集合 Sp，我们枚举 Sp的子集，并将子集中的字母按照字典序进行排序，组成一个字符串，在字典树中查询得到其出现次数。
    //需要注意的是，由于 Sp只是一个普通的集合，而不是二进制表示，因此我们可以使用回溯的方法，在枚举子集的同时维护可以在字典树上进行查询的指针。
    //详细的实现可以见下面的代码。
    //
    //细节
    //在实际的实现中，我们无需显式地构造出加入字典树以及在字典树中查询需要的字符串，可以考虑使用一些等价的简单数据结构（例如列表）表示字符串。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/number-of-valid-words-for-each-puzzle/solution/cai-zi-mi-by-leetcode-solution-345u/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    TrieNode root;
    public List<Integer> findNumOfValidWordsByTrie(String[] words, String[] puzzles) {
        root = new TrieNode();

        for (String word : words) {
            //将 word 中的字母按照字典序排序并去重
            char[] arr = word.toCharArray();
            Arrays.sort(arr);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < arr.length; ++i) {
                if (i == 0 || arr[i] != arr[i - 1]) {
                    sb.append(arr[i]);
                }
            }
            //加入字典树中
            add(root, sb.toString());
        }

        List<Integer> ans = new ArrayList<Integer>();
        for (String puzzle : puzzles) {
            char   required = puzzle.charAt(0);
            char[] arr      = puzzle.toCharArray();
            Arrays.sort(arr);
            ans.add(find(new String(arr), required, root, 0));
        }
        return ans;
    }

    public void add(TrieNode root, String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (cur.child[ch - 'a'] == null) {
                cur.child[ch - 'a'] = new TrieNode();
            }
            cur = cur.child[ch - 'a'];
        }
        ++cur.frequency;
    }

    //在回溯的过程中枚举 puzzle 的所有子集并统计答案
    //find(puzzle, required, cur, pos) 表示 puzzle 的首字母为 required, 当前搜索到字典树上的 cur 节点，并且准备枚举 puzzle 的第 pos 个字母是否选择（放入子集中）
    //find 函数的返回值即为谜底的数量
    public int find(String puzzle, char required, TrieNode cur, int pos) {
        //搜索到空节点，不合法，返回 0
        if (cur == null) {
            return 0;
        }
        //整个 puzzle 搜索完毕，返回谜底的数量
        if (pos == 7) {
            return cur.frequency;
        }

        //选择第 pos 个字母
        int ret = find(puzzle, required, cur.child[puzzle.charAt(pos) - 'a'], pos + 1);

        //当 puzzle.charAt(pos) 不为首字母时，可以不选择第 pos 个字母
        if (puzzle.charAt(pos) != required) {
            ret += find(puzzle, required, cur, pos + 1);
        }

        return ret;
    }

    class TrieNode {
        int        frequency;
        TrieNode[] child;

        public TrieNode() {
            frequency = 0;
            child = new TrieNode[26];
        }
    }
}

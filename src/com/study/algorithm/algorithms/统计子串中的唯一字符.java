package com.study.algorithm.algorithms;

import java.util.*;

//我们定义了一个函数 countUniqueChars(s) 来统计字符串 s 中的唯一字符，并返回唯一字符的个数。
//
//例如：s = "LEETCODE" ，则其中 "L", "T","C","O","D" 都是唯一字符，因为它们只出现一次，所以 countUniqueChars(s) = 5 。
//
//本题将会给你一个字符串 s ，我们需要返回 countUniqueChars(t) 的总和，其中 t 是 s 的子字符串。输入用例保证返回值为 32 位整数。
//
//注意，某些子字符串可能是重复的，但你统计时也必须算上这些重复的子字符串（也就是说，你必须统计 s 的所有子字符串中的唯一字符）。
//
// 
//
//示例 1：
//
//输入: s = "ABC"
//输出: 10
//解释: 所有可能的子串为："A","B","C","AB","BC" 和 "ABC"。
//     其中，每一个子串都由独特字符构成。
//     所以其长度总和为：1 + 1 + 1 + 2 + 2 + 3 = 10
//示例 2：
//
//输入: s = "ABA"
//输出: 8
//解释: 除了 countUniqueChars("ABA") = 1 之外，其余与示例 1 相同。
//示例 3：
//
//输入：s = "LEETCODE"
//输出：92
// 
//
//提示：
//
//1 <= s.length <= 10^5
//s 只包含大写英文字符
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/count-unique-characters-of-all-substrings-of-a-given-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
@Deprecated
public class 统计子串中的唯一字符 {
    //动态规划
    //设dp[i][j]表示字符串下标i...j处区间的子串，总共有多少唯一字符
    //我们在用一个数组map[i][j]表示i...j区间的子串，共有几种字符
    //那么当map[i][j-1]不存在字符C时，dp[j][i]中将包含C
    //否则，从dp[j][i-1]中移除C并赋值给dp[j][i]（不用管dp[j][i-1]是否存在C）
    //最后，再将dp[0][len-1]的所有元素相加即可得到所有子串的唯一字符的数量之和了
    public int uniqueLetterString(String s) {
        int len = s.length();
        Set<Character>[][] map = new Set[len][len];
        Set<Character>[][] dp = new Set[len][len];

        for (int i = 0; i < len; i++) {
            map[i][i] = new HashSet<>();
            map[i][i].add(s.charAt(i));

            dp[i][i] = new HashSet<>();
            dp[i][i].add(s.charAt(i));
            for (int j = 0; j < i; j++) {
                map[j][i] = new HashSet<>(map[j][i - 1]);
                dp[j][i] = new HashSet<>(dp[j][i - 1]);

                if (map[j][i].add(s.charAt(i))) {
                    dp[j][i].add(s.charAt(i));
                } else {
                    dp[j][i].remove(s.charAt(i));
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                sum += dp[j][i].size();
            }
        }

        return sum;
    }

    public int uniqueLetterStringByDp(String s) {
        //对上述方法进行优化
        //使用两个一维数组记录区间子串
        //每次从下标[i...len-1]...下标[len-1]处，只需要一个数组，统计i...len-1区间的各个字符是否出现，以及是否单独出现即可
        //因为只包含大写字母，那么boolean[26]来进行字符是否在区间的统计
        int len = s.length();
        boolean[] allLetter;
        boolean[] uniqueLetter;
        int sum = 0;

        for (int i = 0; i < len; i++) {
            allLetter = new boolean[26];
            uniqueLetter = new boolean[26];
            int count = 0;
            for (int j = i; j < len; j++) {
                int c = s.charAt(j) - 'A';
                if (!allLetter[c]) {
                    uniqueLetter[c] = true;
                    allLetter[c] = true;
                    count++;
                } else {
                    if (uniqueLetter[c]) {
                        uniqueLetter[c] = false;
                        count--;
                    }
                }
                sum += count;
            }

        }

        return sum;
    }

    public int uniqueLetterStringByLeetCode(String s) {
        //对于字符C，我们可以考虑其对整体“唯一子串”进行统计时所做出的贡献是多少
        //当字符C处于下标i处时，我们找到其在左侧第一次出现的下标j，和右侧第一次出现的下标k
        //那么也就是再j...k之间，这个字符C只会出现一次，所以我们只需要统计这个区间中，包含字符C的子串数量即可
        //包含字符C的子串数量=(i-j)*(k-i)
        //我们可以用哈希表Map<Character, List<Integer>>，编译一次字符串，统计所有字符及其出现的下标
        //注意对于最左侧和最右侧的字符，我们可以构造-1和len的下标，使其能保证计算的准确性和便携性
        int len = s.length();

        int sum = 0;

        Map<Character, List<Integer>> map = new HashMap<>();

        List<Integer> list;
        for (int i = 0; i < len; i++) {
            list = map.get(s.charAt(i));
            if (list == null) {
                list = new ArrayList<>();
                list.add(-1);
                map.put(s.charAt(i), list);
            }
            list.add(i);
        }

        for (Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
            list = entry.getValue();
            list.add(len);
            for (int i = 1; i < list.size() - 1; i++) {
                sum += (list.get(i) - list.get(i - 1)) * (list.get(i + 1) - list.get(i));
            }
        }

        return sum;
    }
}

package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。
//
//例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。
//
//总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。
//
//给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？
//
// 
//
//示例 1：
//
//输入：strs = ["tars","rats","arts","star"]
//输出：2
//示例 2：
//
//输入：strs = ["omv","ovm"]
//输出：1
// 
//
//提示：
//
//1 <= strs.length <= 100
//1 <= strs[i].length <= 1000
//sum(strs[i].length) <= 2 * 104
//strs[i] 只包含小写字母。
//strs 中的所有单词都具有相同的长度，且是彼此的字母异位词。
//备注：
//字母异位词（anagram），一种把某个字符串的字母的位置（顺序）加以改换所形成的新词。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/similar-string-groups
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 相似字符串组 {

    @Test
    public void 相似字符串组() {
        System.out.println(
                "相似字符串组:" + numSimilarGroups(new String[]{"omv"}));
    }

    public int numSimilarGroups(String[] strs) {
        //并查集
        //将数组中每个字母看做图中的每个顶点，根据两个字母是否相似，判断两个顶点是否能相连
        //相似的两个字母在图中的同一个连通分量中
        //由此，最终结论就为求，图中所有的连通分量的数量
        int len = strs.length;

        //初始化图
        int[] members = new int[len];
        for (int i = 0; i < len; i++) {
            members[i] = i;
        }

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                //先判断两个顶点是否属于同一个连通分量，若是，则不必判断是否相似，因为已经属于同一个“组”中
                //若不在同一个组，则在判断是否相似，若相似，则合并两个顶点
                if (!isConnection(members, members[i], members[j]) && isSimilar(strs[i], strs[j])) {
                    merge(members, members[i], members[j]);
                }
            }
        }

        //计算连通分量数量
        int result = 0;
        for (int i = 0; i < len; i++) {
            if (members[i] == i) {
                result++;
            }
        }
        return result;
    }

    public int find(int[] members, int x) {
        if (members[x] == x) {
            return x;
        }
        int p = find(members, members[x]);
        members[x] = p;
        return p;
    }

    public void merge(int[] members, int x, int y) {
        members[find(members, x)] = find(members, y);
    }

    public boolean isConnection(int[] members, int x, int y) {
        return find(members, x) == find(members, y);
    }

    //判断两个字母是否相似
    public boolean isSimilar(String a, String b) {
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        char[] diff = new char[2];

        for (int i = 0, len = aChars.length; i < len; i++) {
            if (aChars[i] != bChars[i]) {
                if (diff[0] == 0) {
                    diff[0] = aChars[i];
                    diff[1] = bChars[i];
                } else if (aChars[i] == diff[1] && bChars[i] == diff[0]) {
                    diff[0] = 'a' + 1;
                } else {
                    return false;
                }
            }
        }
        return true;

        //由于条件，每队字母都是异位词，所以不用判断不同的字母是否需要交换，只需要判断不同位置的字母的数量大于2，即可说明是否为相似字母。
        /*int len = a.length();
        int num = 0;
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                num++;
                if (num > 2) {
                    return false;
                }
            }
        }
        return true;*/
    }
}

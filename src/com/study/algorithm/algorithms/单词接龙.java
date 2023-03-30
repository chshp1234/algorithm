package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
//
// 每次转换只能改变一个字母。
// 转换过程中的中间单词必须是字典中的单词。
// 说明:
//
// 如果不存在这样的转换序列，返回 0。
// 所有单词具有相同的长度。
// 所有单词只由小写字母组成。
// 字典中不存在重复的单词。
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
// 示例 1:
//
// 输入:
// beginWord = "hit",
// endWord = "cog",
// wordList = ["hot","dot","dog","lot","log","cog"]
//
// 输出: 5
//
// 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 示例 2:
//
// 输入:
// beginWord = "hit"
// endWord = "cog"
// wordList = ["hot","dot","dog","lot","log"]
//
// 输出: 0
//
// 解释: endWord "cog" 不在字典中，所以无法进行转换。
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/word-ladder
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 单词接龙 {

    @Test
    public void 单词接龙() {
        //        "hit"
        //        "cog"
        //                ["hot","dot","dog","lot","log"]
        System.out.println(
                "单词接龙:"
                        + ladderLength(
                                "cet",
                                "ism",
                                Arrays.asList(
                                        "kid", "tag", "pup", "ail", "tun", "woo", "erg", "luz",
                                        "brr", "gay", "sip", "kay", "per", "val", "mes", "ohs",
                                        "now", "boa", "cet", "pal", "bar", "die", "war", "hay",
                                        "eco", "pub", "lob", "rue", "fry", "lit", "rex", "jan",
                                        "cot", "bid", "ali", "pay", "col", "gum", "ger", "row",
                                        "won", "dan", "rum", "fad", "tut", "sag", "yip", "sui",
                                        "ark", "has", "zip", "fez", "own", "ump", "dis", "ads",
                                        "max", "jaw", "out", "btu", "ana", "gap", "cry", "led",
                                        "abe", "box", "ore", "pig", "fie", "toy", "fat", "cal",
                                        "lie", "noh", "sew", "ono", "tam", "flu", "mgm", "ply",
                                        "awe", "pry", "tit", "tie", "yet", "too", "tax", "jim",
                                        "san", "pan", "map", "ski", "ova", "wed", "non", "wac",
                                        "nut", "why", "bye", "lye", "oct", "old", "fin", "feb",
                                        "chi", "sap", "owl", "log", "tod", "dot", "bow", "fob",
                                        "for", "joe", "ivy", "fan", "age", "fax", "hip", "jib",
                                        "mel", "hus", "sob", "ifs", "tab", "ara", "dab", "jag",
                                        "jar", "arm", "lot", "tom", "sax", "tex", "yum", "pei",
                                        "wen", "wry", "ire", "irk", "far", "mew", "wit", "doe",
                                        "gas", "rte", "ian", "pot", "ask", "wag", "hag", "amy",
                                        "nag", "ron", "soy", "gin", "don", "tug", "fay", "vic",
                                        "boo", "nam", "ave", "buy", "sop", "but", "orb", "fen",
                                        "paw", "his", "sub", "bob", "yea", "oft", "inn", "rod",
                                        "yam", "pew", "web", "hod", "hun", "gyp", "wei", "wis",
                                        "rob", "gad", "pie", "mon", "dog", "bib", "rub", "ere",
                                        "dig", "era", "cat", "fox", "bee", "mod", "day", "apr",
                                        "vie", "nev", "jam", "pam", "new", "aye", "ani", "and",
                                        "ibm", "yap", "can", "pyx", "tar", "kin", "fog", "hum",
                                        "pip", "cup", "dye", "lyx", "jog", "nun", "par", "wan",
                                        "fey", "bus", "oak", "bad", "ats", "set", "qom", "vat",
                                        "eat", "pus", "rev", "axe", "ion", "six", "ila", "lao",
                                        "mom", "mas", "pro", "few", "opt", "poe", "art", "ash",
                                        "oar", "cap", "lop", "may", "shy", "rid", "bat", "sum",
                                        "rim", "fee", "bmw", "sky", "maj", "hue", "thy", "ava",
                                        "rap", "den", "fla", "auk", "cox", "ibo", "hey", "saw",
                                        "vim", "sec", "ltd", "you", "its", "tat", "dew", "eva",
                                        "tog", "ram", "let", "see", "zit", "maw", "nix", "ate",
                                        "gig", "rep", "owe", "ind", "hog", "eve", "sam", "zoo",
                                        "any", "dow", "cod", "bed", "vet", "ham", "sis", "hex",
                                        "via", "fir", "nod", "mao", "aug", "mum", "hoe", "bah",
                                        "hal", "keg", "hew", "zed", "tow", "gog", "ass", "dem",
                                        "who", "bet", "gos", "son", "ear", "spy", "kit", "boy",
                                        "due", "sen", "oaf", "mix", "hep", "fur", "ada", "bin",
                                        "nil", "mia", "ewe", "hit", "fix", "sad", "rib", "eye",
                                        "hop", "haw", "wax", "mid", "tad", "ken", "wad", "rye",
                                        "pap", "bog", "gut", "ito", "woe", "our", "ado", "sin",
                                        "mad", "ray", "hon", "roy", "dip", "hen", "iva", "lug",
                                        "asp", "hui", "yak", "bay", "poi", "yep", "bun", "try",
                                        "lad", "elm", "nat", "wyo", "gym", "dug", "toe", "dee",
                                        "wig", "sly", "rip", "geo", "cog", "pas", "zen", "odd",
                                        "nan", "lay", "pod", "fit", "hem", "joy", "bum", "rio",
                                        "yon", "dec", "leg", "put", "sue", "dim", "pet", "yaw",
                                        "nub", "bit", "bur", "sid", "sun", "oil", "red", "doc",
                                        "moe", "caw", "eel", "dix", "cub", "end", "gem", "off",
                                        "yew", "hug", "pop", "tub", "sgt", "lid", "pun", "ton",
                                        "sol", "din", "yup", "jab", "pea", "bug", "gag", "mil",
                                        "jig", "hub", "low", "did", "tin", "get", "gte", "sox",
                                        "lei", "mig", "fig", "lon", "use", "ban", "flo", "nov",
                                        "jut", "bag", "mir", "sty", "lap", "two", "ins", "con",
                                        "ant", "net", "tux", "ode", "stu", "mug", "cad", "nap",
                                        "gun", "fop", "tot", "sow", "sal", "sic", "ted", "wot",
                                        "del", "imp", "cob", "way", "ann", "tan", "mci", "job",
                                        "wet", "ism", "err", "him", "all", "pad", "hah", "hie",
                                        "aim", "ike", "jed", "ego", "mac", "baa", "min", "com",
                                        "ill", "was", "cab", "ago", "ina", "big", "ilk", "gal",
                                        "tap", "duh", "ola", "ran", "lab", "top", "gob", "hot",
                                        "ora", "tia", "kip", "han", "met", "hut", "she", "sac",
                                        "fed", "goo", "tee", "ell", "not", "act", "gil", "rut",
                                        "ala", "ape", "rig", "cid", "god", "duo", "lin", "aid",
                                        "gel", "awl", "lag", "elf", "liz", "ref", "aha", "fib",
                                        "oho", "tho", "her", "nor", "ace", "adz", "fun", "ned",
                                        "coo", "win", "tao", "coy", "van", "man", "pit", "guy",
                                        "foe", "hid", "mai", "sup", "jay", "hob", "mow", "jot",
                                        "are", "pol", "arc", "lax", "aft", "alb", "len", "air",
                                        "pug", "pox", "vow", "got", "meg", "zoe", "amp", "ale",
                                        "bud", "gee", "pin", "dun", "pat", "ten", "mob")));
    }

    int result = 0;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        // 方法：无向图遍历
        // 把一个字符串能有效转换成另一个字符串时，连接两个字符串，使之成为无向图中的一部分
        // 遍历所有字符串，构造整个无向图
        // 遍历无向图，查找字符串beginWord到字符串endWord的最短路径即可

        // 注意：
        // 1.广度优先和深度优先理论上都可以，但是判断图中两点之间最短距离，用广度优先是最快的，
        // 因为广度优先搜索中，每一层遍历就相当于路径+1，当第一次从头遍历到尾时，就说明此距离是最短距离。
        // 2.在遍历过程中需要一个额外集合，记录已经遍历过的节点：
        // （1）.防止回头遍历，陷入死循环；
        // （2）.防止无效、无意义的重复遍历，当一个节点遍历过，后续再到此节点时，路径长度肯定没有之前的小。

        // 方法二：双向广度优先搜索
        // 思路及解法
        //
        // 根据给定字典构造的图可能会很大，而广度优先搜索的搜索空间大小依赖于每层节点的分支数量。
        // 假如每个节点的分支数量相同，搜索空间会随着层数的增长指数级的增加。
        // 考虑一个简单的二叉树，每一层都是满二叉树的扩展，节点的数量会以 22 为底数呈指数增长。
        //
        // 如果使用两个同时进行的广搜可以有效地减少搜索空间。一边从 beginWord 开始，另一边从 endWord
        // 开始。我们每次从两边各扩展一层节点，当发现某一时刻两边都访问过同一顶点时就停止搜索。
        // 这就是双向广度优先搜索，它可以可观地减少搜索空间大小，从而提高代码运行效率。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/word-ladder/solution/dan-ci-jie-long-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        Map<String, Set<String>> map = new HashMap<>();

        List<String> mapList = new ArrayList<>(wordList);

        if (!mapList.contains(beginWord)) {
            mapList.add(beginWord);
        }

        for (int i = 0, il = mapList.size(); i < il; i++) {
            String sub = mapList.get(i);
            for (int j = i + 1, jl = mapList.size(); j < jl; j++) {
                String next = mapList.get(j);
                if (ifChange(sub, next)) {
                    Set<String> list = map.get(sub);
                    if (list == null) {
                        list = new HashSet<>();
                        map.put(sub, list);
                    }
                    list.add(next);

                    list = map.get(next);
                    if (list == null) {
                        list = new HashSet<>();
                        map.put(next, list);
                    }
                    list.add(sub);
                }
            }
        }
        //        dfs(beginWord, endWord, map, 1);

        return bfs(beginWord, endWord, map);
    }

    public boolean ifChange(String beginWord, String endWord) {
        int change = 0;
        for (int i = 0, l = beginWord.length(); i < l; i++) {
            if (beginWord.charAt(i) != endWord.charAt(i)) {
                change++;
            }
            if (change > 1) {
                return false;
            }
        }

        return true;
    }

    public int bfs(String beginWord, String endWord, Map<String, Set<String>> map) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        int result = 0;

        Set<String> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            int size = queue.size();

            result++;

            for (int i = 0; i < size; i++) {
                String begin = queue.poll();

                visited.add(begin);

                if (begin.equals(endWord)) {
                    return result;
                }

                Set<String> nextWords = map.get(begin);

                if (nextWords == null || nextWords.size() == 0) {
                    continue;
                }

                for (String next : nextWords) {
                    if (!visited.contains(next)) {
                        queue.offer(next);
                    }
                    //                    map.get(next).remove(begin);
                }
            }
        }
        return 0;
    }

    public void dfs(String beginWord, String endWord, Map<String, List<String>> map, int tier) {
        if (beginWord.equals(endWord)) {
            result = tier;
            return;
        }

        if (result > 0 && tier >= result) {
            return;
        }

        List<String> nextWords = map.get(beginWord);

        if (nextWords == null || nextWords.size() == 0) {
            return;
        }

        for (int i = 0, l = nextWords.size(); i < l; i++) {
            String next = nextWords.get(i);
            map.get(next).remove(beginWord);
            int index = nextWords.indexOf(next);
            nextWords.remove(index);
            dfs(next, endWord, map, tier + 1);
            nextWords.add(index, next);
            map.get(next).add(beginWord);
        }
    }
}

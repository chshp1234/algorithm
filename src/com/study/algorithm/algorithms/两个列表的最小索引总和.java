package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.*;

//假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
//
//你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。
//
// 
//
//示例 1:
//
//输入: list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
//输出: ["Shogun"]
//解释: 他们唯一共同喜爱的餐厅是“Shogun”。
//示例 2:
//
//输入:list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["KFC", "Shogun", "Burger King"]
//输出: ["Shogun"]
//解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
// 
//
//提示:
//
//1 <= list1.length, list2.length <= 1000
//1 <= list1[i].length, list2[i].length <= 30 
//list1[i] 和 list2[i] 由空格 ' ' 和英文字母组成。
//list1 的所有字符串都是 唯一 的。
//list2 中的所有字符串都是 唯一 的。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 两个列表的最小索引总和 {
    @Test
    public void 两个列表的最小索引总和() {
        System.out.println("两个列表的最小索引总和:" + Arrays.toString(findRestaurant(
                new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"},
                new String[]{"KFC", "Shogun", "Burger King"}
        )));
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        //哈希表
        //哈希表记录一个数组的每个字符串的下标
        //在遍历另一个数组，如果找到对应的，判断两个下标之和与上一个和比较：
        //1.若相等，则将该字符串加入结果
        //2.若当前和更小，则清空结果数组，将当前字符串加入结果中
        Map<String, Integer> map1 = new HashMap<>();

        int index = 0;
        for (String s : list1) {
            map1.put(s, index++);
        }

        int sum = Integer.MAX_VALUE;
        index = 0;

        List<String> list = new ArrayList<>();
        for (String s : list2) {
            int i2;
            if ((i2 = map1.getOrDefault(s, -1)) != -1) {
                int sum1 = index + i2;
                if (sum1 < sum) {
                    sum = index + i2;
                    list.clear();
                    list.add(s);
                }else if (sum1==sum){
                    list.add(s);
                }
            }
            index++;
        }

        index = 0;
        String[] result = new String[list.size()];
        for (String s : list) {
            result[index++] = s;
        }

        return result;
    }
}

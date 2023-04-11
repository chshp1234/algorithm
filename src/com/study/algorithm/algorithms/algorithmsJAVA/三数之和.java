package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 三数之和 {

    @Test
    public void 三数之和() {
        System.out.println("三数之和:" + threeSum(new int[] {-1, 0, 1, 2, -1, -4}));
    }

    // 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
    //
    // 注意：答案中不可以包含重复的三元组。
    //
    //
    //
    // 示例：
    //
    // 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
    //
    // 满足要求的三元组集合为：
    // [
    //  [-1, 0, 1],
    //  [-1, -1, 2]
    // ]
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/3sum
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public List<List<Integer>> threeSum(int[] nums) {
        // 由于结果需要不包含重复的三元组。
        // 「不重复」的本质是什么？我们保持三重循环的大框架不变，只需要保证：
        //
        // 第二重循环枚举到的元素不小于当前第一重循环枚举到的元素；
        //
        // 第三重循环枚举到的元素不小于当前第二重循环枚举到的元素。
        //
        // 也就是说，我们枚举的三元组 (a, b, c) 满足 a≤b≤c，保证了只有 (a, b, c) 这个顺序会被枚举到，而(b, a, c)、(c, b, a)
        // 等等这些不会，这样就减少了重复。要实现这一点，我们可以将数组中的元素从小到大进行排序，随后使用普通的三重循环就可以满足上面的要求。
        //
        // 同时，对于每一重循环而言，相邻两次枚举的元素不能相同，否则也会造成重复
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/3sum/solution/san-shu-zhi-he-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        // 前置条件
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        // 结果
        List<List<Integer>> result = new ArrayList<>();

        // 哈希表，记录每个数字在数组中的位置，由于不能重复，所以不用担心数组中有重复数字会对哈希表内容有影响
        Map<Integer, Integer> map = new HashMap<>();

        int length = nums.length;

        // 排序——去重，满足从左到右遍历时，得到的数字a<=b<=c
        Arrays.sort(nums);

        // 将数字加入哈希表
        for (int i = 0; i < length; i++) {
            map.put(nums[i], i);
        }

        int target = 0;
        Integer third;
        List<Integer> add;

        /*for (int second = 1; second < length; second++) {
            if (second > 1 && nums[second] == nums[second - 1]) {
                continue;
            }

            if ((third = map.get(target - nums[second])) != null) {
                if (third > second) {
                    add = new ArrayList<>();
                    add.add(nums[0]);
                    add.add(nums[second]);
                    add.add(nums[third]);
                    result.add(add);
                }
            }
        }*/

        // 遍历获得的第一个数字
        for (int first = 0; first < length; first++) {

            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }

            // 要使三数之和为0，所以目标数为第一个数字取反
            target = -nums[first];

            // 遍历获得的第二个数字
            for (int second = first + 1; second < length; second++) {

                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }

                // 尝试从哈希表中获取第三个数字（target - nums[second]），若存在，并且第三个数字需要在第二个数字右侧
                if ((third = map.get(target - nums[second])) != null) {
                    if (third > second) {
                        // 符合条件，添加进列表中
                        add = new ArrayList<>();
                        add.add(nums[first]);
                        add.add(nums[second]);
                        add.add(nums[third]);
                        result.add(add);
                    }
                    // 要满足找到的数字a≤b≤c，所以当第二项b>c时，退出当前循环（再继续查找也只会出现b>c，而此组3个数肯定已存在于列表中）
                    else {
                        break;
                    }
                }
            }
        }

        return result;
    }

    public List<List<Integer>> threeSumByTwoPointers(int[] nums) {

        // 由于结果需要不包含重复的三元组。
        // 「不重复」的本质是什么？我们保持三重循环的大框架不变，只需要保证：
        //
        // 第二重循环枚举到的元素不小于当前第一重循环枚举到的元素；
        //
        // 第三重循环枚举到的元素不小于当前第二重循环枚举到的元素。
        //
        // 也就是说，我们枚举的三元组 (a, b, c) 满足 a≤b≤c，保证了只有 (a, b, c) 这个顺序会被枚举到，而(b, a, c)、(c, b, a)
        // 等等这些不会，这样就减少了重复。要实现这一点，我们可以将数组中的元素从小到大进行排序，随后使用普通的三重循环就可以满足上面的要求。
        //
        // 同时，对于每一重循环而言，相邻两次枚举的元素不能相同，否则也会造成重复
        // 这个方法就是我们常说的「双指针」，当我们需要枚举数组中的两个元素时，如果我们发现随着第一个元素的递增，第二个元素是递减的，那么就可以使用双指针的方法，将枚举的时间复杂度从
        // O(N^2) 减少至 O(N)。为什么是 O(N) 呢？这是因为在枚举的过程每一步中，「左指针」会向右移动一个位置（也就是题目中的
        // bb），而「右指针」会向左移动若干个位置，这个与数组的元素有关，但我们知道它一共会移动的位置数为 O(N)，均摊下来，每次也向左移动一个位置，因此时间复杂度为
        // O(N)。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/3sum/solution/san-shu-zhi-he-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
}

package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
//
//如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
//
// 
//
//进阶：很容易想到时间复杂度为 O(n^2) 的解决方案，你可以设计一个时间复杂度为 O(n logn) 或 O(n) 的解决方案吗？
//
// 
//
//示例 1：
//
//输入：nums = [1,2,3,4]
//输出：false
//解释：序列中不存在 132 模式的子序列。
//示例 2：
//
//输入：nums = [3,1,4,2]
//输出：true
//解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
//示例 3：
//
//输入：nums = [-1,3,2,0]
//输出：true
//解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
// 
//
//提示：
//
//n == nums.length
//1 <= n <= 104
//-109 <= nums[i] <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/132-pattern
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 子序列132模式 {

    @Test
    public void 子序列132模式() {

        System.out.println("子序列132模式:" + find132pattern(new int[]{3, 5, 0, 5, 4}));
    }

    public boolean find132pattern(int[] nums) {
        //单调栈
        //固定13，枚举2
        //用一个List<int[]>存储每一个上升子序列13两个值（保持1最小，3最大）。
        //枚举2：
        //如果2大于1小于3，成功；
        //如果2比1小，将当前13加入列表；
        //如果2比3大，更新3，并从列表中依次比较13：
        //如果此时3（更新后）比列表中的1大，比列表中的3小，成功；
        //如果此时3（更新后）比列表中的1小，跳出循环继续枚举2；
        //如果此时3（更新后）比列表中的3大，弹出此列表项；
        int len   = nums.length;
        int low   = nums[0];
        int high  = nums[0];
        int index = 1;
        //栈，存储1和3
        Deque<int[]> deque = new LinkedList<>();
        int[]        temp  = new int[2];
        while (index < len) {
            if (nums[index] > low && nums[index] < high) {
                //如果2大于1小于3，成功；
                return true;
            } else if (nums[index] < low) {
                if (low == high) {
                    //更新3的值
                    while (index < len) {
                        if (nums[index] < nums[index - 1]) {
                            low = nums[index];
                        } else if (nums[index] > nums[index - 1]) {
                            high = nums[index];
                            break;
                        }
                        index++;
                    }
                } else {
                    //如果2比1小，将当前13加入列表；
                    deque.push(new int[]{low, high});
                    low = high = nums[index];
                }
            } else if (nums[index] > high) {
                //如果2比3大，更新3，并从列表中依次比较13：
                //这里可以使用二分查找（如下方法）
                high = nums[index];
                while (!deque.isEmpty()) {
                    temp = deque.peek();
                    if (high > temp[0] && high < temp[1]) {
                        //如果此时3比列表中的1大，比列表中的3小，成功；
                        return true;
                    } else if (high <= temp[0]) {
                        //如果此时3比列表中的1小，跳出循环继续枚举2；
                        break;
                    } else if (high >= temp[1]) {
                        //如果此时3比列表中的3大，弹出此列表项；
                        deque.pop();
                    }
                }
            }
            index++;
        }

        return false;
    }

    public boolean find132patternBy2(int[] nums) {
        int len   = nums.length;
        int low   = nums[0];
        int high  = nums[0];
        int index = 1;
        //列表，存储1和3的值
        List<int[]> list = new ArrayList<>();
        int[]       temp;
        while (index < len) {
            if (nums[index] > low && nums[index] < high) {
                //如果2大于1小于3，成功；
                return true;
            } else if (nums[index] < low) {
                if (low == high) {
                    //更新3的值
                    while (index < len) {
                        if (nums[index] < nums[index - 1]) {
                            low = nums[index];
                        } else if (nums[index] > nums[index - 1]) {
                            high = nums[index];
                            break;
                        }
                        index++;
                    }
                } else {
                    //如果2比1小，将当前13加入列表；
                    list.add(new int[]{low, high});
                    low = high = nums[index];
                }
            } else if (nums[index] > high) {
                //如果2比3大，更新3，并从列表中依次比较13：
                //这里可以使用二分查找
                high = nums[index];
                int l = 0, r = list.size();
                while (l < r) {
                    int m = l + (r - l) / 2;
                    temp = list.get(m);
                    if (high > temp[0] && high < temp[1]) {
                        //如果此时3比列表中的1大，比列表中的3小，成功；
                        return true;
                    } else if (high <= temp[0]) {
                        //如果此时3比列表中的1小，左区间+1；
                        l = m + 1;
                    } else if (high >= temp[1]) {
                        //如果此时3比列表中的3大，右区间-1；
                        r = r - 1;
                    }
                }
                //移除所有做区间右侧的元素（最大值3将被更新）
                for (int i = list.size() - 1; i >= l; i--) {
                    list.remove(i);
                }
            }
            index++;
        }

        return false;
    }
}

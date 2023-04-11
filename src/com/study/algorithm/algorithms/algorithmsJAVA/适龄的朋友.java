package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

//在社交媒体网站上有 n 个用户。给你一个整数数组 ages ，其中 ages[i] 是第 i 个用户的年龄。
//
//如果下述任意一个条件为真，那么用户 x 将不会向用户 y（x != y）发送好友请求：
//
//age[y] <= 0.5 * age[x] + 7
//age[y] > age[x]
//age[y] > 100 && age[x] < 100
//否则，x 将会向 y 发送一条好友请求。
//
//注意，如果 x 向 y 发送一条好友请求，y 不必也向 x 发送一条好友请求。另外，用户不会向自己发送好友请求。
//
//返回在该社交媒体网站上产生的好友请求总数。
//
// 
//
//示例 1：
//
//输入：ages = [16,16]
//输出：2
//解释：2 人互发好友请求。
//示例 2：
//
//输入：ages = [16,17,18]
//输出：2
//解释：产生的好友请求为 17 -> 16 ，18 -> 17 。
//示例 3：
//
//输入：ages = [20,30,100,110,120]
//输出：3
//解释：产生的好友请求为 110 -> 100 ，120 -> 110 ，120 -> 100 。
// 
//
//提示：
//
//n == ages.length
//1 <= n <= 2 * 104
//1 <= ages[i] <= 120
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/friends-of-appropriate-ages
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 适龄的朋友 {
    @Test
    public void 适龄的朋友() {
        int[] nums = new int[]{16, 16, 16, 17, 18, 18, 19, 20, 30};

        System.out.println("适龄的朋友：" + numFriendRequestsByLeetCode(nums));
    }

    public int numFriendRequests(int[] ages) {
        //排序+双指针
        //1.首先对年龄进行从低到高排序，这样就可以方便找到匹配的y
        //2.使用双指针，left代表匹配的y的最小值，right代表x。
        //3.遍历过程中，若ages[y]符合条件，那么ages[y..x-1]的所有数都必定匹配条件，那么此时result+=right-left
        //4.若此时y不匹配，那么，left+1，若left==right，那么right也+1.
        //5.遍历过程中需注意相同的元素，right右移的过程中，记录相同元素的数量，因为在前面的步骤，我们right都代表的是x，但相同元素下，right也可以代表y，所以需要多一步的计算。
        //记录相同元素的数量same，直到遇到一个不和right相同的元素时，此时result+=(same * (same + 1)) / 2;
        //当然，最后遍历结束也许在统计一次same值。再返回result即可。
        Arrays.sort(ages);
        int len = ages.length;
        int left = 0, right = 1;
        int result = 0;
        int same = 0;
        while (left < len) {
            if (ages[left] <= 14) {
                left++;
            } else {
                break;
            }
        }
        right = left + 1;
        while (right < len) {
            if (ages[right] == ages[right - 1]) {
                same++;
            } else {
                result += (same * (same + 1)) / 2;
                same = 0;
            }
            if (ages[left] - 7 > ages[right] * 0.5) {
                result += right - left;
                right++;
            } else {
                left++;
                if (left == right) {
                    right++;
                }
            }
        }

        result += (same * (same + 1)) / 2;

        return result;
    }

    public int numFriendRequestsByLeetCode(int[] ages) {
        int n = ages.length;
        Arrays.sort(ages);
        int left = 0, right = 0, ans = 0;
        for (int age : ages) {
            if (age < 15) {
                continue;
            }
            while (ages[left] <= 0.5 * age + 7) {
                ++left;
            }
            while (right + 1 < n && ages[right + 1] <= age) {
                ++right;
            }
            ans += right - left;
        }
        return ans;
    }
}

package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
//
//找到所有在 [1, n] 范围之间没有出现在数组中的数字。
//
//您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
//
//示例:
//
//输入:
//[4,3,2,7,8,2,3,1]
//
//输出:
//[5,6]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 找到所有数组中消失的数字 {

    @Test
    public void 找到所有数组中消失的数字() {
        int[] ints = {1, 2, 8, 4, 1, 4, 3, 5};
        System.out.println("找到所有数组中消失的数字:" + findDisappearedNumbers(ints));
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        //原地修改
        //由于元素为1~len，所以元素的值-1都可在数组中找到可存放的位置
        //遍历并交换元素：
        //若元素值不再正确位置，令当前位置的值为0，并继续交换判断下一个位置的元素
        //遍历结束后，查找数组中值为0的元素所在的位置index，则当前值index+1就为“消失的数字”
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] - 1 != i) {
                int temp = nums[i];
                nums[i] = 0;
                swap(nums, temp);
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                result.add(i + 1);
            }
        }
        return result;
    }

    public void swap(int[] arrays, int num) {

        int realIndex = num - 1;
        if (arrays[realIndex] == 0) {
            arrays[realIndex] = num;
            return;
        }
        if (arrays[realIndex] != num) {
            int e = arrays[realIndex];
            arrays[realIndex] = num;
            swap(arrays, e);
        }
    }

    public List<Integer> findDisappearedNumbersByLeetCode(int[] nums) {
        //我们可以用一个哈希表记录数组 nums 中的数字，
        //由于数字范围均在 [1,n] 中，记录数字后我们再利用哈希表检查 [1,n] 中的每一个数是否出现，从而找到缺失的数字。
        //
        //由于数字范围均在 [1,n] 中，我们也可以用一个长度为 n 的数组来代替哈希表。
        //这一做法的空间复杂度是 O(n) 的。而我们的目标是优化空间复杂度到 O(1)。
        //
        //注意到 nums 的长度恰好也为 n，能否让 nums 充当哈希表呢？
        //
        //由于 nums 的数字范围均在 [1,n] 中，我们可以利用这一范围之外的数字，来表达「是否存在」的含义。
        //
        //具体来说，遍历 nums，每遇到一个数 x，就让 nums[x−1] 增加 n。
        //由于 nums 中所有数均在 [1,n] 中，增加以后，这些数必然大于 n。
        //最后我们遍历 nums，若 nums[i] 未大于 n，就说明没有遇到过数 i+1。这样我们就找到了缺失的数字。
        //
        //注意，当我们遍历到某个位置时，其中的数可能已经被增加过，因此需要对 n 取模来还原出它本来的值。
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/solution/zhao-dao-suo-you-shu-zu-zhong-xiao-shi-d-mabl/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        //具体就是使某个位置的元素，既能表示原始值num，也能表示根据当前位置index所代表的数组中存在的值。犇啊~
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                ret.add(i + 1);
            }
        }
        return ret;
    }
}

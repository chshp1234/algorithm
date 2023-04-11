package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
//
//给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
//
// 
//
//示例 1：
//
//输入：A = [1,2,0,0], K = 34
//输出：[1,2,3,4]
//解释：1200 + 34 = 1234
//示例 2：
//
//输入：A = [2,7,4], K = 181
//输出：[4,5,5]
//解释：274 + 181 = 455
//示例 3：
//
//输入：A = [2,1,5], K = 806
//输出：[1,0,2,1]
//解释：215 + 806 = 1021
//示例 4：
//
//输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
//输出：[1,0,0,0,0,0,0,0,0,0,0]
//解释：9999999999 + 1 = 10000000000
// 
//
//提示：
//
//1 <= A.length <= 10000
//0 <= A[i] <= 9
//0 <= K <= 10000
//如果 A.length > 1，那么 A[0] != 0
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/add-to-array-form-of-integer
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 数组形式的整数加法 {
    @Test
    public void 数组形式的整数加法() {
        int[] ints = {2, 1, 5};
        System.out.println("数组形式的整数加法:" + addToArrayForm(ints, 806));
    }

    public List<Integer> addToArrayForm(int[] A, int K) {
        //遍历，从数组尾部和K数尾部依次每位进行相加
        //最后把数组A或K中剩余没有处理的高位再进行处理
        //需注意，进位，要在每一位中进行计算处理
        List<Integer> result = new LinkedList<>();
        int index = A.length - 1;

        int step = 0;
        int num;
        int sum;
        while (index >= 0 && K > 0) {
            num = K % 10;
            sum = A[index] + num + step;
            step = sum / 10;
            sum = sum % 10;
            result.add(0, sum);

            index--;
            K = K / 10;
        }
        while (K > 0) {
            sum = K % 10 + step;
            step = sum / 10;
            sum = sum % 10;
            result.add(0, sum);
            K = K / 10;
        }

        while (index >= 0) {
            sum = A[index] + step;
            step = sum / 10;
            sum = sum % 10;
            result.add(0, sum);
            index--;
        }
        if (step > 0) {
            result.add(0, 1);
        }

        return result;
    }

    //另一个思路是将整个加数 K 加入数组表示的数的最低位。
    //
    //上面的例子 123+912，我们把它表示成 [1,2,3+912]。然后，我们计算 3+912=915。
    //5 留在当前这一位，将 910/10=91 以进位的形式加入下一位。
    //
    //然后，我们再重复这个过程，计算 [1,2+91,5]。
    //我们得到 93，3 留在当前位，将 90/10=9 以进位的形式加入下一位。
    //继而又得到 [1+9,3,5]，重复这个过程之后，最终得到结果 [1,0,3,5]。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/add-to-array-form-of-integer/solution/shu-zu-xing-shi-de-zheng-shu-jia-fa-by-l-jljp/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public List<Integer> addToArrayFormByLeetCode(int[] A, int K) {
        List<Integer> res = new ArrayList<Integer>();
        int n = A.length;
        for (int i = n - 1; i >= 0 || K > 0; --i, K /= 10) {
            if (i >= 0) {
                K += A[i];
            }
            res.add(K % 10);
        }
        Collections.reverse(res);
        return res;
    }
}

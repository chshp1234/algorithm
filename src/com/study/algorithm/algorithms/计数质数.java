package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 统计所有小于非负整数 n 的质数的数量。
//
//
//
// 示例 1：
//
// 输入：n = 10
// 输出：4
// 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
// 示例 2：
//
// 输入：n = 0
// 输出：0
// 示例 3：
//
// 输入：n = 1
// 输出：0
//
//
// 提示：
//
// 0 <= n <= 5 * 106
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/count-primes
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 计数质数 {

    @Test
    public void 计数质数() {

        System.out.println("计数质数:" + countPrimesByLine(101));
    }

    // 质数的定义：在大于 11 的自然数中，除了 11 和它本身以外不再有其他因数的自然数。
    public int countPrimes(int n) {
        // 暴力枚举
        // 线性枚举到n，判断每个数是否是素数
        // 判断一个数x是否是素数，只要枚举[2,x-1]中的每个数y，判断y是否是x的因数。
        // ·进一步优化，x要能整除一个数，这个数最大为x的一半，所以只要枚举[2,x/2]即可。
        // ·再进一步优化，考虑到如果 y 是 x 的因数，那么x/y也必然是 x 的因数，因此我们只要校验 y 或者x/y即可。
        // ·再进一步优化，而如果我们每次选择校验两者中的较小数y，y从小到大遍历的过程中，x/y的值必然是从大到小，y=(x/y)时，此时若y再增大，x/y的值将会减小，
        // 将会出现重复计算。如x=15,y遍历到3时，x/y=5，若y继续增加到y=5，x/y=3，很明显出现重复计算了。所以我们只需要枚举 [2,sqrt{x}]中的所有数即可。
        int result = 0;
        for (int i = 3; i < n; i++) {
            if (isPrimes(i)) {
                result++;
            }
        }
        return n < 3 ? 0 : result + 1;
    }

    public boolean isPrimes(int n) {
        for (int i = 2, l = (int) Math.sqrt(n); i <= l; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int countPrimesByHashMap(int n) {
        // 埃氏筛
        // 用额外的空间，记录每个数是否是素数
        // 如果 x 是质数，那么大于 x 的 x 的倍数 2x,3x,4x,… 一定不是质数，因此我们可以从这里入手。
        // 同暴力，我们只需遍历到sqrt(n)，再之后的值不需要在计算。
        // 如果一个数x是合数，我们不对其进行计算，它一定是某个小于x的质数y的倍数，那么，2x一定也是y的倍数，而2x也肯定经过y进行计算并标记了，所以我们不必对x进行计算。
        // 当然这里还可以继续优化，对于一个质数 x，如果按上文说的我们从 2x 开始标记其实是冗余的，应该直接从 x*x 开始标记，
        // 因为2x,3x,4x,… 这些数一定在 x 之前就被其他数的倍数标记过了，例如 2 的所有倍数，3 的所有倍数等。

        int result = 0;
        boolean[] isNotPrime = new boolean[n];
        for (int i = 2, l = (int) Math.sqrt(n); i <= l; i++) {

            // 如果这个数已经被标记为合数，则继续
            if (isNotPrime[i]) {
                continue;
            }

            // 从i开始计算并标记i*i之后的合数
            for (int j = i; ; ) {
                if (i * j >= n) {
                    break;
                }
                isNotPrime[i * j] = true;
                j++;
            }
        }

        // 标记数组中所有未标记的数即为质数
        for (int i = 2; i < n; i++) {
            if (!isNotPrime[i]) {
                result++;
            }
        }
        return result;
    }

    public int countPrimesByLine(int n) {
        // 线性筛
        // 相较于埃氏筛，我们多维护一个 primes 数组表示当前得到的质数集合。
        // 我们从小到大遍历，如果当前的数 x 是质数，就将其加入primes 数组。
        // 关键之处在：if(i % primes.get(j) == 0)  break;
        // 这句代码保证了每个数最多被筛一次，将时间复杂度降到了线性。
        // 证：prime[]数组中的素数是递增的,当i能整除prime[j]，那么i*prime[j+1]这个合数肯定会被prime[j]乘以某个数筛掉。
        // 因此，这里直接break掉，将i*prime[j+1]及之后的给后面的数去筛。
        // 所以按照最小素因子筛选，可以保证每个数都只会被筛一遍，又能保证每个数都被筛到。

        List<Integer> primes = new ArrayList<>();
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                primes.add(i);
            }
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
                // 按照最小素因子筛选
                isPrime[i * primes.get(j)] = 0;
                // 将i*prime[j+1]及之后的给后面的数去筛
                if (i % primes.get(j) == 0) {
                    break;
                }
            }
        }

        // 返回质数数组集合大小
        return primes.size();
    }
}

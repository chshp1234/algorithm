## 介绍

当需要在n个元素中找出k个元素的组合时，此算法巧妙的运用位运算，可在O($C_n^2$)时间复杂度内求出$C_n^2$个解（复杂度与答案数量等价）。相比暴力解法的O($n^2$)复杂度，会有更高的效率。

## 代码
```C
void GospersHack(int k, int n) {
    int cur = (1 << k) - 1;
    int limit = (1 << n);
    while (cur < limit) {
        // do something
        cout << cur << endl;/*  */

        int lb = cur & -cur;
        int r = cur + lb;
        cur = ((r ^ cur) >> (__builtin_ctz(lb) + 2)) | r;
        // __builtin_ctz(lb)在java中是Integer.numberOfTrailingZeros(lb)，表示二进制表示的lb中尾随0的数量
        // 或：cur = (((r ^ cur) >> 2) / lb) | r;
    }
}
```

## 呈现
假如k=3，n=5，那么运行结果如下：
```
00111
01011
01101
01110
10011
10101
10110
11001
11010
11100
```
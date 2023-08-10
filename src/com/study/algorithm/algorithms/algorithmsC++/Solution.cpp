#include <vector>
#include <iostream>
using namespace std;

// 反转字符串
/*
编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。

不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。



示例 1：

输入：s = ["h","e","l","l","o"]
输出：["o","l","l","e","h"]
示例 2：

输入：s = ["H","a","n","n","a","h"]
输出：["h","a","n","n","a","H"]


提示：

1 <= s.length <= 105
s[i] 都是 ASCII 码表中的可打印字符
*/
void reverseString(vector<char> &s)
{
    /*
    双指针
    使用头尾双指针进行字符交换,交换后头指针+1,尾指针-1
    */
    int l = 0;
    int r = s.size() - 1;
    while (l < r)
    {
        s[l] ^= s[r];
        s[r] ^= s[l];
        s[l] ^= s[r];
        l++;
        r--;
    }
}

int main()
{
    vector<char> v1 = {'h','e','l','l','o'};
    reverseString(v1);
    for (char c : v1) {
        std::cout << c;
    }
    std::cout << std::endl;

    return 0;
}
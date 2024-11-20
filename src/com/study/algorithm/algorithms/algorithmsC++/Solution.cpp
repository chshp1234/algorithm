#include <algorithm>
#include <bitset>
#include <climits>
#include <cmath>
#include <cstddef>
#include <cstdlib>
#include <cstring>
#include <iostream>
#include <iterator>
#include <queue>
#include <string>
#include <type_traits>
#include <unordered_map>
#include <unordered_set>
#include <utility>
#include <vector>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

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
void reverseString(vector<char> &s) {
    /*
    双指针
    使用头尾双指针进行字符交换,交换后头指针+1,尾指针-1
    */
    int l = 0;
    int r = s.size() - 1;
    while (l < r) {
        s[l] ^= s[r];
        s[r] ^= s[l];
        s[l] ^= s[r];
        l++;
        r--;
    }
}

/*给你一个字符串数组 words 和一个字符串 s ，请你判断 s 是不是 words 的 首字母缩略词 。

如果可以按顺序串联 words 中每个字符串的第一个字符形成字符串 s ，则认为 s 是 words 的首字母缩略词。例如，"ab" 可以由 ["apple",
"banana"] 形成，但是无法从 ["bear", "aardvark"] 形成。

如果 s 是 words 的首字母缩略词，返回 true ；否则，返回 false 。



示例 1：

输入：words = ["alice","bob","charlie"], s = "abc"
输出：true
解释：words 中 "alice"、"bob" 和 "charlie" 的第一个字符分别是 'a'、'b' 和 'c'。因此，s = "abc" 是首字母缩略词。
示例 2：

输入：words = ["an","apple"], s = "a"
输出：false
解释：words 中 "an" 和 "apple" 的第一个字符分别是 'a' 和 'a'。
串联这些字符形成的首字母缩略词是 "aa" 。
因此，s = "a" 不是首字母缩略词。
示例 3：

输入：words = ["never","gonna","give","up","on","you"], s = "ngguoy"
输出：true
解释：串联数组 words 中每个字符串的第一个字符，得到字符串 "ngguoy" 。
因此，s = "ngguoy" 是首字母缩略词。


提示：

1 <= words.length <= 100
1 <= words[i].length <= 10
1 <= s.length <= 100
words[i] 和 s 由小写英文字母组成*/
bool isAcronym(vector<string> &words, string s) {
    /*
    模拟，一趟遍历
    首先判断字符串数组长度和字符串长度是否相同，如果不相同则字符串不可能由字符串数组组成
    再对字符串和字符串数组进行遍历，依次判断字符串数组中每个字符串的首字母是否和字符串对于下标处的字符相同
    */
    if (words.size() != s.length()) {
        return false;
    }
    for (int i = 0; i < words.size(); i++) {
        if (words[i][0] != s.at(i)) {
            return false;
        }
    }
    return true;
}
/*给你一个整数 total ，表示你拥有的总钱数。同时给你两个整数 cost1 和 cost2
，分别表示一支钢笔和一支铅笔的价格。你可以花费你部分或者全部的钱，去买任意数目的两种笔。

请你返回购买钢笔和铅笔的 不同方案数目 。



示例 1：

输入：total = 20, cost1 = 10, cost2 = 5
输出：9
解释：一支钢笔的价格为 10 ，一支铅笔的价格为 5 。
- 如果你买 0 支钢笔，那么你可以买 0 ，1 ，2 ，3 或者 4 支铅笔。
- 如果你买 1 支钢笔，那么你可以买 0 ，1 或者 2 支铅笔。
- 如果你买 2 支钢笔，那么你没法买任何铅笔。
所以买钢笔和铅笔的总方案数为 5 + 3 + 1 = 9 种。
示例 2：

输入：total = 5, cost1 = 10, cost2 = 10
输出：1
解释：钢笔和铅笔的价格都为 10 ，都比拥有的钱数多，所以你没法购买任何文具。所以只有 1 种方案：买 0 支钢笔和 0 支铅笔。


提示：

1 <= total, cost1, cost2 <= 106*/
long long waysToBuyPensPencils(int total, int cost1, int cost2) {
    /*
    枚举：
    一步步枚举，枚举购买0支钢笔时，可以购买几支铅笔；购买1支钢笔时，可以购买几支铅笔n0，购买2支钢笔时n1，可以购买几支铅笔n2。。。直到钱用完为止。
    注意可以购买的铅笔的数量n，算在结果里要是n+1，因为也可以购买0支铅笔。
    最后返回，每次枚举时，可以购买的铅笔的数量的总和n0+n1+n2+...
    */
    long long res = 0;
    while (total >= 0) {
        res += (total / cost2) + 1;
        total -= cost1;
    }
    return res;
}

/*给你一个长度为 n ，下标从 0 开始的整数数组 forts ，表示一些城堡。forts[i] 可以是 -1 ，0 或者 1 ，其中：

-1 表示第 i 个位置 没有 城堡。
0 表示第 i 个位置有一个 敌人 的城堡。
1 表示第 i 个位置有一个你控制的城堡。
现在，你需要决定，将你的军队从某个你控制的城堡位置 i 移动到一个空的位置 j ，满足：

0 <= i, j <= n - 1
军队经过的位置 只有 敌人的城堡。正式的，对于所有 min(i,j) < k < max(i,j) 的 k ，都满足 forts[k] == 0 。
当军队移动时，所有途中经过的敌人城堡都会被 摧毁 。

请你返回 最多 可以摧毁的敌人城堡数目。如果 无法 移动你的军队，或者没有你控制的城堡，请返回 0 。



示例 1：

输入：forts = [1,0,0,-1,0,0,0,0,1]
输出：4
解释：
- 将军队从位置 0 移动到位置 3 ，摧毁 2 个敌人城堡，位置分别在 1 和 2 。
- 将军队从位置 8 移动到位置 3 ，摧毁 4 个敌人城堡。
4 是最多可以摧毁的敌人城堡数目，所以我们返回 4 。
示例 2：

输入：forts = [0,0,1,-1]
输出：0
解释：由于无法摧毁敌人的城堡，所以返回 0 。


提示：

1 <= forts.length <= 1000
-1 <= forts[i] <= 1*/
int captureForts(const vector<int> &forts) {
    /*
    枚举
    1.当元素为-1时，说明到达空地，如果起点位置是1，那么计算这之间的敌方数，并比较最大值。并且，可以将此设为起点（或者说终点，接下来就要找元素值为1的起点）
    2.当元素为1时，说明到达一处己方城堡，如果之前的起点是-1，那么说明可以从此往前行军至起点（终点），可计算并比较之间的敌方数的最大值。并且，可将此设为起点，接下来寻找值为-1的终点。
    所以要，最终要解决的问题就是，枚举所有1和-1之间的元素为0的数量，计算并维护最大值即可
    */
    int start = 0;
    int res = 0;
    for (int i = 0; i < forts.size(); i++) {
        switch (forts[i]) {
            case -1:
                if (forts[start] == 1) {
                    res = max(res, i - start - 1);
                }
                start = i;
                break;
            case 1:
                if (forts[start] == -1) {
                    res = max(res, i - start - 1);
                }
                start = i;
                break;
        }
    }
    return res;
}

/*银行内部的防盗安全装置已经激活。给你一个下标从 0 开始的二进制字符串数组 bank ，表示银行的平面图，这是一个大小为 m x n
的二维矩阵。 bank[i] 表示第 i 行的设备分布，由若干 '0' 和若干 '1' 组成。'0' 表示单元格是空的，而 '1' 表示单元格有一个安全设备。

对任意两个安全设备而言，如果同时 满足下面两个条件，则二者之间存在 一个 激光束：

两个设备位于两个 不同行 ：r1 和 r2 ，其中 r1 < r2 。
满足 r1 < i < r2 的 所有 行 i ，都 没有安全设备 。
激光束是独立的，也就是说，一个激光束既不会干扰另一个激光束，也不会与另一个激光束合并成一束。

返回银行中激光束的总数量。



示例 1：



输入：bank = ["011001","000000","010100","001000"]
输出：8
解释：在下面每组设备对之间，存在一条激光束。总共是 8 条激光束：
 * bank[0][1] -- bank[2][1]
 * bank[0][1] -- bank[2][3]
 * bank[0][2] -- bank[2][1]
 * bank[0][2] -- bank[2][3]
 * bank[0][5] -- bank[2][1]
 * bank[0][5] -- bank[2][3]
 * bank[2][1] -- bank[3][2]
 * bank[2][3] -- bank[3][2]
注意，第 0 行和第 3 行上的设备之间不存在激光束。
这是因为第 2 行存在安全设备，这不满足第 2 个条件。
示例 2：



输入：bank = ["000","111","000"]
输出：0
解释：不存在两个位于不同行的设备


提示：

m == bank.length
n == bank[i].length
1 <= m, n <= 500
bank[i][j] 为 '0' 或 '1'*/
int numberOfBeams(vector<string> &bank) {
    /*
    模拟，一趟遍历
    直接对二维数组进行遍历
    记录上一行（数量不为0的行）的激光设备的数量last，计算每一行中，激光设备的数量count，这之间可构成的激光数就为last*count
    注意只有当激光设备的数量不为0时，才将last=count
    最后记录并统计所有激光数量并返回即可
    */
    int res = 0;
    int last = 0;

    for (const string &s : bank) {
        int count = 0;
        for (auto c : s) {
            if (c == '1') {
                count++;
            }
        }
        if (count != 0) {
            res += (count * last);
            last = count;
        }
    }

    return res;
}

// 从两个数字数组里生成最小数字
/*给你两个只包含 1 到 9 之间数字的数组 nums1 和 nums2 ，每个数组中的元素 互不相同 ，请你返回 最小 的数字，两个数组都 至少
包含这个数字的某个数位。


示例 1：

输入：nums1 = [4,1,3], nums2 = [5,7]
输出：15
解释：数字 15 的数位 1 在 nums1 中出现，数位 5 在 nums2 中出现。15 是我们能得到的最小数字。
示例 2：

输入：nums1 = [3,5,2,6], nums2 = [3,1,7]
输出：3
解释：数字 3 的数位 3 在两个数组中都出现了。


提示：

1 <= nums1.length, nums2.length <= 9
1 <= nums1[i], nums2[i] <= 9
每个数组中，元素 互不相同 。*/
int minNumber(const vector<int> &nums1, const vector<int> &nums2) {
    /*
    哈希表
    因为数组元素只有1~9，且每个元素值不同，所以可以使用一个bitset记录一个数组中的元素。
    1.将每个数组元素加入各自的bitset中
    2.遍历bitset时，如果bitset对应位下标i的元素为1，说明元素i存在于数组中
    3.从1~9进行循环遍历bitset，如果元素同时存在与两个数组中（判断bitset的对应位是否为1），则返回此元素
    4.遍历时记录两个数组最小值min1和min2
    5.如果两个数组中没有相同的元素，那么返回min(min1,min2)*10+max(min1,min2)
    */
    bitset<10> bits1;
    bitset<10> bits2;
    for (int i = 0; i < nums1.size(); i++) {
        bits1.set(nums1[i]);
    }
    for (int i = 0; i < nums2.size(); i++) {
        bits2.set(nums2[i]);
    }
    int min1 = 0, min2 = 0;
    for (int i = 1; i <= 9; i++) {
        if (bits1[i] == 1 && bits2[i] == 1) {
            return i;
        }
        if (bits1[i] == 1 && min1 == 0) {
            min1 = i;
        }
        if (bits2[i] == 1 && min2 == 0) {
            min2 = i;
        }
    }
    if (min1 < min2) {
        return min1 * 10 + min2;
    }
    return min2 * 10 + min1;
}

// 最深叶节点的最近公共祖先
/*给你一个有根节点 root 的二叉树，返回它 最深的叶节点的最近公共祖先 。

回想一下：

叶节点 是二叉树中没有子节点的节点
树的根节点的 深度 为 0，如果某一节点的深度为 d，那它的子节点的深度就是 d+1
如果我们假定 A 是一组节点 S 的 最近公共祖先，S 中的每个节点都在以 A 为根节点的子树中，且 A 的深度达到此条件下可能的最大值。


示例 1：


输入：root = [3,5,1,6,2,0,8,null,null,7,4]
输出：[2,7,4]
解释：我们返回值为 2 的节点，在图中用黄色标记。
在图中用蓝色标记的是树的最深的节点。
注意，节点 6、0 和 8 也是叶节点，但是它们的深度是 2 ，而节点 7 和 4 的深度是 3 。
示例 2：

输入：root = [1]
输出：[1]
解释：根节点是树中最深的节点，它是它本身的最近公共祖先。
示例 3：

输入：root = [0,1,3,null,2]
输出：[2]
解释：树中最深的叶节点是 2 ，最近公共祖先是它自己。


提示：

树中的节点数将在 [1, 1000] 的范围内。
0 <= Node.val <= 1000
每个节点的值都是 独一无二 的。


注意：本题与力扣 865 重复：https://leetcode-cn.com/problems/smallest-subtree-with-all-the-deepest-nodes/*/
pair<TreeNode *, int> lcaLeaves(TreeNode *root) {
    /*
    深度优先搜索
    使用dfs遍历，使用一个pair<TreeNode *, int>记录当前子树的最近公共祖先，并记录当前子树的深度。
    1.遍历到空节点时，返回{NULL, 0};
    2.递归遍历左子树left和右子树right
    3.1.如果左子树的节点深度和右子树的节点深度相同，那么，最近的公共祖先就为当前节点
    3.2.如果左子树的节点深度比右子树的节点深度大，那么最近公共祖先为左子树的最近公共祖先
    3.3.如果左子树的节点深度比右子树的节点深度小，那么最近公共祖先为右子树的最近公共祖先
    3.4.节点深度+1，返回当前pair值
    4.最后递归结束返回pair.first即可获取最近的公共祖先
    */
    if (root == NULL) {
        return {NULL, 0};
    }
    auto left = lcaLeaves(root->left);
    auto right = lcaLeaves(root->right);
    if (left.second == right.second) {
        left.first = root;
        left.second++;
        return left;
    }
    if (left.second > right.second) {
        left.second++;
        return left;
    }
    right.second++;
    return right;
}
TreeNode *lcaDeepestLeaves(TreeNode *root) {
    /*
    广度优先搜索
    需要两个队列，一个哈希表
    一个队列tmp记录上一层节点，一个队列que记录下一层节点，一个哈希表childToParent记录每个子节点对应的父节点
    1.先对que进行遍历，将每个子节点对应的父节点加入哈希表中，并维护上一层节点tmp，直到que队列为空
    2.1.再遍历tmp（此时tmp里就为二叉树中最后一层的节点）
    2.2.判断每个节点的父节点是否存在于tmp中，如果不存在，则加入
    2.3.直到tmp队列大小为1，这1个节点就为最近公共祖先，退出循环
    3.返回tmp.front();
    */
    queue<TreeNode *> tmp;
    queue<TreeNode *> que;
    unordered_map<TreeNode *, TreeNode *> childToParent;
    que.push(root);
    while (!que.empty()) {
        int size = que.size();
        tmp = queue<TreeNode *>(que);
        for (int i = 0; i < size; i++) {
            TreeNode *node = que.front();
            que.pop();
            if (node->left != NULL) {
                childToParent[node->left] = node;
                que.push(node->left);
            }
            if (node->right != NULL) {
                childToParent[node->right] = node;
                que.push(node->right);
            }
        }
    }
    while (tmp.size() > 1) {
        int size = tmp.size();
        for (int i = 0; i < size; i++) {
            TreeNode *node = tmp.front();
            tmp.pop();
            if (childToParent[node] != tmp.back()) {
                tmp.push(childToParent[node]);
            }
        }
    }
    return tmp.front();
}

// 获取一个数num的最高位的1
long long highestOneBit(long long num) {
    num |= num >> 1;
    num |= num >> 2;
    num |= num >> 4;
    num |= num >> 8;
    num |= num >> 16;
    return num - (num >> 1);
}
// 两数相除
/*给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。



注意：

整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231−1]。本题中，如果除法结果溢出，则返回 231 − 1


示例 1：

输入：a = 15, b = 2
输出：7
解释：15/2 = truncate(7.5) = 7
示例 2：

输入：a = 7, b = -3
输出：-2
解释：7/-3 = truncate(-2.33333..) = -2
示例 3：

输入：a = 0, b = 1
输出：0
示例 4：

输入：a = 1, b = 1
输出：1


提示:

-231 <= a, b <= 231 - 1
b != 0
注意：本题与主站 29 题相同：https://leetcode-cn.com/problems/divide-two-integers/*/
int divide(int a, int b) {
    // 移位相减除法算法
    // 因为会溢出，所以首先得用long long存储每一位
    // 再用数列式进行除法运算
    // 1.先将a,b取正，再取a的最高位的1，cur，以及一个除数divisor
    // 2.每次运算时，res和divisor左移一位（相当于乘2）
    // 3.再用除法数列式进行计算，cur每次右移一位，并判断当前位的a是否为1，是的话除数divisor+1
    // 4.判断divisor是否大于b，如果大于，res++，并且divisor=divisor-b
    // 5.直到cur等于0，退出循环，因为a和b都取正了，所以最后要做判断，原先的a和b是否同符号，不是的话，结果res取负数。还得判断res是否超过int整形最大值
    long long res = 0;
    long long divisor = 0;
    long long aCopy = abs((long long)a);
    long long bCopy = abs((long long)b);
    long long cur = highestOneBit(aCopy);
    while (cur > 0) {
        res <<= 1;
        divisor <<= 1;
        if ((aCopy & cur) > 0) {
            divisor += 1;
        }
        if (divisor >= bCopy) {
            res += 1;
            divisor -= bCopy;
        }
        cur >>= 1;
    }
    if ((a ^ b) < 0) {
        return -res;
    }
    if (res > 2147483647) {
        return 2147483647;
    }
    return res;
}

// 删除字符使频率相同
/*给你一个下标从 0 开始的字符串 word ，字符串只包含小写英文字母。你需要选择 一个 下标并 删除 下标处的字符，使得 word
中剩余每个字母出现 频率 相同。

如果删除一个字母后，word 中剩余所有字母的出现频率都相同，那么返回 true ，否则返回 false 。

注意：

字母 x 的 频率 是这个字母在字符串中出现的次数。
你 必须 恰好删除一个字母，不能一个字母都不删除。


示例 1：

输入：word = "abcc"
输出：true
解释：选择下标 3 并删除该字母，word 变成 "abc" 且每个字母出现频率都为 1 。
示例 2：

输入：word = "aazz"
输出：false
解释：我们必须删除一个字母，所以要么 "a" 的频率变为 1 且 "z" 的频率为 2
，要么两个字母频率反过来。所以不可能让剩余所有字母出现频率相同。


提示：

2 <= word.length <= 100
word 只包含小写英文字母。*/
bool equalFrequency(string word) {
    /*
    哈希表，计数
    用哈希表maps统计所有字符出现的频次，并维护共有几个不同的字母count
    然后，对每一个字母都进行删除（想象成俄罗斯方块删除一行），统计此时还剩几个不同的字母countNow，以及还剩几个字母sum（数量不为0的）
    最后开始进行判断：
    1.if (countNow == 0 && sum == 0)，说明原先每个字母的频次都是1，那么删除任意一个都可达到条件，返回true
    2.if (countNow == 1 &&sum1)，此时只剩一个字母了
    说明其他字母频次都是1，这个字母频次是2，那么可以删除这个字母达到条件，那么可以返回true
    3.if (countNow == 1 && sum > 1)，剩余字母数量是1，但剩余的字母的频次大于1，显然无法达到条件，返回false
    4.if (count- countNow > 1)，剩余字母数量大于1（），并且原先有好几个频次是1的字母，但+那么无法达到条件，返回false
    5.if (count - countNow == 0)，说明原先没有频次是1的字母，那么可删除的字母将是频次最多的那个，令sum--，并且countNow将不变
    6.if (sum % countNow != 0)，进行平均数计算，如果总数不能被字母数量整除，那也肯定无法达到条件，返回false
    7.循环遍历maps，if (c != 0 && c != sum && (c - 1) != sum)，说明这个字母频次不为0，并且不是平均数，那么无法达到条件，返回false
    */
    int maps[26]{0};
    for (char c : word) {
        maps[c - 'a']++;
    }
    int count = 0;
    int sum = word.length();
    for (int c : maps) {
        if (c != 0) {
            count++;
        }
    }
    // 说明原先每个字母的频次都是1，那么删除任意一个都可达到条件，返回true
    if (sum == count) {
        return true;
    }
    int countNow = count;
    for (int i = 0; i < 26; i++) {
        if (maps[i] != 0) {
            maps[i]--;
            sum--;
            if (maps[i] == 0) {
                countNow--;
            }
        }
    }
    // 此时只剩一个字母了，说明其他字母频次都是1，这个字母频次是2，那么可以删除这个字母达到条件，那么可以返回true
    if (countNow == 1 && sum == 1) {
        return true;
    }
    // 剩余字母数量是1，但剩余的字母的频次大于1，显然无法达到条件，返回false
    if (countNow == 1 && sum > 1) {
        return false;
    }
    // 剩余字母数量大于1（），并且原先有好几个频次是1的字母，但+那么无法达到条件，返回false
    if (count - countNow > 1) {
        return false;
    } else if (count - countNow == 0) {
        // 说明原先没有频次是1的字母，那么可删除的字母将是频次最多的那个，令sum--，并且countNow将不变
        sum--;
    }
    // 进行平均数计算，如果总数不能被字母数量整除，那也肯定无法达到条件，返回false
    if (sum % countNow != 0) {
        return false;
    }
    sum /= countNow;
    for (int c : maps) {
        // 循环遍历maps，
        // if (c != 0 && c != sum && (c - 1) != sum)， 说明这个字母频次不为0，并且不是平均数，那么无法达到条件，返回false
        if (c != 0 && c != sum && (c - 1) != sum) {
            return false;
        }
    }
    return true;
}

// 可以攻击国王的皇后
/*给定一个由整数坐标组成的数组 queens ，表示黑皇后的位置；以及一对坐标 king
，表示白国王的位置，返回所有可以攻击国王的皇后的坐标(任意顺序)。



示例 1：



输入：queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
输出：[[0,1],[1,0],[3,3]]
解释：
[0,1] 的皇后可以攻击到国王，因为他们在同一行上。
[1,0] 的皇后可以攻击到国王，因为他们在同一列上。
[3,3] 的皇后可以攻击到国王，因为他们在同一条对角线上。
[0,4] 的皇后无法攻击到国王，因为她被位于 [0,1] 的皇后挡住了。
[4,0] 的皇后无法攻击到国王，因为她被位于 [1,0] 的皇后挡住了。
[2,4] 的皇后无法攻击到国王，因为她和国王不在同一行/列/对角线上。
示例 2：



输入：queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
输出：[[2,2],[3,4],[4,4]]
示例 3：



输入：queens =
[[5,6],[7,7],[2,1],[0,7],[1,6],[5,1],[3,7],[0,3],[4,0],[1,2],[6,3],[5,0],[0,4],[2,2],[1,1],[6,4],[5,4],[0,0],[2,6],[4,5],[5,2],[1,4],[7,5],[2,3],[0,5],[4,2],[1,0],[2,7],[0,1],[4,6],[6,1],[0,6],[4,3],[1,7]],
king = [3,4] 输出：[[2,3],[1,4],[1,6],[3,7],[4,3],[5,4],[4,5]]


提示：

1 <= queens.length <= 63
queens[i].length == 2
0 <= queens[i][j] < 8
king.length == 2
0 <= king[0], king[1] < 8
一个棋盘格上最多只能放置一枚棋子。*/
vector<vector<int>> queensAttacktheKing(const vector<vector<int>> &queens, const vector<int> &king) {
    // 模拟
    /*
    用一个二位数组distanceWithDir[3][3]，模拟维护国王八个方向上的最近的皇后的距离（设国王位置为[1][1]），并初始化距离为8（棋盘为8*8）
    遍历所有皇后：
    1.if (queen[0] == king[0])，皇后和国王在同一个x轴上，那么其之间的距离为abs(queen[1] - king[1])，
    皇后在国王的y的方向为((queen[1] - king[1]) / distance) + 1
    2.if (queen[1] == king[1])，皇后和国王在同一个y轴上，那么其之间的距离为abs(queen[0] - king[0])，
    皇后在国王的x的方向为((queen[0] - king[0]) / distance) + 1
    3.if (abs(queen[0] - king[0]) == abs(queen[1] - king[1]))，说明皇后和国王在同一个斜线上，其之间的距离为abs(queen[0] -
    king[0])， 皇后在国王x的方向为((queen[0] - king[0]) / distance) + 1，y的方向为((queen[1] - king[1]) / distance) + 1
    4.判断完后，再判断if (distance != -1 && distance < distanceWithDir[dirX][dirY])，distance !=
    -1表示皇后可以再同一条直线或斜线上 distance < distanceWithDir[dirX][dirY]表示皇后离国王更近，那么可以设置值。更新最近的距离。
    5.最后再遍历方向数组distanceWithDir，if (distanceWithDir[r][c] !=
    8)，说明此方向上有皇后可以攻击到国王，根据距离和方向判断出皇后位置， {king[0] + (r - 1) * distanceWithDir[r][c], king[1] + (c
    - 1) * distanceWithDir[r][c]}，并更新至结果数组中。
    */
    vector<vector<int>> res;
    int distanceWithDir[3][3] = {{8, 8, 8}, {8, 8, 8}, {8, 8, 8}};

    for (vector<int> queen : queens) {
        int distance = -1;
        int dirX = -1;
        int dirY = -1;
        if (queen[0] == king[0]) {
            distance = abs(queen[1] - king[1]);
            dirX = 1;
            dirY = ((queen[1] - king[1]) / distance) + 1;
        } else if (queen[1] == king[1]) {
            distance = abs(queen[0] - king[0]);
            dirY = 1;
            dirX = ((queen[0] - king[0]) / distance) + 1;
        } else if (abs(queen[0] - king[0]) == abs(queen[1] - king[1])) {
            distance = abs(queen[0] - king[0]);
            dirX = ((queen[0] - king[0]) / distance) + 1;
            dirY = ((queen[1] - king[1]) / distance) + 1;
        }

        if (distance != -1 && distance < distanceWithDir[dirX][dirY]) {
            distanceWithDir[dirX][dirY] = distance;
        }
    }

    for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 3; c++) {
            if (distanceWithDir[r][c] != 8) {
                res.push_back({king[0] + (r - 1) * distanceWithDir[r][c], king[1] + (c - 1) * distanceWithDir[r][c]});
            }
        }
    }

    return res;
}

// 宝石补给
/*欢迎各位勇者来到力扣新手村，在开始试炼之前，请各位勇者先进行「宝石补给」。

每位勇者初始都拥有一些能量宝石， gem[i] 表示第 i 位勇者的宝石数量。现在这些勇者们进行了一系列的赠送，operations[j] = [x, y]
表示在第 j 次的赠送中 第 x 位勇者将自己一半的宝石（需向下取整）赠送给第 y 位勇者。

在完成所有的赠送后，请找到拥有最多宝石的勇者和拥有最少宝石的勇者，并返回他们二者的宝石数量之差。

注意：

赠送将按顺序逐步进行。
示例 1：

输入：gem = [3,1,2], operations = [[0,2],[2,1],[2,0]]

输出：2

解释： 第 1 次操作，勇者 0 将一半的宝石赠送给勇者 2， gem = [2,1,3] 第 2 次操作，勇者 2 将一半的宝石赠送给勇者 1， gem = [2,2,2]
第 3 次操作，勇者 2 将一半的宝石赠送给勇者 0， gem = [3,2,1] 返回 3 - 1 = 2

示例 2：

输入：gem = [100,0,50,100], operations = [[0,2],[0,1],[3,0],[3,0]]

输出：75

解释： 第 1 次操作，勇者 0 将一半的宝石赠送给勇者 2， gem = [50,0,100,100] 第 2 次操作，勇者 0 将一半的宝石赠送给勇者 1， gem =
[25,25,100,100] 第 3 次操作，勇者 3 将一半的宝石赠送给勇者 0， gem = [75,25,100,50] 第 4 次操作，勇者 3 将一半的宝石赠送给勇者 0，
gem = [100,25,100,25] 返回 100 - 25 = 75

示例 3：

输入：gem = [0,0,0,0], operations = [[1,2],[3,1],[1,2]]

输出：0

提示：

2 <= gem.length <= 10^3
0 <= gem[i] <= 10^3
0 <= operations.length <= 10^4
operations[i].length == 2
0 <= operations[i][0], operations[i][1] < gem.length*/
int giveGem(vector<int> &&gem, vector<vector<int>> &&operations) {
    // 模拟
    // 按题意，模拟每步操作
    // 最后返回gem数组最大值和最小值之差
    for (auto op : operations) {
        int give = gem[op[0]] / 2;
        gem[op[0]] -= give;
        gem[op[1]] += give;
    }

    /*1.求数组的最大值或最小值
    1）vector容器
    例 vector<int> v;
    最大值：int maxValue = *max_element(v.begin(),v.end());
    最小值：int minValue = *min_element(v.begin(),v.end());
    2）普通数组
    例 a[]={1,2,3,4,5,6};
    最大值：int maxValue = *max_element(a,a+6);
    最小值：int minValue = *min_element(a,a+6);

    2.求数组最大值最小值对应的下标
    1）vector容器
    例 vector<int> v;
    最大值下标：int maxPosition = max_element(v.begin(),v.end()) - v.begin();
    最小值下标：int minPosition = min_element(v.begin(),v.end()) - v.begin();
    2）普通数组
    例 a[]={1,2,3,4,5,6};
    最大值下标：int maxPosition = max_element(a,a+6) - a;
    最小值下标：int minPosition = min_element(a,a+6) - a;*/
    return *max_element(gem.begin(), gem.end()) - *min_element(gem.begin(), gem.end());
}

// 计算 K 置位下标对应元素的和
/*给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。

请你用整数形式返回 nums 中的特定元素之 和 ，这些特定元素满足：其对应下标的二进制表示中恰存在 k 个置位。

整数的二进制表示中的 1 就是这个整数的 置位 。

例如，21 的二进制表示为 10101 ，其中有 3 个置位。



示例 1：

输入：nums = [5,10,1,5,2], k = 1
输出：13
解释：下标的二进制表示是：
0 = 0002
1 = 0012
2 = 0102
3 = 0112
4 = 1002
下标 1、2 和 4 在其二进制表示中都存在 k = 1 个置位。
因此，答案为 nums[1] + nums[2] + nums[4] = 13 。
示例 2：

输入：nums = [4,3,2,1], k = 2
输出：1
解释：下标的二进制表示是：
0 = 002
1 = 012
2 = 102
3 = 112
只有下标 3 的二进制表示中存在 k = 2 个置位。
因此，答案为 nums[3] = 1 。


提示：

1 <= nums.length <= 1000
1 <= nums[i] <= 105
0 <= k <= 10*/
/* int sumIndicesWithKSetBits(vector<int> &&nums, int k) {
    //todu:待优化，初始化k个1位比特的index，并且每次已左移的方式增加index，即可保持index里有k个置位，当index大于等于nums.size时，退出循环
    if (k == 0) {
        return nums[0];
    }
    int index = 0;
    for (int i = 0; i < k; i++) {
        index |= (1 << i);
    }
    if (index >= nums.size()) {
        return 0;
    }
    int res = nums[index];
    int maxIndex = k;
    while (true) {
        int moveIndex = maxIndex - 1;
        int minIndex = maxIndex - k;
        while (moveIndex >= minIndex) {
            index |= (1 << (moveIndex + 1));
            index ^= (1 << moveIndex);
            if (index >= nums.size()) {
                return res;
            }
            moveIndex--;
            res += nums[index];
        }
        maxIndex++;
    }
    return res;
} */
int countIndexBit(int index) {
    int count = 0;
    while (index > 0) {
        if ((index & 1) == 1) {
            count++;
        }
        index >>= 1;
    }
    return count;
}
int countIndexBit2(int index) {
    int count = 0;
    while (index > 0) {
        count++;
        index &= (index - 1);
    }
    return count;
}
int sumIndicesWithKSetBits(vector<int> &&nums, int k) {
    /*
    模拟
    遍历数组每个下标，并判断每个下标中有几个置位数，当置位数等于k时，将改下标数加入结果中
    */
    int res = 0;
    for (int i = 0; i < nums.size(); i++) {
        if (countIndexBit(i) == k) {
            res += nums[i];
        }
    }

    return res;
}

// 统计值等于子树平均值的节点数
/*给你一棵二叉树的根节点 root ，找出并返回满足要求的节点数，要求节点的值等于其 子树 中值的 平均值 。

注意：

n 个元素的平均值可以由 n 个元素 求和 然后再除以 n ，并 向下舍入 到最近的整数。
root 的 子树 由 root 和它的所有后代组成。


示例 1：


输入：root = [4,8,5,0,1,null,6]
输出：5
解释：
对值为 4 的节点：子树的平均值 (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4 。
对值为 5 的节点：子树的平均值 (5 + 6) / 2 = 11 / 2 = 5 。
对值为 0 的节点：子树的平均值 0 / 1 = 0 。
对值为 1 的节点：子树的平均值 1 / 1 = 1 。
对值为 6 的节点：子树的平均值 6 / 1 = 6 。
示例 2：


输入：root = [1]
输出：1
解释：对值为 1 的节点：子树的平均值 1 / 1 = 1。


提示：

树中节点数目在范围 [1, 1000] 内
0 <= Node.val <= 1000*/
int averageOfSubtree(TreeNode *root, int &sum, int &count) {
    if (root == nullptr) {
        return 0;
    }

    int LSum = 0, RSum = 0;
    int LCount = 0, RCount = 0;
    int left = averageOfSubtree(root->left, LSum, LCount);
    int right = averageOfSubtree(root->right, RSum, RCount);
    sum += LSum += RSum += root->val;
    count += LCount += RCount += 1;
    if ((sum / count) == root->val) {
        return left + right + 1;
    }
    return left + right;
}
int averageOfSubtree(TreeNode *root) {
    /*
    DFS，后序遍历
    使用dfs的后序遍历
    获得左子树中符合条件的节点数left，右子树中符合条件的节点数right。
    并且传入两个引用，记录子树的节点总和sum和子树的节点数量count，
    那么，后序遍历时，就需要记录左子树的LSum和LCount，以及右子树的RSum和RCount，
    再加上当前节点，整棵树的节点总和就为LSum+RSum+root->val，节点数量就为LCount+RCount+1，
    最后再判断平均数是否等于root->val，如果等于则返回left+right+1，否则返回left+right
    */
    int sum = 0, count = 0;
    return averageOfSubtree(root, sum, count);
}

// 将钱分给最多的儿童
/*给你一个整数 money ，表示你总共有的钱数（单位为美元）和另一个整数 children ，表示你要将钱分配给多少个儿童。

你需要按照如下规则分配：

所有的钱都必须被分配。
每个儿童至少获得 1 美元。
没有人获得 4 美元。
请你按照上述规则分配金钱，并返回 最多 有多少个儿童获得 恰好 8 美元。如果没有任何分配方案，返回 -1 。



示例 1：

输入：money = 20, children = 3
输出：1
解释：
最多获得 8 美元的儿童数为 1 。一种分配方案为：
- 给第一个儿童分配 8 美元。
- 给第二个儿童分配 9 美元。
- 给第三个儿童分配 3 美元。
没有分配方案能让获得 8 美元的儿童数超过 1 。
示例 2：

输入：money = 16, children = 2
输出：2
解释：每个儿童都可以获得 8 美元。


提示：

1 <= money <= 200
2 <= children <= 30*/
int distMoney(int money, int children) {
    // 贪心，模拟
    // 排除一切不可能的原因：
    /*
    1. `if (money < children)`：那么无法分配，返回-1
    2. `if (8 * children == money)`：正好每个小朋友都可以分配到8块
    3. `if (8 * children < money)`：说明有的是钱，那么只要children - 1个小朋友分配8块，最后一个小朋友拿下剩下的所有钱（羡慕~）
    4. 贪心，把硬币按每人8块分配，得出最多可以分配到几个小朋友res，以及最后还剩多少枚硬币left
        1. `if ((left == 4 && (children - res == 1)))`：还剩一个小朋友没拿到钱，并且硬币还剩4枚，那么根据题意，这个小朋友不能拿4枚硬币，那么从之前分配到8枚的小朋友中选一个，拿一部分给这个小朋友，那么最后结果就为res-1
        2. `if ((left + res) < children)`：说明剩余的硬币完全不够分剩下的小朋友，那么只能继续从之前分配到8枚硬币的小朋友中拿出一部分再给这些剩下的小朋友，结果是`res - (children - left - res + 6) / 7`
            - 后面中`children - left - res`表示还剩几个小朋友没拿到钱
            - `/ 7`除以7是因为每个小朋友至少得拿一枚，那么前面拿到8个硬币的小朋友每个人只能贡献出7枚
            - 最后这个`+6`代表向上取整（`x/y` 向上取整:`(x+y-1)/y`）
    5. 返回res
    */
    if (money < children) {
        return -1;
    }
    if (8 * children == money) {
        return children;
    }
    if (8 * children < money) {
        return children - 1;
    }

    int res = money / 8;
    int left = money % 8;
    if ((left == 4 && (children - res == 1))) {
        return res - 1;
    }
    if ((left + res) < children) {
        return res - (children - left - res + 6) / 7;
    }

    return res;
}

//不同的平均值数目
/*给你一个下标从 0 开始长度为 偶数 的整数数组 nums 。

只要 nums 不是 空数组，你就重复执行以下步骤：

找到 nums 中的最小值，并删除它。
找到 nums 中的最大值，并删除它。
计算删除两数的平均值。
两数 a 和 b 的 平均值 为 (a + b) / 2 。

比方说，2 和 3 的平均值是 (2 + 3) / 2 = 2.5 。
返回上述过程能得到的 不同 平均值的数目。

注意 ，如果最小值或者最大值有重复元素，可以删除任意一个。

 

示例 1：

输入：nums = [4,1,4,0,3,5]
输出：2
解释：
1. 删除 0 和 5 ，平均值是 (0 + 5) / 2 = 2.5 ，现在 nums = [4,1,4,3] 。
2. 删除 1 和 4 ，平均值是 (1 + 4) / 2 = 2.5 ，现在 nums = [4,3] 。
3. 删除 3 和 4 ，平均值是 (3 + 4) / 2 = 3.5 。
2.5 ，2.5 和 3.5 之中总共有 2 个不同的数，我们返回 2 。
示例 2：

输入：nums = [1,100]
输出：1
解释：
删除 1 和 100 后只有一个平均值，所以我们返回 1 。
 

提示：

2 <= nums.length <= 100
nums.length 是偶数。
0 <= nums[i] <= 100*/
int distinctAverages(vector<int> &nums) {
    //排序，哈希表，双指针
    //先对数组进行排序，使其可以方便的找到最大值和最小值
    //遍历数组，用一个set集合保存所有最大值和最小值相加的结果（因为平均数要计算小数点，所以不用除以2，直接判断相加的结果是否相同就行）
    //最后返回set的大小即可
    unordered_set<int> set;
    sort(nums.begin(), nums.end());
    int l = 0, r = nums.size() - 1;
    while (l < r) {
        set.insert(nums[l] + nums[r]);
        l++, r--;
    }
    return set.size();
}

//用队列实现栈
/*请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。

实现 MyStack 类：

void push(int x) 将元素 x 压入栈顶。
int pop() 移除并返回栈顶元素。
int top() 返回栈顶元素。
boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 

注意：

你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 

示例：

输入：
["MyStack", "push", "push", "top", "pop", "empty"]
[[], [1], [2], [], [], []]
输出：
[null, null, null, 2, 2, false]

解释：
MyStack myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.top(); // 返回 2
myStack.pop(); // 返回 2
myStack.empty(); // 返回 False
 

提示：

1 <= x <= 9
最多调用100 次 push、pop、top 和 empty
每次调用 pop 和 top 都保证栈不为空
 

进阶：你能否仅用一个队列来实现栈。*/
class MyStack {

    //一个队列实现一个栈
    //每次入队时，将当前队列中的所有元素出队，重新排在新入队的元素的后面
    //这样可以保证后入队的元素总是在最前面
    public:
    queue<int> stack;
    MyStack() {}

    void push(int x) {
        int size = stack.size();
        stack.push(x);
        for (int i = 0; i < size; i++) {
            stack.push(stack.front());
            stack.pop();
        }
    }

    int pop() {
        int top = stack.front();
        stack.pop();
        return top;
    }

    int top() { return stack.front(); }

    bool empty() { return stack.empty(); }
};

//递枕头
/*n 个人站成一排，按从 1 到 n 编号。

最初，排在队首的第一个人拿着一个枕头。每秒钟，拿着枕头的人会将枕头传递给队伍中的下一个人。一旦枕头到达队首或队尾，传递方向就会改变，队伍会继续沿相反方向传递枕头。

例如，当枕头到达第 n 个人时，TA 会将枕头传递给第 n - 1 个人，然后传递给第 n - 2 个人，依此类推。
给你两个正整数 n 和 time ，返回 time 秒后拿着枕头的人的编号。

 

示例 1：

输入：n = 4, time = 5
输出：2
解释：队伍中枕头的传递情况为：1 -> 2 -> 3 -> 4 -> 3 -> 2 。
5 秒后，枕头传递到第 2 个人手中。
示例 2：

输入：n = 3, time = 2
输出：3
解释：队伍中枕头的传递情况为：1 -> 2 -> 3 。
2 秒后，枕头传递到第 3 个人手中。
 

提示：

2 <= n <= 1000
1 <= time <= 1000*/
int passThePillow(int n, int time) {
    //数学
    //我们注意到，当有n个人时，传递n-1次，可以从一头传到另一头，(n-1)*2时，又回到第1个人手中
    //那么我们记once=n-1为一轮
    //也可以发现，当“轮数”time/once为奇数时，枕头往回传，为偶数时，枕头往前传
    //所以可以得出当前枕头的偏移量pos根据轮数奇偶性：
    //1.为奇数时：pos=once - time % once;
    //2.为偶数时：pos=time % once;
    //最后根据第一个人的偏移量可以得出枕头的位置为pos+1；
    int once = n - 1;
    int pos;
    if (((time / once) & 1) == 1) {
        pos = once - time % once;
    } else {
        pos = time % once;
    }
    return pos + 1;
}

//字典序最小回文串
/*给你一个由 小写英文字母 组成的字符串 s ，你可以对其执行一些操作。在一步操作中，你可以用其他小写英文字母 替换  s 中的一个字符。

请你执行 尽可能少的操作 ，使 s 变成一个 回文串 。如果执行 最少 操作次数的方案不止一种，则只需选取 字典序最小 的方案。

对于两个长度相同的字符串 a 和 b ，在 a 和 b 出现不同的第一个位置，如果该位置上 a 中对应字母比 b 中对应字母在字母表中出现顺序更早，则认为 a 的字典序比 b 的字典序要小。

返回最终的回文字符串。

 

示例 1：

输入：s = "egcfe"
输出："efcfe"
解释：将 "egcfe" 变成回文字符串的最小操作次数为 1 ，修改 1 次得到的字典序最小回文字符串是 "efcfe"，只需将 'g' 改为 'f' 。
示例 2：

输入：s = "abcd"
输出："abba"
解释：将 "abcd" 变成回文字符串的最小操作次数为 2 ，修改 2 次得到的字典序最小回文字符串是 "abba" 。
示例 3：

输入：s = "seven"
输出："neven"
解释：将 "seven" 变成回文字符串的最小操作次数为 1 ，修改 1 次得到的字典序最小回文字符串是 "neven" 。
 

提示：

1 <= s.length <= 1000
s 仅由小写英文字母组成*/
string makeSmallestPalindrome(string s) {
    //双指针
    //用首尾双指针判断字符是否相同，若相同，则不必更改，直接将字符加入
    //若不同，则加入字符序相对小的那个字符
    int l = 0, r = s.size() - 1;
    //c++字符串
    string res(s.size(), ' ');

    //C风格的字符串，字符串实际上是使用 null 字符 \0 终止的一维字符数组。因此，一个以 null 结尾的字符串，包含了组成字符串的字符。
    //结尾必须加入'\0'，否则无法判断字符串何时终止，题目将报错。
    //尽量不要使用C风格的字符串：
    //1.安全性：容易导致缓冲区溢出
    //2.方便性
    //3.兼容性和可维护性
    //4.功能性
    // char res[s.size() + 1];
    // res[r + 1] = '\0';

    while (l <= r) {
        if (s[l] == s[r]) {
            res[l] = res[r] = s[l];
        } else if (s[l] < s[r]) {
            res[l] = res[r] = s[l];
        } else {
            res[l] = res[r] = s[r];
        }
        l++, r--;
    }
    return res;
}

//最小公共值
/*给你两个整数数组 nums1 和 nums2 ，它们已经按非降序排序，请你返回两个数组的 最小公共整数 。如果两个数组 nums1 和 nums2 没有公共整数，请你返回 -1 。

如果一个整数在两个数组中都 至少出现一次 ，那么这个整数是数组 nums1 和 nums2 公共 的。

 

示例 1：

输入：nums1 = [1,2,3], nums2 = [2,4]
输出：2
解释：两个数组的最小公共元素是 2 ，所以我们返回 2 。
示例 2：

输入：nums1 = [1,2,3,6], nums2 = [2,3,4,5]
输出：2
解释：两个数组中的公共元素是 2 和 3 ，2 是较小值，所以返回 2 。
 

提示：

1 <= nums1.length, nums2.length <= 105
1 <= nums1[i], nums2[j] <= 109
nums1 和 nums2 都是 非降序 的。*/
int getCommon(vector<int> &nums1, vector<int> &nums2) {
    //双指针
    //因为数组是有序的，所以查找最小公共值时，可以使用双指针，从数组最低位依次判断
    //如果两个值相同，则返回该值
    //否则值小的数组对应的指针右移一位
    //如果循环结束，没找到相同的值，则返回-1
    int i1 = 0, i2 = 0;
    while (i1 < nums1.size() && i2 < nums2.size()) {
        if (nums1[i1] == nums2[i2]) {
            return nums1[i1];
        }
        if (nums1[i1] > nums2[i2]) {
            i2++;
        } else {
            i1++;
        }
    }
    return -1;
}

//有序数组中出现次数超过25%的元素
/*给你一个非递减的 有序 整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。

请你找到并返回这个整数

 

示例：

输入：arr = [1,2,2,6,6,6,6,7,10]
输出：6
 

提示：

1 <= arr.length <= 10^4
0 <= arr[i] <= 10^5*/
int findSpecialInteger(const vector<int> &arr) {
    //贪心，抽屉原理
    //设int step = arr.size() / 4;
    //因为答案唯一，所以遍历时，每次比较arr[i] 和 arr[i - step]，遇到相同时即可直接返回。
    int res = arr[0];
    int step = arr.size() / 4;

    for (int i = step; i < arr.size(); i++) {
        if (arr[i] == arr[i - step]) {
            res = arr[i];
            break;
        }
    }

    return res;
}

//判定字符是否唯一
/*实现一个算法，确定一个字符串 s 的所有字符是否全都不同。

示例 1：

输入: s = "leetcode"
输出: false 
示例 2：

输入: s = "abc"
输出: true
限制：

0 <= len(s) <= 100 
s[i]仅包含小写字母
如果你不使用额外的数据结构，会很加分。*/
bool isUnique(string astr) {
    //哈希表
    //用一个集合记录每个字符是否出现过，如果出现过则返回false
    //因为只包含小写字母，所以也可以用一个int，按每一位来表示每一个字符。
    bool s[26];
    for (char c : astr) {
        if (s[c - 'a']) {
            return false;
        }
        s[c - 'a'] = true;
    }
    return true;
}

//二叉树的直径
/*给你一棵二叉树的根节点，返回该树的 直径 。

二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。

两节点之间路径的 长度 由它们之间边数表示。

 

示例 1：


输入：root = [1,2,3,4,5]
输出：3
解释：3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度。
示例 2：

输入：root = [1,2]
输出：1
 

提示：

树中节点数目在范围 [1, 104] 内
-100 <= Node.val <= 100*/
int lengthOfBinaryTree(TreeNode *root, int &maxDiameter) {
    if (root == nullptr) {
        // 访问到空节点了，返回0
        return 0;
    }
    //左子树的最大臂长（深度）
    int leftMax = lengthOfBinaryTree(root->left, maxDiameter);
    //右子树的最大臂长（深度）
    int rightMax = lengthOfBinaryTree(root->right, maxDiameter);
    //维护并更新所有情况下的最大直径
    maxDiameter = max(maxDiameter, leftMax + rightMax);
    //返回以root为根节点的情况下的最大臂长（深度）
    return max(leftMax, rightMax) + 1;
}
int diameterOfBinaryTree(TreeNode *root) {
    //深度优先搜索（后序遍历）
    //我们可以设置一个函数int lengthOfBinaryTree(TreeNode *root, int &maxDiameter)
    //表示以root为根节点时，最大的臂长（最大深度）
    //那么以root为根节点时，最大直径就为左子树的最大臂长+右子树的最大臂长：leftMax+rightMax
    //并用一个整数maxDiameter实时维护所有情况下的最大直径，并和当前的最大直径相比较并更新
    //而且此时的最大臂长就为max(leftMax, rightMax) + 1
    int maxDiameter = 0;
    lengthOfBinaryTree(root, maxDiameter);
    return maxDiameter;
}

//颜色分类
/*给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

必须在不使用库内置的 sort 函数的情况下解决这个问题。

 

示例 1：

输入：nums = [2,0,2,1,1,0]
输出：[0,0,1,1,2,2]
示例 2：

输入：nums = [2,0,1]
输出：[0,1,2]
 

提示：

n == nums.length
1 <= n <= 300
nums[i] 为 0、1 或 2
 

进阶：

你能想出一个仅使用常数空间的一趟扫描算法吗？*/
void swap(vector<int> &nums, int l, int r) {
    if (l == r) {
        return;
    }
    nums[l] ^= nums[r];
    nums[r] ^= nums[l];
    nums[l] ^= nums[r];
}
void sortColors(vector<int> &nums) {
    //双指针
    //使用双指针r,b指向红色和蓝色的位置
    //1.首先循环判断，nums[i]是否为红色/蓝色，如果是，则该位置不用交换，r++/b--。
    //2.然后进入下一轮循环，进行后续位置交换，再用一个指针，指向当前遍历的位置
    //3.如果nums[i]=2，则跟b进行交互，并且b--；如果nums[i]=0，则跟r进行交互，r++。即可保证红色在头部，蓝色在尾部
    //4.之后在用（1）的方式将红色和蓝色指针进行移动。
    //5.令i = max(r, i + 1)，当第四步红色指针有移动，那么接下来几个数字说明都是红色，可以跳过，否则i+1；
    int r = 0, b = nums.size() - 1, w;
    while (r < b) {
        if (nums[r] == 0) {
            r++;
        } else {
            break;
        }
    }
    while (r < b) {
        if (nums[b] == 2) {
            b--;
        } else {
            break;
        }
    }
    w = r;
    if (w == b) {
        return;
    }
    do {
        if (nums[w] == 2) {
            swap(nums, w, b);
            b--;
        }
        if (nums[w] == 0) {
            swap(nums, w, r);
            r++;
        }
        while (r <= b) {
            if (nums[r] == 0) {
                r++;
            } else {
                break;
            }
        }
        while (r <= b) {
            if (nums[b] == 2) {
                b--;
            } else {
                break;
            }
        }
        w = max(r, w + 1);
    } while (w <= b);
}

//在区间范围内统计奇数数目
/*给你两个非负整数 low 和 high 。请你返回 low 和 high 之间（包括二者）奇数的数目。

 

示例 1：

输入：low = 3, high = 7
输出：3
解释：3 到 7 之间奇数数字为 [3,5,7] 。
示例 2：

输入：low = 8, high = 10
输出：1
解释：8 到 10 之间奇数数字为 [9] 。
 

提示：

0 <= low <= high <= 10^9*/
int countOdds(int low, int high) {
    //设h和l的差为diff
    //可以发现
    //当diff为奇数时，res=(diff+1)/2;
    //当diff为偶数时，并且low为奇数时，res=(diff+1)/2;low为偶数时，res=diff/2;
    //那么可以统一将res=(diff >> 1) + (diff & 1 || low & 1);表示先计算diff/2;，再判断diff和low，只要有一个为奇数，则结果再加1.
    int diff = high - low;
    return (diff >> 1) + (diff & 1 || low & 1);
}

//最小和分割
/*给你一个正整数 num ，请你将它分割成两个非负整数 num1 和 num2 ，满足：

num1 和 num2 直接连起来，得到 num 各数位的一个排列。
换句话说，num1 和 num2 中所有数字出现的次数之和等于 num 中所有数字出现的次数。
num1 和 num2 可以包含前导 0 。
请你返回 num1 和 num2 可以得到的和的 最小 值。

注意：

num 保证没有前导 0 。
num1 和 num2 中数位顺序可以与 num 中数位顺序不同。
 

示例 1：

输入：num = 4325
输出：59
解释：我们可以将 4325 分割成 num1 = 24 和 num2 = 35 ，和为 59 ，59 是最小和。
示例 2：

输入：num = 687
输出：75
解释：我们可以将 687 分割成 num1 = 68 和 num2 = 7 ，和为最优值 75 。
 

提示：

10 <= num <= 109*/
int splitNum(int num) {
    //贪心
    //先将num中的每个数字提取出来到数组arr中，并进行排序
    //令num1中的每个元素依次为数组偶数下标
    //令num2中的每个元素依次为数组奇数下标
    //返回num1+nums2
    vector<int> arr;
    while (num > 0) {
        arr.push_back(num % 10);
        num = num / 10;
    }
    sort(arr.begin(), arr.end());
    int num1 = 0, num2 = 0;
    for (int i = 0; i < arr.size(); i += 2) {
        num1 = num1 * 10 + arr[i];
    }
    for (int i = 1; i < arr.size(); i += 2) {
        num2 = num2 * 10 + arr[i];
    }
    return num1 + num2;
}

//字符串的最大公因子
/*对于字符串 s 和 t，只有在 s = t + ... + t（t 自身连接 1 次或多次）时，我们才认定 “t 能除尽 s”。

给定两个字符串 str1 和 str2 。返回 最长字符串 x，要求满足 x 能除尽 str1 且 x 能除尽 str2 。

 

示例 1：

输入：str1 = "ABCABC", str2 = "ABC"
输出："ABC"
示例 2：

输入：str1 = "ABABAB", str2 = "ABAB"
输出："AB"
示例 3：

输入：str1 = "LEET", str2 = "CODE"
输出：""
 

提示：

1 <= str1.length, str2.length <= 1000
str1 和 str2 由大写英文字母组成*/
int gcd(int a, int b) {
    int temp = a % b;
    while (temp != 0) {
        a = b;
        b = temp;
        temp = a % b;
    }
    return b;
}

bool check(string s, string t) {
    if (s == t) {
        return true;
    }
    for (int i = 0; i < s.size(); i += t.size()) {
        for (int j = 0; j < t.size(); j++) {
            if (s[i + j] != t[j]) {
                return false;
            }
        }
    }
    return true;
}

string gcdOfStrings(string str1, string str2) {
    //最大公约数（辗转相除法）
    //首先求出str1和str2的长度的最大公约数maxGcd
    //然后判断根据此公约数的长度进行分割的字符串t，是否分别能除尽str1和str2即可，
    if (str1.size() < str2.size()) {
        return gcdOfStrings(str2, str1);
    }
    int maxGcd = gcd(str1.size(), str2.size());
    if (check(str1, str2.substr(0, maxGcd)) && check(str2, str2.substr(0, maxGcd))) {
        return str2.substr(0, maxGcd);
    }

    //不需要后续的判断
    //因为如果较小的公约数gcd分割的字符串t1可以除尽str1和str2，那么最大公约数maxGcd分割的字符串t必然也可以除尽str1和str2
    /* maxGcd /= 2;
    while (maxGcd > 1) {
        if (str1.size() % maxGcd == 0 && str2.size() % maxGcd == 0 && check(str1, str2.substr(0, maxGcd)) &&
            check(str2, str2.substr(0, maxGcd))) {
            return str2.substr(0, maxGcd);
        }
        maxGcd--;
    } */
    return "";
}

//找出两数组的不同
/*给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，请你返回一个长度为 2 的列表 answer ，其中：

answer[0] 是 nums1 中所有 不 存在于 nums2 中的 不同 整数组成的列表。
answer[1] 是 nums2 中所有 不 存在于 nums1 中的 不同 整数组成的列表。
注意：列表中的整数可以按 任意 顺序返回。

 

示例 1：

输入：nums1 = [1,2,3], nums2 = [2,4,6]
输出：[[1,3],[4,6]]
解释：
对于 nums1 ，nums1[1] = 2 出现在 nums2 中下标 0 处，然而 nums1[0] = 1 和 nums1[2] = 3 没有出现在 nums2 中。因此，answer[0] = [1,3]。
对于 nums2 ，nums2[0] = 2 出现在 nums1 中下标 1 处，然而 nums2[1] = 4 和 nums2[2] = 6 没有出现在 nums2 中。因此，answer[1] = [4,6]。
示例 2：

输入：nums1 = [1,2,3,3], nums2 = [1,1,2,2]
输出：[[3],[]]
解释：
对于 nums1 ，nums1[2] 和 nums1[3] 没有出现在 nums2 中。由于 nums1[2] == nums1[3] ，二者的值只需要在 answer[0] 中出现一次，故 answer[0] = [3]。
nums2 中的每个整数都在 nums1 中出现，因此，answer[1] = [] 。 
 

提示：

1 <= nums1.length, nums2.length <= 1000
-1000 <= nums1[i], nums2[i] <= 1000*/
vector<vector<int>> findDifference(const vector<int> &nums1, vector<int> &&nums2) {
    //哈希表
    //将num1和num2的元素分别加入集合set1和set2中
    //再分别遍历set1和set2，并判断set1中的元素是否存在于set2中，以及set2中的元素是否存在于set1中
    //若不存在，则将元素加入结果中
    unordered_set<int> set1;
    unordered_set<int> set2;
    for (int n : nums1) {
        set1.insert(n);
    }
    for (int n : nums2) {
        set2.insert(n);
    }
    vector<vector<int>> res(2);
    for (int n : set1) {
        if (set2.find(n) == set2.end()) {
            res[0].push_back(n);
        }
    }
    for (int n : set2) {
        if (set1.find(n) == set1.end()) {
            res[1].push_back(n);
        }
    }

    return res;
}

//找出数组的串联值
/*给你一个下标从 0 开始的整数数组 nums 。

现定义两个数字的 串联 是由这两个数值串联起来形成的新数字。

例如，15 和 49 的串联是 1549 。
nums 的 串联值 最初等于 0 。执行下述操作直到 nums 变为空：

如果 nums 中存在不止一个数字，分别选中 nums 中的第一个元素和最后一个元素，将二者串联得到的值加到 nums 的 串联值 上，然后从 nums 中删除第一个和最后一个元素。
如果仅存在一个元素，则将该元素的值加到 nums 的串联值上，然后删除这个元素。
返回执行完所有操作后 nums 的串联值。

 

示例 1：

输入：nums = [7,52,2,4]
输出：596
解释：在执行任一步操作前，nums 为 [7,52,2,4] ，串联值为 0 。
 - 在第一步操作中：
我们选中第一个元素 7 和最后一个元素 4 。
二者的串联是 74 ，将其加到串联值上，所以串联值等于 74 。
接着我们从 nums 中移除这两个元素，所以 nums 变为 [52,2] 。
 - 在第二步操作中： 
我们选中第一个元素 52 和最后一个元素 2 。 
二者的串联是 522 ，将其加到串联值上，所以串联值等于 596 。
接着我们从 nums 中移除这两个元素，所以 nums 变为空。
由于串联值等于 596 ，所以答案就是 596 。
示例 2：

输入：nums = [5,14,13,8,12]
输出：673
解释：在执行任一步操作前，nums 为 [5,14,13,8,12] ，串联值为 0 。 
- 在第一步操作中： 
我们选中第一个元素 5 和最后一个元素 12 。 
二者的串联是 512 ，将其加到串联值上，所以串联值等于 512 。 
接着我们从 nums 中移除这两个元素，所以 nums 变为 [14,13,8] 。
- 在第二步操作中：
我们选中第一个元素 14 和最后一个元素 8 。
二者的串联是 148 ，将其加到串联值上，所以串联值等于 660 。
接着我们从 nums 中移除这两个元素，所以 nums 变为 [13] 。 
- 在第三步操作中：
nums 只有一个元素，所以我们选中 13 并将其加到串联值上，所以串联值等于 673 。
接着我们从 nums 中移除这个元素，所以 nums 变为空。 
由于串联值等于 673 ，所以答案就是 673 。
 

提示：

1 <= nums.length <= 1000
1 <= nums[i] <= 104*/
long long findTheArrayConcVal(vector<int> &nums) {
    //双指针，模拟
    //使用首尾双指针，来指向元素首尾位置
    //使用to_string函数，将int转换成string
    //使用stoi将string转换成int（也可使用atoi，但这是C标准里的函数，所以需要传入C的字符串，对于string的参数需要用c_str()转换成C字符串）
    long long res = 0;

    int l = 0, r = nums.size() - 1;

    while (l < r) {
        res += stoi((to_string(nums[l]) + to_string(nums[r])));
        l++, r--;
    }

    if (l == r) {
        res += stoi(to_string(nums[l]));
    }

    return res;
}

//分类求和并作差
/*给你两个正整数 n 和 m 。

现定义两个整数 num1 和 num2 ，如下所示：

num1：范围 [1, n] 内所有 无法被 m 整除 的整数之和。
num2：范围 [1, n] 内所有 能够被 m 整除 的整数之和。
返回整数 num1 - num2 。

 

示例 1：

输入：n = 10, m = 3
输出：19
解释：在这个示例中：
- 范围 [1, 10] 内无法被 3 整除的整数为 [1,2,4,5,7,8,10] ，num1 = 这些整数之和 = 37 。
- 范围 [1, 10] 内能够被 3 整除的整数为 [3,6,9] ，num2 = 这些整数之和 = 18 。
返回 37 - 18 = 19 作为答案。
示例 2：

输入：n = 5, m = 6
输出：15
解释：在这个示例中：
- 范围 [1, 5] 内无法被 6 整除的整数为 [1,2,3,4,5] ，num1 = 这些整数之和 =  15 。
- 范围 [1, 5] 内能够被 6 整除的整数为 [] ，num2 = 这些整数之和 = 0 。
返回 15 - 0 = 15 作为答案。
示例 3：

输入：n = 5, m = 1
输出：-15
解释：在这个示例中：
- 范围 [1, 5] 内无法被 1 整除的整数为 [] ，num1 = 这些整数之和 = 0 。 
- 范围 [1, 5] 内能够被 1 整除的整数为 [1,2,3,4,5] ，num2 = 这些整数之和 = 15 。
返回 0 - 15 = -15 作为答案。
 

提示：

1 <= n, m <= 1000*/
int differenceOfSums(int n, int m) {
    //模拟
    //按条件循环模拟
    int res = 0;
    for (int i = 1; i <= n; i++) {
        if (i % m != 0) {
            res += i;
        } else {
            res -= i;
        }
    }
    for (int i = 1; i <= n; i++) {
        if (i % m == 0) {
        }
    }
    return res;

    //数学
    //根据等差数列求和公式
    //只要求出总和sum，以及能被m整除的数sum1，即可得到num1-num2的值
    return n * (n + 1) / 2 - n / m * (n / m + 1) * m;
}

//只出现一次的数字 III
/*给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。

你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。

 

示例 1：

输入：nums = [1,2,1,3,2,5]
输出：[3,5]
解释：[5, 3] 也是有效的答案。
示例 2：

输入：nums = [-1,0]
输出：[-1,0]
示例 3：

输入：nums = [0,1]
输出：[1,0]
 

提示：

2 <= nums.length <= 3 * 104
-231 <= nums[i] <= 231 - 1
除两个只出现一次的整数外，nums 中的其他数字都出现两次*/
vector<int> singleNumber(vector<int> &nums) {
    //哈希表
    //用set保存元素
    //遍历数组。如果set没有元素，则添加进集合中，如果set存在元素，则移除
    //最后set剩余的两个元素就是只出现一次的两个元素
    //因为题目要求只能使用常量的空间，所以此方法不可行（虽然通过了）。
    unordered_set<int> set;
    for (int n : nums) {
        if (set.count(n) == 0) {
            set.insert(n);
        } else {
            set.erase(n);
        }
    }
    vector<int> res{set.begin(), set.end()};
    return res;
}

//故障键盘
/*你的笔记本键盘存在故障，每当你在上面输入字符 'i' 时，它会反转你所写的字符串。而输入其他字符则可以正常工作。

给你一个下标从 0 开始的字符串 s ，请你用故障键盘依次输入每个字符。

返回最终笔记本屏幕上输出的字符串。

 

示例 1：

输入：s = "string"
输出："rtsng"
解释：
输入第 1 个字符后，屏幕上的文本是："s" 。
输入第 2 个字符后，屏幕上的文本是："st" 。
输入第 3 个字符后，屏幕上的文本是："str" 。
因为第 4 个字符是 'i' ，屏幕上的文本被反转，变成 "rts" 。
输入第 5 个字符后，屏幕上的文本是："rtsn" 。
输入第 6 个字符后，屏幕上的文本是： "rtsng" 。
因此，返回 "rtsng" 。
示例 2：

输入：s = "poiinter"
输出："ponter"
解释：
输入第 1 个字符后，屏幕上的文本是："p" 。
输入第 2 个字符后，屏幕上的文本是："po" 。
因为第 3 个字符是 'i' ，屏幕上的文本被反转，变成 "op" 。
因为第 4 个字符是 'i' ，屏幕上的文本被反转，变成 "po" 。
输入第 5 个字符后，屏幕上的文本是："pon" 。
输入第 6 个字符后，屏幕上的文本是："pont" 。
输入第 7 个字符后，屏幕上的文本是："ponte" 。
输入第 8 个字符后，屏幕上的文本是："ponter" 。
因此，返回 "ponter" 。
 

提示：

1 <= s.length <= 100
s 由小写英文字母组成
s[0] != 'i'*/
string finalString(string s) {
    //模拟
    //按题意遍历字符串
    //当遇到字符'i'时，翻转字符串（reverse(res.begin(), res.end())函数）；否则拼接字符；
    string res;
    for (char c : s) {
        if (c == 'i') {
            reverse(res.begin(), res.end());
        } else {
            res += c;
        }
    }
    return res;
}

//同积元组
/*给你一个由 不同 正整数组成的数组 nums ，请你返回满足 a * b = c * d 的元组 (a, b, c, d) 的数量。其中 a、b、c 和 d 都是 nums 中的元素，且 a != b != c != d 。

 

示例 1：

输入：nums = [2,3,4,6]
输出：8
解释：存在 8 个满足题意的元组：
(2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
(3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)
示例 2：

输入：nums = [1,2,4,5,10]
输出：16
解释：存在 16 个满足题意的元组：
(1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
(2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
(2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,5,4)
(4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)
 

提示：

1 <= nums.length <= 1000
1 <= nums[i] <= 104
nums 中的所有元素 互不相同*/
int tupleSameProduct(vector<int> &nums) {
    unordered_map<int, int> counter;

    for (int i = 0; i < nums.size(); i++) {
        for (int j = i + 1; j < nums.size(); j++) {
            // counter[nums[i] * nums[j]] = counter[nums[i] * nums[j]] + 1;
            counter[nums[i] * nums[j]]++;
        }
    }

    int res = 0;
    for (auto &entry : counter) {
        if (entry.second > 1) {
            res += ((entry.second - 1) * entry.second / 2) * 8;
        }
    }

    /* for (auto &[k, v] : counter) {
        res += v * (v - 1) * 4;
    } */

    return res;
}

//根据规则将箱子分类
/*给你四个整数 length ，width ，height 和 mass ，分别表示一个箱子的三个维度和质量，请你返回一个表示箱子 类别 的字符串。

如果满足以下条件，那么箱子是 "Bulky" 的：
箱子 至少有一个 维度大于等于 104 。
或者箱子的 体积 大于等于 109 。
如果箱子的质量大于等于 100 ，那么箱子是 "Heavy" 的。
如果箱子同时是 "Bulky" 和 "Heavy" ，那么返回类别为 "Both" 。
如果箱子既不是 "Bulky" ，也不是 "Heavy" ，那么返回类别为 "Neither" 。
如果箱子是 "Bulky" 但不是 "Heavy" ，那么返回类别为 "Bulky" 。
如果箱子是 "Heavy" 但不是 "Bulky" ，那么返回类别为 "Heavy" 。
注意，箱子的体积等于箱子的长度、宽度和高度的乘积。

 

示例 1：

输入：length = 1000, width = 35, height = 700, mass = 300
输出："Heavy"
解释：
箱子没有任何维度大于等于 104 。
体积为 24500000 <= 109 。所以不能归类为 "Bulky" 。
但是质量 >= 100 ，所以箱子是 "Heavy" 的。
由于箱子不是 "Bulky" 但是是 "Heavy" ，所以我们返回 "Heavy" 。
示例 2：

输入：length = 200, width = 50, height = 800, mass = 50
输出："Neither"
解释：
箱子没有任何维度大于等于 104 。
体积为 8 * 106 <= 109 。所以不能归类为 "Bulky" 。
质量小于 100 ，所以不能归类为 "Heavy" 。
由于不属于上述两者任何一类，所以我们返回 "Neither" 。
 

提示：

1 <= length, width, height <= 105
1 <= mass <= 103*/
string categorizeBox(int length, int width, int height, int mass) {
    //模拟
    //先计算出体积V,再按题意判断箱子是否Bulky以及是否Heavy
    //最后再根据是否Bulky以及是否Heavy给出结论
    long long v = (long long)length * width * height;
    // long long v = 1L * length * width * height;
    bool isBulky = false, isHeavy = false;

    if (length >= 10000 || width >= 10000 || height >= 10000 || v >= 1000000000) {
        isBulky = true;
    }
    if (mass >= 100) {
        isHeavy = true;
    }

    if (isBulky && isHeavy) {
        return "Both";
    }

    if (isBulky) {
        return "Bulky";
    }

    if (isHeavy) {
        return "Heavy";
    }

    return "Neither";
}

//转换数字的最少位翻转次数
/*一次 位翻转 定义为将数字 x 二进制中的一个位进行 翻转 操作，即将 0 变成 1 ，或者将 1 变成 0 。

比方说，x = 7 ，二进制表示为 111 ，我们可以选择任意一个位（包含没有显示的前导 0 ）并进行翻转。比方说我们可以翻转最右边一位得到 110 ，或者翻转右边起第二位得到 101 ，或者翻转右边起第五位（这一位是前导 0 ）得到 10111 等等。
给你两个整数 start 和 goal ，请你返回将 start 转变成 goal 的 最少位翻转 次数。

 

示例 1：

输入：start = 10, goal = 7
输出：3
解释：10 和 7 的二进制表示分别为 1010 和 0111 。我们可以通过 3 步将 10 转变成 7 ：
- 翻转右边起第一位得到：1010 -> 1011 。
- 翻转右边起第三位：1011 -> 1111 。
- 翻转右边起第四位：1111 -> 0111 。
我们无法在 3 步内将 10 转变成 7 。所以我们返回 3 。
示例 2：

输入：start = 3, goal = 4
输出：3
解释：3 和 4 的二进制表示分别为 011 和 100 。我们可以通过 3 步将 3 转变成 4 ：
- 翻转右边起第一位：011 -> 010 。
- 翻转右边起第二位：010 -> 000 。
- 翻转右边起第三位：000 -> 100 。
我们无法在 3 步内将 3 变成 4 。所以我们返回 3 。
 

提示：

0 <= start, goal <= 109*/
int minBitFlips(int start, int goal) {
    //位运算
    //转换次数，其实就是两个数中不同位的个数
    //那么可以先对两个数进行按位异或运算，统计结果中‘1’的个数就是不同位的个数
    int bit = start ^ goal;
    int count = 0;
    while (bit) {
        count++;
        bit &= (bit - 1);
    }
    return count;
}

//掷骰子等于目标和的方法数
/*这里有 n 个一样的骰子，每个骰子上都有 k 个面，分别标号为 1 到 k 。

给定三个整数 n ,  k 和 target ，返回可能的方式(从总共 kn 种方式中)滚动骰子的数量，使正面朝上的数字之和等于 target 。

答案可能很大，你需要对 109 + 7 取模 。

 

示例 1：

输入：n = 1, k = 6, target = 3
输出：1
解释：你扔一个有 6 个面的骰子。
得到 3 的和只有一种方法。
示例 2：

输入：n = 2, k = 6, target = 7
输出：6
解释：你扔两个骰子，每个骰子有 6 个面。
得到 7 的和有 6 种方法：1+6 2+5 3+4 4+3 5+2 6+1。
示例 3：

输入：n = 30, k = 30, target = 500
输出：222616187
解释：返回的结果必须是对 109 + 7 取模。
 

提示：

1 <= n, k <= 30
1 <= target <= 1000*/
int numRollsToTarget(int n, int k, int target) {
    //动态规划
    //设dp[n][t]：用n个骰子，投出数字之和为t的次数
    //dp[n][t]=sum(dp[n-1](t-1)+dp[n-1](t-2)+...+dp[n-1](t-k))(t-k>0)
    //由此，我们也可以发现，dp[n]只跟dp[n-1]即上一行的数据有关，那么可以使用滚动数组的思想，用一个一维数组来表示
    //又因为dp[n][t]是根据上一行中从dp[n-1](t-1)开始累加前面元素的值，那么我们在此遍历时使用倒叙的方式，使得先遍历并更改的值不会对后续遍历的值产生影响，可以省略创建临时数组的过程
    //1.首先，初始化dp，dp[1...min(k, target)]=1。也就是只有一个骰子时，最多只能投出t=min(k, target)，次数自然也是1.
    //2.1.根据n，对每一次增加骰子进行遍历
    //2.2.每一行中，倒叙计算每个dp[t]的值
    //2.3.dp[t]=sum(dp[t-1]+dp[t-2]+...+dp[t-k])(t-k>0)
    //3.计算sum时注意对结果按1000000007取模
    //4.更新dp[t]的值，并且注意，dp[1...t-k]=0,因为必须需要n个骰子，每个骰子最少是1，那么小于n的值必然是0，所以在每一行的遍历中，从target倒叙遍历，最小值也就是n。
    if (n * k < target) {
        return 0;
    }
    int dp[target + 1];
    for (int i = 1, l = min(k, target); i <= l; i++) {
        dp[i] = 1;
    }
    for (int i = min(k, target) + 1; i <= target; i++) {
        dp[i] = 0;
    }
    for (int i = 2; i <= n; i++) {
        for (int j = target; j >= i; j--) {
            int sum = 0;
            for (int c = 1, l = min(k, j - 1); c <= l; c++) {
                sum = (sum + dp[j - c]) % 1000000007;
            }
            dp[j] = sum;
        }
        for (int f = 1; f < i; f++) {
            dp[f] = 0;
        }
    }
    return dp[target];
}

//求一个整数的惩罚数
/*给你一个正整数 n ，请你返回 n 的 惩罚数 。

n 的 惩罚数 定义为所有满足以下条件 i 的数的平方和：

1 <= i <= n
i * i 的十进制表示的字符串可以分割成若干连续子字符串，且这些子字符串对应的整数值之和等于 i 。
 

示例 1：

输入：n = 10
输出：182
解释：总共有 3 个整数 i 满足要求：
- 1 ，因为 1 * 1 = 1
- 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
- 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
因此，10 的惩罚数为 1 + 81 + 100 = 182
示例 2：

输入：n = 37
输出：1478
解释：总共有 4 个整数 i 满足要求：
- 1 ，因为 1 * 1 = 1
- 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
- 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
- 36 ，因为 36 * 36 = 1296 ，且 1296 可以分割成 1 + 29 + 6 。
因此，37 的惩罚数为 1 + 81 + 100 + 1296 = 1478

提示：

1 <= n <= 1000*/
bool backTrackCheckPunish(string sum, string now, int leftPower, int i) {
    //回溯
    //从i*i的最右边开始
    //取出下一个字符left
    //1.下一个字符left单独计算，那么此时sum=sum+now（注意这里是字符串，需要先转换成int）
    //2.下一个字符left加入now中，now = left + now
    if (leftPower == 0) {
        //如果leftPower == 0，说明遍历到头，将sum和now相加（注意这里是字符串，需要先转换成int），判断是否等于i
        return (sum == "" ? 0 : stoi(sum)) + (now == "" ? 0 : stoi(now)) == i;
    }
    string left = to_string(leftPower % 10);
    int nextPower = leftPower / 10;
    if (backTrackCheckPunish(to_string((sum == "" ? 0 : stoi(sum)) + (now == "" ? 0 : stoi(now))), left, nextPower, i)) {
        return true;
    }
    now = left + now;
    return backTrackCheckPunish(sum, now, nextPower, i);
}

int punishmentNumber(int n) {
    //回溯
    //按题意，遍历1~n
    //计算power=i*i
    //用回溯分割power，并判断分割后的子串相加是否等于i
    int res = 0;
    for (int i = 1; i <= n; i++) {
        int power = i * i;
        if (backTrackCheckPunish("", "", power, i)) {
            res += power;
        }
    }
    return res;
}

//最大二进制奇数
/*给你一个 二进制 字符串 s ，其中至少包含一个 '1' 。

你必须按某种方式 重新排列 字符串中的位，使得到的二进制数字是可以由该组合生成的 最大二进制奇数 。

以字符串形式，表示并返回可以由给定组合生成的最大二进制奇数。

注意 返回的结果字符串 可以 含前导零。

 

示例 1：

输入：s = "010"
输出："001"
解释：因为字符串 s 中仅有一个 '1' ，其必须出现在最后一位上。所以答案是 "001" 。
示例 2：

输入：s = "0101"
输出："1001"
解释：其中一个 '1' 必须出现在最后一位上。而由剩下的数字可以生产的最大数字是 "100" 。所以答案是 "1001" 。
 

提示：

1 <= s.length <= 100
s 仅由 '0' 和 '1' 组成
s 中至少包含一个 '1'*/
string maximumOddBinaryNumber(string s) {
    //贪心
    //统计字符串中‘1’的个数cnt1，再将cnt1-1个'1'放入结果字符串头部，中间插入(s.size-cnt1)个字符'0'，并再最后结尾再加入一个字符'1'
    int cnt1 = count(s.begin(), s.end(), '1');
    int count = -1;
    for (char c : s) {
        if (c == '1') {
            count++;
        }
    }
    string res;
    for (int i = 0; i < count; i++) {
        res += '1';
    }
    for (int i = 1, l = s.size() - count; i < l; i++) {
        res += '0';
    }
    res += '1';
    return res;

    //作者：灵茶山艾府
    //链接：https://leetcode.cn/problems/maximum-odd-binary-number/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    //创建一个包含n个元素的string对象，其中每个元素都被初始化为字符c
    // return string(cnt1 - 1, '1') + string(s.length() - cnt1, '0') + '1';
}

//从数量最多的堆取走礼物
/*给你一个整数数组 gifts ，表示各堆礼物的数量。每一秒，你需要执行以下操作：

选择礼物数量最多的那一堆。
如果不止一堆都符合礼物数量最多，从中选择任一堆即可。
选中的那一堆留下平方根数量的礼物（向下取整），取走其他的礼物。
返回在 k 秒后剩下的礼物数量。

 

示例 1：

输入：gifts = [25,64,9,4,100], k = 4
输出：29
解释： 
按下述方式取走礼物：
- 在第一秒，选中最后一堆，剩下 10 个礼物。
- 接着第二秒选中第二堆礼物，剩下 8 个礼物。
- 然后选中第一堆礼物，剩下 5 个礼物。
- 最后，再次选中最后一堆礼物，剩下 3 个礼物。
最后剩下的礼物数量分别是 [5,8,9,4,3] ，所以，剩下礼物的总数量是 29 。
示例 2：

输入：gifts = [1,1,1,1], k = 4
输出：4
解释：
在本例中，不管选中哪一堆礼物，都必须剩下 1 个礼物。 
也就是说，你无法获取任一堆中的礼物。 
所以，剩下礼物的总数量是 4 。
 

提示：

1 <= gifts.length <= 103
1 <= gifts[i] <= 109
1 <= k <= 103*/
long long pickGifts(vector<int> &&gifts, int k) {
    //优先队列
    //将gifts加入优先队列中
    //每次，获取队列中最多的一组礼物gift，并只留下sqrt(gift)数量的礼物，重新加入gifts中
    //k次之后，将剩余的礼物数量相加并返回
    priority_queue<int> queue;
    for (int g : gifts) {
        queue.push(g);
    }
    for (int i = 0; i < k; i++) {
        int top = sqrt(queue.top());
        queue.pop();
        queue.push(top);
    }
    long long res = 0;
    while (!queue.empty()) {
        res += queue.top();
        queue.pop();
    }
    return res;
}

//链表的中间结点
/*给你单链表的头结点 head ，请你找出并返回链表的中间结点。

如果有两个中间结点，则返回第二个中间结点。

 

示例 1：


输入：head = [1,2,3,4,5]
输出：[3,4,5]
解释：链表只有一个中间结点，值为 3 。
示例 2：


输入：head = [1,2,3,4,5,6]
输出：[4,5,6]
解释：该链表有两个中间结点，值分别为 3 和 4 ，返回第二个结点。
 

提示：

链表的结点数范围是 [1, 100]
1 <= Node.val <= 100*/
ListNode *middleNode(ListNode *head) {
    //方法一：两趟遍历
    //1.第一趟遍历，计算链表长度
    //2.第二趟遍历，根据长度，计算并获得链表的中间节点

    //方法二：一趟遍历，快慢指针
    //使用快慢指针可以很快的找到链表的中间节点
    //维护一个快指针，和一个慢指针，初始时都指向头节点
    //遍历时，快指针每次向后移动两个位置，慢指针每次移动一次位置
    //当快指针遍历到尾节点或者尾节点的下一个节点为空时，结束遍历，此时慢指针指向的就是整个链表的中间节点
    ListNode *slow = head;
    ListNode *quick = head;
    while (quick && quick->next) {
        quick = quick->next->next;
        slow = slow->next;
    }
    return slow;
}

//有序三元组中的最大值 I
/*给你一个下标从 0 开始的整数数组 nums 。

请你从所有满足 i < j < k 的下标三元组 (i, j, k) 中，找出并返回下标三元组的最大值。如果所有满足条件的三元组的值都是负数，则返回 0 。

下标三元组 (i, j, k) 的值等于 (nums[i] - nums[j]) * nums[k] 。

 

示例 1：

输入：nums = [12,6,1,2,7]
输出：77
解释：下标三元组 (0, 2, 4) 的值是 (nums[0] - nums[2]) * nums[4] = 77 。
可以证明不存在值大于 77 的有序下标三元组。
示例 2：

输入：nums = [1,10,3,4,19]
输出：133
解释：下标三元组 (1, 2, 4) 的值是 (nums[1] - nums[2]) * nums[4] = 133 。
可以证明不存在值大于 133 的有序下标三元组。 
示例 3：

输入：nums = [1,2,3]
输出：0
解释：唯一的下标三元组 (0, 1, 2) 的值是一个负数，(nums[0] - nums[1]) * nums[2] = -3 。因此，答案是 0 。
 

提示：

3 <= nums.length <= 100
1 <= nums[i] <= 106*/
long long maximumTripletValue(vector<int> &nums) {
    //枚举
    //三层遍历，每层遍历获取ijk
    //实时维护最大值
    long long res = 0;
    for (int i = 0; i < nums.size(); i++) {
        for (int j = i + 1; j < nums.size(); j++) {
            for (int k = j + 1; k < nums.size(); k++) {
                res = max(res, (long long)(nums[i] - nums[j]) * nums[k]);
            }
        }
    }
    return res;

    //枚举优化
    //https://leetcode.cn/problems/maximum-value-of-an-ordered-triplet-i/solutions/2465301/yi-ti-san-jie-mei-ju-qian-hou-zhui-fen-j-eh26/
}

//环和杆
/*总计有 n 个环，环的颜色可以是红、绿、蓝中的一种。这些环分别穿在 10 根编号为 0 到 9 的杆上。

给你一个长度为 2n 的字符串 rings ，表示这 n 个环在杆上的分布。rings 中每两个字符形成一个 颜色位置对 ，用于描述每个环：

第 i 对中的 第一个 字符表示第 i 个环的 颜色（'R'、'G'、'B'）。
第 i 对中的 第二个 字符表示第 i 个环的 位置，也就是位于哪根杆上（'0' 到 '9'）。
例如，"R3G2B1" 表示：共有 n == 3 个环，红色的环在编号为 3 的杆上，绿色的环在编号为 2 的杆上，蓝色的环在编号为 1 的杆上。

找出所有集齐 全部三种颜色 环的杆，并返回这种杆的数量。

 

示例 1：


输入：rings = "B0B6G0R6R0R6G9"
输出：1
解释：
- 编号 0 的杆上有 3 个环，集齐全部颜色：红、绿、蓝。
- 编号 6 的杆上有 3 个环，但只有红、蓝两种颜色。
- 编号 9 的杆上只有 1 个绿色环。
因此，集齐全部三种颜色环的杆的数目为 1 。
示例 2：


输入：rings = "B0R0G0R9R0B0G0"
输出：1
解释：
- 编号 0 的杆上有 6 个环，集齐全部颜色：红、绿、蓝。
- 编号 9 的杆上只有 1 个红色环。
因此，集齐全部三种颜色环的杆的数目为 1 。
示例 3：

输入：rings = "G4"
输出：0
解释：
只给了一个环，因此，不存在集齐全部三种颜色环的杆。
 

提示：

rings.length == 2 * n
1 <= n <= 100
如 i 是 偶数 ，则 rings[i] 的值可以取 'R'、'G' 或 'B'（下标从 0 开始计数）
如 i 是 奇数 ，则 rings[i] 的值可以取 '0' 到 '9' 中的一个数字（下标从 0 开始计数）*/
int countPoints(string rings) {
    //哈希表，状态压缩，位运算
    //对于每个杆子，我们可以使用一个集合来记录其上是否套有不同颜色的环
    //因为只有3种环，所以可以对状态进行压缩，使用1代表红色，2代表绿色，4代表蓝色，那么就可以使用一个int值来表示这3种状态
    //遍历数组，当遇到不同颜色时，将当前颜色对应的标志与对应的杆子的int值进行按位或运算，将标志加入杆子中
    //遍历杆子数组，当其值等于7时，说明这根杆子上有所有颜色的环，结果+1
    vector<int> flags(10);
    for (int i = 0; i < rings.size(); i += 2) {
        int flag = 0;
        switch (rings[i]) {
            case 'R':
                flag = 0b1;
                break;
            case 'G':
                flag = 0b10;
                break;
            case 'B':
                flag = 0b100;
                break;
        }
        flags[rings[i + 1] - '0'] |= flag;
    }

    int res = 0;
    for (int flag : flags) {
        if (flag == 0b111) {
            res++;
        }
    }

    return res;
}

//数组中两个数的最大异或值
/*给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。

 

示例 1：

输入：nums = [3,10,5,25,2,8]
输出：28
解释：最大运算结果是 5 XOR 25 = 28.
示例 2：

输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
输出：127
 

提示：

1 <= nums.length <= 2 * 105
0 <= nums[i] <= 231 - 1*/
class BinaryTrie {
    public:
    BinaryTrie *childOne = nullptr;
    BinaryTrie *childZero = nullptr;
};
void createBinaryTrie(BinaryTrie *root, int num) {
    int flag = 1 << 30;
    while (flag) {
        if (num & flag) {
            if (!root->childOne) {
                root->childOne = new BinaryTrie;
            }
            root = root->childOne;
        } else {
            if (!root->childZero) {
                root->childZero = new BinaryTrie;
            }
            root = root->childZero;
        }
        flag >>= 1;
    }
}
int checkXorByBinaryTrie(BinaryTrie *root, int num) {
    int flag = 1 << 30;
    while (flag) {
        if (num & flag) {
            if (root->childZero) {
                root = root->childZero;
            } else {
                num &= ~flag;
                root = root->childOne;
            }

        } else {
            if (root->childOne) {
                num |= flag;
                root = root->childOne;
            } else {
                root = root->childZero;
            }
        }
        flag >>= 1;
    }
    return num;
}
int findMaximumXORByTrie(vector<int> &&nums) {
    //哈希表，字典树
    //因为一个数字的一个位只有0和1两种状态，那么，使用一个字典树，每棵树表示数字其中一位
    //从最高位1<<30开始，对每个元素构建字典树
    //当前位如果位0，则构入childZero，并将当前父节点设为childZero
    //当前位如果位1，则构入childOne，并将当前父节点设为childOne
    //也就是从最高位1<<30开始，判断每个数字的每一位存在于字典树对应的哪个子树位上
    //当所有元素遍历结束后，一颗字典树构建完成，代表，所有数字的每一位
    //再遍历元素，从最高位1<<30开始：
    //1.如果元素当前位为0，那么需要对应位为1，异或后就能成为更大的1，如果字典树中当前位没有1的子树，那么只能选择0，选择对应的子树后继续下一位计算；
    //2.如果元素当前位为1，那么需要对应位为0，异或后就能成为更大的1，如果字典树中当前位没有0的子树，那么只能选择1，并且对应位应置0，选择对应的子树后继续下一位计算；
    //遍历时更新最大值，并在最后返回
    int res = 0;

    BinaryTrie *root = new BinaryTrie;
    for (int n : nums) {
        createBinaryTrie(root, n);
    }

    unordered_set<int> map;
    for (int n : nums) {
        if (map.find(n) == map.end()) {
            res = max(res, checkXorByBinaryTrie(root, n));
            map.insert(n);
        }
    }

    return res;
}
int findMaximumXOR(vector<int> &&nums) {
    //哈希表（超时）
    //两层循环，找出最大异或值
    //并用哈希表记录已遍历的元素
    unordered_map<int, int> maximum;
    int res = 0;

    for (int i = 0; i < nums.size(); i++) {
        if (maximum[nums[i]]) {
            continue;
        }
        for (int j = i + 1; j < nums.size(); j++) {
            maximum[nums[i]] = max(maximum[nums[i]], nums[i] ^ nums[j]);
        }
        res = max(res, maximum[nums[i]]);
    }

    return res;
}

//最长平衡子字符串
/*给你一个仅由 0 和 1 组成的二进制字符串 s 。  

如果子字符串中 所有的 0 都在 1 之前 且其中 0 的数量等于 1 的数量，则认为 s 的这个子字符串是平衡子字符串。请注意，空子字符串也视作平衡子字符串。 

返回  s 中最长的平衡子字符串长度。

子字符串是字符串中的一个连续字符序列。

 

示例 1：

输入：s = "01000111"
输出：6
解释：最长的平衡子字符串是 "000111" ，长度为 6 。
示例 2：

输入：s = "00111"
输出：4
解释：最长的平衡子字符串是 "0011" ，长度为  4 。
示例 3：

输入：s = "111"
输出：0
解释：除了空子字符串之外不存在其他平衡子字符串，所以答案为 0 。
 

提示：

1 <= s.length <= 50
'0' <= s[i] <= '1'*/
int findTheLongestBalancedSubstring(string s) {
    //计数，状态机
    //遍历字符串，统计连续0出现的次数zeroCount，连续1出现的次数oneCount
    //当再次遇到'0'时，判断并记录最长平衡子字符串的数量max(res, min(zeroCount, oneCount))
    //这里用两个状态STATE_ZERO和STATE_ONE表示，当前字符串遍历是出于哪一种状态中，以此来判断下一个遇到的字符是'0'或'1'时需要如何处理统计次数。
    int zeroCount = 0, oneCount = 0, res = 0;
    const int STATE_ZERO = 0, STATE_ONE = 1;
    int state = STATE_ONE;

    for (char c : s) {
        switch (state) {
            case STATE_ZERO:
                if (c == '0') {
                    zeroCount++;
                } else {
                    state = STATE_ONE;
                    oneCount++;
                }
                break;
            case STATE_ONE:
                if (c == '0') {
                    res = max(res, min(zeroCount, oneCount));
                    zeroCount = 1;
                    oneCount = 0;
                    state = STATE_ZERO;
                } else {
                    oneCount++;
                }
                break;
        }
    }

    return max(res, min(zeroCount, oneCount)) * 2;
}

//按摩师
/*一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。

注意：本题相对原题稍作改动

 

示例 1：

输入： [1,2,3,1]
输出： 4
解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
示例 2：

输入： [2,7,9,3,1]
输出： 12
解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
示例 3：

输入： [2,1,4,5,3,1,1,3]
输出： 12
解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。*/
int massage(vector<int> &nums) {
    //动态规划
    //设dp[i][0]表示以下标i为结尾时，选中下标出的元素时可获得的最大分钟数
    //设dp[i][1]表示以下标i为结尾时，不选中下标出的元素时可获得的最大分钟数
    //那么最后答案就为max(dp[size-1][0],dp[siez-1][1])
    //dp[i][0]=dp[i-1][1]+nums[i]：因为相邻元素不能同时选中，所以这表示上一个下标未选中时的最大分钟数加上当前下标的分钟数
    //dp[i][1]=max(dp[i-1][0],dp[i-1][1])：当前下标的元素不选择，那么上一个元素可以选择也可以不选择，所以就为其中的最大值
    int choosed = 0;
    int unchoosed = 0;

    for (int n : nums) {
        int tmp = unchoosed;
        unchoosed = max(choosed, unchoosed);
        choosed = tmp + n;
    }

    return max(choosed, unchoosed);
}

//BiNode
/*二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。实现一个方法，把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。

返回转换后的单向链表的头节点。

注意：本题相对原题稍作改动

 

示例：

输入： [4,2,5,1,3,null,6,0]
输出： [0,null,1,null,2,null,3,null,4,null,5,null,6]
提示：

节点数量不会超过 100000。*/
void convertBiNodeDFS(TreeNode *root, vector<TreeNode *> &list) {
    if (root) {
        convertBiNodeDFS(root->left, list);
        root->left = nullptr;
        list.push_back(root);
        convertBiNodeDFS(root->right, list);
        root->right = nullptr;
    }
}
TreeNode *convertBiNode(TreeNode *root) {
    //深度优先搜索
    //因为是二叉搜索树，所以树左边节点比根节点小，右边节点比根节点大
    //那么用中序遍历，即可得到从小到大的值
    //用一个list保存树节点从小到大的值
    //并再最后按从小到大依次拼接在每个子树的右节点上即可
    if (!root) {
        return nullptr;
    }
    vector<TreeNode *> list;
    convertBiNodeDFS(root, list);
    for (int i = 0; i < list.size() - 1; i++) {
        list[i]->right = list[i + 1];
    }
    return list[0];
}

//卡牌分组
/*给定一副牌，每张牌上都写着一个整数。

此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：

每组都有 X 张牌。
组内所有的牌上都写着相同的整数。
仅当你可选的 X >= 2 时返回 true。

 

示例 1：

输入：deck = [1,2,3,4,4,3,2,1]
输出：true
解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
示例 2：

输入：deck = [1,1,1,2,2,2,3,3]
输出：false
解释：没有满足要求的分组。

提示：

1 <= deck.length <= 104
0 <= deck[i] < 104*/
bool hasGroupsSizeX(vector<int> &deck) {
    //最大公约数（暴力解法）
    //先用哈希表统计每个数出现的频次
    //再寻找计算所有数的最大公约数，如果最大公约数大于等于2，说明可以分组，否则不能
    //优化：可以用辗转相除法求最大公约数。
    unordered_map<int, int> map;
    for (int n : deck) {
        map[n]++;
    }
    int minCount = deck.size();
    for (auto entry : map) {
        minCount = min(minCount, entry.second);
    }

    for (int i = 2; i <= minCount; i++) {
        bool flag = true;
        for (auto entry : map) {
            if (entry.second % i != 0) {
                flag = false;
            }
        }
        if (flag) {
            return true;
        }
    }
    return false;
}

//可以被一步捕获的棋子数
/*在一个 8 x 8 的棋盘上，有一个白色的车（Rook），用字符 'R' 表示。棋盘上还可能存在空方块，白色的象（Bishop）以及黑色的卒（pawn），分别用字符 '.'，'B' 和 'p' 表示。不难看出，大写字符表示的是白棋，小写字符表示的是黑棋。

车按国际象棋中的规则移动。东，西，南，北四个基本方向任选其一，然后一直向选定的方向移动，直到满足下列四个条件之一：

棋手选择主动停下来。
棋子因到达棋盘的边缘而停下。
棋子移动到某一方格来捕获位于该方格上敌方（黑色）的卒，停在该方格内。
车不能进入/越过已经放有其他友方棋子（白色的象）的方格，停在友方棋子前。
你现在可以控制车移动一次，请你统计有多少敌方的卒处于你的捕获范围内（即，可以被一步捕获的棋子数）。

 

示例 1：



输入：[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","R",".",".",".","p"],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
输出：3
解释：
在本例中，车能够捕获所有的卒。
示例 2：



输入：[[".",".",".",".",".",".",".","."],[".","p","p","p","p","p",".","."],[".","p","p","B","p","p",".","."],[".","p","B","R","B","p",".","."],[".","p","p","B","p","p",".","."],[".","p","p","p","p","p",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
输出：0
解释：
象阻止了车捕获任何卒。
示例 3：



输入：[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","p",".",".",".","."],["p","p",".","R",".","p","B","."],[".",".",".",".",".",".",".","."],[".",".",".","B",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."]]
输出：3
解释： 
车可以捕获位置 b5，d6 和 f5 的卒。
 

提示：

board.length == board[i].length == 8
board[i][j] 可以是 'R'，'.'，'B' 或 'p'
只有一个格子上存在 board[i][j] == 'R'*/
int numRookCaptures(vector<vector<char>> &board) {
    //模拟，遍历
    //遍历二维字符串数组
    //当遇到车'R'时，再对其下标的四个方向进行延申遍历
    //如果遇到黑卒p，则结果+1，并退出循环；如果遇到白象B，则直接退出循环
    int res = 0;
    for (int r = 0; r < board.size(); r++) {
        for (int c = 0; c < board[0].size(); c++) {
            if (board[r][c] == 'R') {
                for (int i = c - 1; i >= 0; i--) {
                    if (board[r][i] == 'p') {
                        res++;
                        break;
                    }
                    if (board[r][i] == 'B') {
                        break;
                    }
                }
                for (int i = r - 1; i >= 0; i--) {
                    if (board[i][c] == 'p') {
                        res++;
                        break;
                    }
                    if (board[i][c] == 'B') {
                        break;
                    }
                }
                for (int i = c + 1; i < board[0].size(); i++) {
                    if (board[r][i] == 'p') {
                        res++;
                        break;
                    }
                    if (board[r][i] == 'B') {
                        break;
                    }
                }
                for (int i = r + 1; i < board.size(); i++) {
                    if (board[i][c] == 'p') {
                        res++;
                        break;
                    }
                    if (board[i][c] == 'B') {
                        break;
                    }
                }
            }
        }
    }
    return res;
}

//三维形体的表面积
/*给你一个 n * n 的网格 grid ，上面放置着一些 1 x 1 x 1 的正方体。每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。

放置好正方体后，任何直接相邻的正方体都会互相粘在一起，形成一些不规则的三维形体。

请你返回最终这些形体的总表面积。

注意：每个形体的底面也需要计入表面积中。

 

示例 1：


输入：grid = [[1,2],[3,4]]
输出：34
示例 2：


输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
输出：32
示例 3：


输入：grid = [[2,2,2],[2,1,2],[2,2,2]]
输出：46
 

提示：

n == grid.length
n == grid[i].length
1 <= n <= 50
0 <= grid[i][j] <= 50*/
int surfaceArea(vector<vector<int>> &grid) {
    //模拟，遍历
    //遍历二维数组，当遇到的格子高度大于0时，开始计算面积
    //1.加上格子的高度*4，代表当前格子的4个面，加上2，代表格子顶部和底部
    //2.当列数大于0时，将格子和左边的格子相比较，减去高度较小的格子的高度*2，代表两个格子粘合的部分
    //3.当行数大于0时，将格子和上边的格子相比较，减去高度较小的格子的高度*2，代表两个格子粘合的部分
    int res = 0;

    for (int r = 0; r < grid.size(); r++) {
        for (int c = 0; c < grid[0].size(); c++) {
            if (grid[r][c] > 0) {
                res += 2 + grid[r][c] * 4;
                if (r > 0) {
                    res -= min(grid[r - 1][c], grid[r][c]) * 2;
                }
                if (c > 0) {
                    res -= min(grid[r][c - 1], grid[r][c]) * 2;
                }
            }
        }
    }

    return res;
}

//数位和相等数对的最大和
/*给你一个下标从 0 开始的数组 nums ，数组中的元素都是 正 整数。请你选出两个下标 i 和 j（i != j），且 nums[i] 的数位和 与  nums[j] 的数位和相等。

请你找出所有满足条件的下标 i 和 j ，找出并返回 nums[i] + nums[j] 可以得到的 最大值 。

 

示例 1：

输入：nums = [18,43,36,13,7]
输出：54
解释：满足条件的数对 (i, j) 为：
- (0, 2) ，两个数字的数位和都是 9 ，相加得到 18 + 36 = 54 。
- (1, 4) ，两个数字的数位和都是 7 ，相加得到 43 + 7 = 50 。
所以可以获得的最大和是 54 。
示例 2：

输入：nums = [10,12,19,14]
输出：-1
解释：不存在满足条件的数对，返回 -1 。
 

提示：

1 <= nums.length <= 105
1 <= nums[i] <= 109*/
int numericalDigitSum(int n) {
    int res = 0;
    while (n > 0) {
        res += n % 10;
        n /= 10;
    }
    return res;
}
int maximumSum(vector<int> &nums) {
    //哈希表，优先队列
    //用哈希表记录每个数的数位和
    //并将相同数位和的数加入优先队列中
    //当计算结束后，再遍历哈希表，并取出每个队列中的最大的两个数（如果队列容量大于1）相加，更新并维护最大值
    //

    int res = -1;
    unordered_map<int, priority_queue<int>> map;

    //优化：
    //优先队列可以优化成仅用一个数，就是最大值
    //哈希表的key还是数位和，value就是最大值
    //计算出数位和后，仅需将其和已存在（如果有）的最大值相加，并更新维护所有数位和对的最大值。再更新当前数位和对应的数的最大值即可。
    // unordered_map<int, int> map;
    for (int n : nums) {
        map[numericalDigitSum(n)].push(n);
    }
    for (auto entry : map) {
        if (entry.second.size() > 1) {
            int f = entry.second.top();
            entry.second.pop();
            int s = entry.second.top();
            res = max(res, f + s);
        }
    }
    return res;
}

//判断一个数是否迷人
/*给你一个三位数整数 n 。

如果经过以下修改得到的数字 恰好 包含数字 1 到 9 各一次且不包含任何 0 ，那么我们称数字 n 是 迷人的 ：

将 n 与数字 2 * n 和 3 * n 连接 。
如果 n 是迷人的，返回 true，否则返回 false 。

连接 两个数字表示把它们首尾相接连在一起。比方说 121 和 371 连接得到 121371 。

 

示例 1：

输入：n = 192
输出：true
解释：我们将数字 n = 192 ，2 * n = 384 和 3 * n = 576 连接，得到 192384576 。这个数字包含 1 到 9 恰好各一次。
示例 2：

输入：n = 100
输出：false
解释：我们将数字 n = 100 ，2 * n = 200 和 3 * n = 300 连接，得到 100200300 。这个数字不符合上述条件。
 

提示：

100 <= n <= 999*/
bool isFascinating(int n) {
    //哈希表
    //用一个集合来存数字的每一位
    //将数字n每一位存入集合中，若这一位的数字已存在或者这一位的数字为0，则不符合条件，返回false
    //将数字2n每一位存入集合中，若这一位的数字已存在或者这一位的数字为0，则不符合条件，返回false
    //将数字3n每一位存入集合中，若这一位的数字已存在或者这一位的数字为0，则不符合条件，返回false
    int n2 = 2 * n;
    int n3 = 3 * n;
    unordered_set<int> set;
    while (n > 0) {
        int left = n % 10;
        if (left == 0 || set.find(left) != set.end()) {
            return false;
        }
        set.insert(left);
        n /= 10;
    }
    while (n2 > 0) {
        int left = n2 % 10;
        if (left == 0 || set.find(left) != set.end()) {
            return false;
        }
        set.insert(left);
        n2 /= 10;
    }
    while (n3 > 0) {
        int left = n3 % 10;
        if (left == 0 || set.find(left) != set.end()) {
            return false;
        }
        set.insert(left);
        n3 /= 10;
    }
    return set.size() == 9;
}

int main() {
    // cout << s << endl;
    /* vector<char> v1 = {'h', 'e', 'l', 'l', 'o'};
    reverseString(v1);
    for (char c : v1) {
        cout << c;
    }
    cout << endl; */

    /* vector<string> v2 = {"h", "e", "l", "l", "o"};
    cout << isAcronym(v2, "hello") << endl; */

    // cout << waysToBuyPensPencils(20, 10, 5) << endl;

    // cout << captureForts({1, 0, 0, -1, 0, 0, 0, 0, 1}) << endl;

    // cout << minNumber(vector{1, 2, 3, 9}, vector{4, 5}) << endl;

    // cout << divide(-17, 4) << endl;

    // cout << equalFrequency("abcc") << endl;

    /* auto res = queensAttacktheKing({{0, 0}, {1, 1}, {4, 4}, {4, 5}, {3, 5}, {2, 2}}, {3, 3});
    for (auto li : res) {
        cout << "[" << li[0] << "," << +li[1] << "] ";
    }
    cout << endl; */

    // cout << giveGem({3, 1, 2}, {{0, 2}, {2, 1}, {2, 0}}) << endl;

    // cout << sumIndicesWithKSetBits({2, 1, 1, 6, 1, 1, 1, 1, 5, 4}, 2);

    // cout << averageOfSubtree(new TreeNode(10));

    // cout << distMoney(20, 3);

    // cout << makeSmallestPalindrome("egcfe");

    // cout << findSpecialInteger({1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 12, 12, 12});

    /* vector<int> list = {2, 2, 0};
    sortColors(list);
    for (int i : list) {
        cout << i << ",";
    } */

    // cout << splitNum(4325);

    // cout << gcdOfStrings("LEET", "ABAB");

    // auto res = findDifference({1, 2, 3}, {2, 4, 6})

    // cout << numRollsToTarget(20, 20, 300);

    // cout << punishmentNumber(37);

    // cout << pickGifts({25, 64, 9, 4, 100}, 4);

    cout << findMaximumXORByTrie({3, 10, 5, 25, 2, 8});

    return 0;
}
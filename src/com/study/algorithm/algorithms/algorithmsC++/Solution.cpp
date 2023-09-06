#include <algorithm>
#include <bitset>
#include <cstddef>
#include <iostream>
#include <queue>
#include <unordered_map>
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

    return 0;
}
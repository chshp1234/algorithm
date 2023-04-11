package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

//序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
//
//     _9_
//    /   \
//   3     2
//  / \   / \
// 4   1  #  6
/// \ / \   / \
//# # # #   # #
//例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
//
//给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
//
//每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
//
//你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
//
//示例 1:
//
//输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
//输出: true
//示例 2:
//
//输入: "1,#"
//输出: false
//示例 3:
//
//输入: "9,#,#,1"
//输出: false
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 验证二叉树的前序序列化 {
    @Test
    public void 验证二叉树的前序序列化() {

        System.out.println("验证二叉树的前序序列化：" + isValidSerialization("955,#,#"));
    }

    public boolean isValidSerialization(String preorder) {

        //由于在空节点上也会填入‘#’,所以根据前序遍历可以得出原二叉树
        //方法一：构造二叉树，如果能遍历到结尾，并得到一颗二叉树，那么说明此前序遍历有效
        //当然，其实并不用真的构造出二叉树，只需要知道能否构造出来即可
        //由于空节点处填入了#，所以相当于每个节点（除了空节点）都有两个子节点。
        //我们可以使用栈，将节点入栈，当节点的两个子节点都遍历完时，在将此节点出栈，此时栈顶的元素也为此节点的父节点。
        //步骤：
        //1.遇到‘，’，继续；
        //2.遇到#时，说明遇到了栈顶元素的一个子节点，我们使用另一个栈，记录栈顶元素左右子树的遍历情况，
        //若左右子树都遍历完，则栈顶元素出栈，否则，说明只遍历完了左子树，接下去还需遍历右子树，所以栈顶元素不出栈；
        //3.遇到数字，说明遇到一个子节点，将此节点加入栈中，并记录开始遍历此节点的左子树。
        //4.最后直到栈为空，说明此树的根节点也出栈了，说明整颗树已遍历完，若还没遍历到数组结尾，则说明这不是一个合法的前序遍历结果；
        //若遍历到数组结尾了，但栈不为空，也说明这不是一个合法的前序遍历结果。
        int len;
        if (preorder == null || (len = preorder.length()) == 0) {
            return false;
        }
        char[] chars = preorder.toCharArray();

        if (chars[0] == ',') {
            return false;
        }

        Deque<Character> root   = new LinkedList<>();
        Deque<Boolean>   isLeft = new LinkedList<>();
        root.push((char) 0);
        isLeft.push(false);

        int index = 0;

        while (index < len) {
            if (chars[index] == ',') {
                index++;
                continue;
            }
            if (chars[index] == '#') {
                while (!isLeft.isEmpty()) {
                    if (isLeft.pop()) {
                        isLeft.push(false);
                        break;
                    } else {
                        root.pop();
                    }
                }
                index++;
                if (isLeft.isEmpty()) {
                    break;
                }
                continue;
            }
            root.push(chars[index]);
            isLeft.push(true);
            index++;
            while (index < len) {
                if (chars[index] == ',' || chars[index] == '#') {
                    break;
                }
                index++;
            }
        }

        return index == len && root.isEmpty();
    }

    //我们可以定义一个概念，叫做槽位。一个槽位可以被看作「当前二叉树中正在等待被节点填充」的那些位置。
    //二叉树的建立也伴随着槽位数量的变化。每当遇到一个节点时：
    //1.如果遇到了空节点，则要消耗一个槽位；
    //2.如果遇到了非空节点，则除了消耗一个槽位外，还要再补充两个槽位。
    //此外，还需要将根节点作为特殊情况处理。
    //我们使用栈来维护槽位的变化。栈中的每个元素，代表了对应节点处剩余槽位的数量，而栈顶元素就对应着下一步可用的槽位数量。
    //当遇到空节点时，仅将栈顶元素减 1；当遇到非空节点时，将栈顶元素减 1 后，再向栈中压入一个 2。
    //无论何时，如果栈顶元素变为 0，就立刻将栈顶弹出。
    //
    //遍历结束后，若栈为空，说明没有待填充的槽位，因此是一个合法序列；
    //否则若栈不为空，则序列不合法。此外，在遍历的过程中，若槽位数量不足，则序列不合法。
    //
    //如果把栈中元素看成一个整体，即所有剩余槽位的数量，也能维护槽位的变化。
    //因此，我们可以只维护一个计数器，代表栈中所有元素之和，其余的操作逻辑均可以保持不变。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree/solution/yan-zheng-er-cha-shu-de-qian-xu-xu-lie-h-jghn/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public boolean isValidSerializationByLeetCode(String preorder) {
        int n     = preorder.length();
        int i     = 0;
        int slots = 1;
        while (i < n) {
            if (slots == 0) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#') {
                slots--;
                i++;
            } else {
                // 读一个数字
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                slots++; // slots = slots - 1 + 2
            }
        }
        return slots == 0;
    }
}

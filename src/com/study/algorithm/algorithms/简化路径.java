package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

//给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
//
//在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
//
//请注意，返回的 规范路径 必须遵循下述格式：
//
//始终以斜杠 '/' 开头。
//两个目录名之间必须只有一个斜杠 '/' 。
//最后一个目录名（如果存在）不能 以 '/' 结尾。
//此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
//返回简化后得到的 规范路径 。
//
// 
//
//示例 1：
//
//输入：path = "/home/"
//输出："/home"
//解释：注意，最后一个目录名后面没有斜杠。
//示例 2：
//
//输入：path = "/../"
//输出："/"
//解释：从根目录向上一级是不可行的，因为根目录是你可以到达的最高级。
//示例 3：
//
//输入：path = "/home//foo/"
//输出："/home/foo"
//解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
//示例 4：
//
//输入：path = "/a/./b/../../c/"
//输出："/c"
// 
//
//提示：
//
//1 <= path.length <= 3000
//path 由英文字母，数字，'.'，'/' 或 '_' 组成。
//path 是一个有效的 Unix 风格绝对路径。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/simplify-path
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 简化路径 {
    @Test
    public void 简化路径() {
        System.out.println("简化路径：" + simplifyPath("/a//b////c/d//././/.."));
    }

    //状态机+栈
    //
    //- 令"/"为状态0;"."为状态1;".."为状态2;"text"为状态3
    //- 当遇到不同字符，根据上一个状态做出不同的动作
    //- 用栈存储当前路径链上的文件
    //- 取出栈中的文件，输出为简单路径
    public String simplifyPath(String path) {
        int len = path.length();
        if (len == 1) {
            return path;
        }

        StringBuilder p = new StringBuilder();
        int state = 0;
        Deque<String> ps = new LinkedList<>();

        for (int i = 1; i < len; i++) {
            char c = path.charAt(i);
            if (c == '/') {
                switch (state) {
                    case 1:
                        state = 0;
                        break;
                    case 2:
                        //若当前状态为..
                        if (!ps.isEmpty()) {
                            ps.pop();
                        }
                        state = 0;
                        break;
                    case 3:
                        //若当前状态为文字
                        ps.push(p.toString());
                        p = new StringBuilder();
                        state = 0;
                        break;
                }
            } else if (c == '.') {
                switch (state) {
                    case 0:
                        state = 1;
                        break;
                    case 1:
                        state = 2;
                        break;
                    case 2:
                        //若当前状态为..，则再加一个.后，变为文件名
                        p.append(".").append(".").append(".");
                        state = 3;
                        break;
                    case 3:
                        //直接拼接.为文件名
                        p.append(".");
                        break;
                }
            } else {
                //若当前字符为文字，那么都拼接上去，并将状态改为3（文字）
                switch (state) {
                    case 0:
                        p.append(c);
                        state = 3;
                        break;
                    case 1:
                        p.append(".").append(c);
                        state = 3;
                        break;
                    case 2:
                        p.append(".").append(".").append(c);
                        state = 3;
                        break;
                    case 3:
                        p.append(c);
                        break;
                }
            }
        }

        //最后还需根据状态做出调整
        switch (state) {
            case 2:
                if (!ps.isEmpty()) {
                    ps.pop();
                }
                break;
            case 3:
                ps.push(p.toString());
                p = new StringBuilder();
                break;
        }

        p.append("/");

        //反向输出栈，为文件路径
        while (!ps.isEmpty()) {
            p.append(ps.pollLast()).append("/");
        }

        if (p.length() > 1) {
            p.deleteCharAt(p.length() - 1);
        }

        return p.toString();
    }
}

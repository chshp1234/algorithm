package com.study.algorithm.algorithmsKT

import org.junit.Test
import java.util.*

//你是一位系统管理员，手里有一份文件夹列表 folder，你的任务是要删除该列表中的所有 子文件夹，并以 任意顺序 返回剩下的文件夹。
//
//如果文件夹 folder[i] 位于另一个文件夹 folder[j] 下，那么 folder[i] 就是 folder[j] 的 子文件夹 。
//
//文件夹的「路径」是由一个或多个按以下格式串联形成的字符串：'/' 后跟一个或者多个小写英文字母。
//
//例如，"/leetcode" 和 "/leetcode/problems" 都是有效的路径，而空字符串和 "/" 不是。
// 
//
//示例 1：
//
//输入：folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
//输出：["/a","/c/d","/c/f"]
//解释："/a/b" 是 "/a" 的子文件夹，而 "/c/d/e" 是 "/c/d" 的子文件夹。
//示例 2：
//
//输入：folder = ["/a","/a/b/c","/a/b/d"]
//输出：["/a"]
//解释：文件夹 "/a/b/c" 和 "/a/b/d" 都会被删除，因为它们都是 "/a" 的子文件夹。
//示例 3：
//
//输入: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
//输出: ["/a/b/c","/a/b/ca","/a/b/d"]
// 
//
//提示：
//
//1 <= folder.length <= 4 * 104
//2 <= folder[i].length <= 100
//folder[i] 只包含小写字母和 '/'
//folder[i] 总是以字符 '/' 起始
//每个文件夹名都是 唯一 的
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/remove-sub-folders-from-the-filesystem
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 删除子文件夹 {
    @Test
    fun 删除子文件夹() {
        println(
            "删除子文件夹:${
                removeSubfolders2(
                    arrayOf("/a", "/a/b", "/a/b/a", "/a/b/c/d")
                )
            }"
        )
    }

    fun removeSubfolders2(folder: Array<String>): List<String> {
        //排序
        //先将文件夹列表进行排序，
        //排序后，如果某个文件夹folder[i]是另一个文件夹的子文件夹folder[j]，那么folder[i]肯定在folder[j]位置后面
        //首先，排序后的第一个文件夹，肯定不会是其他文件夹的子文件夹，将该文件夹加入结果中
        //后续遍历时，取出结果列表中最后一个文件夹last，和当前遍历的文件夹folder[i]进行判断，
        //因为列表已进行字典序的升序排序，所以如果folder[i]是其他文件夹的子文件夹，那么肯定也是last的子文件夹，那么：
        //1.last的长度小于folder[i]的长度
        //2.folder[i].substring(0, last.length)==last
        //3.folder[i][last.length] == '/',说明是下一级文件夹
        //只有满足上述3点，folder[i]才有可能是last的子文件夹，否则，folder[i]不是last的子文件夹，也不可能是其他文件夹的子文件夹，那么就加入结果列表中
        Arrays.sort(folder)
        val ans = ArrayList<String>()
        ans.add(folder[0]);
        for (i in 1 until folder.size) {
            val pre = ans[ans.size - 1].length
            if (!
                (pre < folder[i].length &&
                        ans[ans.size - 1] == folder[i].substring(0, pre) &&
                        folder[i][pre] == '/')
            ) {
                ans.add(folder[i])
            }
        }
        return ans
    }

    fun removeSubfolders(folder: Array<String>): List<String> {
        //哈希表
        //将每个文件夹加入哈希表中
        //再次遍历文件夹列表，并从该文件夹根目录开始，依次判断每一级的父目录，直到该文件夹的上一级父目录为止：
        //如果目录文件夹存在于哈希表中，说明此文件夹是文件夹列表中的另一个文件夹的子文件夹
        //否则该文件夹不是某个文件夹的子文件夹，加入结果列表中
        val res = ArrayList<String>()
        val map = HashSet<String>()
        var pf: StringBuilder
        var fs: List<String>

        for (f in folder) {
            map.add(f)
        }

        OUT@
        for (f in folder) {
            fs = f.split("/")
            pf = java.lang.StringBuilder()
            for (i in 0 until fs.size - 1) {
                pf.append(fs[i])
                if (map.contains(pf.toString())) {
                    continue@OUT
                }
                pf.append("/")
            }
            res.add(f)
        }

        return res
    }
}
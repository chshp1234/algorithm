package com.study.algorithm.algorithms.algorithmsKT

//数，d1.d2.d3 为域名本身。
//
//例如，"9001 discuss.leetcode.com" 就是一个 计数配对域名 ，表示 discuss.leetcode.com 被访问了 9001 次。
//给你一个 计数配对域名 组成的数组 cpdomains ，解析得到输入中每个子域名对应的 计数配对域名 ，并以数组形式返回。可以按 任意顺序 返回答案。
//
// 
//
//示例 1：
//
//输入：cpdomains = ["9001 discuss.leetcode.com"]
//输出：["9001 leetcode.com","9001 discuss.leetcode.com","9001 com"]
//解释：例子中仅包含一个网站域名："discuss.leetcode.com"。
//按照前文描述，子域名 "leetcode.com" 和 "com" 都会被访问，所以它们都被访问了 9001 次。
//示例 2：
//
//输入：cpdomains = ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
//输出：["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
//解释：按照前文描述，会访问 "google.mail.com" 900 次，"yahoo.com" 50 次，"intel.mail.com" 1 次，"wiki.org" 5 次。
//而对于父域名，会访问 "mail.com" 900 + 1 = 901 次，"com" 900 + 50 + 1 = 951 次，和 "org" 5 次。
// 
//
//提示：
//
//1 <= cpdomain.length <= 100
//1 <= cpdomain[i].length <= 100
//cpdomain[i] 会遵循 "repi d1i.d2i.d3i" 或 "repi d1i.d2i" 格式
//repi 是范围 [1, 104] 内的一个整数
//d1i、d2i 和 d3i 由小写英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/subdomain-visit-count
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 子域名访问计数 {
    fun subdomainVisits(cpdomains: Array<String>): List<String> {
        //哈希表计数
        //将子域名和访问次数按<String,Int>形式加入哈希表中
        //首先分割字符串，取出访问次数和域名
        //再将域名按“.”分割成每一段域名
        //从分割的域名段尾部开始遍历，每次拼接上上一个域名段组成一个有效的子域名
        //最后将组成的子域名和计数次数加入哈希表中
        //将哈希表的键值对重新组合，按"次数 子域名"的形式输出成结果列表
        val map = mutableMapOf<String, Int>()

        for (s in cpdomains) {
            val match = s.split(" ")
            val count = match[0].toInt()
            val domains = match[1].split(".")
            var domain = domains[domains.size - 1]
            map[domain] = map.getOrDefault(domain, 0) + count
            for (i in (domains.size - 2) downTo 0) {
                domain = domains[i] + "." + domain
                map[domain] = map.getOrDefault(domain, 0) + count
            }
        }

        return map.map { "${it.value} ${it.key}" }
    }
}
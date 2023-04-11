package com.study.algorithm.algorithms.algorithmsKT

class 设计一个验证系统 {
    //哈希表
    //记录每个tokenId及其对应的currentTime时间
    //更新和获取时，只需要拿出加入时的currentTime，并判断currentTime+timeToLive是否过期即可
    class AuthenticationManager(val timeToLive: Int) {
        val memory = HashMap<String, Int>()
        val expireTime = -100000000
        fun generate(tokenId: String, currentTime: Int) {
            memory[tokenId] = currentTime
        }

        fun renew(tokenId: String, currentTime: Int) {
            val timeLimit = (memory[tokenId] ?: expireTime) + timeToLive
            if (timeLimit > currentTime) {
                memory[tokenId] = currentTime
            }
        }

        fun countUnexpiredTokens(currentTime: Int): Int {
            var res=0
            for (token in memory) {
                val timeLimit = token.value + timeToLive
                if (timeLimit > currentTime) {
                    res++
                }
            }
            return res
        }

    }
}
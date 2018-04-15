package com.nexte.nexte.RankingScene

import com.nexte.nexte.R
import java.util.*



/**
 * Created by albino on 02/04/18.
 */

class RankingWorker {

    fun getUsersInRanking(request: RankingModel.Request, completion: (RankingModel.Response) -> Unit) {
        val response = RankingModel.Response(this.generateRankingMockData())

        completion(response)
    }

    private fun generateRankingMockData(): Array<RankingModel.Player> {

        val userRanking1 = RankingModel.Player("Helena", R.mipmap.ic_launcher, 10, 0, 1)
        val userRanking2 = RankingModel.Player("Leticia", R.mipmap.ic_launcher, 9, 1, 2)
        val userRanking3 = RankingModel.Player("Gabriel", R.mipmap.ic_launcher, 8, 2, 3)
        val userRanking4 = RankingModel.Player("Lorrany", R.mipmap.ic_launcher, 7, 3, 4)
        val userRanking5 = RankingModel.Player("Alexandre", R.mipmap.ic_launcher, 6, 4, 5)
        val userRanking6 = RankingModel.Player("Luis Gustavo", R.mipmap.ic_launcher, 5, 5, 6)
        val userRanking7 = RankingModel.Player("Guilherme", R.mipmap.ic_launcher, 4, 6, 7)
        val userRanking8 = RankingModel.Player("Giovanni", R.mipmap.ic_launcher, 3, 7, 8)
        val userRanking9 = RankingModel.Player("Miguel", R.mipmap.ic_launcher, 2, 6, 9)
        val userRanking10 = RankingModel.Player("Larissa", R.mipmap.ic_launcher, 1, 5, 10)

         return arrayOf(userRanking1, userRanking2, userRanking3, userRanking4, userRanking5, userRanking6,
                 userRanking7, userRanking8, userRanking9, userRanking10)
    }
}
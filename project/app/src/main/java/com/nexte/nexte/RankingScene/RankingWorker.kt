package com.nexte.nexte.RankingScene

import com.nexte.nexte.R
import com.nexte.nexte.UserSingleton

/**
 * Class responsible to do request for anywhere, format Response and
 * call completion to send data for called class
 */
class RankingWorker {

    /**
     * Function to get users in ranking
     *
     * @param request Ranking Model Request that contains need information to send for server
     * @param completion Method to call on parent class
     */
    fun getUsersInRanking(request: RankingModel.Request, completion: (RankingModel.Response) -> Unit) {

        val response = RankingModel.Response(this.generateRankingMockData())

        completion(response)
    }

    /**
     * Method for create initial mocker data to use in fictional app mode of ranking scene
     *
     * @return a array of users on ranking
     */
    private fun generateRankingMockData(): Array<RankingModel.Player> {

        val userRanking1 = RankingModel.Player("Helena", R.mipmap.ic_launcher, 10, 0, 1, "ontem")
        val userRanking2 = RankingModel.Player("Leticia", R.mipmap.ic_launcher, 9, 1, 2, "ontem")
        val userRanking3 = RankingModel.Player("Gabriel", R.mipmap.ic_launcher, 8, 2, 3, "ontem")
        val userRanking4 = RankingModel.Player("Lorrany", R.mipmap.ic_launcher, 7, 3, 4, "ontem")
        val userRanking5 = RankingModel.Player("Alexandre", R.mipmap.ic_launcher, 6, 4, 5, "ontem")
        val userRanking6 = RankingModel.Player("Luis Gustavo", R.mipmap.ic_launcher, 5, 5, 6, "ontem")
        val userRanking7 = RankingModel.Player("Guilherme", R.mipmap.ic_launcher, 4, 6, 7, "ontem")
        val userRanking8 = RankingModel.Player("Giovanni", R.mipmap.ic_launcher, 3, 7, 8, "ontem")
        val userRanking9 = RankingModel.Player("Miguel", R.mipmap.ic_launcher, 2, 6, 9, "ontem")
        val userRanking10 = RankingModel.Player("Gabriel", R.mipmap.ic_launcher, 1, 5, 10, "ontem")
        val userRanking11 = RankingModel.Player("Helena", R.mipmap.ic_launcher, 10, 0, 11, "ontem")
        val userRanking12 = RankingModel.Player("Leticia", R.mipmap.ic_launcher, 9, 1, 12, "ontem")
        val userRanking13 = RankingModel.Player("Gabriel", R.mipmap.ic_launcher, 8, 2, 13, "ontem")
        val userRanking14 = RankingModel.Player("Lorrany", R.mipmap.ic_launcher, 7, 3, 14, "ontem")
        val userRanking15 = RankingModel.Player(UserSingleton.getUserInformations().name, R.mipmap.ic_launcher, 6, 4, 15, "ontem")
        val userRanking16 = RankingModel.Player("Luis Gustavo", R.mipmap.ic_launcher, 5, 5, 16, "ontem")
        val userRanking17 = RankingModel.Player("Guilherme", R.mipmap.ic_launcher, 4, 6, 17, "ontem")
        val userRanking18 = RankingModel.Player("Giovanni", R.mipmap.ic_launcher, 3, 7, 18, "ontem")
        val userRanking19 = RankingModel.Player("Miguel", R.mipmap.ic_launcher, 2, 6, 19, "ontem")
        val userRanking20 = RankingModel.Player("Larissa", R.mipmap.ic_launcher, 1, 5, 20, "ontem")
        val userRanking21 = RankingModel.Player("Helena", R.mipmap.ic_launcher, 10, 0, 21, "ontem")
        val userRanking22 = RankingModel.Player("Leticia", R.mipmap.ic_launcher, 9, 1, 22, "ontem")
        val userRanking23 = RankingModel.Player("Gabriel", R.mipmap.ic_launcher, 8, 2, 23, "ontem")
        val userRanking24 = RankingModel.Player("Lorrany", R.mipmap.ic_launcher, 7, 3, 24, "ontem")
        val userRanking25 = RankingModel.Player("Leticia", R.mipmap.ic_launcher, 6, 4, 25, "ontem")
        val userRanking26 = RankingModel.Player("Luis Gustavo", R.mipmap.ic_launcher, 5, 5, 26, "ontem")
        val userRanking27 = RankingModel.Player("Guilherme", R.mipmap.ic_launcher, 4, 6, 27, "ontem")
        val userRanking28 = RankingModel.Player("Giovanni", R.mipmap.ic_launcher, 3, 7, 28, "ontem")
        val userRanking29 = RankingModel.Player("Miguel", R.mipmap.ic_launcher, 2, 6, 29, "ontem")

         return arrayOf(userRanking1,
                 userRanking2,
                 userRanking3,
                 userRanking4,
                 userRanking5,
                 userRanking6,
                 userRanking7,
                 userRanking8,
                 userRanking9,
                 userRanking10,
                 userRanking11,
                 userRanking12,
                 userRanking13,
                 userRanking14,
                 userRanking15,
                 userRanking16,
                 userRanking17,
                 userRanking18,
                 userRanking19,
                 userRanking20,
                 userRanking21,
                 userRanking22,
                 userRanking23,
                 userRanking24,
                 userRanking25,
                 userRanking26,
                 userRanking27,
                 userRanking28,
                 userRanking29)
    }
}
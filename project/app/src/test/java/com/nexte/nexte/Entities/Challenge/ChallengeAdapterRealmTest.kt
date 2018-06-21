package com.nexte.nexte.Entities.Challenge

import com.nexte.nexte.Entities.Challenge.Helper.CancelledRealm
import com.nexte.nexte.Entities.Challenge.Helper.GameRealm
import com.nexte.nexte.Entities.Challenge.Helper.PlayedRealm
import com.nexte.nexte.HelpForRealm
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class ChallengeAdapterRealmTest: HelpForRealm() {

    var challengeAdapterRealm: ChallengeAdapterRealm? = null

    @Before
    fun setUp() {
        setUpWithChallenge()
        this.challengeAdapterRealm = ChallengeAdapterRealm()
    }

    @Test
    fun testConvertChallengeRealmToChallengeNull(){
        val id = "1"
        val challengerId: String = ""
        val challengedId: String = ""
        val challegeDate: Date = Date()
        val status: String = ""
        val stageCancelledRealm: CancelledRealm? = null
        val stagePlayedRealm: PlayedRealm? = null
        val challengeRealm = ChallengeRealm(id, challengerId, challengedId, challegeDate, status, stageCancelledRealm, stagePlayedRealm)

        val challenge = this.challengeAdapterRealm?.convertChallengeRealmToChallenge(challengeRealm)

        assertEquals(id, challenge?.id)
        assertEquals(challengedId, challenge?.challengedId)
        assertEquals(challengerId, challenge?.challengerId)
    }

    @Test
    fun testConvertChallengeRealmToChallengeNotNull(){
        val id = "1"
        val challengerId: String = ""
        val challengedId: String = ""
        val challegeDate: Date = Date()
        val status: String = ""
        val stageCancelledRealm: CancelledRealm? = CancelledRealm("asd", "CHALLENGER", Date())
        val stagePlayedRealm: PlayedRealm? = null
        val challengeRealm = ChallengeRealm(id, challengerId, challengedId, challegeDate, status, stageCancelledRealm, stagePlayedRealm)

        val challenge = this.challengeAdapterRealm?.convertChallengeRealmToChallenge(challengeRealm)

        assertEquals(id, challenge?.id)
        assertEquals(challengedId, challenge?.challengedId)
        assertEquals(challengerId, challenge?.challengerId)
    }

    @Test
    fun testConvertChallengeRealmToChallengeNotNullCase2(){
        val id = "1"
        val challengerId: String = ""
        val challengedId: String = ""
        val challegeDate: Date = Date()
        val status: String = ""
        val stageCancelledRealm: CancelledRealm? = null
        val gameRealm = GameRealm(3, 1)
        val stagePlayedRealm: PlayedRealm? = PlayedRealm(0, 0, Date(), gameRealm, gameRealm, gameRealm, gameRealm, gameRealm)
        val challengeRealm = ChallengeRealm(id, challengerId, challengedId, challegeDate, status, stageCancelledRealm, stagePlayedRealm)

        val challenge = this.challengeAdapterRealm?.convertChallengeRealmToChallenge(challengeRealm)

        assertEquals(id, challenge?.id)
        assertEquals(challengedId, challenge?.challengedId)
        assertEquals(challengerId, challenge?.challengerId)
    }

    @Test
    fun converListChallengeRealmToListChallenge(){
        val id = "1"
        val challengerId: String = ""
        val challengedId: String = ""
        val challegeDate: Date = Date()
        val status: String = ""
        val stageCancelledRealm: CancelledRealm? = null
        val stagePlayedRealm: PlayedRealm? = null
        val challengeRealm = ChallengeRealm(id, challengerId, challengedId, challegeDate, status, stageCancelledRealm, stagePlayedRealm)

        val challenges = this.challengeAdapterRealm?.convertListChallengeRealmToChallengeList(listOf(challengeRealm, challengeRealm))

        assertEquals(challenges!!.size, 2)
        assertEquals(challenges[0].challengedId, challengedId)
        assertEquals(challenges[0].challengerId, challengerId)
    }

    @Test
    fun convertChallengeToChallengeRealm(){
        val id = "1"
        val challengerId: String = ""
        val challengedId: String = ""
        val challegeDate: Date = Date()
        val status = Challenge.Status.CONFIRMED
        val game = Challenge.Stage.Played.Game(1, 1)
        val stage = Challenge.Stage.Played(1, 1, Date(), game, game, game, game, game, "")
        val challenge = Challenge(id, challengerId, challengedId, challegeDate, status, stage)

        val challengeRealm = this.challengeAdapterRealm?.convertChallengeToChallengeRealm(challenge)

        assertEquals(id, challengeRealm!!.id)
        assertEquals(challengerId, challengeRealm.challengerId)
        assertEquals(challengedId, challengeRealm.challengedId)

    }

    @Test
    fun convertChallengeToChallengeRealmCase2(){
        val id = "1"
        val challengerId: String = ""
        val challengedId: String = ""
        val challegeDate: Date = Date()
        val status = Challenge.Status.WAITING
        val stage = Challenge.Stage.Scheduled()
        val challenge = Challenge(id, challengerId, challengedId, challegeDate, status, stage)

        val challengeRealm = this.challengeAdapterRealm?.convertChallengeToChallengeRealm(challenge)

        assertEquals(id, challengeRealm!!.id)
        assertEquals(challengerId, challengeRealm.challengerId)
        assertEquals(challengedId, challengeRealm.challengedId)

    }

    @Test
    fun convertChallengeToChallengeRealmCase3(){
        val id = "1"
        val challengerId: String = ""
        val challengedId: String = ""
        val challegeDate: Date = Date()
        val status = Challenge.Status.PROCESSED
        val game = Challenge.Stage.Played.Game(1, 1)
        val stage = Challenge.Stage.Canceled("", Challenge.UserType.CHALLENGED, Date())
        val challenge = Challenge(id, challengerId, challengedId, challegeDate, status, stage)

        val challengeRealm = this.challengeAdapterRealm?.convertChallengeToChallengeRealm(challenge)

        assertEquals(id, challengeRealm!!.id)
        assertEquals(challengerId, challengeRealm.challengerId)
        assertEquals(challengedId, challengeRealm.challengedId)

    }

    @Test
    fun testGet(){
        this.challengeAdapterRealm?.realm
        val challenge = this.challengeAdapterRealm?.get("9")
        assertEquals(challenge?.id, "9")
        assertEquals(challenge?.challengerId, "1")
        assertEquals(challenge?.challengedId, "1")
    }

    @After
    fun tearDown() {
    }
}
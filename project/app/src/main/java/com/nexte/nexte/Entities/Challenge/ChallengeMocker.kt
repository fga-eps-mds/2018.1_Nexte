package com.nexte.nexte.Entities.Challenge

import java.util.*

object ChallengeMocker {

    fun generateChalleges(): List<Challenge> {
        val challenges: MutableList<Challenge> = mutableListOf()

        challenges.add(
                Challenge("1",
                        "15",
                        "11",
                        Date(2017, 11, 19),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                1,
                                0,
                                Date(2017, 11, 25),
                                Challenge.Stage.Played.Game(8, 4),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge("2",
                        "18",
                        "20",
                        Date(2017, 11, 20),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                1,
                                0,
                                Date(2017, 11, 22),
                                Challenge.Stage.Played.Game(9, 8),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge("3",
                        "19",
                        "17",
                        Date(2017, 11, 20),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                0,
                                2,
                                Date(2017, 11, 21),
                                Challenge.Stage.Played.Game(5, 7),
                                Challenge.Stage.Played.Game(2, 6),
                                null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "4",
                        "16",
                        "15",
                        Date(2017, 11, 19),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                1,
                                0,
                                Date(2017, 11, 24),
                                Challenge.Stage.Played.Game(8, 3),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "5",
                        "20",
                        "13",
                        Date(2017, 11, 19),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                0,
                                1,
                                Date(2017, 11, 22),
                                Challenge.Stage.Played.Game(2, 6),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "6",
                        "13",
                        "9",
                        Date(2017, 11, 21),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                2,
                                1,
                                Date(2017, 11, 23),
                                Challenge.Stage.Played.Game(1, 6),
                                Challenge.Stage.Played.Game(6, 4),
                                Challenge.Stage.Played.Game(7, 5),
                                null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "7",
                        "8",
                        "5",
                        Date(2017, 11, 19),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                0,
                                1,
                                Date(2017, 11, 24),
                                Challenge.Stage.Played.Game(8, 9),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "8",
                        "10",
                        "7",
                        Date(2017, 11, 21),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                0,
                                1,
                                Date(2017, 11, 21),
                                Challenge.Stage.Played.Game(1, 8),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "9",
                        "7",
                        "4",
                        Date(2017, 11, 19),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                1,
                                0,
                                Date(2017, 11, 20),
                                Challenge.Stage.Played.Game(8, 6),
                                null, null, null, null, null
                        )
                )
        )


        challenges.add(
                Challenge(
                        "10",
                        "6",
                        "3",
                        Date(2017, 11, 19),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                0,
                                2,
                                Date(2017, 11, 23),
                                Challenge.Stage.Played.Game(1, 6),
                                Challenge.Stage.Played.Game(5, 7),
                                null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "11",
                        "4",
                        "2",
                        Date(2017, 11, 19),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                1,
                                0,
                                Date(2017, 11, 22),
                                Challenge.Stage.Played.Game(9, 8),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "12",
                        "3",
                        "1",
                        Date(2017, 11, 19),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                0,
                                2,
                                Date(2017, 11, 25),
                                Challenge.Stage.Played.Game(4, 6),
                                Challenge.Stage.Played.Game(6, 7),
                                null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "13",
                        "19",
                        "17",
                        Date(2017, 11, 27),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                0,
                                1,
                                Date(2017, 11, 27),
                                Challenge.Stage.Played.Game(3, 8),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "14",
                        "17",
                        "14",
                        Date(2017, 11, 26),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                1,
                                0,
                                Date(2017, 11, 30),
                                Challenge.Stage.Played.Game(6, 4),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "15",
                        "16",
                        "15",
                        Date(2017, 11, 27),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                0,
                                1,
                                Date(2017, 11, 29),
                                Challenge.Stage.Played.Game(8, 9),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "16",
                        "14",
                        "11",
                        Date(2017, 11, 28),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                3,
                                1,
                                Date(2017, 12, 1),
                                Challenge.Stage.Played.Game(6, 4),
                                Challenge.Stage.Played.Game(6, 2),
                                Challenge.Stage.Played.Game(5, 7),
                                Challenge.Stage.Played.Game(7, 6),
                                null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "17",
                        "11",
                        "10",
                        Date(2017, 11, 27),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                0,
                                1,
                                Date(2017, 12, 1),
                                Challenge.Stage.Played.Game(6, 8),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "18",
                        "15",
                        "6",
                        Date(2017, 11, 26),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                1,
                                0,
                                Date(2017, 11, 29),
                                Challenge.Stage.Played.Game(8, 5),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "19",
                        "10",
                        "13",
                        Date(2017, 11, 27),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                2,
                                1,
                                Date(2017, 11, 30),
                                Challenge.Stage.Played.Game(6, 4),
                                Challenge.Stage.Played.Game(1, 6),
                                Challenge.Stage.Played.Game(10, 7),
                                null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "20",
                        "9",
                        "5",
                        Date(2017, 11, 26),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                0,
                                1,
                                Date(2017, 11, 27),
                                Challenge.Stage.Played.Game(8, 9),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "21",
                        "12",
                        "9",
                        Date(2017, 11, 26),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                0,
                                1,
                                Date(2017, 11, 28),
                                Challenge.Stage.Played.Game(3, 8),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "22",
                        "5",
                        "1",
                        Date(2017, 11, 26),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                2,
                                0,
                                Date(2017, 12, 2),
                                Challenge.Stage.Played.Game(7, 5),
                                Challenge.Stage.Played.Game(6, 4),
                                null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "23",
                        "7",
                        "4",
                        Date(2017, 11, 27),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                0,
                                1,
                                Date(2017, 12, 1),
                                Challenge.Stage.Played.Game(1, 8),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "24",
                        "3",
                        "2",
                        Date(2017, 11, 26),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                2,
                                0,
                                Date(2017, 11, 30),
                                Challenge.Stage.Played.Game(6, 2),
                                Challenge.Stage.Played.Game(7, 6),
                                null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "25",
                        "8",
                        "7",
                        Date(2017, 11, 27),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                0,
                                1,
                                Date(2017, 11, 29),
                                Challenge.Stage.Played.Game(0, 8),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "26",
                        "19",
                        "18",
                        Date(2017, 12, 3),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                2,
                                0,
                                Date(2017, 12, 4),
                                Challenge.Stage.Played.Game(6, 3),
                                Challenge.Stage.Played.Game(6, 1),
                                null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "27",
                        "18",
                        "12",
                        Date(2017, 12, 6),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                1,
                                2,
                                Date(2017, 12, 9),
                                Challenge.Stage.Played.Game(6, 3),
                                Challenge.Stage.Played.Game(5, 7),
                                Challenge.Stage.Played.Game(2, 10),
                                null, null, null
                        )
                )
        )


        challenges.add(
                Challenge(
                        "28",
                        "16",
                        "11",
                        Date(2017, 12, 4),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                3,
                                2,
                                Date(2017, 12, 6),
                                Challenge.Stage.Played.Game(6, 3),
                                Challenge.Stage.Played.Game(7, 5),
                                Challenge.Stage.Played.Game(1, 6),
                                Challenge.Stage.Played.Game(2, 6),
                                Challenge.Stage.Played.Game(10, 7),
                                null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "29",
                        "12",
                        "14",
                        Date(2017, 12, 3),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                1,
                                0,
                                Date(2017, 12, 5),
                                Challenge.Stage.Played.Game(8, 6),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "30",
                        "11",
                        "9",
                        Date(2017, 12, 3),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                0,
                                1,
                                Date(2017, 12, 7),
                                Challenge.Stage.Played.Game(8, 9),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "31",
                        "14",
                        "13",
                        Date(2017, 12, 5),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                0,
                                1,
                                Date(2017, 12, 9),
                                Challenge.Stage.Played.Game(3, 8),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "32",
                        "9",
                        "10",
                        Date(2017, 12, 3),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                2,
                                0,
                                Date(2017, 12, 6),
                                Challenge.Stage.Played.Game(6, 2),
                                Challenge.Stage.Played.Game(7, 6),
                                null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "33",
                        "13",
                        "15",
                        Date(2017, 12, 4),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                0,
                                1,
                                Date(2017, 12, 5),
                                Challenge.Stage.Played.Game(1, 8),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "34",
                        "10",
                        "2",
                        Date(2017, 12, 3),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                0,
                                1,
                                Date(2017, 12, 4),
                                Challenge.Stage.Played.Game(6, 8),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "35",
                        "8",
                        "7",
                        Date(2017, 12, 5),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                1,
                                0,
                                Date(2017, 12, 7),
                                Challenge.Stage.Played.Game(6, 1),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "36",
                        "6",
                        "3",
                        Date(2017, 12, 3),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                0,
                                2,
                                Date(2017, 12, 4),
                                Challenge.Stage.Played.Game(4, 6),
                                Challenge.Stage.Played.Game(5, 7),
                                null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "37",
                        "15",
                        "1",
                        Date(2017, 12, 4),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                2,
                                1,
                                Date(2017, 12, 9),
                                Challenge.Stage.Played.Game(5, 7),
                                Challenge.Stage.Played.Game(7, 5),
                                Challenge.Stage.Played.Game(10, 8),
                                null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "38",
                        "2",
                        "4",
                        Date(2017, 12, 3),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                1,
                                0,
                                Date(2017, 12, 8),
                                Challenge.Stage.Played.Game(9, 8),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "39",
                        "1",
                        "5",
                        Date(2017, 12, 4),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Played(
                                0,
                                1,
                                Date(2017, 12, 8),
                                Challenge.Stage.Played.Game(6, 8),
                                null, null, null, null, null
                        )
                )
        )

        challenges.add(
                Challenge(
                        "40",
                        "12",
                        "9",
                        Date(2017, 12, 11),
                        Challenge.Status.CONFIRMED,
                        Challenge.Stage.Scheduled()
                )
        )
        
        
        

        return challenges
    }
}

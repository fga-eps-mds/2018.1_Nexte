package com.nexte.nexte.Mocker

import com.nexte.nexte.Entities.User
import com.nexte.nexte.Entities.UserCategory
import java.util.*

class UserMocker {

    fun generateUsers(): List<User> {
        val users: MutableList<User> = mutableListOf()

        users.add(
                User("1",
                        "André Rede",
                        null,
                        "André",
                        Date(1987, 5, 15),
                        3,
                        "andre@nexte.com",
                        "130",
                        162,
                        69,
                        User.Gender.MALE.value,
                        UserCategory(),
                        User.Status.AVAILABLE.value,
                        null,
                        null
                )
        )

        users.add(
                User("2",
                        "Nova Jucá",
                        null,
                        "Nova",
                        Date(1987, 5, 22),
                        4,
                        "nova@nexte.com",
                        "130",
                        165,
                        63,
                        User.Gender.MALE.value,
                        UserCategory(),
                        User.Status.AVAILABLE.value,
                        null,
                        null
                )
        )

        users.add(
                User("3",
                        "Milô Ray",
                        null,
                        "Milô",
                        Date(1985, 4, 28),
                        6,
                        "milo@nexte.com",
                        "130",
                        65,
                        119,
                        User.Gender.MALE.value,
                        UserCategory(),
                        User.Status.AVAILABLE.value,
                        null,
                        null
                )
        )

        users.add(
                User("4",
                        "Samantha Hale",
                        null,
                        "Samantha",
                        Date(1990, 12, 27),
                        5,
                        "samantha@nexte.com",
                        "130",
                        143,
                        194,
                        User.Gender.FEMALE.value,
                        UserCategory(),
                        User.Status.AVAILABLE.value,
                        null,
                        null
                )
        )

        users.add(
                User("5",
                        "Keito Van",
                        null,
                        "Keito",
                        Date(1989, 10, 12),
                        1,
                        "keito@nexte.com",
                        "130",
                        151,
                        103,
                        User.Gender.MALE.value,
                        UserCategory(),
                        User.Status.AVAILABLE.value,
                        null,
                        null
                )
        )

        users.add(
                User("6",
                        "Marina Williams",
                        null,
                        "Marina",
                        Date(1984, 5, 2),
                        9,
                        "marina@nexte.com",
                        "130",
                        233,
                        191,
                        User.Gender.FEMALE.value,
                        UserCategory(),
                        User.Status.AVAILABLE.value,
                        null,
                        null
                )
        )

        users.add(
                User("7",
                        "Gael Montila",
                        null,
                        "Gael",
                        Date(1986, 2, 28),
                        8,
                        "gael@nexte.com",
                        "130",
                        164,
                        195,
                        User.Gender.MALE.value,
                        UserCategory(),
                        User.Status.AVAILABLE.value,
                        null,
                        null
                )
        )

        users.add(
                User("8",
                        "Dominio Khan",
                        null,
                        "Dominio",
                        Date(1992, 3, 1),
                        7,
                        "dominio@nexte.com",
                        "130",
                        255,
                        177,
                        User.Gender.MALE.value,
                        UserCategory(),
                        User.Status.AVAILABLE.value,
                        null,
                        null
                )
        )

        // TODO Challenge received = 40
        users.add(
                User("9",
                        "Rafael Pardal",
                        null,
                        "Pardal",
                        Date(1990, 10, 12),
                        10,
                        "pardal@nexte.com",
                        "130",
                        30,
                        23,
                        User.Gender.MALE.value,
                        UserCategory(),
                        User.Status.AVAILABLE.value,
                        null,
                        null
                )
        )

        users.add(
                User("10",
                        "Tomas Bento",
                        null,
                        "Bento",
                        Date(1988, 7, 22),
                        11,
                        "bento@nexte.com",
                        "130",
                        57,
                        57,
                        User.Gender.MALE.value,
                        UserCategory(),
                        User.Status.AVAILABLE.value,
                        null,
                        null
                )
        )

        users.add(
                User("11",
                        "Davi Goró",
                        null,
                        "Goró",
                        Date(1991, 10, 4),
                        16,
                        "goro@nexte.com",
                        "130",
                        222,
                        183,
                        User.Gender.MALE.value,
                        UserCategory(),
                        User.Status.AVAILABLE.value,
                        null,
                        null
                )
        )

        // TODO Challenge sended = 40
        users.add(
                User("12",
                        "João Godofredo",
                        null,
                        "Godofredo",
                        Date(1987, 2, 9),
                        13,
                        "godofredo@nexte.com",
                        "130",
                        61,
                        35,
                        User.Gender.MALE.value,
                        UserCategory(),
                        User.Status.AVAILABLE.value,
                        null,
                        null
                )
        )

        users.add(
                User("13",
                        "Nick Cairo",
                        null,
                        "Cairo",
                        Date(1993, 3, 13),
                        12,
                        "cairo@nexte.com",
                        "130",
                        68,
                        96,
                        User.Gender.MALE.value,
                        UserCategory(),
                        User.Status.AVAILABLE.value,
                        null,
                        null
                )
        )

        users.add(
                User("14",
                        "Robert Baptist",
                        null,
                        "Baptist",
                        Date(1989, 12, 4),
                        14,
                        "baptist@nexte.com",
                        "130",
                        194,
                        154,
                        User.Gender.MALE.value,
                        UserCategory(),
                        User.Status.AVAILABLE.value,
                        null,
                        null
                )
        )

        users.add(
                User("15",
                        "Lucas DelPulo",
                        null,
                        "DelPulo",
                        Date(1992, 11, 6),
                        2,
                        "delpulo@nexte.com",
                        "130",
                        48,
                        112,
                        User.Gender.MALE.value,
                        UserCategory(),
                        User.Status.AVAILABLE.value,
                        null,
                        null
                )
        )

        users.add(
                User("16",
                        "Róger Frederico",
                        null,
                        "Frederico",
                        Date(1990, 9, 27),
                        15,
                        "frederico@nexte.com",
                        "130",
                        75,
                        121,
                        User.Gender.MALE.value,
                        UserCategory(),
                        User.Status.AVAILABLE.value,
                        null,
                        null
                )
        )

        users.add(
                User("17",
                        "Grego Dimas",
                        null,
                        "Dimas",
                        Date(1990, 4, 19),
                        17,
                        "dimas@nexte.com",
                        "130",
                        71,
                        70,
                        User.Gender.MALE.value,
                        UserCategory(),
                        User.Status.AVAILABLE.value,
                        null,
                        null
                )
        )

        users.add(
                User("18",
                        "Enrique Grasgo",
                        null,
                        "Grasgo",
                        Date(1995, 1, 23),
                        19,
                        "grasgo@nexte.com",
                        "130",
                        11,
                        15,
                        User.Gender.MALE.value,
                        UserCategory(),
                        User.Status.AVAILABLE.value,
                        null,
                        null
                )
        )

        users.add(
                User("19",
                        "João Isnenn",
                        null,
                        "Isnenn",
                        Date(1992, 8, 14),
                        17,
                        "isnenn@nexte.com",
                        "130",
                        61,
                        31,
                        User.Gender.MALE.value,
                        UserCategory(),
                        User.Status.AVAILABLE.value,
                        null,
                        null
                )
        )

        users.add(
                User("20",
                        "Gustavo Kartner",
                        null,
                        "Kartner",
                        Date(1993, 4, 1),
                        20,
                        "kartner@nexte.com",
                        "130",
                        62,
                        142,
                        User.Gender.MALE.value,
                        UserCategory(),
                        User.Status.INJURED.value,
                        null,
                        null
                )
        )
        return users.toList()
    }
}
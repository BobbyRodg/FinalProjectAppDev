/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.woof.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.woof.R

/**
 * A data class to represent the information presented in the dog card
 */
data class Game(
    val id: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    val condition: Int,   // 4: New, 3: Opened, Like New, 2: Very Good, in box with instructions 1: Good, may have cosmetic damage or missing manual, 0: For Parts. Not working
    val releaseYear: Int,
    @StringRes val console: Int,
    @StringRes val shortDescriptions: Int,
    var selected: Boolean = false
)

val games = listOf(
    Game(1, R.drawable.castlevania_cartridge, R.string.game_name_1, 4, 1986, R.string.nes, R.string.game_description_1),
    Game(2, R.drawable.mortal_kombat_cartridge, R.string.game_name_2, 2, 1992, R.string.snes, R.string.game_description_2),
    Game(3, R.drawable.sonic_the_hedgehog_art, R.string.game_name_3, 4, 1991, R.string.genesis, R.string.game_description_3),
    Game(4, R.drawable.chaseh_q, R.string.game_name_4, 4, 1988, R.string.arcade,R.string.game_description_4 ),
    Game(5, R.drawable.demonsword, R.string.game_name_5, 3, 1989, R.string.nes,R.string.game_description_5 ),
    Game(6, R.drawable.doom, R.string.game_name_6, 5, 1993, R.string.pc,R.string.game_description_6 ),
    Game(7, R.drawable.doubledragon2therevenge, R.string.game_name_7, 4, 1988, R.string.nes,R.string.game_description_7 ),
    Game(8, R.drawable.excitebike, R.string.game_name_8, 3, 1984, R.string.nes,R.string.game_description_8 ),
    Game(9, R.drawable.generalchaos, R.string.game_name_9, 2, 1994, R.string.genesis, R.string.game_description_9),
    Game(10, R.drawable.ghoulsnghosts, R.string.game_name_10, 4, 1988, R.string.arcade, R.string.game_description_10),
    Game(11, R.drawable.joust, R.string.game_name_11, 3, 1982, R.string.arcade,R.string.game_description_11 ),
    Game(12, R.drawable.renegade3, R.string.game_name_12, 4, 1992, R.string.nes, R.string.game_description_12),
    Game(13, R.drawable.slotracers, R.string.game_name_13, 2, 1978, R.string.atari, R.string.game_description_13),
    Game(14, R.drawable.thelegendofzelda, R.string.game_name_14, 5, 1986, R.string.nes, R.string.game_description_14),
    Game(15, R.drawable.tron, R.string.game_name_15, 3, 1982, R.string.arcade, R.string.game_description_15),
    Game(16, R.drawable.wildgunman, R.string.game_name_16, 2, 1984, R.string.nes, R.string.game_description_16),
    Game(17, R.drawable.x_men, R.string.game_name_17, 4, 1992, R.string.genesis, R.string.game_description_17),
    Game(18, R.drawable.yarsrevenge, R.string.game_name_18, 3, 1981, R.string.atari, R.string.game_description_18),

    )

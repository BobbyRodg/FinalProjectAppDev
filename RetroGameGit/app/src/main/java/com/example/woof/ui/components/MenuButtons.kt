package com.example.woof.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.woof.R
import com.example.woof.RetroViewModel

@Composable
fun MenuButtons(whichScreen: Int, onChangeScreen: (Int) -> Unit, modifier: Modifier = Modifier, viewModel: RetroViewModel){
    Row(
    modifier = Modifier
    .fillMaxWidth()
    .padding(30.dp)
    .clickable { },
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceAround
    ) {
        RetroButton(onClick = { onChangeScreen(3) }) {
            Text(text = "Filter")
        }
        RetroButton(onClick = { onChangeScreen(0) }) {
            Text(text = "Log in")
        }
        RetroButton(onClick = {


            viewModel.addGame(
                title = "Sonic the Hedgehog",
                condition = 3,
                console = "Sega Genesis",
                imageResourceId = R.drawable.sonic_the_hedgehog_art,
                releaseYear = 1991,
                description = "Sonic the Hedgehog is a platform game developed by Sonic Team and published by Sega. It was first released for the Sega Genesis in 1991."
            )

            viewModel.addGame(
                title = "Mortal Kombat",
                condition = 3,
                console = "Arcade, Various",
                imageResourceId = R.drawable.mortal_kombat_cartridge,
                releaseYear = 1992,
                description = "Mortal Kombat is a fighting game developed by Midway Games and released in arcades in 1992. It became a best-seller and was ported to numerous platforms."
            )

            viewModel.addGame(
                title = "Castlevania",
                condition = 3,
                console = "Nintendo Entertainment System (NES)",
                imageResourceId = R.drawable.castlevania,
                releaseYear = 1986,
                description = "Castlevania is a platform game developed and published by Konami for the Nintendo Entertainment System (NES). It follows Simon Belmont's mission to defeat Dracula."
            )

            viewModel.addGame(
                title = "Chase H.Q.",
                condition = 4,
                console = "Nintendo Entertainment System (NES)",
                imageResourceId = R.drawable.chaseh_q,
                releaseYear = 1988,
                description = "Chase H.Q. is a driving/action game developed by Taito Corporation. Players assume the role of a police officer tasked with chasing down criminals in a high-speed pursuit."
            )

            viewModel.addGame(
                title = "Demon Sword",
                condition = 2,
                console = "Nintendo Entertainment System (NES)",
                imageResourceId = R.drawable.demonsword,
                releaseYear = 1990,
                description = "Demon Sword is a side-scrolling action game developed by TOSE and published by Taito Corporation. Players control a hero on a quest to rescue a princess and defeat a demon lord."
            )

            viewModel.addGame(
                title = "Doom",
                condition = 5,
                console = "PC, Various",
                imageResourceId = R.drawable.doom,
                releaseYear = 1993,
                description = "Doom is a first-person shooter game developed by id Software. It is known for its fast-paced gameplay, innovative technology, and influential level design. Doom has been ported to numerous platforms."
            )

            viewModel.addGame(
                title = "Double Dragon II: The Revenge",
                condition = 3,
                console = "Nintendo Entertainment System (NES)",
                imageResourceId = R.drawable.doubledragon2therevenge,
                releaseYear = 1988,
                description = "Double Dragon II: The Revenge is a beat 'em up game developed by Technōs Japan. It is the sequel to Double Dragon and follows the Lee brothers on a quest for revenge against the Shadow Warriors."
            )

            viewModel.addGame(
                title = "Excitebike",
                condition = 4,
                console = "Nintendo Entertainment System (NES)",
                imageResourceId = R.drawable.excitebike,
                releaseYear = 1985,
                description = "Excitebike is a motocross racing video game developed and published by Nintendo. Players compete in timed races and can design their own tracks in the game's track editor."
            )

            viewModel.addGame(
                title = "General Chaos",
                condition = 2,
                console = "Sega Genesis",
                imageResourceId = R.drawable.generalchaos,
                releaseYear = 1993,
                description = "General Chaos is a real-time tactics game developed by Game Refuge Inc. Players control a squad of soldiers in chaotic battles against opposing teams. The game features humorous and cartoonish graphics."
            )

            viewModel.addGame(
                title = "Ghouls 'n Ghosts",
                condition = 3,
                console = "Sega Genesis",
                imageResourceId = R.drawable.ghoulsnghosts,
                releaseYear = 1988,
                description = "Ghouls 'n Ghosts is a platform game developed by Capcom. It is the sequel to Ghosts 'n Goblins and follows the knight Arthur on a quest to rescue Princess Prin-Prin from demons."
            )

            viewModel.addGame(
                title = "Joust",
                condition = 5,
                console = "Arcade, Various",
                imageResourceId = R.drawable.joust,
                releaseYear = 1982,
                description = "Joust is a platform game developed by Williams Electronics. Players control knights riding flying ostriches and must defeat enemy knights in mid-air combat. Joust was popular in arcades and has been ported to numerous platforms."
            )

            viewModel.addGame(
                title = "Renegade III: The Final Chapter",
                condition = 1,
                console = "Nintendo Entertainment System (NES)",
                imageResourceId = R.drawable.renegade3,
                releaseYear = 1992,
                description = "Renegade III: The Final Chapter is a beat 'em up game developed by Technōs Japan. It is the third installment in the Renegade series and follows the protagonist's quest to defeat a criminal organization."
            )

            viewModel.addGame(
                title = "Slot Racers",
                condition = 2,
                console = "Atari 2600",
                imageResourceId = R.drawable.slotracers,
                releaseYear = 1978,
                description = "Slot Racers is a racing game developed and published by Atari, Inc. Players control cars on a top-down view and race against each other on various tracks."
            )

            viewModel.addGame(
                title = "Tron",
                condition = 4,
                console = "Arcade, Various",
                imageResourceId = R.drawable.tron,
                releaseYear = 1982,
                description = "Tron is an arcade game based on the Disney film of the same name. Players control characters from the movie and must compete in various minigames set in the digital world of Tron."
            )

            viewModel.addGame(
                title = "X-Men",
                condition = 3,
                console = "Arcade, Various",
                imageResourceId = R.drawable.x_men,
                releaseYear = 1992,
                description = "X-Men is a beat 'em up arcade game developed by Konami. Players control various characters from the X-Men comic book series and must defeat enemies through multiple levels."
            )







        }) {
            Text(text="Add game")
        }

    }

}
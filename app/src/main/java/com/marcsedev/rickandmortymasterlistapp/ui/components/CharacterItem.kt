package com.marcsedev.rickandmortymasterlistapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.marcsedev.rickandmortymasterlistapp.R
import com.marcsedev.rickandmortymasterlistapp.data.model.characters.CharacterDetailData
import com.marcsedev.rickandmortymasterlistapp.ui.theme.RickAndMortyMasterListAppTheme

@Composable
fun CharacterItem(
    character: CharacterDetailData,
    //id: Int,
    onOpenDetailCharacter: () -> Unit,
    //navController: NavController
) {
    Box(
        modifier = Modifier
            .size(180.dp, 210.dp)
            .border(1.dp, Color.Transparent, shape = RoundedCornerShape(16.dp))
            .clickable {
            onOpenDetailCharacter()
            //navController.navigate(route = AppScreens.CharacterDetailScreen.route + "/${character.id}")
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            character.image?.let { imageUrl ->
                Box(
                    modifier = Modifier
                        .shadow(elevation = 70.dp, shape = CircleShape)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(imageUrl),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .fillMaxSize()
                            .clip(CircleShape)
                            .shadow(elevation = 24.dp, shape = CircleShape)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                //modifier = Modifier
                //    .shadow(8.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .border(1.dp, Color.Transparent, shape = RoundedCornerShape(16.dp)),

                    ) {
                    Text(
                        text = character.name,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        //color = Color.Magenta,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Row(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    ) {
                        when (character.gender) {
                            "Female" -> {
                                Icon(
                                    painter = painterResource(id = R.drawable.female_24px),
                                    contentDescription = "",
                                    tint = Color.Magenta,
                                    modifier = Modifier
                                        .size(14.dp)
                                        .align(Alignment.CenterVertically)
                                )
                            }

                            "Male" -> {
                                Icon(
                                    painter = painterResource(id = R.drawable.male_24px),
                                    contentDescription = "",
                                    tint = Color.Blue,
                                    modifier = Modifier
                                        .size(14.dp)
                                        .align(Alignment.CenterVertically)
                                )
                            }

                            else -> {
                                Icon(
                                    painter = painterResource(id = R.drawable.unknown_24px),
                                    contentDescription = "",
                                    tint = Color.Gray,
                                    modifier = Modifier
                                        .size(14.dp)
                                        .align(Alignment.CenterVertically)
                                )
                            }
                        }
                        Text(
                            text = character.species,
                            modifier = Modifier.align(Alignment.CenterVertically),
                            fontSize = 12.sp,
                            color = Color.DarkGray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }

                }
            }
        }
    }
}

@Composable
@Preview
fun CharacterItemListPreview() {
    RickAndMortyMasterListAppTheme {
        CharacterItem(
            character = CharacterDetailData(
                id = 1,
                name = "Rick Sanchez",
                status = "Alive",
                species = "Human",
                type = "Scientist",
                gender = "Male",
                originData = null,
                locationData = null,
                image = "",
                episode = listOf("S01E01", "S01E02"),
                url = "https://rickandmortyapi.com/api/character/1",
                created = "2021-10-09T12:00:00Z"
            ),
            onOpenDetailCharacter = {},
            //navController = rememberNavController()
        )
    }
}
package com.marcsedev.rickandmortymasterlistapp.ui.list.masterList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.marcsedev.rickandmortymasterlistapp.R
import com.marcsedev.rickandmortymasterlistapp.data.network.model.characters.CharacterData
import com.marcsedev.rickandmortymasterlistapp.navigation.AppScreens
import com.marcsedev.rickandmortymasterlistapp.ui.theme.RickAndMortyMasterListAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterListScreen(
    viewModel: MasterListViewModel = viewModel(),
    onOpenDetailCharacter: (id: Int) -> Unit,
    navController: NavController
) {
    val charactersList by viewModel.charactersList.observeAsState(emptyList())
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Rick and Morty Character List",
                        color = Color.White // Puedes ajustar el color aquí
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Black
                )
            )
        },
        containerColor = Color.Black
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(R.drawable.cielo_estrellado),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
            )
            Column(
                modifier = Modifier
                    .padding(top = padding.calculateTopPadding())
                    .padding(16.dp),
                horizontalAlignment = CenterHorizontally
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)

                ) {
                    itemsIndexed(charactersList) { index, character ->
                        CharacterItemList(
                            character = character,
                            onOpenDetailCharacter = {
                                character.id
                            },
                            navController = navController
                        )
                        if (index == charactersList.size - 1) {
                            viewModel.loadMoreCharacters()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItemList(
    character: CharacterData,
    onOpenDetailCharacter: (id: Int) -> Unit,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .size(180.dp, 210.dp)
            .border(1.dp, Color.Magenta, shape = RoundedCornerShape(16.dp))
            .clickable {
                //onOpenDetailCharacter(character.id)
                navController.navigate(route = AppScreens.CharacterDetailScreen.route + "/${character.id}")
            }
    ) {
        Image(
            painter = painterResource(R.drawable.cielo_estrellado),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            character.image?.let { imageUrl ->
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .fillMaxWidth()
                        .clip(CircleShape)
                        .background(Color.LightGray)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier.shadow(8.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Text(
                        text = character.name,
                        modifier = Modifier.align(CenterHorizontally),
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = character.species,
                        modifier = Modifier.align(CenterHorizontally),
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MasterListScreenPreview() {
    RickAndMortyMasterListAppTheme {
        val navController = rememberNavController()
        val sampleCharacters = listOf(
            CharacterData(
                id = 1,
                name = "Rick Sanchez",
                status = "Alive",
                species = "Human",
                type = "Scientist",
                gender = "Male",
                originData = null,
                locationData = null,
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                episode = listOf("S01E01", "S01E02"),
                url = "https://example.com/rick",
                created = "2021-10-09T12:00:00Z"
            ),
            CharacterData(
                id = 2,
                name = "Morty Smith",
                status = "Alive",
                species = "Human",
                type = "Sidekick",
                gender = "Male",
                originData = null,
                locationData = null,
                image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                episode = listOf("S01E01", "S01E02"),
                url = "https://example.com/morty",
                created = "2021-10-09T12:00:00Z"
            ),
            CharacterData(
                id = 1,
                name = "Rick Sanchez",
                status = "Alive",
                species = "Human",
                type = "Scientist",
                gender = "Male",
                originData = null,
                locationData = null,
                image = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
                episode = listOf("S01E01", "S01E02"),
                url = "https://example.com/rick",
                created = "2021-10-09T12:00:00Z"
            ),
            CharacterData(
                id = 2,
                name = "Morty Smith",
                status = "Alive",
                species = "Human",
                type = "Sidekick",
                gender = "Male",
                originData = null,
                locationData = null,
                image = "https://rickandmortyapi.com/api/character/avatar/4.jpeg",
                episode = listOf("S01E01", "S01E02"),
                url = "https://example.com/morty",
                created = "2021-10-09T12:00:00Z"
            )
        )
        MasterListScreen(
            viewModel = remember {
                MasterListViewModel().apply {
                    setCharactersList(
                        sampleCharacters
                    )
                }
            },
            onOpenDetailCharacter = {},
            navController = navController
        )
    }
}

@Composable
@Preview
fun CharacterItemListPreview() {
    val navController = rememberNavController()
    val sampleCharacter = CharacterData(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        type = "Scientist",
        gender = "Male",
        originData = null,
        locationData = null,
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        episode = listOf("S01E01", "S01E02"),
        url = "https://rickandmortyapi.com/api/character/1",
        created = "2021-10-09T12:00:00Z"
    )

    RickAndMortyMasterListAppTheme {
        CharacterItemList(
            character = sampleCharacter,
            onOpenDetailCharacter = {},
            navController = navController
        )
    }
}
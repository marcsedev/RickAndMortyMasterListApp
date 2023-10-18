package com.marcsedev.rickandmortymasterlistapp.ui.screens.masterList

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.marcsedev.rickandmortymasterlistapp.R
import com.marcsedev.rickandmortymasterlistapp.data.model.characters.CharacterData
import com.marcsedev.rickandmortymasterlistapp.navigation.AppScreens
import com.marcsedev.rickandmortymasterlistapp.ui.theme.RickAndMortyMasterListAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterListScreen(
    masterListViewModel: MasterListViewModel = viewModel(),
    onOpenDetailCharacter: (Int) -> Unit,
    navController: NavController
) {
    val charactersList by masterListViewModel.charactersList.observeAsState(emptyList())
    val isLoading by masterListViewModel.isLoading.observeAsState(false)

    Scaffold(
        topBar = { MyTopAppBar() },
        bottomBar = { MyBottomNavigation() }
    ) { padding ->
       /* if (isLoading) {
            Loading()
        } else {*/
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
                                masterListViewModel.loadMoreCharacters()
                            }
                        }
                    }
                }
            }
        }
    }
//}

@Composable
fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.cielo_estrellado),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
        )
        CircularProgressIndicator(
            Modifier
                .align(Alignment.Center)
                .animateContentSize()
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "Character List",
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Black
        ),
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }

    )
}

@Composable
fun MyBottomNavigation() {
    var index by remember { mutableIntStateOf(0) }

    NavigationBar(
        containerColor = Color.Black
    ) {
        NavigationBarItem(
            selected = index == 0,
            label = {
                Text(
                    text = "Characters",
                    color = if (index == 0) Color.White else LocalContentColor.current
                )
            },
            onClick = { index = 0 },
            icon = { Icon(imageVector = Icons.Filled.Person, contentDescription = "") },
        )
        NavigationBarItem(
            selected = index == 1,
            label = {
                Text(
                    text = "Chapters",
                    color = if (index == 1) Color.White else LocalContentColor.current
                )
            },
            onClick = { index = 1 },
            icon = { Icon(imageVector = Icons.Filled.List, contentDescription = "") },
        )
        NavigationBarItem(
            selected = index == 2,
            label = {
                Text(
                    text = "Favorites",
                    color = if (index == 2) Color.White else LocalContentColor.current
                )
            },
            onClick = { index = 2 },
            icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = "") },
        )
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
            .border(1.dp, Color.Transparent, shape = RoundedCornerShape(16.dp))
            .clickable {
                navController.navigate(route = AppScreens.CharacterDetailScreen.route + "/${character.id}")
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = CenterHorizontally
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
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        //color = Color.Magenta,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Row(
                        modifier = Modifier.align(CenterHorizontally),
                    ) {
                        when (character.gender) {
                            "Female" -> {
                                Icon(
                                    painter = painterResource(id = R.drawable.female_24px),
                                    contentDescription = "",
                                    tint = Color.Magenta,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                            "Male" -> {
                                Icon(
                                    painter = painterResource(id = R.drawable.male_24px),
                                    contentDescription = "",
                                    tint = Color.Blue,
                                    modifier = Modifier.size(14.dp)

                                )
                            }
                            else -> {
                                Icon(
                                    painter = painterResource(id = R.drawable.unknown_24px),
                                    contentDescription = "",
                                    tint = Color.Gray,
                                    modifier = Modifier.size(14.dp)

                                )
                            }
                        }
                        Text(
                            text = character.species,
                            modifier = Modifier.align(CenterVertically),
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


@Preview
@Composable
fun MasterListScreenPreview() {
    RickAndMortyMasterListAppTheme {
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
                image = R.drawable.rick_and_morty_logo.toString(),
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
                image = R.drawable.rick_and_morty_logo.toString(),
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
                image = R.drawable.rick_and_morty_logo.toString(),
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
                image = R.drawable.rick_and_morty_logo.toString(),
                episode = listOf("S01E01", "S01E02"),
                url = "https://example.com/morty",
                created = "2021-10-09T12:00:00Z"
            )
        )
        MasterListScreen(
            masterListViewModel = remember {
                MasterListViewModel().apply {
                    setCharactersList(
                        sampleCharacters
                    )
                }
            },
            onOpenDetailCharacter = {},
            navController = rememberNavController()
        )
    }
}

@Composable
@Preview
fun CharacterItemListPreview() {
    RickAndMortyMasterListAppTheme {
        CharacterItemList(
            character = CharacterData(
                id = 1,
                name = "Rick Sanchez",
                status = "Alive",
                species = "Human",
                type = "Scientist",
                gender = "Male",
                originData = null,
                locationData = null,
                image = R.drawable.rick_and_morty_logo.toString(),
                episode = listOf("S01E01", "S01E02"),
                url = "https://rickandmortyapi.com/api/character/1",
                created = "2021-10-09T12:00:00Z"
            ),
            onOpenDetailCharacter = {},
            navController = rememberNavController()
        )
    }
}
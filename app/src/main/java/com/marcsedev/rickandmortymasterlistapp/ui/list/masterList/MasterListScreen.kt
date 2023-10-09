package com.marcsedev.rickandmortymasterlistapp.ui.list.masterList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.marcsedev.rickandmortymasterlistapp.data.network.model.characters.CharacterData
import com.marcsedev.rickandmortymasterlistapp.ui.theme.RickAndMortyMasterListAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterListScreen(
    viewModel: MasterListViewModel = viewModel()
    //onBackClick: () -> Unit,
    //onOpenCharacter: (categoryId: Int, title: String) -> Unit,
) {
    val charactersList by viewModel.charactersList.observeAsState(emptyList())

    //val masterListViewModel: MasterListViewModel = viewModel()
    //val charactersList by masterListViewModel.charactersList.observeAsState(emptyList())
    val character by viewModel.character.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Rick and Morty Character List")
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    }
                },
            )
        },
        containerColor = Color.Black
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(top = padding.calculateTopPadding())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)

            ) {
                items(charactersList) { character ->
                    CharacterItemList(
                        character = character,
                        onOpenDetail = {}
                    )
                }
            }
        }
    }
}

@Composable
fun CharacterItemList(character: CharacterData, onOpenDetail: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .size(180.dp, 200.dp)
            .border(1.dp, Color.Magenta, shape = RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray),
            horizontalAlignment = Alignment.CenterHorizontally
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
                modifier = Modifier
                    .background(Color.Transparent)
                    .shadow(8.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Text(
                        text = character.name,
                        modifier = Modifier.align(CenterHorizontally),
                        fontSize = 12.sp
                    )
                    Text(
                        text = character.species,
                        modifier = Modifier.align(CenterHorizontally),
                        fontSize = 12.sp
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
        MasterListScreen(viewModel = remember {
            MasterListViewModel().apply {
                setCharactersList(
                    sampleCharacters
                )
            }
        })
    }
}

@Composable
@Preview
fun CharacterItemListPreview() {
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
        CharacterItemList(character = sampleCharacter) {}
    }
}

package com.marcsedev.rickandmortymasterlistapp.ui.list.masterList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
            .padding(8.dp)
            .size(180.dp, 200.dp)
            .border(1.dp, Color.Magenta, shape = RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Carga de la imagen con Glide
            character.image?.let { imageUrl ->
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .fillMaxWidth()
                        .clip(CircleShape)
                        .background(Color.LightGray),
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .background(Color.White)
            ) {
                Text(
                    text = character.name,
                    modifier = Modifier.align(CenterHorizontally),
                    fontSize = 14.sp
                )
                Text(
                    text = character.species,
                    modifier = Modifier.align(CenterHorizontally),
                    fontSize = 14.sp
                )
            }
        }
    }
}


@Preview
@Composable
fun MasterListScreenPreview() {
    RickAndMortyMasterListAppTheme {
        MasterListScreen()
    }
}

@Preview
@Composable
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
        image = "https://example.com/rick_image.jpg",
        episode = listOf("S01E01", "S01E02"),
        url = "https://example.com/rick",
        created = "2021-10-09T12:00:00Z"
    )

    RickAndMortyMasterListAppTheme {
        CharacterItemList(character = sampleCharacter) {}
    }
}

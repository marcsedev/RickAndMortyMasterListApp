package com.marcsedev.rickandmortymasterlistapp.ui.screens.list.detailList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.marcsedev.rickandmortymasterlistapp.R
import com.marcsedev.rickandmortymasterlistapp.ui.theme.RickAndMortyMasterListAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    viewModel: CharacterDetailViewModel = viewModel(),
    navController: NavController,
    id: Int
) {
    val character by viewModel.character.observeAsState()

    LaunchedEffect(id) {
        id.let {
            viewModel.getCharacterById(it)
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = character?.name ?: "_name",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Black
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
            )
        },
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
                horizontalAlignment = CenterHorizontally
            ) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = padding.calculateTopPadding())
                        .padding(16.dp)
                        .shadow(elevation = 16.dp)
                        .border(1.dp, Color.White, shape = RoundedCornerShape(16.dp))
                ) {
                    Column(
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(character?.image),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(16.dp))
                        )
                        Card(
                            modifier = Modifier
                                .shadow(16.dp)
                                .shadow(elevation = 16.dp),
                            shape = RoundedCornerShape(16.dp),
                        ) {
                            Column(
                                horizontalAlignment = CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                            ) {
                                Text(
                                    text = id.toString(),
                                    modifier = Modifier.align(CenterHorizontally),
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = character?.name ?: "_name",
                                    modifier = Modifier.align(CenterHorizontally),
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = character?.species ?: "_species",
                                    modifier = Modifier.align(CenterHorizontally),
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = character?.status ?: "_status",
                                    modifier = Modifier.align(CenterHorizontally),
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = character?.gender ?: "_gender",
                                    modifier = Modifier.align(CenterHorizontally),
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CharacterDetailScreenPreview() {
    RickAndMortyMasterListAppTheme {
        val navController = rememberNavController()
        CharacterDetailScreen(
            viewModel = remember { CharacterDetailViewModel() },
            navController = navController,
            id = 2
        )
    }
}
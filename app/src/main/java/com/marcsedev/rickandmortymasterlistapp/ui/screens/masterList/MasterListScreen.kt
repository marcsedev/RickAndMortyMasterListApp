package com.marcsedev.rickandmortymasterlistapp.ui.screens.masterList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.marcsedev.rickandmortymasterlistapp.R
import com.marcsedev.rickandmortymasterlistapp.navigation.Routes
import com.marcsedev.rickandmortymasterlistapp.ui.components.CharacterItem
import com.marcsedev.rickandmortymasterlistapp.ui.components.MyBottomNavigationBar
import com.marcsedev.rickandmortymasterlistapp.ui.components.MyFloatingActionButton
import com.marcsedev.rickandmortymasterlistapp.ui.components.MyTopAppBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterListScreen(
    masterListViewModel: MasterListViewModel = viewModel(),
    navController: NavController,
) {

    data class NavigationItem(
        val title: String,
        val selectedIcon: ImageVector,
        val unselectedIcon: ImageVector,
    )

    val charactersList by masterListViewModel.charactersList.collectAsState(emptyList())
    val isLoading by masterListViewModel.isLoading.collectAsState(false)
    val coroutineScope = rememberCoroutineScope()
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val rvState = rememberLazyGridState()
    val showButton by remember {
        derivedStateOf {
            rvState.firstVisibleItemScrollOffset > 0
        }
    }


    val items = listOf(
        NavigationItem(
            title = "Author",
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle,
        ),
        NavigationItem(
            title = "Urgent",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
        ),
        NavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
        ),
    )

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.rick_morty_title),
                        contentDescription = "_title",
                        modifier = Modifier
                            .padding(36.dp)
                            .align(CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    items.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            label = {
                                Text(text = item.title)
                            },
                            selected = index == selectedItemIndex,
                            onClick = {
                                selectedItemIndex = index
                                coroutineScope.launch {
                                    drawerState.close()
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)

                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "v1.0",
                        modifier = Modifier.align(CenterHorizontally),
                        color = Color.LightGray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = { MyTopAppBar(drawerState, coroutineScope) },
            bottomBar = { MyBottomNavigationBar() },
            floatingActionButton = {
                if (showButton) {
                    MyFloatingActionButton {
                        coroutineScope.launch {
                            rvState.animateScrollToItem(0)
                        }
                    }
                }
            }
        ) { padding ->
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                androidx.compose.foundation.Image(
                    painter = painterResource(R.drawable.cielo_estrellado),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                )
                Column(
                    modifier = Modifier
                        .padding(top = padding.calculateTopPadding())
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = CenterHorizontally,
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        state = rvState
                    ) {
                        itemsIndexed(charactersList) { index, character ->
                            CharacterItem(
                                character = character,
                                onOpenDetailCharacter = {
                                    navController.navigate(route = Routes.CharacterDetailScreen.route + "/${character.id}")
                                },
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
}

/*
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MasterListScreenPreview() {
    RickAndMortyMasterListAppTheme {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
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
                MasterListViewModel(CharactersListUseCase()).apply {
                    setCharactersList(
                        sampleCharacters
                    )
                }
            },
            onOpenDetailCharacter = {},
            //navController = rememberNavController(),
        )
    }
}*/
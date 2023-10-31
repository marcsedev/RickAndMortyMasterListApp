package com.marcsedev.rickandmortymasterlistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.marcsedev.rickandmortymasterlistapp.navigation.AppNavigation
import com.marcsedev.rickandmortymasterlistapp.ui.screens.masterList.MasterListViewModel
import com.marcsedev.rickandmortymasterlistapp.ui.theme.RickAndMortyMasterListAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val masterListViewModel: MasterListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyMasterListAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Update navigation
                    AppNavigation(masterListViewModel)
                    //MasterListScreen(onOpenDetailCharacter = {}, masterListViewModel = masterListViewModel )
                    //CharacterDetailScreen(/*navController = ,*/ id = 2)
                }
            }
        }
    }
}
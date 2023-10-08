package com.marcsedev.rickandmortymasterlistapp.ui.list.masterList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcsedev.rickandmortymasterlistapp.data.network.model.characters.CharacterData
import com.marcsedev.rickandmortymasterlistapp.data.respository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MasterListViewModel : ViewModel() {

    private val repository = CharacterRepository()

    private val _charactersList = MutableLiveData<List<CharacterData>>()
    val charactersList: LiveData<List<CharacterData>> = _charactersList

    fun getCharactersList() {
        viewModelScope.launch {
            try {
                val characters = repository.getCharactersList()
                _charactersList.value = characters
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

}

/*
interface MasterListViewModelInterface : BaseViewModelInterface {
    val faqCategoryUiState: StateFlow<FaqsCategoryUiState>
    fun updateSearchText(newSearchText: String)
}

internal val composePreviewFaqsCategoryViewModelInterface by lazy {
    object : FaqsCategoryViewModelInterface {
        override val faqCategoryUiState =
            MutableStateFlow(
                FaqsCategoryUiState(
                    faqsCategoryList = listOf(
                        FaqCategoryData(
                            position = 1,
                            enabled = true,
                            id = 1,
                            title = "_Faq1"
                        )
                    ),
                    searchText = ""
                )
            )

        override fun updateSearchText(newSearchText: String) {}
        override val loading = MutableStateFlow(false)
        override val error = MutableStateFlow(null)
        override fun closeError() {}
    }
}

@HiltViewModel
class FaqsCategoryViewModel @Inject constructor(
    private val faqRepository: FaqRepository,
    private val dioceseRepository: DioceseRepository
) : BaseViewModel(), FaqsCategoryViewModelInterface {

    override var faqCategoryUiState = MutableStateFlow(FaqsCategoryUiState())
        private set

    init {
        updateFaqsCategory()
    }

    override fun updateSearchText(newSearchText: String) {
        val currentSearchText = faqCategoryUiState.value.searchText
        launchWithErrorWrapper {
            if (newSearchText != currentSearchText) {
                faqCategoryUiState.update { it.copy(searchText = newSearchText) }
                updateFaqsCategory()
            }
        }
    }

    private suspend fun getDioceseId(): Int {
        return dioceseRepository.getSavedDiocese().first().id
    }

    private suspend fun getFaqsCategoryList(): List<FaqCategoryData> {
        return faqRepository.getFaqsCategory(getDioceseId())
    }

    private suspend fun getFaqs(): List<FaqData> {
        return faqRepository.getFaqs(
            id = getDioceseId(),
            text = faqCategoryUiState.value.searchText
        )
    }

    private fun updateFaqsCategory() {
        launchWithErrorWrapper(
            showLoading = false,
            showDefaultError = false,
            onError = {
                faqCategoryUiState.update {
                    it.copy(
                        faqsQuestions = emptyList(),
                        showEmpty = true
                    )
                }
            }
        ) {
            val searchText =
                faqCategoryUiState.value.searchText
            if (searchText.isNotBlank()) {
                val faqs = getFaqs()
                faqCategoryUiState.update {
                    it.copy(
                        faqsQuestions = faqs,
                        showEmpty = faqs.isEmpty()
                    )
                }
            } else {
                faqCategoryUiState.update {
                    it.copy(
                        showEmpty = false,
                        faqsCategoryList = getFaqsCategoryList(),
                    )
                }
            }
        }
    }
}

data class FaqsCategoryUiState(
    val faqsCategoryList: List<FaqCategoryData> = emptyList(),
    val faqsQuestions: List<FaqData> = emptyList(),
    var searchText: String = "",
    var showEmpty: Boolean = false
)
*/
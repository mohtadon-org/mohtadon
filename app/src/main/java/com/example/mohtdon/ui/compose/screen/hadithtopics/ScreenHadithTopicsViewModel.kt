package com.example.mohtdon.ui.compose.screen.hadithtopics

import com.example.mohtdon.ui.compose.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScreenHadithTopicsViewModel @Inject constructor(

) : BaseViewModel<HadithTopicsUiState, HadithTopicsScreenUiEffect>(HadithTopicsUiState()),
    HadithTopicsScreenInteraction {

    init {
        getData()
    }

    override fun getData() {
        iState.update {
            it.copy(
                topics = listOf(
                    HadithTopicItem(1, "الصلاة"),
                    HadithTopicItem(2, "الصوم"),
                    HadithTopicItem(3, "الزكاة"),
                    HadithTopicItem(4, "الرزق"),
                    HadithTopicItem(5, "الحزن"),
                    HadithTopicItem(6, "معاملات المسلم"),
                    HadithTopicItem(7, "الجهاد"),
                    HadithTopicItem(8, "أذكار الآذان"),
                    HadithTopicItem(9, "أذكار بعد الصلاة"),
                    HadithTopicItem(10, "أذكار نزول المطر")
                )
            )
        }
    }

    override fun onClickBack() {

    }

    override fun onClickTopic(topicId: Int) {
        sendUiEffect(HadithTopicsScreenUiEffect.NavigateToTopic(topicId))
    }

    override fun onSearchValueChange(value: String) {
        iState.update {
            it.copy(
                searchValue = value,
            )
        }
    }

    override fun onToggleSearch() {
        iState.update {
            it.copy(
                searchValue = it.searchValue,
                isSearchVisible = !it.isSearchVisible
            )
        }
    }

    override fun onClickSearch(searchValue: String) {
        //TODO Search with the value
    }
}
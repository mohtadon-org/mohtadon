package com.example.straterproject.ui.viewModels

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.dataSource.repository.CoordinatesPrefRepositoryImp
import com.example.domain.models.todayPrayerTimes.TodayPrayerTimes
import com.example.domain.usecases.GetTodayPrayerTimesUseCase
import com.example.straterproject.ui.base.BaseViewModel
import com.example.straterproject.ui.fragments.home.HomeUIEvent
import com.example.straterproject.ui.fragments.home.HomeUiState
import com.example.straterproject.utilities.Event
import com.example.straterproject.utilities.LATITUDE
import com.example.straterproject.utilities.LONGITUDE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTodayPrayerTimesUseCase: GetTodayPrayerTimesUseCase ,
//    @ApplicationContext context: Context
    private val sharedPreferences: SharedPreferences

) : BaseViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    private val _homeUiEvent = MutableStateFlow<Event<HomeUIEvent?>>(Event(null))
    val homeUIEvent = _homeUiEvent.asStateFlow()

    lateinit var coordinatesPrefRepository: CoordinatesPrefRepositoryImp

    init {
        _homeUiState.update {
            it.copy(isLoading = true)
        }

        val latitude = sharedPreferences.getString(LATITUDE, "0.0" )?.toDouble()
        val longitude = sharedPreferences.getString(LONGITUDE,"0.0")?.toDouble()


        getTodayPrayerTimes()
    }

    fun getCurrentTime() {
//        val time = System.currentTimeMillis()
    }

    fun getTheNextSalahName() {

    }

    fun getTheNextSalahTime() {

    }

    fun getCurrentLocation() {

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentDate(): String {
        return LocalDate.now().toString()
    }

    private fun getTodayPrayerTimes(): TodayPrayerTimes? {
        var todayPrayerTimes: TodayPrayerTimes? = null
        viewModelScope.launch {
            async {
                val todayPrayerTimes = getTodayPrayerTimesUseCase("", 0.0, 0.0)
            }.await()
        }

        return todayPrayerTimes
    }
}
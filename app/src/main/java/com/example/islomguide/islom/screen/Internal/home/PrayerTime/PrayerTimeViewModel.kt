package com.example.islomguide.islom.screen.Internal.home.PrayerTime

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.islomguide.core.data.repository.PrayerTimesRepository
import com.example.islomguide.core.data.model.Timings
import retrofit2.HttpException
import java.io.IOException
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.islomguide.R
import com.example.islomguide.core.data.repository.UserLocationRepository
import com.example.islomguide.core.main.Routes.FeatureRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


sealed interface PrayerTimeUiState {
    data class Success(val text: Timings?) : PrayerTimeUiState
    object Error : PrayerTimeUiState
    object Loading : PrayerTimeUiState
}

@RequiresApi(Build.VERSION_CODES.O)
class PrayerTimeViewModel(
    private val prayerTimesRepository: PrayerTimesRepository,
    private val userLocationRepository: UserLocationRepository
) : ViewModel() {

    var prayerTimeUiState: PrayerTimeUiState by mutableStateOf(PrayerTimeUiState.Loading)
        private set

    var currentTime: String by mutableStateOf("")
    var currentTimeToNextPrayer : String by mutableStateOf("")
    var selectedCountry : String by mutableStateOf("")
    var selectedCity: String by mutableStateOf("")

    init{
        viewModelScope.launch {
            userLocationRepository.getCity.collect{
                selectedCity = it
            }
            userLocationRepository.getCountry.collect{
                selectedCountry = it
            }
            getCurrentDateAndPrayerTimes()
        }
    }

    @SuppressLint("NewApi")
    var todayDate = LocalDate.now()

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentDateAndPrayerTimes() {
        viewModelScope.launch {
            try {
                val dateString: String = todayDate.toString()
                if (todayDate != null) {
                    // 2. Если текущая дата получена, отправляем запрос для времени намаза
                    getPrayerTimes(getCity(), getCountry(), dateString)
                } else {
                    // Если дата не получена
                    prayerTimeUiState = PrayerTimeUiState.Error
                }
            } catch (e: Exception) {
                prayerTimeUiState = PrayerTimeUiState.Error
            }
        }
    }

    private fun getPrayerTimes(city: String, country: String, date: String) {
        viewModelScope.launch {
            prayerTimeUiState = try {
                PrayerTimeUiState.Success(prayerTimesRepository.getPrayerTimes(city,country,date))
            } catch (e: IOException) {
                Log.e("PrayerTimeViewModel", "IOException: ${e.message}")
                PrayerTimeUiState.Error
            } catch (e: HttpException) {
                Log.e("PrayerTimeViewModel", "HttpException: ${e.message}")
                PrayerTimeUiState.Error
            }
        }
    }

    fun setCountry(context: Context, city: String){
        val countries = context.resources.getStringArray(R.array.countries)
        val cities = context.resources.getStringArray(R.array.cities)
        val index = cities.indexOf(city)

        val country = if (index != -1 && index < countries.size) {
            countries[index]
        } else {
            ""
        }
         viewModelScope.launch {
            if (country != null) {
                userLocationRepository.saveCountryPreferences(country)
                selectedCountry = country
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentTime(context: Context): String {
        val zoneId = ZoneId.of(getZone(context))
        val currentTime = ZonedDateTime.now(zoneId)
        return currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
    }

    fun setCity(city: String) {
        viewModelScope.launch {
            userLocationRepository.saveCityPreferences(city)
            selectedCity = city
        }
    }

    fun getCity(): String{
        return selectedCity
    }

    fun getCountry(): String{
        return selectedCountry
    }

    // Получаем временную зону по выбранному городу
    fun getZone(context: Context): String {
        return getZoneByCity(selectedCity, context)
    }

    fun getZoneByCity(city: String,context: Context): String {
        val cities = context.resources.getStringArray(R.array.cities)
        val zones = context.resources.getStringArray(R.array.zones)
        val cityTimeZones = cities.zip(zones).toMap()
        // Возвращаем временную зону для города или "UTC" если город не найден
        return cityTimeZones[city] ?: "UTC"
    }


    @SuppressLint("NewApi")
    fun getCurrentPrayer(context: Context, timings: Timings?): String {
        if (timings == null) return "Unknown"
        val currentTime = LocalTime.parse(getCurrentTime(context))
        return when {
            currentTime.isBefore(LocalTime.parse(timings.Fajr)) -> "Fajr"
            currentTime.isBefore(LocalTime.parse(timings.Dhuhr)) -> "Zuhr"
            currentTime.isBefore(LocalTime.parse(timings.Asr)) -> "Asr"
            currentTime.isBefore(LocalTime.parse(timings.Maghrib)) -> "Maghrib"
            currentTime.isBefore(LocalTime.parse(timings.Isha)) -> "Isha"
            else -> "Sunset"
        }
    }

    fun getPrayerGraph(context: Context,timings: Timings?): FeatureRoutes{
        return when(getCurrentPrayer(context, timings)){
            "Fajr" -> FeatureRoutes.PR_Fajr
            "Zuhr" -> FeatureRoutes.PR_Zuhr
            "Asr" -> FeatureRoutes.PR_Asr
            "Maghrib" -> FeatureRoutes.PR_Magrib
            "Isha" -> FeatureRoutes.PR_Isha
            else -> {FeatureRoutes.PR_Fajr}
        }
    }
    @SuppressLint("NewApi")
    fun getNextDay(num : Long){
        val nextDay = todayDate.plusDays(num)
        todayDate = nextDay
    }

    @SuppressLint("NewApi")
    fun getYesterday(num: Long){
        val perviousDay = todayDate.minusDays(num)
        todayDate = perviousDay
    }

    @SuppressLint("NewApi", "DefaultLocale")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getTimeToNextPrayer(context: Context, timings: Timings?): String {
       if (timings == null) return "Unknown"
        val currentTime = LocalTime.parse(getCurrentTime(context))
        val prayerTimes = listOf(
            LocalTime.parse(timings.Fajr),
            LocalTime.parse(timings.Dhuhr),
            LocalTime.parse(timings.Asr),
            LocalTime.parse(timings.Maghrib),
            LocalTime.parse(timings.Isha)
        )

        val nextPrayerTime = prayerTimes.firstOrNull { it.isAfter(currentTime) } ?: prayerTimes.first()
        val duration = java.time.Duration.between(currentTime, nextPrayerTime)
        return String.format("-%02d:%02d:%02d", duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart())
    }
    @SuppressLint("NewApi")
    fun updateCurrentTime(context: Context) {
        currentTime = getCurrentTime(context)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun updateTimeToNextPrayer(context: Context, timings: Timings?){
        currentTimeToNextPrayer = getTimeToNextPrayer(context,timings)
    }
}

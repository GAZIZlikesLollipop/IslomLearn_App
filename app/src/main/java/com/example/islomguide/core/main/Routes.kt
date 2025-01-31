package com.example.islomguide.core.main

object Routes {

    // Основной граф для базовых экранов
    sealed class BaseGraph(val route: String) {
        object Home : BaseGraph("home")
        object Education : BaseGraph("education")
        object Practice : BaseGraph("practice")
        object Setting : BaseGraph("setting")
        object Welcome : BaseGraph("welcome")
    }

    // Вложенные графы, которые расширяют основной граф
    sealed class InternalGraph(val route: String) {
        object Book : InternalGraph("book")
        object Dua : InternalGraph("dua")
        object Tasbeh : InternalGraph("tasbeh")
        object PrayerTime : InternalGraph("prayer_time")
        object QiblaLocation : InternalGraph("qibla_location")
        object Mosque : InternalGraph("mosque")
        object Calendar : InternalGraph("calendar")
        object PrayerRead : InternalGraph("prayer_read")
        object Zicry : InternalGraph("zicry")
        object PrayerTracker : InternalGraph("prayer_tracker")
    }

    // Графы, относящиеся к конкретным функциям и действиям
    sealed class FeatureRoutes(val route: String) {
        object PR_Fajr : FeatureRoutes("pr_fajr")
        object PR_Zuhr : FeatureRoutes("pr_zuhr")
        object PR_Asr : FeatureRoutes("pr_asr")
        object PR_Magrib : FeatureRoutes("pr_magrib")
        object PR_Isha : FeatureRoutes("pr_isha")
        object B_Juz : FeatureRoutes("b_juz")
        object B_Bookmarks : FeatureRoutes("b_bookmarks")
        object B_BookDT : FeatureRoutes("b_book_dt")
        object B_JuzDT : FeatureRoutes("b_juz_dt")
        object B_BMDT : FeatureRoutes("b_bm_dt")
    }
}


package com.example.islomguide.core.main

object Routes {
    enum class BaseGraph{
        Home,
        Education,
        Practice,
        Setting
    }
    enum class InternalGraph{
        Quran,
        Dua,
        Tasbeh,
        Prayer_Time,
        Qibla_Location,
        Mosque,
        Calendar,
        Prayer_Read,
        Islom_Base_Guide,
        Zicry,
        Prayer_Tracker,
    }
    enum class FeatureRoutes{
        PR_Fajr,
        PR_Zuhr,
        PR_Asr,
        PR_Magrib,
        PR_Isha
    }
}
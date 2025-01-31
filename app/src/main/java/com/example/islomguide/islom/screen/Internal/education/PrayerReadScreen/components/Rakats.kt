package com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.components

import android.content.Context
import androidx.compose.foundation.lazy.LazyListScope
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.core.ui_kit.TextComponents.ArticleHeader
import com.example.islomguide.core.ui_kit.TextComponents.ArticleParagraph
import com.example.islomguide.core.ui_kit.TextComponents.ArticleSubheader


fun LazyListScope.FirstRakatMen(
    navController: NavController,
    context: Context,
    action: String,
    special_fajr: Array<String>,
    special_zuhr : Array<String>,
    special_asr: Array<String>,
    special_maghrib: Array<String>,
    special_isha: Array<String>
){
    val sections = context.resources.getStringArray(R.array.prayer_sections)
    val men_content = context.resources.getStringArray(R.array.prayer_men_content)

    item{ ArticleHeader(chooseContent(navController,special_fajr[0], special_zuhr[0], special_asr[0],special_maghrib[0], special_isha[0])) }
    item{ ArticleParagraph(chooseContent(navController, special_fajr[2], special_zuhr[2], special_asr[2],special_maghrib[2], special_isha[2])) }
    item{ ArticleHeader(action)}
    item{ ArticleHeader(sections[0]) }
    item{ ArticleSubheader(sections[1])
        ArticleParagraph(men_content[0]) }
    item{ ArticleSubheader(sections[2])
        ArticleParagraph(men_content[1]) }
    item{ ArticleSubheader(sections[3])
        ArticleParagraph(men_content[2]) }
    item{ ArticleSubheader(sections[4])
        ArticleParagraph(men_content[3]) }
    item{ ArticleSubheader(sections[5])
        ArticleParagraph(men_content[4]) }
    item{ ArticleSubheader(sections[6])
        ArticleParagraph(men_content[5]) }
    item{ ArticleSubheader(sections[7])
        ArticleParagraph(men_content[6]) }
    item{ ArticleSubheader(sections[8])
        ArticleParagraph(men_content[7]) }
    item{ ArticleSubheader(sections[9])
        ArticleParagraph(men_content[8]) }
}

fun LazyListScope.FirstRakatWomen(
    navController: NavController,
    context: Context,
    action: String,
    special_fajr: Array<String>,
    special_zuhr : Array<String>,
    special_asr: Array<String>,
    special_maghrib: Array<String>,
    special_isha: Array<String>
){
    val sections = context.resources.getStringArray(R.array.prayer_sections)
    val women_content = context.resources.getStringArray(R.array.prayer_women_content)
    item{ ArticleHeader(chooseContent(navController,special_fajr[1], special_zuhr[1], special_asr[1],special_maghrib[1], special_isha[1])) }
    item{ ArticleParagraph(chooseContent(navController, special_fajr[2], special_zuhr[2], special_asr[2],special_maghrib[2], special_isha[2])) }
    item{ ArticleHeader(action)}
    item{ ArticleHeader(sections[0]) }
    item{ ArticleSubheader(sections[1])
        ArticleParagraph(women_content[0]) }
    item{ ArticleSubheader(sections[2])
        ArticleParagraph(women_content[1]) }
    item{ ArticleSubheader(sections[3])
        ArticleParagraph(women_content[2]) }
    item{ ArticleSubheader(sections[4])
        ArticleParagraph(women_content[3]) }
    item{ ArticleSubheader(sections[5])
        ArticleParagraph(women_content[4]) }
    item{ ArticleSubheader(sections[6])
        ArticleParagraph(women_content[5]) }
    item{ ArticleSubheader(sections[7])
        ArticleParagraph(women_content[6]) }
    item{ ArticleSubheader(sections[8])
        ArticleParagraph(women_content[7]) }
    item{ ArticleSubheader(sections[9])
        ArticleParagraph(women_content[8]) }
}

fun LazyListScope.NotFirstRakatMen(context: Context,action: String){

    val sections = context.resources.getStringArray(R.array.prayer_sections)
    val men_content = context.resources.getStringArray(R.array.prayer_men_content)

    item{ ArticleHeader(action)}
    item{ ArticleHeader(sections[0]) }
    item{ ArticleSubheader(sections[1])
        ArticleParagraph(men_content[0]) }
    item{ ArticleSubheader(sections[2])
        ArticleParagraph(men_content[1]) }
    item{ ArticleSubheader(sections[3])
        ArticleParagraph(men_content[2]) }
    item{ ArticleSubheader(sections[4])
        ArticleParagraph(men_content[3]) }
    item{ ArticleSubheader(sections[5])
        ArticleParagraph(men_content[4]) }
    item{ ArticleSubheader(sections[6])
        ArticleParagraph(men_content[5]) }
    item{ ArticleSubheader(sections[7])
        ArticleParagraph(men_content[6]) }
    item{ ArticleSubheader(sections[8])
        ArticleParagraph(men_content[7]) }
    item{ ArticleSubheader(sections[9])
        ArticleParagraph(men_content[8]) }
}

fun LazyListScope.NotFirstRakatWomen(context: Context,action: String){
    val sections = context.resources.getStringArray(R.array.prayer_sections)
    val women_content = context.resources.getStringArray(R.array.prayer_women_content)
    item{ ArticleHeader(action)}
    item{ ArticleHeader(sections[0]) }
    item{ ArticleSubheader(sections[1])
        ArticleParagraph(women_content[0]) }
    item{ ArticleSubheader(sections[2])
        ArticleParagraph(women_content[1]) }
    item{ ArticleSubheader(sections[3])
        ArticleParagraph(women_content[2]) }
    item{ ArticleSubheader(sections[4])
        ArticleParagraph(women_content[3]) }
    item{ ArticleSubheader(sections[5])
        ArticleParagraph(women_content[4]) }
    item{ ArticleSubheader(sections[6])
        ArticleParagraph(women_content[5]) }
    item{ ArticleSubheader(sections[7])
        ArticleParagraph(women_content[6]) }
    item{ ArticleSubheader(sections[8])
        ArticleParagraph(women_content[7]) }
    item{ ArticleSubheader(sections[9])
        ArticleParagraph(women_content[8]) }
}

fun LazyListScope.SecondRakatMen(context: Context){
    val sections = context.resources.getStringArray(R.array.prayer_sections)
    val men_content = context.resources.getStringArray(R.array.prayer_men_content)
    item{ ArticleHeader(sections[10])}
    item{ ArticleSubheader(sections[11])
        ArticleParagraph(men_content[9])}
    item{ ArticleSubheader(sections[12])
        ArticleParagraph(men_content[10])}
    item{ ArticleSubheader(sections[13])
        ArticleParagraph(men_content[11])}
    item{ ArticleSubheader(sections[14])
        ArticleParagraph(men_content[12])}
    item{ ArticleSubheader(sections[15])
        ArticleParagraph(men_content[13])}
    item{ ArticleSubheader(sections[16])
        ArticleParagraph(men_content[14])}
    item{ ArticleSubheader(sections[17])
        ArticleParagraph(men_content[15])}
    item{ ArticleSubheader(sections[18])
        ArticleParagraph(men_content[16])}
}
fun LazyListScope.SecondRakatWomen(context: Context){
    val sections = context.resources.getStringArray(R.array.prayer_sections)
    val women_content = context.resources.getStringArray(R.array.prayer_women_content)
    item{ ArticleHeader(sections[10])}
    item{ ArticleSubheader(sections[11])
        ArticleParagraph(women_content[9])}
    item{ ArticleSubheader(sections[12])
        ArticleParagraph(women_content[10])}
    item{ ArticleSubheader(sections[13])
        ArticleParagraph(women_content[11])}
    item{ ArticleSubheader(sections[14])
        ArticleParagraph(women_content[12])}
    item{ ArticleSubheader(sections[15])
        ArticleParagraph(women_content[13])}
    item{ ArticleSubheader(sections[16])
        ArticleParagraph(women_content[14])}
    item{ ArticleSubheader(sections[17])
        ArticleParagraph(women_content[15])}
    item{ ArticleSubheader(sections[18])
        ArticleParagraph(women_content[16])}
}
fun LazyListScope.ThirdRakatMen(context: Context){
    val sections = context.resources.getStringArray(R.array.prayer_sections)
    val men_content = context.resources.getStringArray(R.array.prayer_men_content)
    item{ ArticleHeader(sections[19])}
    item{ ArticleSubheader(sections[20])
        ArticleParagraph(men_content[17])}
    item{ ArticleSubheader(sections[21])
        ArticleParagraph(men_content[18])}
    item{ ArticleSubheader(sections[22])
        ArticleParagraph(men_content[19])}
    item{ ArticleSubheader(sections[23])
        ArticleParagraph(men_content[20])}
    item{ ArticleSubheader(sections[24])
        ArticleParagraph(men_content[21])}
    item{ ArticleSubheader(sections[25])
        ArticleParagraph(men_content[22])}
    item{ ArticleSubheader(sections[26])
        ArticleParagraph(men_content[23])}
}

fun LazyListScope.ThirdRakatWomen(context: Context){

    val sections = context.resources.getStringArray(R.array.prayer_sections)
    val women_content = context.resources.getStringArray(R.array.prayer_women_content)

    item{ ArticleHeader(sections[19])}
    item{ ArticleSubheader(sections[20])
        ArticleParagraph(women_content[17])}
    item{ ArticleSubheader(sections[21])
        ArticleParagraph(women_content[18])}
    item{ ArticleSubheader(sections[22])
        ArticleParagraph(women_content[19])}
    item{ ArticleSubheader(sections[23])
        ArticleParagraph(women_content[20])}
    item{ ArticleSubheader(sections[24])
        ArticleParagraph(women_content[21])}
    item{ ArticleSubheader(sections[25])
        ArticleParagraph(women_content[22])}
    item{ ArticleSubheader(sections[26])
        ArticleParagraph(women_content[23])}
}
fun LazyListScope.FourthRakatMen(context: Context){

    val sections = context.resources.getStringArray(R.array.prayer_sections)
    val men_content = context.resources.getStringArray(R.array.prayer_men_content)

    item{ ArticleHeader(sections[27])}
    item{ ArticleSubheader(sections[27])
        ArticleParagraph(men_content[24])}
    item{ ArticleSubheader(sections[28])
        ArticleParagraph(men_content[25])}
    item{ ArticleSubheader(sections[29])
        ArticleParagraph(men_content[26])}
    item{ ArticleSubheader(sections[30])
        ArticleParagraph(men_content[27])}
    item{ ArticleSubheader(sections[31])
        ArticleParagraph(men_content[28])}
    item{ ArticleSubheader(sections[32])
        ArticleParagraph(men_content[29])}
    item{ ArticleSubheader(sections[33])
        ArticleParagraph(men_content[30])}
    item{ ArticleSubheader(sections[34])
        ArticleParagraph(men_content[31])}
    item{ ArticleSubheader(sections[35])
        ArticleParagraph(men_content[32])}
    item{ ArticleSubheader(sections[36])
        ArticleParagraph(men_content[33])}
}
fun LazyListScope.FourthRakatWomen(context: Context){

    val sections = context.resources.getStringArray(R.array.prayer_sections)
    val women_content = context.resources.getStringArray(R.array.prayer_women_content)

    item{ ArticleHeader(sections[27])}
    item{ ArticleSubheader(sections[27])
        ArticleParagraph(women_content[24])}
    item{ ArticleSubheader(sections[28])
        ArticleParagraph(women_content[25])}
    item{ ArticleSubheader(sections[29])
        ArticleParagraph(women_content[26])}
    item{ ArticleSubheader(sections[30])
        ArticleParagraph(women_content[27])}
    item{ ArticleSubheader(sections[31])
        ArticleParagraph(women_content[28])}
    item{ ArticleSubheader(sections[32])
        ArticleParagraph(women_content[29])}
    item{ ArticleSubheader(sections[33])
        ArticleParagraph(women_content[30])}
    item{ ArticleSubheader(sections[34])
        ArticleParagraph(women_content[31])}
    item{ ArticleSubheader(sections[35])
        ArticleParagraph(women_content[32])}
    item{ ArticleSubheader(sections[36])
        ArticleParagraph(women_content[33])}
}
package com.twofasapp.locale

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import kotlin.math.abs
import kotlin.math.sign

object TwLocale {
    private val timeUnits = TimeUnit.values().reversed()

    val strings: Strings
        @Composable
        get() = Strings(LocalContext.current)

    val links: Links = Links()

    @Composable
    fun formatDuration(millis: Long): String {
        val diff = System.currentTimeMillis() - millis
        val diffSign = diff.sign
        val diffAbs = abs(diff)

        timeUnits.forEach { timeUnit ->
            val diffInUnit = diffAbs / timeUnit.millis
            val diffInUnitModulo = diffAbs % timeUnit.millis
            val timeUnitHalf = timeUnit.millis / 2

            if (diffInUnit >= 1 && diffInUnitModulo >= timeUnitHalf) {
                return format(LocalContext.current, diffInUnit + 1, diffSign, timeUnit)
            }

            if (diffInUnit >= 1 && diffInUnitModulo < timeUnitHalf) {
                return format(LocalContext.current, diffInUnit, diffSign, timeUnit)
            }
        }

        return format(LocalContext.current, diff, diffSign, timeUnits.last())
    }

    private fun format(context: Context, quantity: Long, sign: Int, timeUnit: TimeUnit): String {
        return context.resources.getQuantityString(
            if (sign > 0) timeUnit.pastStringRes else timeUnit.pastStringRes, // TODO: Handle future values
            quantity.toInt(),
            quantity.toInt(),
        )
    }

    enum class TimeUnit(val millis: Long, val pastStringRes: Int) {
        Second(1_000L, R.plurals.past_duration_seconds),
        Minute(60 * 1_000L, R.plurals.past_duration_minutes),
        Hour(60 * 60 * 1_000L, R.plurals.past_duration_hours),
        Day(24 * 60 * 60 * 1_000L, R.plurals.past_duration_days),
        Week(7 * 24 * 60 * 60 * 1_000L, R.plurals.past_duration_weeks),
        Month(4 * 7 * 24 * 60 * 60 * 1_000L, R.plurals.past_duration_months),
    }
}
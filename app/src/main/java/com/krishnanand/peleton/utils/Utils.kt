package com.krishnanand.peleton.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.krishnanand.peleton.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Utils {

    @JvmStatic
    @BindingAdapter("showTempInCelcius", "temperaturePrefix")
    fun showTempInCelcius(textView:TextView, number: Float, temperaturePrefix: String) {
        textView.text = "$temperaturePrefix : ${number.toString()} C"
    }

    @JvmStatic
    @BindingAdapter("convertDateStringToDay")
    fun convertIsoDateStringToFormattedDate(textView: TextView, applicableDate: String) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val applicableLocalDate: LocalDate = LocalDate.parse(applicableDate, DateTimeFormatter.ISO_DATE)
            val localDateAsString = applicableLocalDate.format(DateTimeFormatter.ofPattern("EEE, MMM dd"))
            val todaysDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
            textView.text = if (todaysDate == applicableDate) textView.resources.getText(R.string.today_string) else localDateAsString
        } else {
            textView.text = applicableDate
        }
    }
}
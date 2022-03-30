package com.example.casemovieapp.common

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.casemovieapp.R
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("urlToImage")
fun urlToImage(view: ImageView, imgUrl: String?) {
    if (!imgUrl.isNullOrEmpty()) {
        Glide.with(view).load(Constants.IMAGE_BASE_URL + imgUrl ).into(view)
    }

}

@BindingAdapter("voteAvarage")
fun voteAvarage(view: TextView, avarage: Double) {
    view.text = avarage.toString()
}

@BindingAdapter("formattedDate")
fun formattedDate(view: TextView, date: String?) {
    if (!date.isNullOrEmpty()) {
    val parser = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT)
    val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)
    view.text = formatter.format(parser.parse(date))
    }else view.text = ""
}
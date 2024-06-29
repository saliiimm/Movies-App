package com.example.moviesapp.utils

import android.content.Context
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.moviesapp.models.ProductionCountry
import com.squareup.picasso.Picasso
import java.util.Locale

fun displayCountryFlags(context: Context, countries: List<ProductionCountry>, container: LinearLayout) {
    container.removeAllViews()
    for (country in countries) {
        val flagImageView = ImageView(context)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(8, 0, 8, 0)
        }
        flagImageView.layoutParams = layoutParams

        val flagUrl = "https://flagcdn.com/64x48/${country.iso_3166_1.lowercase(Locale.ROOT)}.png"
        Picasso.get().load(flagUrl).into(flagImageView)
        container.addView(flagImageView)
    }
}

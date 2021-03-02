package com.example.androiddevchallenge.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Puppy(
    val name: Int,
    val description: String,
    @DrawableRes val img: Int,
    val sex: Int // 0 = male, 1 = female
): Parcelable
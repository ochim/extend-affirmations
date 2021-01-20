package com.example.affirmations.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Affirmation (
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
): Parcelable
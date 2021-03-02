package com.example.androiddevchallenge.data

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Puppy

fun puppies(): List<Puppy> {
    return listOf(
        Puppy(
            R.string.hachico,
            description = LoremIpsum(20).values.joinToString(),
            img = R.drawable.puppy1,
            sex = 0
        ),
        Puppy(
            R.string.hachico,
            description = LoremIpsum(20).values.joinToString(),
            img = R.drawable.puppy2,
            sex = 0
        ),
        Puppy(
            R.string.hachico,
            description = LoremIpsum(20).values.joinToString(),
            img = R.drawable.puppy3,
            sex = 1
        ),
        Puppy(
            R.string.hachico,
            description = LoremIpsum(20).values.joinToString(),
            img = R.drawable.puppy4,
            sex = 1
        ),
        Puppy(
            R.string.hachico,
            description = LoremIpsum(20).values.joinToString(),
            img = R.drawable.puppy5,
            sex = 1
        ),
        Puppy(
            R.string.hachico,
            description = LoremIpsum(20).values.joinToString(),
            img = R.drawable.puppy6,
            sex = 0
        ),
        Puppy(
            R.string.hachico,
            description = LoremIpsum(20).values.joinToString(),
            img = R.drawable.puppy7,
            sex = 1
        ),
        Puppy(
            R.string.hachico,
            description = LoremIpsum(20).values.joinToString(),
            img = R.drawable.puppy8,
            sex = 0
        ),
        Puppy(
            R.string.hachico,
            description = LoremIpsum(20).values.joinToString(),
            img = R.drawable.puppy9,
            sex = 0
        ),
    )

}
/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

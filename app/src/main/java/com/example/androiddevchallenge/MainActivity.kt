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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.androiddevchallenge.data.puppies
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val onToggleTheme = remember { mutableStateOf(false) }

            MyTheme(darkTheme = onToggleTheme.value) {
                MyApp {
                    AppNavigator() {
                        onToggleTheme.value = !onToggleTheme.value
                        onToggleTheme.value
                    }
                }
            }
        }
    }

}

@Composable
fun AppNavigator(onToggleTheme: () -> Boolean) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "puppiesView",
        builder = {
            composable("puppiesView") {
                PuppyListView(
                    navController,
                    puppies = puppies(),
                    onToggleTheme
                )
            }
            composable(
                "puppuDetailsView/{puppyId}",
                arguments = listOf(navArgument("puppyId") {
                    type = NavType.IntType
                })
            ) { backStackEntry ->
                backStackEntry.arguments?.getInt("puppyId")?.let {
                    PuppyDetailsView(puppy = puppies()[it])
                }
            }
        })
}

// Start building your app here!
@Composable
fun MyApp(content: @Composable () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        content()
    }
}


@Composable
fun PuppyListView(
    navController: NavHostController,
    puppies: List<Puppy>,
    onToggleThemeClick: () -> Boolean
) {
    val onDarkMode = remember { mutableStateOf(false) }

    fun navigateToDetailsView(puppyId: Int) {
        navController.navigate("puppuDetailsView/${puppyId}")
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {


        Row {
            Text(
                text = "Adopt Hachi",
                style = typography.h5,
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = {
                    onDarkMode.value = onToggleThemeClick()
                },
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = if (onDarkMode.value) {
                        "Light"
                    } else {
                        "Dark"
                    }
                )
            }
        }

        Text(
            text = "Your hachi is waiting for you",
            style = typography.caption,
        )

        Spacer(modifier = Modifier.size(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(items = puppies) { index, puppy ->
                PuppyItem(puppy, {
                    navigateToDetailsView(index)
                })
            }
        }
    }
}

@Composable
fun PuppyItem(puppy: Puppy, onClick: () -> Unit) {
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .clickable(onClick = {
                onClick()
            })
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .height(140.dp)
        ) {
            Row(
                modifier = Modifier
                    .height(140.dp)
            ) {
                Image(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(140.dp)
                        .height(140.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                topStart = 16.dp,
                                bottomStart = 16.dp,
                                topEnd = 0.dp,
                                bottomEnd = 0.dp
                            )
                        ),
                    painter = painterResource(puppy.img),
                    contentDescription = puppy.description,
                )

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .height(140.dp),
                    verticalArrangement = Arrangement.spacedBy(3.dp)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = puppy.name),
                            style = typography.h6,
                            modifier = Modifier.weight(1f)
                        )
                        PuppySex(sex = puppy.sex)
                    }

                    Text(
                        text = puppy.description,
                        maxLines = 2,
                        style = typography.body1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Distance()
                }
            }
        }

    }
}

@Composable
fun PuppyDetailsView(puppy: Puppy) {
    Column {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = puppy.img),
                contentDescription = puppy.description,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 260.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(
                            topStart = 40.dp,
                            topEnd = 40.dp,
                            bottomEnd = 0.dp,
                            bottomStart = 0.dp
                        )
                    )
            ) {

                Card(
                    shape = RoundedCornerShape(
                        topEnd = 40.dp,
                        topStart = 40.dp
                    ),
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = puppy.name),
                                style = typography.h6,
                                modifier = Modifier.weight(1f)
                            )
                            Distance()
                        }

                        PuppySex(sex = puppy.sex)

                        Spacer(modifier = Modifier.size(8.dp))

                        Text(
                            text = puppy.description,
                            style = typography.body1,
                        )

                        Spacer(modifier = Modifier.size(16.dp))

                        Button(
                            onClick = { },
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .align(alignment = Alignment.End)
                                .padding(8.dp)
                                .width(120.dp)
                        ) {
                            Text(
                                text = stringResource(
                                    id = R.string.adopt_me
                                ),
                                color = Color.White
                            )
                        }

                    }
                }

            }
        }
    }
}

@Composable
fun PuppySex(sex: Int) {
    val sexString: String = if (sex == 0) {
        stringResource(id = R.string.male)
    } else {
        stringResource(id = R.string.female)
    }

    val textColor = if (sex == 0) {
        colorResource(id = R.color.blue_900)
    } else {
        colorResource(id = R.color.pink_900)
    }

    Text(
        text = sexString,
        color = textColor,
        style = typography.caption
    )
}

@Composable
fun Distance() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .width(24.dp)
                .height(24.dp),
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(2.dp))
        Text(
            text = "1.43 km",
            style = typography.caption
        )
    }
}


@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    val navController = rememberNavController()
    MyTheme {
        MyApp {
            PuppyListView(navController, puppies = puppies()) {
                true
            }
        }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    val navController = rememberNavController()
    MyTheme(darkTheme = true) {
        MyApp {
            PuppyListView(navController, puppies = puppies()) {
                true
            }
        }
    }


}

@Preview("Puppy Preview", widthDp = 360, heightDp = 640)
@Composable
fun PuppyPreview() {
    MyTheme {
        MyApp {
            PuppyDetailsView(puppies()[0])
        }
    }
}

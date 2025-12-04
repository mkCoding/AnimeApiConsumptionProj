package com.example.animeapi.presentation.screens

import android.media.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.animeapi.data.model.DataModel
import com.example.animeapi.data.model.ImagesModel
import com.example.animeapi.data.model.JpgModel
import com.example.animeapi.data.model.TopAnimeModel
import com.example.animeapi.presentation.viewmodel.AnimeTopState

@Composable
fun AnimeTopScreen(
    animeTopState: AnimeTopState
){

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(animeTopState){
            is AnimeTopState.Loading-> {
                CircularProgressIndicator()
            }
            is AnimeTopState.Success->{
                val list = animeTopState.animeTopData.data
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        //.padding(16.dp)
                ) {
                    items(list.orEmpty()){item ->

                        Text("${item.title}", fontSize = 30.sp)

                        Spacer(Modifier.height(16.dp))

                        AsyncImage(
                            model = item.images?.jpg?.image_url,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                               // .height(200.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )

                        HorizontalDivider(
                            Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            thickness = 5.dp,
                            color = Color.Black)
                    }
                }
            }
            is AnimeTopState.Error -> {
                Text("${animeTopState.message}")
            }
            else -> {/*left intentionally blank*/}
        }

    }
}


@Preview(showBackground = true)
@Composable
fun AnimeTopScreenPreview(){
    AnimeTopScreen(
        animeTopState = AnimeTopState.Success(
            animeTopData = TopAnimeModel(
                data = listOf(
                    DataModel(
                        title = "Anime Show 2",
                        images = ImagesModel(JpgModel(image_url = "https://cdn.myanimelist.net/images/anime/1015/138006.jpg"))
                    ),
                    DataModel(
                        title = "Castle Master"
                    ),
                    DataModel(
                        title = "Sparrow"
                    )
                )
            )
        )
    )

}

object PreviewAnimeData {
    val sample = DataModel(
        title = "Anime Show 2"
    )
}
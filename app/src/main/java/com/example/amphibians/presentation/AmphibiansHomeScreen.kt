package com.example.amphibians.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.data.remote.model.Amphibian

@Composable
fun AmphibiansHomeScreen(
    amphibiansNetworkResponse: AmphibiansNetworkResponse,
    retryAction: () -> Unit,
    modifier:Modifier = Modifier
) {
    
    when(amphibiansNetworkResponse){
        is AmphibiansNetworkResponse.Loading -> {
            LoadingScreen(modifier.fillMaxSize())
        }
        is AmphibiansNetworkResponse.Success -> {
            AmphibiansListScreen(modifier,amphibiansNetworkResponse)
        }
        is AmphibiansNetworkResponse.Error -> {
            ErrorScreen(
                retryAction,
                modifier = modifier
            )
        }
    }


}

@Composable
fun ErrorScreen(
    retryAction : ()-> Unit,
    modifier: Modifier = Modifier
) {
//    TODO("Not yet implemented")
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Error,
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Spacer(Modifier.height(10.dp))
        Button(onClick = retryAction ) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

@Composable
fun AmphibiansListScreen(
    modifier: Modifier,
    amphibiansNetworkResponse: AmphibiansNetworkResponse.Success
) {
//    TODO("Not yet implemented")

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(300.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(amphibiansNetworkResponse.amphibians){
            AmphibianCard(modifier = Modifier.padding(5.dp), amphibian = it)
        }
    }

}

@Composable
private fun AmphibianCard(
    modifier: Modifier = Modifier,
    amphibian: Amphibian
) {
    Card(
        modifier = modifier.width(250.dp),
        elevation = CardDefaults.cardElevation(5.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.aspectRatio(1.5f)

        ) {

            Text(
                text = ("${amphibian.name} (${amphibian.type}) "),
                style = MaterialTheme.typography.headlineSmall,
//                modifier = Modifier.padding(5.dp , end = 0.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box{
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(amphibian.imgSrc)
                        .crossfade(true)
                        .build(),
                    error = painterResource(id = R.drawable.ic_broken_image),
                    placeholder = painterResource(id = R.drawable.loading_img),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
//            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = amphibian.description,
                modifier= Modifier.padding(10.dp)
            )

        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
//    TODO("Not yet implemented")
    Image(
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = null,
        modifier = modifier
        )
}

@Preview(showSystemUi = true,)
@Composable
private fun LoadingScreenPreview() {
    (Modifier.fillMaxSize())
}

@Preview(showSystemUi = true,)
@Composable
private fun ErrorScreenPreview() {
    ErrorScreen({},Modifier.fillMaxSize())
}

@Preview(showSystemUi = true)
@Composable
private fun AmphibianCardPrev() {
    AmphibianCard(
        amphibian = Amphibian(
            "Great Basin Spadefoot",
            "Toad",
             "This toad spends most of its life underground due to the arid desert conditions in which it lives. Spadefoot toads earn the name because of their hind legs which are wedged to aid in digging. They are typically grey, green, or brown with dark spots.",
            "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png"
    )
    )
}


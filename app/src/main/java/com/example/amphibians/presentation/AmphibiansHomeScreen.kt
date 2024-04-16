package com.example.amphibians.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.data.remote.model.Amphibian


@Composable
fun AmphibianApp() {

    Scaffold(

        topBar = { AmphibiansTopBar() }
    ) {it ->

        val viewmodel: AmphibiansViewModel = viewModel()
       AmphibiansHomeScreen(
           amphibiansNetworkResponse = viewmodel.amphibianNetworkResponse,
           retryAction = { viewmodel.getAmphibiansData() },
           contentPadding = it
       )
    }
}

@Composable
fun AmphibiansHomeScreen(
    amphibiansNetworkResponse: AmphibiansNetworkResponse,
    retryAction: () -> Unit,
    modifier:Modifier = Modifier,
    contentPadding: PaddingValues
) {
    
    when(amphibiansNetworkResponse){
        is AmphibiansNetworkResponse.Loading -> {
            LoadingScreen(contentPadding,modifier.fillMaxSize())
        }
        is AmphibiansNetworkResponse.Success -> {
            AmphibiansListScreen(
                modifier,
                amphibiansNetworkResponse,
                contentPadding = contentPadding
            )
        }
        is AmphibiansNetworkResponse.Error -> {
            ErrorScreen(
                contentPadding,
                retryAction,
                modifier = modifier.fillMaxSize()
            )
        }
    }


}

@Composable
fun ErrorScreen(
    contentPadding: PaddingValues,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
//    TODO("Not yet implemented")
    Column(
        modifier = modifier.padding(contentPadding),
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
    amphibiansNetworkResponse: AmphibiansNetworkResponse.Success,
    contentPadding: PaddingValues = PaddingValues(10.dp)
) {
//    TODO("Not yet implemented")

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(300.dp),
        contentPadding = contentPadding
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansTopBar() {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = MaterialTheme.colorScheme.primaryContainer)
    )
}

@Composable
fun LoadingScreen(contentPadding: PaddingValues, modifier: Modifier = Modifier) {
//    TODO("Not yet implemented")
    Image(
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = null,
        modifier = modifier.padding(contentPadding)
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
    ErrorScreen(PaddingValues(5.dp), {}, Modifier.fillMaxSize())
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


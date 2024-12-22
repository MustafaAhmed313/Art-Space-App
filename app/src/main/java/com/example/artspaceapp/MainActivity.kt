package com.example.artspaceapp

import android.media.Image
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    ArtSpaceApp(modifier = Modifier)
                }
            }
        }
    }
}




@Composable
fun ArtSpaceApp(modifier: Modifier) {

    var index by remember {
        mutableIntStateOf(0)
    };

    val arts = listOf<Art>(
        Art(
            title = stringResource(id = R.string.image1_title),
            artist = stringResource(id = R.string.image1_artist),
            year = stringResource(id = R.string.image1_year),
            painter = painterResource(id = R.drawable.ecpc)
        ),
        Art(
            title = stringResource(id = R.string.image2_title),
            artist = stringResource(id = R.string.image2_artist),
            year = stringResource(id = R.string.image2_year),
            painter = painterResource(id = R.drawable.techtalent)
        ),
        Art(
            title = stringResource(id = R.string.image3_title),
            artist = stringResource(id = R.string.image3_artist),
            year = stringResource(id = R.string.image3_year),
            painter = painterResource(id = R.drawable.share_eclipses)
        ),
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ArtContent(
            modifier = modifier,
            art = arts[index]
        )

        Row(
            modifier = modifier
                .padding(bottom = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,

        ) {
            Button(
                onClick = {
                    if (index == 0) index = arts.size - 1
                    else index -= 1
                },
                modifier = Modifier.size(150.dp, 40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff525d76),
                    contentColor = Color.White
                )
                ) {
                Text(text = stringResource(id = R.string.previous_btn))
            }
            
            Spacer(modifier = Modifier.size(width = 50.dp, height = 0.dp))

            Button(
                onClick = {
                    index += 1
                    index %= arts.size
                },
                modifier = Modifier.size(150.dp, 40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff525d76),
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(id = R.string.next_btn))
            }
        }
    }
}

@Composable
fun ArtContent(modifier: Modifier, art: Art) {
    Column(
        modifier = modifier
            .padding(bottom = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = modifier
                .shadow(elevation = 5.dp)
                .size(width = 350.dp, height = 400.dp)
                .padding(all = 30.dp)
        ) {

            Image(
                painter = art.painter,
                contentDescription = art.title,
                modifier = modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

        }
        
        Spacer(modifier = Modifier.size(width = 0.dp, height = 80.dp))

        Column(
            modifier = modifier
                .size(width = 350.dp, height = 120.dp)
                .background(color = Color(0xffeceaf1))
                .wrapContentSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = art.title,
                fontSize = 30.sp,
                fontWeight = FontWeight.W300
            )
            Row (modifier = modifier) {
                Text(
                    text = art.artist,
                    fontWeight = FontWeight.Bold
                )
                Text(text = " (${art.year})")
            }
        }
    }
    
}

data class Art(
    val title: String,
    val artist: String,
    val year: String,
    val painter: Painter
)


@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceAppTheme {
        ArtSpaceApp(modifier = Modifier.fillMaxSize())
    }
}
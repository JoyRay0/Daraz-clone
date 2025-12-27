package com.rk_softwares.e_commerce.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.airbnb.lottie.model.content.CircleShape
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rk_softwares.e_commerce.Other.IntentHelper
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.activity.ui.theme.E_commerceTheme
import com.rk_softwares.e_commerce.server.FullProductInfoServer


class Act_product_image : ComponentActivity() {

    private lateinit var fullProductInfoServer: FullProductInfoServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val sku = intent.getStringExtra("sku_") ?: ""

            val list = remember { mutableStateListOf<String>() }
            var imageUrl by remember { mutableStateOf("") }
            var imageCount by remember { mutableIntStateOf(0) }
            val systemUi = rememberSystemUiController()

            systemUi.setStatusBarColor(
                color = Color(0xFF000000),
                darkIcons = false
            )

            systemUi.setNavigationBarColor(
                color = Color(0xFF000000),
                darkIcons = false
            )


            fullProductInfoServer = FullProductInfoServer(this)
            fullProductInfoServer.productImages(sku = sku, onResult = { item ->

                item.images.forEach {
                    list.add(it)
                }

            })

            /*
            list.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.tT7UG7QNOQKvijhtgqq2lAHaE8%3Fpid%3DApi%26ucfimg%3D1&f=1&ipt=2b9104c7222fc496f57011d6bc14daac4cf08239b93c6d60761aa01894f209f6&ipo=images")
            list.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.KYVQ29k3GaAFBFxpHLCr8AHaEf%3Fpid%3DApi%26ucfimg%3D1&f=1&ipt=490de6bb352627ae0ffe4efe8498ff3bf40dae45bbc1f85a74ffc259b6e26eca&ipo=images")
            list.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%2Fid%2FOIP.kKSNTRAx0Zm792CEpN6v4AHaEI%3Fcb%3Ducfimg2%26pid%3DApi%26ucfimg%3D1&f=1&ipt=8ac0acae28c8ec6866e756a0d89b1147eed9387af9ddd31b2e03ed7774831091&ipo=images")
            list.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%2Fid%2FOIP.VdDc3iT3PCrJnnsiThNzGgHaF_%3Fcb%3Ducfimg2%26pid%3DApi%26ucfimg%3D1&f=1&ipt=3a70939cc4110cc58e602ae4dcb9f3929af171596d281e899d91acae58a0839a&ipo=images")
            list.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.2TZsEf6qo40tMJJooGViWQHaE8%3Fpid%3DApi%26ucfimg%3D1&f=1&ipt=831ab342df3670a553b78c6246763e0c46a00041005668cc314ec6812dcca64e&ipo=images")
            list.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%2Fid%2FOIP.lTVZtGbX1MNU5NyNCe93TwHaFj%3Fcb%3Ducfimg2%26pid%3DApi%26ucfimg%3D1&f=1&ipt=f71b1172d8e53f81cd2a4240a0e40cc49f062ad838ad54f3138c451ceb78f986&ipo=images")
            list.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%2Fid%2FOIP.HP3Fy6qfftq8jyeiIbSohAHaHa%3Fcb%3Ducfimg2%26pid%3DApi%26ucfimg%3D1&f=1&ipt=6219d51e311fee64b673e447518325c0345f048a958c1dc461e4d7d4a0093ba3&ipo=images")
            list.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%2Fid%2FOIP.IdDWxWZ7wxxg81Ioy47uwQHaEK%3Fcb%3Ducfimg2%26pid%3DApi%26ucfimg%3D1&f=1&ipt=65ab9c0340dd784fe8849d9528634861b2fbdefbd0b489d927260fa8a10068f6&ipo=images")
            list.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%2Fid%2FOIP.3CuLZGMVt5amN22OmnZbuwHaEo%3Fcb%3Ducfimg2%26pid%3DApi%26ucfimg%3D1&f=1&ipt=7089329477212ed6f68c76cfb7584d9ef04ff141b696d8dc1df2dc6aa008f6db&ipo=images")
            list.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.QDVkZK3rlMqx1qeFHzjcVAHaFj%3Fcb%3Ducfimg2%26pid%3DApi%26ucfimg%3D1&f=1&ipt=274c39eaf9b7e6a871ab39835b468b9b26faea5141dd6d83103d508c03603a26&ipo=images")
             */
            E_commerceTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize().background(color = Color(0xFF000000))) { innerPadding ->

                    Column(

                        modifier = Modifier.fillMaxSize().padding(innerPadding).background(color = Color(0xFF000000))

                    ) {

                        Box(

                            modifier = Modifier.wrapContentWidth().align(Alignment.End).padding(15.dp)

                        ) {

                            Row(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .background(color = Color(0x6D595250), shape = RoundedCornerShape(15.dp))
                                    .padding(7.dp)
                                    .align(Alignment.TopEnd)
                            ) {

                                Text( "${imageCount + 1}",
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFFDEC8C8),
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                )

                                Text("/",
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFFDEC8C8),
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                )

                                Text(list.size.toString(),
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFFDEC8C8),
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                )

                            }//row

                        }//box

                        Box(

                            modifier = Modifier.fillMaxSize().background(color = Color(0xFF000000))

                        ) {

                            Nav(
                                imageClick = { index ->

                                    imageUrl = list[index]
                                    imageCount = index

                                }, image = list,
                                modifier = Modifier.align(Alignment.BottomCenter)
                            )

                            FullImage(
                                image = if (imageUrl.isEmpty() && list.isNotEmpty()){

                                    list[0]

                                } else imageUrl,

                                modifier = Modifier.align(Alignment.TopCenter)
                            )

                        }//box

                    }//column


                }//scaffold
            }
        }
    }
}//class end


@Preview(showBackground = true)
@Composable
fun Nav(imageClick : (Int) -> Unit = {}, image : SnapshotStateList<String> = SnapshotStateList(), modifier: Modifier = Modifier){

    var selectedImage by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier.fillMaxWidth().padding(10.dp).background(color = Color(0xFF000000))
    ) {

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(shape = RoundedCornerShape(20.dp))
                .background(
                    color = Color(0x40FFFFFF).copy(0.15f)
                )
                .border(
                    width = 1.dp,
                    color = Color.White.copy(0.10f),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(3.dp)
                .horizontalScroll(rememberScrollState())
                .align(Alignment.CenterHorizontally)
        ) {

            image.forEachIndexed { index, it ->

                Box(

                    modifier = Modifier
                        .width(65.dp)
                        .height(65.dp)
                        .padding(7.dp)
                        .clip(shape = CircleShape)
                        .border(
                            width = 1.dp,
                            color = if (selectedImage == index) Color(0xFFFF5722) else Color.Transparent,
                            shape = CircleShape)
                        .clickable(
                            interactionSource = null,
                            indication = null
                        ){
                            selectedImage = index
                            imageClick(selectedImage)
                        }

                ) {

                    AsyncImage(model = it,
                        contentDescription = "",
                        placeholder = painterResource(R.drawable.img_loading_daraz),
                        error = painterResource(R.drawable.img_loading_daraz),
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()

                    )

                }//box

            }//list end

        }//row

    }//column

}// fun end

@Preview(showBackground = true)
@Composable
fun FullImage(image : String = "", modifier: Modifier = Modifier){

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(color = Color.White)
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(),
            contentDescription = "",
            placeholder = painterResource(R.drawable.img_loading_daraz),
            error = painterResource(R.drawable.img_loading_daraz),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
            )

    }//box

}//fun end
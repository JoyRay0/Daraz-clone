package com.rk_softwares.e_commerce.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.material.carousel.Arrangement
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.activity.ui.theme.E_commerceTheme
import com.rk_softwares.e_commerce.model.Product
import com.rk_softwares.e_commerce.server.FullProductInfoServer


class Act_BuyNow : ComponentActivity() {

    private lateinit var pruduct : FullProductInfoServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            init()

            val sku = intent.getStringExtra("sku") ?: ""

            val systemUi = rememberSystemUiController()
            val list = remember { mutableStateListOf<Product>() }

            systemUi.setStatusBarColor(color = Color.White, darkIcons = true)
            systemUi.setNavigationBarColor(color = Color.White)



            LaunchedEffect(Unit) {

                pruduct.productImages(sku, onResult = { result ->

                    list.add(Product(

                        title = result.title,
                        price = result.price,
                        rating = result.rating,
                        thumbnail = result.thumbnail,
                        images = result.images,
                        brand = result.brand,
                        stock = result.stock,
                        shippingInformation = result.shippingInformation


                    ))

                })

            }

            
            E_commerceTheme {

                FullScreen(

                    backClick = { finish() },
                    data = list

                )

            }
        }
    }//on create=============================================

    private fun init(){
        
        pruduct = FullProductInfoServer(this)
        
    }

}//class=====================================================


@Preview(showBackground = true)
@Composable
private fun FullScreen(

    backClick: () -> Unit = {},
    data: List<Product> = emptyList()

) {

    Scaffold(

        topBar = {

            Toolbar( backClick = { backClick() } )

        },

        bottomBar = {

            BottomBar()

        },

        modifier = Modifier.fillMaxSize()
        
    ) { innerPadding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())

        ) {

            HorizontalDivider(
                color = Color(0xB2EAB7B7),
                thickness = 1.dp
            )

            Address()

            HorizontalDivider(
                color = Color.Transparent,
                thickness = 3.dp
            )

            val product = data.firstOrNull()

            product?.let {

                ProductImage(
                    rating = product.rating,
                    title = product.title,
                    brand = product.brand,
                    price = product.price,
                    thumbnailImage = product.thumbnail,
                    imageList = product.images,
                    productStock = product.stock

                )

            }

        }//column

    }//scaffold

}//fun end

@Preview(showBackground = true)
@Composable
private fun Toolbar(backClick : () -> Unit = {}) {

    Box(

        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFFFFFFF))

    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp)
                .background(color = Color(0xFFFFFFFF))
                .align(Alignment.TopStart)

        ) {

            IconButton(

                onClick = backClick,
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(shape = CircleShape)
                    .size(35.dp)
                    .align(Alignment.CenterVertically)


            ) {

                Icon( painter = painterResource(R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier
                        .wrapContentWidth()
                        .size(16.dp)
                        .align(Alignment.CenterVertically)

                )

            }

            Spacer(modifier = Modifier.width(7.dp))

            Text("Checkout",
                fontSize = 15.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
            )

        }//row

    }//box
    
}//fun end


@Preview(showBackground = true)
@Composable
private fun BottomBar(totalPrice : Double = 5.5555, payClick : () -> Unit = {}) {

    Box(

        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFFFFFFF))

    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp)

        ) {

            Column(

                modifier = Modifier.wrapContentWidth()

            ) {

                //total price
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.Start)
                        .padding(top = 7.dp, bottom = 3.dp, start = 3.dp, end = 3.dp)
                ) {

                    Text("Total : :",
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.CenterVertically)
                        )

                    Icon( painter = painterResource(R.drawable.ic_dollar),
                        contentDescription = "dollar",
                        tint = Color(0xFFFF5722),
                        modifier = Modifier
                            .wrapContentWidth()
                            .size(17.dp)
                            .align(Alignment.CenterVertically)

                    )

                    Text(totalPrice.toString(),
                        fontSize = 18.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFF5722),
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.CenterVertically)
                    )

                }
                //total price

                Text("VAT included",
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(3.dp)
                        .align(Alignment.Start)
                    )

            }//column

            Spacer(modifier = Modifier.width(7.dp))

            //pay
            Box(

                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(14.dp))
                    .clickable {
                        payClick()
                    }
                    .background(color = Color(0xFFFC6131))
                    .padding(7.dp)
                    .align(Alignment.CenterVertically)


            ) {

                Text("Proceed to Pay",
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(5.dp)
                        .align(Alignment.Center)
                    )

            }
            //pay

        }//row

    }//box

}//fun end


@Preview(showBackground = true)
@Composable
private fun Address(
    name : String = "Rada krishna",
    number : String = "00000000000",
    address : String = "Madhabdi, Narsungdi",
    addressCategory : String = "HOME",
    editClick : () -> Unit = {}
) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(7.dp)

    ) {

        Icon( painter = painterResource(R.drawable.ic_location),
            contentDescription = "Location",
            tint = Color.Blue,
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterVertically)
                .size(20.dp)

        )

        Spacer(modifier = Modifier.width(7.dp))

        Box(

            modifier = Modifier.fillMaxWidth()

        ) {

            //name and address
            Column(

                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.TopStart)

            ) {

                Text(
                    name,
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    number,
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(3.dp))

                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.Start)
                ) {

                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                            .clip(shape = RoundedCornerShape(5.dp))
                            .background(color = Color(0xFF27E3FC))
                            .align(Alignment.CenterVertically)

                    ) {

                        Text(addressCategory,
                            fontSize = 10.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.W500,
                            color = Color.Blue,
                            modifier = Modifier
                                .wrapContentWidth()
                                .padding(start = 4.dp, end = 4.dp)
                                .align(Alignment.Center)
                        )

                    }//box


                    Spacer(modifier = Modifier.width(2.dp))

                    Text(address,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray,
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.CenterVertically)
                    )

                }//row

                Spacer(modifier = Modifier.height(5.dp))

            }//column
            //name and address

            Text("EDIT",
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W500,
                color = Color.Blue,
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(shape = RoundedCornerShape(7.dp))
                    .clickable { editClick() }
                    //.background(color = Color(0xFFFF9800))
                    .padding(5.dp)
                    .align(Alignment.TopEnd)
                )

        }//box

    }//row

}//fun end


@Preview(showBackground = true)
@Composable
private fun ProductImage(
    rating : Double = 0.0,
    title : String = "Product name",
    brand : String = "No Brand",
    price : Double = 0.0,
    thumbnailImage : String = "",
    imageList : List<String> = emptyList(),
    productStock : Int = 0

) {

    var selectedIndex by remember { mutableIntStateOf(0) }
    var productQuantity by remember { mutableIntStateOf(1) }
    var voucherCode by remember { mutableStateOf("") }
    val voucher by remember { mutableStateOf("YEAR2026") }
    var isVoucherApplied by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    //val list = remember { mutableStateListOf<String>() }

    //list.clear()

    //list.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.zB1rsS80G2GPbY0mMvKnigHaEK%3Fpid%3DApi&f=1&ipt=86a3f0524572a730db9f55d783e9e8ddc83067443ebbed48da416478f93f31e7&ipo=images")
    //list.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.kGmuLIMjc1TVVqyC43yAZgHaEK%3Fpid%3DApi&f=1&ipt=68157fbab4dca7f0c14cf71a0c7cf6af51f4c41c2cd87158b633ade229fa2cbe&ipo=images")
   // list.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.MCLzVoExgXPyNi_V5gb1AwHaE7%3Fpid%3DApi&f=1&ipt=5142623a1e14b522723873ae4e7771e314e12cebbaa4c610711098921078f9ef&ipo=images")
    //list.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.gabnTIrb9_VUBujEuAZp1QHaEK%3Fpid%3DApi&f=1&ipt=5ac9f3bb42707ffdfee677dd1b614a94f027ff201e9d53747190ccb17b82ce20&ipo=images")
    //list.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.1zIwY9hrcRVit24BKtTZcAHaD6%3Fpid%3DApi&f=1&ipt=c8dffcfb75d504d78f9334f81a645aa844e696898e3b871147aa999e4486ea03&ipo=images")
    //list.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.RmldSfev8EynQNF1x-MWqAHaEK%3Fpid%3DApi&f=1&ipt=8337be9ab20f1acd61ca96f27584bb0b80c5d52316fe7916c2e350b23005592b&ipo=images")

    Column(

        modifier = Modifier.fillMaxWidth().background(color = Color.White).padding(5.dp)

    ) {

        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier.wrapContentWidth()
        ) {

            Icon( painter = painterResource(R.drawable.ic_store),
                contentDescription = "Store",
                tint = Color.Gray,
                modifier = Modifier
                    .wrapContentWidth()
                    .size(16.dp)
                    .align(Alignment.CenterVertically)
                )

            Spacer(modifier = Modifier.width(7.dp))

            Text("Daraz Store",
                fontSize = 15.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
                )

        }//row

        Spacer(modifier = Modifier.height(10.dp))

        Text(

            buildAnnotatedString {

                withStyle(style = SpanStyle(fontSize = 13.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Normal ,color = Color(0xFF484343))){
                    append("Product rating  ")
                }

                withStyle(style = SpanStyle(fontSize = 14.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold ,color = Color(0xFF484343))){
                    append("$rating")
                }

                withStyle(style = SpanStyle(fontSize = 13.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Normal ,color = Color(0xFF484343))){
                    append("/5")
                }

            },
            style = TextStyle(
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            ),
            modifier = Modifier
                .wrapContentWidth()
                .clip(shape = RoundedCornerShape(5.dp))
                .background(color = Color(0xFFF8FDCD))
                .padding(start = 7.dp, end = 7.dp, top = 5.dp, bottom = 5.dp)
                .align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(10.dp))

        //main image and price

        Row(

            modifier = Modifier.fillMaxWidth().align(Alignment.Start)

        ){

            //image

            Box(

                modifier = Modifier
                    .height(75.dp)
                    .width(75.dp)
                    .clip(shape = RoundedCornerShape(13.dp))
                    .align(Alignment.CenterVertically)

            ) {

                AsyncImage( model = thumbnailImage,
                    contentDescription = "",
                    placeholder = painterResource(R.drawable.img_loading_daraz),
                    error = painterResource(R.drawable.img_loading_daraz),
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(0xFFF5F1F1))
                        .align(Alignment.Center)

                )

            }//box
            //image

            Spacer(modifier = Modifier.width(12.dp))

            //title, price, discount, brand
            Column(

                modifier = Modifier.fillMaxWidth()

            ) {

                Text(title,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.W500,
                    color = Color(0xFF000000),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(brand,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    ),
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    modifier = Modifier.wrapContentWidth()
                ) {

                    Icon( painter = painterResource(R.drawable.ic_dollar),
                        contentDescription = "dollar",
                        tint = Color.Black,
                        modifier = Modifier
                            .wrapContentWidth()
                            .size(12.dp)
                            .align(Alignment.CenterVertically)

                    )

                    Spacer(modifier = Modifier.width(1.dp))

                    Text(price.toString(),
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.W500,
                        color = Color.Black,
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.CenterVertically)

                    )

                }//row


            }//column
            //title, price, discount, brand

        }//row

        //main image and price

        Spacer(modifier = Modifier.height(10.dp))

        //other images

        Text("Color Family",
            fontSize = 15.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF000000),
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.Start)
            )

        Spacer(modifier = Modifier.height(7.dp))

        //if (imageList.isEmpty()) return

        Column(

            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {

            imageList.chunked(3).forEachIndexed { mainIndex ,rowItem ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)

                ) {

                    rowItem.forEachIndexed{ localIndex, it ->

                        Spacer(modifier = Modifier.width(3.dp))

                        val globalIndex = mainIndex * 3 + localIndex

                        Box(
                            modifier = Modifier
                                //.weight(1f)
                                .width(100.dp)
                                .height(100.dp)
                                .clip(shape = RoundedCornerShape(14.dp))
                                .clickable{

                                    selectedIndex = globalIndex

                                }
                                .border(
                                    width = 2.dp,
                                    shape = RoundedCornerShape(14.dp),
                                    color = if (globalIndex == selectedIndex) Color(0xFFFF5722) else Color.Transparent

                                )

                        ) {

                            AsyncImage(model = it,
                                placeholder = painterResource(R.drawable.img_loading_daraz),
                                error = painterResource(R.drawable.img_loading_daraz),
                                contentDescription = "More images",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color = Color(0xABEEEAEA))
                                    .align(Alignment.Center)

                            )

                        }//box

                        Spacer(modifier = Modifier.width(4.dp))

                    }

                }//row

            }//image list

        }//column

        //other images

        Spacer(modifier = Modifier.height(10.dp))
        
        HorizontalDivider(
            color = Color(0x8AFCC2C2),
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(10.dp))

        //quantity
        Box(

            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)

        ) {

            Text("Quantity",
                fontSize = 15.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterStart)
                )

            Row(

                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterEnd)

            ) {

                Text("-",
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF4F4545),
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    ),
                    modifier = Modifier
                        .wrapContentWidth()
                        .clip(shape = RoundedCornerShape(5.dp))
                        .clickable( enabled = (productQuantity > 1)) {

                            productQuantity -= 1

                        }
                        .alpha( if (productQuantity == 1) 0.5f else 1f )
                        .background(color = Color(0xFFEEEEEE))
                        .padding(start = 12.dp, end = 12.dp)
                        .align(Alignment.CenterVertically)
                    )

                Spacer(modifier = Modifier.width(10.dp))

                Text(productQuantity.toString(),
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterVertically)
                    )

                Spacer(modifier = Modifier.width(10.dp))

                Text("+",
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF625656),
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    ),
                    modifier = Modifier
                        .wrapContentWidth()
                        .clip(shape = RoundedCornerShape(5.dp))
                        .clickable (

                            enabled = if (productStock == productQuantity) false else true

                        ){

                            productQuantity += 1

                        }
                        .alpha( if (productStock == productQuantity) 0.5f else 1f )
                        .background(color = Color(0xFFEEEEEE))
                        .padding(start = 10.dp, end = 10.dp)
                        .align(Alignment.CenterVertically)
                )

            }//row

        }//box
        //quantity

        Spacer(modifier = Modifier.height(10.dp))

        HorizontalDivider(
            color = Color(0x8AFCC2C2),
            thickness = 1.dp
        )

        //product cost and other cost

        Spacer(modifier = Modifier.height(10.dp))

        Box(

            modifier = Modifier.fillMaxWidth()

        ) {

            Text(

                buildAnnotatedString {

                    withStyle(style = SpanStyle(

                        fontSize = 13.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black

                    )) {

                        append("Merchandise Subtotal ")

                    }

                    withStyle(style = SpanStyle(

                        fontSize = 13.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray

                    )){

                        append("(")

                    }

                    withStyle(style = SpanStyle(

                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray

                    )){

                        append("$productQuantity")

                    }

                    withStyle(style = SpanStyle(

                        fontSize = 13.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray

                    )){

                        append(" item)")

                    }

                },

                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterStart)

            )

            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterEnd)
            ) {

                Icon( painter = painterResource(R.drawable.ic_dollar),
                    contentDescription = "dollar",
                    tint = Color.Black,
                    modifier = Modifier
                        .wrapContentWidth()
                        .size(13.dp)
                        .align(Alignment.CenterVertically)

                )

                Spacer(modifier = Modifier.width(2.dp))

                val totalPrice = price * productQuantity

                val actualPrice = if ((totalPrice.toString()).length > 5) totalPrice.toFloat() else totalPrice

                Text(actualPrice.toString(),
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterVertically)
                    )

            }//row

        }//box

        Spacer(modifier = Modifier.height(10.dp))

        Box(

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Shipping Fee Subtotal",
                fontSize = 13.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterStart)
                )

            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterEnd)
            ) {

                Icon( painter = painterResource(R.drawable.ic_dollar),
                    contentDescription = "dollar",
                    tint = Color.Black,
                    modifier = Modifier
                        .wrapContentWidth()
                        .size(13.dp)
                        .align(Alignment.CenterVertically)

                )

                Spacer(modifier = Modifier.width(2.dp))

                Text("10",
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterVertically)
                )

            }//row

        }//box

        Spacer(modifier = Modifier.height(12.dp))

        Box(

            modifier = Modifier.fillMaxWidth().align(Alignment.Start)

        ) {

            Text("Voucher & Code",
                fontSize = 13.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterStart)
                )

            //text filed
            Box(

                modifier = Modifier
                    .wrapContentWidth()
                    .clip(shape = RoundedCornerShape(7.dp))
                    .border(
                        width = 1.dp,
                        color = if (isVoucherApplied) Color.Transparent else Color(0xFFFF5722),
                        shape = RoundedCornerShape(7.dp)
                    )
                    .alpha( if (isVoucherApplied) 0f else 1f )
                    .padding(7.dp)
                    .align(Alignment.CenterEnd)
                    .imePadding()

            ) {

                if (voucherCode.isEmpty()){

                    Text("Enter your voucher code",
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.W500,
                        color = Color.Gray,
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false
                            )
                        ),
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.Center)
                        )

                }


                if (!isVoucherApplied){

                    BasicTextField(

                        value = voucherCode,
                        onValueChange = { if (it.length <= 10) voucherCode = it },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Go,
                            keyboardType = KeyboardType.Text
                        ),
                        keyboardActions = KeyboardActions(
                            onGo = {
                                keyboardController?.hide()

                                if (voucherCode == voucher) isVoucherApplied = true else isVoucherApplied = false

                            }
                        ),
                        textStyle = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.W500),
                        maxLines = 1,
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(top = 3.dp, bottom = 3.dp, end = 3.dp)
                            .align(Alignment.CenterStart)

                    )

                }

            }

            Text(voucher,
                fontSize = 13.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(shape = RoundedCornerShape(7.dp))
                    .alpha( if(isVoucherApplied) 1f else 0f )
                    .background(color = Color(0xFFEAE8E8))
                    .padding(5.dp)
                    .align(Alignment.CenterEnd)
            )

            //text filed

        }//box

        //product cost and other cost


    }//column

}//fun end
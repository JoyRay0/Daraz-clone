package com.rk_softwares.e_commerce.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import coil3.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rk_softwares.e_commerce.Other.IntentHelper
import com.rk_softwares.e_commerce.Other.KeyHelper
import com.rk_softwares.e_commerce.Other.ShortMessageHelper
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.activity.ui.theme.E_commerceTheme
import com.rk_softwares.e_commerce.database.Cart
import com.rk_softwares.e_commerce.database.Wishlist
import com.rk_softwares.e_commerce.model.Product
import com.rk_softwares.e_commerce.server.FullProductInfoServer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Act_wishlist : ComponentActivity() {

    //=================================================

    private lateinit var product : FullProductInfoServer

    private lateinit var wishlistDB : Wishlist

    private lateinit var cartDB : Cart

    //=================================================

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val systemUi = rememberSystemUiController()
            val list = remember { mutableStateListOf<Product>() }

            systemUi.setStatusBarColor(color = Color.White, darkIcons = true)
            systemUi.setNavigationBarColor(color = Color.White)

            init()

            lifecycleScope.launch(Dispatchers.IO){

                val data = wishlistDB.getAll()

                withContext(Dispatchers.Main){

                    list.addAll(data)

                }

            }

            E_commerceTheme {
                FullScreen(list = list, this@Act_wishlist, addOneItemToCart = { sku ->

                    lifecycleScope.launch(Dispatchers.IO){

                        if (list.isNotEmpty()){

                            if (!cartDB.checkDuplicateData(sku)){

                                val item = list.find { it.sku == sku}

                                item?.let {

                                    cartDB.insert(
                                        item.sku,
                                        item.thumbnail,
                                        item.title,
                                        item.price,
                                        item.discountPercentage
                                    )
                                }

                                withContext(Dispatchers.Main){

                                    ShortMessageHelper.toast(this@Act_wishlist, "Added to cart")

                                }

                            }else{

                                withContext(Dispatchers.Main){

                                    ShortMessageHelper.toast(this@Act_wishlist, "Already in cart")

                                }


                            }



                        }

                    }//background task

                }, addAllItemToCart = {

                    lifecycleScope.launch(Dispatchers.IO){

                        if (list.isNotEmpty()){

                            var addedCount = 0

                            list.forEach { item ->
                                
                                if (!cartDB.checkDuplicateData(item.sku)){

                                    cartDB.insert(
                                        item.sku,
                                        item.thumbnail,
                                        item.title,
                                        item.price,
                                        item.discountPercentage
                                    )

                                    addedCount ++

                                }
                                
                            }

                            withContext(Dispatchers.Main){

                                if (addedCount > 0){

                                    ShortMessageHelper.toast(this@Act_wishlist, "$addedCount items added to cart")

                                }else{

                                    ShortMessageHelper.toast(this@Act_wishlist, "All items are already in cart")

                                }

                            }

                        }

                    }//background task


                }, deleteItem = { sku ->

                    lifecycleScope.launch(Dispatchers.IO){

                        val deleted = wishlistDB.delete(sku)

                        withContext(Dispatchers.Main){

                            if (deleted){

                                list.removeAll { it.sku == sku }
                                ShortMessageHelper.toast(this@Act_wishlist, "Deleted")

                            }else{

                                ShortMessageHelper.toast(this@Act_wishlist, "Not deleted")

                            }

                        }

                    }

                })
            }
        }
    }//on create=============================================

    private fun init(){

        wishlistDB = Wishlist(this)
        product = FullProductInfoServer(this)
        cartDB = Cart(this)

    }

    override fun onDestroy() {
        super.onDestroy()

        wishlistDB.closeDB()
        cartDB.closeDB()

    }

}//class ended================================================

@Preview(showBackground = true)
@Composable
fun FullScreen(list: List<Product> = emptyList(), activity: Activity? = null, addOneItemToCart : (String) -> Unit = {}, addAllItemToCart : () -> Unit = {}, deleteItem : (String) -> Unit = {}){

    val context = LocalContext.current

    Box(

        modifier = Modifier.fillMaxSize()

    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            //top bar

            Toolbar(
                boxModifier = Modifier.align(Alignment.CenterHorizontally),
                backClick = {

                    activity?.finish()

                            },
                searchClick = {
                    activity?.let {
                        IntentHelper.intent(it, Act_search::class.java)

                    } },
                cartClick = {
                    activity?.let {
                        IntentHelper.setDataIntent(
                            it,
                            Act_home::class.java,
                            KeyHelper.getHomeInfo(),
                            "Fg_cart"
                        )
                    }
                }
            )

            //top bar

            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                val lazyState = rememberLazyListState()

                if (list.isEmpty()){

                    Spacer(modifier = Modifier.height(60.dp))

                    Image( painter = painterResource(R.drawable.img_empty_wishlist),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .size(180.dp)

                    )

                }else{

                    LazyColumn(
                        state = lazyState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 40.dp),
                        contentPadding = PaddingValues(3.dp),

                        ) {

                        items(

                            items = list,
                            key = {it.sku}

                        ){ it ->

                            val image = it.thumbnail
                            val title = it.title
                            val price = it.price
                            val discount = it.discountPercentage

                            Wishlist(
                                modifier = Modifier.animateContentSize(),
                                imageUrl = image,
                                title = title,
                                price = price,
                                discount = discount,
                                addCart = {

                                    addOneItemToCart(it.sku)

                                },
                                delete = {deleteItem(it.sku)},


                            )

                        }

                    }

                }



            }//box

        }//column

        //bottom bar

        BottomNav(boxModifier = Modifier.align(Alignment.BottomCenter), addAllCartClick = {addAllItemToCart()})

        //bottom bar


    }//box


}//fun end


@Preview(showBackground = true)
@Composable
private fun Toolbar(
    boxModifier: Modifier = Modifier,
    backClick : () -> Unit = {},
    searchClick : () -> Unit = {},
    cartClick : () -> Unit = {}
    ){

    Box(
        modifier = boxModifier.fillMaxWidth().background(color = Color.White)
    ) {

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .padding(7.dp)
                .align(Alignment.CenterStart)
        ) {

            IconButton(
                onClick = backClick,
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(shape = CircleShape)
                    .align(Alignment.CenterVertically)
                    .size(35.dp)
            ) {

                Icon( painter = painterResource(R.drawable.ic_back),
                    contentDescription = "",
                    tint = Color(0xFF000000),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(16.dp)

                )

            }

            Spacer(modifier = Modifier.width(12.dp))

            Text("My Wishlist",
                fontSize = 17.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF000000),
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
                )

        }//row

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .padding(5.dp)
                .align(Alignment.CenterEnd)
        ) {


            IconButton(
                onClick = searchClick,
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(shape = CircleShape)
                    .align(Alignment.CenterVertically)
                    .size(35.dp)
            ) {

                Icon( painter = painterResource(R.drawable.ic_search),
                    contentDescription = "",
                    tint = Color(0xFF000000),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(20.dp)

                )

            }

            Spacer(modifier = Modifier.width(5.dp))

            IconButton(
                onClick = cartClick,
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(shape = CircleShape)
                    .align(Alignment.CenterVertically)
                    .size(35.dp)
            ) {

                Icon( painter = painterResource(R.drawable.ic_cart),
                    contentDescription = "",
                    tint = Color(0xFF000000),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(20.dp)

                )

            }

            Spacer(modifier = Modifier.width(5.dp))

            IconButton(
                onClick = {},
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(shape = CircleShape)
                    .align(Alignment.CenterVertically)
                    .size(35.dp)
            ) {

                Icon( painter = painterResource(R.drawable.ic_three_dots_ver),
                    contentDescription = "",
                    tint = Color(0xFF000000),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(18.dp)

                )

            }



        }//row


    }//box

}//fun end

@Preview(showBackground = true)
@Composable
private fun BottomNav(boxModifier: Modifier = Modifier, addAllCartClick : () -> Unit = {}){

    Box(
        modifier = boxModifier.fillMaxWidth().padding(10.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color(0xFFFF5722))
                .padding(10.dp)
                .clickable(
                    indication = null,
                    interactionSource = null
                ){
                    addAllCartClick()
                }
        ) {

            Text("Add all to cart",
                fontSize = 17.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                )

        }//row

    }//box

}//fun end


@Preview(showBackground = true)
@Composable
fun Wishlist(
    modifier: Modifier = Modifier,
    imageUrl : String = "",
    title : String = "Rada krishna",
    price : Double = 0.0,
    discount : Double = 0.0,
    addCart : () -> Unit = {},
    delete : () -> Unit = {},
    ){

    var isClicked by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)

    ) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color(0x81EFEAEA))
                .padding(7.dp)
                .align(Alignment.Center)
                .clickable(
                    indication = null,
                    interactionSource = null
                ){
                    if (isClicked) isClicked = false else isClicked = true
                }

        ) {

            //image
            Box(
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color.White)
                    .align(Alignment.Top)

            ) {

                if (isClicked){

                    IconButton(
                        onClick = delete,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color(0x75836A6A))
                            .align(Alignment.Center)
                    ) {

                        Icon( painter = painterResource(R.drawable.ic_delete),
                            contentDescription = "",
                            tint = Color(0xF0383434),
                            modifier = Modifier
                                .align(Alignment.Center)

                        )

                    }

                }else{

                    AsyncImage(model = imageUrl,
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds,
                        placeholder = painterResource(R.drawable.img_loading_daraz),
                        error = painterResource(R.drawable.img_loading_daraz),
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center)
                    )

                }



            }//box
            //image

            Spacer(modifier = Modifier.width(5.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Top)
                    .padding(3.dp)
            ) {

                Text(title,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.Start)
                )

                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.wrapContentWidth()
                    ) {

                        Row(
                            modifier = Modifier
                                .wrapContentWidth()
                                .align(Alignment.Start)
                        ) {

                            Icon( painter = painterResource(R.drawable.ic_taka),
                                contentDescription = "",
                                tint = Color(0xFFFF5722),
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .align(Alignment.CenterVertically)
                                    .size(15.dp)

                            )


                            Text(price.toString(),
                                fontSize = 17.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFFFF5722),
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .align(Alignment.CenterVertically)
                            )

                        }//row

                        Row(
                            modifier = Modifier
                                .wrapContentWidth()
                                .align(Alignment.Start)
                        ) {

                            Text("-",
                                fontSize = 15.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF000000),
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .align(Alignment.CenterVertically)
                            )

                            Text(discount.toString(),
                                fontSize = 13.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF000000),
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .align(Alignment.CenterVertically)
                            )

                            Icon( painter = painterResource(R.drawable.ic_discount),
                                contentDescription = "",
                                tint = Color(0xFF000000),
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .align(Alignment.CenterVertically)
                                    .size(11.dp)

                            )

                        }//row

                    }//column

                    Row (
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.BottomEnd)
                            .clip(shape = RoundedCornerShape(7.dp))
                            .background(color = Color(0xFFFF5722))
                            .padding(start = 7.dp, end = 7.dp, top = 3.dp, bottom = 3.dp)
                            .clickable(
                                indication = null,
                                interactionSource = null,
                            ){
                                addCart()
                            }

                    ) {

                        Text("+",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFFFFFFFF),
                            modifier = Modifier
                                .wrapContentWidth()
                                .align(Alignment.CenterVertically)
                        )

                        Icon( painter = painterResource(R.drawable.ic_cart_fill),
                            contentDescription = "",
                            tint = Color(0xFFFFFFFF),
                            modifier = Modifier
                                .wrapContentWidth()
                                .align(Alignment.CenterVertically)
                                .size(19.dp)

                        )


                    }//row

                }//row


            }//column

        }//row

    }//box

}//fun end


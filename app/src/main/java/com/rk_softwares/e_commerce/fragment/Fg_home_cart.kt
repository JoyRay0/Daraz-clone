package com.rk_softwares.e_commerce.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil3.compose.AsyncImage
import com.rk_softwares.e_commerce.Other.EdgeToEdge
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.activity.Act_home
import com.rk_softwares.e_commerce.activity.ui.theme.E_commerceTheme
import com.rk_softwares.e_commerce.database.Cart
import com.rk_softwares.e_commerce.server.CartServer
import com.rk_softwares.e_commerce.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Fg_home_cart : Fragment() {

    //XML id's-------------------------------------------------------

    private lateinit var fl_toolbar : FrameLayout
    private lateinit var ll_empty_cart : LinearLayout
    private lateinit var btn_start_shopping : AppCompatTextView

    private lateinit var cv_cart : ComposeView

    //others
    private lateinit var edge_to_edge : EdgeToEdge
    private lateinit var cartServer : CartServer

    private lateinit var cartDB : Cart


    //XML id's-------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_home_cart, container, false)

        init(view)

        compose()

        btn_start_shopping.setOnClickListener {

            startActivity(Intent(requireActivity(), Act_home::class.java))

        }


        return view
    }//on create===============================================================

    private fun init(view: View){

        //identity period-----------------------------------------------------

        fl_toolbar = view.findViewById(R.id.fl_toolbar)
        ll_empty_cart = view.findViewById(R.id.ll_empty_cart)
        btn_start_shopping = view.findViewById(R.id.btn_start_shopping)
        cv_cart = view.findViewById(R.id.cv_cart)

        //identity period-----------------------------------------------------

        edge_to_edge = EdgeToEdge(requireActivity())
        edge_to_edge.setToolBar(fl_toolbar)

        cartServer = CartServer(requireActivity())

        cartDB = Cart(requireActivity())

    }

    private fun compose(){

        cv_cart.setContent {

            val list = remember { mutableStateListOf<Product>() }

            LaunchedEffect(Unit) {

                val item = withContext(Dispatchers.IO){

                    cartDB.getAll()

                }

                list.clear()
                list.addAll(item)

            }

            E_commerceTheme { 

                //CartItem()

                Column(

                    modifier = Modifier.fillMaxSize()

                ) {

                    LazyColumn(

                        modifier = Modifier.fillMaxWidth(),
                        state = rememberLazyListState()

                    ) {

                        items(
                            items = list,
                            //key = {it.sku}
                        ){ it ->

                            CartItem(
                                imageUrl = it.thumbnail,
                                title = it.title,
                                price = it.price,
                                discount = it.discountPercentage,

                                )

                        }

                    }//lazycolumn

                }//column


            }

        }

    }

}//public class=================================================================

@Preview(showBackground = true)
@Composable
private fun CartItem(
    imageUrl : String = "",
    title : String = "Rada krishna",
    price : Double = 0.0,
    discount : Double = 0.0,
    payClick : () -> Unit = {}
) {

    var isDeleteVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxWidth().padding(5.dp)
    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 1.dp, shape = RoundedCornerShape(12.dp))
                .clip(shape = RoundedCornerShape(12.dp))
                .clickable(
                    indication = null,
                    interactionSource = null
                ){ isDeleteVisible = !isDeleteVisible }
                .background(color = Color.White)
                .padding(7.dp)

        ) {

            //image

            Box(

                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(color = if (isDeleteVisible) Color(0xFFC9BABA) else Color(0xFFEAE9E9))
                    .align(Alignment.CenterVertically)

            ) {

                if (isDeleteVisible){

                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .wrapContentWidth()
                            .clip(shape = CircleShape)
                            //.background(color = Color.Blue)
                            .size(45.dp)
                            .align(Alignment.Center)
                    ) {

                        Icon( painter = painterResource(R.drawable.ic_delete),
                            contentDescription = "Pay",
                            tint = Color(0xFF343333),
                            modifier = Modifier
                                .wrapContentWidth()
                                .size(25.dp)
                                .align(Alignment.Center)

                        )

                    }

                }else{

                    AsyncImage( model = imageUrl,
                        contentDescription = "Image",
                        contentScale = ContentScale.FillBounds,
                        placeholder = painterResource(R.drawable.img_loading_daraz),
                        error = painterResource(R.drawable.img_loading_daraz),
                        modifier = Modifier
                            .fillMaxSize()

                    )

                }


            }//box

            //image

            Spacer(modifier = Modifier.width(10.dp))

            //title, price, discount

            Column(

                modifier = Modifier
                    .fillMaxWidth()

            ) {

                Text(title,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)
                    )

                Spacer(modifier = Modifier.height(4.dp))

                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)

                ) {

                    Column(

                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.CenterStart)

                    ) {

                        Row(

                            modifier = Modifier.wrapContentWidth().align(Alignment.Start)

                        ) {

                            Icon( painter = painterResource(R.drawable.ic_dollar),
                                contentDescription = "Price",
                                tint = Color(0xFFFF5722),
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .size(13.dp)
                                    .align(Alignment.CenterVertically)

                            )

                            Spacer(modifier = Modifier.width(2.dp))

                            Text(price.toString(),
                                fontSize = 16.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFF5722),
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .align(Alignment.CenterVertically)
                            )

                        }//row

                        Spacer(modifier = Modifier.height(5.dp))

                        Row(

                            modifier = Modifier
                                .wrapContentWidth()
                                .clip(shape = RoundedCornerShape(5.dp))
                                .background(color = Color(0xFFFDEAE3))
                                .padding(3.dp)
                                .align(Alignment.Start)

                        ) {

                            Text("-",
                                fontSize = 11.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black,
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .align(Alignment.CenterVertically)
                            )

                            Spacer(modifier = Modifier.width(2.dp))

                            Text(discount.toString(),
                                fontSize = 11.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black,
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .align(Alignment.CenterVertically)
                            )

                            Icon( painter = painterResource(R.drawable.ic_discount),
                                contentDescription = "Discount",
                                tint = Color.Black,
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .size(9.dp)
                                    .align(Alignment.CenterVertically)

                            )

                        }//row

                    }//column

                    Box(

                        modifier = Modifier
                            .wrapContentWidth()
                            .clip(shape = RoundedCornerShape(10.dp))
                            .clickable{ payClick() }
                            .background(color = Color(0xFFF1EFEF))
                            .align(Alignment.CenterEnd)
                            .padding(5.dp)

                    ) {

                        Image( painter = painterResource(R.drawable.img_pay),
                            contentDescription = "Pay",
                            modifier = Modifier
                                .wrapContentWidth()
                                .size(30.dp)
                                .align(Alignment.Center)

                        )

                    }//box


                }//box



            }//column

            //title, price, discount

        }//row

    }//box

}//fun end
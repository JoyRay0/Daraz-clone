package com.rk_softwares.e_commerce.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.activity.ui.theme.E_commerceTheme
import com.rk_softwares.e_commerce.model.ChatMessage

class Act_chat : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val systemUi = rememberSystemUiController()
            val chatList = remember { mutableStateListOf<ChatMessage>() }

            systemUi.setStatusBarColor(
                color = Color.White,
                darkIcons = true
            )
            
            systemUi.setNavigationBarColor(color = Color.White)


            E_commerceTheme {

                chatList.add(ChatMessage(
                    id = 1,
                    message = "Hello from customer",
                    isMe = true
                ))

                chatList.add(ChatMessage(
                    id = 2,
                    message = "Hello from store",
                    isMe = false
                ))

                FullScreen(

                    backClick = { finish() },
                    list = chatList

                )
            }
        }
    }//on create========================================
    
}//class ended==========================================

@Preview(showBackground = true)
@Composable
private fun FullScreen(
    backClick: () -> Unit = {},
    list: MutableList<ChatMessage> = mutableListOf()
) {

    Scaffold(

        topBar = {Toolbar (

            backClick = backClick

        )},
        bottomBar = {BottomNav (
            
            list = list

        )},
        modifier = Modifier
            .fillMaxSize()

    ) { innerPadding ->


        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)

        ) {


            //chat body

            val lazyState = rememberLazyListState()

            LazyColumn(

                state = lazyState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp)
                
            ) {

                items(

                    items = list,
                    key = { it.id }

                ){ msg ->

                    ChatBubble(

                        message = msg.message,
                        isSender = msg.isMe

                    )

                }

            }

            //chat body

        }//column

    }//scaffold

}//fun end

@Preview(showBackground = true)
@Composable
private fun Toolbar(
    storeName : String = "Rada krishna",
    backClick : () -> Unit = {}
    ) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(5.dp)
    ) {

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterStart)
        ) {

            IconButton(
                onClick = backClick,
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(shape = CircleShape)
                    //.background(color = Color.Blue)
                    .size(35.dp)
                    .align(Alignment.CenterVertically)
            ) {

                Icon( painter = painterResource(R.drawable.ic_back),
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier
                        .wrapContentWidth()
                        .size(16.dp)
                        .align(Alignment.CenterVertically)

                )

            }

            Spacer(modifier = Modifier.width(5.dp))

            Text(storeName,
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W500,
                color = Color(0xFF2F2D2D),
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
                )

        }//row

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterEnd)
        ) {

            IconButton(
                onClick = {},
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(shape = CircleShape)
                    //.background(color = Color.Blue)
                    .size(35.dp)
                    .align(Alignment.CenterVertically)
            ) {

                Icon( painter = painterResource(R.drawable.ic_store),
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier
                        .wrapContentWidth()
                        .size(19.dp)
                        .align(Alignment.CenterVertically)

                )

            }

            Spacer(modifier = Modifier.width(5.dp))

            IconButton(
                onClick = {},
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(shape = CircleShape)
                    //.background(color = Color.Blue)
                    .size(35.dp)
                    .align(Alignment.CenterVertically)
            ) {

                Icon( painter = painterResource(R.drawable.ic_three_dots_ver),
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier
                        .wrapContentWidth()
                        .size(19.dp)
                        .align(Alignment.CenterVertically)

                )

            }
            

        }//row

    }//box

}//fun end


@Preview(showBackground = true)
@Composable
private fun BottomNav(
    list : MutableList<ChatMessage> = mutableListOf()) {

    var messageText by remember { mutableStateOf("") }
    var messageCount by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp))
            .clip(shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .background(color = Color(0xFFFFFFFF))

    ) {

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)

        ) {

            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(shape = RoundedCornerShape(7.dp))
                    .background(color = Color(0x57E7E0E0))
                    .align(Alignment.Start)
                    .padding(5.dp)
            ) {

                Icon( painter = painterResource(R.drawable.ic_rating),
                    contentDescription = "",
                    tint = Color(0xFF83C23A),
                    modifier = Modifier
                        .wrapContentWidth()
                        .size(12.dp)
                        .align(Alignment.CenterVertically)

                )

                Text("Rate Service",
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterVertically)
                        .padding(start = 2.dp)
                )

            }//row

            Spacer(modifier = Modifier.height(9.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()

            ) {

                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterVertically)
                        .clip(shape = CircleShape)
                        .background(color = Color(0xFFFF5722))
                        .size(30.dp)
                ) {

                    Icon( painter = painterResource(R.drawable.ic_plus),
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.CenterVertically)
                            .size(22.dp)

                    )

                }

                Spacer(modifier = Modifier.width(5.dp))

                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(10.dp))
                        .border(
                            width = 1.dp,
                            color = Color(0xE8D3A7A7),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .align(Alignment.CenterVertically)
                        .padding(10.dp)


                ) {

                    if (messageText.isEmpty()){

                        Text("Enter your message",
                            fontSize = 14.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF8D7070),
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterStart)
                        )

                    }

                    BasicTextField(
                        value = messageText,
                        onValueChange = { messageText = it },
                        textStyle = TextStyle(fontSize = 16.sp),
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .align(Alignment.CenterStart)

                    )

                    IconButton(
                        enabled = messageText.isNotEmpty(),
                        onClick = {
                            list.add(ChatMessage(
                                System.currentTimeMillis(),
                                messageText,
                                true
                            ))

                            messageText = ""
                        },
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.CenterEnd)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xFFF8B756))
                            .size(32.dp)
                            .alpha(if (messageText.isEmpty()) 0.5f else 1f)
                    ) {

                        Icon( painter = painterResource(R.drawable.ic_send),
                            contentDescription = "Send",
                            tint = Color(0xFFFFFFFF),
                            modifier = Modifier
                                .wrapContentWidth()
                                .align(Alignment.Center)
                                .rotate(268f)
                                .size(22.dp)

                        )

                    }

                }//box text filed

            }//row
            
        }//column

    }//column

}//fun end


@Preview(showBackground = true)
@Composable
fun ChatBubble(message : String = "Hello", isSender : Boolean = false) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = if (isSender) Arrangement.End else Arrangement.Start

    ) {

        Text(message,
            fontSize = 15.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            color =  if (isSender) Color(0xFFFFFFFF) else Color.Black ,
            modifier = Modifier
                .wrapContentWidth()
                .clip(shape = RoundedCornerShape(10.dp))
                .background(
                    color = if (isSender) Color(0xFFF36335) else Color(0xFFC9C7C7)
                )
                .padding(10.dp)
            )

    }//row

}//fun end



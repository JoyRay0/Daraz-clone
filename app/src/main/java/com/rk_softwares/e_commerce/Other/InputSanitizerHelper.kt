package com.rk_softwares.e_commerce.Other

object InputSanitizerHelper {

    private val normalString = Regex("[!':;/?()+\\-*&%৳#@<>\\=\\[\\]|`^,.{}]")
    private val emailString = Regex("[!':;/?()+\\-*&%৳#<>\\=\\[\\]|`^,{}]")


    fun isValidString(input : String) : String{

        return if (normalString.containsMatchIn(input)) {
            ""
        } else if (input == "null"){
            ""
        } else  {
            input.replace(Regex("\\s+"), " ").trim()
        }

    }

    fun isValidEmail(input : String) : String{

        return if (emailString.containsMatchIn(input)) {
            ""
        } else if (input == "null"){
            ""
        }
        else {
            input.replace(Regex("\\s+"), " ").trim()
        }

    }

}
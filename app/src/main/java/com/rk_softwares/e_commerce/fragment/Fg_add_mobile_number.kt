package com.rk_softwares.e_commerce.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.rk_softwares.e_commerce.Other.StorageHelper
import com.rk_softwares.e_commerce.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class Fg_add_mobile_number : Fragment() {

    //XML id's-----------------------------------------------------

    private lateinit var ed_number : AppCompatEditText
    private lateinit var cd_verify : CardView
    private lateinit var sp_number : AppCompatSpinner

    //other
    private lateinit var numbers : StorageHelper


    private var selectedNumber : String = ""

    //XML id's-----------------------------------------------------

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_add_mobile_number, container, false)

        //identity period-----------------------------------------------------

        ed_number = view.findViewById(R.id.ed_number)
        cd_verify = view.findViewById(R.id.cd_verify)
        sp_number = view.findViewById(R.id.sp_number)
        //identity period-----------------------------------------------------

        numbers = StorageHelper(requireActivity(), "mobile_number")

        verifyNumber()
        spinnerNumber()

        return view
    }//on create======================================================

    //mobile number-----------------------------------------------------
    private fun verifyNumber(){

        cd_verify.setOnClickListener {

            val number = ed_number.text.toString().trim()

            if (number == null || number.isEmpty()){

                ed_number.error = "Number required"

            }else{

                numbers.deleteData("user_number")

                numbers.setData("user_number", selectedNumber+number)

                Toast.makeText(requireActivity(), "Verification successful", Toast.LENGTH_SHORT).show()
                //Toast.makeText(requireActivity(), selectedNumber+number, Toast.LENGTH_SHORT).show()

                CoroutineScope(Dispatchers.Main).launch{

                    ed_number.text?.clear()
                    delay(2000)

                }

            }


        }

    }
    //spinner number---------------------------------------------------------------
    private fun spinnerNumber(){

        val number = arrayOf("+880", "+94", "+977", "+92")

        val adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, number)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_number.adapter = adapter

        sp_number.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                selectedNumber = number[position]

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }


        }

    }

}//public class==========================================================
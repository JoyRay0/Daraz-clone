package com.rk_sofwares.e_commerce.fragment

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.rk_sofwares.e_commerce.R
import com.rk_sofwares.e_commerce.adapter.AddressAdapter
import com.rk_sofwares.e_commerce.database.Address
import java.util.ArrayList
import java.util.HashMap

class Fg_address_book : Fragment() {

    //xml id's---------------------------------------------------------------------

    private lateinit var rv_address : RecyclerView;
    private lateinit var cv_address : CardView;

    private var a_list : ArrayList<HashMap<String, String>> = ArrayList()

    //inti
    var categoryRadio = ""
    var shippingRadio = ""
    var billingRadio = ""

    //other

    private lateinit var addressDB : Address
    private lateinit var addressAdapter : AddressAdapter

    //xml id's---------------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_profile_setting_address_book, container, false)

        //identity period------------------------------------------------------------

        rv_address = view.findViewById(R.id.rv_address);
        cv_address = view.findViewById(R.id.cv_address);

        //identity period------------------------------------------------------------

        addressDB = Address(requireActivity())
        addressAdapter = AddressAdapter(requireActivity(), a_list)
        rv_address.adapter = addressAdapter

        cv_address.setOnClickListener {

            addressDialog()

        }

        a_list.clear()
        a_list.addAll(addressDB.getAll())
        addressAdapter.notifyDataSetChanged()

        return view
    }// on create============================================================

    private fun addressDialog(){

        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.lay_add_address_dialog)

        dialog.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
        dialog.window?.setGravity(Gravity.BOTTOM)

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val cv_no = dialog.findViewById<CardView>(R.id.cv_no);
        val cv_save = dialog.findViewById<CardView>(R.id.cv_save);

        val ed_name = dialog.findViewById<AppCompatEditText>(R.id.ed_name);
        val ed_number = dialog.findViewById<AppCompatEditText>(R.id.ed_number);
        val ed_region = dialog.findViewById<AppCompatEditText>(R.id.ed_region);
        val ed_city = dialog.findViewById<AppCompatEditText>(R.id.ed_city);
        val ed_district = dialog.findViewById<AppCompatEditText>(R.id.ed_district);
        val ed_address = dialog.findViewById<AppCompatEditText>(R.id.ed_address);
        val ed_landmark = dialog.findViewById<AppCompatEditText>(R.id.ed_landmark);

        val rb_home = dialog.findViewById<AppCompatRadioButton>(R.id.rb_home);
        val rb_office = dialog.findViewById<AppCompatRadioButton>(R.id.rb_office);
        val rb_dsa_on = dialog.findViewById<AppCompatRadioButton>(R.id.rb_dsa_on);
        val rb_dsa_off = dialog.findViewById<AppCompatRadioButton>(R.id.rb_dsa_off);
        val rb_dba_on = dialog.findViewById<AppCompatRadioButton>(R.id.rb_dba_on);
        val rb_dba_off = dialog.findViewById<AppCompatRadioButton>(R.id.rb_dba_off);

        checkToggleButton(rb_home, rb_office, "home", "office", "c_home")
        checkToggleButton(rb_dsa_off, rb_dsa_on,"off", "on", "s_on")
        checkToggleButton(rb_dba_off, rb_dba_on,"off", "on", "b_on")



        cv_no.setOnClickListener {

            dialog.dismiss()

        }

        cv_save.setOnClickListener {

            val category = categoryRadio
            val shipping = shippingRadio
            val billing = billingRadio

            val landmark = ed_landmark.text.toString().trim()

            val name = checkEditText(ed_name, "Name needed")
            val number = checkEditText(ed_number, "Number needed")
            val region =  checkEditText(ed_region, "Region needed")
            val city =  checkEditText(ed_city, "City needed")
            val district =  checkEditText(ed_district, "District needed")
            val address =  checkEditText(ed_address, "Address needed")


            if ((name.isEmpty()) || (number.isEmpty()) || (region.isEmpty()) || (city.isEmpty()) || (district.isEmpty()) || (address.isEmpty())){

                Toast.makeText(requireActivity(), "Some filed are missing", Toast.LENGTH_SHORT).show()

            }else{

                addressDB.insert(name, number, region, city, district, address, landmark, categoryRadio, shippingRadio, billingRadio)

                Toast.makeText(requireActivity(), "Address added successfully", Toast.LENGTH_SHORT).show()

                a_list.clear()
                a_list.addAll(addressDB.getAll())
                addressAdapter.notifyDataSetChanged()

                dialog.dismiss()


            }



        }

        dialog.show()

    }

    private fun checkEditText(editText : AppCompatEditText, error : String, ) : String{

        val ed = editText.text.toString().trim()

        if (ed == null || ed.isEmpty()){

           editText.error = error

            return ""

        }else{

            return ed

        }

    }

    private fun checkToggleButton(onBtn : AppCompatRadioButton, offBtn : AppCompatRadioButton, defaultValue : String, normalValue : String, ck : String){

        val selectedBackground = ContextCompat.getDrawable(requireActivity(), R.drawable.sh_toggle_checked_button)
        val selectedTextColor = "#FFFFFF".toColorInt()

        val unSelectedBackground = ContextCompat.getDrawable(requireActivity(), R.drawable.sh_toggle_not_checked_button)
        val unSelectedTextColor = "#000000".toColorInt()

        onBtn.setTextColor(selectedTextColor)
        onBtn.background = selectedBackground

        offBtn.setTextColor(unSelectedTextColor)
        offBtn.background = unSelectedBackground

        if (ck == "c_home") categoryRadio = defaultValue else if (ck == "s_on") shippingRadio = defaultValue else billingRadio = defaultValue

        onBtn.setOnClickListener {

            onBtn.setTextColor(selectedTextColor)
            onBtn.background = selectedBackground

            offBtn.setTextColor(unSelectedTextColor)
            offBtn.background = unSelectedBackground

            if (ck == "c_home") categoryRadio = defaultValue else if (ck == "s_on") shippingRadio = defaultValue else billingRadio = defaultValue

        }

        offBtn.setOnClickListener {

            offBtn.setTextColor(selectedTextColor)
            offBtn.background = selectedBackground

            onBtn.setTextColor(unSelectedTextColor)
            onBtn.background = unSelectedBackground

            if (ck == "c_home") categoryRadio = normalValue else if (ck == "s_on") shippingRadio = normalValue else billingRadio = normalValue
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        addressDB.closeDB()
    }

}//public class==========================================================
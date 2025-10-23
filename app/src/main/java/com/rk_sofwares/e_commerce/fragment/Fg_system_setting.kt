package com.rk_sofwares.e_commerce.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.widget.SwitchCompat
import com.rk_sofwares.e_commerce.Other.StorageHelper
import com.rk_sofwares.e_commerce.R

class Fg_system_setting : Fragment() {

    //XML id's-----------------------------------------------------------

    //Relative layout
    private lateinit var rl_location : RelativeLayout;
    private lateinit var rl_microphone : RelativeLayout;
    private lateinit var rl_camera : RelativeLayout;
    private lateinit var rl_Gallery : RelativeLayout;

    //switch
    private lateinit var sc_loc : SwitchCompat;
    private lateinit var sc_mic : SwitchCompat;
    private lateinit var sc_cam : SwitchCompat;
    private lateinit var sc_pho : SwitchCompat;

    //other
    private lateinit var storageHelper: StorageHelper;

    //XML id's-----------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_system_setting, container, false)

        //identity period-------------------------------------------------------------

        rl_location = view.findViewById(R.id.rl_location);
        rl_microphone = view.findViewById(R.id.rl_microphone);
        rl_camera = view.findViewById(R.id.rl_camera);
        rl_Gallery = view.findViewById(R.id.rl_Gallery);

        sc_loc = view.findViewById(R.id.sc_loc);
        sc_mic = view.findViewById(R.id.sc_mic);
        sc_cam = view.findViewById(R.id.sc_cam);
        sc_pho = view.findViewById(R.id.sc_pho);

        //identity period-------------------------------------------------------------

        storageHelper = StorageHelper(requireActivity(), "systemSetting");

        getSwitchData("location", "locationOff", sc_loc, "locationOn");
        getSwitchData("microphone", "microphoneOff", sc_mic, "microphoneOn");
        getSwitchData("camera", "cameraOff", sc_cam, "cameraOn");
        getSwitchData("gallery", "galleryOff", sc_pho, "galleryOn");

        switchChecked(rl_location, sc_loc, "locationOn", "locationOff", "location");
        switchChecked(rl_microphone, sc_mic, "microphoneOn", "microphoneOff", "microphone");
        switchChecked(rl_camera, sc_cam, "cameraOn", "cameraOff", "camera");
        switchChecked(rl_Gallery, sc_pho, "galleryOn", "galleryOff", "gallery");


        return view
    }// on create=====================================================================

    private fun switchChecked(rl : RelativeLayout, sc: SwitchCompat, switchOn : String, switchOff : String, cacheKey : String){

        rl.setOnClickListener {

            if (sc.isChecked) sc.isChecked = false else sc.isChecked = true

            val value = if (sc.isChecked) switchOn else switchOff

            storageHelper.deleteData(cacheKey);
            storageHelper.setData(cacheKey, value);

        }

    }

    private fun getSwitchData(key : String, value : String, sc : SwitchCompat, defaultValue : String){

        val switch = storageHelper.getData(key);

        if (switch == null){

            sc.isChecked = false;

            storageHelper.setData(key, value);

        }else{

            if (switch == defaultValue) sc.isChecked = true else sc.isChecked = false

        }

    }

}// public class======================================================================
package com.nicomahnic.dadm.clase4.fragments.secondActivityFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.nicomahnic.dadm.clase4.R
import com.nicomahnic.dadm.clase4.activities.SecondActivity
import com.nicomahnic.dadm.clase4.databinding.FragmentDeviceDetailsBinding

class DeviceDetailsFragment : Fragment(R.layout.fragment_device_details) {

    private lateinit var binding: FragmentDeviceDetailsBinding
    private lateinit var v: View
    private val args: DeviceDetailsFragmentArgs by navArgs()

    override fun onViewCreated (view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDeviceDetailsBinding.bind(view)

        v = view

        val serviceName = args.deviceName
        val description = args.description

        binding.txtServiceTitle.text = serviceName
        binding.txtDescription.text = description

        Log.d("NM","HOLA ${SecondActivity.User.name} $serviceName ,$description")
    }

}
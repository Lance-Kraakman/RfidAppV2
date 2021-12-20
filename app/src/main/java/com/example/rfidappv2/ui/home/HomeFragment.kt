package com.example.rfidappv2.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rfidappv2.AddDevice
import com.example.rfidappv2.MainActivity
import com.example.rfidappv2.R
import com.example.rfidappv2.Register
import com.example.rfidappv2.databinding.FragmentHomeBinding
import com.example.rfidappv2.models.Device
import com.example.rfidappv2.mvvm.IViewModal
import com.example.rfidappv2.ui.fragments.DeviceListDialogFragment
import com.google.firebase.auth.FirebaseAuth
import java.util.ArrayList

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    private lateinit var iViewModal: IViewModal
    var deviceList: List<Device> = ArrayList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iViewModal = ViewModelProvider(this)[IViewModal::class.java]

        binding.buttonHomeAddDevice.setOnClickListener {
            val i = Intent(context, AddDevice::class.java)
            requireContext()!!.startActivity(i)


          /*  iViewModal.addNewDevice(
                FirebaseAuth.getInstance().currentUser?.uid,
                Device(
                    "demo1",
                    "demo1",
                    "demo3",
                    "demo4",
                    "demo3",
                    "demo4",
                    "testidf",
                    "lorem",
                    "lu"
                )
            )*/
        }
        binding.buttonHomeMyDevices.setOnClickListener {

            DeviceListDialogFragment.newInstance(0).show(requireActivity().supportFragmentManager, "dialog");
            //getist of all devices
           /* iViewModal.getGetAllListOfDevices(FirebaseAuth.getInstance().currentUser!!.uid)
                .observe(viewLifecycleOwner,
                    Observer<List<Device?>?> {
                        Log.d("TAG", "onViewCreated: all device" + it.size)
                    })*/
        }
        binding.buttonHomeRemoveDevice.setOnClickListener {

        }
        binding.buttonHomeAddStation.setOnClickListener {

        };
        binding.buttonHomeRemoveStation.setOnClickListener {

        };

        binding.buttonHomeMyStations.setOnClickListener {

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
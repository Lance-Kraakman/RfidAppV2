package com.example.rfidappv2.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rfidappv2.Device
import com.example.rfidappv2.R
import com.example.rfidappv2.databinding.FragmentDashboardBinding
import com.example.rfidappv2.MainActivity




class DashboardFragment : Fragment() {

    private lateinit var dashboardLinearLayout: LinearLayout;
    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null
    private var dashboardDevices: List<Device> = ArrayList()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.consoleView
//        dashboardViewModel._displayAllDevices.observe(viewLifecycleOwner, Observer {
//            Log.i("fuck", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
//            Log.i("off", it.toString())
//            textView.text = it.toString() // Not displaying properly but displaying enough :)
//        })

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
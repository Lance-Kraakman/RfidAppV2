package com.example.rfidappv2.ui.dashboard

import android.bluetooth.BluetoothClass
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rfidappv2.R
import com.example.rfidappv2.adapters.SearchAdapter
import com.example.rfidappv2.databinding.FragmentDashboardBinding
import com.example.rfidappv2.models.Device
import com.example.rfidappv2.models.Station
import com.example.rfidappv2.mvvm.IViewModal
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import java.util.ArrayList

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null
    var recylerview: RecyclerView? = null
    var search_box: TextInputEditText? = null
    var search_btn: ImageView? = null
    var adapter: SearchAdapter? = null

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


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
        bindActions()
        setupAdapter()
        getAllListOfDevices()
        getAllStations()
    }

    fun setupAdapter() {
        adapter = SearchAdapter();
        val manager: LinearLayoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        recylerview?.layoutManager = manager
        recylerview?.adapter = adapter;


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    lateinit var auth: FirebaseAuth;
    var userKey: String? = null

    lateinit var viewModel: IViewModal
    fun bindViews() {
        recylerview = binding.recylerDashbaord
        search_box = binding.editTextDeviceSearch
        search_btn = binding.searchBtn
    }

    fun bindActions() {
        auth = FirebaseAuth.getInstance();
        userKey = auth.currentUser?.uid
        search_box?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                filterData(editable.toString())

            }
        })
    }

    var listOfDevice: MutableList<Device> = ArrayList()
    fun getAllListOfDevices() {
        viewModel.getGetAllListOfDevices(userKey).observe(viewLifecycleOwner, Observer {
            listOfDevice = it;
        })

    }
    var listOfStations : MutableList<Station> = ArrayList()
    fun getAllStations(){
        viewModel.getGetAllStationsList(userKey).observe(viewLifecycleOwner, Observer {
            listOfStations = it;
        })
    }

    private fun filterData(query: String) {
        var filterList: MutableList<Device> = ArrayList()
        for (device in listOfDevice) {
            if (query.lowercase() == device.name.lowercase()) {
                filterList.add(device)
            }
        }
       // adapter?.setFilterList(filterList)
    }

    fun prepareStationWithDevices(){

    }

}
package com.example.rfidappv2.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rfidappv2.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    lateinit var notificationsViewModel: AboutViewModel
    private var _binding: FragmentAboutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(AboutViewModel::class.java)

        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications // 'creates a binding'
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it //u Creating a textView variable as an observer -> Copy this in our mainActivity and this should be how to do the live data thingy
        })
        notificationsViewModel.insert()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
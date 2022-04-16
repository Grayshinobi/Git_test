package com.example.chataddict.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.chataddict.R
import com.example.chataddict.databinding.FragmentContactsBinding

class ContactsFragment : Fragment() {
    private var _binding : FragmentContactsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this)[ContactsViewModel::class.java]

        _binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_contacts, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        /*@Suppress("DEPRECATION")
        binding.bottomNav.setOnNavigationItemReselectedListener { item ->
            when (item.itemId){
                R.id.contacts -> {requireView().findNavController().navigate(R.id.action_conversation_to_contacts)
                    return@setOnNavigationItemReselectedListener
                    true}
                R.id.conversation -> {requireView().findNavController().navigate(R.id.action_contacts_to_conversation)
                    return@setOnNavigationItemReselectedListener
                    true}

                else -> {
                    return@setOnNavigationItemReselectedListener
                    true}
            }

        }*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
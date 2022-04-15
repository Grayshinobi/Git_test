package com.example.chataddict.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.chataddict.R
import com.example.chataddict.databinding.FragmentContactsBinding
import com.example.chataddict.ui.contacts.ContactsFragmentDirections
import com.example.chataddict.ui.conversation.ConversationFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView

class ContactAdapter : Fragment() {
    private lateinit var binding  : FragmentContactsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_contacts)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        BottomNavigationView.OnNavigationItemReselectedListener { item->
            when(item.itemId){
                R.id.conversation -> { requireView().findNavController().navigate(ContactsFragmentDirections.actionContactsToConversation())}

                R.id.contacts ->{requireView().findNavController().navigate(ConversationFragmentDirections.actionConversationToContacts())}

                }
            }



        return binding.root

    }
}
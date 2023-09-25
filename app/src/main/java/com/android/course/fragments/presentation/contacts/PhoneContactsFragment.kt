package com.android.course.fragments.presentation.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.android.course.fragments.R
import com.android.course.fragments.data.repo.ContactsRepository
import com.android.course.fragments.di.GlobalDI


class PhoneContactsFragment : Fragment() {

    private val repo: ContactsRepository = GlobalDI.contactsRepo

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ContactsRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.phone_contacts_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ContactsRecyclerViewAdapter()
        adapter.setData(repo.getContacts().toList())
        recyclerView = view.findViewById(R.id.phone_contacts_recycler)
        recyclerView.adapter = adapter
        setRecyclerViewTouchHelper()
    }

    private fun setRecyclerViewTouchHelper() = ItemTouchHelper(
        ContactsRecyclerSimpleItemTouchHelper(
            onMove = adapter::onMove,
            onSwipe = adapter::onSwipe
        )
    ).attachToRecyclerView(recyclerView)

    companion object {
        fun newInstance(): PhoneContactsFragment = PhoneContactsFragment()
    }
}
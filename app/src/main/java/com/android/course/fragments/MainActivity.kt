package com.android.course.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.course.fragments.model.PhoneContact
import com.android.course.fragments.repo.ContactsRepository


class MainActivity : AppCompatActivity() {

    private lateinit var phoneContacts: List<PhoneContact>
    private val contactsRepo: ContactsRepository by lazy { ContactsRepository(contentResolver) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermissions()
        phoneContacts = contactsRepo.getContacts()

        //ToDo refactor
        val bundle = Bundle().apply {
            putParcelableArray(CONTACTS_KEY, phoneContacts.toTypedArray())
        }
        val contactsFragment = PhoneContactsFragment.newInstance()
            .apply {
                arguments = bundle
            }

        supportFragmentManager.beginTransaction()
            .add(R.id.phone_contacts_fragment_container_view_tag, contactsFragment)
            .commit()
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                listOf(
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ).toTypedArray(), 0
            )
        }
    }

    companion object {
        const val CONTACTS_KEY = "CONTACTS_KEY"
    }
}
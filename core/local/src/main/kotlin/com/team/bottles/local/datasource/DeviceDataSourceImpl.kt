package com.team.bottles.local.datasource

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DeviceDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : DeviceDataSource {

    override suspend fun getContacts(): List<String> {
        val contacts = mutableListOf<String>()
        val contentResolver: ContentResolver = context.contentResolver
        val cursor: Cursor? = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        cursor?.use {
            val numberIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

            while (it.moveToNext()) {
                val number = it.getString(numberIndex)
                contacts.add(number)
            }
        }

        return contacts
    }

}
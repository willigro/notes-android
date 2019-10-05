package com.rittamann.minichecklist.data.repository.config

import android.database.Cursor
import com.rittamann.minichecklist.data.base.Note
import com.rittamann.minichecklist.utils.DateUtil

object CursorDAO {

    fun item(cursor: Cursor) = Note()
        .apply {
            id = cursor.getLong(cursor.getColumnIndex(TableNote.ID))
            content = cursor.getString(cursor.getColumnIndex(TableNote.CONTENT))
            createCate = DateUtil.parseDate(cursor.getString(cursor.getColumnIndex(TableNote.CREATE_DATE)))
            checked = cursor.getInt(cursor.getColumnIndex(TableNote.CHECKED)) > 0
        }
}
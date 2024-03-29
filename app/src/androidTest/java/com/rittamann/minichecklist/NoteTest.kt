package com.rittamann.minichecklist

import com.rittamann.minichecklist.data.base.Note
import org.junit.Assert
import org.junit.Test
import java.util.*

class NoteTest {

    @Test
    fun new_item() {
        Assert.assertNotNull(Note())
    }

    @Test
    fun new_item_set_id_and_name() {
        val item = Note().apply {
            id = 1
            content = "New item"
        }
        Assert.assertNotNull(item)
        Assert.assertEquals(1, item.id)
        Assert.assertEquals("New item", item.content)
    }

    @Test
    fun new_item_set_date() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, 2)
        val item = Note().apply {
            createCate = calendar
        }
        val calendarTest = Calendar.getInstance()
        calendarTest.add(Calendar.DAY_OF_MONTH, 2)
        Assert.assertEquals(calendar.get(Calendar.DAY_OF_MONTH), item.getDay())
    }
}
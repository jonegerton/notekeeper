package com.ja2.notekeeper

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DataManagerTest {

    @Before
    fun setUp() {
        DataManager.notes.clear()
        DataManager.initializeNotes()
    }

    @Test
    fun addNote() {

        val course = DataManager.courses.get("android_async")!!
        var noteTitle = "This is a test note"
        val noteText = "This is the body of my test note"

        val index = DataManager.addNote(course,noteTitle,noteText)
        val note = DataManager.notes[index]

        assertSame("Course", course, note.course)
        assertSame("Title", noteTitle, note.title)
        assertSame("Text", noteText, note.text)
    }

    @Test
    fun findSimilarNote() {
        val course = DataManager.courses.get("android_async")!!
        var noteTitle = "This is a test note"
        val noteText1 = "This is the body of my test note"
        val noteText2 = "This is the body of my second test note"

        val index1 = DataManager.addNote(course,noteTitle,noteText1)
        val index2 = DataManager.addNote(course,noteTitle,noteText2)

        var note1 = DataManager.findNote(course, noteTitle, noteText1)
        var foundIndex1 = DataManager.notes.indexOf(note1)
        assertEquals(index1, foundIndex1)

        var note2 = DataManager.findNote(course, noteTitle, noteText2)
        var foundIndex2 = DataManager.notes.indexOf(note2)
        assertEquals(index2, foundIndex2)

    }

}
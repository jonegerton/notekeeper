package com.ja2.notekeeper

import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.*
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class CreateNewNoteTest {

    @Rule @JvmField
    val noteListActivity = ActivityScenarioRule(NoteListActivity::class.java)

    @Test
    fun createNewNote() {

        var noteTitle = "This is a test note"
        val noteText = "This is the body of my test note"
        var course = DataManager.courses["android_async"]

        onView(withId(R.id.fab)).perform(click())

        onView(withId(R.id.spinnerCourses)).perform(click())
        onData(allOf(instanceOf(CourseInfo::class.java), equalTo(course))).perform(click())

        onView(withId(R.id.textNoteTitle)).perform(typeText(noteTitle))
        onView(withId(R.id.textNoteText)).perform(typeText(noteText))

        closeSoftKeyboard()
        pressBack()

        val newNote = DataManager.notes.last()
        assertEquals("course", course, newNote.course)
        assertEquals("title", noteTitle, newNote.title)
        assertEquals("text", noteText, newNote.text)

    }

}
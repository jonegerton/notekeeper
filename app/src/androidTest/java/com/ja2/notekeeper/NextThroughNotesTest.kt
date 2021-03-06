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
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.*
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class NextThroughNotesTest {

    @Rule @JvmField
    val noteListActivity = ActivityScenarioRule(NoteListActivity::class.java)

    @Test
    fun nextThroughNotes() {

        onData(allOf(instanceOf(NoteInfo::class.java), equalTo(DataManager.notes[0]))).perform(click())

        for (index in 0..DataManager.notes.lastIndex) {
            var note = DataManager.notes[index]

            onView(withId(R.id.spinnerCourses)).check(
                matches(withSpinnerText(note.course?.title)))
            onView(withId(R.id.textNoteTitle)).check(
                matches(withText(note.title)))
            onView(withId(R.id.textNoteText)).check(
                matches(withText(note.text)))

            if (index != DataManager.notes.lastIndex) {
                onView(allOf(withId(R.id.action_next), isEnabled())).perform(click())
            }
        }

        onView(withId(R.id.action_next)).check(
            matches(not(isEnabled())))
    }

}
package com.ja2.notekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.ja2.notekeeper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val tag = this::class.simpleName
    private lateinit var binding: ActivityMainBinding
    private var notePosition = POSITION_NOT_SET

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapterCourses = ArrayAdapter<CourseInfo>(this,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList())
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.content.spinnerCourses.adapter = adapterCourses

        notePosition = savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOT_SET) ?:
            intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET)

        Log.v(tag,"notePosition: $notePosition")

        if (notePosition != POSITION_NOT_SET) {
            displayNote()
        } else {
            DataManager.notes.add(NoteInfo())
            notePosition = DataManager.notes.lastIndex
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                moveNext()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        if (notePosition >= DataManager.notes.lastIndex) {
            val menuItem = menu?.findItem(R.id.action_next)
            if (menuItem != null) {
                menuItem.icon = getDrawable(R.drawable.ic_baseline_block_24)
                menuItem.isEnabled = false
            }
        }
        return super.onPrepareOptionsMenu(menu)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(NOTE_POSITION, notePosition)
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    fun saveNote() {
        val note = DataManager.notes[notePosition]
        note.title = binding.content.textNoteTitle.text.toString()
        note.text = binding.content.textNoteText.text.toString()
        note.course = binding.content.spinnerCourses.selectedItem as CourseInfo
    }

    private fun moveNext() {
        notePosition += 1
        displayNote()
        invalidateOptionsMenu()
    }

    private fun displayNote() {
        var note = DataManager.notes[notePosition]
        binding.content.textNoteTitle.setText(note.title)
        binding.content.textNoteText.setText(note.text)

        val coursePosition = DataManager.courses.values.indexOf(note.course)
        binding.content.spinnerCourses.setSelection(coursePosition)
    }
}
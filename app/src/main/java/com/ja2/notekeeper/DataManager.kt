package com.ja2.notekeeper

object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initializeCourses()
        initializeNotes()
    }

    private fun initializeCourses() {
        var course = CourseInfo("android_intents", "Android Programming with Intents")
        courses.set(course.courseID, course)

        course = CourseInfo(courseID = "android_async", title = "Android Async Programming and Services")
        courses.set(course.courseID, course)

        course = CourseInfo(title = "Java Fundamentals: The Java Language", courseID = "java_lang")
        courses.set(course.courseID, course)

        course = CourseInfo("java_core", "Java Fundamentals: The Core Platform")
        courses.set(course.courseID, course)
    }

    private fun initializeNotes() {
        var note = NoteInfo(courses["android_intents"] as CourseInfo, "Dynamic intent resolution", "Wow, intents allow components to be resolved at runtime")
        notes.add(note)

        note = NoteInfo(courses["android_intents"] as CourseInfo, "Delegating intents", "PendingIntents are powerful; they delegate much more than just a component invocation")
        notes.add(note)

        note = NoteInfo(courses["android_async"] as CourseInfo, "Service default threads", "Did you know that by default and Android Service will tie up the UI thread?")
        notes.add(note)

        note = NoteInfo(courses["android_async"] as CourseInfo, "Long Running Operations", "Foreground Services can be tied to a notification icon")
        notes.add(note)

        note = NoteInfo(courses["java_lang"] as CourseInfo, "Parameters", "Leverage variable-length parameter lists")
        notes.add(note)

        note = NoteInfo(courses["java_lang"] as CourseInfo, "Anonymous Classes", "Anonymous classes simplify implementing one-use types")
        notes.add(note)

        note = NoteInfo(courses["java_core"] as CourseInfo, "Compiler Options", "The -jar option isn't compatible with the -cp option")
        notes.add(note)

        note = NoteInfo(courses["java_core"] as CourseInfo, "Serialization", "Remember to include SerialVersionUID to assure version compatibility")
        notes.add(note)
    }


}
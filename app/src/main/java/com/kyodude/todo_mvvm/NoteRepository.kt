package com.kyodude.todo_mvvm

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

class NoteRepository(application: Application) {
    private var noteDao: NoteDao
    private var allNotes: LiveData<List<Note>>
    init {
        val noteDatabase = NoteDatabase.getDatabase(application, GlobalScope)
        noteDao = noteDatabase.noteDao()
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note: Note) {
        GlobalScope.launch {
            noteDao.insert(note)
        }
    }

    fun update(note: Note) {
        GlobalScope.launch {
            noteDao.update(note)
        }
    }

    fun delete(note: Note) {
        GlobalScope.launch {
            noteDao.delete(note)
        }
    }

    fun deleteAllNotes() {
        GlobalScope.launch {
            noteDao.deleteAllNotes()
        }
    }

    fun getAllNotes():LiveData<List<Note>> {
        return allNotes
    }
}
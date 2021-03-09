package com.kyodude.todo_mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast

class AddEditNoteActivity : AppCompatActivity() {

    companion object {
        @JvmStatic val EXTRA_ID: String = "com.kyodude.todo_mvvm.EXTRA_ID"
        @JvmStatic val EXTRA_TITLE: String = "com.kyodude.todo_mvvm.EXTRA_TITLE"
        @JvmStatic val EXTRA_DESC: String = "com.kyodude.todo_mvvm.EXTRA_DESC"
        @JvmStatic val EXTRA_PRIORITY: String = "com.kyodude.todo_mvvm.EXTRA_PRIORITY"
    }

    lateinit var editTextTitle: EditText
    lateinit var editTextdesc: EditText
    lateinit var numberPicker: NumberPicker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        editTextTitle = findViewById(R.id.et_title)
        editTextdesc = findViewById(R.id.et_desc)
        numberPicker = findViewById(R.id.numberPicker)

        numberPicker.minValue = 1
        numberPicker.maxValue = 10

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        val intent = intent
        if(intent.hasExtra(EXTRA_ID))
        {
            title = "Edit Note"
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE))
            editTextdesc.setText(intent.getStringExtra(EXTRA_DESC))
            numberPicker.value = intent.getIntExtra(EXTRA_PRIORITY,0)
        }
        else
        {
            title = "Add Note"
        }

    }

    private fun saveNote() {
        val title = editTextTitle.text.toString()
        val desc = editTextdesc.text.toString()
        val priority = numberPicker.value

        if(title.trim().isEmpty()|| desc.trim().isEmpty())
        {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show()
            return
        }

        val intent2 = Intent()
        intent2.putExtra(EXTRA_TITLE,title)
        intent2.putExtra(EXTRA_DESC, desc)
        intent2.putExtra(EXTRA_PRIORITY, priority)

        val id = intent.getIntExtra(EXTRA_ID, -1)

        if(id != -1) intent2.putExtra(EXTRA_ID, id)

        setResult(RESULT_OK, intent2)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.add_note, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_note -> {
                saveNote()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}
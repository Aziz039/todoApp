package com.example.todoapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginLeft
import androidx.core.view.setPadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

lateinit var clMain: ConstraintLayout

lateinit var rc_todoNotes: RecyclerView
lateinit var fb_addNewToDo: FloatingActionButton
lateinit var bt_deleteAll: MenuItem

lateinit var tempArrayList: ArrayList<String>

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clMain = findViewById(R.id.clMain)

        rc_todoNotes = findViewById(R.id.rc_todoNotes)

        fb_addNewToDo = findViewById(R.id.fb_addNewToDo)
        fb_addNewToDo.setOnClickListener { handleAddNewToDo() }

//        bt_deleteAll = findViewById(R.id.bt_deleteAll)
//        bt_deleteAll.setOnMenuItemClickListener { handlerDeleteAllNotes() }

        tempArrayList = ArrayList<String>()

    }

    fun handleAddNewToDo() {
        Log.d("RecycleAdapter", "New I was clicked!")
        customAlert()
    }

    private fun customAlert(){
        // first we create a variable to hold an AlertDialog builder
        val dialogBuilder = AlertDialog.Builder(this)

        // Set up the input
        val input = EditText(this)
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setHint("Enter new todo..")
        input.inputType = InputType.TYPE_CLASS_TEXT
        input.setPadding(30)
        dialogBuilder.setView(input)

        // positive button text and action
        dialogBuilder.setPositiveButton("Add", DialogInterface.OnClickListener {
                    dialog, id ->  handleNewTodoText(input)
            }).setNegativeButton("Cancel", DialogInterface.OnClickListener{
                dialog, id -> Log.d("RecycleAdapter", "Canceled")
            })
        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("New item:")

        // show alert dialog
        alert.show()
    }


    fun handleNewTodoText(input: EditText) {
        Log.d("RecycleAdapter", "Added note!")
        if (input.text.isNullOrBlank()) {
            Snackbar.make(clMain, "Please insert a text to add note..", Snackbar.LENGTH_LONG).show()
        } else {
            tempArrayList.add(input.text.toString())
            rc_todoNotes.adapter = RecyclerAdapter(tempArrayList)
            rc_todoNotes.layoutManager = LinearLayoutManager(this)
        }
    }
//
//    fun handlerDeleteAllNotes():Boolean {
//        tempArrayList = ArrayList<String>()
//        rc_todoNotes.adapter = RecyclerAdapter(tempArrayList)
//        rc_todoNotes.layoutManager = LinearLayoutManager(this)
//        return true
//    }

    // menu functions
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.bt_deleteAll -> {
                val allNotesSize = tempArrayList.size
                Toast.makeText(this, "${tempArrayList.size} notes had been deleted!", Toast.LENGTH_LONG).show()
                tempArrayList = ArrayList<String>()
                rc_todoNotes.adapter = RecyclerAdapter(tempArrayList)
                rc_todoNotes.layoutManager = LinearLayoutManager(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
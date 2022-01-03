package com.reemsd.todolist_app

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.get


class MainActivity : AppCompatActivity() {


    lateinit var inputTask: EditText
    private lateinit var Tasklistview: ListView
    var add_task: Button? = null
    lateinit var checkboxtask: CheckBox
    var taskview = ArrayList<String>()
    private var Adapter: ArrayAdapter<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputTask = findViewById(R.id.input_task)
        Tasklistview = findViewById(R.id.ToDolist)
        add_task = findViewById(R.id.add_task)



        add_task?.setOnClickListener {
            AddTask()
        }

    }

    fun AddTask() {
        var text = inputTask.text.toString()

        if (text.isEmpty()) {
            Toast.makeText(this, "please Enter your task", Toast.LENGTH_SHORT).show()


        } else {
            taskview.add(text)
            Adapter = ArrayAdapter(this, R.layout.item_list, R.id.title, taskview)
            Tasklistview.adapter = Adapter
        }
    }

    fun DeleteTask(view: View) {
        val parent: View = view.parent as View
        val tasktextview: TextView = parent.findViewById<TextView>(R.id.title)
        var task: String = tasktextview.text.toString()
        taskview.removeAt(taskview.indexOf(task))
        Adapter?.notifyDataSetChanged()
    }

    fun Update(view: View) {
        val parent: View = view.parent as View
        val tasktextview: String = parent.findViewById<TextView>(R.id.title).text.toString()
        var index: Int = taskview.indexOf(tasktextview)
        var taskEditText = EditText(this)
        var dialog = AlertDialog.Builder(this)
            .setTitle("Update")
            .setView(taskEditText)
            .setPositiveButton("Update", DialogInterface.OnClickListener { dialog, which ->
                if (taskEditText.text.isEmpty()) {
                    Toast.makeText(this, "please Enter your task", Toast.LENGTH_SHORT).show()
                } else {
                    taskview.set(index, taskEditText.text.toString())
                }
                Adapter?.notifyDataSetChanged()
            })
            .setNegativeButton("cancel", null)
            .create()
        dialog.show()


    }

    fun checkbox(view: View): Boolean {
        val parent: View = view.parent as View
        val  checkboxtask: CheckBox = parent.findViewById(R.id.check)
        Toast.makeText(this, "fun checkbox ", Toast.LENGTH_SHORT).show()
        return checkboxtask.isChecked
    }
}
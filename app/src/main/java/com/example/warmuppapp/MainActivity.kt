package com.example.warmuppapp

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.warmuppapp.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var lvTodoList: ListView
    private lateinit var fab: FloatingActionButton
    private lateinit var shoppingItems: ArrayList<String>
    private lateinit var itemAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lvTodoList = findViewById(R.id.lvtodoList)
        fab = findViewById(R.id.floatingActionButton)
        shoppingItems = ArrayList()
        shoppingItems.add("Apfel")
        shoppingItems.add("Banane")

        itemAdapter= ArrayAdapter(this, android.R.layout.simple_list_item_1, shoppingItems)
        lvTodoList.adapter=itemAdapter

        lvTodoList.onItemLongClickListener = OnItemLongClickListener { arg0, arg1, pos, id ->
            shoppingItems.removeAt(pos)
            itemAdapter.notifyDataSetChanged()
            Toast.makeText(applicationContext,"Element Deleted!",Toast.LENGTH_SHORT).show()
            true
        }

        fab.setOnClickListener {
            var builder = AlertDialog.Builder(this)

            builder.setTitle("Add!")
            var input = EditText(this)
            input.hint="Input Text"
            input.inputType= InputType.TYPE_CLASS_TEXT
            builder.setView(input)
            builder.setPositiveButton("Ok."){ _, _ ->
                shoppingItems.add(input.text.toString())
            }
            builder.setNegativeButton("Cancel"){ _, _ ->
                Toast.makeText(applicationContext,"Canceled!",Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }


    }
}
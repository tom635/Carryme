package com.example.carryme.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carryme.Adapter.ShoppingAdapter
import com.example.carryme.R
import com.example.carryme.data.db.ShoppingDatabase
import com.example.carryme.data.db.entities.ShoppingItem
import com.example.carryme.data.repositories.ShoppingRepository
import kotlinx.android.synthetic.main.activity_shopping.*
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(),KodeinAware {
    override val kodein by  kodein()
    private val factory:ShoppingViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)
        val viewModel=ViewModelProviders.of(this,factory).get(ShoppingViewModel::class.java)
        val adapter=ShoppingAdapter(listOf(),viewModel)
        rvShoppingitems.layoutManager=LinearLayoutManager(this)
        rvShoppingitems.adapter=adapter
       viewModel.getAllShoppingItems().observe(
           this, Observer  {
               adapter.items=it
               adapter.notifyDataSetChanged()

            }
       )
        fab.setOnClickListener{
            AddShoppingItemDialog(this,object :AddDialogListner{
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }



}
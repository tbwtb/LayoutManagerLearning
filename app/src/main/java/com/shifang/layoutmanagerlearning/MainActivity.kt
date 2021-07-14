package com.shifang.layoutmanagerlearning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shifang.layoutmanagerlearning.databinding.ActivityMainBinding
import com.shifang.layoutmanagerlearning.databinding.ItemTestBinding

class MainActivity : AppCompatActivity() {

    private val inflate by lazy { ActivityMainBinding.inflate (layoutInflater) }
    val array by lazy { arrayOf(1,2,10, 4, 6, 145, 12, 1516,12,33,44,556,78,123,45345,123123,34563456,123412,2345345234123,12312312125198) }
    private val adapter by lazy { CustomAdapter(array) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(inflate.root)
        val adapter = adapter
        inflate.rv.layoutManager = LinearLayoutManager(this)
        inflate.rv.adapter = adapter

    }

    inner class CustomAdapter(private val array: Array<Long>) : RecyclerView.Adapter<CustomViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            println("zjy onCreateViewHolder")
            return CustomViewHolder(ItemTestBinding.inflate(LayoutInflater.from(parent.context)))
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            println("zjy onBindViewHolder")
            holder.bind("position : ${array[position]}")
        }

        override fun getItemCount(): Int {
            return array.size
        }

    }

    inner class CustomViewHolder(private val item: ItemTestBinding) : RecyclerView.ViewHolder(item.root) {
        fun bind(txt: String) {
            item.tvText.text = txt
        }
    }
}
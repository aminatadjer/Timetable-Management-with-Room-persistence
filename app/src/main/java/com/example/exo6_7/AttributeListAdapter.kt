package com.example.exo6_7

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_attribute.view.*
import kotlin.reflect.typeOf

class AttributeListAdapter(activity: Activity,type:String,private val attributesList : ArrayList<AttributeWithSize>)
    : RecyclerView.Adapter<AttributeListAdapter.AttributeViewHolder>(){
    val typo=type
    val activity=activity
    override fun getItemCount() = attributesList.size

    class AttributeViewHolder(v : View) : RecyclerView.ViewHolder(v){

        val attributeName = v.findViewById<TextView>(R.id.attributeName)
        val sizeAttribute = v.findViewById<TextView>(R.id.size)
        val layout=v.findViewById<CardView>(R.id.cardAttribute)

        fun bindAttribute(attr: AttributeWithSize) {
            attributeName.text=attr.attribute
            sizeAttribute.text=attr.size.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttributeViewHolder {
        val inflatedView = parent.inflate(R.layout.attribute_item, false)
        return AttributeViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: AttributeListAdapter.AttributeViewHolder, position: Int) {
        val item = attributesList[position]
        holder.bindAttribute(item)
        holder.layout.setOnClickListener {

            val showSeanceIntent = Intent(activity, SeanceActivity::class.java)
            showSeanceIntent.putExtra("attribut",  typo)
            showSeanceIntent.putExtra("valeur",  holder.attributeName.text)
            activity.startActivity(showSeanceIntent)
        }


    }


}
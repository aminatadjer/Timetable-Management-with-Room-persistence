package com.example.exo6_7

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_attribute.view.*

class SeanceListAdapter(private val seanceList : ArrayList<Seance>)
    : RecyclerView.Adapter<SeanceListAdapter.AttributeViewHolder>(){

    override fun getItemCount() = seanceList.size

    class AttributeViewHolder(v : View) : RecyclerView.ViewHolder(v){
        val jour = v.findViewById<TextView>(R.id.jour)
        val heure = v.findViewById<TextView>(R.id.heure)
        val salle = v.findViewById<TextView>(R.id.salle)
        val nomSeance= v.findViewById<TextView>(R.id.nomSeance)

        fun bindAttribute(attr: Seance) {
            jour.text=attr.jour
            heure.text=attr.hDebut
            salle.text=attr.salle
            nomSeance.text=attr.module+"  -"+attr.ens

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttributeViewHolder {
        val inflatedView = parent.inflate(R.layout.seance_item, false)
        return AttributeViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: SeanceListAdapter.AttributeViewHolder, position: Int) {
        val item = seanceList[position]
        holder.bindAttribute(item)

    }


}
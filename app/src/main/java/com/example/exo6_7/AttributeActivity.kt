package com.example.exo6_7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_attribute.*


class AttributeActivity : AppCompatActivity() {
    var attributesList = arrayListOf<AttributeWithSize>()
    lateinit var calendarDB : SeanceDB

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: AttributeListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attribute)
        linearLayoutManager = LinearLayoutManager(this)
        calendarDB = SeanceDB.getDB(this)
        val type = intent.getStringExtra("typeAttribut")
        when(type){
            "semaine" -> attributesList.addAll(calendarDB.seanceDao().getAllSemaine())
            "jour" -> attributesList.addAll(calendarDB.seanceDao().getAllJour())
            "module" -> attributesList.addAll(calendarDB.seanceDao().getAllModule())
            "salle" -> attributesList.addAll(calendarDB.seanceDao().getAllSalle())
            "enseignant" -> attributesList.addAll(calendarDB.seanceDao().getAllEns())
        }
        nomAttribut.text="Liste des "+type+"s"
        recyclerView.layoutManager = linearLayoutManager
        adapter = AttributeListAdapter(this,type,attributesList)
        recyclerView.adapter = adapter

}
}
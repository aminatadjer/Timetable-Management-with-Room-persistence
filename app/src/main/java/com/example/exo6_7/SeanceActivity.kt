package com.example.exo6_7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_attribute.*
import kotlinx.android.synthetic.main.activity_seance.*


class SeanceActivity : AppCompatActivity() {
    var seanceList = arrayListOf<Seance>()
    lateinit var calendarDB : SeanceDB

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: SeanceListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seance)
        linearLayoutManager = LinearLayoutManager(this)
        calendarDB = SeanceDB.getDB(this)

        val type = intent.getStringExtra("attribut")
        val valeur = intent.getStringExtra("valeur")
        when(type){
            "semaine" -> seanceList.addAll(calendarDB.seanceDao().getSeanceBySemaine(valeur))
            "jour" -> seanceList.addAll(calendarDB.seanceDao().getSeanceByJour(valeur))
            "module" -> seanceList.addAll(calendarDB.seanceDao().getSeanceByModule(valeur))
            "salle" -> seanceList.addAll(calendarDB.seanceDao().getSeanceBySalle(valeur))
            "enseignant" -> seanceList.addAll(calendarDB.seanceDao().getSeanceByEnseignant(valeur))
        }
        nomAttribut1.text=valeur
        listeSeances.layoutManager = linearLayoutManager
        adapter =SeanceListAdapter(seanceList)
        listeSeances.adapter = adapter

    }
}
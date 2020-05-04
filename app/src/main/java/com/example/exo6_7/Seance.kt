package com.example.exo6_7

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "seance")
class Seance( @PrimaryKey var id : Int,
              var jour:String,
              var hDebut:String,
              var hFin:String,
              var module:String,
              var salle:String,
              var ens:String,
              var semaine:String

) {


}
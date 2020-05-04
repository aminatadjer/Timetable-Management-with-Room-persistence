package com.example.exo6_7

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SeanceDao {
//Get seance By *
    @Query("Select * from seance")
    fun getAllSeance(): List<Seance>

    @Query("Select * from seance where semaine= :semaine")
    fun getSeanceBySemaine(semaine:String): List<Seance>

    @Query("Select * from seance where jour= :jour")
    fun getSeanceByJour(jour:String): List<Seance>

    @Query("Select * from seance where module= :module")
    fun getSeanceByModule(module:String): List<Seance>

    @Query("Select * from seance where salle= :salle")
    fun getSeanceBySalle(salle:String): List<Seance>

    @Query("Select * from seance where ens= :ens")
    fun getSeanceByEnseignant(ens:String): List<Seance>


// Get * from seance with count

    @Query("Select semaine attribute, count(*) size from seance group by semaine")
    fun getAllSemaine():List<AttributeWithSize>

    @Query("Select jour attribute, count(*) size from seance group by jour")
    fun getAllJour():List<AttributeWithSize>

    @Query("Select module attribute, count(*) size from seance group by module")
    fun getAllModule():List<AttributeWithSize>

    @Query("Select salle attribute, count(*) size from seance group by salle")
    fun getAllSalle():List<AttributeWithSize>

    @Query("Select ens attribute, count(*) size from seance group by ens")
    fun getAllEns():List<AttributeWithSize>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewSeance(seance: Seance)


}
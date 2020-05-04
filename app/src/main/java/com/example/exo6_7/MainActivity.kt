package com.example.exo6_7

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.exo6_7.AttributeActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val PERMISSION: Int = 1000
    lateinit var calendarDB : SeanceDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calendarDB = SeanceDB.getDB(this)
        //Lire du fichier Gson
        loadEdtToDB()
        parModule.setOnClickListener {
            val a = Intent(this, AttributeActivity::class.java)
            a.putExtra("typeAttribut","module")
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(a)
        }
        parSemaine.setOnClickListener {
            val a = Intent(this, AttributeActivity::class.java)
            a.putExtra("typeAttribut","semaine")
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(a)
        }
        parJour.setOnClickListener {
            val a = Intent(this, AttributeActivity::class.java)
            a.putExtra("typeAttribut","jour")
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(a)
        }
        parSalle.setOnClickListener {
            val a = Intent(this, AttributeActivity::class.java)
            a.putExtra("typeAttribut","salle")
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(a)
        }
        parEns.setOnClickListener {
            val a = Intent(this, AttributeActivity::class.java)
            a.putExtra("typeAttribut","enseignant")
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(a)
        }
        addToCalendar.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
                val permission = arrayOf(Manifest.permission.WRITE_CALENDAR)
                requestPermissions(permission, PERMISSION)
            }else {
                addToCalendar()
            }

        }
    }
    fun loadEdtToDB(){
        val utils:Utils= Utils()
        val jsonFileString = utils.getJsonDataFromAsset(applicationContext, "edt.json")
        Log.i("data", jsonFileString)
        val gson = Gson()
        val listSeanceType = object : TypeToken<List<Seance>>() {}.type
        var seances: List<Seance> = gson.fromJson(jsonFileString, listSeanceType)
        seances.forEachIndexed { idx, seance ->
            val id = seance.id
            val jour = seance.jour
            val semaine = seance.semaine
            val hDebut = seance.hDebut
            val hFin = seance.hFin
            val module = seance.module
            val salle = seance.salle
            val ens = seance.ens
            val seanceToAdd=Seance(id,jour,hDebut,hFin,module,salle, ens, semaine)
            calendarDB.seanceDao().insertNewSeance(seanceToAdd)
        }
        Toast.makeText(this, "L'emploi du temps a été inséré en BDD avec succès", Toast.LENGTH_LONG).show()
    }
    @SuppressLint("MissingPermission")
    fun addToCalendar(){
        val seances= calendarDB.seanceDao().getAllSeance()
        for (seance in seances){

            val jour = seance.jour
            val hDebut = seance.hDebut
            val hFin = seance.hFin
            val date = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)
            val calendarD = Calendar.getInstance()
            calendarD.time = date.parse("$jour $hDebut")
            val calendarF = Calendar.getInstance()
            calendarF.time = date.parse("$jour $hFin")
            val v = ContentValues().apply {
                val ID: Long = 1
                put(CalendarContract.Events.CALENDAR_ID, ID)
                put(CalendarContract.Events.TITLE, "${seance.module} ${seance.ens}")
                put(CalendarContract.Events.DTSTART, calendarD.timeInMillis)
                put(CalendarContract.Events.DTEND, calendarF.timeInMillis)
                put(CalendarContract.Events.DESCRIPTION, seance.salle)
                put(CalendarContract.Events.EVENT_TIMEZONE, "Africa/Algiers")
            }
            val uri: Uri? = contentResolver.insert(CalendarContract.Events.CONTENT_URI, v)
            Toast.makeText(this, "L'emploi du temps a été ajouté au calendrier de votre téléphone", Toast.LENGTH_LONG).show()
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    addToCalendar()
                } else {
                    Toast.makeText(this, "Impossible", Toast.LENGTH_SHORT)
                }
                return
            }
        }
    }
}
